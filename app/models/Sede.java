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
public class Sede extends Model {

    @Required @As("yyyy-MM-dd")
    private final Date fecha;

    @Required
    public String nombreSede;

    @Required
    public String direccionNegocio;

    @Required
    public String igv;

    @Range(min = 0, max = 5)
    @Required
    public String comisionTarjeta;

    @Match("[0-9]{11}")
    public String ruc;

    public int correlativoBoleta;

    public int serieBoleta;

    public int correlativoFactura;

    public int serieFactura;

    @Phone
    public String telefono;

    @URL
    public String webNegocio;

    @MinSize(100)
    public String slogan;

    @Required
    public boolean isEliminado;


    @Reference
    public User userCreo;

    public Sede(){
	fecha=new Date();
	isEliminado=false;
    }

    public Sede(String name, String addr, String i, String c) {
	    fecha=new Date();
	    nombreSede=name;
	    direccionNegocio=addr;
	    igv=i;
	    comisionTarjeta=c;
	    isEliminado=false;
    }

    public String toString() {
        return nombreSede;
    }

   public void setIsEliminado( boolean a){
	    isEliminado=a;
   }

   public boolean getIsEliminado(){
	    return isEliminado;
  }
  
  public User getUserCreo(){
	  return userCreo;
  }

  public void setUserCreo(User u){
	  userCreo=u;
  }


}
