package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import entity.Kozmeticar;
import entity.KozmetickiTretman;
import entity.Sprema;
import entity.StanjeKozmetickogTretmana;
import entity.TipKozmetickogTretmana;
import entity.Zaposleni;

public class KozmeticarManager {
	private String kozmeticarFile;
	private ArrayList<Kozmeticar> kozmeticari;
	private TipKozmetickogTretmanaManager tipMng;
	private KozmetickiTretmanManager tretmanMng;
	
	public KozmeticarManager(String kozmeticarFile, TipKozmetickogTretmanaManager tipMng, KozmetickiTretmanManager tretmanMng)
	{
		this.kozmeticarFile = kozmeticarFile;
		this.kozmeticari = new ArrayList<Kozmeticar>();
		this.tipMng = tipMng;
		this.tretmanMng = tretmanMng;
	}
	
	public ArrayList<Kozmeticar> getKozmeticari()
	{
		return this.kozmeticari;
	}
	
	public ArrayList<Zaposleni> getKozmeticariZaposleni()
	{
		ArrayList<Zaposleni> zaposleni = new ArrayList<Zaposleni>();
		for(Kozmeticar kozmeticar : this.kozmeticari)
		{
			zaposleni.add(kozmeticar);
		}
		
		return zaposleni;
	}
	
	public void dodajKt2()
	{
		this.add(Sprema.PROSECNA, 12, 0, 80000, "Sima", "Simic", "Muski", 
				"0904561289", "Ulica 101 Cacak", "koz1", "kozjedan",new ArrayList<TipKozmetickogTretmana>());
		this.add(Sprema.VISOKA, 131, 0, 35, "Zika", "Zikic", "Muski", 
				"3812323139", "Ulica 142 Novi Sad", "koz2", "kozdva",new ArrayList<TipKozmetickogTretmana>());
		this.add(Sprema.ISPOD_PROSEKA, 5, 0, 65000, "Jadranka", "Jovanovic", "Zenski", 
				"1235126912", "Ulica 32 Zrenjanin", "koz3", "koztri",new ArrayList<TipKozmetickogTretmana>());
	}
	
	public void izmeniKt2()
	{
		Kozmeticar izmena = this.pronadjiPoImenuIPrezimenu("Jadranka","Jovanovic");
		this.edit(izmena.getId(), izmena.getObuceniTipovi(), izmena.getTretmani(), izmena.getSprema(), 
				izmena.getStaz(), izmena.getBonus(), izmena.getPlata(), "Jovana",
				izmena.getPrezime(), izmena.getPol(), izmena.getTelefon(),
				izmena.getAdresa(), izmena.getKorisnickoIme(), izmena.getLozinka());
	}
	
	public void obrisiKt2()
	{
		this.remove(this.pronadjiPoImenuIPrezimenu("Zika","Zikic").getId());
	}
	
	public void dodajObuceneTipoveKt2()
	{
		ArrayList<TipKozmetickogTretmana> tipovi = new ArrayList<TipKozmetickogTretmana>(
				Arrays.asList(this.tipMng.pronadjiPoNazivu("masaza"),this.tipMng.pronadjiPoNazivu("manikir")));
		Kozmeticar izmena = this.pronadjiPoImenuIPrezimenu("Sima","Simic");
		this.edit(izmena.getId(), tipovi, izmena.getTretmani(), izmena.getSprema(), 
				izmena.getStaz(), izmena.getBonus(), izmena.getPlata(), izmena.getIme(),
				izmena.getPrezime(), izmena.getPol(), izmena.getTelefon(),
				izmena.getAdresa(), izmena.getKorisnickoIme(), izmena.getLozinka());
		
		tipovi = new ArrayList<TipKozmetickogTretmana>(
				Arrays.asList(this.tipMng.pronadjiPoNazivu("manikir")));
		izmena = this.pronadjiPoImenuIPrezimenu("Jovana","Jovanovic");
		this.edit(izmena.getId(), tipovi, izmena.getTretmani(), izmena.getSprema(), 
				izmena.getStaz(), izmena.getBonus(), izmena.getPlata(), izmena.getIme(),
				izmena.getPrezime(), izmena.getPol(), izmena.getTelefon(),
				izmena.getAdresa(), izmena.getKorisnickoIme(), izmena.getLozinka());
	}
	
