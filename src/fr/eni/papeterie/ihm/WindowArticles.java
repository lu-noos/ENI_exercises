package fr.eni.papeterie.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;

public class WindowArticles extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel panArticles;
	private JPanel panTotal;
	private PanelBoutons panelBoutons;
	private JTextField reference, designation, marque, stock, prix;
	private ButtonGroup grpRadio, grpRadio2;
	private JRadioButton radRamette, radStylo, rad80, rad100;
	private JComboBox<String> cbbCouleur;
	
	private Integer idCourant;
	
	public WindowArticles() {
		//init
		setTitle("Papeterie - articles");
		setBounds(0,0,350,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		//instanciation des elements
		grpRadio = new ButtonGroup();
		grpRadio.add(getRadRamette());
		grpRadio.add(getRadStylo());
		
		grpRadio2 = new ButtonGroup();
		grpRadio2.add(getRad80());
		grpRadio2.add(getRad100());
		
		//création du layout
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 5;
		
		//entrée des éléments
		panTotal = new JPanel();
		panArticles = new JPanel();
		panArticles.setLayout(gbl);
		
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridy = 0;
		gbc.gridx = 0;
		panArticles.add(new JLabel("Référence"), gbc);
		gbc.gridy=1;
		panArticles.add(new JLabel("Designation"), gbc);
		gbc.gridy=2;
		panArticles.add(new JLabel("Marque"), gbc);
		gbc.gridy=3;
		panArticles.add(new JLabel("Stock"), gbc);
		gbc.gridy=4;
		panArticles.add(new JLabel("Prix"), gbc);
		
		gbc.gridy = 0;
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panArticles.add(getReference(), gbc);
		gbc.gridy=1;
		panArticles.add(getDesignation(), gbc);
		gbc.gridy=2;
		panArticles.add(getMarque(), gbc);
		gbc.gridy=3;
		panArticles.add(getStock(), gbc);
		gbc.gridy=4;
		panArticles.add(getPrix(), gbc);
		gbc.gridy=5;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		panArticles.add(new JLabel("Type"), gbc);
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panArticles.add(getRadRamette(), gbc);
		gbc.gridy=6;
		panArticles.add(getRadStylo(), gbc);
		gbc.gridy=7;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		panArticles.add(new JLabel("Grammage"), gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.WEST;
		panArticles.add(getRad80(), gbc);
		gbc.gridy=8;
		panArticles.add(getRad100(), gbc);
		gbc.gridy=9;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		panArticles.add(new JLabel("Couleur"), gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panArticles.add(getCbbCouleur(), gbc);
		gbc.gridy=10;
		gbc.gridx = 0;
		
		panTotal.add(panArticles);
		panTotal.add(getPanelBoutons());
		
		//ajout du panel au jframe
		this.setContentPane(panTotal);
	}
	
	public JTextField getReference() {
		if (reference == null) {
			reference = new JTextField(20);
		}
		return reference;
	}

	public JTextField getDesignation() {
		if (designation == null) {
			designation = new JTextField(20);
		}
		return designation;
	}

	public JTextField getMarque() {
		if (marque == null) {
			marque = new JTextField(20);
		}
		return marque;
	}

	public JTextField getStock() {
		if (stock == null) {
			stock = new JTextField(20);
		}
		return stock;
	}

	public JTextField getPrix() {
		if (prix == null) {
			prix = new JTextField(20);
		}
		return prix;
	}

	public void precedent() {
		Controller.get().precedent();

	}

	public void suivant() {
		Controller.get().suivant();

	}

	public void nouveau() {
		Controller.get().nouveau();

	}

	public void enregistrer() {
		Controller.get().enregistrer();

	}

	public void supprimer() {
		Controller.get().supprimer();

	}

	public JRadioButton getRadRamette() {
		if (radRamette == null) {
			radRamette = new JRadioButton("Ramette");
			radRamette.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(radRamette.isSelected()) {
						getRad80().setEnabled(true);
						getRad100().setEnabled(true);
						getCbbCouleur().setEnabled(false);
					}
				}
			});
		}
		return radRamette;
	}

	public JRadioButton getRadStylo() {
		if (radStylo == null) {
			radStylo = new JRadioButton("Stylo");
			radStylo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(radStylo.isSelected()) {
						getRad80().setEnabled(false);
						getRad100().setEnabled(false);
						getCbbCouleur().setEnabled(true);
					}
				}
			});
		}
		return radStylo;
	}

	public PanelBoutons getPanelBoutons() {
		if (panelBoutons == null) {
			panelBoutons = new PanelBoutons();
			panelBoutons.addPanelBoutonObserver(new IPanelBoutonsObserver() {
				@Override
				public void precedent() {
					Controller.get().precedent();
				}

				@Override
				public void suivant() {
					Controller.get().suivant();
				}

				@Override
				public void nouveau() {
					Controller.get().nouveau();
					bloquer();
					getRadRamette().setEnabled(true);
					getRadStylo().setEnabled(true);
				}

				@Override
				public void enregistrer() {
					Controller.get().enregistrer();
					debloquer();
				}

				@Override
				public void supprimer() {
					Controller.get().supprimer();
					debloquer();
				}
			});
		}
		return panelBoutons;
	}
	
	public JRadioButton getRad80() {
		if (rad80 == null) {
			rad80 = new JRadioButton("80 grammes");
		}
		return rad80;
	}

	public JRadioButton getRad100() {
		if (rad100 == null) {
			rad100 = new JRadioButton("100 grammes");
		}
		return rad100;
	}

	public JComboBox<String> getCbbCouleur() {
		if (cbbCouleur == null) {
			cbbCouleur = new JComboBox<String>(new String[] {"N/A", "bleu", "noir", "rouge", "jaune", "vert", "orange"});
		}
		return cbbCouleur;
	}

	 void debloquer() {
		panelBoutons.getBtnPrev().setEnabled(true);
		panelBoutons.getBtnSuiv().setEnabled(true);
		panelBoutons.getBtnNew().setEnabled(true);
	 }
	
	public void bloquer() {
		panelBoutons.getBtnPrev().setEnabled(false);
		panelBoutons.getBtnSuiv().setEnabled(false);
		panelBoutons.getBtnNew().setEnabled(false);
	}
	
	//afficher l'article dont l'index est selectionné
	public void afficher(Article a) {
		if (a.getIdArticle() != null) {
			idCourant = a.getIdArticle();
		}
		
		getRadRamette().setEnabled(false);
		getRadStylo().setEnabled(false);
		getRad80().setEnabled(false);
		getRad100().setEnabled(false);
		getCbbCouleur().setEnabled(false);
		panelBoutons.getBtnPrev().setEnabled(true);
		panelBoutons.getBtnSuiv().setEnabled(true);
		panelBoutons.getBtnSave().setEnabled(true);
		panelBoutons.getBtnNew().setEnabled(true);
		
		getReference().setText(a.getReference());
		getDesignation().setText(a.getDesignation());
		getMarque().setText(a.getMarque());
		getStock().setText(a.getQteStock() +"");
		getPrix().setText(a.getPrixUnitaire() +"");
		
		if(idCourant==0) {
			panelBoutons.getBtnPrev().setEnabled(false);
		}
		if (idCourant==4) {
			panelBoutons.getBtnSuiv().setEnabled(false);
		}
		if (a instanceof Stylo) {
			getRadStylo().setEnabled(true);
			getRadStylo().setSelected(true);
			getCbbCouleur().setEnabled(true);
			getRad80().setSelected(false);
			getRad100().setSelected(false);
			switch (((Stylo) a).getCouleur()) {
			case "bleu":
				getCbbCouleur().setSelectedIndex(1);
				break;
			case "noir":
				getCbbCouleur().setSelectedIndex(2);
				break;
			case "rouge":
				getCbbCouleur().setSelectedIndex(3);
				break;
			case "jaune":
				getCbbCouleur().setSelectedIndex(4);
				break;
			case "vert":
				getCbbCouleur().setSelectedIndex(5);
				break;
			case "orange":
				getCbbCouleur().setSelectedIndex(6);
				break;
			default:
				getCbbCouleur().setSelectedIndex(0);
			}
		} else if (a instanceof Ramette) {
			rad80.setEnabled(true);
			rad100.setEnabled(true);
			if (((Ramette) a).getGrammage()==80) {
				rad80.setSelected(true);
			} else {
				rad100.setSelected(true);
			}
			radRamette.setEnabled(true);
			radRamette.setSelected(true);
		}
	}
	
	public Article getArticle() {
		Article sortie=null;
		if (radRamette.isSelected()) {
			sortie = new Ramette("", "", "", 0f, 0, 0);
			sortie.setReference(reference.getText());
			sortie.setMarque(marque.getText());
			sortie.setDesignation(designation.getText());
			sortie.setQteStock(Integer.parseInt(stock.getText()));
			sortie.setPrixUnitaire(Float.parseFloat(prix.getText()));
			if(rad80.isSelected()) {
				((Ramette) sortie).setGrammage(80);
			} else if (rad100.isSelected()) {
				((Ramette) sortie).setGrammage(100);
			} else {
				System.err.println("Aucun grammage selectionné");
			}
		} else if (radStylo.isSelected()) {
			sortie = new Stylo("", "", "", 0f, 0, "");
			sortie.setReference(reference.getText());
			sortie.setMarque(marque.getText());
			sortie.setDesignation(designation.getText());
			sortie.setQteStock(Integer.parseInt(stock.getText()));
			sortie.setPrixUnitaire(Float.parseFloat(prix.getText()));
			if (cbbCouleur.getSelectedIndex()!=0) {
				((Stylo) sortie).setCouleur((String)cbbCouleur.getSelectedItem());
			} else {
				System.err.println("Aucune couleur selectionnée");
			}
		} else {
			System.err.println("Aucun type selectionné");
		}
		return sortie;
	}
}