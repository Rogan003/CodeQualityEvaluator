package entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Kozmeticar extends Zaposleni implements ToFile{
	private ArrayList<TipKozmetickogTretmana> obuceniTipovi;
	private ArrayList<KozmetickiTretman> tretmani;
	public static int bonusPoTretmanu = 0;
	public static int brojTretmanaBonus = 0;
	public static double zaradaBonus = 0;
	
	public Kozmeticar(Sprema sprema, int staz, int bonus, int plata, String ime, String prezime, String pol, String telefon,
			  String adresa, String korisnickoIme, String lozinka, ArrayList<TipKozmetickogTretmana> obuceniTipovi)
	{
		super(sprema,staz,bonus,plata,ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
		this.obuceniTipovi = obuceniTipovi;
		this.tretmani = new ArrayList<KozmetickiTretman>();
	}
	
	public Kozmeticar(ArrayList<TipKozmetickogTretmana> obuceniTipovi, ArrayList<KozmetickiTretman> tretmani,
			Sprema sprema, int staz, int bonus, int plata, String ime, String prezime, String pol, String telefon,
			  String adresa, String korisnickoIme, String lozinka)
	{
		super(sprema,staz,bonus,plata,ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
		this.obuceniTipovi = obuceniTipovi;
		this.tretmani = tretmani;
	}
	
	public ArrayList<TipKozmetickogTretmana> getObuceniTipovi()
	{
		return this.obuceniTipovi;
	}
	
	public ArrayList<KozmetickiTretman> getTretmani()
	{
		return this.tretmani;
	}
	
	public void setObuceniTipovi(ArrayList<TipKozmetickogTretmana> obuceniTipovi)
	{
		this.obuceniTipovi = obuceniTipovi;
	}
	
	public void setTretmani(ArrayList<KozmetickiTretman> tretmani)
	{
		this.tretmani = tretmani;
	}
	
	public ArrayList<LocalDateTime> getRaspored(LocalTime pocetak, LocalTime kraj)
	{// testirati, jako racunam na to da salon nema radno vreme koje obuhvata ponoc i da radi samo na pune sate
		ArrayList<LocalDateTime> raspored = new ArrayList<LocalDateTime>();
		LocalDateTime termin = LocalDateTime.now();
		termin = termin.plusHours(1).minusMinutes(termin.getMinute()).minusSeconds(termin.getSecond());
		LocalDateTime najkasnijiTretman = LocalDateTime.now().plusDays(7);
		for(KozmetickiTretman tretman : this.tretmani)
		{
			if(tretman.getTermin().isAfter(najkasnijiTretman))
			{
				najkasnijiTretman = tretman.getTermin();
			}
		}
		
		while(termin.isBefore(najkasnijiTretman))
		{
			if(termin.getHour() > kraj.getHour())
			{
				termin = termin.plusHours(24 - kraj.getHour() + pocetak.getHour()); // valjda povecava i dane
				continue;
			}
			
			if(termin.getHour() < pocetak.getHour())
			{
				termin = termin.plusHours(pocetak.getHour() - termin.getHour());
			}

			boolean isTaken = false;
			for(KozmetickiTretman tretman : this.tretmani)
			{
				if(tretman.getTermin().equals(termin))
				{
					isTaken = true;
					termin.plusHours(1 + tretman.getUsluga().getTrajanje() % 60);
					break;
				}
			}
			
			if(!isTaken)
			{
				raspored.add(termin);
				termin = termin.plusHours(1);
			}
		}
		return raspored;
	}
	
	public ArrayList<LocalDateTime> getRasporedZauzetih()
	{
		ArrayList<LocalDateTime> raspored = new ArrayList<LocalDateTime>();
		
		for(KozmetickiTretman tretman : this.getTretmani())
		{
			raspored.add(tretman.getTermin());
		}
		
		return raspored;
	}
	
	public void ispisiRaspored()
	{
		for(KozmetickiTretman tretman : this.getTretmani())
		{
			if(tretman.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN)
			{
				System.out.println("Zauzet " + tretman.getUsluga().getTrajanje() + " minuta"
						+ ": " + tretman.getTermin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			}
		}
	}
	
	public void zakaziTretman(KozmetickiTretman tretman)
	{
		this.tretmani.add(tretman);
	}
	
	@Override
	public int izraunajPlatu()
	{
		int brojTretmana = 0;
		LocalDateTime mesec = LocalDateTime.now().minusMonths(1);
		LocalDateTime danas = LocalDateTime.now();
		for(KozmetickiTretman tretman : this.tretmani)
		{
			if(tretman.getTermin().isAfter(mesec) && tretman.getTermin().isBefore(danas) && 
					tretman.getStanje() == StanjeKozmetickogTretmana.IZVRSEN)
			{
				brojTretmana++;
			}
		}
		
		this.bonus = bonusPoTretmanu * brojTretmana;
		return super.izraunajPlatu();
	}
	
	public void dodajBonus(int bonus, int brojTretmana)
	{	
		this.bonus = bonus * brojTretmana;
	}
	
	@Override
	public String toString()
	{
//		StringBuilder ispisTretmana = new StringBuilder(50);
//		for(TipKozmetickogTretmana tip : this.obuceniTipovi)
//		{
//			ispisTretmana.append(tip + "\n");
//		}
//		
//		ispisTretmana.append("Zakazani tretmani: " + "\n");
//		for(KozmetickiTretman tretman : this.tretmani)
//		{
//			ispisTretmana.append(tretman + "\n");
//		}
//		
//		return super.toString() + "\n" + "Obuceni tipovi: " + ispisTretmana;
		return this.ime + " " + this.prezime;
	}
	
	@Override
	public String toFile()
	{
		StringBuilder tretmaniIspis = new StringBuilder(50);
		
		for(TipKozmetickogTretmana tip : this.obuceniTipovi)
		{
			tretmaniIspis.append(tip.getId() + ",");
		}
		
		String povratna = null;
		if(tretmaniIspis.length() != 0)
		{
			povratna = tretmaniIspis.substring(0,tretmaniIspis.length() - 1);
		}
		else
		{
			povratna = "";
		}

		tretmaniIspis = new StringBuilder(povratna);
		tretmaniIspis.append(";");
		for(KozmetickiTretman tretman : this.tretmani)
		{
			tretmaniIspis.append(tretman.getId() + ",");
		}
		
		if(tretmaniIspis.length() != povratna.length() + 1)
		{
			povratna = tretmaniIspis.substring(0,tretmaniIspis.length() - 1);
		}
		else
		{
			povratna = tretmaniIspis.toString();
		}
		
		return this.id + ";" + this.ime + ";" + this.prezime + ";" + this.pol + ";" + this.telefon + ";"
				+ this.adresa + ";" + this.korisnickoIme + ";" + this.lozinka + ";" + this.sprema + ";"
				+ this.staz + ";" + this.bonus + ";" + this.plata + ";" + povratna;
	}
}
