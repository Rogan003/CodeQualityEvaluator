package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import entity.Menadzer;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;
import view.forms.IzmeniSalon;
import view.forms.KozmeticariForm;
import view.forms.PostaviBonusForm;
import view.forms.TipForm;
import view.forms.UslugaForm;
import view.forms.ZaposleniForm;
import view.tables.CenovnikTableFrame;
import view.tables.KlijentTableFrame;
import view.tables.KozmeticarTableFrame;
import view.tables.KozmetickeUslugeTableFrame;
import view.tables.KozmetickiTipoviTableFrame;
import view.tables.KozmetickiTretmaniTableFrame;
import view.tables.MenadzerTableFrame;
import view.tables.RecepcionerTableFrame;

public class MenadzerDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	private Menadzer menadzer; 
	
	public MenadzerDialog(ManagerFactory managers, Menadzer menadzer)
	{
		this.menadzer = menadzer;
		this.managers = managers;
		this.setTitle("Kozmeticki salon");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initMenadzerDialog(this);
		this.pack();
	}
	
	public void initMenadzerDialog(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 3", "[]10[]10[]", "[]20[][][][][][][][][]10[][]");
		dialog.setLayout(layout);
		
		JButton menadzeriDodaj = new JButton("Dodaj");
		JButton menadzeriPrikaz = new JButton("Prikazi");
		JButton klijentiDodaj = new JButton("Dodaj");
		JButton klijentiPrikaz = new JButton("Prikazi");
		JButton recepcioneriDodaj = new JButton("Dodaj");
		JButton recepcioneriPrikaz = new JButton("Prikazi");
		JButton kozmeticariDodaj = new JButton("Dodaj");
		JButton kozmeticariPrikaz = new JButton("Prikazi");
		JButton tipoviDodaj = new JButton("Dodaj");
		JButton tipoviPrikaz = new JButton("Prikazi");
		JButton uslugeDodaj = new JButton("Dodaj");
		JButton uslugePrikaz = new JButton("Prikazi");
		JButton tretmanPrikaz = new JButton("Prikazi");
		JButton cenovnikPrikaz = new JButton("Prikazi");
		JButton salonPrikaz = new JButton("Prikazi");
		JButton salonIzmena = new JButton("Izmeni");
		JButton prihodi = new JButton("Prihodi");
		JButton rashodi = new JButton("Rashodi");
		JButton klPostavi = new JButton("Postavi iznos");
		JButton klSpisak = new JButton("Spisak");
		JButton prihodiChart = new JButton("Prihodi");
		JButton kozChart = new JButton("Kozmeticari");
		JButton tretmaniChart = new JButton("Tretmani");
		JButton kozIzvestaj = new JButton("Kozmeticari");
		JButton tretmaniIzvestaj = new JButton("Tretmani");
		JButton uslugeIzvestaj = new JButton("Usluge");
		JButton postaviBonus = new JButton("Postavi");
		JButton dodeliBonus = new JButton("Dodeli");
		
		dialog.add(new JLabel("Menadzerski prikaz"), "span 3");
		dialog.add(new JLabel("Menadzeri: "));
		dialog.add(menadzeriDodaj);
		dialog.add(menadzeriPrikaz);
		dialog.add(new JLabel("Klijenti: "));
		dialog.add(klijentiDodaj);
		dialog.add(klijentiPrikaz);
		dialog.add(new JLabel("Recepcioneri: "));
		dialog.add(recepcioneriDodaj);
		dialog.add(recepcioneriPrikaz);
		dialog.add(new JLabel("Kozmeticari: "));
		dialog.add(kozmeticariDodaj);
		dialog.add(kozmeticariPrikaz);
		dialog.add(new JLabel("Tipovi tretmana: "));
		dialog.add(tipoviDodaj);
		dialog.add(tipoviPrikaz);
		dialog.add(new JLabel("Kozmeticke usluge: "));
		dialog.add(uslugeDodaj);
		dialog.add(uslugePrikaz);
		dialog.add(new JLabel("Zakazani tretmani: "));
		dialog.add(tretmanPrikaz, "span 2");
		dialog.add(new JLabel("Cenovnik: "));
		dialog.add(cenovnikPrikaz, "span 2");
		dialog.add(new JLabel("Kozmeticki salon: "));
		dialog.add(salonPrikaz);
		dialog.add(salonIzmena);
		dialog.add(new JLabel("Zarada: "));
		dialog.add(prihodi);
		dialog.add(rashodi);
		dialog.add(new JLabel("Kartica lojalnosti: "));
		dialog.add(klPostavi);
		dialog.add(klSpisak);
		dialog.add(new JLabel("Bonus: "));
		dialog.add(postaviBonus);
		dialog.add(dodeliBonus);
		dialog.add(new JLabel("Izvestaji: "), "span 3");
		dialog.add(kozIzvestaj);
		dialog.add(tretmaniIzvestaj);
		dialog.add(uslugeIzvestaj);
		dialog.add(new JLabel("Dijagrami: "), "span 3");
		dialog.add(prihodiChart);
		dialog.add(kozChart);
		dialog.add(tretmaniChart);
		
		klijentiPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KlijentTableFrame tf = new KlijentTableFrame(MenadzerDialog.this.managers,
						MenadzerDialog.this.managers.getKlijentMng());
				tf.setVisible(true);
			}
		});
		
		klijentiDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterDialog registerUserDialog = new RegisterUserDialog(MenadzerDialog.this.managers,null,true);
				registerUserDialog.init();
				registerUserDialog.prikazi();
			}
		});
		
		salonPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickiSalonPrikaz kozmetickiSalonPrikaz = new KozmetickiSalonPrikaz(MenadzerDialog.this.managers.getSalonMng().getKozmetickiSalon());
				kozmetickiSalonPrikaz.prikazi();
			}
		});
		
		salonIzmena.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzmeniSalon is = new IzmeniSalon(MenadzerDialog.this.managers);
				is.init();
				is.prikazi();
			}
		});
		
		menadzeriPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenadzerTableFrame tf = new MenadzerTableFrame(MenadzerDialog.this.managers,
						MenadzerDialog.this.managers.getMenadzerMng());
				tf.setVisible(true);
			}
		});
		
		menadzeriDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZaposleniForm menadzerRegistruj = new ZaposleniForm(MenadzerDialog.this.managers, null,true);
				menadzerRegistruj.init();
				menadzerRegistruj.prikazi();
			}
		});
		
		kozmeticariPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmeticarTableFrame tf = new KozmeticarTableFrame(MenadzerDialog.this.managers,
						MenadzerDialog.this.managers.getKozmeticarMng());
				tf.setVisible(true);
			}
		});
		
		kozmeticariDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmeticariForm kozmeticarRegistruj = new KozmeticariForm(MenadzerDialog.this.managers, null);
				kozmeticarRegistruj.init();
				kozmeticarRegistruj.prikazi();
			}
		});
		
		recepcioneriPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RecepcionerTableFrame tf = new RecepcionerTableFrame(MenadzerDialog.this.managers,
						MenadzerDialog.this.managers.getRecepcionerMng());
				tf.setVisible(true);
			}
		});
		
		recepcioneriDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZaposleniForm menadzerRegistruj = new ZaposleniForm(MenadzerDialog.this.managers, null,false);
				menadzerRegistruj.init();
				menadzerRegistruj.prikazi();
			}
		});
		
		tipoviPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickiTipoviTableFrame tf = new KozmetickiTipoviTableFrame(
						MenadzerDialog.this.managers,MenadzerDialog.this.managers.getTipMng());
				tf.setVisible(true);
			}
		});
		
		tipoviDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipForm tipForm = new TipForm(MenadzerDialog.this.managers);
				tipForm.init();
				tipForm.prikazi();
			}
		});
		
		uslugePrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickeUslugeTableFrame tf = new KozmetickeUslugeTableFrame(MenadzerDialog.this.managers,
						MenadzerDialog.this.managers.getUslugaMng());
				tf.setVisible(true);
			}
		});
		
		uslugeDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UslugaForm uslugaForm = new UslugaForm(MenadzerDialog.this.managers);
				uslugaForm.init();
				uslugaForm.prikazi();
			}
		});
		
		tretmanPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KozmetickiTretmaniTableFrame tf = new KozmetickiTretmaniTableFrame(MenadzerDialog.this.managers.getTretmanMng(), null);
				tf.setVisible(true);
			}
		});
		
		cenovnikPrikaz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CenovnikTableFrame tf = new CenovnikTableFrame(MenadzerDialog.this.managers.getCenovnikMng(),
						MenadzerDialog.this.managers.getUslugaMng());
				tf.setVisible(true);
			}
		});
		
		prihodi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajiDialog id = new IzvestajiDialog(MenadzerDialog.this.managers,3);
				id.init();
				id.prikazi();
			}
		});
		
		rashodi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajiDialog id = new IzvestajiDialog(MenadzerDialog.this.managers,4);
				id.init();
				id.prikazi();
			}
		});
		
		klPostavi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{
					String rez = JOptionPane.showInputDialog(null, "Unesite iznos za karticu lojanlosti: ");
					if(rez != null)
					{
						int iznosZaKarticu = Integer.parseInt(rez);
						MenadzerDialog.this.managers.postaviIznosZaKarticu(MenadzerDialog.this.menadzer, iznosZaKarticu);	
					}
				}
				catch(NumberFormatException exp)
				{
					JOptionPane.showMessageDialog(null, "Iznos mora biti broj!");
				}
			}
		});
		
		klSpisak.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SpisakKarticaLojalnostiDialog kld = new SpisakKarticaLojalnostiDialog(
						MenadzerDialog.this.managers.getKlijentMng().getKlijenti());
				kld.prikazi();
			}
		});
		
		prihodiChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Date> meseci3 = new ArrayList<Date>();
				for(int i = 0;i < 12;i++)
				{
					meseci3.add(Date.from(LocalDate.now().minusMonths(i).atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
				HashMap<String,Double[]> prihodi = managers.prihodiPoTipu();
				
			    XYChart chart = new XYChartBuilder().width(600).height(400).title("Prikaz po tipu kozmetickog tretmana").build();
			    
			    for(Map.Entry<String, Double[]> set : prihodi.entrySet())
			    {
			    	List<Double> vrednosti = new ArrayList<Double>();

			    	for(Double d : set.getValue())
			    	{
			    		vrednosti.add(d);
			    	}
			    	
			    	Collections.reverse(vrednosti);
			    	chart.addSeries(set.getKey(), meseci3, vrednosti);
			    }
			    
			    JPanel panel=new XChartPanel<XYChart>(chart);
		        JDialog d=new JDialog(MenadzerDialog.this,"Prihodi po tipu kozmetickog tretmana");
		        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        d.setContentPane(panel);
		        d.pack();
		        d.setLocationRelativeTo(MenadzerDialog.this);
		        d.setVisible(true);
			}
		});
		
		kozChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		        HashMap<String,Integer> angazovanje = managers.angazovanjeKozmeticari();
				 
			    PieChart chart = new PieChartBuilder().width(800).height(600).title("Angazovanje kozmeticara u poslednjih 30 dana").build();
			    
			    for(Map.Entry<String, Integer> set : angazovanje.entrySet())
			    {
			    	chart.addSeries(set.getKey(), set.getValue());
			    }
			    
			    JPanel panel=new XChartPanel<PieChart>(chart);
		        JDialog d=new JDialog(MenadzerDialog.this,"Angazovanje kozmeticara u poslednjih 30 dana");
		        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        d.setContentPane(panel);
		        d.pack();
		        d.setLocationRelativeTo(MenadzerDialog.this);
		        d.setVisible(true);
			}
		});
		
		tretmaniChart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String,Integer> angazovanje = managers.zastupljenostUsluga();
				 
			    PieChart chart = new PieChartBuilder().width(800).height(600).title("Zastupljenost kozmetickih usluga").build();
			    
			    for(Map.Entry<String, Integer> set : angazovanje.entrySet())
			    {
			    	chart.addSeries(set.getKey(), set.getValue());
			    }
			    
			    JPanel panel=new XChartPanel<PieChart>(chart);
		        JDialog d=new JDialog(MenadzerDialog.this,"Zastupljenost kozmetickih usluga");
		        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		        d.setContentPane(panel);
		        d.pack();
		        d.setLocationRelativeTo(MenadzerDialog.this);
		        d.setVisible(true);
			}
		});
		
		kozIzvestaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajiDialog kozId = new IzvestajiDialog(MenadzerDialog.this.managers,0);
				kozId.init();
				kozId.prikazi();
			}
		});
		
		tretmaniIzvestaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajiDialog kozId = new IzvestajiDialog(MenadzerDialog.this.managers,1);
				kozId.init();
				kozId.prikazi();
			}
		});
		
		uslugeIzvestaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajiDialog kozId = new IzvestajiDialog(MenadzerDialog.this.managers,2);
				kozId.init();
				kozId.prikazi();
			}
		});
		
		postaviBonus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PostaviBonusForm pfb = new PostaviBonusForm(MenadzerDialog.this.managers);
				pfb.init();
				pfb.prikazi();
			}
		});
		
		dodeliBonus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean dodeljen = MenadzerDialog.this.managers.dodeliBonus();
				if(dodeljen)
				{
					JOptionPane.showMessageDialog(null, "Uspesno dodeljeni bonusi", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Nije postavljen iznos za bonus!", "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
}
