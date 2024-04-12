package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import entity.Cenovnik;
import entity.KozmetickaUsluga;

public class CenovnikManager {
	private String cenovnikFile;
	private Cenovnik cenovnik;
	private KozmetickaUslugaManager kozmetickaUslugaMng;
	
	public CenovnikManager(String cenovnikFile, KozmetickaUslugaManager kozmetickaUslugaMng)
	{
		this.cenovnikFile = cenovnikFile;
		this.kozmetickaUslugaMng = kozmetickaUslugaMng;
	}
	
	public Cenovnik getCenovnik()
	{
		return this.cenovnik;
	}
	
	public void dodajKt2()
	{	
		this.cenovnik = new Cenovnik(this.kozmetickaUslugaMng.getUsluge(),
				new ArrayList<Double>(Arrays.asList(2000.00,2500.00,1500.00,1600.00,2000.00,1600.00)));
		this.saveData();
	}
	
	public void postaviCenovnik()
	{
		this.cenovnik = new Cenovnik(new ArrayList<KozmetickaUsluga>(), new ArrayList<Double>()); // prazan cenovnik?
	}
	
	// ove metode treba popuniti kao da je konzolni? ili samo popuniti nekako pa pozivati kao testove
	public void dodajNaCenovnikCLI()
	{
		this.dodajNaCenovnik(kozmetickaUslugaMng.getUsluge().get(0), 1000);
		this.dodajNaCenovnik(kozmetickaUslugaMng.getUsluge().get(1), 600);
	}
	
	public void prikaziCLI()
	{
		System.out.println(this.cenovnik);
	}
	
	public void skiniSaCenovnikaCLI()
	{
		this.skiniSaCenovnika(kozmetickaUslugaMng.getUsluge().get(0));
	}
	
	public void izmeniSaCenovnikaCLI()
	{
		this.izmeniSaCenovnika(kozmetickaUslugaMng.getUsluge().get(1), 700);
	}
	
	public void dodajNaCenovnik(KozmetickaUsluga usluga, double cena)
	{
		this.cenovnik.dodaj(usluga, cena);
		this.cenovnik.dodaj(usluga, cena);
		this.saveData();
	}
	
	public void skiniSaCenovnika(KozmetickaUsluga usluga)
	{
		this.cenovnik.skini(usluga);
		this.saveData();
	}
	
	public void izmeniSaCenovnika(KozmetickaUsluga usluga, double novaCena)
	{
		this.cenovnik.izmeni(usluga, novaCena);
		this.saveData();
	}
	
	public double pronadjiCenu(KozmetickaUsluga usluga)
	{
		return this.cenovnik.pronadjiCenu(usluga);
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.cenovnikFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] atributi = linija.split(";");
				ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga>();
				ArrayList<Double> cene = new ArrayList<Double>();
				for(int i = 1;i < atributi.length;i++)
				{
					String[] podela = atributi[i].split(":");
					usluge.add(this.kozmetickaUslugaMng.pronadjiPoId(Integer.parseInt(podela[0])));
					cene.add(Double.parseDouble(podela[1]));
				}
				this.cenovnik = new Cenovnik(usluge,cene);
				this.cenovnik.setId(Integer.parseInt(atributi[0]));
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
			pw = new PrintWriter(new FileWriter(this.cenovnikFile,false));
			pw.println(this.cenovnik.toFile());
			pw.close();
		}
		catch(IOException e)
		{
			return false;
		}
		
		return true;
	}
}
