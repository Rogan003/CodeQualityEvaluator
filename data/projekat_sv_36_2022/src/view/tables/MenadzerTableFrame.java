package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Menadzer;
import manage.ManagerFactory;
import manage.MenadzerManager;
import model.ZaposleniModel;
import view.forms.ZaposleniForm;

public class MenadzerTableFrame extends TableFrame{
	private static final long serialVersionUID = 1L;
	
	private MenadzerManager menMng;
	private ManagerFactory managers;
	
	public MenadzerTableFrame(ManagerFactory managers, MenadzerManager menMng)
	{
		super("Menadzeri",new ZaposleniModel(menMng.getMenadzeriZaposleni()));
		this.menMng = menMng;
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
					Menadzer k = MenadzerTableFrame.this.menMng.pronadjiPoId(id);
					if(k != null) {
						ZaposleniForm zf = new ZaposleniForm(managers,k,true);
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
					Menadzer k = MenadzerTableFrame.this.menMng.pronadjiPoId(id);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete menadzera?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							MenadzerTableFrame.this.menMng.remove(k.getId());
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog menadzera!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
}
