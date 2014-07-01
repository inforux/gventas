package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@Check("admin")
@With(Secure.class)
public class Sistema extends Controller {

    public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
    }

    public static void categorias() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void umedidas() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void descuentos() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void puntosventa() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

}

