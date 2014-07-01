package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;

import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;
import play.data.validation.Required;
import com.google.code.morphia.annotations.Embedded;

import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;
import play.modules.morphia.Model;

 
@Entity
public class User extends Model {

    @Unique
	@Email
    @Required 
	private String email;

	@Required
	public String password;

	public String sede;

	@Required
	private final Date fecha;

	@Required
	private String nombres;
		
	@Required
	private String apellidos1;

	@Required
	private String apellidos2;

	/**
	**	true	:	masculino
	**	false	:	femenino
	**/
	@Required
	private boolean sexo;

	@Required
	public boolean isAbrirCaja;

	@Required
	private boolean isAdmin;

	public User (){
		fecha= new Date();
	}

	public User(String mail, String passwd, String name,String ln1, String ln2, boolean sex, Sede s)
	{
		email= mail;
        if(s != null)
    		sede= String.valueOf(s.getId());
		password=passwd;
		nombres= name;
		apellidos1=ln1;
		apellidos2=ln2;
		sexo= sex;
		fecha= new Date();
		isAdmin = false;
		isEliminado=false;
        save();
	}

	public boolean getAdmin(){
		return isAdmin;
	}

	public  void setAdmin(boolean v){
		isAdmin=v;
	}

	public String getNombres(){
		return nombres;
	}

	public void setNombres(String n){
		nombres= n;
	}

	public String getApellidos1(){
		return apellidos1;
	}

	public void setApellidos1(String n){
		apellidos1= n;
	}

	public String getApellidos2(){
		return apellidos2;
	}

	public void setApellidos2(String n){
		apellidos2= n;
	}

    public void setSede(Sede s){
        if(s != null)
            sede=s.getId();
    }

	public Sede getSede(){
		return Sede.findById(sede);
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String e){
		email=e;
	}

	public String getNCompleto(){
		String completo="";
		if (  nombres != null)
			completo=nombres;
		if(apellidos1 !=  null)
			completo=completo+" "+apellidos1;
		if(apellidos2!= null)
			completo=completo+" "+apellidos2;

		return completo;
	}

	public void setIsAbrirCaja(boolean ac){
		isAbrirCaja=ac;
    	}

	public boolean getIsAbrirCaja(){
	    return isAbrirCaja;
    	}

	public boolean getIsAdmin(){
		return isAdmin;
	}

	public void setIsAdmin(boolean is){
        isAdmin=is;
	}

    public void setPassword(String passwd){
        password=passwd;
        _set("password", password);
    }

    	public boolean isEliminado;

        public void setIsEliminado( boolean a){
	    isEliminado=a;
    	}

	public boolean getIsEliminado(){
	    return isEliminado;
    	}


	public String toString()
	{
		return email;
	}

        public static User connect(String email, String password) {
       		 return find("byEmailAndPassword", email, password).first();
    	}
	
}
