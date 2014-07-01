package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;
import play.data.binding.As;
import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;


@Entity
public class Unidad extends Model {
 
    @Required @As("yyyy-MM-dd")
    private final Date fecha;

    @Required
    public String nombre;

    @Unique
    @Required
    public String abreviado;

    @Required 
    public boolean isEliminado;

    @Reference
    public User userCreo;

    public Unidad(){
	fecha=new Date();
	isEliminado=false;
    }

    public Unidad( String c, String a) {
	    fecha=new Date();
	    nombre=c;
	    abreviado=a;
	    isEliminado=false;
    }

    public String toString() {
        return abreviado;
    }
  
    public User getUserCreo(){
	  return userCreo;
    }

    public void setUserCreo(User u)
    {
	  userCreo=u;
    }
}
