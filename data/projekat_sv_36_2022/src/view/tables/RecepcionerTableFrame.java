package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Recepcioner;
import manage.ManagerFactory;
import manage.RecepcionerManager;
import model.ZaposleniModel;
import view.forms.ZaposleniForm;

public class RecepcionerTableFrame extends TableFrame{

	private static final long serialVersionUID = 1L;
	
	private RecepcionerManager recMng;
	private ManagerFactory managers;
	
	public RecepcionerTableFrame(ManagerFactory managers, RecepcionerManager recMng)
	{
		super("Recepcioneri",new ZaposleniModel(recMng.getRecepcioneriZaposleni()));
		this.recMng = recMng;
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
					Recepcioner k = RecepcionerTableFrame.this.recMng.pronadjiPoId(id);
					if(k != null) {
						ZaposleniForm zf = new ZaposleniForm(managers,k,false);
						zf.init();
						zf.prikazi();
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
					Recepcioner k = RecepcionerTableFrame.this.recMng.pronadjiPoId(id);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete recepcionera?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							RecepcionerTableFrame.this.recMng.remove(k.getId());
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog recepcionera!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
