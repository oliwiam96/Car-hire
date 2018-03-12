package com.sample;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.sample.Rezerwacja;
import com.sample.Klient;

public class Zwrot {
	//in
	public Date dataZwrotu;
	public boolean karaDowod = false;
	public boolean karaPolisa = false;
	public int ileZgubionychKolpakow = 0;
	public Klient klient;
	public double liczbaKm;
	public Rezerwacja rezerwacja;
	
	
	//out
	public double ostatecznaOplata = 0;
	public double ostatecznaOplataPlusRezerwacja = 0;
	


	public Zwrot(Date dataZwrotu, boolean karaDowod, boolean karaPolisa,
			int ileZgubionychKolpakow, Klient klient,
			double liczbaKm, Rezerwacja rezerwacja) {
		super();
		this.dataZwrotu = dataZwrotu;
		this.karaDowod = karaDowod;
		this.karaPolisa = karaPolisa;
		this.ileZgubionychKolpakow = ileZgubionychKolpakow;
		this.klient = klient;
		this.liczbaKm = liczbaKm;
		this.rezerwacja = rezerwacja;
	}
	
	public long liczbaDni(){
		LocalDate date1 = rezerwacja.dataPobrania.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = dataZwrotu.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return ChronoUnit.DAYS.between(date1, date2) + 1;
	}
	
	
	

}
