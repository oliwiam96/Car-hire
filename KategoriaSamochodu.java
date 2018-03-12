package com.sample;

import com.sample.Kategoria;

public class KategoriaSamochodu {
	
	public Kategoria symbol;
	public double cenaZaDzienPowyzej3Dni;
	public double cenaZaDzienDoMaks3Dni;
	public double cenaZaFotelik;
	public double cenaZaBagaznik;
	
	
	public KategoriaSamochodu(Kategoria symbol, double cenaZaDzienPowyzej3Dni,
			double cenaZaDzienDoMaks3Dni, double cenaZaFotelik, double cenaZaBagaznik){
		this.symbol = symbol;
		this.cenaZaDzienPowyzej3Dni = cenaZaDzienPowyzej3Dni;
		this.cenaZaDzienDoMaks3Dni = cenaZaDzienDoMaks3Dni;
		this.cenaZaFotelik = cenaZaFotelik;
		this.cenaZaBagaznik = cenaZaBagaznik;
	}
	

}
