package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import entity.Klijent;
import entity.Kozmeticar;
import entity.KozmetickaUsluga;
import entity.StanjeKozmetickogTretmana;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class ZakazivanjeDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	private boolean inited;
	private Klijent klijent;
	private ArrayList<KozmetickaUsluga> kozUsluge;
	
	public ZakazivanjeDialog(ManagerFactory managers, Klijent klijent, ArrayList<KozmetickaUsluga> kozUsluge)
	{
		this.managers = managers;
		this.inited = false;
		this.klijent = klijent;
		this.kozUsluge = kozUsluge;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			this.setTitle("Zakazi tretman");
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			initZakazivanje(this);
			this.pack();
			this.inited = true;
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
	
	public void initZakazivanje(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		dialog.setLayout(layout);
		
		JTextField tfNaziv = new JTextField(20);
		
		if(kozUsluge == null)
		{
			kozUsluge = this.managers.getUslugaMng().getUsluge();
		}
		
		String [] usluge = new String[kozUsluge.size()];
		int index = 0;
		for(KozmetickaUsluga usluga : kozUsluge)
		{
			usluge[index] = usluga.getPodtip() + " " + this.managers.getCenovnikMng().pronadjiCenu(usluga) + " dinara "
					+ usluga.getTrajanje() + " minuta";
			index++;
		}
		JComboBox cbUsluge = new JComboBox(usluge);
		JButton izaberi = new JButton("Izaberi");
		JButton dodeli = new JButton("Dodeli i zakazi");
		JButton btnCancel = new JButton("Cancel");
		SpinnerDateModel dateModel = new SpinnerDateModel();
	    JSpinner spinner = new JSpinner(dateModel);
		
		dialog.add(new JLabel("Zakazite tretman: "),"span 2");
		if(this.klijent == null)
		{
			dialog.add(new JLabel("Unesite korisnicko ime klijenta: "));
			dialog.add(tfNaziv);
		}
		dialog.add(new JLabel("Izaberite kozmeticku uslugu: "));
		dialog.add(cbUsluge);
		dialog.add(new JLabel("Izaberite datum i vreme: "));
		dialog.add(spinner);
		dialog.add(new JLabel("Izaberite kozmeticara: "));
		dialog.add(izaberi);
		dialog.add(new JLabel("Automatski dodeli kozmeticara i zakazi: "));
		dialog.add(dodeli);
		dialog.add(btnCancel);
		
		izaberi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kli = tfNaziv.getText().trim();
				if(ZakazivanjeDialog.this.klijent == null)
				{
					ZakazivanjeDialog.this.klijent = ZakazivanjeDialog.this.managers.getKlijentMng().pronadjiPoKorisnickomImenu(kli);
				}
				
				KozmetickaUsluga usluga = ZakazivanjeDialog.this.managers.getUslugaMng().getUsluge().get(cbUsluge.getSelectedIndex());
				
				LocalDateTime termin = LocalDateTime.ofInstant(((Date) spinner.getValue()).toInstant(), ZoneId.systemDefault());
				
				if(ZakazivanjeDialog.this.klijent == null || termin.isBefore(LocalDateTime.now()))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke ili su neispravni!");
				}
				else
				{
					ArrayList<Kozmeticar> dostupni = new ArrayList<Kozmeticar>();
					for(Kozmeticar kozmeticar : ZakazivanjeDialog.this.managers.getKozmeticarMng().getKozmeticari())
					{
						if(kozmeticar.getObuceniTipovi().contains(usluga.getTip()) && !kozmeticar.getRasporedZauzetih().contains(termin))
						{
							dostupni.add(kozmeticar);
						}
					}
					Kozmeticar kozmeticarRad = (Kozmeticar) JOptionPane.showInputDialog(null, "Izaberite kozmeticara: ", "Izaberite kozmeticara",
							JOptionPane.PLAIN_MESSAGE,null, dostupni.toArray(), dostupni.toArray()[0]);
					boolean isOkay = ZakazivanjeDialog.this.managers.zakaziTretman(klijent, kozmeticarRad, usluga,
							StanjeKozmetickogTretmana.ZAKAZAN, termin,
							ZakazivanjeDialog.this.managers.getCenovnikMng().pronadjiCenu(usluga));
					if(isOkay)
					{
						JOptionPane.showMessageDialog(null, "Uspesno zakazan tretman!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nije moguce zakazati tretman u ovom terminu!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}					
			}
		});
		
		dodeli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kli = tfNaziv.getText().trim();
				if(ZakazivanjeDialog.this.klijent == null)
				{
					ZakazivanjeDialog.this.klijent = ZakazivanjeDialog.this.managers.getKlijentMng().pronadjiPoKorisnickomImenu(kli);
				}
				
				KozmetickaUsluga usluga = ZakazivanjeDialog.this.managers.getUslugaMng().getUsluge().get(cbUsluge.getSelectedIndex());
				
				LocalDateTime termin = LocalDateTime.ofInstant(((Date) spinner.getValue()).toInstant(), ZoneId.systemDefault());
				
				if(ZakazivanjeDialog.this.klijent == null || termin.isBefore(LocalDateTime.now()))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke ili su neispravni!");
				}
				else
				{
					boolean isOkay = ZakazivanjeDialog.this.managers.zakaziTretman(klijent, null, usluga,
							StanjeKozmetickogTretmana.ZAKAZAN, termin,
							ZakazivanjeDialog.this.managers.getCenovnikMng().pronadjiCenu(usluga));
					if(isOkay)
					{
						JOptionPane.showMessageDialog(null, "Uspesno zakazan tretman!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Nije moguce zakazati tretman u ovom terminu!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}					
			}
		});

		// Cancel dugme samo sakriva trenutni prozor
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		
	}

}
