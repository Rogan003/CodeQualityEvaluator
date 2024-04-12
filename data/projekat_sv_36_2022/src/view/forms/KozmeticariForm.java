package view.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import entity.Korisnik;
import entity.Sprema;
import entity.TipKozmetickogTretmana;
import entity.Zaposleni;
import entity.Kozmeticar;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import view.RegisterDialog;

public class KozmeticariForm extends RegisterDialog{

	private static final long serialVersionUID = 1L;
	
	public KozmeticariForm(ManagerFactory managers, Korisnik korisnik)
	{
		super(managers,korisnik);
		if(korisnik == null)
		{
			super.setTitle("Dodaj kozmeticara");
		}
		else
		{
			super.setTitle("Izmeni kozmeticara");
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
		ArrayList<JRadioButton> tipovi = new ArrayList<JRadioButton>();
		for(TipKozmetickogTretmana tip : this.managers.getTipMng().getTipovi())
		{
			tipovi.add(new JRadioButton(tip.getNaziv()));
		}
		
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
			
			int place = 0;
			for(TipKozmetickogTretmana tip : this.managers.getTipMng().getTipovi())
			{
				if(((Kozmeticar) this.korisnik).getObuceniTipovi().contains(tip))
				{
					tipovi.get(place).setSelected(true);
				}
				place++;
			}
		}
		
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		dialog.getRootPane().setDefaultButton(btnOk);

		if(this.korisnik == null)
		{
			dialog.add(new JLabel("Dodaj kozmeticara ."), "span 2");
		}
		else
		{
			dialog.add(new JLabel("Izmeni kozmeticara ."), "span 2");
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
		dialog.add(new JLabel("Obuceni tipovi: "));
		for(JRadioButton dugme : tipovi)
		{
			dialog.add(dugme);
		}
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
				
				ArrayList<TipKozmetickogTretmana> tipoviKozmeticar = new ArrayList<TipKozmetickogTretmana>();
				
				int i = 0;
				for(JRadioButton dugme : tipovi)
				{
					if(dugme.isSelected())
					{
						tipoviKozmeticar.add(KozmeticariForm.this.managers.getTipMng().getTipovi().get(i));
					}
					i++;
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
						
						if(KozmeticariForm.this.korisnik == null)
						{
							KozmeticariForm.this.managers.registrujKozmeticara(sprema, stazInt, 0, osnovaInt, ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka, tipoviKozmeticar);
						}
						else
						{
							KozmeticariForm.this.managers.getKozmeticarMng().edit(KozmeticariForm.this.korisnik.getId(), 
									tipoviKozmeticar, ((Kozmeticar) KozmeticariForm.this.korisnik).getTretmani(), 
									sprema, stazInt, ((Kozmeticar) KozmeticariForm.this.korisnik).getBonus(), osnovaInt,
									ime, prezime, pol, brojTelefona, adresa, korisnickoIme, lozinka);
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
