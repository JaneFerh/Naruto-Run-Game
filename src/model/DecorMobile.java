package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Classe représentative d'un décor qui n'a aucune incidence sur le personnage du joueur.
 * Récupère les images associées au décor, modifie l'état du décor, initialise les
 * positions en abscisse et ordonnée du décor et détermine le côté vers lequel le
 * décor se déplace
 */
public abstract class DecorMobile extends Thread implements Decor {
	
	/* Valeur à incrémenter lors du déplacement du décor */
	public static final int PAS = 10;
	
	/* Etat du décor */
	private int etat;
	
	/* Images représentatives du décor en déplacement vers la gauche */
	private ArrayList<Image> imagesG;
	
	/* Images représentatives du décor en déplacement vers la droite */
	private ArrayList<Image> imagesD;
	
	/* Détermine si le décor se déplace vers la droite ou la gauche */
	protected boolean isMovingToRight;
	
	/* Position initiale en abscisse du décor */
	protected int positionX;
	
	/* Position initiale en ordonnée du décor */
	protected int positionY;
	
	/**
	 * Constructeur de la classe
	 * @param cheminImages
	 * @param nombreImages
	 */
	public DecorMobile(String cheminImages, int nombreImages) {
		this.etat = 0;
		this.imagesG = new ArrayList<Image>();
		this.imagesD = new ArrayList<Image>();
		this.isMovingToRight = true;
		this.positionX = 0;
		this.positionY = 0;
		
		this.loadImages(cheminImages, nombreImages);
	}
	
	/**
	 * Détermine si le décor est sorti des bordures de la fenêtre
	 * @return vrai si le décor est sorti des bordures de la fenêtre, faux sinon
	 */
	public abstract boolean isRemovable();
	
	/**
	 * Retourne la position en abscisse du décor
	 * @return la position en abscisse du décor
	 */
	@Override
	public int getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Retourne la position en ordonnée du décor
	 * @return la position en ordonnée du décor
	 */
	@Override
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Retourne l'image du décor associée à son état
	 * @return l'image du décor
	 */
	@Override
	public Image getImage() {
		if(this.isMovingToRight) return this.imagesD.get(this.etat);
		else return this.imagesG.get(this.etat);
	}
	
	/**
	 * Retourne la largeur de l'image
	 * @return la largeur de l'image
	 */
	@Override
	public int getWidth() {
		return this.imagesD.get(this.etat).getWidth(null);
	}
	
	/**
	 * Retourne la hauteur de l'image
	 * @return la hauteur de l'image
	 */
	@Override
	public int getHeight() {
		return this.imagesD.get(this.etat).getHeight(null);
	}
	
	/**
	 * Modifie le côté vers lequel le décor se déplace
	 */
	protected void setIsMovingToRight() {
		this.isMovingToRight = !this.isMovingToRight;
	}
	
	/**
	 * Incrémente la position en abscisse du décor
	 * (déplacement vers la droite)
	 */
	protected void moveToRight() {
		this.positionX += PAS;
	}
	
	/**
	 * Décrémente la position en abscisse du décor
	 * (déplacement vers la gauche)
	 */
	protected void moveToLeft() {
		this.positionX -= PAS;
	}
	
	/**
	 * Modifie l'image affichée
	 */
	@Override
	public void setEtat() {
		this.etat++;
		if(this.etat >= this.imagesD.size()) {
			this.etat = 0;
		}
	}
	
	/**
	 * Récupère les images du décor
	 * @param cheminImages chemin vers les images du décor
	 * @param nombreImages nombre d'images du décor
	 */
	@Override
	public void loadImages(String cheminImages, int nombreImages) {
		int i;
		for(i=1 ; i<=nombreImages ; i++) {
			try {
				this.imagesD.add(ImageIO.read(new File("ressources/" + cheminImages + "_right_" + i + ".png")));
				this.imagesG.add(ImageIO.read(new File("ressources/" + cheminImages + "_left_" + i + ".png")));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
