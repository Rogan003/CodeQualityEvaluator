package model;

import entity.Klijent;
import manage.KlijentManager;

public class KlijentModel extends MyModel{
	private static final long serialVersionUID = 1L;
	private KlijentManager kliMng;
	
	public KlijentModel(KlijentManager kliMng)
	{
		super(new String[]{"ID","Ime","Prezime","Korisnicko ime","Lozinka","Pol","Broj telefona", "Adresa","Kartica lojalnosti"
				,"Potrosio"});
		this.kliMng = kliMng;
	}
	
	@Override
	public int getRowCount() {
		return this.kliMng.getKlijenti().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Klijent klijent = this.kliMng.getKlijenti().get(rowIndex);
		
		switch(columnIndex)
		{
		case 0:
			return klijent.getId();
		case 1:
			return klijent.getIme();
		case 2:
			return klijent.getPrezime();
		case 3:
			return klijent.getKorisnickoIme();
		case 4:
			return klijent.getLozinka();
		case 5:
			return klijent.getPol();
		case 6:
			return klijent.getTelefon();
		case 7:
			return klijent.getAdresa();
		case 8:
			if(klijent.hasKarticaLojalnosti())
			{
				return "Ima";
			}
			else
			{
				return "Nema";
			}
		case 9:
			return klijent.getPotrosio();
		default:
			return null;
		}
	}

}
