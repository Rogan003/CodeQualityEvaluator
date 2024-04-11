package manage;

import entity.TipKozmetickogTretmana;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import entity.Klijent;
import entity.Korisnik;
import entity.Kozmeticar;
import entity.KozmetickaUsluga;
import entity.KozmetickiTretman;
import entity.Menadzer;
import entity.Recepcioner;
import entity.Sprema;
import entity.StanjeKozmetickogTretmana;
import utils.AppSettings;

public class ManagerFactory {
	private AppSettings appSettings;
	private CenovnikManager cenovnikMng;
	private KlijentManager klijentMng;
	private KozmeticarManager kozmeticarMng;
	private KozmetickaUslugaManager uslugaMng;
	private KozmetickiSalonManager salonMng;
	private KozmetickiTretmanManager tretmanMng;
	private MenadzerManager menadzerMng;
	private RecepcionerManager recepcionerMng;
	private TipKozmetickogTretmanaManager tipMng;
	
	public ManagerFactory(AppSettings appSettings)
	{
		this.appSettings = appSettings;
		this.salonMng = new KozmetickiSalonManager(this.appSettings.getKozmetickiSalonFilename());
		this.menadzerMng = new MenadzerManager(this.appSettings.getMenadzerFilename());
		this.recepcionerMng = new RecepcionerManager(this.appSettings.getRecepcionerFilename());
		this.tipMng = new TipKozmetickogTretmanaManager(this.appSettings.getTipKozmetickogTretmanaFilename());
		this.uslugaMng = new KozmetickaUslugaManager(this.appSettings.getKozmetickaUslugaFilename(), this.tipMng);
		this.cenovnikMng = new CenovnikManager(this.appSettings.getCenovnikFilename(), this.uslugaMng);
		this.tretmanMng = new KozmetickiTretmanManager(this.appSettings.getKozmetickiTretmanFilename(), this.uslugaMng, 
				this.cenovnikMng);
		this.kozmeticarMng = new KozmeticarManager(this.appSettings.getKozmeticarFilename(), this.tipMng, this.tretmanMng);
		this.klijentMng = new KlijentManager(this.appSettings.getKlijentFilename(), this.tretmanMng);
	}
	
	public CenovnikManager getCenovnikMng() {
		return this.cenovnikMng;
	}

	public KlijentManager getKlijentMng() {
		return this.klijentMng;
	}

	public KozmeticarManager getKozmeticarMng() {
		return this.kozmeticarMng;
	}

	public KozmetickaUslugaManager getUslugaMng() {
		return this.uslugaMng;
	}

	public KozmetickiSalonManager getSalonMng() {
		return this.salonMng;
	}

	public KozmetickiTretmanManager getTretmanMng() {
		return this.tretmanMng;
	}

	public MenadzerManager getMenadzerMng() {
		return this.menadzerMng;
	}

	public RecepcionerManager getRecepcionerMng() {
		return this.recepcionerMng;
	}

	public TipKozmetickogTretmanaManager getTipMng() {
		return this.tipMng;
	}
	
	public void test()
	{
		this.salonMng.postaviKozmetickiSalon();
		this.salonMng.ispisiKozmetickiSalon();
		this.salonMng.izmeniKozmetickiSalon();
		this.salonMng.ispisiKozmetickiSalon();
		
		this.menadzerMng.dodajCLI();
		this.menadzerMng.prikaziCLI();
		this.menadzerMng.izmeniCLI();
		this.menadzerMng.prikaziCLI();
		this.menadzerMng.obrisiCLI();
		this.menadzerMng.prikaziCLI();
		
		this.recepcionerMng.dodajCLI();
		this.recepcionerMng.prikaziCLI();
		this.recepcionerMng.izmeniCLI();
		this.recepcionerMng.prikaziCLI();
		this.recepcionerMng.obrisiCLI();
		this.recepcionerMng.prikaziCLI();
		
		this.tipMng.dodajCLI();
		this.tipMng.prikaziCLI();
		this.tipMng.izmeniCLI();
		this.tipMng.prikaziCLI();
		this.tipMng.obrisiCLI();
		this.tipMng.prikaziCLI();
		
		this.uslugaMng.dodajCLI();
		this.uslugaMng.prikaziCLI();
		this.uslugaMng.izmeniCLI();
		this.uslugaMng.prikaziCLI();
		this.uslugaMng.obrisiCLI();
		this.uslugaMng.prikaziCLI();
		
		this.cenovnikMng.postaviCenovnik();
		this.cenovnikMng.dodajNaCenovnikCLI();;
		this.cenovnikMng.prikaziCLI();
		this.cenovnikMng.izmeniSaCenovnikaCLI();;
		this.cenovnikMng.prikaziCLI();
		this.cenovnikMng.skiniSaCenovnikaCLI();
		this.cenovnikMng.prikaziCLI();
		
		this.tretmanMng.dodajCLI();
		this.tretmanMng.prikaziCLI();
		this.tretmanMng.izmeniCLI();
		this.tretmanMng.prikaziCLI();
		this.tretmanMng.obrisiCLI();
		this.tretmanMng.prikaziCLI();
		
		this.kozmeticarMng.dodajCLI();
		this.kozmeticarMng.prikaziCLI();
		this.kozmeticarMng.izmeniCLI();
		this.kozmeticarMng.prikaziCLI();
		this.kozmeticarMng.obrisiCLI();
		this.kozmeticarMng.prikaziCLI();
		
		this.klijentMng.dodajCLI();
		this.klijentMng.prikaziCLI();
		this.klijentMng.izmeniCLI();
		this.klijentMng.prikaziCLI();
		this.klijentMng.obrisiCLI();
		this.klijentMng.prikaziCLI();
	}
	
