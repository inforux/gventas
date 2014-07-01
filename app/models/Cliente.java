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
public class Cliente extends Model {

    @Required
    @Unique
    @Match("[0-9]{8}")
    public String dni;

    @Required
    public String nombres;

    @Phone
    public String fono;
    
    @Required
    private final Date fecha;

    @Required
    public boolean isEliminado;

    @Reference
    public User userCreo;

    public Cliente(){
	    fecha=new Date();
	    isEliminado=false;
    }

    public Cliente(String d, String name, String f){
	fecha = new Date();
	dni= d;
	fono = f;
	nombres= name;
	isEliminado=false;
    }

    public Cliente(String d, String name){
	fecha = new Date();
	dni= d;
	nombres= name;
	isEliminado=false;
    }

    public User getUserCreo(){
	  return userCreo;
    }

    public void setUserCreo(User u){
	  userCreo=u;
    }

    public String toString(){
	    return nombres;
    }

}
