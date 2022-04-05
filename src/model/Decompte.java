package model;

/**
 * Modifie le temps restant au joueur pour atteindre le prochain point de contrôle
 */
public class Decompte extends Thread {
	
	/* Temps de repos */
	public static final int PAUSE = 1000;
	
	/* Temps initial donné au joueur pour atteindre le prochain point de contrôle */
	public static final int TEMPS_INITIAL = 30;
	
	/* Permet de récupérer la vitesse du personnage */
	private Vitesse vitesse;
	
	/* Temps restant au joueur pour atteindre le prochain point de contrôle */
	private int decompte;
	
	/* Temps variable à ajouter au temps restant */
	private int temps;
	
	
	/**
	 * Constructeur de la classe
	 * @param vitesse
	 */
	public Decompte(Vitesse vitesse) {
		this.vitesse = vitesse;
		this.decompte = TEMPS_INITIAL;
		this.temps = TEMPS_INITIAL;
		
		this.start();
	}
	
	/**
	 * Décrémente le temps restant
	 */
	private void decreaseTime() {
		if(this.decompte > 0) {
			this.decompte--;
		}
	}
	
	/**
	 * Retourne le temps restant au joueur pour atteindre le prochain point de contrôle
	 * @return le temps restant
	 */
	public int getTime() {
		return this.decompte;
	}
	
	/**
	 * Incrémente le temps restant
	 */
	public void increaseTime() {
		this.temps--;
		this.decompte += this.temps;
	}
	
	/**
	 * Détermine si le temps restant a atteint zéro
	 * @return vrai si le temps restant a atteint zéro, faux sinon
	 */
	public boolean runOutOfTime() {
		return (this.decompte <= 0);
	}
	
	/**
	 * Décrémente le temps restant tant que celui-ci ou la vitesse du personnage
	 * n'a pas atteint zéro
	 */
	public void run() {
		// Le temps continue d'être décrémenté tant que le decompte n'atteint pas zéro
		while(!this.runOutOfTime() || !this.vitesse.speedAtZero()) {
			
			this.decreaseTime();
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
