package fr.eni.papeterie.ihm;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 	//UI Manager
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println("Erreur : " + e);
		}
		
		//init ihm
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
					Controller.get().startApp();
			}
		});
		WindowCatalogue lancementCatalogue = new WindowCatalogue();
		lancementCatalogue.setVisible(true);
	}
}
