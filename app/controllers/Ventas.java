package controllers;
 
import models.*;
import prints.*;
import java.util.Date;
import play.db.Model;
import play.data.validation.*;
import play.data.binding.Binder;
import play.data.binding.NoBinding;
import play.data.binding.As;
import java.lang.reflect.Constructor;
import play.exceptions.TemplateNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import java.math.RoundingMode;
import java.util.List;
import java.util.Iterator;



import play.*;
import play.mvc.*;
 
@With(Secure.class) 
@CRUD.For(Venta.class)
public class Ventas extends CRUD {

	static List<DetalleProducto> detallesVenta;

    	public static User getUsrLogueado(){
		return 	User.find("byEmail", Security.connected()).first();
    	}

	public static void blank(){
		User usrLogueado = getUsrLogueado();
		boolean ok= false;
		detallesVenta=null;
		render(usrLogueado, ok );
	}

	@Check("admin")
    	public static void show(Long id) throws Exception {

	User usrLogueado = getUsrLogueado();	

	Venta object= Venta.findById(id);

	List<DetalleProducto> detallesProducto= DetalleProducto.find("venta", object).asList();
	System.out.println("haberrr "+ detallesProducto.size());
       	notFoundIfNull(object);

        try {
            render(object, usrLogueado, detallesProducto);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", object, usrLogueado);
        }
    }

	@Check("admin")
    public static void list(int page, String search, String searchFields, String orderBy, String order) {

	User usrLogueado = getUsrLogueado();	
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }

