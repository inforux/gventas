package controllers;
 
import models.*;
import play.db.Model;
import play.data.validation.*;
import play.data.binding.Binder;
import play.data.binding.NoBinding;
import play.data.binding.As;
import java.lang.reflect.Constructor;
import play.exceptions.TemplateNotFoundException;
import java.util.List;



import play.*;
import play.mvc.*;
 
@Check("admin")
@With(Secure.class) 
@CRUD.For(Cliente.class)
public class Clientes extends CRUD {    

   public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
   }

    public static void show(Long id) throws Exception {

	User usrLogueado = getUsrLogueado();	

	validation.match(id, "[a-z,0-9]");	
	if (validation.hasErrors()) 
		id= null;
       	notFoundIfNull(id);

	Cliente object= Cliente.findById(id);
       	notFoundIfNull(object);

        try {
            render(object, usrLogueado);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", object, usrLogueado);
        }
    }

    public static void list(int page, String search, String searchFields, String orderBy, String order) {

	User usrLogueado = getUsrLogueado();	
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }


	boolean isReporte=false;
	String url1 = "/reportes/clientes/list";
	String url2 = "/reportes/clientes/list/";
	if (url1.equals(request.url) || url2.equals(request.url))
	{
		isReporte=true;
	}


        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, (String) request.args.get("where"));
        Long count = type.count(search, searchFields, (String) request.args.get("where"));
        Long totalCount = type.count(null, null, (String) request.args.get("where"));
        try {
            render(type, objects, count, totalCount, page, orderBy, order, isReporte, usrLogueado);
        }

       	catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order, isReporte, usrLogueado);
        }

    }


    public static void blank(){
	User usrLogueado = getUsrLogueado();
	render(usrLogueado );
    }


}
