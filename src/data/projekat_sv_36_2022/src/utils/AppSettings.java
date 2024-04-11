package utils;

public class AppSettings {
	private String cenovnikFilename;
	private String klijentFilename;
	private String kozmeticarFilename;
	private String kozmetickaUslugaFilename;
	private String kozmetickiSalonFilename;
	private String kozmetickiTretmanFilename;
	private String menadzerFilename;
	private String recepcionerFilename;
	private String tipKozmetickogTretmanaFilename;
	
	public AppSettings(String cenovnikFilename, String klijentFilename, String kozmeticarFilename, 
			String kozmetickaUslugaFilename, String kozmetickiSalonFilename, String kozmetickiTretmanFilename, 
			String menadzerFilename, String recepcionerFilename, String tipKozmetickogTretmanaFilename)
	{
		this.cenovnikFilename = cenovnikFilename;
		this.klijentFilename = klijentFilename;
		this.kozmeticarFilename = kozmeticarFilename;
		this.kozmetickaUslugaFilename = kozmetickaUslugaFilename;
		this.kozmetickiSalonFilename = kozmetickiSalonFilename;
		this.kozmetickiTretmanFilename = kozmetickiTretmanFilename;
		this.menadzerFilename = menadzerFilename;
		this.recepcionerFilename = recepcionerFilename;
		this.tipKozmetickogTretmanaFilename = tipKozmetickogTretmanaFilename;
	}
	
	public String getCenovnikFilename()
	{
		return this.cenovnikFilename;
	}
	
	public String getKlijentFilename()
	{
		return this.klijentFilename;
	}
	
	public String getKozmeticarFilename()
	{
		return this.kozmeticarFilename;
	}
	
	public String getKozmetickaUslugaFilename()
	{
		return this.kozmetickaUslugaFilename;
	}
	
	public String getKozmetickiSalonFilename()
	{
		return this.kozmetickiSalonFilename;
	}
	
	public String getKozmetickiTretmanFilename()
	{
		return this.kozmetickiTretmanFilename;
	}
	
	public String getMenadzerFilename()
	{
		return this.menadzerFilename;
	}
	
	public String getRecepcionerFilename()
	{
		return this.recepcionerFilename;
	}
	
	public String getTipKozmetickogTretmanaFilename()
	{
		return this.tipKozmetickogTretmanaFilename;
	}
}
