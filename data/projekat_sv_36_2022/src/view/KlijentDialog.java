package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import entity.Klijent;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class KlijentDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private Klijent klijent;
	private ManagerFactory managers;
	
	public KlijentDialog(Klijent klijent, ManagerFactory managers)
	{
		this.klijent = klijent;
		this.managers = managers;
		this.setTitle("Kozmeticki salon");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initKlijentDialog(this);
		this.pack();
	}
	
	public void initKlijentDialog(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][]");
		dialog.setLayout(layout);
		
		JButton zakaziTretman = new JButton("Zakazi");
		JButton prikaziTretmane = new JButton("Prikazi");
		JButton preuzmiKarticu = new JButton("Preuzmi");
		JProgressBar stanje = new JProgressBar();
		String ispis = "Stanje: " + this.klijent.getPotrosio() ;
		if(Klijent.iznosZaKarticu == -1) // cuvanje iznosa za karticu u fajlu!!
		{
			stanje.setValue(0);
		}
		else
		{
			stanje.setValue((int) ((this.klijent.getPotrosio() / Klijent.iznosZaKarticu) * 100));
			ispis = "Stanje: " + this.klijent.getPotrosio() + "/" + Klijent.iznosZaKarticu;
		}
		
		dialog.add(new JLabel("Klijentski prikaz"), "span 2");
		dialog.add(new JLabel("Zakazi tretman: "));
		dialog.add(zakaziTretman);
		dialog.add(new JLabel("Prikazi zakazane tretmane: ")); // ovde dodati otkazivanje
		dialog.add(prikaziTretmane);
		dialog.add(new JLabel("Kartica lojalnosti: "));
		dialog.add(preuzmiKarticu);
		dialog.add(new JLabel(ispis));
		dialog.add(stanje);
		
		preuzmiKarticu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int odgovor = KlijentDialog.this.managers.preuzmiKarticu(KlijentDialog.this.klijent);
				
				if(odgovor == -1)
				{
					JOptionPane.showMessageDialog(null, "Tretnutno nemamo opciju za karticu lojalnosti");
				}
				else if(odgovor == 0)
				{
					JOptionPane.showMessageDialog(null, "Niste dovoljno potrosili za karticu lojalnosti");
				}
				else if(odgovor == -2)
				{
					JOptionPane.showMessageDialog(null, "Vec imate karticu lojalnosti");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Uspesno ste preuzeli karticu lojalnosti");
				}
			}
		});
		
		prikaziTretmane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TretmaniPrikazFrame tf = new TretmaniPrikazFrame(KlijentDialog.this.klijent.getTretmani(), KlijentDialog.this.klijent,
						KlijentDialog.this.managers);
				tf.prikazi();
			}
		});
		
		zakaziTretman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PretragaDialog pd = new PretragaDialog(KlijentDialog.this.managers, KlijentDialog.this.klijent);
				pd.init();
				pd.prikazi();
			}
		});
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}

}
