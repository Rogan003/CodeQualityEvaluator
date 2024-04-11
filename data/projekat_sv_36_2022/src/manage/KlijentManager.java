package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import entity.Klijent;
import entity.KozmetickiTretman;

public class KlijentManager {
	private String klijentFile;
	private ArrayList<Klijent> klijenti;
	private KozmetickiTretmanManager tretmanMng;
	
	public KlijentManager(String klijentFile, KozmetickiTretmanManager tretmanMng)
	{
		this.klijentFile = klijentFile;
		this.klijenti = new ArrayList<Klijent>();
		this.tretmanMng = tretmanMng;
	}
	
	public ArrayList<Klijent> getKlijenti()
	{
		return this.klijenti;
	}
	
	public void dodajKt2()
	{
		this.add("Milica", "Milic", "Zenski", 
				"0902461289", "Ulica 121 Trstenik", "kli1", "klijedan");
		this.add("Mika", "Mikic", "Muski", 
				"35312323139", "Ulica 146 Novi Sad", "kli4", "klicetiri");	
	}
	
	public void dodajCLI()
	{
		this.add("Klijent 1", "Jedan", "Muski", 
				"0902461289", "Ulica 121 Trstenik", "kli1", "klijedan");
		this.add("Klijent 4", "Cetiri", "Muski", 
				"35312323139", "Ulica 146 Novi Sad", "kli4", "klicetiri");
		this.add("Klijent 3", "Tri", "Zenski", 
				"123514469124", "Ulica 31 Subotica", "kli3", "klitri");
	}
	
	public void prikaziCLI()
	{
		for(Klijent klijent : this.klijenti)
		{
			System.out.println(klijent);
		}
	}
	
	public void izmeniCLI()
	{
		ArrayList<KozmetickiTretman> tretmani = new ArrayList<KozmetickiTretman>(
				Arrays.asList(this.tretmanMng.getTretmani().get(0)));
		this.edit(this.klijenti.get(1).getId(), 10000, true, tretmani, "Klijent 2",
				"Dva", "Zenski", "3913805279", "Ulica 145 Novi Sad", "kli2", "klidva");
	}
	
	public void obrisiCLI()
	{
		this.remove(this.klijenti.get(2).getId());
	}
	
	public Klijent pronadjiPoId(int id)
	{
		for(Klijent klijent : this.klijenti)
		{
			if(klijent.getId() == id)
				return klijent;
		}
		
		return null;
	}
	
	public Klijent pronadjiPoImenuIPrezimenu(String ime, String prezime)
	{
		for(Klijent klijent : this.klijenti)
		{
			if(klijent.getIme().equals(ime) && klijent.getPrezime().equals(prezime))
				return klijent;
		}
		
		return null;
	}
	
	public Klijent pronadjiPoKorisnickomImenu(String korIme)
	{
		for(Klijent klijent : this.klijenti)
		{
			if(klijent.getKorisnickoIme().equals(korIme))
				return klijent;
		}
		
		return null;
	}
	
	public Klijent add(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		Klijent klijent = new Klijent(ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
		this.klijenti.add(klijent);
		this.saveData();
		return klijent;
	}
	
	public void edit(int id, double potrosio, boolean karticaLojalnosti, ArrayList<KozmetickiTretman> tretmani,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		Klijent izmena = this.pronadjiPoId(id);
		izmena.setPotrosio(potrosio);
		izmena.setKarticaLojalnosti(karticaLojalnosti);
		izmena.setTretmani(tretmani);
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
		this.klijenti.remove(this.pronadjiPoId(id));
		this.saveData();
	}
	
	public ArrayList<Klijent> klijentiPravoKarticaLojalnosti()
	{
		ArrayList<Klijent> imajuPravo = new ArrayList<Klijent>();
		
		for(Klijent klijent : this.klijenti)
		{
			if(klijent.getPotrosio() > Klijent.iznosZaKarticu)
				imajuPravo.add(klijent);
		}
		
		return imajuPravo;
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.klijentFile));
			String linija = null;
			boolean ucitanIznosZaKarticu = false;
			while ((linija = br.readLine()) != null) {
				if(ucitanIznosZaKarticu)
				{
					String[] atributi = linija.split(";");
					ArrayList<KozmetickiTretman> zakazani = new ArrayList<KozmetickiTretman>();
					if(atributi.length > 10)
					{
						for(String s : atributi[10].split(","))
						{
							zakazani.add(this.tretmanMng.pronadjiPoId(Integer.parseInt(s)));
						}
					}
					Klijent noviKlijent = new Klijent(Double.parseDouble(atributi[8]), Boolean.parseBoolean(atributi[9]),
							zakazani,atributi[1],atributi[2],atributi[3],atributi[4],atributi[5], atributi[6],atributi[7]);
					noviKlijent.setId(Integer.parseInt(atributi[0]));
					this.klijenti.add(noviKlijent);
				}
				else
				{
					Klijent.iznosZaKarticu = Integer.parseInt(linija);
					ucitanIznosZaKarticu = true;
				}
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
			pw = new PrintWriter(new FileWriter(this.klijentFile,false));
			pw.println(Klijent.iznosZaKarticu);
			for(Klijent klijent : this.klijenti)
			{
				pw.println(klijent.toFile());
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
