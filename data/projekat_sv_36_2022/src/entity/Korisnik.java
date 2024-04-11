package entity;

public abstract class Korisnik extends IncrementId{
	protected String ime;
	protected String prezime;
	protected String pol;
	protected String telefon;
	protected String adresa;
	protected String korisnickoIme;
	protected String lozinka;
	
	Korisnik(String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.telefon = telefon;
		this.adresa = adresa;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
	
	public String getIme()
	{
		return this.ime;
	}
	
	public String getPrezime()
	{
		return this.prezime;
	}
	
	public String getPol()
	{
		return this.pol;
	}
	
	public String getTelefon()
	{
		return this.telefon;
	}
	
	public String getAdresa()
	{
		return this.adresa;
	}
	
	public String getKorisnickoIme()
	{
		return this.korisnickoIme;
	}
	
	public String getLozinka()
	{
		return this.lozinka;
	}
	
	public void setIme(String ime)
	{
		this.ime = ime;
	}
	
	public void setPrezime(String prezime)
	{
		this.prezime = prezime;
	}
	
	public void setPol(String pol)
	{
		this.pol = pol;
	}
	
	public void setTelefon(String telefon)
	{
		this.telefon = telefon;
	}
	
	public void setAdresa(String adresa)
	{
		this.adresa = adresa;
	}
	
	public void setKorisnickoIme(String korisnickoIme)
	{
		this.korisnickoIme = korisnickoIme;
	}
	
	public void setLozinka(String lozinka)
	{
		this.lozinka = lozinka;
	}
	
	// mozda onaj hashCode?
	
	@Override
	public String toString()
	{
		return "Id: " + this.id + "\nKorisnicko ime: " + this.korisnickoIme + "\n" + "Lozinka: " + 
				this.lozinka + "\nIme: " + this.ime + "\nPrezime: " + this.prezime + "\nPol: " + this.pol + 
				"\nTelefon: " + this.telefon + "\nAdresa: " + this.adresa;
	}
}
