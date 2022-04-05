package model;

/**
 * Gère le score du joueur en l'incrémentant ou en le décrémentant
 */
public class Score {

	/* Temsp de repos */
	public static final int PAUSE = 200;
	
	/* Valeur à incrémenter au fil du temps */
	public static final int TIME = 10;
	
	/* Valeur à incrémenter lorsque le personnage atteint le point de contrôle */
	public static final int CHECKPOINT = 20;
	
	/* Valeur à incrémenter lorsque le personnage dépasse un adversaire */
	public static final int OPPONENT = 50;
	
	/* Valeur à décrémenter lorsque le personnage est touché par un obstacle */
	public static final int OBSTACLE = 10;
	
	/* Score du joueur */
	private int score;
	
	/**
	 * Constructeur de la classe
	 */
	public Score() {
		this.score = 0;
	}
	
	/**
	 * Retourne le score du joueur
	 * @return le score du joueur
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Incrémente le score au fil du temps
	 */
	public void increaseByTime() {
		this.score += TIME;
	}
	
	/**
	 * Incrémente le temps lorsque le personnage atteint un point de contrôle
	 */
	public void increaseByCheckpoint() {
		this.score += CHECKPOINT;
	}
	
	/**
	 * Incrémente le score lorsque le personnage dépasse un adversaire
	 */
	public void increaseByOpponent() {
		this.score += OPPONENT;
	}
	
	/**
	 * Décrémente le score lorsque le personnage est touché par un obstacle
	 */
	public void decreaseByObstacle() {
		this.score -= TIME;
	}
}