	public void dodajObuceneTipoveKt3()
	{
		ArrayList<TipKozmetickogTretmana> tipovi = new ArrayList<TipKozmetickogTretmana>(
				Arrays.asList(this.tipMng.pronadjiPoNazivu("masaza"),this.tipMng.pronadjiPoNazivu("manikir")));
		Kozmeticar izmena = this.pronadjiPoImenuIPrezimenu("Sima","Simic");
		this.edit(izmena.getId(), tipovi, izmena.getTretmani(), izmena.getSprema(), 
				izmena.getStaz(), izmena.getBonus(), izmena.getPlata(), izmena.getIme(),
				izmena.getPrezime(), izmena.getPol(), izmena.getTelefon(),
				izmena.getAdresa(), izmena.getKorisnickoIme(), izmena.getLozinka());
		
		tipovi = new ArrayList<TipKozmetickogTretmana>(
				Arrays.asList(this.tipMng.pronadjiPoNazivu("manikir")));
		izmena = this.pronadjiPoImenuIPrezimenu("Jovana","Jovanovic");
		this.edit(izmena.getId(), tipovi, izmena.getTretmani(), izmena.getSprema(), 
				izmena.getStaz(), izmena.getBonus(), izmena.getPlata(), izmena.getIme(),
				izmena.getPrezime(), izmena.getPol(), izmena.getTelefon(),
				izmena.getAdresa(), izmena.getKorisnickoIme(), izmena.getLozinka());
		
		tipovi = new ArrayList<TipKozmetickogTretmana>(
				Arrays.asList(this.tipMng.pronadjiPoNazivu("masaza"),this.tipMng.pronadjiPoNazivu("manikir"),
						this.tipMng.pronadjiPoNazivu("pedikir")));
		izmena = this.pronadjiPoImenuIPrezimenu("Zika","Zikic");
		this.edit(izmena.getId(), tipovi, izmena.getTretmani(), izmena.getSprema(), 
				izmena.getStaz(), izmena.getBonus(), izmena.getPlata(), izmena.getIme(),
				izmena.getPrezime(), izmena.getPol(), izmena.getTelefon(),
				izmena.getAdresa(), izmena.getKorisnickoIme(), izmena.getLozinka());
	}
	
	public void dodeliBonusNajboljem()
	{
		Kozmeticar k = null;
		int maks = 0;
		LocalDateTime danas = LocalDateTime.now();
		LocalDateTime mesec = LocalDateTime.now().plusMonths(1);
		for(Kozmeticar kozmeticar : this.kozmeticari)
		{
			int brojTretmana = 0;
			
			for(KozmetickiTretman tretman : kozmeticar.getTretmani())
			{
				if(tretman.getTermin().isAfter(danas) && tretman.getTermin().isBefore(mesec) && 
						tretman.getStanje() == StanjeKozmetickogTretmana.IZVRSEN)
				{
					brojTretmana++;
				}
			}
			

			if(brojTretmana > maks || k == null)
			{
				k = kozmeticar;
				maks = brojTretmana;
			}
		}
		
		k.dodajBonus(3000, maks);
		this.saveData();
	}
	
	public void dodajCLI()
	{
		ArrayList<TipKozmetickogTretmana> tipovi = new ArrayList<TipKozmetickogTretmana>(
				Arrays.asList(this.tipMng.getTipovi().get(0),this.tipMng.getTipovi().get(1)));
		this.add(Sprema.IZNAD_PROSEKA, 12, 0, 80000, "Kozmeticar 1", "Jedan", "Zenski", 
				"0904561289", "Ulica 101 Cacak", "koz1", "kozjedan",tipovi);
		this.add(Sprema.PROSECNA, 131, 0, 35, "Kozmeticar 4", "Cetiri", "Zenski", 
				"3812323139", "Ulica 142 Novi Sad", "koz4", "kozcetiri",tipovi);
		tipovi = new ArrayList<TipKozmetickogTretmana>(Arrays.asList(this.tipMng.getTipovi().get(0),
				this.tipMng.getTipovi().get(1),this.tipMng.getTipovi().get(2)));
		this.add(Sprema.ISPOD_PROSEKA, 5, 0, 65000, "Kozmeticar 3", "Tri", "Muski", 
				"1235126912", "Ulica 32 Zrenjanin", "koz3", "koztri",tipovi);
	}
	
	public void prikaziCLI()
	{
		for(Kozmeticar kozmeticar : this.kozmeticari)
		{
			System.out.println(kozmeticar);
		}
	}
	
