package view;

import java.time.format.DateTimeFormatter;

import javax.swing.JDialog;
import javax.swing.JLabel;

import entity.KozmetickiSalon;
import net.miginfocom.swing.MigLayout;

public class KozmetickiSalonPrikaz extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private KozmetickiSalon salon;
	
	public KozmetickiSalonPrikaz(KozmetickiSalon salon)
	{
		this.salon = salon;
		this.setTitle("Kozmeticki salon");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initSalonDialog(this);
		this.pack();
	}
	
	private void initSalonDialog(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[]10[]", "[]20[][][]");
		dialog.setLayout(layout);
		
		dialog.add(new JLabel("Kozmeticki salon - informacije"), "span 2");
		dialog.add(new JLabel("Naziv: "));
		dialog.add(new JLabel(this.salon.getNaziv()));
		dialog.add(new JLabel("Pocetak radnog vremena: "));
		dialog.add(new JLabel(this.salon.getRadnoVreme()[0].format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
		dialog.add(new JLabel("Kraj radnog vremena: "));
		dialog.add(new JLabel(this.salon.getRadnoVreme()[1].format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
}
