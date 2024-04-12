package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Kozmeticar;
import manage.KozmeticarManager;
import manage.ManagerFactory;
import model.ZaposleniModel;
import view.forms.KozmeticariForm;

public class KozmeticarTableFrame extends TableFrame{

	private static final long serialVersionUID = 1L;
	
	private KozmeticarManager kozMng;
	private ManagerFactory managers;
	
	public KozmeticarTableFrame(ManagerFactory managers, KozmeticarManager kozMng)
	{
		super("Kozmeticari",new ZaposleniModel(kozMng.getKozmeticariZaposleni()));
		this.kozMng = kozMng;
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
					Kozmeticar k = KozmeticarTableFrame.this.kozMng.pronadjiPoId(id);
					if(k != null) {
						KozmeticariForm kf = new KozmeticariForm(managers,k);
						kf.init();
						kf.prikazi();
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
					Kozmeticar k = KozmeticarTableFrame.this.kozMng.pronadjiPoId(id);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete kozmeticara?", 
								k.getIme() + " "+k.getPrezime() +" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							KozmeticarTableFrame.this.kozMng.remove(k.getId());
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog kozmeticara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
