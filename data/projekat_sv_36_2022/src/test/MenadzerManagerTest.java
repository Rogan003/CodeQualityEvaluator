package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.Korisnik;
import entity.Menadzer;
import entity.Sprema;
import manage.ManagerFactory;
import manage.MenadzerManager;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MenadzerManagerTest {

	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	
	public static MenadzerManager menMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		menMng = new MenadzerManager("./data/menadzeriTest.csv");
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/menadzeriTest.csv");
		fajl.delete();
	}
	
	@Test
	public void testLogin() {
		Korisnik user = managers.login("men1", "menjedan");
		assertTrue(user != null && user instanceof Menadzer);
		
		user = managers.login("neok", "nesto");
		assertTrue(user == null);
		
		user = managers.login("kli1","klijedan");
		assertTrue(user != null && !(user instanceof Menadzer));
	}
	
	@Test
	public void testAdd()
	{
		managers.getMenadzerMng().add(Sprema.VISOKA, 10, 0, 50000,
				"Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan");
		managers.getMenadzerMng().add(Sprema.IZNAD_PROSEKA, 11, 10000, 100000,
				"Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123");
		managers.getMenadzerMng().add(Sprema.NISKA, 15, 0, 60000,
				"Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01");
		
		Menadzer m = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("veki");
		Menadzer m2 = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("taca");
		Menadzer m3 = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(m != null);
		assertTrue(m.getIme().equals("Veselin"));
		assertTrue(m.getPrezime().equals("Roganovic"));
		assertTrue(m.getPol().equals("Muski"));
		assertTrue(m.getTelefon().equals("+381655730724"));
		assertTrue(m.getAdresa().equals("NS"));
		assertTrue(m.getKorisnickoIme().equals("veki"));
		assertTrue(m.getLozinka().equals("rogan"));
		assertTrue(m.getSprema() == Sprema.VISOKA);
		assertTrue(m.getStaz() == 10);
		assertTrue(m.getBonus() == 0);
		assertTrue(m.getPlata() == 50000);

		assertTrue(m2 != null);
		assertTrue(m2.getIme().equals("Tamara"));
		assertTrue(m2.getPrezime().equals("Cvjetkovic"));
		assertTrue(m2.getPol().equals("Zenski"));
		assertTrue(m2.getTelefon().equals("+381651234123"));
		assertTrue(m2.getAdresa().equals("GR"));
		assertTrue(m2.getKorisnickoIme().equals("taca"));
		assertTrue(m2.getLozinka().equals("taca123"));
		assertTrue(m2.getSprema() == Sprema.IZNAD_PROSEKA);
		assertTrue(m2.getStaz() == 11);
		assertTrue(m2.getBonus() == 10000);
		assertTrue(m2.getPlata() == 100000);
		
		assertTrue(m3 != null);
		assertTrue(m3.getIme().equals("Marko"));
		assertTrue(m3.getPrezime().equals("Shevo"));
		assertTrue(m3.getPol().equals("Muski"));
		assertTrue(m3.getTelefon().equals("+3819429249"));
		assertTrue(m3.getAdresa().equals("NS"));
		assertTrue(m3.getKorisnickoIme().equals("msh"));
		assertTrue(m3.getLozinka().equals("msh01"));
		assertTrue(m3.getSprema() == Sprema.NISKA);
		assertTrue(m3.getStaz() == 15);
		assertTrue(m3.getBonus() == 0);
		assertTrue(m3.getPlata() == 60000);
	}
	
	@Test
	public void testEdit()
	{
		Menadzer m = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("veki");
		Menadzer m2 = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("taca");
		
		managers.getMenadzerMng().edit(m.getId(), Sprema.ISPOD_PROSEKA, 5, 5000, 43000,
				"Neko", "Nesto", "Zenski", "+38124005293", "Negde", "korisnik", "korisnik5");
		managers.getMenadzerMng().edit(m2.getId(), Sprema.VISOKA, 6, 15000, 70000,
				"Klijent", "Pet", "Muski", "+38113983243", "Grad", "klijent5", "kli123");
		
		assertTrue(m != null);
		assertTrue(m.getIme().equals("Neko"));
		assertTrue(m.getPrezime().equals("Nesto"));
		assertTrue(m.getPol().equals("Zenski"));
		assertTrue(m.getTelefon().equals("+38124005293"));
		assertTrue(m.getAdresa().equals("Negde"));
		assertTrue(m.getKorisnickoIme().equals("korisnik"));
		assertTrue(m.getLozinka().equals("korisnik5"));
		assertTrue(m.getSprema() == Sprema.ISPOD_PROSEKA);
		assertTrue(m.getStaz() == 5);
		assertTrue(m.getBonus() == 5000);
		assertTrue(m.getPlata() == 43000);

		assertTrue(m2 != null);
		assertTrue(m2.getIme().equals("Klijent"));
		assertTrue(m2.getPrezime().equals("Pet"));
		assertTrue(m2.getPol().equals("Muski"));
		assertTrue(m2.getTelefon().equals("+38113983243"));
		assertTrue(m2.getAdresa().equals("Grad"));
		assertTrue(m2.getKorisnickoIme().equals("klijent5"));
		assertTrue(m2.getLozinka().equals("kli123"));
		assertTrue(m2.getSprema() == Sprema.VISOKA);
		assertTrue(m2.getStaz() == 6);
		assertTrue(m2.getBonus() == 15000);
		assertTrue(m2.getPlata() == 70000);
	}
	
	@Test
	public void testRemove()
	{
		
		Menadzer m = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("korisnik");
		Menadzer m2 = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("klijent5");
		Menadzer m3 = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("msh");
		
		managers.getMenadzerMng().remove(m.getId());
		managers.getMenadzerMng().remove(m2.getId());
		managers.getMenadzerMng().remove(m3.getId());
		
		m = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("korisnik");
		m2 = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("klijent5");
		m3 = managers.getMenadzerMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(m == null);
		assertTrue(m2 == null);
		assertTrue(m3 == null);
	}
	
	@Test
	public void testFile()
	{
		menMng.add(Sprema.VISOKA, 10, 0, 50000,
				"Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan");
		menMng.add(Sprema.IZNAD_PROSEKA, 11, 10000, 100000,
				"Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123");
		menMng.add(Sprema.NISKA, 15, 0, 60000,
				"Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01");
		
		MenadzerManager pomocni = new MenadzerManager("./data/menadzeriTest.csv");
		pomocni.loadData();
		
		Menadzer m = pomocni.pronadjiPoKorisnickomImenu("veki");
		Menadzer m2 = pomocni.pronadjiPoKorisnickomImenu("taca");
		Menadzer m3 = pomocni.pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(m != null);
		assertTrue(m.getIme().equals("Veselin"));
		assertTrue(m.getPrezime().equals("Roganovic"));
		assertTrue(m.getPol().equals("Muski"));
		assertTrue(m.getTelefon().equals("+381655730724"));
		assertTrue(m.getAdresa().equals("NS"));
		assertTrue(m.getKorisnickoIme().equals("veki"));
		assertTrue(m.getLozinka().equals("rogan"));
		assertTrue(m.getSprema() == Sprema.VISOKA);
		assertTrue(m.getStaz() == 10);
		assertTrue(m.getBonus() == 0);
		assertTrue(m.getPlata() == 50000);

		assertTrue(m2 != null);
		assertTrue(m2.getIme().equals("Tamara"));
		assertTrue(m2.getPrezime().equals("Cvjetkovic"));
		assertTrue(m2.getPol().equals("Zenski"));
		assertTrue(m2.getTelefon().equals("+381651234123"));
		assertTrue(m2.getAdresa().equals("GR"));
		assertTrue(m2.getKorisnickoIme().equals("taca"));
		assertTrue(m2.getLozinka().equals("taca123"));
		assertTrue(m2.getSprema() == Sprema.IZNAD_PROSEKA);
		assertTrue(m2.getStaz() == 11);
		assertTrue(m2.getBonus() == 10000);
		assertTrue(m2.getPlata() == 100000);
		
		assertTrue(m3 != null);
		assertTrue(m3.getIme().equals("Marko"));
		assertTrue(m3.getPrezime().equals("Shevo"));
		assertTrue(m3.getPol().equals("Muski"));
		assertTrue(m3.getTelefon().equals("+3819429249"));
		assertTrue(m3.getAdresa().equals("NS"));
		assertTrue(m3.getKorisnickoIme().equals("msh"));
		assertTrue(m3.getLozinka().equals("msh01"));
		assertTrue(m3.getSprema() == Sprema.NISKA);
		assertTrue(m3.getStaz() == 15);
		assertTrue(m3.getBonus() == 0);
		assertTrue(m3.getPlata() == 60000);
	}

}
