package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import entity.Kozmeticar;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class KozmeticarDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private Kozmeticar kozmeticar;
	private ManagerFactory managers;
	
	public KozmeticarDialog(Kozmeticar kozmeticar, ManagerFactory managers)
	{
		this.kozmeticar = kozmeticar;
		this.managers = managers;
		this.setTitle("Kozmeticki salon");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initKozmeticarDialog(this);
		this.pack();
	}
	
	public void initKozmeticarDialog(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]");
		dialog.setLayout(layout);
		
		JButton prikaziTretmane = new JButton("Prikazi");
		JButton prikaziRaspored = new JButton("Prikazi");
		
		dialog.add(new JLabel("Kozmeticarski prikaz"), "span 2");
		dialog.add(new JLabel("Prikazi zakazane tretmane: "));
		dialog.add(prikaziTretmane);
		dialog.add(new JLabel("Prikazi raspored: "));
		dialog.add(prikaziRaspored);
		
		prikaziTretmane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TretmaniPrikazFrame tf = new TretmaniPrikazFrame(KozmeticarDialog.this.kozmeticar.getTretmani(), KozmeticarDialog.this.kozmeticar, 
						KozmeticarDialog.this.managers);
				tf.prikazi();
			}
		});
		
		prikaziRaspored.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RasporedFrame tf = new RasporedFrame(KozmeticarDialog.this.kozmeticar.getTretmani());
				tf.prikazi();
			}
		});
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}

}
