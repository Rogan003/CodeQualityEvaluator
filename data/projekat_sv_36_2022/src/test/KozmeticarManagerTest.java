package test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.Korisnik;
import entity.Kozmeticar;
import entity.KozmetickiTretman;
import entity.Recepcioner;
import entity.Sprema;
import entity.TipKozmetickogTretmana;
import manage.KozmeticarManager;
import manage.ManagerFactory;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KozmeticarManagerTest {

	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	
	public static KozmeticarManager kozMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		kozMng = new KozmeticarManager("./data/kozmeticariTest.csv", managers.getTipMng(), managers.getTretmanMng());
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/kozmeticariTest.csv");
		fajl.delete();
	}
	
	@Test
	public void testLogin() {
		Korisnik user = managers.login("koz1", "kozjedan");
		assertTrue(user != null && user instanceof Kozmeticar);
		
		user = managers.login("neok", "nesto");
		assertTrue(user == null);
		
		user = managers.login("kli1","klijedan");
		assertTrue(user != null && !(user instanceof Recepcioner));
	}
	
	@Test
	public void testAdd()
	{
		managers.getKozmeticarMng().add(Sprema.VISOKA, 10, 0, 50000,
				"Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan",
				new ArrayList<TipKozmetickogTretmana>(Arrays.asList(managers.getTipMng().pronadjiPoNazivu("masaza"))));
		managers.getKozmeticarMng().add(Sprema.IZNAD_PROSEKA, 11, 10000, 100000,
				"Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123",
				new ArrayList<TipKozmetickogTretmana>(Arrays.asList(managers.getTipMng().pronadjiPoNazivu("manikir"),
						managers.getTipMng().pronadjiPoNazivu("pedikir"))));
		managers.getKozmeticarMng().add(Sprema.NISKA, 15, 0, 60000,
				"Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01", new ArrayList<TipKozmetickogTretmana>());
		
		Kozmeticar k = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("veki");
		Kozmeticar k2 = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("taca");
		Kozmeticar k3 = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(k != null);
		assertTrue(k.getIme().equals("Veselin"));
		assertTrue(k.getPrezime().equals("Roganovic"));
		assertTrue(k.getPol().equals("Muski"));
		assertTrue(k.getTelefon().equals("+381655730724"));
		assertTrue(k.getAdresa().equals("NS"));
		assertTrue(k.getKorisnickoIme().equals("veki"));
		assertTrue(k.getLozinka().equals("rogan"));
		assertTrue(k.getSprema() == Sprema.VISOKA);
		assertTrue(k.getStaz() == 10);
		assertTrue(k.getBonus() == 0);
		assertTrue(k.getPlata() == 50000);
		assertTrue(k.getObuceniTipovi().size() == 1);
		assertTrue(k.getObuceniTipovi().get(0).getNaziv().equals("masaza"));
		assertTrue(k.getTretmani().size() == 0);

		assertTrue(k2 != null);
		assertTrue(k2.getIme().equals("Tamara"));
		assertTrue(k2.getPrezime().equals("Cvjetkovic"));
		assertTrue(k2.getPol().equals("Zenski"));
		assertTrue(k2.getTelefon().equals("+381651234123"));
		assertTrue(k2.getAdresa().equals("GR"));
		assertTrue(k2.getKorisnickoIme().equals("taca"));
		assertTrue(k2.getLozinka().equals("taca123"));
		assertTrue(k2.getSprema() == Sprema.IZNAD_PROSEKA);
		assertTrue(k2.getStaz() == 11);
		assertTrue(k2.getBonus() == 10000);
		assertTrue(k2.getPlata() == 100000);
		assertTrue(k2.getObuceniTipovi().size() == 2);
		assertTrue(k2.getObuceniTipovi().get(0).getNaziv().equals("manikir"));
		assertTrue(k2.getObuceniTipovi().get(1).getNaziv().equals("pedikir"));
		assertTrue(k2.getTretmani().size() == 0);
		
		assertTrue(k3 != null);
		assertTrue(k3.getIme().equals("Marko"));
		assertTrue(k3.getPrezime().equals("Shevo"));
		assertTrue(k3.getPol().equals("Muski"));
		assertTrue(k3.getTelefon().equals("+3819429249"));
		assertTrue(k3.getAdresa().equals("NS"));
		assertTrue(k3.getKorisnickoIme().equals("msh"));
		assertTrue(k3.getLozinka().equals("msh01"));
		assertTrue(k3.getSprema() == Sprema.NISKA);
		assertTrue(k3.getStaz() == 15);
		assertTrue(k3.getBonus() == 0);
		assertTrue(k3.getPlata() == 60000);
		assertTrue(k3.getObuceniTipovi().size() == 0);
		assertTrue(k3.getTretmani().size() == 0);
	}
	
	@Test
	public void testEdit()
	{
		Kozmeticar k = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("veki");
		Kozmeticar k2 = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("taca");
		
		managers.getKozmeticarMng().edit(k.getId(), new ArrayList<TipKozmetickogTretmana>(Arrays.asList(managers.getTipMng().pronadjiPoNazivu("manikir"),
				managers.getTipMng().pronadjiPoNazivu("masaza"))), k.getTretmani(), Sprema.ISPOD_PROSEKA, 5, 5000, 43000,
				"Neko", "Nesto", "Zenski", "+38124005293", "Negde", "korisnik", "korisnik5");
		managers.getKozmeticarMng().edit(k2.getId(), new ArrayList<TipKozmetickogTretmana>(Arrays.asList(managers.getTipMng().pronadjiPoNazivu("pedikir"))),
				k2.getTretmani(), Sprema.VISOKA, 6, 15000, 70000,
				"Klijent", "Pet", "Muski", "+38113983243", "Grad", "klijent5", "kli123");
		
		assertTrue(k != null);
		assertTrue(k.getIme().equals("Neko"));
		assertTrue(k.getPrezime().equals("Nesto"));
		assertTrue(k.getPol().equals("Zenski"));
		assertTrue(k.getTelefon().equals("+38124005293"));
		assertTrue(k.getAdresa().equals("Negde"));
		assertTrue(k.getKorisnickoIme().equals("korisnik"));
		assertTrue(k.getLozinka().equals("korisnik5"));
		assertTrue(k.getSprema() == Sprema.ISPOD_PROSEKA);
		assertTrue(k.getStaz() == 5);
		assertTrue(k.getBonus() == 5000);
		assertTrue(k.getPlata() == 43000);
		assertTrue(k.getObuceniTipovi().size() == 2);
		assertTrue(k.getObuceniTipovi().get(0).getNaziv().equals("manikir"));
		assertTrue(k.getObuceniTipovi().get(1).getNaziv().equals("masaza"));
		assertTrue(k.getTretmani().size() == 0);

		assertTrue(k2 != null);
		assertTrue(k2.getIme().equals("Klijent"));
		assertTrue(k2.getPrezime().equals("Pet"));
		assertTrue(k2.getPol().equals("Muski"));
		assertTrue(k2.getTelefon().equals("+38113983243"));
		assertTrue(k2.getAdresa().equals("Grad"));
		assertTrue(k2.getKorisnickoIme().equals("klijent5"));
		assertTrue(k2.getLozinka().equals("kli123"));
		assertTrue(k2.getSprema() == Sprema.VISOKA);
		assertTrue(k2.getStaz() == 6);
		assertTrue(k2.getBonus() == 15000);
		assertTrue(k2.getPlata() == 70000);
		assertTrue(k2.getObuceniTipovi().size() == 1);
		assertTrue(k2.getObuceniTipovi().get(0).getNaziv().equals("pedikir"));
		assertTrue(k2.getTretmani().size() == 0);
	}
	
	@Test
	public void testRemove()
	{
		Kozmeticar k = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("korisnik");
		Kozmeticar k2 = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("klijent5");
		Kozmeticar k3 = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("msh");
		
		managers.getKozmeticarMng().remove(k.getId());
		managers.getKozmeticarMng().remove(k2.getId());
		managers.getKozmeticarMng().remove(k3.getId());
		
		k = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("korisnik");
		k2 = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("klijent5");
		k3 = managers.getKozmeticarMng().pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(k == null);
		assertTrue(k2 == null);
		assertTrue(k3 == null);
	}
	
	@Test
	public void testFile()
	{
		kozMng.add(Sprema.VISOKA, 10, 0, 50000,
				"Veselin", "Roganovic", "Muski", "+381655730724", "NS", "veki", "rogan",
				new ArrayList<TipKozmetickogTretmana>(Arrays.asList(managers.getTipMng().pronadjiPoNazivu("masaza"))));
		kozMng.add(Sprema.IZNAD_PROSEKA, 11, 10000, 100000,
				"Tamara", "Cvjetkovic", "Zenski", "+381651234123", "GR", "taca", "taca123",
				new ArrayList<TipKozmetickogTretmana>(Arrays.asList(managers.getTipMng().pronadjiPoNazivu("manikir"),
						managers.getTipMng().pronadjiPoNazivu("pedikir"))));
		kozMng.add(Sprema.NISKA, 15, 0, 60000,
				"Marko", "Shevo", "Muski", "+3819429249", "NS", "msh", "msh01", new ArrayList<TipKozmetickogTretmana>());
		
		Kozmeticar k = kozMng.pronadjiPoKorisnickomImenu("veki");
		Kozmeticar k2 = kozMng.pronadjiPoKorisnickomImenu("taca");
		
		kozMng.edit(k.getId(), k.getObuceniTipovi(),
				new ArrayList<KozmetickiTretman>(Arrays.asList(managers.getTretmanMng().getTretmani().get(0))),
				k.getSprema(), k.getStaz(), k.getBonus(), k.getPlata(), k.getIme(), k.getPrezime(), k.getPol(), k.getTelefon(), k.getAdresa(),
				k.getKorisnickoIme(), k.getLozinka());
		
		kozMng.edit(k2.getId(), k2.getObuceniTipovi(),
				new ArrayList<KozmetickiTretman>(Arrays.asList(managers.getTretmanMng().getTretmani().get(1))),
				k2.getSprema(), k2.getStaz(), k2.getBonus(), k2.getPlata(), k2.getIme(), k2.getPrezime(), k2.getPol(), k2.getTelefon(),
				k2.getAdresa(), k2.getKorisnickoIme(), k2.getLozinka());
		
		KozmeticarManager pomocni = new KozmeticarManager("./data/kozmeticariTest.csv", managers.getTipMng(), managers.getTretmanMng());
		pomocni.loadData();
		
		k = pomocni.pronadjiPoKorisnickomImenu("veki");
		k2 = pomocni.pronadjiPoKorisnickomImenu("taca");
		Kozmeticar k3 = pomocni.pronadjiPoKorisnickomImenu("msh");
		
		assertTrue(k != null);
		assertTrue(k.getIme().equals("Veselin"));
		assertTrue(k.getPrezime().equals("Roganovic"));
		assertTrue(k.getPol().equals("Muski"));
		assertTrue(k.getTelefon().equals("+381655730724"));
		assertTrue(k.getAdresa().equals("NS"));
		assertTrue(k.getKorisnickoIme().equals("veki"));
		assertTrue(k.getLozinka().equals("rogan"));
		assertTrue(k.getSprema() == Sprema.VISOKA);
		assertTrue(k.getStaz() == 10);
		assertTrue(k.getBonus() == 0);
		assertTrue(k.getPlata() == 50000);
		assertTrue(k.getObuceniTipovi().size() == 1);
		assertTrue(k.getObuceniTipovi().get(0).getNaziv().equals("masaza"));
		assertTrue(k.getTretmani().size() == 1);
		assertTrue(k.getTretmani().get(0) == managers.getTretmanMng().getTretmani().get(0));

		assertTrue(k2 != null);
		assertTrue(k2.getIme().equals("Tamara"));
		assertTrue(k2.getPrezime().equals("Cvjetkovic"));
		assertTrue(k2.getPol().equals("Zenski"));
		assertTrue(k2.getTelefon().equals("+381651234123"));
		assertTrue(k2.getAdresa().equals("GR"));
		assertTrue(k2.getKorisnickoIme().equals("taca"));
		assertTrue(k2.getLozinka().equals("taca123"));
		assertTrue(k2.getSprema() == Sprema.IZNAD_PROSEKA);
		assertTrue(k2.getStaz() == 11);
		assertTrue(k2.getBonus() == 10000);
		assertTrue(k2.getPlata() == 100000);
		assertTrue(k2.getObuceniTipovi().size() == 2);
		assertTrue(k2.getObuceniTipovi().get(0).getNaziv().equals("manikir"));
		assertTrue(k2.getObuceniTipovi().get(1).getNaziv().equals("pedikir"));
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
		assertTrue(k3.getSprema() == Sprema.NISKA);
		assertTrue(k3.getStaz() == 15);
		assertTrue(k3.getBonus() == 0);
		assertTrue(k3.getPlata() == 60000);
		assertTrue(k3.getObuceniTipovi().size() == 0);
		assertTrue(k3.getTretmani().size() == 0);
	}

}
