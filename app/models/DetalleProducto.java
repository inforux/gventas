package models;
 
import java.util.*;
import javax.persistence.*;

import java.util.UUID;
import play.data.binding.*;
import play.data.validation.*;
import java.math.BigDecimal;

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
public class DetalleProducto extends Model {
 
    @Required @As("yyyy-MM-dd")
    private final Date fecha;

    @Required
    public String peso;

    @Required
    public String precio;

    public String descuento;

    public boolean isDescuentoPorUnidad;

    @Reference
    public Producto producto;

    @Reference
    public Venta venta;

    @Reference
    public Compra compra;

    public String uid;

    public DetalleProducto(Producto vproducto, String vpeso,String vprecio  ){
	fecha= new Date();
	producto=vproducto;
	peso=vpeso;
	precio=vprecio;
    }

    public Producto getProducto(){
	    return producto;
    }

    public void setProducto(Producto p){
	    producto=p;
    }

    public void setCompra(Compra c){
	    compra=c;
    }

    public Compra getCompra(){
	   return compra; 
    }

    public void setVenta(Venta v){
	    venta=v;
    }

    public Venta getVenta(){
	    return venta;
    }

    public void setUid(String u){
	    uid = u;
    }

    public String getUid(){
	    return uid;
    }

    public BigDecimal getTotal(){
	    BigDecimal cant = new BigDecimal(peso);
	    BigDecimal prec= new BigDecimal(precio);
	    return prec.multiply(cant);
    }
}