	public void test2()
	{
		this.salonMng.ispisiKozmetickiSalon();
		this.menadzerMng.prikaziCLI();
		this.recepcionerMng.prikaziCLI();
		this.tipMng.prikaziCLI();
		this.uslugaMng.prikaziCLI();
		this.cenovnikMng.prikaziCLI();
		this.tretmanMng.prikaziCLI();
		this.kozmeticarMng.prikaziCLI();
		this.klijentMng.prikaziCLI();
	}
	
	public void obrisiTip(String naziv)
	{
		TipKozmetickogTretmana tip = this.tipMng.pronadjiPoNazivu(naziv);
		this.tipMng.remove(tip.getId());
		
		ArrayList<KozmetickaUsluga> brisanje = new ArrayList<KozmetickaUsluga>();
		for(KozmetickaUsluga usluga : this.uslugaMng.getUsluge())
		{
			if(usluga.getTip() == tip)
			{
				brisanje.add(usluga);
			}
		}
		
		for(KozmetickaUsluga usluga : brisanje)
		{
			this.uslugaMng.remove(usluga.getId());
			this.cenovnikMng.skiniSaCenovnika(usluga);
		}
	}
	
	public void zakaziTretmane()
	{
		KozmetickaUsluga usluga = this.uslugaMng.pronadjiPoNazivu("Relaks masaza");
		KozmetickiTretman tretman = this.tretmanMng.add(usluga, StanjeKozmetickogTretmana.ZAKAZAN,
				LocalDateTime.of(2023, 5, 18, 13, 00), this.cenovnikMng.pronadjiCenu(usluga));
		Kozmeticar kozmeticar = this.kozmeticarMng.pronadjiPoImenuIPrezimenu("Sima", "Simic");
		kozmeticar.zakaziTretman(tretman);
		Klijent klijent = this.klijentMng.pronadjiPoImenuIPrezimenu("Milica", "Milic");
		klijent.zakaziTretman(tretman);
		
		usluga = this.uslugaMng.pronadjiPoNazivu("Gel lak");
		tretman = this.tretmanMng.add(usluga, StanjeKozmetickogTretmana.ZAKAZAN,
				LocalDateTime.of(2023, 5, 19, 11, 00), this.cenovnikMng.pronadjiCenu(usluga));
		kozmeticar = this.kozmeticarMng.pronadjiPoImenuIPrezimenu("Sima", "Simic");
		kozmeticar.zakaziTretman(tretman);
		klijent = this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic");
		klijent.zakaziTretman(tretman);
		
		usluga = this.uslugaMng.pronadjiPoNazivu("Spa manikir");
		tretman = this.tretmanMng.add(usluga, StanjeKozmetickogTretmana.ZAKAZAN,
				LocalDateTime.of(2023, 5, 18, 10, 00), this.cenovnikMng.pronadjiCenu(usluga));
		kozmeticar = this.kozmeticarMng.pronadjiPoImenuIPrezimenu("Jovana", "Jovanovic");
		kozmeticar.zakaziTretman(tretman);
		klijent = this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic");
		klijent.zakaziTretman(tretman);
	}
	