	boolean isReporte=false;
	String url1 = "/reportes/ventas/list";
	String url2 = "/reportes/ventas/list/";
	if (url1.equals(request.url) || url2.equals(request.url))
	{
		isReporte=true;
	}

        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, (String) request.args.get("where"));
        Long count = type.count(search, searchFields, (String) request.args.get("where"));
        Long totalCount = type.count(null, null, (String) request.args.get("where"));
        try {
            render(type, objects, count, totalCount, page, orderBy, order, isReporte, usrLogueado);
        }

       	catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order, isReporte, usrLogueado);
        }

    }



    public static void ajaxDetalleVenta(String id, String peso, String oldTotal, String oldSubTotal, int tipoComprobante){

	    boolean chkAprobado = false;
            User usrLogueado = getUsrLogueado();
            Sede sede= usrLogueado.getSede();
	    Producto producto= Producto.find("idVenta", id).first();
	    String error="";
	    UUID identificador=null;
   	    BigDecimal precio=new BigDecimal("0.00");
	    BigDecimal subTotal=new BigDecimal(oldSubTotal);
	    BigDecimal precioTotal=new BigDecimal(oldTotal);

	    if ( producto != null)
	    {
			int items=0;
			if ( new BigDecimal(producto.cantidad).compareTo(new BigDecimal(peso)) > 0 )
			{
			        precio =new BigDecimal(producto.getPrecioVenta() );
				precio= precio.multiply(new BigDecimal(peso));
				precio = precio.setScale(2, RoundingMode.DOWN);
				int n=precio.toString().length() -1;
			    	int x = Character.getNumericValue(precio.toString().charAt(n));
				if (x < 5)
					precio = precio.subtract(new BigDecimal("0.0"+ String.valueOf(x)));
				
				subTotal=subTotal.add(precio);

				if( tipoComprobante > 1){

					BigDecimal igv=subTotal.multiply(new BigDecimal(sede.igv));
					precioTotal=subTotal.add(igv);
					
					precioTotal= precioTotal.setScale(2, RoundingMode.DOWN);
					n=precioTotal.toString().length() -1;
				    	x = Character.getNumericValue(precioTotal.toString().charAt(n));
					if (x < 5)
						precioTotal= precioTotal.subtract(new BigDecimal("0.0"+ String.valueOf(x)));
				}
				else precioTotal=subTotal;

				//creamos el detalle
				DetalleProducto dt = new DetalleProducto(producto, peso, producto.precioVenta);
		 		identificador= UUID.randomUUID();
				dt.setUid(identificador.toString());
				if(detallesVenta == null)
					detallesVenta= new ArrayList<DetalleProducto>();
				detallesVenta.add(dt);

				items=detallesVenta.size();


				//descontamos 
				BigDecimal cantidad = new BigDecimal(producto.cantidad);
				cantidad=cantidad.subtract(new BigDecimal(peso));
				producto.setCantidad(cantidad.toString());

				chkAprobado=true;
			}
			else{
				error="Stock insuficiente. Stock actual: "+ producto.cantidad;
			}

	    		render(identificador, chkAprobado, precioTotal, precio, producto, peso, error, items, subTotal);
	    }
	    else{
		error="Problemas, revise el codigo de venta";
		render(chkAprobado, error);
	    } 
    }

    public static void ajaxValidaImporte(String a, String b, String c){
	blank();
    }

    public static void chkSoloEfectivo(String t,String pago){
	    BigDecimal pagoCon = new BigDecimal(pago);
	    BigDecimal total = new BigDecimal(t);
	    BigDecimal cambio= new BigDecimal("0.00");
	    int op = pagoCon.compareTo(total);
	    boolean isError=true;
	    if ( op >= 0)
	    {
		isError=false;
	    	cambio= pagoCon.subtract(total);
	    }
	    render( isError, cambio);
    }

    public static void ajaxFormaDePago(int i){
	render(i);
    }

    public static void ajaxTotal(String total, String subTotal, String igv){
	render(total, subTotal, igv);
    }

    public static void ajaxConfirmaVentaSoloEfectivo( @As(",") List<String> items, String pago,String vuelto,int tipoComprobante, String mTotal ){
	
	User usrLogueado = getUsrLogueado();
        Sede sede= usrLogueado.getSede();
	boolean ok= false;
	if ( usrLogueado.getIsAbrirCaja()){
	if(detallesVenta != null){
		if (items != null)
		{
			Venta venta= new Venta();
			venta.save();
			int cantidadProductos=0;
			BigDecimal montoTotal= new BigDecimal(mTotal);

			Iterator it2 = items.iterator();
			while(it2.hasNext())
			{
				String item_i= (String)it2.next();
				Iterator it = detallesVenta.iterator();
				while(it.hasNext())
				{
					DetalleProducto detalle_i= (DetalleProducto)it.next();
					if(item_i.equals(detalle_i.getUid()))
					{
						detalle_i.setVenta(venta);
						detalle_i.save();
						cantidadProductos++;
						break;
					}
				}
			}

			if( tipoComprobante > 1){
				venta.igv="0.19";
			}

			venta.cantidadProductos=cantidadProductos;
			venta.pagoCon=pago;
			venta.sede=sede;
			venta.userCreo=usrLogueado;
			venta.cambio=vuelto;
			venta.montoTotal=montoTotal.toString();
			ok= true;
			venta.save();

			//el comprobante
			Comprobante comprobante = new Comprobante( );
			comprobante.setVenta(venta);
			comprobante.setUserCreo(usrLogueado);
			comprobante.setSede(sede);
			comprobante.setTotal(venta.montoTotal.toString());
			comprobante.setSubTotal(montoTotal.toString());
			if (tipoComprobante == 0){
				Integer i = (int) (long) new Date().getTime()/1000;
				comprobante.setCorrelativo(i) ;	
			}
			if (tipoComprobante == 1){
				int correlativo=  sede.correlativoBoleta;
				comprobante.setCorrelativo(correlativo);	
				// update el correlativo
				sede.correlativoBoleta= correlativo+1; 
				sede.save();
			}

        		flash.success("Se realizo con exito la venta", venta);
			new ComprobanteVenta( comprobante); // imprime el papel fisico

		}
	}

	}

	detallesVenta=null;
		
        render("Ventas/blank.html", usrLogueado, ok);

	//render(ok);
    }

    public static void ajaxTipoDocumento(int tipoComprobante, String montoT, String subT){
	User usrLogueado = getUsrLogueado();
        Sede sede= usrLogueado.getSede();
	BigDecimal montoTotal = new BigDecimal(montoT);
	BigDecimal subTotal= new BigDecimal(subT);
	BigDecimal igv = new BigDecimal("0.00");

	if( tipoComprobante > 1){
		igv=subTotal.multiply(new BigDecimal(sede.igv));
		montoTotal=subTotal.add(igv);
	}
	else{
		igv=new BigDecimal("0.00");
		montoTotal=subTotal;
	} 

	montoTotal= montoTotal.setScale(2, RoundingMode.DOWN);
	int n=montoTotal.toString().length() -1;
    	int x = Character.getNumericValue(montoTotal.toString().charAt(n));
	if (x < 5)
		montoTotal= montoTotal.subtract(new BigDecimal("0.0"+ String.valueOf(x)));

	igv= igv.setScale(2, RoundingMode.DOWN);
	n=igv.toString().length() -1;
    	x = Character.getNumericValue(igv.toString().charAt(n));
	if (x < 5)
		igv= igv.subtract(new BigDecimal("0.0"+ String.valueOf(x)));

	render(montoTotal, subTotal, igv);
    }


    public static void modalClientes(){
		User usrLogueado = getUsrLogueado();
		List<Cliente> clientes= Cliente.find("isEliminado", false ).asList();
		render(usrLogueado, clientes);
   }

}
