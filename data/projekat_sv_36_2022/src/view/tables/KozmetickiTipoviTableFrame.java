package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.TipKozmetickogTretmana;
import manage.ManagerFactory;
import manage.TipKozmetickogTretmanaManager;
import model.TipKozmetickogTretmanaModel;
import view.forms.TipForm;

public class KozmetickiTipoviTableFrame extends TableFrame{

	private static final long serialVersionUID = 1L;
	
	private TipKozmetickogTretmanaManager tipMng;
	private ManagerFactory managers;
	
	public KozmetickiTipoviTableFrame(ManagerFactory managers,TipKozmetickogTretmanaManager tipMng)
	{
		super("Tipovi kozmetickih tretmana", new TipKozmetickogTretmanaModel(tipMng));
		this.tipMng = tipMng;
		this.managers = managers;
	}
	
	protected void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//StudentAddEditDialog add = new StudentAddEditDialog(StudentTableFrame.this, studentMng, null);
				//add.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					TipKozmetickogTretmana k = KozmetickiTipoviTableFrame.this.tipMng.pronadjiPoId(id);
					if(k != null) {
						TipForm tipForm = new TipForm(managers,k);
						tipForm.init();
						tipForm.prikazi();
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani artikal!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = table.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id = Integer.parseInt(table.getValueAt(red, 0).toString());
					TipKozmetickogTretmana k = KozmetickiTipoviTableFrame.this.tipMng.pronadjiPoId(id);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete tip?", 
								k.getNaziv() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							KozmetickiTipoviTableFrame.this.tipMng.remove(k.getId());
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani tip!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
