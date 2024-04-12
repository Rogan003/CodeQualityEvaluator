package test;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import entity.KozmetickaUsluga;
import entity.KozmetickiTretman;
import entity.StanjeKozmetickogTretmana;
import manage.KozmetickiTretmanManager;
import manage.ManagerFactory;
import utils.AppSettings;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KozmetickiTretmanManagerTest {

	public static ManagerFactory managers = new ManagerFactory(new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
			"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
			"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv"));
	public static KozmetickiTretmanManager tretmanMng;
	public static double pot1;
	public static double pot2;
	
	@BeforeClass
	public static void setUpBeforeClass()
	{
		managers.loadData();
		pot1 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getPotrosio();
		pot2 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").getPotrosio();
		tretmanMng = new KozmetickiTretmanManager("./data/tretmaniTest.csv", managers.getUslugaMng(), managers.getCenovnikMng());
	}
	
	@AfterClass
	public static void tearDownAfterClass()
	{
		File fajl = new File("./data/tretmaniTest.csv");
		fajl.delete();
		int duzina1 = managers.getTretmanMng().getTretmani().size();
		managers.getTretmanMng().remove(managers.getTretmanMng().getTretmani().get(duzina1 - 3).getId());
		managers.getTretmanMng().remove(managers.getTretmanMng().getTretmani().get(duzina1 - 3).getId());
		managers.getTretmanMng().remove(managers.getTretmanMng().getTretmani().get(duzina1 - 3).getId());
		int duzina2 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getTretmani().size();
		managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getTretmani().remove(duzina2 - 1);
		managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getTretmani().remove(duzina2 - 2);
		int duzina3 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").getTretmani().size();
		managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").getTretmani().remove(duzina3 - 1);
		managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").setPotrosio(pot1);
		managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").setPotrosio(pot2);
		int duzina4 = managers.getKozmeticarMng().getKozmeticari().get(0).getTretmani().size();
		int duzina5 = managers.getKozmeticarMng().getKozmeticari().get(1).getTretmani().size();
		int duzina6 = managers.getKozmeticarMng().getKozmeticari().get(2).getTretmani().size();
		managers.getKozmeticarMng().getKozmeticari().get(0).getTretmani().remove(duzina4 - 1);
		managers.getKozmeticarMng().getKozmeticari().get(1).getTretmani().remove(duzina5 - 1);
		managers.getKozmeticarMng().getKozmeticari().get(2).getTretmani().remove(duzina6 - 1);
		managers.getKlijentMng().saveData();
		managers.getKozmeticarMng().saveData();
	}

	@Test
	public void testAdd()
	{
		KozmetickaUsluga fm = managers.getUslugaMng().pronadjiPoNazivu("Francuski manikir");
		boolean t1 = managers.zakaziTretman(managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic"),
				managers.getKozmeticarMng().getKozmeticari().get(2), fm, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 7, 10, 18, 00), managers.getCenovnikMng().pronadjiCenu(fm));
		
		KozmetickaUsluga sp = managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir");
		boolean t2 = managers.zakaziTretman(managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic"),
				managers.getKozmeticarMng().getKozmeticari().get(1), sp, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 7, 11, 9, 00), managers.getCenovnikMng().pronadjiCenu(sp));
		
		KozmetickaUsluga sm = managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza");
		boolean t3 = managers.zakaziTretman(managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic"),
				managers.getKozmeticarMng().getKozmeticari().get(0), sm, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 7, 12, 8, 00), managers.getCenovnikMng().pronadjiCenu(sm));
		
		boolean t4 = managers.zakaziTretman(managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic"),
				managers.getKozmeticarMng().getKozmeticari().get(2), fm, StanjeKozmetickogTretmana.ZAKAZAN, 
				LocalDateTime.of(2023, 7, 10, 18, 00), managers.getCenovnikMng().pronadjiCenu(fm)); // treba da padne
		
		int duzina2 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getTretmani().size();
		KozmetickiTretman k1 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getTretmani().get(duzina2 - 1);
		KozmetickiTretman k2 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getTretmani().get(duzina2 - 2);
		int duzina3 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").getTretmani().size();
		KozmetickiTretman k3 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").getTretmani().get(duzina3 - 1);
		int duzina4 = managers.getKozmeticarMng().getKozmeticari().get(0).getTretmani().size();
		int duzina5 = managers.getKozmeticarMng().getKozmeticari().get(1).getTretmani().size();
		int duzina6 = managers.getKozmeticarMng().getKozmeticari().get(2).getTretmani().size();
		KozmetickiTretman k4 = managers.getKozmeticarMng().getKozmeticari().get(0).getTretmani().get(duzina4 - 1);
		KozmetickiTretman k5 = managers.getKozmeticarMng().getKozmeticari().get(1).getTretmani().get(duzina5 - 1);
		KozmetickiTretman k6 = managers.getKozmeticarMng().getKozmeticari().get(2).getTretmani().get(duzina6 - 1);
		
		assertTrue(k1.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"));
		assertTrue(k1.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN);
		assertTrue(k1.getTermin().equals(LocalDateTime.of(2023, 7, 11, 9, 00)));
		assertTrue(k1.getCena() == 1600);
		
		assertTrue(k2.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Francuski manikir"));
		assertTrue(k2.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN);
		assertTrue(k2.getTermin().equals(LocalDateTime.of(2023, 7, 10, 18, 00)));
		assertTrue(k2.getCena() == 1500);
		
		assertTrue(k3.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		assertTrue(k3.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN);
		assertTrue(k3.getTermin().equals(LocalDateTime.of(2023, 7, 12, 8, 00)));
		assertTrue(k3.getCena() == 2500);

		assertTrue(k4.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		assertTrue(k4.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN);
		assertTrue(k4.getTermin().equals(LocalDateTime.of(2023, 7, 12, 8, 00)));
		assertTrue(k4.getCena() == 2500);
		
		assertTrue(k5.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Spa pedikir"));
		assertTrue(k5.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN);
		assertTrue(k5.getTermin().equals(LocalDateTime.of(2023, 7, 11, 9, 00)));
		assertTrue(k5.getCena() == 1600);
		
		assertTrue(k6.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Francuski manikir"));
		assertTrue(k6.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN);
		assertTrue(k6.getTermin().equals(LocalDateTime.of(2023, 7, 10, 18, 00)));
		assertTrue(k6.getCena() == 1500);
		
		assertTrue(t1);
		assertTrue(t2);
		assertTrue(t3);
		assertTrue(!t4);
	}
	
	@Test
	public void testEdit()
	{
		int duzina = managers.getTretmanMng().getTretmani().size();
		KozmetickiTretman t1 = managers.getTretmanMng().getTretmani().get(duzina - 3);
		KozmetickiTretman t2 = managers.getTretmanMng().getTretmani().get(duzina - 2);
		KozmetickiTretman t3 = managers.getTretmanMng().getTretmani().get(duzina - 1);
		
		double potrosio1 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getPotrosio();
		double potrosio2 = managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").getPotrosio();
		
		managers.otkaziTretman(t1, false);
		managers.otkaziTretman(t2, true);
		managers.nijeSePojavio(t3);
		
		assertTrue(t1.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_SALON);
		assertTrue(t2.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT);
		assertTrue(t3.getStanje() == StanjeKozmetickogTretmana.NIJE_SE_POJAVIO);
		assertTrue(managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Milica", "Milic").getPotrosio() == 
				(potrosio1 - t1.getCena() - t2.getCena()));
		assertTrue(managers.getKlijentMng().pronadjiPoImenuIPrezimenu("Mika", "Mikic").getPotrosio() == (potrosio2 - t3.getCena()));
	}
	
	@Test
	public void testFile()
	{
		tretmanMng.add(managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"), StanjeKozmetickogTretmana.ZAKAZAN,
				LocalDateTime.of(2023, 6, 10, 18, 00), 1000);
		tretmanMng.add(managers.getUslugaMng().pronadjiPoNazivu("Gel lak"), StanjeKozmetickogTretmana.IZVRSEN,
				LocalDateTime.of(2023, 7, 20, 15, 00), 2000);
		
		KozmetickiTretmanManager pomocni = new KozmetickiTretmanManager("./data/tretmaniTest.csv", managers.getUslugaMng(), managers.getCenovnikMng());
		pomocni.loadData();
		
		KozmetickiTretman t1 = pomocni.getTretmani().get(0);
		KozmetickiTretman t2 = pomocni.getTretmani().get(1);
		
		assertTrue(t1.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Sportska masaza"));
		assertTrue(t1.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN);
		assertTrue(t1.getTermin().equals(LocalDateTime.of(2023, 6, 10, 18, 00)));
		assertTrue(t1.getCena() == 1000);
		
		assertTrue(t2.getUsluga() == managers.getUslugaMng().pronadjiPoNazivu("Gel lak"));
		assertTrue(t2.getStanje() == StanjeKozmetickogTretmana.IZVRSEN);
		assertTrue(t2.getTermin().equals(LocalDateTime.of(2023, 7, 20, 15, 00)));
		assertTrue(t2.getCena() == 2000);
	}

}
