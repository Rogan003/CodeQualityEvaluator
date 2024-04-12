package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import manage.KozmetickiTretmanManager;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import view.tables.KozmetickiTretmaniTableFrame;

public class RecepcionerDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private KozmetickiTretmanManager tretmanMng;
	private ManagerFactory managers;
	
	public RecepcionerDialog(KozmetickiTretmanManager tretmanMng, ManagerFactory managers)
	{
		this.tretmanMng = tretmanMng;
		this.managers = managers;
		this.setTitle("Kozmeticki salon");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initRecepcionerDialog(this);
		this.pack();
	}
	
	public void initRecepcionerDialog(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]");
		dialog.setLayout(layout);
		
		JButton zakaziTretman = new JButton("Zakazi");
		JButton prikaziTretmane = new JButton("Prikazi");
		
		dialog.add(new JLabel("Klijentski prikaz"), "span 2");
		dialog.add(new JLabel("Zakazi tretman: "));
		dialog.add(zakaziTretman);
		dialog.add(new JLabel("Prikazi sve tretmane(sa mogucnosti izmene i otkazivanje): "));
		dialog.add(prikaziTretmane);
		
		prikaziTretmane.addActionListener(new ActionListener() { // jel okej otkazivanje, izmena ovde
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickiTretmaniTableFrame tf = new KozmetickiTretmaniTableFrame(RecepcionerDialog.this.tretmanMng, RecepcionerDialog.this.managers);
				tf.setVisible(true);
			}
		});
		
		zakaziTretman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZakazivanjeDialog zd = new ZakazivanjeDialog(RecepcionerDialog.this.managers,null,null);
				zd.init();
				zd.prikazi();
			}
		});
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
}
