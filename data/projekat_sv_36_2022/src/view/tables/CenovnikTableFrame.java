package view.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.KozmetickaUsluga;
import manage.CenovnikManager;
import manage.KozmetickaUslugaManager;
import model.CenovnikModel;


public class CenovnikTableFrame extends TableFrame{

	private static final long serialVersionUID = 1L;
	
	private CenovnikManager cenovnikMng;
	private KozmetickaUslugaManager uslugaMng;
	
	public CenovnikTableFrame(CenovnikManager cenovnikMng,KozmetickaUslugaManager uslugaMng)
	{
		super("Cenovnik", new CenovnikModel(cenovnikMng));
		this.cenovnikMng = cenovnikMng;
		this.uslugaMng = uslugaMng;
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
					KozmetickaUsluga k = CenovnikTableFrame.this.uslugaMng.pronadjiPoId(id);
					if(k != null) {
						try
						{
							String rez = JOptionPane.showInputDialog(null, "Unesite novu cenu za " + k.getPodtip() + ": ");
							if(rez != null)
							{
								int novaCena = Integer.parseInt(rez);
								if(novaCena < 0)
								{
									throw new NumberFormatException("cena");
								}
								CenovnikTableFrame.this.cenovnikMng.izmeniSaCenovnika(k, novaCena);
							}
						}
						catch(NumberFormatException exp)
						{
							JOptionPane.showMessageDialog(null, "Cena mora biti pozitivan ceo broj!", "Greska", JOptionPane.ERROR_MESSAGE);

						}
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
					KozmetickaUsluga k = CenovnikTableFrame.this.uslugaMng.pronadjiPoId(id);
					if(k != null) {
						int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da obrisete cenu za ovu uslugu?", 
								k.getPodtip() + " cena: " + CenovnikTableFrame.this.cenovnikMng.pronadjiCenu(k)
								+" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							CenovnikTableFrame.this.cenovnikMng.skiniSaCenovnika(k);
							refreshData();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu uslugu i cenu!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
}
