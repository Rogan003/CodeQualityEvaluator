package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.KozmetickaUsluga;
import manage.KozmetickaUslugaManager;
import manage.ManagerFactory;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KozmetickaUslugaManagerTest {

	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	public static KozmetickaUslugaManager uslugaMng;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		uslugaMng = new KozmetickaUslugaManager("./data/uslugaTest.csv", managers.getTipMng());
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/uslugaTest.csv");
		fajl.delete();
	}

	@Test
	public void testAdd() 
	{
		managers.getUslugaMng().add(managers.getTipMng().pronadjiPoNazivu("masaza"), 30, "spm");
		managers.getUslugaMng().add(managers.getTipMng().pronadjiPoNazivu("pedikir"), 45, "pedikir usluga");
		managers.getUslugaMng().add(managers.getTipMng().pronadjiPoNazivu("manikir"), 60, "manikir usluga");
		
		KozmetickaUsluga u1 = managers.getUslugaMng().pronadjiPoNazivu("spm");
		KozmetickaUsluga u2 = managers.getUslugaMng().pronadjiPoNazivu("pedikir usluga");
		KozmetickaUsluga u3 = managers.getUslugaMng().pronadjiPoNazivu("manikir usluga");
		
		assertTrue(u1 != null);
		assertTrue(u1.getPodtip().equals("spm"));
		assertTrue(u1.getTrajanje() == 30);
		assertTrue(u1.getTip() == managers.getTipMng().pronadjiPoNazivu("masaza"));
		
		assertTrue(u2 != null);
		assertTrue(u2.getPodtip().equals("pedikir usluga"));
		assertTrue(u2.getTrajanje() == 45);
		assertTrue(u2.getTip() == managers.getTipMng().pronadjiPoNazivu("pedikir"));
		
		assertTrue(u3 != null);
		assertTrue(u3.getPodtip().equals("manikir usluga"));
		assertTrue(u3.getTrajanje() == 60);
		assertTrue(u3.getTip() == managers.getTipMng().pronadjiPoNazivu("manikir"));
	}
	
	@Test
	public void testEdit()
	{
		KozmetickaUsluga u1 = managers.getUslugaMng().pronadjiPoNazivu("spm");
		KozmetickaUsluga u2 = managers.getUslugaMng().pronadjiPoNazivu("pedikir usluga");
		
		managers.getUslugaMng().edit(u1.getId(), managers.getTipMng().pronadjiPoNazivu("pedikir"), 75, "pdk 2");
		managers.getUslugaMng().edit(u2.getId(), managers.getTipMng().pronadjiPoNazivu("masaza"), 90, "msz 2");
		
		assertTrue(u1 != null);
		assertTrue(u1.getPodtip().equals("pdk 2"));
		assertTrue(u1.getTrajanje() == 75);
		assertTrue(u1.getTip() == managers.getTipMng().pronadjiPoNazivu("pedikir"));
		
		assertTrue(u2 != null);
		assertTrue(u2.getPodtip().equals("msz 2"));
		assertTrue(u2.getTrajanje() == 90);
		assertTrue(u2.getTip() == managers.getTipMng().pronadjiPoNazivu("masaza"));
	}
	
	@Test
	public void testRemove()
	{
		KozmetickaUsluga u1 = managers.getUslugaMng().pronadjiPoNazivu("pdk 2");
		KozmetickaUsluga u2 = managers.getUslugaMng().pronadjiPoNazivu("msz 2");
		KozmetickaUsluga u3 = managers.getUslugaMng().pronadjiPoNazivu("manikir usluga");
		
		managers.getUslugaMng().remove(u1.getId());
		managers.getUslugaMng().remove(u2.getId());
		managers.getUslugaMng().remove(u3.getId());
		
		u1 = managers.getUslugaMng().pronadjiPoNazivu("pdk 2");
		u2 = managers.getUslugaMng().pronadjiPoNazivu("msz 2");
		u3 = managers.getUslugaMng().pronadjiPoNazivu("manikir usluga");
		
		assertTrue(u1 == null);
		assertTrue(u2 == null);
		assertTrue(u3 == null);
	}
	
	@Test
	public void testFile()
	{
		uslugaMng.add(managers.getTipMng().pronadjiPoNazivu("masaza"), 30, "spm");
		uslugaMng.add(managers.getTipMng().pronadjiPoNazivu("pedikir"), 45, "pedikir usluga");
		uslugaMng.add(managers.getTipMng().pronadjiPoNazivu("manikir"), 60, "manikir usluga");
		
		KozmetickaUslugaManager pomocni = new KozmetickaUslugaManager("./data/uslugaTest.csv", managers.getTipMng());
		pomocni.loadData();
		
		KozmetickaUsluga u1 = pomocni.pronadjiPoNazivu("spm");
		KozmetickaUsluga u2 = pomocni.pronadjiPoNazivu("pedikir usluga");
		KozmetickaUsluga u3 = pomocni.pronadjiPoNazivu("manikir usluga");
		
		assertTrue(u1 != null);
		assertTrue(u1.getPodtip().equals("spm"));
		assertTrue(u1.getTrajanje() == 30);
		assertTrue(u1.getTip() == managers.getTipMng().pronadjiPoNazivu("masaza"));
		
		assertTrue(u2 != null);
		assertTrue(u2.getPodtip().equals("pedikir usluga"));
		assertTrue(u2.getTrajanje() == 45);
		assertTrue(u2.getTip() == managers.getTipMng().pronadjiPoNazivu("pedikir"));
		
		assertTrue(u3 != null);
		assertTrue(u3.getPodtip().equals("manikir usluga"));
		assertTrue(u3.getTrajanje() == 60);
		assertTrue(u3.getTip() == managers.getTipMng().pronadjiPoNazivu("manikir"));
	}

}
