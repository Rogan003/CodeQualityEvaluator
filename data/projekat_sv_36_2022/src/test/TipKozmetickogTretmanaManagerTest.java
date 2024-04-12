package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.TipKozmetickogTretmana;
import manage.ManagerFactory;
import manage.TipKozmetickogTretmanaManager;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TipKozmetickogTretmanaManagerTest {
	
	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	public static TipKozmetickogTretmanaManager tipMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		tipMng = new TipKozmetickogTretmanaManager("./data/tiptest.csv");
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/tiptest.csv");
		fajl.delete();
	}

	@Test
	public void testAdd() 
	{
		managers.getTipMng().add("tip1");
		managers.getTipMng().add("tip2");
		managers.getTipMng().add("tip3");
		
		TipKozmetickogTretmana t1 = managers.getTipMng().pronadjiPoNazivu("tip1");
		TipKozmetickogTretmana t2 = managers.getTipMng().pronadjiPoNazivu("tip2");
		TipKozmetickogTretmana t3 = managers.getTipMng().pronadjiPoNazivu("tip3");
		
		assertTrue(t1 != null);
		assertTrue(t1.getNaziv().equals("tip1"));
		
		assertTrue(t2 != null);
		assertTrue(t2.getNaziv().equals("tip2"));
		
		assertTrue(t3 != null);
		assertTrue(t3.getNaziv().equals("tip3"));
	}
	
	@Test
	public void testEdit()
	{
		TipKozmetickogTretmana t1 = managers.getTipMng().pronadjiPoNazivu("tip1");
		TipKozmetickogTretmana t2 = managers.getTipMng().pronadjiPoNazivu("tip2");
		
		managers.getTipMng().edit(t1.getId(), "tip4");
		managers.getTipMng().edit(t2.getId(), "tip5");
		
		assertTrue(t1 != null);
		assertTrue(t1.getNaziv().equals("tip4"));
		
		assertTrue(t2 != null);
		assertTrue(t2.getNaziv().equals("tip5"));
	}
	
	@Test
	public void testRemove()
	{
		TipKozmetickogTretmana t1 = managers.getTipMng().pronadjiPoNazivu("tip4");
		TipKozmetickogTretmana t2 = managers.getTipMng().pronadjiPoNazivu("tip5");
		TipKozmetickogTretmana t3 = managers.getTipMng().pronadjiPoNazivu("tip3");
		
		managers.getTipMng().remove(t1.getId());
		managers.getTipMng().remove(t2.getId());
		managers.getTipMng().remove(t3.getId());
		
		t1 = managers.getTipMng().pronadjiPoNazivu("tip4");
		t2 = managers.getTipMng().pronadjiPoNazivu("tip5");
		t3 = managers.getTipMng().pronadjiPoNazivu("tip3");
		
		assertTrue(t1 == null);
		assertTrue(t2 == null);
		assertTrue(t3 == null);
	}
	
	@Test
	public void testFile()
	{
		tipMng.add("tip1");
		tipMng.add("tip2");
		tipMng.add("tip3");
		
		TipKozmetickogTretmanaManager pomocni = new TipKozmetickogTretmanaManager("./data/tiptest.csv");
		pomocni.loadData();
		
		TipKozmetickogTretmana t1 = pomocni.pronadjiPoNazivu("tip1");
		TipKozmetickogTretmana t2 = pomocni.pronadjiPoNazivu("tip2");
		TipKozmetickogTretmana t3 = pomocni.pronadjiPoNazivu("tip3");
		
		assertTrue(t1 != null);
		assertTrue(t1.getNaziv().equals("tip1"));
		
		assertTrue(t2 != null);
		assertTrue(t2.getNaziv().equals("tip2"));
		
		assertTrue(t3 != null);
		assertTrue(t3.getNaziv().equals("tip3"));
	}

}
