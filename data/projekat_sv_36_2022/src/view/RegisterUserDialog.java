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
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class RegisterUserDialog extends RegisterDialog {
	private boolean manager;
	
	public RegisterUserDialog(ManagerFactory managers, Korisnik korisnik, boolean manager) {
		super(managers,korisnik);
		this.manager = manager;
		if(korisnik != null)
		{
			super.setTitle("Izmenite korisnika");
		}
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void initRegisterGUI(JDialog dialog) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		dialog.setLayout(layout);

		JTextField tfKorisnickoIme;
		JPasswordField pfLozinka;
		JTextField tfIme;
		JTextField tfPrezime;
		JTextField tfPol;
		JTextField tfBrojTelefona;
		JTextField tfAdresa;
		
		if(this.korisnik == null)
		{
			tfKorisnickoIme = new JTextField(20);
			pfLozinka = new JPasswordField(20);
			tfIme = new JTextField(20);
			tfPrezime = new JTextField(20);
			tfPol = new JTextField(20);
			tfBrojTelefona = new JTextField(20);
			tfAdresa = new JTextField(20);
		}
		else
		{
			tfKorisnickoIme = new JTextField(korisnik.getKorisnickoIme());
			pfLozinka = new JPasswordField(korisnik.getLozinka());
			tfIme = new JTextField(korisnik.getIme());
			tfPrezime = new JTextField(korisnik.getPrezime());
			tfPol = new JTextField(korisnik.getPol());
			tfBrojTelefona = new JTextField(korisnik.getTelefon());
			tfAdresa = new JTextField(korisnik.getAdresa());
		}
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		// Ako postavimo dugme 'btnOK' kao defaul button, onda ce svaki pritisak tastera
		// Enter na tastaturi
		// Izazvati klik na njega
		dialog.getRootPane().setDefaultButton(btnOk);

		if(this.manager)
		{
			if(this.korisnik == null)
			{
				dialog.add(new JLabel("Dodaj klijenta"), "span 2");
			}
			else
			{
				dialog.add(new JLabel("Izmenite klijenta"),"span 2");
			}
		}
		else
		{
			dialog.add(new JLabel("Dobrodošli. Molimo da se registrujete."), "span 2");
		}
		dialog.add(new JLabel("Korisničko ime"));
		dialog.add(tfKorisnickoIme);
		dialog.add(new JLabel("Šifra"));
		dialog.add(pfLozinka);
		dialog.add(new JLabel("Ime"));
		dialog.add(tfIme);
		dialog.add(new JLabel("Prezime"));
		dialog.add(tfPrezime);
		dialog.add(new JLabel("Pol"));
		dialog.add(tfPol);
		dialog.add(new JLabel("Broj telefona"));
		dialog.add(tfBrojTelefona);
		dialog.add(new JLabel("Adresa"));
		dialog.add(tfAdresa);
		dialog.add(new JLabel());
		dialog.add(btnOk, "split 2");
		dialog.add(btnCancel);
		
		// Klik na Register dugme
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = tfKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword()).trim();
				String ime = tfIme.getText().trim();
				String prezime = tfPrezime.getText().trim();
				String pol = tfPol.getText().trim();
				String brojTelefona = tfBrojTelefona.getText().trim();
				String adresa = tfAdresa.getText().trim();
				if(korisnickoIme.equals("") || lozinka.equals("") || ime.equals("") || prezime.equals("")
						|| pol.equals("") || brojTelefona.equals("") || adresa.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
				}
				else
				{
					if(RegisterUserDialog.this.korisnik == null)
					{
						RegisterUserDialog.this.korisnik = managers.registrujKlijenta(ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka);				
					}
					else
					{
						managers.getKlijentMng().edit(RegisterUserDialog.this.korisnik.getId()
								, ((Klijent) RegisterUserDialog.this.korisnik).getPotrosio(), 
								((Klijent) RegisterUserDialog.this.korisnik).hasKarticaLojalnosti(), 
								((Klijent) RegisterUserDialog.this.korisnik).getTretmani(),
								ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka);
					}
					dialog.setVisible(false);
					dialog.dispose();
					// Prikazemo glavni prozor
					if(!RegisterUserDialog.this.manager)
					{
						KlijentDialog kliDialog = new KlijentDialog((Klijent) RegisterUserDialog.this.korisnik, RegisterUserDialog.this.managers);
						kliDialog.prikazi();
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
