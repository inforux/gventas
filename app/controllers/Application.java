package controllers;

import java.util.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.persistence.Query;
import play.db.jpa.JPA;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.math.BigDecimal;
import java.math.RoundingMode;

import play.*;
import play.mvc.*;

import models.*;
import prints.*;

@With(Secure.class)
public class Application extends Controller {


    public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
    }

    public static void index() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void nventa() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void aventa() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void ventas() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void cobrar() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void ncompra() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void acompra() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void compras() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void iventas() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void icompras() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void iproductos() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void productos() {
	User usrLogueado = getUsrLogueado();
	List<Producto> productos= Producto.find("isEliminado", false ).asList();
        render( usrLogueado, productos);
   }

    public static void comprobantes() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void proveedores() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void clientes() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void usuarios() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void editar() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void copiaSeguridad() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void generarCopiaSeguridad(){
	//String cadena="mysqldump --opt --password=Purge --user=root PuntoVenta > backup.sql";
	//Query query = JPA.em().createQuery(cadena);
	render();
    }

    public static void ajaxAbrirCaja(boolean is){
	User usrLogueado = User.find("byEmail", Security.connected()).first();
	SessionCaja newSession=null;
	boolean stadoCaja = false;
	stadoCaja= usrLogueado.getIsAbrirCaja();
	List<SessionCaja> listaSessiones = null;
	List<SessionCaja> sessionesCajaDia = new ArrayList<SessionCaja>();
	int sessionCaja=0;
	if( stadoCaja != is){

		listaSessiones= SessionCaja.find("user", usrLogueado).asList();
		if( is){ // creamos una nueva session de caja
			if ( listaSessiones != null)	
			{
				Iterator it = listaSessiones.iterator();
				while(it.hasNext())
				{
					SessionCaja sessionCaja_i= (SessionCaja)it.next();
						Calendar cal1 = Calendar.getInstance();
						Calendar cal2 = Calendar.getInstance();	
						cal1.setTime(sessionCaja_i.fechaInicio);
						cal2.setTime(new Date());
						if ( cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) )
							sessionesCajaDia.add(sessionCaja_i);
				}
				sessionCaja = sessionesCajaDia.size() +1;
			}
			newSession= new SessionCaja( sessionCaja, new Date(), usrLogueado);
		}
		else{ // crerramos caja
			if ( listaSessiones != null)	
			{
				int size= listaSessiones.size();
				if ( size > 0)
				{
					SessionCaja sessionCajaOld = listaSessiones.get(size-1);	
					if ( sessionCajaOld != null )
						sessionCajaOld.setFechaFin(new Date());
				}
			}
		}

		usrLogueado.setIsAbrirCaja(is);
		usrLogueado.save();
	}
	render( usrLogueado);
    }

    public static void nPago(){
	
	User usrLogueado = User.find("byEmail", Security.connected()).first();
	if ( usrLogueado.getIsAbrirCaja())
	{

	}
	else{
	}
		render( usrLogueado);
    }

    public static void rDinero(){


	User usrLogueado = User.find("byEmail", Security.connected()).first();
	if ( usrLogueado.getIsAbrirCaja())
	{

	}
	else{
	}
		render( usrLogueado);
    }

    public static void epaginas(){


	User usrLogueado = User.find("byEmail", Security.connected()).first();
	if ( usrLogueado.getIsAbrirCaja())
	{

	}
	else{
	}
		render( usrLogueado);
    }

    public static void config() {

	User usrLogueado = User.find("byEmail", Security.connected()).first();
	String cIp="";
	String cMac="";
	if ( usrLogueado.getIsAbrirCaja())
	{

	}
	else{
	}


	// optenemos la ip, y mac addres
	try 
	{
			InetAddress ip;
			ip= InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			 
					 
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			cIp= ip.getHostAddress();
			cMac= sb.toString();
				
	}
	catch(Exception e) 
	{
		e.printStackTrace();
	}
	render( usrLogueado, cIp, cMac   );
    }


}
