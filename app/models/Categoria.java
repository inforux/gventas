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
public class Categoria extends Model {

    @Required @As("yyyy-MM-dd")
    private final Date fecha;

    @Required
    public String nombre;

    @Required 
    @ManyToOne
    public Unidad unidad;

    @Required 
    public boolean isEliminado;

    @ManyToOne
    public User userCreo;

    @OneToMany(mappedBy="categoria", cascade=CascadeType.ALL)
    private List<Producto> productos;

    public Categoria(){
	fecha=new Date();
	isEliminado=false;
    }

    public Categoria( String c, Unidad u ) {
	    fecha=new Date();
	    nombre=c;
	    unidad=u;
	    isEliminado=false;
    }

    public String getUnd(){
	    return unidad.toString();
    }

    public String toString() {
        return nombre;
    }
  
    public User getUserCreo(){
	  return userCreo;
    }

    public void setUserCreo(User u)
    {
	  userCreo=u;
    }
}
