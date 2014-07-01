package prints;
import java.io.FileWriter; 
import java.util.ArrayList; 
import javax.swing.JOptionPane; 
public class Ticket{ 
	public Ticket(){
		} 
		static ArrayList<String> CabezaLineas=new ArrayList<String>(); 
		static ArrayList<String> subCabezaLineas=new ArrayList<String>(); 
		static ArrayList<String> items=new ArrayList<String>(); 
		static ArrayList<String> totales=new ArrayList<String>(); 
		static ArrayList<String> LineasPie=new ArrayList<String>(); 
		public static void AddCabecera(String line){CabezaLineas.add(line);} 
		public static void AddSubCabecera(String line){subCabezaLineas.add(line);} 
			
		public static void AddItem(String cantidad,String item,String price,String total){ 
			OrderItem newItem = new OrderItem(' '); 
			items.add(newItem.GeneraItem(cantidad, item, price, total) ); 
		} 
		public static void AddTotal(String name,String price){ 
			OrderTotal newTotal = new OrderTotal(' '); 
			totales.add(newTotal.GeneraTotal(name, price)); 
		} 
		public static void AddPieLinea(String line){LineasPie.add(line);} 
			
		public static String DibujarLinea(int valor){ 
			String raya=""; 
			for(int x=0;x<valor;x++){raya+="-";} 
			return raya; 
		} 
		public static String DarEspacio(){return "\n";} 
			
		public static void SetFormato(FileWriter pw,int formato){ 
			try{ 
				char[] ESC_CUT_PAPER = new char[] { 0x1B, '!',(char)formato}; 
				pw.write(ESC_CUT_PAPER); 
				}
			catch(Exception e){ System.out.print(e); } 
		} 
		public static void ImprimirDocumento(String impresora,boolean abrir)
		{ 
			try
			{
				 //FileWriter imp = new FileWriter("LPT1"); 
				FileWriter imp = new FileWriter(impresora); 
				char[] Caracter = new char[] { 0x1B, 'R',18}; 
				imp.write(Caracter); 
				for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++){ 
					imp.write(CabezaLineas.get(cabecera)); 
				} 
				for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){ 
					imp.write(subCabezaLineas.get(subcabecera)); 
				} 
						
				for(int ITEM=0;ITEM<items.size();ITEM++){ 
					imp.write(items.get(ITEM)); 
				} 
					
				for(int total=0;total<totales.size();total++){ 
					imp.write(totales.get(total)); 
				} 
					
				for(int pie=0;pie<LineasPie.size();pie++){ 
					imp.write(LineasPie.get(pie)); 
				} 
					
				for(int u=0;u<=5;u++){
					imp.write("\n");
				}
				
				//corta el papel 
				char[] CORTAR_PAPEL=new char[]{0x1B,'m'};
				imp.write(CORTAR_PAPEL);
				if(abrir)
				{ 
					char ABRIR_GAVETA[]={(char)27,(char)112,(char)0,(char)10,(char)100}; 
					imp.write(ABRIR_GAVETA); 
					
				} 
				imp.close(); 
				//limpio las listas que contiene los datos 
				CabezaLineas.removeAll(CabezaLineas); 
	 			subCabezaLineas.removeAll(subCabezaLineas); 
				items.removeAll(items); 
				totales.removeAll(totales); 
				LineasPie.removeAll(LineasPie);  
			}
			catch(Exception e)
			{ 
				JOptionPane.showMessageDialog(null,"Error al Imprimir ajo:\n"+e.getMessage()); 
				CabezaLineas.removeAll(CabezaLineas); 
				subCabezaLineas.removeAll(subCabezaLineas); 
				items.removeAll(items); 
				totales.removeAll(totales); 
				LineasPie.removeAll(LineasPie); 
			}
	} 
	public static void ImprimirDocumento(String impresora,boolean abrir, boolean formatoCabecera,int formato){ 
		try{ 
			//FileWriter imp = new FileWriter("LPT1"); 
			FileWriter imp = new FileWriter(impresora); 
			for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++){ 
				SetFormato(imp,27); imp.write(CabezaLineas.get(cabecera)); 
			} 
			for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){ 
				imp.write(subCabezaLineas.get(subcabecera)); 
			} 
			for(int ITEM=0;ITEM<items.size();ITEM++){ 
				imp.write(items.get(ITEM)); 
			} 
			for(int total=0;total<totales.size();total++){ 
				imp.write(totales.get(total)); 
			} 
			for(int pie=0;pie<LineasPie.size();pie++){ 
				imp.write(LineasPie.get(pie)); 
			} 
			for(int u=0;u<=10;u++){
				imp.write("\n");
			} 
			//corta el papel 
			char[] CORTAR_PAPEL=new char[]{0x1B,'m'}; 
			imp.write(CORTAR_PAPEL); 
			if(abrir)
			{ 
				char ABRIR_GAVETA[]={(char)27,(char)112,(char)0,(char)10,(char)100}; 
				imp.write(ABRIR_GAVETA); 
			} 
			imp.close(); 
			//limpio las listas que contiene los datos 
			CabezaLineas.removeAll(CabezaLineas); 
			subCabezaLineas.removeAll(subCabezaLineas); 
			items.removeAll(items); 
			totales.removeAll(totales); 
			LineasPie.removeAll(LineasPie); 
		}
		catch(Exception e){ 
			JOptionPane.showMessageDialog(null,"Error al Imprimir mela:\n"+e.getMessage()); 
			CabezaLineas.removeAll(CabezaLineas); 
			subCabezaLineas.removeAll(subCabezaLineas); 
			items.removeAll(items); 
			totales.removeAll(totales); 
			LineasPie.removeAll(LineasPie); 
		} 
	} 
	
}