	public void prikaziTretmane()
	{
		for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
		{
			System.out.println(tretman);
			
			for(Klijent klijent : this.klijentMng.getKlijenti())
			{
				if(klijent.getTretmani().contains(tretman))
				{
					System.out.println("Klijent: " + klijent.getIme() + " " + klijent.getPrezime());
					break;
				}
			}
			
			for(Kozmeticar kozmeticar : this.kozmeticarMng.getKozmeticari())
			{
				if(kozmeticar.getTretmani().contains(tretman))
				{
					System.out.println("Kozmeticar: " + kozmeticar.getIme() + " " + kozmeticar.getPrezime());
					break;
				}
			}
			
			System.out.println();
		}
	}
	
	public void testKt2()
	{
		this.salonMng.postaviKozmetickiSalon();
		System.out.println("Kozmeticki salon: ");
		this.salonMng.ispisiKozmetickiSalon();
		System.out.println("===========================");
		
		this.menadzerMng.dodajKt2();
		this.recepcionerMng.dodajKt2();
		this.kozmeticarMng.dodajKt2();
		this.klijentMng.dodajKt2();
		
		this.kozmeticarMng.izmeniKt2();
		System.out.println("Menadzeri:");
		this.menadzerMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Recepcioneri:");
		this.recepcionerMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Kozmeticari:");
		this.kozmeticarMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Klijenti:");
		this.klijentMng.prikaziCLI();
		System.out.println("===========================");
		
		this.kozmeticarMng.obrisiKt2();
		System.out.println("Menadzeri:");
		this.menadzerMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Recepcioneri:");
		this.recepcionerMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Kozmeticari:");
		this.kozmeticarMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Klijenti:");
		this.klijentMng.prikaziCLI();
		System.out.println("===========================");
		
		this.tipMng.dodajKt2();
		this.uslugaMng.dodajKt2();
		this.cenovnikMng.dodajKt2();
		System.out.println("Usluge:");
		this.uslugaMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Cenovnik: ");
		this.cenovnikMng.prikaziCLI();
		System.out.println("===========================");
		
		this.uslugaMng.izmeniKt2();
		System.out.println("Usluge:");
		this.uslugaMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Cenovnik: ");
		this.cenovnikMng.prikaziCLI();
		System.out.println("===========================");
		
		this.obrisiTip("pedikir");
		System.out.println("Usluge:");
		this.uslugaMng.prikaziCLI();
		System.out.println("===========================");
		System.out.println("Cenovnik: ");
		this.cenovnikMng.prikaziCLI();
		System.out.println("===========================");
		
		this.kozmeticarMng.dodajObuceneTipoveKt2();
		this.zakaziTretmane();
		System.out.println("Zakazani tretmani: ");
		this.prikaziTretmane();
		System.out.println("===========================");
		
		this.tretmanMng.izmeniKt2();
		System.out.println("Zakazani tretmani: ");
		this.prikaziTretmane();
		System.out.println("===========================");
		
		System.out.println("Cene zakazanih tretmana(i malo sire): ");
		this.tretmanMng.prikaziCLI();
		System.out.println("===========================");
		this.cenovnikMng.izmeniSaCenovnika(this.uslugaMng.pronadjiPoNazivu("Relaks masaza"), 1700);
		System.out.println("Cene zakazanih tretmana(i malo sire): ");
		this.tretmanMng.prikaziCLI();
		System.out.println("===========================");
	}
	
	public void setKt3()
	{
		this.salonMng.postaviKozmetickiSalon();
		
		this.menadzerMng.dodajKt2();
		this.recepcionerMng.dodajKt2();
		this.kozmeticarMng.dodajKt2();
		this.klijentMng.dodajKt2();
		
		this.kozmeticarMng.izmeniKt2();
		
		this.tipMng.dodajKt2();
		this.uslugaMng.dodajKt2();
		this.cenovnikMng.dodajKt2();
		this.tretmanMng.reset();
		
		this.kozmeticarMng.dodajObuceneTipoveKt3();
	}
	
	public void testKt3()
	{
		System.out.println("Kontrolna tacka 3\n====================");
		
		KozmetickaUsluga fm = this.uslugaMng.pronadjiPoNazivu("Francuski manikir");
		this.zakaziTretman(this.klijentMng.pronadjiPoImenuIPrezimenu("Milica", "Milic"),
				this.kozmeticarMng.getKozmeticari().get(2), fm, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 6, 10, 18, 00), this.cenovnikMng.pronadjiCenu(fm));
		
