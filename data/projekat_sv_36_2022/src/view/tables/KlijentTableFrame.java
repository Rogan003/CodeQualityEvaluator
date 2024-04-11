package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Klijent;
import manage.KlijentManager;
import manage.ManagerFactory;
import model.KlijentModel;
import view.RegisterUserDialog;

public class KlijentTableFrame extends TableFrame{

	private static final long serialVersionUID = 1L;
	
	private KlijentManager kliMng;
	private ManagerFactory managers;
	
	public KlijentTableFrame(ManagerFactory managers, KlijentManager kliMng)
	{
		super("Klijenti", new KlijentModel(kliMng));
		this.kliMng = kliMng;
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
					Klijent k = KlijentTableFrame.this.kliMng.pronadjiPoId(id);
					if(k != null) {
						RegisterUserDialog rud = new RegisterUserDialog(managers,k,true);
						rud.init();
						rud.prikazi();
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
					Klijent k = KlijentTableFrame.this.kliMng.pronadjiPoId(id);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete klijenta?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							KlijentTableFrame.this.kliMng.remove(k.getId());
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog klijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
