package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import entity.KozmetickiTretman;
import entity.StanjeKozmetickogTretmana;
import manage.KozmetickiTretmanManager;
import manage.ManagerFactory;
import model.KozmetickiTretmanModel;

public class KozmetickiTretmaniTableFrame extends TableFrame{

	private static final long serialVersionUID = 1L;
	
	private KozmetickiTretmanManager tretmanMng;
	private ManagerFactory managers;
	
	public KozmetickiTretmaniTableFrame(KozmetickiTretmanManager tretmanMng, ManagerFactory managers)
	{
		super("Zakazani kozmeticki tretmani", new KozmetickiTretmanModel(tretmanMng));
		this.tretmanMng = tretmanMng;
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
					KozmetickiTretman k = KozmetickiTretmaniTableFrame.this.tretmanMng.pronadjiPoId(id);
					if(k != null) {
						if(k.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN)
						{
							StanjeKozmetickogTretmana[] stanja = new StanjeKozmetickogTretmana[] {StanjeKozmetickogTretmana.IZVRSEN, StanjeKozmetickogTretmana.NIJE_SE_POJAVIO};
							StanjeKozmetickogTretmana stanje = (StanjeKozmetickogTretmana) JOptionPane.showInputDialog(null, "Izaberite stanje: ", "Promenite stanje",
									JOptionPane.PLAIN_MESSAGE,null, stanja, stanja[0]);
							
							if(stanje != null)
							{
								managers.getTretmanMng().edit(k.getId(), k.getUsluga(), stanje, k.getTermin(), k.getCena());
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Ovu opciju moguce je odraditi samo na zakazanim tretmanima!", "Greska", JOptionPane.ERROR_MESSAGE);

						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					KozmetickiTretman k = KozmetickiTretmaniTableFrame.this.tretmanMng.pronadjiPoId(id);
					if(k != null) {
						if(KozmetickiTretmaniTableFrame.this.managers == null)
						{
							JOptionPane.showMessageDialog(null, "Ovu opciju ne treba da koristi menadzer!", "Greska", JOptionPane.ERROR_MESSAGE);
						}
						else
						{ // testirati otkazivanje tretmana
							KozmetickiTretman otkazan = null;
							for(KozmetickiTretman tretman : KozmetickiTretmaniTableFrame.this.tretmanMng.getTretmani())
							{
								if(tretman.getId() == k.getId())
								{
									otkazan = tretman;
									break;
								}
							}
							
							if(otkazan.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN)
							{
								int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da otkazete tretman?", 
										k.getUsluga().getPodtip() + " za " + k.getTermin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
										+" - Potvrda otkazivanja", JOptionPane.YES_NO_OPTION);
								if(izbor == JOptionPane.YES_OPTION) {
									KozmetickiTretmaniTableFrame.this.managers.otkaziTretman(otkazan, false);
									refreshData();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Odabrani tretman je vec otkazan ili mu je termin prosao!", "Greska", JOptionPane.ERROR_MESSAGE);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani tretman!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
