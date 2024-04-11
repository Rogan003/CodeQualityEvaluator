package view.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class IzmeniSalon extends JDialog{

	private static final long serialVersionUID = 1L;
	
	protected ManagerFactory managers;
	protected boolean inited;
	
	public IzmeniSalon(ManagerFactory managers)
	{
		this.managers = managers;
		this.inited = false;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			this.setTitle("Izmena salona");
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			initSalonIzmena(this);
			this.pack();
			this.inited = true;
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
	
	public void initSalonIzmena(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		dialog.setLayout(layout);
		
		JTextField tfNaziv = new JTextField(this.managers.getSalonMng().getKozmetickiSalon().getNaziv());
		JTextField tfPocetak = new JTextField(Integer.toString(this.managers.getSalonMng().getKozmetickiSalon().getRadnoVreme()[0].getHour()));
		JTextField tfKraj = new JTextField(Integer.toString(this.managers.getSalonMng().getKozmetickiSalon().getRadnoVreme()[1].getHour()));
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		
		dialog.getRootPane().setDefaultButton(btnOk);
		
		dialog.add(new JLabel("Izmeni kozmeticki salon: "), "span 2");
		dialog.add(new JLabel("Naziv: "));
		dialog.add(tfNaziv);
		dialog.add(new JLabel("Radno vreme(pocetak i kraj u satima): "), "span 2");
		dialog.add(tfPocetak);
		dialog.add(tfKraj);
		dialog.add(btnOk);
		dialog.add(btnCancel);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = tfNaziv.getText().trim();
				String pocetak = tfPocetak.getText().trim();
				String kraj = tfKraj.getText().trim();
				if(naziv.equals("") || pocetak.equals("") || kraj.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
				}
				else
				{
					try
					{
						int pocetakrv = Integer.parseInt(pocetak);
						int krajrv = Integer.parseInt(kraj);
						if(pocetakrv < 0 || pocetakrv > 23 || krajrv < 1 || krajrv > 24)
						{
							throw new Exception();
						}
						
						IzmeniSalon.this.managers.getSalonMng().edit(naziv, LocalTime.of(pocetakrv, 0), LocalTime.of(krajrv, 0));
						
						dialog.setVisible(false);
						dialog.dispose();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "Pocetak i kraj radnog vremena nisu u ispravnom formatu(nisu validan broj sati)!");
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
