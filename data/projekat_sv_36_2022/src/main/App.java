package main;

import view.MainFrame;

import manage.ManagerFactory;
import utils.AppSettings;

public class App {

	public static void main(String[] args) {
		System.out.println("Podesavanje aplikacije...");
		
		AppSettings appSettings = new AppSettings("./data/cenovnik.csv", "./data/klijenti.csv", "./data/kozmeticari.csv",
				"./data/kozmetickeUsluge.csv", "./data/kozmetickiSalon.csv", "./data/kozmetickiTretmani.csv",
				"./data/menadzeri.csv", "./data/recepcioneri.csv", "./data/tipoviKozmetickihTretmana.csv");
		
		ManagerFactory controllers = new ManagerFactory(appSettings);
		controllers.loadData();
		
		System.out.println("Pokretanje aplikacije...");
		//controllers.test();
		//controllers.test2();
		//controllers.testKt2();
		//controllers.setKt3();
		//controllers.testKt3();
		
		MainFrame main = new MainFrame(controllers);
		main.toString(); // samo da ne stoji da je unused
	}

}
