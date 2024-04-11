package manage;

import entity.KozmetickiSalon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

public class KozmetickiSalonManager {
	private String kozmetickiSalonFile;
	private KozmetickiSalon kozmetickiSalon;

	public KozmetickiSalonManager(String kozmetickiSalonFile)
	{
		this.kozmetickiSalonFile = kozmetickiSalonFile;
	}
	
	public KozmetickiSalon getKozmetickiSalon()
	{
		return this.kozmetickiSalon;
	}
	
	public void postaviKozmetickiSalon()
	{
		this.kozmetickiSalon = new KozmetickiSalon("Moj salon", LocalTime.parse("08:00:00"), LocalTime.parse("20:00:00"));
		this.saveData();
	}
	
	public void ispisiKozmetickiSalon()
	{
		System.out.println(this.kozmetickiSalon);
	}
	
	public void izmeniKozmetickiSalon()
	{
		this.edit("Salon 3", LocalTime.parse("08:00:00"), LocalTime.parse("16:00:00"));
	}
	
	public void edit(String naziv, LocalTime pocetak, LocalTime kraj)
	{
		this.kozmetickiSalon.setNaziv(naziv);
		this.kozmetickiSalon.setRadnoVreme(pocetak, kraj);
		this.saveData();
	}
	
	public boolean loadData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(this.kozmetickiSalonFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] atributi = linija.split(";");
				this.kozmetickiSalon = new KozmetickiSalon(atributi[1], LocalTime.parse(atributi[2]), LocalTime.parse(atributi[3]));
				this.kozmetickiSalon.setId(Integer.parseInt(atributi[0]));
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
			pw = new PrintWriter(new FileWriter(this.kozmetickiSalonFile,false));
			pw.println(this.kozmetickiSalon.toFile());
			pw.close();
		}
		catch(IOException e)
		{
			return false;
		}
		
		return true;
	}
}
