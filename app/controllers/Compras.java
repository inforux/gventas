package controllers;
 
import models.*;
import play.db.Model;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import play.data.validation.*;
import play.data.binding.Binder;
import play.data.binding.NoBinding;
import play.data.binding.As;
import java.lang.reflect.Constructor;
import play.exceptions.TemplateNotFoundException;
import java.util.List;
import java.math.BigDecimal;



import play.*;
import play.mvc.*;
 
@With(Secure.class) 
@CRUD.For(Compra.class)
public class Compras extends CRUD {    

   static List<DetalleProducto> detallesCompra;

   public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
   }


    public static void show(Long id) throws Exception {
	User usrLogueado = getUsrLogueado();	

	validation.match(id, "[a-z,0-9]");	
	if (validation.hasErrors()) 
		id= null;
       	notFoundIfNull(id);

	Compra object= Compra.findById(id);
       	notFoundIfNull(object);

        try {
            render(object, usrLogueado);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", object, usrLogueado);
        }
    }

	public static void blank(){
		User usrLogueado = getUsrLogueado();
		boolean ok= false;
		List<Proveedor> proveedores= Proveedor.find("isEliminado", false).asList();

		detallesCompra=null;
		render(usrLogueado, proveedores, ok );
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
	String url1 = "/reportes/compras/list";
	String url2 = "/reportes/compras/list/";
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

    public static void modalDetalleProducto(){
		User usrLogueado = getUsrLogueado();
		List<Producto> productos= Producto.find("isEliminado,sede", false, usrLogueado.sede).asList();

		render(usrLogueado, productos );
   }

    public static void ajaxTotalCompra(String total){
	render(total);
    }

    public static void ajaxDetalleCompra(String peso, String precio, Long idProducto, String oldTotal){
	    	boolean chkAprobado= false;
		int items=0;
		User usrLogueado = getUsrLogueado();
		String error="";
	        UUID identificador=null;
		Producto producto= Producto.findById(idProducto);
		BigDecimal peso1= new BigDecimal("0.00");
		BigDecimal precio1= new BigDecimal("0.00");
		BigDecimal total= new BigDecimal("0.00");
		if ( oldTotal == null)
			oldTotal= "0.00";
		BigDecimal montoTotal= new BigDecimal(oldTotal);
		if ( producto != null)
		{
			if (peso.length() > 0  && precio.length() >0 )
			{
				peso1= new BigDecimal(peso);
				precio1= new BigDecimal(precio);
				total = peso1.multiply(precio1); 
				montoTotal= montoTotal.add(total);

				//creamos el detalle
				DetalleProducto dt = new DetalleProducto(producto, peso, precio);
		 		identificador= UUID.randomUUID();
				dt.setUid(identificador.toString());
				if(detallesCompra== null)
					detallesCompra= new ArrayList<DetalleProducto>();
				detallesCompra.add(dt);

				items=detallesCompra.size();



				chkAprobado=true;

			}
			else 	error="Ingresar valores para peso y precio";

		}
		else{
			error="No se pudo encontrar el producto";
		}
		render(usrLogueado, producto, peso, precio, total, chkAprobado, error, montoTotal, items, identificador );
   }

   public static void ajaxGenerarComprobante(){

		Integer serie = (int) (long) new Date().getTime()*1000;
		System.out.println("serie: "+ serie);
		render(serie);
   }

   public static void confirmaCompra(){
	render();
   }

    public static void ajaxConfirmaCompra( @As(",") List<String> items, String idProveedor,String idComprobante, String mTotal ){
	User usrLogueado = getUsrLogueado();
        Sede sede= usrLogueado.getSede();
	BigDecimal montoTotal= new BigDecimal(mTotal);
	Producto producto= null;
	boolean ok= false;
	if(detallesCompra!= null){
		if (items != null)
		{
			Compra compra= new Compra();
			Proveedor proveedor= Proveedor.findById(new Long(idProveedor));
			compra.sede=sede;
			compra.userCreo=usrLogueado;
			compra.montoTotal=montoTotal.toString();
			compra.subTotal=montoTotal.toString();
			compra.setProveedor(proveedor);
			compra.setIgv("0.00");
			compra.setCantidadProductos(items.size());
			ok= true;
			compra.save();


			Iterator it2 = items.iterator();
			while(it2.hasNext())
			{
				String item_i= (String)it2.next();
				Iterator it = detallesCompra.iterator();
				while(it.hasNext())
				{
					DetalleProducto detalle_i= (DetalleProducto)it.next();
					if(item_i.equals(detalle_i.getUid()))
					{
						detalle_i.setCompra(compra);
						detalle_i.save();

						//actualizamos el stock de los productos
						producto= Producto.findById(detalle_i.getProducto().getId());
						BigDecimal cantidad= new BigDecimal(producto.getCantidad());	
						cantidad= cantidad.add(new BigDecimal(detalle_i.peso));
	 					producto.setCantidad(cantidad.toString());
						producto.save();

						break;
					}
				}
			}



			//el comprobante
			Comprobante comprobante = new Comprobante( );
			comprobante.setUserCreo(usrLogueado);
			comprobante.setSede(sede);
			comprobante.setTotal(compra.montoTotal);
			comprobante.setSubTotal(montoTotal.toString());
			comprobante.setIgv("0.00");
			Integer i = (int) (long) new Date().getTime()/1000;
			comprobante.setCorrelativo(i) ;
			comprobante.setCompra(compra);
			}
		}

	detallesCompra=null;
	List<Proveedor> proveedores= Proveedor.find("isEliminado", false ).asList();
        render("Compras/blank.html", usrLogueado, ok, proveedores);

	//render(ok);
    }




}
