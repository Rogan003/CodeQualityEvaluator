package entity;

public class TipKozmetickogTretmana extends IncrementId implements ToFile{
	private String naziv;
	
	public TipKozmetickogTretmana(String naziv)
	{
		super();
		this.naziv = naziv;
	}
	
	public String getNaziv()
	{
		return this.naziv;
	}
	
	public void setNaziv(String naziv)
	{
		this.naziv = naziv;
	}
	
	@Override
	public String toString()
	{
		return this.naziv;
	}
	
	@Override 
	public String toFile()
	{
		return this.id + ";" + this.naziv;
	}
}
