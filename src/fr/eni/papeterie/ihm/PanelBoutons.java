package fr.eni.papeterie.ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBoutons extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnPrev, btnNew, btnSave, btnDel, btnSuiv;
	private List<IPanelBoutonsObserver> observateurs;
	private ImageIcon prev = new ImageIcon(this.getClass().getResource("/Back24.gif"));
	private ImageIcon del = new ImageIcon(this.getClass().getResource("/Delete24.gif"));
	private ImageIcon suiv = new ImageIcon(this.getClass().getResource("/Forward24.gif"));
	private ImageIcon nouv = new ImageIcon(this.getClass().getResource("/New24.gif"));
	private ImageIcon save = new ImageIcon(this.getClass().getResource("/Save24.gif"));
	
	public PanelBoutons () {
		
		GridLayout gl = new GridLayout(0, 5, 3, 3);
		setLayout(gl);
		
		add(getBtnPrev());
		add(getBtnNew());
		add(getBtnSave());
		add(getBtnDel());
		add(getBtnSuiv());
		
		observateurs = new ArrayList<IPanelBoutonsObserver>();
		
	}
	public void addPanelBoutonObserver(IPanelBoutonsObserver observer) {
		observateurs.add(observer);
	}
	
	public JButton getBtnPrev() {
		if (btnPrev == null) {
			btnPrev = new JButton();
			btnPrev.setIcon(prev);
			btnPrev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver observateur: observateurs){
						observateur.precedent();
					}
				}
			});
		}
		return btnPrev;
	}

	public JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton();
			btnNew.setIcon(nouv);
			btnNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver observateur: observateurs){
						observateur.nouveau();
					}
				}
			});
		}
		return btnNew;
	}

	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton();
			btnSave.setIcon(save);
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver observateur: observateurs){
						observateur.enregistrer();
					}
				}
			});
		}
		return btnSave;
	}

	public JButton getBtnDel() {
		if (btnDel == null) {
			btnDel = new JButton();
			btnDel.setIcon(del);
			btnDel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver observateur: observateurs){
						observateur.supprimer();
					}
				}
			});
		}
		return btnDel;
	}

	public JButton getBtnSuiv() {
		if (btnSuiv == null) {
			btnSuiv = new JButton();
			btnSuiv.setIcon(suiv);
			btnSuiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for(IPanelBoutonsObserver observateur: observateurs){
						observateur.suivant();
					}
				}
			});
		}
		return btnSuiv;
	}
	
}
