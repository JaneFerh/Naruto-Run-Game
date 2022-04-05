package model;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Récupère des images représentatives du point de contrôle, détermine lorsque le personnage a atteint le point
 * de contrôle et initialise les positions en ordonnée et abscisse du point de contrôle
 */
public class PointControle extends Thread {

	/* Temps de repos */
	public static final int PAUSE = 50;
	
	/* Nombre d'images du point de contrôle */
	public static final int NB_IMAGES = 7;
	
	/* Permet de modifier la valeur qui permet de déterminer lorsque le point de contrôle est atteint */
	private EtatPointControle etatPointControle;
	
	/* Permet de récupérer la valeur de décalage */
	private Piste piste;
	
	/* Permet d'incrémenter le score du joueur */
	private Score score;
	
	/* Permet d'incrémenter le temps restant */
	private Decompte decompte;
	
	/* Détermine la fin de partie */
	private FinPartie finPartie;
	
	/* Position en abscisse du point de contrôle */
	private int positionX;
	
	/* Position en ordonnée du point de contrôle */
	private int positionY;
	
	/* Etat du point de contrôle */
	private int etat;
	
	/* Permet d'incrémenter la vitesse et le score qu'une seule fois par point de contrôle atteint */
	private boolean onlyOneTime;
	
	/* Liste d'images de point de contrôle */
	private ArrayList<Image> images;
	
	/**
	 * Constructeur de la classe
	 * @param etatPointControle
	 * @param piste
	 * @param score
	 * @param decompte
	 * @param finPartie
	 */
	public PointControle(EtatPointControle etatPointControle, Piste piste, Score score, Decompte decompte, FinPartie finPartie) {
		this.etatPointControle = etatPointControle;
		this.piste = piste;
		this.score = score;
		this.decompte = decompte;
		this.finPartie = finPartie;
		
		this.images = new ArrayList<Image>();
		this.loadImages();
		
		Point[] points = this.piste.getPiste();
		
		this.positionX = this.piste.getPositionXOnTrack(points[points.length-2], points[points.length-1]); // Positionner au niveau de l'intersection de la piste et l'horizon
		this.positionY = (this.piste.getPositionY() - Piste.HAUTEUR_MIN + this.images.get(0).getHeight(null) + EtatPointControle.HAUTEUR_IMG);
		this.etat = 0;
		this.onlyOneTime = true;
		
		this.start();
	}
	
	/**
	 * Récupère les images du point de contrôle
	 */
	private void loadImages() {
		for(int i=1 ; i<=NB_IMAGES ; i++) {
			try {
				this.images.add(ImageIO.read(new File("ressources/checkpoint/kakashi_checkpoint_" + i + ".png")));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Retourne la position en abscisse du point de contrôle
	 * @return la position en abscisse du point de contrôle
	 */
	public int getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Retourne la position en ordonnée du point de contrôle
	 * @return la position en ordonnée du point de contrôle
	 */
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Retourne l'image du point de contrôle
	 * @return l'image du point de contrôle
	 */
	public Image getImage() {
		return this.images.get(this.etat);
	}
	
	/**
	 * Retourne la largeur de l'image
	 * @return la largeur de l'image
	 */
	public int getWidth() {
		return this.images.get(this.etat).getWidth(null);
	}
	
	/**
	 * Retourne la hauteur de l'image
	 * @return la hauteur de l'image
	 */
	public int getHeight() {
		return this.images.get(this.etat).getHeight(null);
	}
	
	/**
	 * Modifie l'état du point de contrôle
	 */
	private void setEtat() {
		if(this.etat < (NB_IMAGES-1)) {
			this.etat++;
		}
	}
	
	/**
	 * Détermine lorsque le personnage a atteint le point de contrôle
	 * @return vrai si le personnage a atteint le point de contrôle, faux sinon
	 */
	public boolean isReached() {
		return (this.onlyOneTime && (EtatNaruto.POSITION_INITIALE_Y <= (this.piste.getPositionY() - this.positionY)));
	}
	
	/**
	 * Lorsque le personnage a atteint le point de contrôle, le score et la vitesse sont incrémentés
	 * tant que la partie n'est pas terminée
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			this.setEtat();
			
			// Si le personnage a atteint le point de contrôle
			if(this.isReached()) {
				AudioJeu.playSound(new File("ressources/Son/checkpoint1.wav"));
				this.score.increaseByCheckpoint();
				this.decompte.increaseTime();
				this.onlyOneTime = false;
				this.etatPointControle.isReached = true;
				this.finPartie.increaseCheckpoints(); // Incrémente le nombre de points de contrôle atteints
			}
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}