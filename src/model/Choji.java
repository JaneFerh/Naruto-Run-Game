package model;

import view.Affichage;

/**
 * Classe représentative d'un personnage immobile (qui ne se déplace pas) et qui représente un décor
 * positionné au niveau de l'horizon
 */
public class Choji extends DecorImmobile {
	
	/* Nombre d'images du personnage */
	public static final int NB_IMAGES = 2;
	
	/* Position initiale en abscisse du personnage */
	public static final int POSITION_INITIALE_X = 200;
	
	/**
	 * Constructeur de la classe
	 * @param finPartie
	 */
	public Choji(FinPartie finPartie) {
		super("decors/choji/choji", NB_IMAGES, finPartie);
		
		this.positionX = POSITION_INITIALE_X;
		this.positionY = (Affichage.COORD_Y_HORIZON - this.getHeight());
		
		this.start();
	}
}
