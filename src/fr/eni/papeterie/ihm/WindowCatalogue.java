package fr.eni.papeterie.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fr.eni.papeterie.bll.BLLException;
import fr.eni.papeterie.bll.CatalogueManager;
import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;

public class WindowCatalogue extends JFrame {

	private static final long serialVersionUID = 1L;

	private ImageIcon ramette = new ImageIcon(this.getClass().getResource("/ramette.gif"));
	private ImageIcon crayon = new ImageIcon(this.getClass().getResource("/pencil.gif"));
	private JTable tableCatalogue;
	private JScrollPane scrollPane;
	private CatalogueManager mngr2;
	private List<Article> articles2 = new ArrayList<>();
	
	public WindowCatalogue() {
		//init
		setTitle("Catalogue");
		setBounds(0,300,400,200);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		
		//extraction & remplissage catalogue
		mngr2 = null;
		try {
			mngr2 = new CatalogueManager();
		} catch (BLLException e1) {
			e1.printStackTrace();
		}
		String [] columnNames = {"", "Ref", "Marque", "Nom", "Prix", "Stock"};
		articles2 = mngr2.getCatalogue();
		Object[][] data = new Object[articles2.size()][6];
		for (int i = 0 ; i<articles2.size(); i++) {
			if (articles2.get(i) instanceof Ramette) {
				data[i][0] = ramette;
			} else {
				data[i][0] = crayon;
			}
			data[i][1] = articles2.get(i).getReference();
			data[i][2] = articles2.get(i).getMarque();
			data[i][3] = articles2.get(i).getDesignation();
			data[i][4] = articles2.get(i).getPrixUnitaire();
			data[i][5] = articles2.get(i).getQteStock();
		}
		
		//retirer la modification des cellules
		DefaultTableModel myModel = new DefaultTableModel(data, columnNames) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int col) { 
				return false;
			}
			 @Override
			 public Class<?> getColumnClass(int column) {
				 switch(column) {
				 case 0: return ImageIcon.class;
				 default: return Object.class;
				 }
			 }
		};
		tableCatalogue = new JTable(data, columnNames);
		tableCatalogue.setModel(myModel);
		tableCatalogue.setRowHeight(30);
		tableCatalogue.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableCatalogue.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableCatalogue.getColumnModel().getColumn(4).setPreferredWidth(40);
		tableCatalogue.getColumnModel().getColumn(5).setPreferredWidth(40);
		scrollPane = new JScrollPane(tableCatalogue);
		tableCatalogue.setFillsViewportHeight(true);
		this.setContentPane(scrollPane);
	}
}






