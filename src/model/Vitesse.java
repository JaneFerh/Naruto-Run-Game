package model;

/**
 * Gère la vitesse du personnage en l'incrémentant ou en le décrémentant
 */
public class Vitesse {
	
	/* Vitesse initiale du personnage */
	public static final int VITESSE_INITIALE = 30;
	
	/* Valeur la plus grande que puisse prendre l'accélération du personnage */
	public static final int VITESSE_MAX = Piste.DEPLACEMENT*8;
	
	/* Valeur la plus petite que puisse prendre la décélération du personnage */
	public static final int VITESSE_MIN = 0;
	
	/* Valeur à incrémenter en cas d'accélération */
	public static final int ACCELERE = Piste.DEPLACEMENT/2;
	
	/* Valeur à décrémenter en cas de déccélération */
	public static final int DECELERE = Piste.DEPLACEMENT/2;
	
	/* Vitesse du personnage */
	private int vitesse;
	
	/**
	 * Constructeur de la classe
	 */
	public Vitesse() {
		this.vitesse = VITESSE_INITIALE;
	}
	
	/**
	 * Retourne la vitesse du personnage
	 * @return la vitesse du personnage
	 */
	public int getVitesse() {
		return this.vitesse;
	}
	
	/**
	 * Incrémente la vitesse du personnage
	 */
	public void accelerate() {
		if( (this.vitesse + ACCELERE) <= VITESSE_MAX) {
			this.vitesse += ACCELERE;
		}
	}
	
	/**
	 * Décrémente la vitesse du personnage
	 */
	public void decelerate() {
		if( (this.vitesse - DECELERE) >= VITESSE_MIN) {
			this.vitesse -= DECELERE;
		}
		else {
			this.vitesse = 0;
		}
	}
	
	/**
	 * Détermine lorsque la vitesse du personnage a atteint zéro
	 * @return vrai si la vitesse du personnage atteint zéro, faux sinon
	 */
	public boolean speedAtZero() {
		return (this.vitesse <= 0);
	}
}