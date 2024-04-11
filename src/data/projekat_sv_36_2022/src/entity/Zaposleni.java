package entity;

public abstract class Zaposleni extends Korisnik{
	protected Sprema sprema;
	protected int staz;
	protected int bonus;
	protected int plata;
	
	Zaposleni(Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		super(ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
		this.sprema = sprema;
		this.staz = staz;
		this.bonus = bonus;
		this.plata = plata;
	}
	
	public Sprema getSprema()
	{
		return this.sprema;
	}
	
	public int getStaz()
	{
		return this.staz;
	}
	
	public int getBonus()
	{
		return this.bonus;
	}
	
	public int getPlata()
	{
		return this.plata;
	}
	
	public void setSprema(Sprema sprema)
	{
		this.sprema = sprema;
	}
	
	public void setStaz(int staz)
	{
		this.staz = staz;
	}
	
	public void setBonus(int bonus)
	{
		this.bonus = bonus;
	}
	
	public void setPlata(int plata)
	{
		this.plata = plata;
	}
	
	public int izraunajPlatu()
	{
		return this.plata + this.bonus + 500 * this.staz + 5000 * this.sprema.ordinal();
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "\nSprema: " + this.sprema + "\nStaz: " + this.staz + "\nBonus: " + this.bonus
				+ "\nOsnova: " + this.plata + "\nPlata: " + this.izraunajPlatu();
	}
}
