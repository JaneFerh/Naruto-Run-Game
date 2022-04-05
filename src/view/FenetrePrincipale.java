package view;

import javax.swing.JFrame;

import control.ControlFenetrePrincipale;

/**
 * Fenêtre principale de l'application dans laquelle est affichée le composant permettant d'afficher le jeu
 */
public class FenetrePrincipale extends JFrame {
	
	/**
	 * Constructeur de la classe
	 * @param fenetreAccueil
	 * @param affichage
	 */
	public FenetrePrincipale(FenetreAccueil fenetreAccueil, Affichage affichage) {
		this.setTitle("Game - Naruto Run");
        
        this.add(affichage); // Ajout du composant au sein de la fenêtre
        
        this.pack(); // La taille de la fenêtre s'adapte à la taille du composant
        this.setLocationRelativeTo(null); // La fenêtre est positionnée au milieu de l'écran
        this.setResizable(false); // Le redimensionnement de la fenêtre devient impossible
        this.setVisible(true);
	}
}