package controllers;

import play.modules.excel.RenderExcel;
import java.util.Date;
import play.Logger;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

@Check("admin")
@With({ExcelControllerHelper.class, Secure.class})
public class Reportes extends Controller {

    public static User getUsrLogueado(){
	return 	User.find("byEmail", Security.connected()).first();
    }
    
    public static void productos() {

    	List<Producto> productos= Producto.findAll();
	render(productos);
    }

    public static void generateProducto(Long id) {
        Logger.info("generateNameCard");
	User usuarioLogueado = getUsrLogueado(); 
    	Producto producto= Producto.findById(id);
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", producto.getId() + ".xls");
    	render(producto, usuarioLogueado);
    }

	@Check("admin")
    public static void generateProductos() {
        Logger.info("generateNameCard");
	User usuarioLogueado = getUsrLogueado(); 
    	List<Producto> productos= Producto.findAll();
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", productos + ".xls");
    	render(productos, usuarioLogueado);
    }

	@Check("admin")
    public static void generateIProductos() {
        Logger.info("generateNameCard for Inventario de productos");
    	User usrLogueado = getUsrLogueado(); 

        List<Producto> productos =null;
    	productos= Producto.find().filter("isEliminado", false).order("-fecha").asList();

    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", productos + ".xls");
        Date fecha= new Date();

        String size="0";
        if ( productos != null)
            size=String.valueOf(productos.size());
    	render(productos, usrLogueado,size, fecha );
    }

     public static void generateIProductos2(String id) {
        Logger.info("generateNameCard for Inventario de productos 2");
    	User usrLogueado = getUsrLogueado(); 


        System.out.println("idInvent: "+ id);
        Inventario inventario= Inventario.findById(id);
        List<DetalleInventario> productos = new ArrayList<DetalleInventario>();

			Iterator it = inventario.detallesInventario.iterator();
			while(it.hasNext())
			{
				DetalleInventario item_i= (DetalleInventario)it.next();
                productos.add(item_i);
            }
            System.out.println("size de productos: "+ productos.size());

    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", productos + ".xls");

    	render(productos, usrLogueado);
    }

    public static void generateCliente(Long id) {
        Logger.info("generateNameCard");
    	Cliente cliente= Cliente.findById(id);
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", cliente.getId() + ".xls");
    	render(cliente);
    }

    public static void generateClientes() {
        Logger.info("generateNameCard");
    	List<Cliente> clientes= Cliente.findAll();
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", clientes+ ".xls");
    	render(clientes);
    }

    public static void generateProveedor(Long id) {
        Logger.info("generateNameCard");
    	Proveedor proveedor= Proveedor.findById(id);
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", proveedor.getId() + ".xls");
    	render(proveedor);
    }

    public static void generateProveedores() {
        Logger.info("generateNameCard");
    	List<Proveedor> proveedores= Proveedor.findAll();
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", proveedores+ ".xls");
    	render(proveedores);
    }

    public static void generateVenta(Long id) {
        Logger.info("generateNameCard");
    	Venta venta= Venta.findById(id);
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", venta.getId() + ".xls");
    	render(venta);
    }

    public static void generateVentas() {
        Logger.info("generateNameCard");
    	List<Venta> ventas= Venta.findAll();
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", ventas+ ".xls");
    	render(ventas);
    }

    public static void generateCompra(Long id) {
        Logger.info("generateNameCard");
    	Compra compra= Compra.findById(id);
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", compra.getId() + ".xls");
    	render(compra);
    }

    public static void generateCompras() {
        Logger.info("generateNameCard");
    	List<Compra> compras= Compra.findAll();
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", compras+ ".xls");
    	render(compras);
    }

    public static void generateUsuario(Long id) {
        Logger.info("generateNameCard");
    	User usuario= User.findById(id);
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", usuario.getId() + ".xls");
    	render(usuario);
    }

    public static void generateUsuarios() {
        Logger.info("generateNameCard");
    	List<User> usuarios= User.findAll();
    	request.format = "xls";
        renderArgs.put("__EXCEL_FILE_NAME__", usuarios+ ".xls");
    	render(usuarios);
    }


    public static void usuarios() {
            render();
    }

    public static void ventas() {
            render();
    }

    public static void compras() {
            render();
    }

    public static void clientes() {
            render();
    }

    public static void proveedores() {
            render();
    }


    public static void rcaja() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }

    public static void rpuntoventa() {
	User usrLogueado = getUsrLogueado(); 
        render( usrLogueado);
    }
}
