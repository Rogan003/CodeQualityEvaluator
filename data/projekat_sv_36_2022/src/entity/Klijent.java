package entity;

import java.util.ArrayList;

public class Klijent extends Korisnik implements ToFile{
	private ArrayList<KozmetickiTretman> tretmani;
	private double potrosio;
	private boolean karticaLojalnosti; 
	public static final double popust = 0.1;
	public static int iznosZaKarticu = -1;
	
	public Klijent(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		super(ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
		this.potrosio = 0;
		this.karticaLojalnosti = false;
		this.tretmani = new ArrayList<KozmetickiTretman>();
	}
	
	public Klijent(double potrosio, boolean karticaLojalnosti, ArrayList<KozmetickiTretman> tretmani,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		super(ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
		this.potrosio = potrosio;
		this.karticaLojalnosti = karticaLojalnosti;
		this.tretmani = tretmani;
	}
	
	public double getPotrosio()
	{
		return this.potrosio;
	}
	
	public boolean hasKarticaLojalnosti()
	{
		return this.karticaLojalnosti;
	}
	
	public ArrayList<KozmetickiTretman> getTretmani()
	{
		return this.tretmani;
	}
	
	public void setPotrosio(double potrosio)
	{
		this.potrosio = potrosio;
	}
	
	public void setKarticaLojalnosti(boolean karticaLojalnosti)
	{
		this.karticaLojalnosti = karticaLojalnosti;
	}
	
	public void setTretmani(ArrayList<KozmetickiTretman> tretmani)
	{
		this.tretmani = tretmani;
	}
	
	public void zakaziTretman(KozmetickiTretman tretman)
	{
		this.potrosio += tretman.getCena();
		if(this.karticaLojalnosti)
		{
			this.potrosio -= tretman.getCena() * Klijent.popust;
		}
		this.tretmani.add(tretman);
	}
	
	public void otkaziTretman(KozmetickiTretman otkazan, boolean recepcioner)
	{
		if(recepcioner)
		{
			otkazan.setStanjeKozmetickogTretmana(StanjeKozmetickogTretmana.OTKAZAO_SALON);
		}
		else
		{
			otkazan.setStanjeKozmetickogTretmana(StanjeKozmetickogTretmana.OTKAZAO_KLIJENT);
		}
		this.potrosio -= otkazan.getCena();
	}
	
	public void nijeSePojavio(KozmetickiTretman tretman)
	{
		this.potrosio -= tretman.getCena();
	}
	/*
	public void preuzmiKarticuLojalnosti()
	{
		if(Klijent.iznosZaKarticu == -1)
		{
			System.out.println("Trenutno nemamo dostupnu opciju za karticu lojalnosti!");
		}
		else if(this.potrosio > Klijent.iznosZaKarticu)
		{
			this.karticaLojalnosti = true;
		}
		else
		{
			System.out.println("Niste dovoljno potrosili!");
		}
	}*/
	
	public int preuzmiKarticuLojalnosti()
	{
		if(this.karticaLojalnosti)
		{
			return -2;
		}
		
		if(Klijent.iznosZaKarticu == -1)
		{
			return -1;
		}
		else if(this.potrosio >= Klijent.iznosZaKarticu)
		{
			this.karticaLojalnosti = true;
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	@Override
	public String toString()
	{
		String klstr = "nema";
		if(this.karticaLojalnosti)
		{
			klstr = "ima";
		}
		
		StringBuilder tretmaniIspis = new StringBuilder(50);
		
		for(KozmetickiTretman tretman : this.tretmani)
		{
			tretmaniIspis.append(tretman + "\n");
		}
		
		return super.toString() + "\nPotrosio: " + this.potrosio + "\nKartica lojalnosti: " + klstr
				+ "\nTretmani: " + tretmaniIspis + "\n";
				
	} 
	
	@Override
	public String toFile()
	{
		StringBuilder tretmaniIspis = new StringBuilder(50);
		
		for(KozmetickiTretman tretman : this.tretmani)
		{
			tretmaniIspis.append(tretman.getId() + ",");
		}
		
		String povratna = null;
		if(tretmaniIspis.length() != 0)
		{
			povratna = tretmaniIspis.substring(0,tretmaniIspis.length() - 1);
		}
		else
		{
			povratna = "";
		}
		
		return this.id + ";" + this.ime + ";" + this.prezime + ";" + this.pol + ";" + this.telefon + ";"
				+ this.adresa + ";" + this.korisnickoIme + ";" + this.lozinka + ";" + this.potrosio + ";"
				+ this.karticaLojalnosti + ";" + povratna;
	}
}
