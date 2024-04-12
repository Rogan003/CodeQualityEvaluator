package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.KozmetickaUsluga;
import manage.KozmetickaUslugaManager;
import manage.ManagerFactory;
import model.KozmetickaUslugaModel;
import view.forms.UslugaForm;

public class KozmetickeUslugeTableFrame extends TableFrame{

	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	private KozmetickaUslugaManager uslugaMng;
	
	public KozmetickeUslugeTableFrame(ManagerFactory managers, KozmetickaUslugaManager uslugaMng)
	{
		super("Kozmeticke usluge", new KozmetickaUslugaModel(uslugaMng));
		this.uslugaMng = uslugaMng;
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
					KozmetickaUsluga k = KozmetickeUslugeTableFrame.this.uslugaMng.pronadjiPoId(id);
					if(k != null) {
						UslugaForm uf = new UslugaForm(managers,k);
						uf.init();
						uf.prikazi();
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
					KozmetickaUsluga k = KozmetickeUslugeTableFrame.this.uslugaMng.pronadjiPoId(id);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete uslugu?", 
								k.getPodtip() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							KozmetickeUslugeTableFrame.this.uslugaMng.remove(k.getId());
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu uslugu!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
