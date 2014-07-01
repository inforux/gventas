package controllers;
import models.*;
import play.db.Model;
import java.util.List;
import java.util.*;
 
import play.*;
import play.mvc.*;
 
@Check("admin")
@With(Secure.class) 
public class Inventarios extends Controller {

   public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
   }

    public static void nuevo(){
	    User usrLogueado = getUsrLogueado();
    // enviamos todos los productos que estan habilitados
        List<Producto> productos =null;
    	productos= Producto.find().filter("isEliminado", false).order("-fecha").asList();
    	render(usrLogueado, productos );
    }

    public static void list() {

	User usrLogueado = getUsrLogueado();	
	List<Inventario> inventarios= Inventario.find("isEliminado", false ).asList();
	render(usrLogueado, inventarios);
    }

    public static void saveInventario(String descrip){
        
        // generamos el inventario
        
	    User usrLogueado = getUsrLogueado();	
        Inventario newInventario= null;
    	List<Inventario> inventarios= Inventario.find("isEliminado", false ).asList();

        List<Producto> productos =null;
    	productos= Producto.find().filter("isEliminado", false).order("-fecha").asList();

        newInventario= new Inventario(descrip, usrLogueado);
        // add los detalles

			Iterator it = productos.iterator();
            DetalleInventario detalle_i= null;
			while(it.hasNext())
			{
					Producto producto_i= (Producto)it.next();
                    detalle_i= new DetalleInventario(producto_i.getCantidad(), producto_i.getPrecioVenta(), producto_i, producto_i.idVenta ); 
                    newInventario.addDetalle(detalle_i);
                    newInventario.save();
            }
            redirect("/inventarios/list");
	
    }

    public static void modalInventario(String id) {
        Inventario inventario= null;
        if ( !id.equals(""))
            inventario = Inventario.findById(id);        
        render(inventario);
    }

}
