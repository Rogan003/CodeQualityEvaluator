package model;

import java.time.format.DateTimeFormatter;

import entity.KozmetickiTretman;
import manage.KozmetickiTretmanManager;

public class KozmetickiTretmanModel extends MyModel{

	private static final long serialVersionUID = 1L;
	
	private KozmetickiTretmanManager tretmanMng;

	public KozmetickiTretmanModel(KozmetickiTretmanManager tretmanMng)
	{
		super(new String[]{"ID","Usluga","Tip tretmana","Stanje","Termin","Trajanje","Cena"});
		this.tretmanMng = tretmanMng;
	}
	
	@Override
	public int getRowCount() {
		return this.tretmanMng.getTretmani().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		KozmetickiTretman tretman = this.tretmanMng.getTretmani().get(rowIndex);
		
		switch(columnIndex)
		{
		case 0:
			return tretman.getId();
		case 1:
			return tretman.getUsluga().getPodtip();
		case 2:
			return tretman.getUsluga().getTip().getNaziv();
		case 3:
			return tretman.getStanje();
		case 4:
			return tretman.getTermin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		case 5:
			return tretman.getUsluga().getTrajanje();
		case 6:
			return tretman.getCena();
		default:
			return null;
		}
	}
}
