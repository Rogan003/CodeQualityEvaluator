package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.KozmetickaUsluga;
import entity.TipKozmetickogTretmana;

public class KozmetickaUslugaManager {
	private String kozmetickaUslugaFile;
	private ArrayList<KozmetickaUsluga> usluge;
	private TipKozmetickogTretmanaManager tipMng;
	
	public KozmetickaUslugaManager(String kozmetickaUslugaFile, TipKozmetickogTretmanaManager tipMng)
	{
		this.kozmetickaUslugaFile = kozmetickaUslugaFile;
		this.tipMng = tipMng;
		this.usluge = new ArrayList<KozmetickaUsluga>();
	}
	
	public ArrayList<KozmetickaUsluga> getUsluge()
	{
		return this.usluge;
	}
	
	public void dodajKt2()
	{
		this.add(tipMng.pronadjiPoNazivu("masaza"), 45, "Relaks masaza");
		this.add(tipMng.pronadjiPoNazivu("masaza"), 75, "Sportska masaza");
		this.add(tipMng.pronadjiPoNazivu("manikir"), 50, "Francuski manikir");
		this.add(tipMng.pronadjiPoNazivu("manikir"), 80, "Gel lak");
		this.add(tipMng.pronadjiPoNazivu("manikir"), 90, "Spa manikir");
		this.add(tipMng.pronadjiPoNazivu("masaza"), 45, "Spa pedikir");
	}
	
	public void izmeniKt2()
	{
		KozmetickaUsluga izmena = this.pronadjiPoNazivu("Francuski manikir");
		this.edit(izmena.getId(), izmena.getTip(), 55, izmena.getPodtip());
		izmena = this.pronadjiPoNazivu("Spa pedikir");
		this.edit(izmena.getId(), this.tipMng.pronadjiPoNazivu("pedikir"), izmena.getTrajanje(), izmena.getPodtip());
	}
	
	public void dodajCLI()
	{
		this.add(tipMng.getTipovi().get(0), 60, "Podtip 1");
		this.add(tipMng.getTipovi().get(1), 30, "Podtip 4");
		this.add(tipMng.getTipovi().get(2), 50, "Podtip 3");
	}
	
	public void prikaziCLI()
	{
		for(KozmetickaUsluga usluga : this.usluge)
		{
			System.out.println(usluga);
		}
	}
	
	public void izmeniCLI()
	{
		this.edit(this.usluge.get(1).getId(), tipMng.getTipovi().get(3), 40, "Podtip 2");
	}
	
	public void obrisiCLI()
	{
		this.remove(this.usluge.get(2).getId());
	}
	
	public KozmetickaUsluga pronadjiPoId(int id)
	{
		for(KozmetickaUsluga usluga : this.usluge)
		{
			if(usluga.getId() == id)
				return usluga;
		}
		
		return null;
	}
	
	public KozmetickaUsluga pronadjiPoNazivu(String naziv)
	{
		for(KozmetickaUsluga usluga : this.usluge)
		{
			if(usluga.getPodtip().equals(naziv))
				return usluga;
		}
		
		return null;
	}
	
	public void add(TipKozmetickogTretmana tip, int trajanje, String podtip)
	{
		this.usluge.add(new KozmetickaUsluga(tip,trajanje,podtip));
		this.saveData();
	}
	
	public void edit(int id, TipKozmetickogTretmana noviTip, int novoTrajanje, String podtip)
	{
		KozmetickaUsluga izmena = this.pronadjiPoId(id);
		izmena.setTip(noviTip);
		izmena.setTrajanje(novoTrajanje);
		izmena.setPodtip(podtip);
		this.saveData();
	}
	
	public void remove(int id)
	{
		KozmetickaUsluga brisanje = this.pronadjiPoId(id);
		this.usluge.remove(brisanje);
		this.saveData();
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.kozmetickaUslugaFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] atributi = linija.split(";");
				KozmetickaUsluga novaUsluga = new KozmetickaUsluga(this.tipMng.pronadjiPoId(Integer.parseInt(atributi[1])),
						Integer.parseInt(atributi[3]),atributi[2]);
				novaUsluga.setId(Integer.parseInt(atributi[0]));
				this.usluge.add(novaUsluga);
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
			pw = new PrintWriter(new FileWriter(this.kozmetickaUslugaFile,false));
			for(KozmetickaUsluga usluga : this.usluge)
			{
				pw.println(usluga.toFile());
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
