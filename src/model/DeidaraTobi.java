package model;

import view.Affichage;

/**
 * Classe représentrative d'un lot de personnages qui se déplace de droite (resp. gauche) à gauche (resp. droite)
 * et qui rerprésente un décor (elle n'a aucune incidence sur le personnage du joueur)
 */
public class DeidaraTobi extends DecorMobile {
	
	/* Temps de repos */
	public static final int PAUSE = 90;
	
	/* Nombre d'images du décor */
	public static final int NB_IMAGES = 6;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/**
	 * Constructeur de la classe
	 * @param finPartie
	 */
	public DeidaraTobi(FinPartie finPartie) {
		super("decors/deidara_tobi/deidara_tobi", NB_IMAGES);
		this.finPartie = finPartie;
		
		this.positionX = (this.isMovingToRight ? -this.getWidth() : Affichage.LARGEUR_COMPOSANT);
		this.positionY = 0;
		
		this.start();
	}
	
	/**
	 * Détermine si le décor est sorti des bordures de la fenêtre
	 * @return vrai si le décor est sorti des bordures de la fenêtre, faux sinon
	 */
	@Override
	public boolean isRemovable() {
		return (this.isMovingToRight ? (this.positionX >= Affichage.LARGEUR_COMPOSANT) : (this.positionX <= -this.getWidth()));
	}
	
	/**
	 * Effectue le déplacement du décor vers la direcion indiquée par isMovingToRight
	 * tant que la partie n'est pas terminée
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			// Si le décor est hors de la fenêtre, le décor se déplace de l'autre côté
			if(this.isRemovable()) {
				this.setIsMovingToRight();
			}
			
			this.setEtat(); // Modifie l'image affichée
			
			// Déplacement vers la droite
			if(this.isMovingToRight) {
				this.moveToRight();
			}
			
			// Déplacement vers la gauche
			else {
				this.moveToLeft();
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
