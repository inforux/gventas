package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;
import play.data.binding.As;

import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import org.bson.types.ObjectId;
 
@Entity
public class Precio extends Model {

    @Required @As("yyyy-MM-dd")
    private final Date fecha;

    @ManyToOne
    @Required
    public Producto producto;

    @Required
    public String precioCosto;

    @Required
    public String precioVenta;

    @Required
    public String cantidad;

    public Precio(Producto p, String costo, String venta, String stock) {
	    fecha=new Date();
	    producto=p;
	    precioCosto=costo;
	    precioVenta=venta;
	    cantidad=stock;
    }

    public String toString() {
        return precioCosto;
    }
}
