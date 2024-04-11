package model;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	protected String[] columnNames;
	
	public MyModel(String [] columnNames)
	{
		this.columnNames = columnNames;
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}
	
	@Override
	public String getColumnName(int column)
	{
		return this.columnNames[column];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return this.getValueAt(0,columnIndex).getClass();
	}

	@Override
	public int getRowCount() {
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

}
