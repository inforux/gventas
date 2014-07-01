package models;
import java.math.BigDecimal;
 
import java.util.*;
import javax.persistence.*;

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
public class Compra extends Model {


    @Required @As("yyyy-MM-dd")
    private Date fecha;

    @Reference
    private Comprobante comprobante;

    @Required
    private int modoPago;//0= efeectivo; 1= tarjeta, 2= cobrar

    @Required
    public String montoTotal;

    @Required
    public String subTotal;

    public String igv;

    public String saldo;

    public String aCta;

    public int cantidadProductos;

    @Reference
    public User userCreo;

    @Reference
    public Sede sede;

    @Reference
    private Proveedor proveedor;

    private boolean isEliminado;

    public Compra(){
	fecha= new Date();		
	isEliminado=false;
    }

    public Compra(Sede sed, User  usr, int modo, String mTotal , int cant, Comprobante c){
	sede=sed;
	fecha = new Date();
	userCreo= usr;
	modoPago=modo;
	montoTotal= new String(mTotal);
	subTotal= new String(mTotal);
	cantidadProductos= cant;
	comprobante=c;
	isEliminado=false;
    }

    public int getModoPago(){
	return modoPago;
    }

    public void setModoPago(int m){ 
	modoPago=m;
    }

    public void setIgv(String i){
	    igv=i;
    }

    public String getMontoTotal(){
	    return montoTotal;
    }

    public String getSaldo(){
	    return saldo;
    }

    public void setSaldo(String c){
	    saldo= c;
    }

    public String getACta(){
	    return aCta;
    }

    public void setACta(String c){
	    aCta= c;
    }

    public int getCantidadProductos(){
	    return cantidadProductos;
    }

    public void setCantidadProductos(int c){
	    cantidadProductos= c;
    }

    public Proveedor getProveedor(){
	    return proveedor;
    }

    public void setProveedor(Proveedor p){
	   proveedor=p;
    }

    public User getUserCreo(){
	  return userCreo;
    }

    public void setUserCreo(User u){
	  userCreo=u;
    }
	
    public String toString(){
	    return comprobante.toString();
    }

    public boolean getIsEliminado(){
	    return isEliminado;
    }

    public void setIsEliminado(boolean a){
	isEliminado=a;
    }

}
