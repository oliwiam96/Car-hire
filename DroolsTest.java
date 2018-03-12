package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.Kategoria;
import com.sample.KategoriaSamochodu;
import com.sample.Klient;
import com.sample.Rezerwacja;
import com.sample.Zwrot;
import com.sample.Samochod;
import java.util.Date;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.time.temporal.ChronoUnit;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

            // go !
            
            KategoriaSamochodu kategoriaA = new KategoriaSamochodu(Kategoria.A, 59.0, 69.0, 10.0, 10.0);
            KategoriaSamochodu kategoriaB = new KategoriaSamochodu(Kategoria.B, 69.0, 79.0, 10.0, 10.0);
            KategoriaSamochodu kategoriaD = new KategoriaSamochodu(Kategoria.D, 79.0, 89.0, 10.0, 10.0);
            kSession.insert(kategoriaA);
            kSession.insert(kategoriaB);
            kSession.insert(kategoriaD);
            kSession.insert(new Samochod(kategoriaA, "Samochód 1"));
            kSession.insert(new Samochod(kategoriaA, "Samochód 2"));
            kSession.insert(new Samochod(kategoriaB, "Samochód 3"));
            kSession.insert(new Samochod(kategoriaD, "Samochód 4"));
            kSession.insert(new Samochod(kategoriaD, "Samochód 5"));
            
            Klient k1 = new Klient(true);
            Klient k2 = new Klient(false);
            Klient k3 = new Klient(false);
            Klient k4 = new Klient(false);
            Klient k5 = new Klient(true);
            
            
            // UWAGA! MIESIAC MUSI BYC W PRZEDZIALE 0-11, CZYLI ROBIMY -1
            
            
            Rezerwacja r1 = new Rezerwacja(k1, new GregorianCalendar(2016, 10, 11).getTime(), 
            		new GregorianCalendar(2016, 10, 12).getTime(),
            		true, true, null);
            Zwrot z1 = new Zwrot(new GregorianCalendar(2016, 10, 12).getTime(), false, false, 0, k1, 66, r1);

            
            Rezerwacja r2 = new Rezerwacja(k2, new GregorianCalendar(2016, 10, 10).getTime(), 
            		new GregorianCalendar(2016, 10, 13).getTime(),
            		false, true, kategoriaB);
            Zwrot z2 = new Zwrot(new GregorianCalendar(2016, 10, 13).getTime(), false, false, 0, k2, 100, r2);

            Rezerwacja r2prim = new Rezerwacja(k2, new GregorianCalendar(2016, 10, 11).getTime(),
            		new GregorianCalendar(2016, 10, 14).getTime(),
            		false, false, kategoriaA);
            
            Rezerwacja r3 = new Rezerwacja(k3, new GregorianCalendar(2016, 10, 11).getTime(),
            		new GregorianCalendar(2016, 10, 16).getTime(),
            		false, false, kategoriaB);
            Zwrot z3 = new Zwrot(new GregorianCalendar(2016, 10, 18).getTime(),
            		true, false, 2, k3, 1000, r3);
            
            Rezerwacja r4 = new Rezerwacja(k4, new GregorianCalendar(2016, 10, 14).getTime(),
            		new GregorianCalendar(2016, 10, 16).getTime(), false, false, kategoriaB);
            Zwrot z4 = new Zwrot(new GregorianCalendar(2016, 10, 16).getTime(), false, false, 1, k4, 100, r4);
            
            Rezerwacja r5 = new Rezerwacja(k5, new GregorianCalendar(2016, 10, 11).getTime(),
            		new GregorianCalendar(2016, 10, 14).getTime(), false, true, kategoriaA);
            Zwrot z5 = new Zwrot(new GregorianCalendar(2016, 10, 14).getTime(), false, false, 0, k5, 100, r5);
            
            
            
            
            kSession.insert(k1);
            kSession.insert(k2);
            kSession.insert(k3);
            kSession.insert(k4);
            kSession.insert(k5);
            
            kSession.insert(r1);
            kSession.insert(r2);
            kSession.insert(r2prim);
            kSession.insert(r3);
            kSession.insert(r4);
            kSession.insert(r5);
            
            kSession.insert(z1);
            kSession.insert(z2);
            kSession.insert(z3);
            kSession.insert(z4);
            kSession.insert(z5);
            

            kSession.fireAllRules();
            
            System.out.println("\n\n ***WYNIKI*** \n\n");
            
            if(r1.czyWykonalna){
            	System.out.println("Rezerwacja r1 wykonalna");
            	System.out.println("Klient dostanie samochod o nazwie " + r1.samochod.nazwa);
            	System.out.println("Proponowana cena: " + r1.proponowanaCena);
            	System.out.println("SUMA oddanie: "+ z1.ostatecznaOplata);
            	System.out.println("SUMA: " + z1.ostatecznaOplataPlusRezerwacja);
            }
            else{
            	System.out.println("Rezerwacja r1 niewykonalna.");
            }
            System.out.println("");
            
            if(r2.czyWykonalna){
            	System.out.println("Rezerwacja r2 wykonalna");
            	System.out.println("Klient dostanie samochod o nazwie " + r2.samochod.nazwa);
            	System.out.println("Proponowana cena: " + r2.proponowanaCena);
            	System.out.println("SUMA oddanie: "+ z2.ostatecznaOplata);
            	System.out.println("SUMA: " + z2.ostatecznaOplataPlusRezerwacja);
            }
            else{
            	System.out.println("Rezerwacja r2 niewykonalna.");
            }
            System.out.println("");
            
            if(r2prim.czyWykonalna){
            	System.out.println("Rezerwacja r2prim wykonalna");
            	System.out.println("Klient dostanie samochod o nazwie " + r2prim.samochod.nazwa);
            	System.out.println("Proponowana cena: " + r2prim.proponowanaCena);
            	
            }
            else{
            	System.out.println("Rezerwacja r2prim niewykonalna.");
            }
            System.out.println("");
            
            if(r3.czyWykonalna){
            	System.out.println("Rezerwacja r3 wykonalna");
            	System.out.println("Klient dostanie samochod o nazwie " + r3.samochod.nazwa);
            	System.out.println("Proponowana cena: " + r3.proponowanaCena);
            	System.out.println("SUMA oddanie: "+ z3.ostatecznaOplata);
            	System.out.println("SUMA: " + z3.ostatecznaOplataPlusRezerwacja);
            }
            else{
            	System.out.println("Rezerwacja r3 niewykonalna.");
            }
            System.out.println("");
            
            if(r4.czyWykonalna){
            	System.out.println("Rezerwacja r4 wykonalna");
            	System.out.println("Klient dostanie samochod o nazwie " + r4.samochod.nazwa);
            	System.out.println("Proponowana cena: " + r4.proponowanaCena);
            	System.out.println("SUMA oddanie: "+ z4.ostatecznaOplata);
            	System.out.println("SUMA: " + z4.ostatecznaOplataPlusRezerwacja);
            }
            else{
            	System.out.println("Rezerwacja r4 niewykonalna.");
            }
            System.out.println("");
            
            if(r5.czyWykonalna){
            	System.out.println("Rezerwacja r5 wykonalna");
            	System.out.println("Klient dostanie samochod o nazwie " + r5.samochod.nazwa);
            	System.out.println("Proponowana cena: " + r5.proponowanaCena);
            	System.out.println("SUMA oddanie: "+ z5.ostatecznaOplata);
            	System.out.println("SUMA: " + z5.ostatecznaOplataPlusRezerwacja);
            }
            else{
            	System.out.println("Rezerwacja r5 niewykonalna.");
            }
            System.out.println("");
            
 
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
