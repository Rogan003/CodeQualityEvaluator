package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KozmetickiTretman extends IncrementId implements ToFile{
	private KozmetickaUsluga usluga;
	private StanjeKozmetickogTretmana stanje;
	private LocalDateTime termin;
	private double cena;
	
	public KozmetickiTretman(KozmetickaUsluga usluga,StanjeKozmetickogTretmana stanje,LocalDateTime termin,double cena)
	{
		super();
		this.usluga = usluga;
		this.stanje = stanje;
		this.termin = termin;
		this.cena = cena;
	}
	
	public KozmetickaUsluga getUsluga()
	{
		return this.usluga;
	}
	
	public StanjeKozmetickogTretmana getStanje()
	{
		return this.stanje;
	}
	
	public LocalDateTime getTermin()
	{
		return this.termin;
	}
	
	public double getCena()
	{
		return this.cena;
	}
	
	public void setUsluga(KozmetickaUsluga usluga)
	{
		this.usluga = usluga;
	}
	
	public void setStanjeKozmetickogTretmana(StanjeKozmetickogTretmana stanje)
	{
		this.stanje = stanje;
	}
	
	public void setTermin(LocalDateTime termin)
	{
		this.termin = termin;
	}
	
	public void setCena(double cena)
	{
		this.cena = cena;
	}
	
	@Override
	public String toString()
	{
		return "Id: " + this.id + "\nKozmeticka usluga: " + this.usluga + "\nStanje: " + this.stanje +
				"\nTermin: " + this.termin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "\nCena: " + this.cena;
	}
	
	@Override
	public String toFile()
	{
		return this.id + ";" + this.usluga.getId() + ";" + this.stanje +
				";" + this.termin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + ";" + this.cena;
	}
}
