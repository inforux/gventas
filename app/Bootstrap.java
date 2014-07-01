import models.*;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
 
@OnApplicationStart
public class Bootstrap extends Job<Object> {
	
    public void doJob() {
      	/*
	 *
	*Creamos usuarios 
	*/ 

    	if(User.count() == 0) {
	System.out.println("Empezando el bootStrap");

	Sede sedeSanJose= new Sede("San Jose","Mercado San jose- int. 67", "0.19","1.0" ).save(); 

	User adm1= new User("wsilva@sanjose.com", "kikito","Walter","Silva Paiva"," ", true, sedeSanJose).save();
	User adm2= new User("admin@sanjose.com", "admin","Admin"," "," ", true, sedeSanJose).save();

	User usuario2 = new User("mromero@sanjose.com", "mromero","Maritza", "Romero","Agurto", true, sedeSanJose).save();
	User usuario3 = new User("ecoello@sanjose.com", "ecoello","Brenda", "Coello","Farfan", false, sedeSanJose).save();

	adm1.setAdmin(true);
	adm2.setAdmin(true);
	adm1.save();
	adm2.save();

	Unidad u1 = new Unidad("kilogramo", "kg");
	Unidad u2 = new Unidad("Unidad", "und");
	u1.setUserCreo(usuario2);
	u1.setUserCreo(adm1);

	u1.save();
	u2.save();

	Categoria ca= new Categoria("Pescados(Kg)", u1).save();
	Categoria ca2= new Categoria("Mariscos(Kg)", u1).save();
	Categoria ca3= new Categoria("Mariscos(Und)", u2).save();

	ca.setUserCreo(usuario2);
	ca2.setUserCreo(adm1);
	ca3.setUserCreo(adm1);
	ca.save();
	ca2.save();
	ca3.save();


		Producto p1= new Producto("CACHEMA CHICA","01001", sedeSanJose, ca).save();		
		p1.setUserCreo(adm2);
		p1.setPrecioVenta("7.50");
		p1.save();

		Producto p2= new Producto("CACHEMA GRANDE","01003", sedeSanJose, ca).save();		
		p2.setUserCreo(adm2);
		p2.setPrecioVenta("12.00");
		p2.save();

		Producto p3= new Producto("CACHEMA SECHURANA","01004", sedeSanJose, ca).save();		
		p3.setUserCreo(adm2);
		p3.setPrecioVenta("12.00");
		p3.save();

		Producto p4= new Producto("CACHEMA P","01005", sedeSanJose, ca).save();		
		p4.setUserCreo(adm2);
		p4.setPrecioVenta("4.00");
		p4.save();

		Producto p5= new Producto("DONCELLA","01101", sedeSanJose, ca).save();		
		p5.setUserCreo(adm2);
		p5.setPrecioVenta("7.00");
		p5.save();

		Producto p6= new Producto("HUEVERAS","01102", sedeSanJose, ca).save();		
		p6.setUserCreo(adm2);
		p6.setPrecioVenta("5.00");
		p6.save();

		Producto p7= new Producto("CABRILLA","02000", sedeSanJose, ca).save();		
		p7.setUserCreo(adm2);
		p7.setPrecioVenta("22.00");
		p7.save();

		Producto p8= new Producto("CABRILLA CHICA","02001", sedeSanJose, ca).save();		
		p8.setUserCreo(adm2);
		p8.setPrecioVenta("17.00");
		p8.save();

		Producto p9= new Producto("CABRILLA GRANDE","02002", sedeSanJose, ca).save();		
		p9.setUserCreo(adm2);
		p9.setPrecioVenta("22.00");
		p9.save();

		Producto p10= new Producto("CABRILLON","02005", sedeSanJose, ca).save();		
		p10.setUserCreo(adm2);
		p10.setPrecioVenta("33.00");
		p10.save();

		Producto p11= new Producto("CABRILLON GRANDE","02006", sedeSanJose, ca).save();		
		p11.setUserCreo(adm2);
		p11.setPrecioVenta("33.00");
		p11.save();

		Producto p12= new Producto("PEJE","03000", sedeSanJose, ca).save();		
		p12.setUserCreo(adm2);
		p12.setPrecioVenta("13.00");
		p12.save();

		Producto p13= new Producto("PEJE CHICO","03001", sedeSanJose, ca).save();		
		p13.setUserCreo(adm2);
		p13.setPrecioVenta("13.00");
		p13.save();

		Producto p14= new Producto("PEJE GRANDE","03003", sedeSanJose, ca).save();		
		p14.setUserCreo(adm2);
		p14.setPrecioVenta("13");
		p14.save();

		Producto p15= new Producto("SUCO","03004", sedeSanJose, ca).save();		
		p15.setUserCreo(adm2);
		p15.setPrecioVenta("9");
		p15.save();

		Producto p16= new Producto("PAMPANO ESPEJO","03005", sedeSanJose, ca).save();		
		p16.setUserCreo(adm2);
		p16.setPrecioVenta("5.50");
		p16.save();

		Producto p17= new Producto("CABALLA","04000", sedeSanJose, ca).save();		
		p17.setUserCreo(adm2);
		p17.setPrecioVenta("10");
		p17.save();

		Producto p18= new Producto("CABALLA","04000", sedeSanJose, ca).save();		
		p18.setUserCreo(adm2);
		p18.setPrecioVenta("10");
		p18.save();

		Producto p19= new Producto("CABALLA FRESCA","04001", sedeSanJose, ca).save();		
		p19.setUserCreo(adm2);
		p19.setPrecioVenta("10");
		p19.save();

		Producto p20= new Producto("CABALLA FRESCA CHICA","04002", sedeSanJose, ca).save();		
		p20.setUserCreo(adm2);
		p20.setPrecioVenta("3.50");
		p20.save();

		Producto p21= new Producto("JUREL","05000", sedeSanJose, ca).save();		
		p21.setUserCreo(adm2);
		p21.setPrecioVenta("12");
		p21.save();

		Producto p22= new Producto("JUREL FRESCO GRANDE","05001", sedeSanJose, ca).save();		
		p22.setUserCreo(adm2);
		p22.setPrecioVenta("11");
		p22.save();

		Producto p23= new Producto("JUREL FRESCO","05002", sedeSanJose, ca).save();		
		p23.setUserCreo(adm2);
		p23.setPrecioVenta("11");
		p23.save();

		Producto p24= new Producto("JUREL CONGELADO","05003", sedeSanJose, ca).save();		
		p24.setUserCreo(adm2);
		p24.setPrecioVenta("11");
		p24.save();

		Producto p25= new Producto("BONITO","05004", sedeSanJose, ca).save();		
		p25.setUserCreo(adm2);
		p25.setPrecioVenta("6.50");
		p25.save();

		Producto p26= new Producto("FILETE DE MERLUZA","06001", sedeSanJose, ca).save();		
		p26.setUserCreo(adm2);
		p26.setPrecioVenta("6.50");
		p26.save();

		Producto p27= new Producto("MERLUZA ENTERA","06002", sedeSanJose, ca).save();		
		p27.setUserCreo(adm2);
		p27.setPrecioVenta("3");
		p27.save();

		Producto p28= new Producto("FILETE DE CAMOTILLO","06003", sedeSanJose, ca).save();		
		p28.setUserCreo(adm2);
		p28.setPrecioVenta("20");
		p28.save();

		Producto p29= new Producto("FILETE DE CONGRIO","06004", sedeSanJose, ca).save();		
		p29.setUserCreo(adm2);
		p29.setPrecioVenta("20");
		p29.save();

		Producto p30= new Producto("C.DE CONGRIO ROJO","06005", sedeSanJose, ca).save();		
		p30.setUserCreo(adm2);
		p30.setPrecioVenta("7");
		p30.save();

		Producto p31= new Producto("F. CONGRIO ROJO","06006", sedeSanJose, ca).save();		
		p31.setUserCreo(adm2);
		p31.setPrecioVenta("30");
		p31.save();

		Producto p32= new Producto("CABEZAS DE CONGRIO","06007", sedeSanJose, ca).save();		
		p32.setUserCreo(adm2);
		p32.setPrecioVenta("3.00");
		p32.save();

		Producto p33= new Producto("CONGRIO ROJO ENTERO","06008", sedeSanJose, ca).save();		
		p33.setUserCreo(adm2);
		p33.setPrecioVenta("17.00");
		p33.save();

		Producto p34= new Producto("CONGRIO ENTERO N","06009", sedeSanJose, ca).save();		
		p34.setUserCreo(adm2);
		p34.setPrecioVenta("7.00");
		p34.save();

		Producto p35= new Producto("CONGRIO ENTERO GATO","06010", sedeSanJose, ca).save();		
		p35.setUserCreo(adm2);
		p35.setPrecioVenta("11");
		p35.save();

		Producto p36= new Producto("FILETE DE CONGRIO NEGRO","06011", sedeSanJose, ca).save();		
		p36.setUserCreo(adm2);
		p36.setPrecioVenta("17");
		p36.save();

		Producto p37= new Producto("TOLLO DE LECHE","07001", sedeSanJose, ca).save();		
		p37.setUserCreo(adm2);
		p37.setPrecioVenta("17");
		p37.save();

		Producto p38= new Producto("TOLLO DIAMANTE","07002", sedeSanJose, ca).save();		
		p38.setUserCreo(adm2);
		p38.setPrecioVenta("12");
		p38.save();

		Producto p39= new Producto("MERO GRANDE","08001", sedeSanJose, ca).save();		
		p39.setUserCreo(adm2);
		p39.setPrecioVenta("22");
		p39.save();

		Producto p40= new Producto("MERO CHICO","08002", sedeSanJose, ca).save();		
		p40.setUserCreo(adm2);
		p40.setPrecioVenta("18");
		p40.save();

		Producto p41= new Producto("MIXTURA","08003", sedeSanJose, ca).save();		
		p41.setUserCreo(adm2);
		p41.setPrecioVenta("12");
		p41.save();

		Producto p42= new Producto("LANGOSTINO","09001", sedeSanJose, ca).save();		
		p42.setUserCreo(adm2);
		p42.setPrecioVenta("20");
		p42.save();

		Producto p43= new Producto("CALAMAR","09002", sedeSanJose, ca).save();		
		p43.setUserCreo(adm2);
		p43.setPrecioVenta("8");
		p43.save();

		Producto p44= new Producto("CONCHA DE ABANICO","09003", sedeSanJose, ca).save();		
		p44.setUserCreo(adm2);
		p44.setPrecioVenta("8");
		p44.save();

		Producto p45= new Producto("CARACOL","09004", sedeSanJose, ca).save();		
		p45.setUserCreo(adm2);
		p45.setPrecioVenta("12");
		p45.save();

		Producto p46= new Producto("CONCHAS BLANCAS","09005", sedeSanJose, ca).save();		
		p46.setUserCreo(adm2);
		p46.setPrecioVenta("16");
		p46.save();

		Producto p47= new Producto("CONCHAS NEGRA CHICA","09006", sedeSanJose, ca).save();		
		p47.setUserCreo(adm2);
		p47.setPrecioVenta("22");
		p47.save();

		Producto p48= new Producto("CONCHAS NEGRAS GRANDES","09007", sedeSanJose, ca).save();		
		p48.setUserCreo(adm2);
		p48.setPrecioVenta("22");
		p48.save();

		Producto p49= new Producto("YUYO","09008", sedeSanJose, ca).save();		
		p49.setUserCreo(adm2);
		p49.setPrecioVenta("8");
		p49.save();

		Producto p50= new Producto("CANGREJOS","09009", sedeSanJose, ca).save();		
		p50.setUserCreo(adm2);
		p50.setPrecioVenta("5");
		p50.save();

		//proveedores
		Proveedor prov1 = new Proveedor("90563434872","Terminal Pescquero - Piura").save();
		Proveedor prov2 = new Proveedor("90563434569","Mercado modelo - pescado").save();
		Proveedor prov3 = new Proveedor("90561644872","Sr. Esmeralda").save();

		//atributos del proveedor
		prov1.setUserCreo(adm2);
		prov1.save();
		prov2.setUserCreo(adm2);
		prov2.save();
		prov3.setUserCreo(adm2);
		prov3.save();

		//clientes
		Cliente cli1=new Cliente("44339995","Silva Paiva Miguel Angel","998543678").save();
		Cliente cli2=new Cliente("02873167","Paiva Calderon Tereza","998543678").save();

		//atributos del cliente
		cli1.setUserCreo(adm2);
		cli1.save();
		cli2.setUserCreo(adm2);
		cli2.save();

		//comprobantes

		//atributos de los detalles

    	}
    }
}
