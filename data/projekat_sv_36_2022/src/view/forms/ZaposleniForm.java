package view.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.Korisnik;
import entity.Sprema;
import entity.Zaposleni;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import view.RegisterDialog;

public class ZaposleniForm extends RegisterDialog{

	private static final long serialVersionUID = 1L;
	
	private boolean manager;
	
	public ZaposleniForm(ManagerFactory managers, Korisnik korisnik, boolean manager)
	{
		super(managers,korisnik);
		this.manager = manager;
		if(manager)
		{
			if(korisnik == null)
			{
				super.setTitle("Dodajte menadzera");
			}
			else
			{
				super.setTitle("Izmenite menadzera");
			}
		}
		else
		{
			if(korisnik == null)
			{
				super.setTitle("Dodajte recepcionera");
			}
			else
			{
				super.setTitle("Izmenite recepcionera");
			}
		}
	}
	
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
		JTextField tfStaz;
		JTextField tfOsnova;
		String [] spremaEnum = {"Niska", "Ispod proseka", "Prosecna", "Iznad proseka", "Visoka"};
		JComboBox cbSprema= new JComboBox(spremaEnum);
		
		if(this.korisnik == null)
		{
			tfKorisnickoIme = new JTextField(20);
			pfLozinka = new JPasswordField(20);
			tfIme = new JTextField(20);
			tfPrezime = new JTextField(20);
			tfPol = new JTextField(20);
			tfBrojTelefona = new JTextField(20);
			tfAdresa = new JTextField(20);
			tfStaz = new JTextField(20);
			tfOsnova = new JTextField(20);
		}
		else
		{
			tfKorisnickoIme = new JTextField(this.korisnik.getKorisnickoIme());
			pfLozinka = new JPasswordField(this.korisnik.getLozinka());
			tfIme = new JTextField(this.korisnik.getIme());
			tfPrezime = new JTextField(this.korisnik.getPrezime());
			tfPol = new JTextField(this.korisnik.getPol());
			tfBrojTelefona = new JTextField(this.korisnik.getTelefon());
			tfAdresa = new JTextField(this.korisnik.getAdresa());
			tfStaz = new JTextField(Integer.toString(((Zaposleni) this.korisnik).getStaz()));
			tfOsnova = new JTextField(Integer.toString(((Zaposleni) this.korisnik).getPlata()));
			
			Sprema spremaZaposleni = ((Zaposleni) this.korisnik).getSprema();
			if(spremaZaposleni == Sprema.NISKA)
			{
				cbSprema.setSelectedIndex(0);
			}
			else if(spremaZaposleni == Sprema.ISPOD_PROSEKA)
			{
				cbSprema.setSelectedIndex(1);
			}
			else if(spremaZaposleni == Sprema.PROSECNA)
			{
				cbSprema.setSelectedIndex(2);
			}
			else if(spremaZaposleni == Sprema.IZNAD_PROSEKA)
			{
				cbSprema.setSelectedIndex(3);
			}
			else if(spremaZaposleni == Sprema.VISOKA)
			{
				cbSprema.setSelectedIndex(4);
			}
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
				dialog.add(new JLabel("Dodaj menadzera"), "span 2");
			}
			else
			{
				dialog.add(new JLabel("Izmenite menadzera"), "span 2");
			}
		}
		else
		{
			if(this.korisnik == null)
			{
				dialog.add(new JLabel("Dodaj recepcionera"), "span 2");
			}
			else
			{
				dialog.add(new JLabel("Izmenite recepcionera"), "span 2");
			}
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
		dialog.add(new JLabel("Godine staza: "));
		dialog.add(tfStaz);
		dialog.add(new JLabel("Osnova: "));
		dialog.add(tfOsnova);
		dialog.add(new JLabel("Sprema: "));
		dialog.add(cbSprema);
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
				String staz = tfStaz.getText().trim();
				String osnova = tfOsnova.getText().trim();
				Sprema sprema = null;
				if(cbSprema.getSelectedIndex() == 0)
				{
					sprema = Sprema.NISKA;
				}
				else if(cbSprema.getSelectedIndex() == 1)
				{
					sprema = Sprema.ISPOD_PROSEKA;
				}
				else if(cbSprema.getSelectedIndex() == 2)
				{
					sprema = Sprema.PROSECNA;
				}
				else if(cbSprema.getSelectedIndex() == 3)
				{
					sprema = Sprema.IZNAD_PROSEKA;
				}
				else if(cbSprema.getSelectedIndex() == 4)
				{
					sprema = Sprema.VISOKA;
				}
				
				if(korisnickoIme.equals("") || lozinka.equals("") || ime.equals("") || prezime.equals("")
						|| pol.equals("") || brojTelefona.equals("") || adresa.equals("") || sprema == null || staz.equals("")
						|| osnova.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
				}
				else
				{
					try 
					{
						int stazInt = Integer.parseInt(staz);
						int osnovaInt = Integer.parseInt(osnova);
						
						if(stazInt < 0 || osnovaInt < 0)
						{
							throw new Exception();
						}
						
						if(ZaposleniForm.this.manager)
						{
							if(ZaposleniForm.this.korisnik == null)
							{
								managers.registrujMenadzera(sprema,stazInt,0,osnovaInt, ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka);				
							}
							else
							{
								ZaposleniForm.this.managers.getMenadzerMng().edit(ZaposleniForm.this.korisnik.getId(), sprema, 
										stazInt, ((Zaposleni) ZaposleniForm.this.korisnik).getBonus(),
										osnovaInt, ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka);
							}
						}
						else
						{
							if(ZaposleniForm.this.korisnik == null)
							{
								managers.registrujRecepcionera(sprema,stazInt,0,osnovaInt, ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka);				
							}
							else
							{
								ZaposleniForm.this.managers.getRecepcionerMng().edit(ZaposleniForm.this.korisnik.getId(), sprema,
										stazInt, ((Zaposleni) ZaposleniForm.this.korisnik).getBonus(), 
										osnovaInt, ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka);
							}
						}
						dialog.setVisible(false);
						dialog.dispose();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "Staz i osnova moraju biti pozitivni celi brojevi!");
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
