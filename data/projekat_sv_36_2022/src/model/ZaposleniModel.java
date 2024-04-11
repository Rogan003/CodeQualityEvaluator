package model;

import java.util.ArrayList;

import entity.Zaposleni;

public class ZaposleniModel extends MyModel{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Zaposleni> zaposleni; // da li prikazati sve ili samo finalnu platu?
	
	public ZaposleniModel(ArrayList<Zaposleni> zaposleni)
	{
		super(new String[]{"ID","Ime","Prezime","Korisnicko ime","Lozinka","Pol","Broj telefona","Adresa",
			"Sprema","Staz","Bonus","Osnova","Plata"});
		this.zaposleni = zaposleni;
	}
	
	@Override
	public int getRowCount() {
		return this.zaposleni.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Zaposleni zaposlen = this.zaposleni.get(rowIndex);
		
		switch(columnIndex)
		{
		case 0:
			return zaposlen.getId();
		case 1:
			return zaposlen.getIme();
		case 2:
			return zaposlen.getPrezime();
		case 3:
			return zaposlen.getKorisnickoIme();
		case 4:
			return zaposlen.getLozinka();
		case 5:
			return zaposlen.getPol();
		case 6:
			return zaposlen.getTelefon();
		case 7:
			return zaposlen.getAdresa();
		case 8:
			return zaposlen.getSprema();
		case 9:
			return zaposlen.getStaz();
		case 10:
			return zaposlen.getBonus();
		case 11:
			return zaposlen.getPlata();
		case 12:
			return zaposlen.izraunajPlatu();
		default:
			return null;
		}
	}
}
