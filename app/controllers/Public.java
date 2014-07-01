package controllers;

import java.util.*;
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

public class Public extends Controller {


    public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
    }

    public static void index() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void nosotros() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void cabrillas() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void cachemas() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void contacto() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void ofertas() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

}
