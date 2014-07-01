package controllers;
 
import models.*;
import play.db.Model;
import play.data.validation.*;
import play.data.binding.As;
import play.data.binding.Binder;
import play.data.binding.NoBinding;
import play.data.binding.As;
import java.lang.reflect.Constructor;
import play.exceptions.TemplateNotFoundException;
import java.util.List;
import org.bson.types.ObjectId;



import play.*;
import play.mvc.*;



@With(Secure.class) 
public class Productos extends CRUD {    

    public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
    }

    public static void list(int page, String search, String searchFields, String orderBy, String order) {

	User usrLogueado = getUsrLogueado();	
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }


	boolean isReporte=false;
	String url1 = "/reportes/productos/list";
	String url2 = "/reportes/productos/list/";
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


    public static void show(Long id) throws Exception {

	User usrLogueado = getUsrLogueado();	


	Producto object= Producto.findById(id);
       	notFoundIfNull(object);

        try {
            render(object, usrLogueado);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", object, usrLogueado);
        }
    }

    public static void blank() throws Exception {
	User usrLogueado = getUsrLogueado();	
	ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Model object = (Model) constructor.newInstance();
        try {
            render(type, object, usrLogueado);
        } catch (TemplateNotFoundException e) {
            render("CRUD/blank.html", type, object, usrLogueado);
        }
    }

    public static void create() throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        //Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
        //constructor.setAccessible(true);
        //Producto object = (Producto) constructor.newInstance();
	Producto object = new Producto();
        Binder.bindBean(params.getRootParamNode(), "object", object);
	object.setUserCreo(getUsrLogueado());

        Sede sede = null;
        sede = Sede.findById( getUsrLogueado().sede);
        if (sede != null)
        	object.setSede(sede);

        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", play.i18n.Messages.get("crud.hasErrors"));
            try {
                render(request.controller.replace(".", "/") + "/blank.html", type, object);
            } catch (TemplateNotFoundException e) {
                render("CRUD/blank.html", type, object);
            }
        }
	object.save();

        flash.success(play.i18n.Messages.get("crud.created", type.modelName));
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(request.controller + ".blank");
        }
        redirect(request.controller + ".show", object._key());
    }

}
