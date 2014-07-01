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
public class Pago extends Model {

    @Reference
    public final Comprobante comprobante;

    @Reference
    public final Cliente cliente;

    @Required
    public final int modoPago;//0= efeectivo; 1= tarjeta

    public final String codeOperacion;

    public final BigDecimal montoAbonado;

    public final BigDecimal montoContabilizado;
 
    @Required
    public final Date fecha;

    public final BigDecimal monto;

    public final BigDecimal nuevoSaldo;

    public final BigDecimal saldo;

    public final BigDecimal pagoCon;

    public final BigDecimal cambio;

    @Reference
    public User userCreo;

    public Pago(int modo, Cliente c, String emonto, String esaldo, String enSaldo, String pagoC, String camb, String codeOp, String emAbonado, String emContabilizado, Comprobante comp) {
	modoPago=modo;
	fecha = new Date();
	cliente=c;
	comprobante=comp;
	monto= new BigDecimal(emonto);
	saldo=new BigDecimal(esaldo);
	nuevoSaldo=new  BigDecimal(enSaldo);
	codeOperacion=codeOp;
	pagoCon=new  BigDecimal(pagoC);
	cambio=new  BigDecimal(camb);
	montoAbonado=new BigDecimal(emAbonado);
	montoContabilizado=new BigDecimal(emContabilizado);
    }

    public User getUserCreo(){
	  return userCreo;
    }

    public void setUserCreo(User u){
	  userCreo=u;
    }


}
