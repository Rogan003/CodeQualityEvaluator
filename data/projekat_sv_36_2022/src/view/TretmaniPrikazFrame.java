package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Klijent;
import entity.Korisnik;
import entity.KozmetickiTretman;
import entity.StanjeKozmetickogTretmana;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class TretmaniPrikazFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<KozmetickiTretman> tretmani;
	
	public TretmaniPrikazFrame(ArrayList<KozmetickiTretman> tretmani, Korisnik korisnik, ManagerFactory managers)
	{
		this.tretmani = tretmani;
		setTitle("Zakazani kozmeticki tretmani");	
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.init(korisnik,managers);
		this.pack();
	}
	
	public void init(Korisnik korisnik, ManagerFactory managers)
	{
		MigLayout layout = new MigLayout("wrap 7", "[]20[]20[]20[]20[]", "[]20[]");
		this.setLayout(layout);
		
		JTextField indexTretmana = new JTextField(3);
		JButton otkazi = new JButton("Otkazi tretman");
		
		this.add(new JLabel("Zakazani kozmeticki tretmani"), "span 7");
		this.add(new JLabel("Redni broj"));
		this.add(new JLabel("Usluga"));
		this.add(new JLabel("Termin"));
		this.add(new JLabel("Cena"));
		this.add(new JLabel("Stanje"));
		this.add(new JLabel("Placeno"));
		if(korisnik instanceof Klijent)
		{
			this.add(new JLabel("Kozmeticar"));
		}
		else
		{
			this.add(new JLabel("Klijent"));
		}
		
		int num = 1;
		double trosak = 0;
		
		for(KozmetickiTretman tretman : this.tretmani)
		{
			this.add(new JLabel((Integer.toString(num++) + ". ")));
			this.add(new JLabel(tretman.getUsluga().getPodtip()));
			this.add(new JLabel(tretman.getTermin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
			this.add(new JLabel(Double.toString(tretman.getCena())));
			this.add(new JLabel(tretman.getStanje().toString()));
			double cena = tretman.getCena();
			
			if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_SALON)
			{
				cena = 0;
			}
			else if(tretman.getStanje() == StanjeKozmetickogTretmana.OTKAZAO_KLIJENT)
			{
				cena *= 0.1;
			}
			
			trosak += cena;
			
			this.add(new JLabel(Double.toString(cena)));
			
			if(korisnik instanceof Klijent)
			{
				this.add(new JLabel(managers.pronadjiKozmeticaraTretman(tretman)));
			}
			else
			{
				this.add(new JLabel(managers.pronadjiKlijentaTretman(tretman)));
			}
		}
		
		if(korisnik instanceof Klijent)
		{
			this.add(new JLabel("Ukupno potrosio: " + trosak), "span 7");
			this.add(new JLabel("Redni broj tretmana za otkazivanje(samo broj): "), "span 2");
			this.add(indexTretmana);
			this.add(otkazi);
			
			otkazi.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try
					{
						int index = Integer.parseInt(indexTretmana.getText().trim());
						managers.otkaziTretman(TretmaniPrikazFrame.this.tretmani.get(index - 1), true);
						JOptionPane.showMessageDialog(null, "Uspesno otkazan tretman broj " + index);
						TretmaniPrikazFrame.this.pack();
						TretmaniPrikazFrame.this.revalidate();
						TretmaniPrikazFrame.this.repaint();
					}
					catch(Exception exp)
					{
						JOptionPane.showMessageDialog(null, "Redni broj mora biti ponudjeni broj!");
					}
				}
			});
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}

}
