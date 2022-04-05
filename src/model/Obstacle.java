package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import view.Affichage;

/**
 * Récupère des images représentatives de l'obstacle, détermine lorsque le personnage est touché par un obstacle
 * et initialise les positions en ordonnée et abscisse de l'obstacle
 */
public class Obstacle extends Thread implements Decor {

	/* Temps de repos */
	public static final int PAUSE = 80;
	
	/* Largeur de l'image */
	public static final int LARGEUR_IMAGE = 20;
	
	/* Hauteur de l'image */
	public static final int HAUTEUR_IMAGE = 20;
	
	/* Permet de récupérer la position du personnage */
	private EtatNaruto etatNaruto;
	
	/* Permet de récupérer la valeur de décalage */
	private Piste piste;
	
	/* Permet de décrémenter la vitesse du personnage lorsque le personnage est touché par un obstacle */
	private Vitesse vitesse;
	
	/* Permet de décrémenter le score du joueur lorsque le personnage est touché par un obstacle */
	private Score score;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/* Position en abscisse de l'obstacle */
	private int positionX;
	
	/* Position en ordonnée de l'obstacle */
	private int positionY;
	
	/* Liste d'images contenant des obstacles */
	protected ArrayList<Image> images;
	
	/* Générateur de valeurs aléatoires */
	private Random rand;
	
	/* Etat de l'obstacle */
	private int etat;
	
	/* Permet de décrémenter la vitesse et le score qu'une seule fois par obstacle touché */
	private boolean onlyOneTime;
	
	/**
	 * Constructeur de la classe
	 * @param cheminImages
	 * @param nombreImages
	 * @param positionY
	 * @param etatNaruto
	 * @param piste
	 * @param vitesse
	 * @param score
	 */
	public Obstacle(String cheminImages, int nombreImages, int positionY, EtatNaruto etatNaruto, Piste piste, Vitesse vitesse, Score score, FinPartie finPartie) {
		this.positionY = positionY;
		this.etatNaruto = etatNaruto;
		this.piste = piste;
		this.vitesse = vitesse;
		this.score = score;
		this.finPartie = finPartie;
		
		this.images = new ArrayList<Image>();
		this.loadImages(cheminImages, nombreImages);
		
		this.rand = new Random();
		this.etat = 0;
		this.onlyOneTime = true;
		
		this.positionX = this.rand.nextInt(Affichage.LARGEUR_COMPOSANT - this.getWidth() - LARGEUR_IMAGE);
		
		this.start();
	}
	
	/**
	 * Retourne la position en abscisse de l'obstacle
	 * @return la position en abscisse de l'obstacle
	 */
	@Override
	public int getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Retourne la position en ordonnée de l'obstacle
	 * @return la position en ordonnée de l'onstacle
	 */
	@Override
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Retourne l'image de l'obstacle
	 * @return l'image de l'obstacle
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
		return (this.images.get(0).getWidth(null) + LARGEUR_IMAGE);
	}
	
	/**
	 * Retourne la hauteur de l'image
	 * @return la hauteur de l'image
	 */
	@Override
	public int getHeight() {
		return (this.images.get(0).getHeight(null) + HAUTEUR_IMAGE);
	}
	
	/**
	 * Modifie l'état de l'obstacle
	 */
	@Override
	public void setEtat() {
		this.etat++;
		if(this.etat >= this.images.size()) {
			this.etat = 0;
		}
	}
	
	/**
	 * Détermine lorsque le personnage a touché une première fois l'obstacle
	 * @return vrai si le personnage a touché une première fois l'obstacle, faux sinon
	 */
	public boolean isTouched() {
		
		return (
			this.onlyOneTime
			&& (this.positionX >= (this.etatNaruto.getPositionX() - (int)(this.getWidth()/2)))
			&& (this.positionX + this.getWidth()) <= (this.etatNaruto.getPositionX() + EtatNaruto.LARGEUR_IMG + (int)(this.getWidth()/2))
			&& ((this.positionY + this.getHeight() + this.piste.getPositionY()) >= EtatNaruto.POSITION_INITIALE_Y)
			&& ((this.positionY + this.piste.getPositionY()) < (EtatNaruto.POSITION_INITIALE_Y + EtatNaruto.HAUTEUR_IMG))
		);
	}
	
	/**
	 * Récupère les images de l'obstacle
	 * @param cheminImages chemin vers les images
	 * @param nombreImages nombre d'images
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
	 * Lorsque le personnage est touché par un obstacle, le score et la vitesse sont
	 * décrémentés tant que la partie n'est pas terminée
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			this.setEtat();
			
			// Si le personnage est touché par un obstacle
			if(isTouched()) {
				AudioJeu.playSound(new File("ressources/Son/obstacle.wav"));
				this.score.decreaseByObstacle();
				this.vitesse.decelerate();
				this.onlyOneTime = false;
				this.etatNaruto.isTouched = true;
				this.finPartie.increaseObstacles(); // Incrémente le nombre d'obstacles touchés
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