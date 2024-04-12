package view;

import manage.ManagerFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import entity.Korisnik;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ManagerFactory managers;
	
	private Korisnik korisnik;

	public MainFrame(ManagerFactory managers) { // ovi glavni frejmovimi su mi zapravo dijalozi, uraditi nesto po tom pitanju?
		this.managers = managers;
		LoginDialog loginDialog = new LoginDialog(this.managers,this.korisnik);
		RegisterDialog registerUserDialog = new RegisterUserDialog(this.managers,this.korisnik,false);
		
		startFrame(loginDialog,registerUserDialog);
	}

	private void startFrame(LoginDialog loginDialog, RegisterDialog registerDialog)
	{
		this.setTitle("Dobrodosli!");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		initStartFrame(this, loginDialog, registerDialog);
		this.pack();
		this.setVisible(true);
	}
	
	private void initStartFrame(JFrame dialog, LoginDialog loginDialog, RegisterDialog registerUserDialog)
	{ // da li ostaviti login register u pozadini? ako ne samo to ispraviti da se nakon logina zatvori taj prozor
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		dialog.setLayout(layout);

		JButton btnLogin = new JButton("LOGIN");
		JButton btnRegister = new JButton("REGISTER");

		// Ako postavimo dugme 'btnOK' kao defaul button, onda ce svaki pritisak tastera
		// Enter na tastaturi
		// Izazvati klik na njega
		dialog.getRootPane().setDefaultButton(btnLogin);

		dialog.add(new JLabel("Dobrodošli. Izaberite opciju: "), "span 2");
		dialog.add(btnLogin);
		dialog.add(btnRegister);
		
		// Klik na Login dugme
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog.init();
				loginDialog.prikazi();
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerUserDialog.init();
				registerUserDialog.prikazi();
			}
		});
	}
}
