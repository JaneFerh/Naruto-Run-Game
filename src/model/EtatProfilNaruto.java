package model;

/**
 * Modifie l'état du profil du personnage lorsque le personnage atteint le point de contrôle 
 * ou est touché par un obstacle
 */
public class EtatProfilNaruto extends Thread {

	/* Délai de maintenance de l'image lorsque le personnage est blessé ou heureux */
	public static final int PAUSE_SUR_IMAGE = 600;
	
	/* Délai avant que le profil ne change */
	public static final int PAUSE = 50;
	
	/* Nombre d'images */
	public static final int NB_IMAGES = 3;
	
	/* Permet de déterminer lorsque le personnage a touché un obstacle  */
	private EtatNaruto etatNaruto;
	
	/* Permet de déterminer lorsque le personnage a atteint le point de contrôle */
	private EtatPointControle etatPointControle;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/* Etat du profil */
	private int etat;
	
	/**
	 * Constructeur de la classe
	 * @param etatNaruto
	 * @param etatPointControle
	 * @param finPartie
	 */
	public EtatProfilNaruto(EtatNaruto etatNaruto, EtatPointControle etatPointControle, FinPartie finPartie) {
		this.etatNaruto = etatNaruto;
		this.etatPointControle = etatPointControle;
		this.finPartie = finPartie;
		
		this.etat = 0;
		
		this.start();
	}
	
	/**
	 * Retourne l'état du profil
	 * @return l'état du profil
	 */
	public int getEtat() {
		return this.etat;
	}
	
	/**
	 * Modifie l'état du profil afin qu'elle affiche une image
	 * du personnage blessé
	 */
	private void stateIsHurt() {
		this.etat = 2;
	}
	
	/**
	 * Modifie l'état du profil afin qu'elle affiche une image
	 * du personnage heureux
	 */
	private void stateIsHappy() {
		this.etat = 1;
	}
	
	/**
	 * Modifie l'état du profil afin qu'elle affiche une image
	 * du personnage indifférent
	 */
	private void stateIsNormal() {
		this.etat = 0;
	}
	
	/**
	 * Modifie l'état du profil du personnage tant que la partie
	 * n'est pas terminée
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			// Si le personnage a touché un obstacle
			if(this.etatNaruto.isTouched) {
				
				this.stateIsHurt();
				
				// L'image reste afficher pendant un certain temps
				try {
					Thread.sleep(PAUSE_SUR_IMAGE);
				}
				
				catch(Exception e) {
					e.printStackTrace();
				}
				
				this.stateIsNormal();
				this.etatNaruto.isTouched = false;
			}
			// Si le personnage a atteint le point de contrôle
			else if(this.etatPointControle.isReached) {
				
				this.stateIsHappy();
				
				// L'image reste afficher pendant un certain temps
				try {
					Thread.sleep(PAUSE_SUR_IMAGE);
				}
				
				catch(Exception e) {
					e.printStackTrace();
				}
				
				this.stateIsNormal();
				this.etatPointControle.isReached = false;
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
