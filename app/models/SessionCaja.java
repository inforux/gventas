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
public class SessionCaja extends Model {

    @Required 
    public int numeroSession;
 
    @Required
    public Date fechaInicio;

    @Required
    public Date fechaFin;

    public void setFechaFin(Date d){
	fechaFin = d;
	save();
    }

    public int cantImpresion;

    @Reference
    public User user;

    public User getUser(){
	    return user;
    }

    public SessionCaja(int num, Date fInicio, User u ) {
	numeroSession= num;
	fechaInicio = fInicio;
	user = u;
	this.isEliminado= false; 
	save();
    }

    boolean isEliminado;
    public void eliminar(){
	    isEliminado=true;
	    save();
    }

    public String toString(){
	    return String.valueOf(numeroSession);
    }
 
}

