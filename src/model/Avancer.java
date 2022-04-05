package model;

/**
 * Incrémente la valeur de décalage afin de simuler l'avancement du personnage et
 * incrémente le score du joueur
 */
public class Avancer extends Thread {
	
	/* Temps de repos */
	public static final int PAUSE = 150;
	
	/* Permet d'incrémenter la valeur de décalage */
	private Piste piste;
	
	/* Permet d'incrémenter le score du joueur */
	private Score score;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/**
	 * Constructeur de la classe
	 * @param piste
	 * @param score
	 * @param finPartie
	 */
	public Avancer(Piste piste, Score score, FinPartie finPartie) {
		this.piste = piste;
		this.score = score;
		this.finPartie = finPartie;
		
		this.start();
	}
	
	/**
	 * Incrémente la valeur de décalage et le score du joueur tant que le temps
	 * restant ou la vitesse du personnage n'a pas atteint zéro
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			this.piste.setPositionY();
			this.score.increaseByTime();
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}