package entity;

import java.util.ArrayList;

public class Recepcioner extends Zaposleni implements ToFile{
	public Recepcioner(Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		super(sprema,staz,bonus,plata,ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
	}
	
	public void zakaziTretman(ArrayList<KozmetickiTretman> tretmani, KozmetickiTretman tretman)
	{
		tretmani.add(tretman);
	}
	
	public void izmeniTretman(ArrayList<KozmetickiTretman> tretmani, int index,
			KozmetickiTretman noviTretman)
	{
		tretmani.set(index,noviTretman); // mada ovo vrv nece ni trebati
	}
	
	public void izbaciTretman(ArrayList<KozmetickiTretman> tretmani, int index)
	{
		KozmetickiTretman stari = tretmani.get(index);
		stari.setStanjeKozmetickogTretmana(StanjeKozmetickogTretmana.OTKAZAO_SALON);
	}
	
	@Override
	public String toFile()
	{
		return this.id + ";" + this.ime + ";" + this.prezime + ";" + this.pol + ";" + this.telefon + ";"
				+ this.adresa + ";" + this.korisnickoIme + ";" + this.lozinka + ";" + this.sprema + ";"
				+ this.staz + ";" + this.bonus + ";" + this.plata;
	}
}
