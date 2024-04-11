package manage;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entity.KozmetickaUsluga;
import entity.KozmetickiTretman;
import entity.StanjeKozmetickogTretmana;

public class KozmetickiTretmanManager {
	private String kozmetickiTretmanFile;
	private ArrayList<KozmetickiTretman> tretmani;
	private KozmetickaUslugaManager kozmetickaUslugaMng;
	private CenovnikManager cenovnikMng;
	
	public KozmetickiTretmanManager(String kozmetickiTretmanFile, KozmetickaUslugaManager kozmetickaUslugaMng,
			CenovnikManager cenovnikMng)
	{
		this.kozmetickiTretmanFile = kozmetickiTretmanFile;
		this.tretmani = new ArrayList<KozmetickiTretman>();
		this.kozmetickaUslugaMng = kozmetickaUslugaMng;
		this.cenovnikMng = cenovnikMng;
	}
	
	public ArrayList<KozmetickiTretman> getTretmani()
	{
		return this.tretmani;
	}
	
	public void reset()
	{
		while(this.tretmani.size() != 0)
		{
			this.tretmani.remove(0);
		}
		
		this.saveData();
	}
	
	public void izmeniKt2()
	{
		KozmetickiTretman tretman = this.tretmani.get(1);
		KozmetickaUsluga usluga = this.kozmetickaUslugaMng.pronadjiPoNazivu("Francuski manikir");
		this.edit(tretman.getId(), usluga,
				tretman.getStanje(), tretman.getTermin(), this.cenovnikMng.pronadjiCenu(usluga));
	}
	
	public void dodajCLI()
	{ 
		KozmetickaUsluga nova = this.kozmetickaUslugaMng.getUsluge().get(1);
		this.add(nova, StanjeKozmetickogTretmana.ZAKAZAN,
				LocalDateTime.of(2023, 4, 30, 13, 00), this.cenovnikMng.pronadjiCenu(nova));
		nova = this.kozmetickaUslugaMng.getUsluge().get(1);
		this.add(nova, StanjeKozmetickogTretmana.NIJE_SE_POJAVIO,
				LocalDateTime.of(2023, 4, 30, 9, 00), this.cenovnikMng.pronadjiCenu(nova));
		nova = this.kozmetickaUslugaMng.getUsluge().get(1);
		this.add(nova, StanjeKozmetickogTretmana.ZAKAZAN,
				LocalDateTime.of(2023, 4, 30, 15, 00), this.cenovnikMng.pronadjiCenu(nova));
	}
	
	public void prikaziCLI()
	{
		for(KozmetickiTretman tretman : this.tretmani)
		{
			System.out.println(tretman);
			System.out.println();
		}
	}
	
	public void izmeniCLI()
	{
		KozmetickaUsluga nova = this.kozmetickaUslugaMng.getUsluge().get(1);
		this.edit(this.tretmani.get(2).getId(),nova, StanjeKozmetickogTretmana.OTKAZAO_SALON,
				LocalDateTime.of(2023, 4, 30, 18, 00), this.cenovnikMng.pronadjiCenu(nova));
	}
	
	public void obrisiCLI()
	{
		this.remove(this.tretmani.get(1).getId());
	}
	
	public KozmetickiTretman pronadjiPoId(int id)
	{
		for(KozmetickiTretman tretman : this.tretmani)
		{
			if(tretman.getId() == id)
				return tretman;
		}
		
		return null;
	}
	
	public KozmetickiTretman add(KozmetickaUsluga usluga, StanjeKozmetickogTretmana stanje, LocalDateTime termin, double cena)
	{
		KozmetickiTretman tretman = new KozmetickiTretman(usluga,stanje,termin,cena);
		this.tretmani.add(tretman);
		this.saveData();
		return tretman;
	}
	
	public void edit(int id, KozmetickaUsluga usluga, StanjeKozmetickogTretmana stanje, LocalDateTime termin, double cena)
	{
		KozmetickiTretman izmena = this.pronadjiPoId(id);
		izmena.setUsluga(usluga);
		izmena.setStanjeKozmetickogTretmana(stanje);
		izmena.setTermin(termin);
		izmena.setCena(cena);
		this.saveData();
	}
	
	public void remove(int id)
	{
		this.tretmani.remove(this.pronadjiPoId(id));
		this.saveData();
	}
	
	public ArrayList<KozmetickiTretman> prikaziZakazaneIstekle()
	{
		ArrayList<KozmetickiTretman> zakazaniIstekli = new ArrayList<KozmetickiTretman>();
		LocalDateTime trenutno = LocalDateTime.now();
		
		for(KozmetickiTretman tretman : this.tretmani)
		{
			if(tretman.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN && tretman.getTermin().isBefore(trenutno))
			{
				zakazaniIstekli.add(tretman);
			}
		}
		
		return zakazaniIstekli;
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.kozmetickiTretmanFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] atributi = linija.split(";");
				StanjeKozmetickogTretmana stanje = null;
				switch(atributi[2])
				{
					case "ZAKAZAN":
						stanje = StanjeKozmetickogTretmana.ZAKAZAN;
						break;
					case "OTKAZAO_SALON":
						stanje = StanjeKozmetickogTretmana.OTKAZAO_SALON;
						break;
					case "OTKAZAO_KLIJENT":
						stanje = StanjeKozmetickogTretmana.OTKAZAO_KLIJENT;
						break;
					case "IZVRSEN":
						stanje = StanjeKozmetickogTretmana.IZVRSEN;
						break;
					case "NIJE_SE_POJAVIO":
						stanje = StanjeKozmetickogTretmana.NIJE_SE_POJAVIO;
						break;
				}
				KozmetickiTretman noviTretman = new KozmetickiTretman(this.kozmetickaUslugaMng.pronadjiPoId(Integer.parseInt(atributi[1])),
						stanje,LocalDateTime.parse(atributi[3],DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
						Double.parseDouble(atributi[4]));
				noviTretman.setId(Integer.parseInt(atributi[0]));
				this.tretmani.add(noviTretman);
			}
			br.close();
		}
		catch(IOException e)
		{
			return false;
		}
		return true;
	}
	
	public boolean saveData()
	{
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(this.kozmetickiTretmanFile,false));
			for(KozmetickiTretman tretman : this.tretmani)
			{
				pw.println(tretman.toFile());
			}
			pw.close();
		}
		catch(IOException e)
		{
			return false;
		}
		
		return true;
	}
}
