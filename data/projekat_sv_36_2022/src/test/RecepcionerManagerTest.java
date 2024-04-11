package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.Korisnik;
import entity.Recepcioner;
import entity.Sprema;
import manage.ManagerFactory;
import manage.RecepcionerManager;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecepcionerManagerTest {

	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	
	public static RecepcionerManager recMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		recMng = new RecepcionerManager("./data/recepcioneriTest.csv");
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/recepcioneriTest.csv");
		fajl.delete();
	}
	
	@Test
	public void testLogin() {
		Korisnik user = managers.login("rec1", "recjedan");
		assertTrue(user != null && user instanceof Recepcioner);
		
		user = managers.login("neok", "nesto");
		assertTrue(user == null);
		
		user = managers.login("kli1","klijedan");
		assertTrue(user != null && !(user instanceof Recepcioner));
	}
	
	@Test
	public void testAdd()
	{
		managers.getRecepcionerMng().add(Sprema.VISOKA, 10, 0, 50000,
				"Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan");
		managers.getRecepcionerMng().add(Sprema.IZNAD_PROSEKA, 11, 10000, 100000,
				"Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123");
		managers.getRecepcionerMng().add(Sprema.NISKA, 15, 0, 60000,
				"Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01");
		
		Recepcioner r = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("veki");
		Recepcioner r2 = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("taca");
		Recepcioner r3 = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(r != null);
		assertTrue(r.getIme().equals("Veselin"));
		assertTrue(r.getPrezime().equals("Roganovic"));
		assertTrue(r.getPol().equals("Muski"));
		assertTrue(r.getTelefon().equals("+381655730724"));
		assertTrue(r.getAdresa().equals("NS"));
		assertTrue(r.getKorisnickoIme().equals("veki"));
		assertTrue(r.getLozinka().equals("rogan"));
		assertTrue(r.getSprema() == Sprema.VISOKA);
		assertTrue(r.getStaz() == 10);
		assertTrue(r.getBonus() == 0);
		assertTrue(r.getPlata() == 50000);

		assertTrue(r2 != null);
		assertTrue(r2.getIme().equals("Tamara"));
		assertTrue(r2.getPrezime().equals("Cvjetkovic"));
		assertTrue(r2.getPol().equals("Zenski"));
		assertTrue(r2.getTelefon().equals("+381651234123"));
		assertTrue(r2.getAdresa().equals("GR"));
		assertTrue(r2.getKorisnickoIme().equals("taca"));
		assertTrue(r2.getLozinka().equals("taca123"));
		assertTrue(r2.getSprema() == Sprema.IZNAD_PROSEKA);
		assertTrue(r2.getStaz() == 11);
		assertTrue(r2.getBonus() == 10000);
		assertTrue(r2.getPlata() == 100000);
		
		assertTrue(r3 != null);
		assertTrue(r3.getIme().equals("Marko"));
		assertTrue(r3.getPrezime().equals("Shevo"));
		assertTrue(r3.getPol().equals("Muski"));
		assertTrue(r3.getTelefon().equals("+3819429249"));
		assertTrue(r3.getAdresa().equals("NS"));
		assertTrue(r3.getKorisnickoIme().equals("msh"));
		assertTrue(r3.getLozinka().equals("msh01"));
		assertTrue(r3.getSprema() == Sprema.NISKA);
		assertTrue(r3.getStaz() == 15);
		assertTrue(r3.getBonus() == 0);
		assertTrue(r3.getPlata() == 60000);
	}
	
	@Test
	public void testEdit()
	{
		Recepcioner r = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("veki");
		Recepcioner r2 = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("taca");
		
		managers.getRecepcionerMng().edit(r.getId(), Sprema.ISPOD_PROSEKA, 5, 5000, 43000,
				"Neko", "Nesto", "Zenski", "+38124005293", "Negde", "korisnik", "korisnik5");
		managers.getRecepcionerMng().edit(r2.getId(), Sprema.VISOKA, 6, 15000, 70000,
				"Klijent", "Pet", "Muski", "+38113983243", "Grad", "klijent5", "kli123");
		
		assertTrue(r != null);
		assertTrue(r.getIme().equals("Neko"));
		assertTrue(r.getPrezime().equals("Nesto"));
		assertTrue(r.getPol().equals("Zenski"));
		assertTrue(r.getTelefon().equals("+38124005293"));
		assertTrue(r.getAdresa().equals("Negde"));
		assertTrue(r.getKorisnickoIme().equals("korisnik"));
		assertTrue(r.getLozinka().equals("korisnik5"));
		assertTrue(r.getSprema() == Sprema.ISPOD_PROSEKA);
		assertTrue(r.getStaz() == 5);
		assertTrue(r.getBonus() == 5000);
		assertTrue(r.getPlata() == 43000);

		assertTrue(r2 != null);
		assertTrue(r2.getIme().equals("Klijent"));
		assertTrue(r2.getPrezime().equals("Pet"));
		assertTrue(r2.getPol().equals("Muski"));
		assertTrue(r2.getTelefon().equals("+38113983243"));
		assertTrue(r2.getAdresa().equals("Grad"));
		assertTrue(r2.getKorisnickoIme().equals("klijent5"));
		assertTrue(r2.getLozinka().equals("kli123"));
		assertTrue(r2.getSprema() == Sprema.VISOKA);
		assertTrue(r2.getStaz() == 6);
		assertTrue(r2.getBonus() == 15000);
		assertTrue(r2.getPlata() == 70000);
	}
	
	@Test
	public void testRemove()
	{
		Recepcioner r = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("korisnik");
		Recepcioner r2 = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("klijent5");
		Recepcioner r3 = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("msh");
		
		managers.getRecepcionerMng().remove(r.getId());
		managers.getRecepcionerMng().remove(r2.getId());
		managers.getRecepcionerMng().remove(r3.getId());
		
		r = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("korisnik");
		r2 = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("klijent5");
		r3 = managers.getRecepcionerMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(r == null);
		assertTrue(r2 == null);
		assertTrue(r3 == null);
	}
	
	@Test
	public void testFile()
	{
		recMng.add(Sprema.VISOKA, 10, 0, 50000,
				"Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan");
		recMng.add(Sprema.IZNAD_PROSEKA, 11, 10000, 100000,
				"Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123");
		recMng.add(Sprema.NISKA, 15, 0, 60000,
				"Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01");
		
		RecepcionerManager pomocni = new RecepcionerManager("./data/recepcioneriTest.csv");
		pomocni.loadData();
		
		Recepcioner r = pomocni.pronadjiPoKorisnickomImenu("veki");
		Recepcioner r2 = pomocni.pronadjiPoKorisnickomImenu("taca");
		Recepcioner r3 = pomocni.pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(r != null);
		assertTrue(r.getIme().equals("Veselin"));
		assertTrue(r.getPrezime().equals("Roganovic"));
		assertTrue(r.getPol().equals("Muski"));
		assertTrue(r.getTelefon().equals("+381655730724"));
		assertTrue(r.getAdresa().equals("NS"));
		assertTrue(r.getKorisnickoIme().equals("veki"));
		assertTrue(r.getLozinka().equals("rogan"));
		assertTrue(r.getSprema() == Sprema.VISOKA);
		assertTrue(r.getStaz() == 10);
		assertTrue(r.getBonus() == 0);
		assertTrue(r.getPlata() == 50000);

		assertTrue(r2 != null);
		assertTrue(r2.getIme().equals("Tamara"));
		assertTrue(r2.getPrezime().equals("Cvjetkovic"));
		assertTrue(r2.getPol().equals("Zenski"));
		assertTrue(r2.getTelefon().equals("+381651234123"));
		assertTrue(r2.getAdresa().equals("GR"));
		assertTrue(r2.getKorisnickoIme().equals("taca"));
		assertTrue(r2.getLozinka().equals("taca123"));
		assertTrue(r2.getSprema() == Sprema.IZNAD_PROSEKA);
		assertTrue(r2.getStaz() == 11);
		assertTrue(r2.getBonus() == 10000);
		assertTrue(r2.getPlata() == 100000);
		
		assertTrue(r3 != null);
		assertTrue(r3.getIme().equals("Marko"));
		assertTrue(r3.getPrezime().equals("Shevo"));
		assertTrue(r3.getPol().equals("Muski"));
		assertTrue(r3.getTelefon().equals("+3819429249"));
		assertTrue(r3.getAdresa().equals("NS"));
		assertTrue(r3.getKorisnickoIme().equals("msh"));
		assertTrue(r3.getLozinka().equals("msh01"));
		assertTrue(r3.getSprema() == Sprema.NISKA);
		assertTrue(r3.getStaz() == 15);
		assertTrue(r3.getBonus() == 0);
		assertTrue(r3.getPlata() == 60000);
	}

}
