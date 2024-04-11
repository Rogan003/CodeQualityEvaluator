package view.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.TipKozmetickogTretmana;
import manage.ManagerFactory;
import net.miginfocom.swing.MigLayout;

public class TipForm extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	private boolean inited;
	private TipKozmetickogTretmana stariTip = null;
	
	public TipForm(ManagerFactory managers)
	{
		this.managers = managers;
		this.inited = false;
	}
	
	public TipForm(ManagerFactory managers,TipKozmetickogTretmana stariTip)
	{
		this.managers = managers;
		this.stariTip = stariTip;
		this.inited = false;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			if(this.stariTip == null)
			{
				this.setTitle("Dodaj tip");
			}
			else
			{
				this.setTitle("Izmeni tip");
			}
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			initTipForm(this);
			this.pack();
			this.inited = true;
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
	
	public void initTipForm(JDialog dialog)
	{
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		dialog.setLayout(layout);
		
		JTextField tfNaziv;
		if(this.stariTip == null)
		{
			tfNaziv = new JTextField(20);
		}
		else
		{
			tfNaziv = new JTextField(this.stariTip.getNaziv());
		}
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		
		dialog.getRootPane().setDefaultButton(btnOk);
		
		if(this.stariTip == null)
		{
			dialog.add(new JLabel("Dodaj tip"), "span 2");
		}
		else
		{
			dialog.add(new JLabel("Izmeni tip"), "span 2");
		}
		dialog.add(new JLabel("Naziv: "));
		dialog.add(tfNaziv);
		dialog.add(btnOk);
		dialog.add(btnCancel);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tip = tfNaziv.getText().trim();
				if(tip.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!");
				}
				else
				{
					if(TipForm.this.stariTip == null)
					{
						TipForm.this.managers.getTipMng().add(tip);
					}
					else
					{
						TipForm.this.managers.getTipMng().edit(TipForm.this.stariTip.getId(), tip);
					}
					dialog.setVisible(false);
					dialog.dispose();
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
