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
import play.data.binding.NoBinding;

import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import org.bson.types.ObjectId;

@Entity
public class Producto extends Model {
 

    @Required @As("yyyy-MM-dd")
    public Date fecha;

    @Required
    public String nombre;

    @Unique
    @Required
    @Match("[A-Z,0-9]{5}")
    public String idVenta;

    @Required
    public String cantidad;

    @Min(0)
    @Required
    public String precioVenta;

    @Reference
    public Sede sede;

    @Reference
    public Categoria categoria;

    @Reference
    public User userCreo;

    public Producto(){
	    fecha= new Date();
	    cantidad="0";
    }

    public Producto(String name,String idB, Sede s, Categoria c ){
	categoria=c;
	fecha = new Date();
	sede=s;
	cantidad= "0";
	idVenta= idB;
	nombre=name;
	isEliminado=false;
    }

    public void setNombre(String n){
	    if (n.length() > 0)
	    {
		    nombre=n;
	    }
    }

    public void setCantidad(String c){
	    cantidad=c;
    }

    public String getCantidad(){
	    return cantidad;
    }

    public Date getFecha(){
	    return fecha;
    }

    public boolean isEliminado;

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

    public Sede getSede(){
	  return sede;
    }

    public void setSede(Sede s){
	  sede=s;
    }

    public void setPrecioVenta(String p)
    {
	    precioVenta=p;
    }

    public String getPrecioVenta()
    {
	return precioVenta;
    }

    public String toString(){
	    return nombre;
    }
}
