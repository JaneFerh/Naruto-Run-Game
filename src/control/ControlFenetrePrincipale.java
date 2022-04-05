package control;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import model.FinPartie;
import view.FenetreAccueil;
import view.FenetrePrincipale;

/**
 * Provoque la fermeture de la fenêtre de jeu et rend visible la fenêtre d'accueil
 */
public class ControlFenetrePrincipale implements WindowListener {

	/* Permet de récupérer la fenêtre d'accueil */
	private FenetreAccueil fenetreAccueil;
	
	/* Permet de récupérer  la fenêtre de jeu */
	private FenetrePrincipale fenetrePrincipale;
	
	/* Permet de déterminer lorsque la fenêtre de jeu se ferme */
	private FinPartie finPartie;
	
	/**
	 * Constructeur de la classe
	 * @param fenetreAccueil
	 * @param fenetrePrincipale
	 * @param finPartie
	 */
	public ControlFenetrePrincipale(FenetreAccueil fenetreAccueil, FenetrePrincipale fenetrePrincipale, FinPartie finPartie) {
		this.fenetreAccueil = fenetreAccueil;
		this.fenetrePrincipale = fenetrePrincipale;
		this.finPartie = finPartie;
		
		fenetrePrincipale.addWindowListener(this);
	}
	
	/**
	 * Lorsque l'utilisateur tente de fermer la fenêtre de jeu, avant que
	 * cela ne se produise, la fenêtre d'accueil devient visible
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		this.finPartie.setIsClosing(); // Détermine la fermeture de la fenêtre
		this.fenetrePrincipale.dispose(); // Ferme de la fenêtre de jeu
		this.fenetreAccueil.setVisible(true); // Rend visible la fenêtre d'accueil
	}
	
	@Override
	public void windowActivated(WindowEvent e) {}
	
	@Override
	public void windowClosed(WindowEvent e) {}
	
	@Override
	public void windowDeactivated(WindowEvent e) {}
	
	@Override
	public void windowDeiconified(WindowEvent e) {}
	
	@Override
	public void windowIconified(WindowEvent e) {}
	
	@Override
	public void windowOpened(WindowEvent e) {}
}
