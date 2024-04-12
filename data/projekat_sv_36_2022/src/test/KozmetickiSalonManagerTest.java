package test;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import manage.KozmetickiSalonManager;
import manage.ManagerFactory;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KozmetickiSalonManagerTest {
	
	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	
	public static KozmetickiSalonManager salonMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		salonMng = new KozmetickiSalonManager("./data/salontest.csv");
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		managers.getSalonMng().edit("Moj salon", LocalTime.parse("08:00:00"), LocalTime.parse("20:00:00"));
		File fajl = new File("./data/salontest.csv");
		fajl.delete();
	}

	@Test
	public void testAdd() 
	{
		managers.getSalonMng().postaviKozmetickiSalon(); // ovo izmeniti mozda
		
		assertTrue(managers.getSalonMng().getKozmetickiSalon().getNaziv().equals("Moj salon"));
		assertTrue(managers.getSalonMng().getKozmetickiSalon().getRadnoVreme()[0] == LocalTime.parse("08:00:00"));
		assertTrue(managers.getSalonMng().getKozmetickiSalon().getRadnoVreme()[1] == LocalTime.parse("20:00:00"));
	}
	
	@Test
	public void testEdit()
	{
		managers.getSalonMng().edit("Salon", LocalTime.parse("10:00:00"), LocalTime.parse("19:00:00"));
		
		assertTrue(managers.getSalonMng().getKozmetickiSalon().getNaziv().equals("Salon"));
		assertTrue(managers.getSalonMng().getKozmetickiSalon().getRadnoVreme()[0] == LocalTime.parse("10:00:00"));
		assertTrue(managers.getSalonMng().getKozmetickiSalon().getRadnoVreme()[1] == LocalTime.parse("19:00:00"));
	}

	@Test
	public void testFile()
	{
		salonMng.postaviKozmetickiSalon();
		
		KozmetickiSalonManager pomocni = new KozmetickiSalonManager("./data/salontest.csv");
		pomocni.loadData();
		
		assertTrue(pomocni.getKozmetickiSalon().getNaziv().equals("Moj salon"));
		assertTrue(pomocni.getKozmetickiSalon().getRadnoVreme()[0] == LocalTime.parse("08:00:00"));
		assertTrue(pomocni.getKozmetickiSalon().getRadnoVreme()[1] == LocalTime.parse("20:00:00"));
	}
}
