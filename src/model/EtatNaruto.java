package model;

import view.Affichage;

/**
 * Modifie l'état et la position du personnage
 */
public class EtatNaruto extends Thread {
	
	/* Temps de repos */
	public static final int PAUSE = 50;
	
	/* Nombre d'images du personnage */
	public static final int NB_IMG = 8;
	
	/* Largeur de l'image */
	public static final int LARGEUR_IMG = 50;
	
	/* Hauteur de l'image */
	public static final int HAUTEUR_IMG = 100;
	
	/* Valeur la plus petite que puisse atteindre le personnage */
	public static final int LARGEUR_MIN = 0;
	
	/* Valeur la plus grande que puisse atteindre le personnage */
	public static final int LARGEUR_MAX = Affichage.LARGEUR_COMPOSANT;
	
	/* Position initiale du personnage dans l'axe des abscisses (milieu de l'écran) */
	public static final int POSITION_INITIALE_X = ( (Affichage.LARGEUR_COMPOSANT/2) - (LARGEUR_IMG/2) );
	
	/* Position initiale du personnage dans l'axe des ordonnées */
	public static final int POSITION_INITIALE_Y = (Affichage.HAUTEUR_COMPOSANT - Piste.ESPACEMENT_Y - HAUTEUR_IMG);
	
	/* Valeur incrémentée ou décrémentée en fonction de la direction dans laquelle le personnage se déplace */
	public static final int MOUVEMENT = 20;
	
	/* Position du personnage sur l'axe des abscisses */
	private int positionX;
	
	/* Etat du personnage */
	private int etat;
	
	/* Permet le déplacement du personnage vers la gauche */
	public boolean left;
	
	/* Permet le déplacement du personnage vers la droite */
	public boolean right;
	
	/* Permet de déterminer lorsque le personnage est touché par un obstacle */
	public boolean isTouched;
	
	/* Permet de déterminer lorsque le personnage a atteint le point de contrôle */
	public boolean reachedCheckpoint;
	
	private FinPartie finPartie;
	
	/**
	 * Constructeur de la classe
	 * @param finPartie
	 */
	public EtatNaruto(FinPartie finPartie) {
		this.finPartie = finPartie;
		
		this.positionX = POSITION_INITIALE_X;
		this.etat = 0;
		this.left = false;
		this.right = false;
		this.isTouched = false;
		this.reachedCheckpoint = false;
		
		this.start();
	}
	
	/**
	 * Modifie l'état du personnage (l'image affichée)
	 */
	private void setEtat() {
		this.etat++;
		
		if(this.etat >= NB_IMG) {
			this.etat = 0;
		}
	}
	
	/**
	 * Retourne l'état du personnage
	 * @return l'état du personnage
	 */
	public int getEtat() {
		return this.etat;
	}
	
	/**
	 * Retourne la position sur l'axe des abscisses du personnage
	 * @return la position sur l'axe des abscisses du personnage
	 */
	public int getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Déplace le personnage vers la droite (incrémente la position sur l'axe des abscisses du personnage)
	 */
	public void moveToRight() {
		if(this.positionX <= (LARGEUR_MAX - LARGEUR_IMG)) {
			this.positionX += MOUVEMENT;
		}
	}
	
	/**
	 * Déplace le personnage vers la gauche (décémente la position sur l'axe des abscisses du personnage)
	 */
	public void moveToLeft() {
		if(this.positionX >= LARGEUR_MIN) {
			this.positionX -= MOUVEMENT;
		}
	}
	
	/**
	 * Modifie l'état du personnage tant que la partie n'est pas terminée
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			this.setEtat();
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.getStackTrace();
			}
			
		}
	}
}