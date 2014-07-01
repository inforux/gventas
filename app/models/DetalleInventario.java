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
public class DetalleInventario extends Model {

    public String pesoFisico;

    @Required
    public String pesoInventario;

    public String diferencia;

    @Required
    public String costoUnidad;

    public String costoTotalDiferencia;

    @Required 
    public Producto producto;

    public String nproducto;

    @Required
    public String descripcion;

    @Required
    public String codBalanza;

    public DetalleInventario(String peso, String costo, Producto idProducto, String balanza){
        pesoInventario=peso;
        costoUnidad=costo;
        producto=idProducto;
        nproducto=idProducto.nombre;
        codBalanza=balanza;
        save();
    }


}
