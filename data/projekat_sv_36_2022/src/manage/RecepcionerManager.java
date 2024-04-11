package manage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.Recepcioner;
import entity.Sprema;
import entity.Zaposleni;

public class RecepcionerManager {
	private String recepcionerFile;
	private ArrayList<Recepcioner> recepcioneri;
	
	public RecepcionerManager(String recepcionerFile)
	{
		this.recepcionerFile = recepcionerFile;
		this.recepcioneri = new ArrayList<Recepcioner>();
	}
	
	public ArrayList<Recepcioner> getRecepcioner()
	{
		return this.recepcioneri;
	}
	
	public ArrayList<Zaposleni> getRecepcioneriZaposleni()
	{
		ArrayList<Zaposleni> zaposleni = new ArrayList<Zaposleni>();
		
		for(Recepcioner recepcioner : this.recepcioneri)
		{
			zaposleni.add(recepcioner);
		}
		
		return zaposleni;
	}
	
	public Recepcioner pronadjiPoId(int id)
	{
		for(Recepcioner recepcioner : this.recepcioneri)
		{
			if(recepcioner.getId() == id)
				return recepcioner;
		}
		
		return null;
	}
	
	public Recepcioner pronadjiPoKorisnickomImenu(String korIme)
	{
		for(Recepcioner recepcioner : this.recepcioneri)
		{
			if(recepcioner.getKorisnickoIme().equals(korIme))
				return recepcioner;
		}
		
		return null;
	}
	
	public void dodajKt2()
	{
		this.add(Sprema.VISOKA, 10, 500, 50000, "Pera", "Peric", "Muski", 
				"123456789", "Ulica 1 Beograd", "rec1", "recjedan");
	}
	
	public void dodajCLI()
	{
		this.add(Sprema.IZNAD_PROSEKA, 10, 500, 50000, "Recepcioner 1", "Jedan", "Muski", 
				"123456789", "Ulica 1 Beograd", "rec1", "recjedan");
		this.add(Sprema.ISPOD_PROSEKA, 130, 500, 25, "Recepcioner 4", "Cetiri", "Muski", 
				"481958139", "Ulica 14 Beograd", "rec4", "reccetiri");
		this.add(Sprema.NISKA, 7, 300, 60000, "Recepcioner 3", "Tri", "Zenski", 
				"123456912", "Ulica 3 Zrenjanin", "rec3", "rectri");
	}
	
	public void prikaziCLI()
	{
		for(Recepcioner recepcioner  : this.recepcioneri)
		{
			System.out.println(recepcioner);
		}
	}
	
	public void izmeniCLI()
	{
		this.edit(this.recepcioneri.get(1).getId(), Sprema.PROSECNA, 15, 1000, 75000, "Recepcioner 2", 
				"Dva", "Muski", "0655730724", 
				"Ulica 2 Novi Sad", "rec2", "recdva");
	}
	
	public void obrisiCLI()
	{
		this.remove(this.recepcioneri.get(2).getId());
	}
	
	public void add(Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		this.recepcioneri.add(new Recepcioner(sprema,staz,bonus,plata,ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka));
		this.saveData();
	}
	
	public void edit(int id, Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		Recepcioner izmena = this.pronadjiPoId(id);
		izmena.setSprema(sprema);
		izmena.setStaz(staz);
		izmena.setBonus(bonus);
		izmena.setPlata(plata);
		izmena.setIme(ime);
		izmena.setPrezime(prezime);
		izmena.setPol(pol);
		izmena.setTelefon(telefon);
		izmena.setAdresa(adresa);
		izmena.setKorisnickoIme(korisnickoIme);
		izmena.setLozinka(lozinka); 
		this.saveData();
	}
	
	public void remove(int id)
	{
		this.recepcioneri.remove(this.pronadjiPoId(id));
		this.saveData();
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.recepcionerFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] atributi = linija.split(";");
				Sprema sprema = null;
				switch(atributi[8])
				{
					case "VISOKA":
						sprema = Sprema.VISOKA;
						break;
					case "IZNAD_PROSEKA":
						sprema = Sprema.IZNAD_PROSEKA;
						break;
					case "PROSECNA":
						sprema = Sprema.PROSECNA;
						break;
					case "ISPOD_PROSEKA":
						sprema = Sprema.ISPOD_PROSEKA;
						break;
					case "NISKA":
						sprema = Sprema.NISKA;
						break;
				}
				Recepcioner noviRecepcioner = new Recepcioner(sprema, Integer.parseInt(atributi[9]), 
						Integer.parseInt(atributi[10]), Integer.parseInt(atributi[11]),atributi[1],atributi[2],
						atributi[3],atributi[4],atributi[5], atributi[6],atributi[7]);
				noviRecepcioner.setId(Integer.parseInt(atributi[0]));
				this.recepcioneri.add(noviRecepcioner);
			}
			br.close();
		}
		catch(IOException e)
		{
			return false;
		}
		return true;
	}
	
	public boolean saveData()
	{
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(this.recepcionerFile,false));
			for(Recepcioner recepcioner : this.recepcioneri)
			{
				pw.println(recepcioner.toFile());
			}
			pw.close();
		}
		catch(IOException e)
		{
			return false;
		}
		
		return true;
	}

}
