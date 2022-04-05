package view;

import javax.swing.JFrame;

import model.EtatAccueil;

/**
 * Fenêtre dans laquelle est affichée un composant contenant des boutons permettant d'accéder
 * à la suite de l'application
 */
public class FenetreAccueil extends JFrame {
	
	/**
	 * Constructeur de la classe
	 * @param vueAccueil
	 * @param etatAccueil
	 */
	public FenetreAccueil(VueAccueil vueAccueil, EtatAccueil etatAccueil) {
		etatAccueil.setFrame(this);
		
		this.setTitle("Home - Naruto Run");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.add(vueAccueil); // Ajout du composant au sein de la fenêtre
        
        this.pack(); // La taille de la fenêtre s'adapte à la taille du composant
        this.setLocationRelativeTo(null); // La fenêtre est positionnée au milieu de l'écran
        this.setResizable(false); // Le redimensionnement de la fenêtre devient impossible
        this.setVisible(true);
	}
}
