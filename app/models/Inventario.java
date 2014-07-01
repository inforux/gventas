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
public class Inventario extends Model {
 
    @Required @As("yyyy-MM-dd")
    public final Date fecha;

    @Required
    public String descripcion;

    @Required 
    public boolean isEliminado;

    @Reference
    public User userCreo;
    
    public List<DetalleInventario> detallesInventario;

    public Inventario(String d, User usr){
        descripcion=d;
        userCreo=usr;
    	fecha=new Date();
    	isEliminado=false;
        save();
    }

    public void addDetalle(DetalleInventario detalle){
        if( detallesInventario == null)
            detallesInventario= new ArrayList<DetalleInventario>();
        detallesInventario.add(detalle);
    }
}
