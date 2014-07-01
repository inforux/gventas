package prints;

import models.*;

import java.util.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;
 
public class Caja{

	    Iterator it;

	    List<Venta> ventasTotal = null;
	    List<Venta> ventasFiltroTotal =null; 

            SessionCaja sessionCaja = null;
	    BigDecimal montoVenta= new BigDecimal(0.00);
	    BigDecimal montoSoloEfectivo = new BigDecimal("0.00");

	    BigDecimal efectivo= new BigDecimal(0.00);;
	    BigDecimal tarjeta= new BigDecimal(0.00);;
	    BigDecimal cta= new BigDecimal(0.00);;

	    BigDecimal efectivoContado= new BigDecimal("0.00");
	    BigDecimal efectivoCtaCobrar= new BigDecimal("0.00");
	    BigDecimal efectivoTarjeta= new BigDecimal("0.00");

    public Caja( SessionCaja s, User cajero ){
		imprimir( s, cajero);
    }
    
     public void imprimir( SessionCaja sessionCaja, User cajero){
        try{
            Sede sede= cajero.getSede();
            Date date=new Date();
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");

	    //empezamos a ddibujar
            Ticket ticket = new Ticket();
            ticket.AddCabecera("        Pescado y Mariscos SAN JOSE");
            ticket.AddCabecera(ticket.DarEspacio());
	    ticket.AddCabecera("   tlf: " + sede.webNegocio+ "      r.u.c:"+ sede.ruc);
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddSubCabecera("        "+fecha.format(date) + " " + hora.format(date));
            ticket.AddCabecera(ticket.DarEspacio());
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddCabecera("            CAJERO: "+ cajero.getNCompleto());
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddCabecera("    Fecha Apertura: "+ sessionCaja.fechaInicio);
            ticket.AddCabecera(ticket.DarEspacio());
            ticket.AddCabecera("   Fecha de Cierre: "+ sessionCaja.fechaInicio);
            ticket.AddSubCabecera(ticket.DarEspacio());
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());

		// empieza el algortima para los calculos
		if(sessionCaja != null)
	        {
		//ventasTotal= Venta.find("byIsEliminadoAndUser",false, cajero ).fetch();
		ventasTotal= Venta.find().filter("isEliminado", false).filter("user", cajero ).asList();

		it = ventasTotal.iterator();
		while(it.hasNext())
		{
			Venta venta_i= (Venta)it.next();
			if ( venta_i.getFecha().compareTo(sessionCaja.fechaInicio) >= 0 && venta_i.getFecha().compareTo(sessionCaja.fechaFin) <= 0  )
			{
				if ( ventasFiltroTotal == null)
					ventasFiltroTotal = new ArrayList<Venta>();
				ventasFiltroTotal.add(venta_i);
			}
		}
				

		if( ventasFiltroTotal != null)
		{
			it = ventasFiltroTotal.iterator();
			while(it.hasNext())
			{
				Venta venta_i= (Venta)it.next();
				montoVenta=montoVenta.add(new BigDecimal(venta_i.montoTotal));
				if ( venta_i.modoPago==0)
				{
					efectivo=efectivo.add(new BigDecimal(venta_i.montoTotal));
					efectivoContado=efectivoContado.add(new BigDecimal(venta_i.montoTotal));
				}

				if( venta_i.modoPago==1){
					tarjeta=tarjeta.add(new BigDecimal(venta_i.montoTotal));
					if ( venta_i.saldoTemporal != null)
						efectivoTarjeta= efectivoTarjeta.add( new BigDecimal(venta_i.saldoTemporal));
				}	

				if( venta_i.modoPago ==2)
				{
					cta=cta.add( new BigDecimal(venta_i.montoTotal));
					if ( venta_i.aCta != null )
						efectivoCtaCobrar=efectivoCtaCobrar.add( new BigDecimal(venta_i.aCta));
				}

			}

			montoSoloEfectivo = montoSoloEfectivo.add(efectivoContado).add(efectivoTarjeta).add(efectivoCtaCobrar);
		}
		}



		// imprimimos lo calculado
            ticket.AddCabecera("MONTO VENTAS --------------------"+ montoVenta.toString()+"----");
            ticket.AddSubCabecera(ticket.DarEspacio());
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddCabecera(" EFECTIVO:--------------- -------"+ montoSoloEfectivo.toString());
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddCabecera("    VENTA EFECTIVO: --------------"+ efectivoContado.toString()+"-----");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddCabecera("    Efect. x Vent. Tarjeta.-----"+ efectivoTarjeta.toString()+"-----");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddCabecera("    Efect. x Vent. Cta cobrar.--"+ efectivoCtaCobrar.toString()+"-----");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddCabecera(" VENTA CTA COBRAR:----- -------"+  cta.toString()+"-----");
            ticket.AddSubCabecera(ticket.DarEspacio());
            ticket.AddCabecera(" VENTA TARJETA:----- ----------"+  tarjeta.toString()+"-----");
            ticket.AddSubCabecera(ticket.DarEspacio());
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
	    ticket.AddSubCabecera(ticket.DibujarLinea(40));
            ticket.AddSubCabecera(ticket.DarEspacio());
           
            ticket.ImprimirDocumento("LPT1", true);
        }catch(Exception e){System.out.print("\nerror "+e.toString());}
    }

}


