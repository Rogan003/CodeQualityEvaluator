package test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.Klijent;
import entity.Korisnik;
import entity.KozmetickiTretman;
import manage.KlijentManager;
import manage.ManagerFactory;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KlijentManagerTest {
	
	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	
	public static KlijentManager kliMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		kliMng = new KlijentManager("./data/klijentiTest.csv", managers.getTretmanMng());
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/klijentiTest.csv");
		fajl.delete();
	}
	
	@Test
	public void testLogin() {
		Korisnik user = managers.login("kli1", "klijedan");
		assertTrue(user != null && user instanceof Klijent);
		
		user = managers.login("neok", "nesto");
		assertTrue(user == null);
		
		user = managers.login("rec1","recjedan");
		assertTrue(user != null && !(user instanceof Klijent));
	}
	
	@Test
	public void testAdd()
	{
		managers.getKlijentMng().add("Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan");
		managers.getKlijentMng().add("Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123");
		managers.getKlijentMng().add("Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01");
		
		Klijent k = managers.getKlijentMng().pronadjiPoKorisnickomImenu("veki");
		Klijent k2 = managers.getKlijentMng().pronadjiPoKorisnickomImenu("taca");
		Klijent k3 = managers.getKlijentMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(k != null);
		assertTrue(k.getIme().equals("Veselin"));
		assertTrue(k.getPrezime().equals("Roganovic"));
		assertTrue(k.getPol().equals("Muski"));
		assertTrue(k.getTelefon().equals("+381655730724"));
		assertTrue(k.getAdresa().equals("NS"));
		assertTrue(k.getKorisnickoIme().equals("veki"));
		assertTrue(k.getLozinka().equals("rogan"));

		assertTrue(k2 != null);
		assertTrue(k2.getIme().equals("Tamara"));
		assertTrue(k2.getPrezime().equals("Cvjetkovic"));
		assertTrue(k2.getPol().equals("Zenski"));
		assertTrue(k2.getTelefon().equals("+381651234123"));
		assertTrue(k2.getAdresa().equals("GR"));
		assertTrue(k2.getKorisnickoIme().equals("taca"));
		assertTrue(k2.getLozinka().equals("taca123"));
		
		assertTrue(k3 != null);
		assertTrue(k3.getIme().equals("Marko"));
		assertTrue(k3.getPrezime().equals("Shevo"));
		assertTrue(k3.getPol().equals("Muski"));
		assertTrue(k3.getTelefon().equals("+3819429249"));
		assertTrue(k3.getAdresa().equals("NS"));
		assertTrue(k3.getKorisnickoIme().equals("msh"));
		assertTrue(k3.getLozinka().equals("msh01"));
	}
	
	@Test
	public void testEdit()
	{
		Klijent k = managers.getKlijentMng().pronadjiPoKorisnickomImenu("veki");
		Klijent k2 = managers.getKlijentMng().pronadjiPoKorisnickomImenu("taca");
		
		managers.getKlijentMng().edit(k.getId(), 100.0, true, k.getTretmani(), "Neko", "Nesto", "Zenski", 
				"+38124005293", "Negde", "korisnik", "korisnik5");
		managers.getKlijentMng().edit(k2.getId(), 1000.0, false, k2.getTretmani(), "Klijent", "Pet", "Muski",
				"+38113983243", "Grad", "klijent5", "kli123");
		
		assertTrue(k != null);
		assertTrue(k.getIme().equals("Neko"));
		assertTrue(k.getPrezime().equals("Nesto"));
		assertTrue(k.getPol().equals("Zenski"));
		assertTrue(k.getTelefon().equals("+38124005293"));
		assertTrue(k.getAdresa().equals("Negde"));
		assertTrue(k.getKorisnickoIme().equals("korisnik"));
		assertTrue(k.getLozinka().equals("korisnik5"));
		assertTrue(k.getPotrosio() == 100.0);
		assertTrue(k.hasKarticaLojalnosti());

		assertTrue(k2 != null);
		assertTrue(k2.getIme().equals("Klijent"));
		assertTrue(k2.getPrezime().equals("Pet"));
		assertTrue(k2.getPol().equals("Muski"));
		assertTrue(k2.getTelefon().equals("+38113983243"));
		assertTrue(k2.getAdresa().equals("Grad"));
		assertTrue(k2.getKorisnickoIme().equals("klijent5"));
		assertTrue(k2.getLozinka().equals("kli123"));
		assertTrue(k2.getPotrosio() == 1000.0);
		assertTrue(!k2.hasKarticaLojalnosti());
	}
	
	@Test
	public void testRemove()
	{
		Klijent k = managers.getKlijentMng().pronadjiPoKorisnickomImenu("korisnik");
		Klijent k2 = managers.getKlijentMng().pronadjiPoKorisnickomImenu("klijent5");
		Klijent k3 = managers.getKlijentMng().pronadjiPoKorisnickomImenu("msh");
		
		managers.getKlijentMng().remove(k.getId());
		managers.getKlijentMng().remove(k2.getId());
		managers.getKlijentMng().remove(k3.getId());
		
		k = managers.getKlijentMng().pronadjiPoKorisnickomImenu("korisnik");
		k2 = managers.getKlijentMng().pronadjiPoKorisnickomImenu("klijent5");
		k3 = managers.getKlijentMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(k == null);
		assertTrue(k2 == null);
		assertTrue(k3 == null);
	}
	
	@Test
	public void testFile()
	{	
		kliMng.add("Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan");
		kliMng.add("Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123");
		kliMng.add("Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01");
		
		Klijent k = kliMng.pronadjiPoKorisnickomImenu("veki");
		Klijent k2 = kliMng.pronadjiPoKorisnickomImenu("taca");
		
		kliMng.edit(k.getId(), 100.0, true, new ArrayList<KozmetickiTretman>(Arrays.asList(managers.getTretmanMng().getTretmani().get(0))),
				"Neko", "Nesto", "Zenski", "+38124005293", "Negde", "korisnik", "korisnik5");
		kliMng.edit(k2.getId(), 1000.0, false, new ArrayList<KozmetickiTretman>(Arrays.asList(managers.getTretmanMng().getTretmani().get(1))),
				"Klijent", "Pet", "Muski", "+38113983243", "Grad", "klijent5", "kli123");
		
		KlijentManager pomocni = new KlijentManager("./data/klijentiTest.csv", managers.getTretmanMng());
		pomocni.loadData();
		
		k = pomocni.pronadjiPoKorisnickomImenu("korisnik");
		k2 = pomocni.pronadjiPoKorisnickomImenu("klijent5");
		Klijent k3 = pomocni.pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(k != null);
		assertTrue(k.getIme().equals("Neko"));
		assertTrue(k.getPrezime().equals("Nesto"));
		assertTrue(k.getPol().equals("Zenski"));
		assertTrue(k.getTelefon().equals("+38124005293"));
		assertTrue(k.getAdresa().equals("Negde"));
		assertTrue(k.getKorisnickoIme().equals("korisnik"));
		assertTrue(k.getLozinka().equals("korisnik5"));
		assertTrue(k.getPotrosio() == 100.0);
		assertTrue(k.hasKarticaLojalnosti());
		assertTrue(k.getTretmani().size() == 1);
		assertTrue(k.getTretmani().get(0) == managers.getTretmanMng().getTretmani().get(0));

		assertTrue(k2 != null);
		assertTrue(k2.getIme().equals("Klijent"));
		assertTrue(k2.getPrezime().equals("Pet"));
		assertTrue(k2.getPol().equals("Muski"));
		assertTrue(k2.getTelefon().equals("+38113983243"));
		assertTrue(k2.getAdresa().equals("Grad"));
		assertTrue(k2.getKorisnickoIme().equals("klijent5"));
		assertTrue(k2.getLozinka().equals("kli123"));
		assertTrue(k2.getPotrosio() == 1000.0);
		assertTrue(!k2.hasKarticaLojalnosti());
		assertTrue(k2.getTretmani().size() == 1);
		assertTrue(k2.getTretmani().get(0) == managers.getTretmanMng().getTretmani().get(1));
		
		assertTrue(k3 != null);
		assertTrue(k3.getIme().equals("Marko"));
		assertTrue(k3.getPrezime().equals("Shevo"));
		assertTrue(k3.getPol().equals("Muski"));
		assertTrue(k3.getTelefon().equals("+3819429249"));
		assertTrue(k3.getAdresa().equals("NS"));
		assertTrue(k3.getKorisnickoIme().equals("msh"));
		assertTrue(k3.getLozinka().equals("msh01"));
		assertTrue(k3.getPotrosio() == 0);
		assertTrue(!k3.hasKarticaLojalnosti());
		assertTrue(k3.getTretmani().size() == 0);
	}
}
