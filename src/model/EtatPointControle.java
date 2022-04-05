package model;

/**
 * Génère un point de contrôle lorsque le score du joueur atteint un certain score
 */
public class EtatPointControle extends Thread {
	
	/* Temps de repos */
	public static final int PAUSE = 100;
	
	/* Largeur de l'image */
	public static final int LARGEUR_IMG = 40;
	
	/* Hauteur de l'image */
	public static final int HAUTEUR_IMG = 80;
	
	/* Valeur à incrémenter à la valeur de seuil */
	public static final int SEUIL = 500;
	
	/* Permet de récupérer la position de la piste */
	private Piste piste;
	
	/* Permet d'incrémenter le score lorsque le personnage atteint le point de contrôle */
	private Score score;
	
	/* Permet d'incrémenter le temps restant lorsque le personnage atteint le point de contrôle */
	private Decompte decompte;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/* Valeur à laquelle apparaîtra le point de contrôle */
	private int seuil;
	
	/* Classe représentative du point de contrôle */
	private PointControle pointControle;
	
	/* Détermine si le point de contrôle a été atteint */
	public boolean isReached;
	
	/**
	 * Constructeur de la classe
	 * @param piste
	 * @param score
	 * @param decompte
	 * @param finPartie
	 */
	public EtatPointControle(Piste piste, Score score, Decompte decompte, FinPartie finPartie) {
		this.piste = piste;
		this.score = score;
		this.decompte = decompte;
		this.finPartie = finPartie;
		
		this.isReached = false;
		this.seuil = SEUIL;
		this.pointControle = null;
		
		this.start();
	}
	
	/**
	 * Incrémente la valeur à laquelle apparaîtra le point de contrôle
	 */
	public void increaseLimit() {
		this.seuil += SEUIL;
	}
	
	/**
	 * Modifie l'emplacement du point de contrôle et incrémente la valeur à laquelle apparaîtra
	 * le point de contrôle lorsque le point de contrôle est atteint
	 */
	public void spawnCheckpoint() {
		if(this.score.getScore() >= this.seuil) {
			this.pointControle = new PointControle(this, this.piste, this.score, this.decompte, this.finPartie);
			this.increaseLimit();
		}
	}
	
	/**
	 * Retourne le point de contrôle
	 * @return le point de contrôle
	 */
	public PointControle getCheckpoint() {
		return this.pointControle;
	}
	
	/**
	 * Vérifie que le point de contrôle est atteint, dans ce cas, l'emplacement du point de contrôle
	 * est modifié et la valeur à laquelle apparaîtra le point de contrôle est incrémentée tant que
	 * la partie n'est pas terminée
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			this.spawnCheckpoint();
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
