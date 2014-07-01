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
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;


@Entity
public class Venta extends Model {

    @Required @As("yyyy-MM-dd")
    private Date fecha;

    @Required
    public int modoPago;//0= efeectivo; 1= tarjeta, 2= cobrar

    @Required
    public String montoTotal;

    public String pagoCon;

    public String saldo;

    public String aCta;

    public String montoACobrar;

    public String codeOperacion;

    public String montoAbonado;

    public String montoContabilizado;

    public String saldoTemporal;

    public String cambio;

    public String igv;

    public int cantidadProductos;

    @Reference
    public Comprobante comprobante;

    @Reference
    public Cliente cliente;

    public List<ObjectId> detallesProducto;

    @Reference
    public User userCreo;

    @Reference
    public Sede sede;

    @Required
    public boolean isEliminado;

    public Venta(){
	fecha=new Date();
	isEliminado=false;
	pagoCon=new String("0");
	saldo=new String("0");
	aCta=new String("0");
	montoACobrar=new String("0");
	codeOperacion="---";
	montoAbonado=new String("0");
	montoContabilizado=new String("0");
	saldoTemporal=new String("0");
	cambio=new String("0");
	montoTotal=new String("0.00");
	igv=new String("0.00");

    }

    public Venta(Sede s, User usr, int modo, String mTotal, int cant, Comprobante comp){
	pagoCon=new String("0");
	saldo=new String("0");
	aCta=new String("0");
	montoACobrar=new String("0");
	codeOperacion="---";
	montoAbonado=new String("0");
	montoContabilizado=new String("0");
	saldoTemporal=new String("0");
	cambio=new String("0");

	sede=s;
	userCreo=usr;
	fecha = new Date();
	comprobante=comp;
	montoTotal=new String(mTotal);
	isEliminado=false;
	modoPago=modo;
	cantidadProductos= cant;
	isEliminado=false;
    }

    public Date getFecha(){
	    return fecha;
    }

    public String getMontoTotal(){
	    return montoTotal;
    }

    public void setPagolCon(String p){
	    pagoCon= new String(p);
    }

    public void setSaldo(String c){
	    saldo= new String(c);
    }

    public void setACta(String c){
	    aCta= new String(c);
    }

    public void setMontoACobrar(String c){
	    montoACobrar= new String(c);
    }

    public void setMontoAbonado(String m){
	    montoAbonado=new String(m);
    }

    public void setMontoContabilizado(String m){
	    montoContabilizado=new String(m);
    }

    public void setSaldoTemporal(String s){
	    saldoTemporal=new String(s);
    }

    public void setCambio(String c){
	    cambio= new String(c);
    }

    public Comprobante getComprobante(){
	    return comprobante;
    }

    public void setCliente(Cliente c){
	    cliente = c;
    }

    public Cliente getCliente(){
	    return cliente;
    }

    public List<DetalleProducto> getDetallesProducto(){
	    List<DetalleProducto> detalles= DetalleProducto.find("venta", this).asList();
	    return detalles;
    }


    public User getUserCreo(){
	  return userCreo;
    }

    public String toString(){
	    return comprobante.toString();
    }

    public void setUserCreo(User u){
	  userCreo=u;
    }

}
