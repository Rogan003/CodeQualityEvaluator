package entity;

public class Menadzer extends Zaposleni implements ToFile{
	public Menadzer(Sprema sprema, int staz, int bonus, int plata,
			String ime, String prezime, String pol, String telefon, String adresa, String korisnickoIme, String lozinka)
	{
		super(sprema,staz,bonus,plata,ime,prezime,pol,telefon,adresa,korisnickoIme,lozinka);
	}
	
	public void setIznosZaKarticu(int iznos)
	{
		Klijent.iznosZaKarticu = iznos;
	}
	
	public void postaviIznosZaBonus(int iznos)
	{
		Kozmeticar.bonusPoTretmanu = iznos;
	}
	
	@Override
	public String toFile()
	{
		return this.id + ";" + this.ime + ";" + this.prezime + ";" + this.pol + ";" + this.telefon + ";"
				+ this.adresa + ";" + this.korisnickoIme + ";" + this.lozinka + ";" + this.sprema + ";"
				+ this.staz + ";" + this.bonus + ";" + this.plata;
	}
}
