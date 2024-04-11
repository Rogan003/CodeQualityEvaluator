package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entity.TipKozmetickogTretmana;

public class TipKozmetickogTretmanaManager {
	private String tipKozmetickogTretmanaFile;
	private ArrayList<TipKozmetickogTretmana> tipovi;
	
	public TipKozmetickogTretmanaManager(String tipKozmetickogTretmanaFile)
	{
		this.tipKozmetickogTretmanaFile = tipKozmetickogTretmanaFile;
		this.tipovi = new ArrayList<TipKozmetickogTretmana>();
	}
	
	public ArrayList<TipKozmetickogTretmana> getTipovi()
	{
		return this.tipovi;
	}
	
	public void dodajKt2()
	{
		this.add("masaza");
		this.add("manikir");
		this.add("pedikir");
	}
	
	public void dodajCLI()
	{
		this.add("Masaza");
		this.add("Sisanj");
		this.add("Bla bla");
		this.add("Manikir");
		this.add("Pedikir");
	}
	
	public void prikaziCLI()
	{
		for(TipKozmetickogTretmana tip : this.tipovi)
		{
			System.out.println(tip);
		}
	}
	
	public void izmeniCLI()
	{
		this.edit(this.tipovi.get(1).getId(),"Sisanje");
	}
	
	public void obrisiCLI()
	{
		this.remove(this.tipovi.get(2).getId());
	}
	
	public TipKozmetickogTretmana pronadjiPoId(int id)
	{
		for(TipKozmetickogTretmana t : this.tipovi)
		{
			if(t.getId() == id)
			{
				return t;
			}
		}
		
		return null;
	}
	
	public TipKozmetickogTretmana pronadjiPoNazivu(String naziv)
	{
		for(TipKozmetickogTretmana t : this.tipovi)
		{
			if(t.getNaziv().equals(naziv))
			{
				return t;
			}
		}
		
		return null;
	}
	
	public void add(String naziv)
	{
		this.tipovi.add(new TipKozmetickogTretmana(naziv));
		this.saveData();
	}
	
	public void edit(int id, String naziv)
	{
		TipKozmetickogTretmana izmena = this.pronadjiPoId(id);
		izmena.setNaziv(naziv);
		this.saveData();
	}
	
	public void remove(int id)
	{
		TipKozmetickogTretmana brisanje = this.pronadjiPoId(id);
		this.tipovi.remove(brisanje);
		this.saveData();
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.tipKozmetickogTretmanaFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] atributi = linija.split(";");
				TipKozmetickogTretmana noviTip = new TipKozmetickogTretmana(atributi[1]);
				noviTip.setId(Integer.parseInt(atributi[0]));
				this.tipovi.add(noviTip);
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
			pw = new PrintWriter(new FileWriter(this.tipKozmetickogTretmanaFile,false));
			for(TipKozmetickogTretmana tip : this.tipovi)
			{
				pw.println(tip.toFile());
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
