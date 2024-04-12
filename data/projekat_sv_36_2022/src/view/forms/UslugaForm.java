package view.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.KozmetickaUsluga;
import entity.TipKozmetickogTretmana;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class UslugaForm extends JDialog{

	private static final long serialVersionUID = 1L;
	
	protected ManagerFactory managers;
	protected boolean inited;
	private KozmetickaUsluga staraUsluga = null;
	
	public UslugaForm(ManagerFactory managers)
	{
		this.managers = managers;
		this.inited = false;
	}
	
	public UslugaForm(ManagerFactory managers, KozmetickaUsluga staraUsluga)
	{
		this.managers = managers;
		this.inited = false;
		this.staraUsluga = staraUsluga;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			if(this.staraUsluga == null)
			{
				this.setTitle("Dodaj uslugu");
			}
			else
			{
				this.setTitle("Izmeni uslugu");
			}
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			initTipForm(this);
			this.pack();
			this.inited = true;
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
	
	public void initTipForm(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		dialog.setLayout(layout);
		
		JTextField tfNaziv;
		JTextField tfTrajanje;
		JTextField tfCena;
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		JComboBox cbTipovi = new JComboBox(this.managers.getTipMng().getTipovi().toArray());
		if(this.staraUsluga == null)
		{
			tfNaziv = new JTextField(20);
			tfTrajanje = new JTextField(20);
			tfCena = new JTextField(20);
		}
		else
		{
			tfNaziv = new JTextField(this.staraUsluga.getPodtip());
			tfTrajanje = new JTextField(Integer.toString(this.staraUsluga.getTrajanje()));
			tfCena = new JTextField(Double.toString(this.managers.getCenovnikMng().pronadjiCenu(this.staraUsluga)));
			int index = 0;
			for(TipKozmetickogTretmana tip : this.managers.getTipMng().getTipovi())
			{
				if(tip.getId() == this.staraUsluga.getTip().getId())
				{
					break;
				}
				index++;
			}
			cbTipovi.setSelectedIndex(index);
		}
		
		
		dialog.getRootPane().setDefaultButton(btnOk);
		
		if(this.staraUsluga == null)
		{
			dialog.add(new JLabel("Dodaj uslugu"), "span 2");
		}
		else
		{
			dialog.add(new JLabel("Izmeni uslugu"), "span 2");
		}
		dialog.add(new JLabel("Naziv podtipa: "));
		dialog.add(tfNaziv);
		dialog.add(new JLabel("Trajanje(u minutima): "));
		dialog.add(tfTrajanje);
		dialog.add(new JLabel("Cena: "));
		dialog.add(tfCena);
		dialog.add(new JLabel("Izaberite tip tretmana: "));
		dialog.add(cbTipovi);
		dialog.add(btnOk);
		dialog.add(btnCancel);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String podtip = tfNaziv.getText().trim();
				String trajanje = tfTrajanje.getText().trim();
				String cena = tfCena.getText().trim();
				
				TipKozmetickogTretmana tip = UslugaForm.this.managers.getTipMng().getTipovi().get(cbTipovi.getSelectedIndex());
				
				if(podtip.equals("") || trajanje.equals("") || cena.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
				}
				else
				{
					try
					{
						int trajanjeUsluge = Integer.parseInt(trajanje);
						int cenaUsluge = Integer.parseInt(cena);
						if(trajanjeUsluge < 0 || cenaUsluge < 0)
						{
							throw new Exception();
						}
						
						if(UslugaForm.this.staraUsluga == null)
						{
							UslugaForm.this.managers.getUslugaMng().add(tip, trajanjeUsluge, podtip);
							UslugaForm.this.managers.getCenovnikMng().dodajNaCenovnik(
									UslugaForm.this.managers.getUslugaMng().pronadjiPoNazivu(podtip), cenaUsluge);
						}
						else
						{
							UslugaForm.this.managers.getUslugaMng().edit(UslugaForm.this.staraUsluga.getId(),
									tip, trajanjeUsluge, podtip);
							UslugaForm.this.managers.getCenovnikMng().izmeniSaCenovnika(staraUsluga, cenaUsluge);
						}
						dialog.setVisible(false);
						dialog.dispose();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "Cena i trajanje moraju biti pozitivni celi brojevi!");
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
