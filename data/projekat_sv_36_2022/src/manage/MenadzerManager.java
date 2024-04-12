package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.Menadzer;
import entity.Sprema;
import entity.Zaposleni;

public class MenadzerManager {
	private String menadzerFile;
	private ArrayList<Menadzer> menadzeri;
	
	public MenadzerManager(String menadzerFile)
	{
		this.menadzerFile = menadzerFile;
		this.menadzeri = new ArrayList<Menadzer>();
	}
	
	public ArrayList<Menadzer> getMenadzeri()
	{
		return this.menadzeri;
	}
	
	public ArrayList<Zaposleni> getMenadzeriZaposleni()
	{
		ArrayList<Zaposleni> zaposleni = new ArrayList<Zaposleni>();
		for(Menadzer menadzer : this.menadzeri)
		{
			zaposleni.add(menadzer);
		}
		
		return zaposleni;
	}
	
	public void dodajKt2()
	{
		this.add(Sprema.PROSECNA, 10, 500, 50000, "Nikola", "Nikolic", "Muski", 
				"1234561289", "Ulica 10 Cacak", "men1", "menjedan");
	}
	
	public void dodajCLI()
	{
		this.add(Sprema.PROSECNA, 10, 500, 50000, "Menadzer 1", "Jedan", "Zenski", 
				"1234561289", "Ulica 10 Cacak", "men1", "menjedan");
		this.add(Sprema.ISPOD_PROSEKA, 130, 500, 25, "Menadzer 4", "Cetiri", "Muski", 
				"4819523139", "Ulica 142 Beograd", "men4", "mencetiri");
		this.add(Sprema.VISOKA, 7, 300, 60000, "Menadzer 3", "Tri", "Zenski", 
				"123326912", "Ulica 32 Zrenjanin", "men3", "mentri");
	}
	
	public void prikaziCLI()
	{
		for(Menadzer menadzer  : this.menadzeri)
		{
			System.out.println(menadzer);
		}
	}
	
	public void izmeniCLI()
	{
		this.edit(this.menadzeri.get(1).getId(), Sprema.NISKA, 15, 1000, 75000, "Menadzer 2", 
				"Dva", "Muski", "0653731724", 
				"Ulica 2 Novi Sad", "men2", "mendva");
	}
	
	public void obrisiCLI()
	{
		this.remove(this.menadzeri.get(2).getId());
	}
	
	public Menadzer pronadjiPoId(int id)
	{
		for(Menadzer menadzer : this.menadzeri)
		{
			if(menadzer.getId() == id)
				return menadzer;
		}
		
		return null;
	}
	
	public Menadzer pronadjiPoKorisnickomImenu(String korIme)
	{
		for(Menadzer menadzer : this.menadzeri)
		{
			if(menadzer.getKorisnickoIme().equals(korIme))
				return menadzer;
		}
		
		return null;
	}
	
	public void add(Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		this.menadzeri.add(new Menadzer(sprema,staz,bonus,plata,ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka));
		this.saveData();
	}
	
	public void edit(int id, Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		Menadzer izmena = this.pronadjiPoId(id);
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
		this.menadzeri.remove(this.pronadjiPoId(id));
		this.saveData();
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.menadzerFile));
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
				Menadzer noviMenadzer = new Menadzer(sprema, Integer.parseInt(atributi[9]), Integer.parseInt(atributi[10]),
						Integer.parseInt(atributi[11]),atributi[1],atributi[2],
						atributi[3],atributi[4],atributi[5], atributi[6],atributi[7]);
				noviMenadzer.setId(Integer.parseInt(atributi[0]));
				this.menadzeri.add(noviMenadzer);
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
			pw = new PrintWriter(new FileWriter(this.menadzerFile,false));
			for(Menadzer menadzer : this.menadzeri)
			{
				pw.println(menadzer.toFile());
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
