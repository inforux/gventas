package models;
import java.math.BigDecimal;
 
import java.util.*;
import javax.persistence.*;
 
import play.data.validation.*;

import play.modules.morphia.Model;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import org.bson.types.ObjectId;
 
@Entity
public class Comprobante extends Model {

 
    @Required
    private Date fecha;

    public String serie;

    public int correlativo;

    @Required
    private String subTotal;

    @Required
    private String total;

    @Required
    public String igv;

    @Reference
    private Compra compra;

    @Reference
    private Venta venta;

    @Reference
    public Sede sede;

    @Reference
    public User userCreo;

    @Reference
    public boolean isEliminado;

    public Comprobante(){
	    fecha=new Date();
	    isEliminado=false;
    }

    public Comprobante(String vserie, int vcorrelativo, String vigv ) {

	fecha = new Date();
	serie=vserie;
	correlativo=vcorrelativo;
	igv=new String(vigv);
	isEliminado= false; 
    }

    public Date getFecha(){
	    return fecha;
    }

    public void setCorrelativo( int c){
	correlativo=c;
    }

    public int getCorrelativo( ){
	return correlativo;	
    }

    public void setSerie( String c){
	serie=c;
    }

    public String getSerie( ){
	return serie;	
    }

    public void setIgv(String i){
	    igv= i;
    }

    public void setCompra(Compra c){
	   compra=c;
    }

    public Compra getCompra(){
	    return compra;
    }

    public void setVenta(Venta c){
	   venta=c;
    }

    public Venta getVenta(){
	    return venta;
    }

    public User getUserCreo(){
	  return userCreo;
    }

    public void setUserCreo(User u){
	  userCreo=u;
    }

    public void setSede(Sede s){
	    sede=s;
    }

    public String getTotal(){
	    return total;
    }

    public void setTotal(String b){
	    total=b;
    }

    public String getSubTotal(){
	    return subTotal;
    }

    public void setSubTotal(String b){
	    subTotal=b;
    }

    public Sede getSede(){
	    return sede;
    }

    public String toString(){
	    return serie+" - "+correlativo;
    }

    public void setIsEliminado( boolean v){
	    isEliminado=v;
    }

}
