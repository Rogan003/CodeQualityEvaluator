package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import entity.KozmetickaUsluga;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class IzvestajiDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	private boolean inited;
	private int type;
	
	public IzvestajiDialog(ManagerFactory managers, int type)
	{
		this.managers = managers;
		this.inited = false;
		this.type = type;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			this.setTitle("Izvestaji");
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			initIzvestaji(this);
			this.pack();
			this.inited = true;
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
	
	public void initIzvestaji(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 3", "[][]", "[]20[][]20[]");
		dialog.setLayout(layout);
		
		SpinnerDateModel dateModel = new SpinnerDateModel();
		SpinnerDateModel dateModel2 = new SpinnerDateModel();
	    JSpinner spinner1 = new JSpinner(dateModel);
	    JSpinner spinner2 = new JSpinner(dateModel2);
	    JButton btnOk = new JButton("Generisi izvestaj");
		
		if(this.type == 0)
		{
			dialog.add(new JLabel("Izvestaj za kozmeticare"), "span 3");
		}
		else if(this.type == 1)
		{
			dialog.add(new JLabel("Izvestaj za statuse kozmetickih tretmana"), "span 3");
		}
		else if(this.type == 2)
		{
			dialog.add(new JLabel("Izvestaj za kozmeticke usluge"), "span 3");
		}
		else if(this.type == 3)
		{
			dialog.add(new JLabel("Prihodi"), "span 3");
		}
		else
		{
			dialog.add(new JLabel("Rashodi"), "span 3");
		}
		
		dialog.add(new JLabel("Izaberite datume(od-do): "));
		dialog.add(spinner1);
		dialog.add(spinner2);
		dialog.add(spinner2);
		dialog.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LocalDateTime termin1 = LocalDateTime.ofInstant(((Date) spinner1.getValue()).toInstant(), ZoneId.systemDefault());
				LocalDateTime termin2 = LocalDateTime.ofInstant(((Date) spinner2.getValue()).toInstant(), ZoneId.systemDefault());

				if(type == 0)
				{
					dialog.add(new JLabel("Kozmeticar"));
					dialog.add(new JLabel("Broj tretmana"));
					dialog.add(new JLabel("Prihodi"));
					HashMap<String,Double[]> tretmaniPrikaz = managers.tretmaniIPrihodi(termin1, termin2);
					
					for(Map.Entry<String, Double[]> set : tretmaniPrikaz.entrySet())
				    {
				    	dialog.add(new JLabel(set.getKey()));
				    	dialog.add(new JLabel(Double.toString(set.getValue()[0])));
				    	dialog.add(new JLabel(Double.toString(set.getValue()[1])));
				    }
				}
				else if(type == 1)
				{
					dialog.add(new JLabel("Potvrdjeno"));
					dialog.add(new JLabel("Otkazao klijent"));
					dialog.add(new JLabel("Otkazao salon"));
					
					int [] tretmani = managers.tretmaniIStanje(termin1, termin2);
					
					dialog.add(new JLabel(Integer.toString(tretmani[0])));
					dialog.add(new JLabel(Integer.toString(tretmani[1])));
					dialog.add(new JLabel(Integer.toString(tretmani[2])));
				}
				else if(type == 2)
				{
					HashMap<KozmetickaUsluga,Double[]> tretmaniPrikaz = managers.prikazKozmetickeUsluge(termin1, termin2);
					
					for(Map.Entry<KozmetickaUsluga, Double[]> set : tretmaniPrikaz.entrySet())
				    {
						KozmetickaUsluga usluga = set.getKey();
				    	dialog.add(new JLabel(usluga.getPodtip()));
				    	dialog.add(new JLabel(usluga.getTip().getNaziv()));
				    	dialog.add(new JLabel(Integer.toString(usluga.getTrajanje()) + " minuta"));
				    	dialog.add(new JLabel(Integer.toString((set.getValue()[0].intValue())) + " zakazanih tretmana"));
				    	dialog.add(new JLabel(Double.toString(set.getValue()[1]) + " dinara prihoda"));
				    	dialog.add(new JLabel(""), "span 4");
				    }

				}
				else if(type == 3)
				{
					dialog.add(new JLabel("Prihodi: "));
					dialog.add(new JLabel(Double.toString(managers.getPrihodi(termin1, termin2))));
				}
				else
				{
					dialog.add(new JLabel("Rashodi: "));
					dialog.add(new JLabel(Double.toString(managers.getRashodi(termin1, termin2))));
				}
				
				IzvestajiDialog.this.remove(btnOk);
				dialog.pack();
				dialog.revalidate();
				dialog.repaint();
			}
		});
	}

}
