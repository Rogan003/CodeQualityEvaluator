package model;

import entity.TipKozmetickogTretmana;
import manage.TipKozmetickogTretmanaManager;

public class TipKozmetickogTretmanaModel extends MyModel{

	private static final long serialVersionUID = 1L;
	
	private TipKozmetickogTretmanaManager tipMng;
	
	public TipKozmetickogTretmanaModel(TipKozmetickogTretmanaManager tipMng)
	{
		super(new String[]{"ID","Naziv"});
		this.tipMng = tipMng;
	}
	
	@Override
	public int getRowCount() {
		return this.tipMng.getTipovi().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TipKozmetickogTretmana tip = this.tipMng.getTipovi().get(rowIndex);
		
		switch(columnIndex)
		{
		case 0:
			return tip.getId();
		case 1:
			return tip.getNaziv();
		default:
			return null;
		}
	}
}
