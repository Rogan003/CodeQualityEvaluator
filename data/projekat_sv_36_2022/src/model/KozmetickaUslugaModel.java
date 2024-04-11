package model;

import entity.KozmetickaUsluga;
import manage.KozmetickaUslugaManager;

public class KozmetickaUslugaModel extends MyModel{

	private static final long serialVersionUID = 1L;
	
	private KozmetickaUslugaManager uslugaMng;
	
	public KozmetickaUslugaModel(KozmetickaUslugaManager uslugaMng)
	{
		super(new String[] {"ID","Tip","Podtip","Trajanje"});
		this.uslugaMng = uslugaMng;
	}
	
	@Override
	public int getRowCount() {
		return this.uslugaMng.getUsluge().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		KozmetickaUsluga usluga = this.uslugaMng.getUsluge().get(rowIndex);
		
		switch(columnIndex)
		{
		case 0:
			return usluga.getId();
		case 1:
			return usluga.getTip().getNaziv();
		case 2:
			return usluga.getPodtip();
		case 3:
			return usluga.getTrajanje();
		default:
			return null;
		}
	}
}
