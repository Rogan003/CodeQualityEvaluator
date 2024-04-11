package entity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class KozmetickiSalon extends IncrementId implements ToFile{
	private String naziv;
	private LocalTime[] radnoVreme;
	
	public KozmetickiSalon(String naziv, LocalTime pocetak, LocalTime kraj)
	{
		super();
		this.naziv = naziv;
		this.radnoVreme = new LocalTime[2];
		this.radnoVreme[0] = pocetak;
		this.radnoVreme[1] = kraj;
	}
	
	public String getNaziv()
	{
		return this.naziv;
	}
	
	public LocalTime[] getRadnoVreme()
	{
		return this.radnoVreme;
	}
	
	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}
	
	public void setRadnoVreme(LocalTime pocetak, LocalTime kraj)
	{
		this.radnoVreme[0] = pocetak;
		this.radnoVreme[1] = kraj;
	}
	
	@Override
	public String toString()
	{
		return "Id: " + this.id + "\nNaziv: " + this.naziv
				+ "\nPocetak radnog vremena: " + this.radnoVreme[0].format(DateTimeFormatter.ofPattern("HH:mm:ss"))
				+ "\nKraj radnog vremena: " + this.radnoVreme[1].format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	@Override
	public String toFile()
	{
		return this.id + ";" + this.naziv + ";" + this.radnoVreme[0].format(DateTimeFormatter.ofPattern("HH:mm:ss"))
				+ ";" + this.radnoVreme[1].format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
}
