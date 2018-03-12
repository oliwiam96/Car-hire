package com.sample;

import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.ZoneId;



import com.sample.KategoriaSamochodu;
import com.sample.Klient;
import com.sample.Samochod;

public class Rezerwacja {
	
	//in:
	public Klient klient; 
	public Date dataPobrania;
	public KategoriaSamochodu preferowanaKategoria; //moze byc null
	public Date dataZwrotu;
	public boolean czyFotelik = false;
	public boolean czyBagaznik = false;
	
	//out:
	public double proponowanaCena = 0;
	public boolean czyWykonalna = false;
	public Samochod samochod = null;
	
	
	//pom
	public boolean doliczonoAkcesoria;
	public Rezerwacja(Klient klient, Date dataPobrania,
			Date dataZwrotu, boolean czyFotelik, boolean czyBagaznik,
			KategoriaSamochodu preferowanaKategoria) {
		super();
		this.klient = klient;
		this.dataPobrania = dataPobrania;
		this.dataZwrotu = dataZwrotu;
		this.czyFotelik = czyFotelik;
		this.czyBagaznik = czyBagaznik;
		this.preferowanaKategoria = preferowanaKategoria;
	}
	
	public boolean czyKolidujePrzedzial(Date poczatek, Date koniec){
		// 	TO DO EQUALS???
		if(poczatek.before(dataZwrotu) && koniec.after(dataPobrania)){
			return true;
		}
		return false;
	}
	public long liczbaDni(){
		LocalDate date1 = dataPobrania.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = dataZwrotu.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return ChronoUnit.DAYS.between(date1, date2) + 1;
	}
}
