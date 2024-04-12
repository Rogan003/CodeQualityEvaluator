package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.Klijent;
import entity.Korisnik;
import entity.Kozmeticar;
import entity.Menadzer;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class LoginDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	private Korisnik korisnik;
	private boolean inited;
	
	public LoginDialog(ManagerFactory managers, Korisnik korisnik){
			this.korisnik = korisnik;
			this.managers = managers;
			this.inited = false;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			this.setTitle("Prijava");
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		dialog.setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		// Ako postavimo dugme 'btnOK' kao defaul button, onda ce svaki pritisak tastera
		// Enter na tastaturi
		// Izazvati klik na njega
		dialog.getRootPane().setDefaultButton(btnOk);

		dialog.add(new JLabel("Dobrodošli. Molimo da se prijavite."), "span 2");
		dialog.add(new JLabel("Korisničko ime"));
		dialog.add(tfKorisnickoIme);
		dialog.add(new JLabel("Šifra"));
		dialog.add(pfLozinka);
		dialog.add(new JLabel());
		dialog.add(btnOk, "split 2");
		dialog.add(btnCancel);

		// Klik na Login dugme
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = tfKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword()).trim();
				if(korisnickoIme.equals("") || lozinka.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
				}
				else
				{
					LoginDialog.this.korisnik = managers.login(korisnickoIme, lozinka);
					if(LoginDialog.this.korisnik == null)
					{
						JOptionPane.showMessageDialog(null, "Neispravni podaci!");
					}
					else
					{
						dialog.setVisible(false);
						dialog.dispose();
						// Prikazemo glavni prozor
						if(LoginDialog.this.korisnik instanceof Menadzer)
						{
							MenadzerDialog menDialog = new MenadzerDialog(LoginDialog.this.managers, (Menadzer) LoginDialog.this.korisnik);
							menDialog.prikazi();
						}
						else if(LoginDialog.this.korisnik instanceof Klijent)
						{
							KlijentDialog kliDialog = new KlijentDialog((Klijent) LoginDialog.this.korisnik, LoginDialog.this.managers);
							kliDialog.prikazi();
						}
						else if(LoginDialog.this.korisnik instanceof Kozmeticar)
						{
							KozmeticarDialog kozDialog = new KozmeticarDialog((Kozmeticar) LoginDialog.this.korisnik,
									LoginDialog.this.managers);
							kozDialog.prikazi();
						}
						else
						{
							RecepcionerDialog recDialog = new RecepcionerDialog(LoginDialog.this.managers.getTretmanMng(),
									LoginDialog.this.managers);
							recDialog.prikazi();
						}					
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
