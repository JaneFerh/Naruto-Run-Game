package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.EtatAccueil;

/**
 * Provoque l'ouverture de la fenêtre de jeu, l'affichage des scores ou
 * la fermeture de l'application lorsque le bouton correspondant est cliqué
 */
public class ControlAccueil implements ActionListener {

	/* Modèle contenant les actions des boutons associés à ce contrôleur */
	private EtatAccueil etatAccueil;
	
	/**
	 * Constructeur de la classe
	 * @param etatAccueil
	 */
	public ControlAccueil(EtatAccueil etatAccueil) {
		this.etatAccueil = etatAccueil;
	}
	
	/**
	 * Effectue une action en fonction de l'intitulé du bouton cliqué
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Si le bouton a pour intitulé "Start", alors la fenêtre de jeu s'ouvre
		if(e.getActionCommand().equals("Start")) {
			this.etatAccueil.start();
		}
		
		// Si le bouton a pour intitulé "Score", alors les scores s'affichent
		else if(e.getActionCommand().equals("Score")) {
			this.etatAccueil.score();
		}
		
		// Si le bouton a pour intitulé "Quit", alors l'application se termine
		else if(e.getActionCommand().equals("Quit")) {
			this.etatAccueil.quit();
		}
	}
	
}
