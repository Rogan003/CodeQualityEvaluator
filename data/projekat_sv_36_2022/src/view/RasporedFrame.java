package view;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import entity.KozmetickiTretman;
import entity.StanjeKozmetickogTretmana;
import net.miginfocom.swing.MigLayout;

public class RasporedFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<KozmetickiTretman> tretmani;
	
	public RasporedFrame(ArrayList<KozmetickiTretman> tretmani)
	{
		this.tretmani = tretmani;
		setTitle("Raspored");	
		setSize(200, 200); // fill visina?
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.init();
	}
	
	public void init()
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]30[]");
		this.setLayout(layout);
		
		this.add(new JLabel("Raspored"), "span 2");
		
		for(KozmetickiTretman tretman : this.tretmani)
		{
			if(tretman.getStanje() == StanjeKozmetickogTretmana.ZAKAZAN)
			{
				this.add(new JLabel("Zauzet: "));
				this.add(new JLabel(tretman.getTermin().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
			}
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
}
