package view;

import javax.swing.JDialog;

import entity.Korisnik;
import manage.ManagerFactory;

public abstract class RegisterDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	protected ManagerFactory managers;
	protected Korisnik korisnik;
	protected boolean inited;
	
	public RegisterDialog(ManagerFactory managers, Korisnik korisnik)
	{
		this.managers = managers;
		this.korisnik = korisnik;
		this.inited = false;
	}
	
	public void init()
	{
		if(!this.inited)
		{
			this.setTitle("Registracija");
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			initRegisterGUI(this);
			this.pack();
			this.inited = true;
		}
	}
	
	public void prikazi()
	{
		this.setVisible(true);
	}
	
	public abstract void initRegisterGUI(JDialog dialog);
}
