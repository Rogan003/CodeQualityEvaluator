package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import entity.TipKozmetickogTretmana;
import entity.Klijent;
import entity.KozmetickaUsluga;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class PretragaDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	private boolean inited;
	private Klijent klijent;
	
	public PretragaDialog(ManagerFactory managers, Klijent klijent){
			this.managers = managers;
			this.inited = false;
			this.klijent = klijent;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			this.setTitle("Zakazivanje tretmana - pretraga");
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			initLoginGUI(this);
			this.pack();
			this.inited = true;
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
	
	private void initLoginGUI(JDialog dialog) {
		MigLayout layout = new MigLayout("wrap 3", "[][]", "[]20[][]20[]");
		dialog.setLayout(layout);
		
		String [] tipovi = new String[this.managers.getTipMng().getTipovi().size()];
		
		int i = 0;
		for(TipKozmetickogTretmana tip : this.managers.getTipMng().getTipovi())
		{
			tipovi[i++] = tip.getNaziv();
		}
		
		JComboBox cbTipovi = new JComboBox(tipovi);
		JSpinner cenaDole = new JSpinner();
		JSpinner cenaGore = new JSpinner();
		JSpinner trajanjeDole = new JSpinner();
		JSpinner trajanjeGore = new JSpinner();
		JButton btnOk = new JButton("Pretrazi");
		JButton btnCancel = new JButton("Zakazivanje bez pretrage");

		dialog.getRootPane().setDefaultButton(btnOk);

		dialog.add(new JLabel("Unesite podatka za pretragu ili predjite direktno na zakazivanje: "), "span 3");
		dialog.add(new JLabel("Tip: "));
		dialog.add(cbTipovi, "span 2");
		dialog.add(new JLabel("Cena (min-max): "));
		dialog.add(cenaDole);
		dialog.add(cenaGore);
		dialog.add(new JLabel("Trajanje (min-max)"));
		dialog.add(trajanjeDole);
		dialog.add(trajanjeGore);
		dialog.add(btnOk, "split 2");
		dialog.add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<KozmetickaUsluga> kozUsluge = new ArrayList<KozmetickaUsluga>();
				
				for(KozmetickaUsluga usluga : managers.getUslugaMng().getUsluge())
				{
					if(usluga.getTip() == managers.getTipMng().getTipovi().get(cbTipovi.getSelectedIndex()) &&
							usluga.getTrajanje() >= (int) trajanjeDole.getValue() && usluga.getTrajanje() <= (int) trajanjeGore.getValue()
							&& managers.getCenovnikMng().pronadjiCenu(usluga) >= (int) cenaDole.getValue()
							&& managers.getCenovnikMng().pronadjiCenu(usluga) <= (int) cenaGore.getValue())
					{
						kozUsluge.add(usluga);
					}
				}
				
				dialog.setVisible(false);
				dialog.dispose();
				ZakazivanjeDialog zd = new ZakazivanjeDialog(PretragaDialog.this.managers, PretragaDialog.this.klijent,kozUsluge);
				zd.init();
				zd.prikazi();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				dialog.dispose();
				ZakazivanjeDialog zd = new ZakazivanjeDialog(PretragaDialog.this.managers, PretragaDialog.this.klijent,null);
				zd.init();
				zd.prikazi();
			}
		});

	}
}
