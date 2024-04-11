package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import manage.CenovnikManager;
import manage.ManagerFactory;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CenovnikManagerTest {
	
	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	
	public static CenovnikManager cenovnikMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		cenovnikMng = new CenovnikManager("./data/cenovnikTest.csv", managers.getUslugaMng());
		cenovnikMng.postaviCenovnik();
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/cenovnikTest.csv");
		fajl.delete();
	}

	@Test
	public void testAdd() 
	{
		cenovnikMng.dodajNaCenovnik(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"), 1500);
		cenovnikMng.dodajNaCenovnik(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"), 1000);
		cenovnikMng.dodajNaCenovnik(managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"), 2500);
		
		double cena1 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		double cena2 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"));
		double cena3 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"));
		
		assertTrue(cena1 != -1);
		assertTrue(cena1 == 1500);
		assertTrue(cena2 != -1);
		assertTrue(cena2 == 1000);
		assertTrue(cena3 != -1);
		assertTrue(cena3 == 2500);
	}
	
	@Test
	public void testEdit()
	{
		cenovnikMng.izmeniSaCenovnika(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"), 1800);
		cenovnikMng.izmeniSaCenovnika(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"), 1300);
		
		double cena1 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		double cena2 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"));
		
		assertTrue(cena1 != -1);
		assertTrue(cena1 == 1800);
		assertTrue(cena2 != -1);
		assertTrue(cena2 == 1300);
	}
	
	@Test
	public void testRemove()
	{
		cenovnikMng.skiniSaCenovnika(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		cenovnikMng.skiniSaCenovnika(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"));
		cenovnikMng.skiniSaCenovnika(managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"));
		
		double cena1 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		double cena2 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"));
		double cena3 = cenovnikMng.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"));
		
		assertTrue(cena1 == -1);
		assertTrue(cena2 == -1);
		assertTrue(cena3 == -1);
	}
	
	@Test
	public void testFile()
	{
		cenovnikMng.dodajNaCenovnik(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"), 1500);
		cenovnikMng.dodajNaCenovnik(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"), 1000);
		cenovnikMng.dodajNaCenovnik(managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"), 2500);
		
		CenovnikManager pomocni = new CenovnikManager("./data/cenovnikTest.csv", managers.getUslugaMng());
		pomocni.postaviCenovnik();
		pomocni.loadData();
		
		double cena1 = pomocni.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		double cena2 = pomocni.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"));
		double cena3 = pomocni.pronadjiCenu(managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"));
		
		assertTrue(cena1 != -1);
		assertTrue(cena1 == 1500);
		assertTrue(cena2 != -1);
		assertTrue(cena2 == 1000);
		assertTrue(cena3 != -1);
		assertTrue(cena3 == 2500);
	}

}
