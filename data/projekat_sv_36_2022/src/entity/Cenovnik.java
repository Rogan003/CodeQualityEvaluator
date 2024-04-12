package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Cenovnik extends IncrementId implements ToFile {
	private HashMap<KozmetickaUsluga,Double> cenovnik;
	
	public Cenovnik(ArrayList<KozmetickaUsluga> tipovi,ArrayList<Double> cene)
	{
		super();
		this.cenovnik = new HashMap<KozmetickaUsluga,Double>();
		int i = 0;
		for(KozmetickaUsluga t : tipovi)
		{
			this.cenovnik.put(t, cene.get(i++));
		}
	}
	
	public HashMap<KozmetickaUsluga,Double> getCenovnik()
	{
		return this.cenovnik;
	}
	
	public void setCenovnik(HashMap<KozmetickaUsluga,Double> cenovnik)
	{
		this.cenovnik = cenovnik;
	}
	
	public void dodaj(KozmetickaUsluga usluga, double cena)
	{
		this.cenovnik.put(usluga, cena);
	}
	
	public void izmeni(KozmetickaUsluga usluga, double novaCena)
	{
		this.cenovnik.put(usluga, novaCena);
	}
	
	public void skini(KozmetickaUsluga usluga)
	{
		this.cenovnik.remove(usluga);
	}
	
	public double pronadjiCenu(KozmetickaUsluga usluga)
	{
		if(this.cenovnik.containsKey(usluga))
		{
			return this.cenovnik.get(usluga);
		}
		else
		{
			return -1;
		}
		
	}
	
	@Override
	public String toString()
	{
		StringBuilder ret = new StringBuilder(50);
		
		for(KozmetickaUsluga usluga : this.cenovnik.keySet())
		{
			ret.append(usluga.getPodtip() + ": " + this.pronadjiCenu(usluga) + "\n");
		}
		
		return "Id: " + this.id + "\nCenovnik:\n" + ret;
	}
	
	@Override
	public String toFile()
	{
		StringBuilder ret = new StringBuilder(50);
		
		for(KozmetickaUsluga usluga : this.cenovnik.keySet())
		{
			ret.append(usluga.getId() + ":" + this.pronadjiCenu(usluga) + ";");
		}
		
		String povratna = null;
		if(ret.length() != 0)
		{
			povratna = ret.substring(0,ret.length() - 1);
		}
		else
		{
			povratna = "";
		}
		
		return this.id + ";" + povratna;
	}

}