		KozmetickaUsluga sp = this.uslugaMng.pronadjiPoNazivu("Spa pedikir");
		this.zakaziTretman(this.klijentMng.pronadjiPoImenuIPrezimenu("Milica", "Milic"),
				this.kozmeticarMng.getKozmeticari().get(1), sp, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 6, 11, 9, 00), this.cenovnikMng.pronadjiCenu(sp));
		
		KozmetickaUsluga sm = this.uslugaMng.pronadjiPoNazivu("Sportska masaza");
		this.zakaziTretman(this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic"),
				this.kozmeticarMng.getKozmeticari().get(0), sm, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 6, 12, 8, 00), this.cenovnikMng.pronadjiCenu(sm));
		KozmetickaUsluga rm = this.uslugaMng.pronadjiPoNazivu("Relaks masaza");
		this.zakaziTretman(this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic"),
				this.kozmeticarMng.getKozmeticari().get(1), rm, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 6, 13, 19, 00), this.cenovnikMng.pronadjiCenu(rm));
		
		this.zakaziTretman(this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic"),
				this.kozmeticarMng.getKozmeticari().get(2), fm, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 6, 10, 18, 00), this.cenovnikMng.pronadjiCenu(fm)); // ovde ce biti greska
		
		System.out.println();
		
		System.out.println("Kozmeticar 2, tretmani i raspored: ");
		this.ispisiTretmaneiRaspored(kozmeticarMng.getKozmeticari().get(1));
		
		System.out.println();
		
		this.postaviIznosZaKarticu(this.menadzerMng.getMenadzeri().get(0), 3500);
		
		Klijent klijent = this.klijentMng.pronadjiPoImenuIPrezimenu("Milica", "Milic");
		KozmetickiTretman tretman = klijent.getTretmani().get(0);
		System.out.println("Stanje tretmana pre: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti pre: " + klijent.getPotrosio());
		System.out.println("Bilans salona pre: " + this.getBilansSalon());
		this.izvrsenTretman(tretman);
		System.out.println("Stanje tretmana posle: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti posle: " + klijent.getPotrosio());
		System.out.println("Bilans salona posle: " + this.getBilansSalon());
		System.out.println();
		
		tretman = klijent.getTretmani().get(1);
		System.out.println("Stanje tretmana pre: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti pre: " + klijent.getPotrosio());
		System.out.println("Bilans salona pre: " + this.getBilansSalon());
		this.otkaziTretman(tretman, true);
		System.out.println("Stanje tretmana posle: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti posle: " + klijent.getPotrosio());
		System.out.println("Bilans salona posle: " + this.getBilansSalon());
		System.out.println();
		
		klijent = this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic");
		tretman = klijent.getTretmani().get(0);
		System.out.println("Stanje tretmana pre: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti pre: " + klijent.getPotrosio());
		System.out.println("Bilans salona pre: " + this.getBilansSalon());
		this.otkaziTretman(tretman, false);
		System.out.println("Stanje tretmana posle: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti posle: " + klijent.getPotrosio());
		System.out.println("Bilans salona posle: " + this.getBilansSalon());
		System.out.println();
		
		klijent = this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic");
		tretman = klijent.getTretmani().get(1);
		System.out.println("Stanje tretmana pre: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti pre: " + klijent.getPotrosio());
		System.out.println("Bilans salona pre: " + this.getBilansSalon());
		this.nijeSePojavio(tretman);
		System.out.println("Stanje tretmana posle: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti posle: " + klijent.getPotrosio());
		System.out.println("Bilans salona posle: " + this.getBilansSalon());
		System.out.println();
		
		KozmetickaUsluga gl = this.uslugaMng.pronadjiPoNazivu("Gel lak");
		this.zakaziTretman(this.klijentMng.pronadjiPoImenuIPrezimenu("Milica", "Milic"),
				this.kozmeticarMng.getKozmeticari().get(0), gl, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 6, 14, 9, 00), this.cenovnikMng.pronadjiCenu(gl));
		
		KozmetickaUsluga sm2 = this.uslugaMng.pronadjiPoNazivu("Spa manikir");
		this.zakaziTretman(this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic"),
				null, sm2, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 6, 14, 9, 00), this.cenovnikMng.pronadjiCenu(sm2));
		
		klijent = this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic");
		tretman = klijent.getTretmani().get(2);
		this.izvrsenTretman(tretman);
		System.out.println("Stanje tretmana posle: " + tretman.getStanje());
		System.out.println("Stanje na kartici lojalnosti posle: " + klijent.getPotrosio());
		System.out.println("Bilans salona posle: " + this.getBilansSalon());
		System.out.println();
		
		System.out.println(this.klijentMng.pronadjiPoImenuIPrezimenu("Mika", "Mikic"));
		System.out.println();
		
		//System.out.println("Prihodi: " + this.getPrihodi());
		//System.out.println("Rashodi: " + this.getRashodi());
		System.out.println();
		
		this.kozmeticarMng.dodeliBonusNajboljem();
		for(Kozmeticar kozmeticar : this.kozmeticarMng.getKozmeticari())
		{
			System.out.println("Kozmeticar: " + kozmeticar.getIme() + " " + kozmeticar.getPrezime());
			System.out.println("Bonus: " + kozmeticar.getBonus());
		}
		System.out.println();
		
	}
	
	public void loadData()
	{
		this.salonMng.loadData();
		this.menadzerMng.loadData();
		this.recepcionerMng.loadData();
		this.tipMng.loadData();
		this.uslugaMng.loadData();
		this.cenovnikMng.loadData();
		this.tretmanMng.loadData();
		this.kozmeticarMng.loadData();
		this.klijentMng.loadData();
	}
	
	// same funkcionalnosti projekta
	
	public Klijent registrujKlijenta(String ime, String prezime, String pol, String telefon, 
			String adresa, String korisnickoIme, String lozinka)
	{
		return this.klijentMng.add(ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka);
	}
	
	public void registrujMenadzera(Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		this.menadzerMng.add(sprema, staz, bonus, plata, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka);
	}
	
	public void registrujRecepcionera(Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		this.recepcionerMng.add(sprema, staz, bonus, plata, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka);
	}
	
	public void registrujKozmeticara(Sprema sprema, int staz, int bonus, int plata, String ime, String prezime, String pol, String telefon,
			  String adresa, String korisnickoIme, String lozinka, ArrayList<TipKozmetickogTretmana> obuceniTipovi)
	{
		this.kozmeticarMng.add(sprema, staz, bonus, plata, ime, prezime, pol, telefon, adresa, korisnickoIme, lozinka, obuceniTipovi);
	}
	
	public boolean zakaziTretman(Klijent klijent, Kozmeticar kozmeticar,
			KozmetickaUsluga usluga, StanjeKozmetickogTretmana stanje, LocalDateTime termin, double cena)
	{
		boolean isOkay = true;
		
		if(termin.getHour() >= this.salonMng.getKozmetickiSalon().getRadnoVreme()[1].getHour()
				|| termin.getHour() < this.salonMng.getKozmetickiSalon().getRadnoVreme()[0].getHour())
		{
			isOkay = false;
		}
		
		if(kozmeticar != null)
		{
			if(!(kozmeticar.getObuceniTipovi().contains(usluga.getTip())))
			{
				isOkay = false;
			}
			
			if(isOkay)
			{
				for(LocalDateTime vreme : kozmeticar.getRasporedZauzetih())
				{
					if(vreme.equals(termin))
					{
						isOkay = false;
					}
				}
			}
		}
		else
		{
			for(Kozmeticar k : this.kozmeticarMng.getKozmeticari())
			{
				if(k.getObuceniTipovi().contains(usluga.getTip()))
				{
					boolean free = true;
					
					for(LocalDateTime vreme : k.getRasporedZauzetih())
					{
						if(vreme.equals(termin))
						{
							free = false;
						}
					}
					
					if(free)
					{
						kozmeticar = k;
						break;
					}
				}
			}
		}
		
		if(isOkay)
		{
			for(KozmetickiTretman tretman : klijent.getTretmani())
			{
				if(tretman.getTermin().equals(termin))
				{
					isOkay = false;
				}
			}
		}
		
		if(isOkay)
		{
			KozmetickiTretman tretman = this.tretmanMng.add(usluga, stanje, termin, cena);
			kozmeticar.zakaziTretman(tretman);
			klijent.zakaziTretman(tretman);
			this.kozmeticarMng.saveData();
			this.klijentMng.saveData();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void otkaziTretman(KozmetickiTretman tretman, boolean klijent)
	{	
		for(Klijent k : this.klijentMng.getKlijenti())
		{
			if(k.getTretmani().contains(tretman))
			{
				k.otkaziTretman(tretman,!klijent);
				break;
			}
		}
		
		this.tretmanMng.saveData();
		this.klijentMng.saveData();
		this.kozmeticarMng.saveData();
	}
	
	public double getPrihodi(LocalDateTime pocetak,LocalDateTime kraj)
	{
		double prihodi = 0;
		for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
		{
			if(tretman.getTermin().isAfter(pocetak) && tretman.getTermin().isBefore(kraj))
			{
				prihodi += tretman.getCena();
			}
		}
		
		return prihodi;
	}
	
	public double getRashodi(LocalDateTime pocetak,LocalDateTime kraj)
	{
		double rashodi = 0;
		for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
		{
			if(tretman.getTermin().isAfter(pocetak) && tretman.getTermin().isBefore(kraj))
			{
				if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
				{
					rashodi += tretman.getCena() * 0.9;
				}
				else if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_SALON)
				{
					rashodi += tretman.getCena();
				}
			}
		}
		
		return rashodi;
	}
	
	public void postaviIznosZaKarticu(Menadzer mng, int iznos)
	{
		mng.setIznosZaKarticu(iznos);
		this.klijentMng.saveData();
	}
	
	public int preuzmiKarticu(Klijent klijent)
	{
		int rez = klijent.preuzmiKarticuLojalnosti();
		this.klijentMng.saveData();
		
		return rez;
	}
	
	public void postaviBonus(int iznos, int brojTretmana, double zarada)
	{
		Kozmeticar.bonusPoTretmanu = iznos;
		Kozmeticar.brojTretmanaBonus = brojTretmana;
		Kozmeticar.zaradaBonus = zarada;
		this.kozmeticarMng.saveData();
	}
	
	public boolean dodeliBonus()
	{
		if(Kozmeticar.bonusPoTretmanu == 0)
		{
			return false;
		}
		else
		{
			for(Kozmeticar k : this.kozmeticarMng.getKozmeticari())
			{
				double zarada = 0;
				int broj = 0;
				for(KozmetickiTretman tretman : k.getTretmani())
				{
					if(tretman.getTermin().isAfter(LocalDateTime.now().minusMonths(1)) && 
							tretman.getTermin().isBefore(LocalDateTime.now()))
					{
						if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
						{
							zarada += 0.1 * tretman.getCena();
						}
						else if(tretman.getStanje() != StanjeKozmetickogTretmana.OTKAZAO_SALON)
						{
							broj++;
							zarada += tretman.getCena();
						}
					}
				}
				
				if(broj >= Kozmeticar.brojTretmanaBonus && zarada >= Kozmeticar.zaradaBonus)
				{
					k.setBonus(Kozmeticar.bonusPoTretmanu);
				}
			}
			
			return true;
		}
	}
	
	public void nijeSePojavio(KozmetickiTretman tretman)
	{
		tretman.setStanjeKozmetickogTretmana(StanjeKozmetickogTretmana.NIJE_SE_POJAVIO);
		for(Klijent klijent : this.klijentMng.getKlijenti())
		{
			if(klijent.getTretmani().contains(tretman))
			{
				klijent.nijeSePojavio(tretman);
				break;
			}
		}
		
		this.tretmanMng.saveData();
		this.klijentMng.saveData();
		this.kozmeticarMng.saveData();
	}
	
	public void izvrsenTretman(KozmetickiTretman tretman)
	{
		tretman.setStanjeKozmetickogTretmana(StanjeKozmetickogTretmana.IZVRSEN);
		this.tretmanMng.saveData();
		this.klijentMng.saveData();
		this.kozmeticarMng.saveData();
	}
	
	public void klijentiPravoKarticaLojalnosti()
	{
		this.klijentMng.klijentiPravoKarticaLojalnosti();
	}
	
	public void obrisiTipTretmana(TipKozmetickogTretmana tip)
	{	
		ArrayList<KozmetickaUsluga> brisanje = new ArrayList<KozmetickaUsluga>();
		for(KozmetickaUsluga usluga : this.uslugaMng.getUsluge())
		{
			if(usluga.getTip() == tip)
			{
				brisanje.add(usluga);
			}
		}
		
		for(KozmetickaUsluga usluga : brisanje)
		{
			this.uslugaMng.remove(usluga.getId());
			this.cenovnikMng.skiniSaCenovnika(usluga);
		}
	}
	
	public HashMap<String,Double[]> tretmaniIPrihodi(LocalDateTime pocetak,LocalDateTime kraj)
	{
		HashMap<String,Double[]> tip = new HashMap<String,Double[]>();
		
		for(Kozmeticar kozmeticar : this.kozmeticarMng.getKozmeticari())
		{
			int brojTretmana = 0;
			double prihodi = 0;
			for(KozmetickiTretman tretman : kozmeticar.getTretmani())
			{
				if(tretman.getTermin().isAfter(pocetak) && tretman.getTermin().isBefore(kraj))
				{
					brojTretmana++;
					if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
					{
						prihodi += tretman.getCena() * 0.1;
					}
					else if(tretman.getStanje() != StanjeKozmetickogTretmana.OTKAZAO_SALON)
					{
						prihodi += tretman.getCena();
					}
				}
			}
			
			tip.put(kozmeticar.getIme() + " " + kozmeticar.getPrezime(), new Double []{(double) brojTretmana,prihodi});
		}
		
		return tip;
	}
	
	public int[] tretmaniIStanje(LocalDateTime pocetak,LocalDateTime kraj)
	{
		int otkazaniSalon = 0;
		int otkazaniKlijent = 0;
		int potvrdjeni = 0;
		for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
		{
			if(tretman.getTermin().isAfter(pocetak) && tretman.getTermin().isBefore(kraj))
			{
				if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_SALON)
				{
					otkazaniSalon += 1;
				}
				else if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
				{
					otkazaniKlijent += 1;
				}
				else if(tretman.getStanje() == StanjeKozmetickogTretmana.IZVRSEN)
				{
					potvrdjeni += 1;
				}
			}
		}
		
		return new int [] {potvrdjeni, otkazaniKlijent, otkazaniSalon};
	}
	
	public HashMap<KozmetickaUsluga,Double[]> prikazKozmetickeUsluge(LocalDateTime pocetak,LocalDateTime kraj)
	{
		HashMap<KozmetickaUsluga,Double[]> usluge = new HashMap<KozmetickaUsluga,Double[]>();
		
		for(KozmetickaUsluga usluga : this.uslugaMng.getUsluge())
		{
			double prihodi = 0;
			int brojTretmana = 0;
			for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
			{
				if(tretman.getUsluga() == usluga && tretman.getTermin().isBefore(kraj) &&
						tretman.getTermin().isAfter(pocetak))
				{
					brojTretmana++;
					if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
					{
						prihodi += tretman.getCena() * 0.1;
					}
					else if(tretman.getStanje() != StanjeKozmetickogTretmana.OTKAZAO_SALON)
					{
						prihodi += tretman.getCena();
					} 
				}
			}
			
			usluge.put(usluga, new Double[] {(double) brojTretmana,prihodi});
		}
		
		return usluge;
	}
	
	public void prikazKlijenataKartica()
	{
		if(Klijent.iznosZaKarticu == -1)
		{
			System.out.println("Nijedan menadzer nije postavio iznos za karticu!");
		}
		else
		{
			for(Klijent klijent : this.klijentMng.getKlijenti())
			{
				if(klijent.getPotrosio() >= Klijent.iznosZaKarticu)
				{
					System.out.println(klijent.getIme() + " " + klijent.getPrezime());
				}
			}
		}
	}
	
	public Korisnik login(String username, String password)
	{
		for(Klijent klijent : this.klijentMng.getKlijenti())
		{
			if(klijent.getKorisnickoIme().equals(username) && klijent.getLozinka().equals(password))
			{
				return klijent;
			}
		}
		
		for(Kozmeticar kozmeticar : this.kozmeticarMng.getKozmeticari())
		{
			if(kozmeticar.getKorisnickoIme().equals(username) && kozmeticar.getLozinka().equals(password))
			{
				return kozmeticar;
			}
		}
		
		for(Recepcioner recepcioner : this.recepcionerMng.getRecepcioner())
		{
			if(recepcioner.getKorisnickoIme().equals(username) && recepcioner.getLozinka().equals(password))
			{
				return recepcioner;
			}
		}
		
		for(Menadzer menadzer : this.menadzerMng.getMenadzeri())
		{
			if(menadzer.getKorisnickoIme().equals(username) && menadzer.getLozinka().equals(password))
			{
				return menadzer;
			}
		}
		
		return null;
	}
	
	public void ispisiTretmaneiRaspored(Kozmeticar kozmeticar)
	{
		for(KozmetickiTretman tretman : kozmeticar.getTretmani())
		{
			if(tretman.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN)
			{
				System.out.println(tretman.getTermin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + " " +
						tretman.getUsluga().getPodtip() + " " + tretman.getCena());
			}
		}
		
		kozmeticar.ispisiRaspored();
	}
	
	public double getBilansSalon()
	{
		double prihodi = 0;
		
		for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
		{
			if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
			{
				prihodi += tretman.getCena() * 0.1;
			}
			else if(tretman.getStanje() != StanjeKozmetickogTretmana.OTKAZAO_SALON)
			{
				prihodi += tretman.getCena();
			}
		}
		
		return prihodi;
	}
	
	public HashMap<String,Integer> angazovanjeKozmeticari()
	{
		HashMap<String,Integer> angazovanje = new HashMap<String,Integer>();
		
		for(Kozmeticar kozmeticar : this.kozmeticarMng.getKozmeticari())
		{
			int brojTretmana = 0;
			
			for(KozmetickiTretman tretman : kozmeticar.getTretmani())
			{
				if(tretman.getTermin().isAfter(LocalDateTime.now().minusDays(30)) && tretman.getTermin().isBefore(LocalDateTime.now())
						&& tretman.getStanje() == StanjeKozmetickogTretmana.IZVRSEN)
				{
					brojTretmana++;
				}
			}
			
			angazovanje.put(kozmeticar.getIme() + " " + kozmeticar.getPrezime(), brojTretmana);
		}
		
		return angazovanje;
	}
	
	public HashMap<String,Integer> zastupljenostUsluga()
	{
		HashMap<String,Integer> zastupljenost = new HashMap<String,Integer>();
		zastupljenost.put("ZAKAZAN",0);
		zastupljenost.put("OTKAZAO SALON", 0);
		zastupljenost.put("OTKAZAO KLIJENT", 0);
		zastupljenost.put("IZVRSEN", 0);
		zastupljenost.put("NIJE SE POJAVIO", 0);
		
		for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
		{
			if(tretman.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN)
			{
				zastupljenost.replace("ZAKAZAN", zastupljenost.get("ZAKAZAN") + 1);
			}
			else if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_SALON)
			{
				zastupljenost.replace("OTKAZAO SALON", zastupljenost.get("OTKAZAO SALON") + 1);
			}
			else if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
			{
				zastupljenost.replace("OTKAZAO KLIJENT", zastupljenost.get("OTKAZAO KLIJENT") + 1);
			}
			else if(tretman.getStanje() == StanjeKozmetickogTretmana.IZVRSEN)
			{
				zastupljenost.replace("IZVRSEN", zastupljenost.get("IZVRSEN") + 1);
			}
			else
			{
				zastupljenost.replace("NIJE SE POJAVIO", zastupljenost.get("NIJE SE POJAVIO") + 1);
			}
		}
		
		return zastupljenost;
	}
	
	public HashMap<String,Double[]> prihodiPoTipu()
	{
		HashMap<String,Double[]> prihodi = new HashMap<String,Double[]>();
		
		for(TipKozmetickogTretmana tip : this.tipMng.getTipovi())
		{
			prihodi.put(tip.getNaziv(), new Double[] {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0});
		}
		
		prihodi.put("Ukupno", new Double[] {0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0});
		
		for(KozmetickiTretman tretman : this.tretmanMng.getTretmani())
		{
			if(tretman.getTermin().isAfter(LocalDateTime.now().minusYears(1)))
			{
				double prihod = 0;
				if(tretman.getStanje() == StanjeKozmetickogTretmana.IZVRSEN || tretman.getStanje() == StanjeKozmetickogTretmana.NIJE_SE_POJAVIO)
				{
					prihod += tretman.getCena();
				}
				else if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
				{
					prihod += tretman.getCena() / 10;
				}
				Double [] vrednosti = prihodi.get(tretman.getUsluga().getTip().getNaziv());
				int index = tretman.getTermin().getMonthValue() - LocalDate.now().getMonthValue();
				if(tretman.getTermin().getMonthValue() - LocalDate.now().getMonthValue() < 0)
				{
					index = tretman.getTermin().getMonthValue() - LocalDate.now().getMonthValue() + 12;
				}
				vrednosti[index % 12] += prihod;
				Double [] vrednosti2 = prihodi.get("Ukupno");
				vrednosti2[index % 12] += prihod;
			}
		}
		
		return prihodi;
	}
	
	public String pronadjiKlijentaTretman(KozmetickiTretman tretman)
	{
		
		for(Klijent klijent : this.klijentMng.getKlijenti())
		{
			if(klijent.getTretmani().contains(tretman))
			{
				return klijent.getIme() + " " + klijent.getPrezime();
			}
		}
		
		return "Ne postoji!";
	}
	
	public String pronadjiKozmeticaraTretman(KozmetickiTretman tretman)
	{
		for(Kozmeticar kozmeticar : this.kozmeticarMng.getKozmeticari())
		{
			if(kozmeticar.getTretmani().contains(tretman))
			{
				return kozmeticar.getIme() + " " + kozmeticar.getPrezime();
			}
		}
		
		return "Ne postoji!";
	}
}
