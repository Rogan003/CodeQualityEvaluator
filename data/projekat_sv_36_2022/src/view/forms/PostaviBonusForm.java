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

public class PostaviBonusForm extends JDialog{

	private static final long serialVersionUID = 1L;
	
	protected ManagerFactory managers;
	protected boolean inited;
	
	public PostaviBonusForm(ManagerFactory managers)
	{
		this.managers = managers;
		this.inited = false;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			this.setTitle("Postavi bonus");
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
		
		JTextField tfIznos = new JTextField(10);
		JTextField tfBrojTretmana = new JTextField(10);
		JTextField tfZarada = new JTextField(10);
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		
		dialog.getRootPane().setDefaultButton(btnOk);
		
		dialog.add(new JLabel("Postavi bonus: "), "span 2");
		dialog.add(new JLabel("Iznos koji se dodeljuje: "));
		dialog.add(tfIznos);
		dialog.add(new JLabel("Broj tretmana potreban za bonus: "));
		dialog.add(tfBrojTretmana);
		dialog.add(new JLabel("Zarada potrebna za bonus: "));
		dialog.add(tfZarada);
		dialog.add(btnOk);
		dialog.add(btnCancel);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String iznos = tfIznos.getText().trim();
				String brojTretmana = tfBrojTretmana.getText().trim();
				String zarada = tfZarada.getText().trim();
				if(iznos.equals("") || brojTretmana.equals("") || zarada.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
				}
				else
				{
					try
					{
						int iznosInt = Integer.parseInt(iznos);
						int brojTretamanaInt = Integer.parseInt(brojTretmana);
						double zaradaDouble = Double.parseDouble(zarada);
						
						if(iznosInt < 0 || brojTretamanaInt < 0 || zaradaDouble < 0)
						{
							throw new Exception();
						}
						
						PostaviBonusForm.this.managers.postaviBonus(iznosInt, brojTretamanaInt, zaradaDouble);
						
						dialog.setVisible(false);
						dialog.dispose();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "Podaci nisu validni!");
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
