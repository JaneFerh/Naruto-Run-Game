package view;

import java.awt.Graphics;

import model.EtatObstacle;
import model.Obstacle;
import model.Piste;

/**
 * Dessine des obstacles au sein du composant
 */
public class VueObstacle {
	
	/* Permet de récupérer les obstacles */
	private EtatObstacle etatObstacle;
	
	/* Permet de récupérer la valeur de décalage */
	private Piste piste;
	
	/**
	 * Constructeur de la classe
	 * @param etatObstacle
	 * @param piste
	 */
	public VueObstacle(EtatObstacle etatObstacle, Piste piste) {
		this.etatObstacle = etatObstacle;
		this.piste = piste;
	}
	
	/**
	 * Dessine des obstacles
	 * @param pinceau
	 */
	public void drawObstacles(Graphics pinceau) {
		Obstacle[] obstacles = this.etatObstacle.getObstacles();
		int i;
		
		for(i=0 ; i<obstacles.length ; i++) {
			pinceau.drawImage(obstacles[i].getImage(), obstacles[i].getPositionX(), obstacles[i].getPositionY() + this.piste.getPositionY(), obstacles[i].getWidth(), obstacles[i].getHeight(), null);
		}
	}
}
