package model;

import java.awt.Image;

/**
 * Interface contenant des méthodes appliquées aux décors
 */
public interface Decor {
	
	/**
	 * Retourne la position en abscisse du décor
	 * @return la position en abscisse du décor
	 */
	public int getPositionX();
	
	/**
	 * Retourne la position en ordonnée du décor
	 * @return la position en ordonnée du décor
	 */
	public int getPositionY();
	
	/**
	 * Retourne l'image du décor
	 * @return l'image du décor
	 */
	public Image getImage();
	
	/**
	 * Retourne la largeur de l'image
	 * @return la largeur de l'image
	 */
	public int getWidth();
	
	/**
	 * Retourne la hauteur de l'image
	 * @return la hauteur de l'image
	 */
	public int getHeight();
	
	/**
	 * Modifie l'image affichée
	 */
	public void setEtat();
	
	/**
	 * Récupère les images du décor
	 * @param cheminImages chemin vers les images du décor
	 * @param nombreImages nombre d'images du décor
	 */
	public void loadImages(String cheminImages, int nombreImages);
}
