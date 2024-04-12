package model;

import java.util.ArrayList;

import entity.KozmetickaUsluga;
import manage.CenovnikManager;

public class CenovnikModel extends MyModel{

	private static final long serialVersionUID = 1L;
	
	private CenovnikManager cenovnikMng;
	
	public CenovnikModel(CenovnikManager cenovnikMng)
	{
		super(new String[]{"Usluga ID","Usluga","Cena"});
		this.cenovnikMng = cenovnikMng;
	}
	
	@Override
	public int getRowCount() {
		return this.cenovnikMng.getCenovnik().getCenovnik().size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) { // da li ce da radi uopste?
		ArrayList<KozmetickaUsluga> usluge = new ArrayList<KozmetickaUsluga>(this.cenovnikMng.getCenovnik().getCenovnik().keySet());
		KozmetickaUsluga usluga = usluge.get(rowIndex);
		
		switch(columnIndex)
		{
		case 0:
			return usluga.getId();
		case 1:
			return usluga.getPodtip();
		case 2:
			return this.cenovnikMng.getCenovnik().getCenovnik().get(usluga);
		default:
			return null;
		}
	}

}
