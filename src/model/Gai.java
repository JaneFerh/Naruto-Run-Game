package model;

import view.Affichage;

/**
 * Classe représentative d'un personnage immobile (qui ne se déplace pas) et qui représente un décor
 */
public class Gai extends DecorImmobile {
	
	/* Nombre d'images du personnage */
	public static final int NB_IMAGES = 7;
	
	/* Position initiale en abscisse du personnage */
	public static final int POSITION_INITIALE_X = (Affichage.LARGEUR_COMPOSANT - 200);
	
	/**
	 * Constructeur de la classe
	 */
	public Gai(FinPartie finPartie) {
		super("decors/gai/gai", NB_IMAGES, finPartie);
		
		this.positionX = POSITION_INITIALE_X;
		this.positionY = (Affichage.COORD_Y_HORIZON - this.getHeight());
		
		this.start();
	}
}