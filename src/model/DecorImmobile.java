package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Classe représentative d'un décor qui n'a aucune incidence sur le personnage du joueur.
 * Récupère les images associées au décor, modifie l'état du décor et initialise les
 * positions en abscisse et ordonnée du décor
 */
public class DecorImmobile extends Thread implements Decor {
	
	/* Temsp de repos */
	public static final int PAUSE = 100;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/* Images représentatives du décor */
	private ArrayList<Image> images;
	
	/* Etat du décor */
	private int etat;
	
	/* Position en abscisse du décor */
	protected int positionX;
	
	/* Position en ordonnée du décor */
	protected int positionY;
	
	/**
	 * Constructeur de la classe
	 * @param cheminImages
	 * @param nombreImages
	 */
	public DecorImmobile(String cheminImages, int nombreImages, FinPartie finPartie) {
		this.finPartie = finPartie;
		
		this.images = new ArrayList<Image>();
		this.etat = 0;
		this.positionX = 0;
		this.positionY = 0;
		
		this.loadImages(cheminImages, nombreImages);
	}
	
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
		return this.images.get(this.etat);
	}
	
	/**
	 * Retourne la largeur de l'image
	 * @return la largeur de l'image
	 */
	@Override
	public int getWidth() {
		return this.images.get(this.etat).getWidth(null);
	}
	
	/**
	 * Retourne la hauteur de l'image
	 * @return la hauteur de l'image
	 */
	@Override
	public int getHeight() {
		return this.images.get(this.etat).getHeight(null);
	}
	
	/**
	 * Modifie l'image affichée
	 */
	@Override
	public void setEtat() {
		this.etat++;
		if(this.etat >= this.images.size()) {
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
		for(int i=1 ; i<=nombreImages ; i++) {
			try {
				this.images.add(ImageIO.read(new File("ressources/" + cheminImages + "_" + i + ".png")));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Modifie l'état du décor tant que la partie n'est pas terminée
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			this.setEtat();
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
