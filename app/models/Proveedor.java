package models;
 
import java.util.*;
import javax.persistence.*;
import java.math.BigDecimal;

import play.data.binding.*;
import play.data.validation.*;
import play.data.validation.MaxSize;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.binding.As;

import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import org.bson.types.ObjectId;

@Entity
public class Proveedor extends Model {

    @Unique
    @Required
    @Match("[0-9]{11}")
    public String ruc;

    @Required
    public String razonSocial;

    @Unique
    @Match("[0-9]{8}")
    public String dni;

    @Phone
    public String fono;
    
    @Required
    private final Date fecha;

    @Reference
    public User userCreo;

    @Required
    public boolean isEliminado;

    public Proveedor(){
	    fecha=new Date();
    }

    public Proveedor(String vruc, String vrazonSocial){
	fecha = new Date();
	ruc=vruc;
	razonSocial=vrazonSocial;
	isEliminado=false;
    }

    public String toString(){
	    return razonSocial;
    }

    public void eliminar(){
	    isEliminado=true;
    }

    public User getUserCreo(){
	  return userCreo;
    }

    public void setUserCreo(User u){
	  userCreo=u;
    }

}