	public void izmeniCLI()
	{
		ArrayList<TipKozmetickogTretmana> tipovi = new ArrayList<TipKozmetickogTretmana>(
				Arrays.asList(this.tipMng.getTipovi().get(0)));
		ArrayList<KozmetickiTretman> tretmani = new ArrayList<KozmetickiTretman>(
				Arrays.asList(this.tretmanMng.getTretmani().get(0)));
		this.edit(this.kozmeticari.get(1).getId(), tipovi, tretmani, Sprema.NISKA, 20, 2004, 100000, "Kozmeticar 2",
				"Dva", "Zenski", "136523139", "Ulica 122 Novi Sad", "koz2", "kozdva");
	}
	
	public void obrisiCLI()
	{
		this.remove(this.kozmeticari.get(2).getId());
	}
	
	public Kozmeticar pronadjiPoId(int id)
	{
		for(Kozmeticar kozmeticar : this.kozmeticari)
		{
			if(kozmeticar.getId() == id)
				return kozmeticar;
		}
		
		return null;
	}
	
	public Kozmeticar pronadjiPoKorisnickomImenu(String korIme)
	{
		for(Kozmeticar kozmeticar : this.kozmeticari)
		{
			if(kozmeticar.getKorisnickoIme().equals(korIme))
				return kozmeticar;
		}
		
		return null;
	}
	
	public Kozmeticar pronadjiPoImenuIPrezimenu(String ime, String prezime)
	{
		for(Kozmeticar kozmeticar : this.kozmeticari)
		{
			if(kozmeticar.getIme().equals(ime) && kozmeticar.getPrezime().equals(prezime))
				return kozmeticar;
		}
		
		return null;
	}
	
	public void add(Sprema sprema, int staz, int bonus, int plata, String ime, String prezime, String pol, String telefon,
			  String adresa, String korisnickoIme, String lozinka, ArrayList<TipKozmetickogTretmana> obuceniTipovi)
	{
		this.kozmeticari.add(new Kozmeticar(sprema, staz, bonus, plata, ime, prezime, pol, telefon, adresa, korisnickoIme,
				lozinka, obuceniTipovi));
		this.saveData();
	}
	
	public void edit(int id, ArrayList<TipKozmetickogTretmana> obuceniTipovi, ArrayList<KozmetickiTretman> tretmani,
			Sprema sprema, int staz, int bonus, int plata, String ime, String prezime, String pol, String telefon,
			String adresa, String korisnickoIme, String lozinka)
	{
		Kozmeticar izmena = this.pronadjiPoId(id);
		izmena.setObuceniTipovi(obuceniTipovi);
		izmena.setTretmani(tretmani);
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
		this.kozmeticari.remove(this.pronadjiPoId(id));
		this.saveData();
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.kozmeticarFile));
			String linija = null;
			boolean ucitanBonus = false;
			while ((linija = br.readLine()) != null) {
				if(ucitanBonus)
				{
					String[] atributi = linija.split(";");
					ArrayList<TipKozmetickogTretmana> tipovi = new ArrayList<TipKozmetickogTretmana>();
					if(atributi.length > 12)
					{
						for(String s : atributi[12].split(","))
						{
							tipovi.add(this.tipMng.pronadjiPoId(Integer.parseInt(s)));
						}
					}
					ArrayList<KozmetickiTretman> zakazani = new ArrayList<KozmetickiTretman>();
					if(atributi.length > 13)
					{
						for(String s : atributi[13].split(","))
						{
							zakazani.add(this.tretmanMng.pronadjiPoId(Integer.parseInt(s)));
						}
					}
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
					Kozmeticar noviKozmeticar = new Kozmeticar(tipovi,zakazani,
							sprema, Integer.parseInt(atributi[9]), Integer.parseInt(atributi[10]),
							Integer.parseInt(atributi[11]),atributi[1],atributi[2],
							atributi[3],atributi[4],atributi[5], atributi[6],atributi[7]);
					noviKozmeticar.setId(Integer.parseInt(atributi[0]));
					this.kozmeticari.add(noviKozmeticar);
				}
				else
				{
					String[] atributi = linija.split(";");
					Kozmeticar.bonusPoTretmanu = Integer.parseInt(atributi[0]);
					Kozmeticar.brojTretmanaBonus = Integer.parseInt(atributi[1]);
					Kozmeticar.zaradaBonus = Double.parseDouble(atributi[2]);
					ucitanBonus = true;
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
			pw = new PrintWriter(new FileWriter(this.kozmeticarFile,false));
			pw.println(Kozmeticar.bonusPoTretmanu + ";" + Kozmeticar.brojTretmanaBonus + ";" + Kozmeticar.zaradaBonus);
			for(Kozmeticar kozmeticar : this.kozmeticari)
			{
				pw.println(kozmeticar.toFile());
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
