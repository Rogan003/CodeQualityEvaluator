package view;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;

import entity.Klijent;
import net.miginfocom.swing.MigLayout;

public class SpisakKarticaLojalnostiDialog extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Klijent> klijenti;
	
	public SpisakKarticaLojalnostiDialog(ArrayList<Klijent> klijenti)
	{
		this.klijenti = klijenti;
		this.setTitle("Spisak klijenata za karticu lojalnosti");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initSpisakDialog(this);
		this.pack();
	}
	
	public void initSpisakDialog(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 1", "[]", "[]20[][][]");
		dialog.setLayout(layout);
		
		dialog.add(new JLabel("Spisak klijenata koji mogu imati karticu lojalnosti: "));
		boolean prazno = false;
		
		for(Klijent klijent : this.klijenti)
		{
			if(klijent.getPotrosio() >= Klijent.iznosZaKarticu)
			{
				dialog.add(new JLabel(klijent.getIme() + " " + klijent.getPrezime()));
				prazno = true;
			}
		}
		
		if(!prazno)
		{
			dialog.add(new JLabel("Nema klijenata koji zadovoljavaju uslove!"));
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}

}
