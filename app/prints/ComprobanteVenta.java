package prints;

import models.*;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;
 
public class ComprobanteVenta{

    public Venta venta= null;
    public String peso;
    public String precio;
    public String producto;
    public String total;

    public ComprobanteVenta( Comprobante c){
		imprimir( c);
    }
    
     public void imprimir( Comprobante c){
        try{
	    venta = c.getVenta();
            Sede sede= c.getUserCreo().getSede();
            Date date=new Date();
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");

	    //empezamos a ddibujar
            Ticket ticket = new Ticket();
            ticket.AddCabecera("        Pescado y Mariscos SAN JOSE");
            ticket.AddCabecera(ticket.DarEspacio());
	    ticket.AddCabecera("   tlf: " + sede.telefono+ "      R.U.C:"+ sede.ruc);
            ticket.AddCabecera(ticket.DarEspacio());
	    ticket.AddCabecera("     Comprobante No:"+ c.correlativo);
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddCabecera("        "+fecha.format(date) + " " + hora.format(date));
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddCabecera(ticket.DibujarLinea(40));

            ticket.AddCabecera(ticket.DarEspacio());
	    ticket.AddCabecera("Peso(kg)  P.U.(s/)  Producto    Total(s/)");
            ticket.AddCabecera(ticket.DarEspacio());
	    ticket.AddCabecera(ticket.DibujarLinea(40));
            ticket.AddCabecera(ticket.DarEspacio());

	    //descripcion
	    venta= c.getVenta();
	    if ( venta != null)
	    {
		List<DetalleProducto> detalleVentas= DetalleProducto.find().filter("venta", venta).asList();
		//List<DetalleProducto> detalleVentas= venta.detallesProducto;

		if ( detalleVentas != null)
		{
			Iterator it = detalleVentas.iterator();
			while(it.hasNext())
			{
				DetalleProducto detalle_i= (DetalleProducto)it.next();
				peso= detalle_i.peso;
				if ( peso.length()  < 5)
				{
					int pp=5-peso.length();
					String pes="";
		                        for(int f=0;f<pp;f++)
						pes+=" ";
					peso+=pes;
				}

				precio= detalle_i.precio;
				if ( precio.length() < 7)
				{
					int cp=7-precio.length();
					String pre="";
		                        for(int f=0;f<cp;f++)
						pre+=" ";
					precio+=pre;
				}


				producto= detalle_i.producto.toString();
				if(producto.length()>18)
					producto=producto.substring(0,17)+".";
				else
				{
				          int cantp=18-producto.length();
					  String pro="";
				          for(int y1=0;y1<cantp;y1++)
						  pro+=" ";
					  producto+=pro;
				}


				total= detalle_i.getTotal().toString();
				if ( total.length() < 7)
				{
					int ct=7-total.length();
					String tot="";
		                        for(int f=0;f<ct;f++)
						tot+=" ";
					total+=tot;
				}

            			//agrego los items al detalle
			        ticket.AddItem(peso,precio,producto,total);
			        ticket.AddItem("","","","\n");
			}
		  } 


	    }

	    //cantidad de productos

            ticket.AddTotal(ticket.DibujarLinea(40),"");
	    if ( venta.detallesProducto != null)
	    {
        	ticket.AddTotal("Cant. Productos:         ",Integer.toString(venta.detallesProducto.size()));

	    }
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal(ticket.DibujarLinea(40),"");
            ticket.AddTotal("",ticket.DarEspacio());
			
	    //Modo de pago
	    ticket.AddTotal("Total                   ",venta.montoTotal.toString());
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal("Pago con                ",venta.pagoCon.toString());
            ticket.AddTotal("",ticket.DarEspacio());
            ticket.AddTotal("Vuelto                  ",venta.cambio.toString());

            ticket.AddPieLinea(ticket.DarEspacio());
            ticket.AddPieLinea(ticket.DibujarLinea(40));
            ticket.AddPieLinea(ticket.DarEspacio());

            ticket.AddPieLinea("Ticket para uso de control interno, si desea boleta de venta sirvase acercase a caja. ");
            ticket.AddPieLinea("Gracias por su Preferencia");
           
            ticket.ImprimirDocumento("LPT1", true);
        }catch(Exception e){System.out.print("\nerror "+e.toString());}
    }

}

