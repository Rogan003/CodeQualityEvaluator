package view.tables;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import model.MyModel;

public abstract class TableFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	protected JToolBar mainToolbar = new JToolBar();
	protected JButton btnAdd = new JButton();
	protected JButton btnEdit = new JButton();
	protected JButton btnDelete = new JButton();
	protected JTextField tfSearch = new JTextField(20);
	protected JTable table;
	protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	protected MyModel model;
	private boolean filter = false;

	public TableFrame(String naziv, MyModel model)
	{
		setTitle(naziv);	
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.model = model;
		if(naziv == "Zakazani kozmeticki tretmani")
		{
			this.filter = true;
		}
		this.init();
	}
	
	protected void init()
	{
		// toolbar
				ImageIcon addIcon = new ImageIcon("img/add.png");		
				ImageIcon scaled = new ImageIcon(addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
				addIcon = scaled;
				btnAdd.setIcon(addIcon);
				mainToolbar.add(btnAdd);
				ImageIcon editIcon = new ImageIcon("img/edit.gif");
				btnEdit.setIcon(editIcon);
				mainToolbar.add(btnEdit);
				ImageIcon deleteIcon = new ImageIcon("img/remove.gif");
				btnDelete.setIcon(deleteIcon);
				mainToolbar.add(btnDelete);
				mainToolbar.setFloatable(false);		
				add(mainToolbar, BorderLayout.NORTH);
				
				// table
				table = new JTable(this.model);		
				table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setReorderingAllowed(false);
				// podesavanje manuelnog sortera tabele, potrebno i za pretragu
				tableSorter.setModel((AbstractTableModel) table.getModel());
				table.setRowSorter(tableSorter);
				JScrollPane sc = new JScrollPane(table);
				add(sc, BorderLayout.CENTER);
				
				//search
				JPanel pSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
				pSearch.setBackground(Color.yellow);
				if(this.filter)
				{
					JTextField tfTretman = new JTextField(10);
					JTextField tfTip = new JTextField(10);
					JTextField tfCena1 = new JTextField(10);
					JTextField tfCena2 = new JTextField(10);
					
					pSearch.add(new JLabel("Tretman: "));
					pSearch.add(tfTretman);
					pSearch.add(new JLabel("Tip: "));
					pSearch.add(tfTip);
					pSearch.add(new JLabel("Cena(min): "));
					pSearch.add(tfCena1);
					pSearch.add(new JLabel("Cena(max): "));
					pSearch.add(tfCena2);
					add(pSearch, BorderLayout.SOUTH);
					
					tfTretman.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void changedUpdate(DocumentEvent e) {
							ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
							if(tfTretman.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTretman.getText().trim(),1));
							}
							if(tfTip.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTip.getText().trim(),2));
							}
							if(tfCena1.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, Double.parseDouble(tfCena1.getText().trim()) - 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);

								}
							}
							if(tfCena2.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, Double.parseDouble(tfCena2.getText().trim()) + 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);
								}
							}
							
							RowFilter filter = RowFilter.andFilter(filters);
							
							tableSorter.setRowFilter(filter);
						}
					});
					
					tfTip.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void changedUpdate(DocumentEvent e) {
							ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
							if(tfTretman.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTretman.getText().trim(),1));
							}
							if(tfTip.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTip.getText().trim(),2));
							}
							if(tfCena1.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, Double.parseDouble(tfCena1.getText().trim()) - 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);

								}
							}
							if(tfCena2.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, Double.parseDouble(tfCena2.getText().trim()) + 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);
								}
							}
							
							RowFilter filter = RowFilter.andFilter(filters);
							
							tableSorter.setRowFilter(filter);
						}
					});
					
					tfCena1.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void changedUpdate(DocumentEvent e) {
							ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
							if(tfTretman.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTretman.getText().trim(),1));
							}
							if(tfTip.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTip.getText().trim(),2));
							}
							if(tfCena1.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, Double.parseDouble(tfCena1.getText().trim()) - 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);

								}
							}
							if(tfCena2.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, Double.parseDouble(tfCena2.getText().trim()) + 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);
								}
							}
							
							RowFilter filter = RowFilter.andFilter(filters);
							
							tableSorter.setRowFilter(filter);
						}
					});

					tfCena2.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void changedUpdate(DocumentEvent e) {
							ArrayList<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
							if(tfTretman.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTretman.getText().trim(),1));
							}
							if(tfTip.getText().trim().length() != 0)
							{
								filters.add(RowFilter.regexFilter("(?i)" + tfTip.getText().trim(),2));
							}
							if(tfCena1.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, Double.parseDouble(tfCena1.getText().trim()) - 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);

								}
							}
							if(tfCena2.getText().trim().length() != 0)
							{
								try
								{
									filters.add(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, Double.parseDouble(tfCena2.getText().trim()) + 1, 6));
								}
								catch(Exception exp)
								{
									JOptionPane.showMessageDialog(null, "Cena mora biti broj!", "Greska", JOptionPane.ERROR_MESSAGE);
								}
							}
							
							RowFilter filter = RowFilter.andFilter(filters);
							
							tableSorter.setRowFilter(filter);
						}
					});
				}
				else
				{
					pSearch.add(new JLabel("Search:"));
					pSearch.add(tfSearch);
					
					add(pSearch, BorderLayout.SOUTH);
					
					tfSearch.getDocument().addDocumentListener(new DocumentListener() {
						
						@Override
						public void removeUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void insertUpdate(DocumentEvent e) {
							changedUpdate(e);
						}
						
						@Override
						public void changedUpdate(DocumentEvent e) {
							//System.out.println("~ "+tfSearch.getText());
							if (tfSearch.getText().trim().length() == 0) {
							     tableSorter.setRowFilter(null);
							  } else {
								  tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
							  }
						}
					});
				}

				initActions();
	}
	
	// potrebno osvezavanje podataka u tabeli bez gasenja prozora
		public void refreshData() {
			this.model.fireTableDataChanged();
		}
	
	protected abstract void initActions();
}
