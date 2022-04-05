package view;

import java.awt.Graphics;

import model.EtatPointControle;
import model.Piste;
import model.PointControle;

/**
 * Dessine le point de contrôle au sein du composant
 */
public class VuePointControle {
	
	/* Permet de récupérer le point de contrôle */
	private EtatPointControle etatPointControle;
	
	/* Permet de récupérer la valeur de décalage de la piste */
	private Piste piste;
	
	/**
	 * Constructeur de la classe
	 * @param etatPointControle
	 * @param piste
	 */
	public VuePointControle(EtatPointControle etatPointControle, Piste piste) {
		this.etatPointControle = etatPointControle;
		this.piste = piste;
	}
	
	/**
	 * Dessine un point de contrôle représenté par un personnage près de la piste
	 * @param pinceau
	 */
	public void drawCheckpoint(Graphics pinceau) {
		PointControle pointControle = this.etatPointControle.getCheckpoint(); // On récupère le point de contrôle

		if(pointControle != null) {
			pinceau.drawImage(
					pointControle.getImage(),
					(pointControle.getPositionX() - Affichage.ESPACEMENT_PISTE - pointControle.getWidth() - EtatPointControle.LARGEUR_IMG),
					(this.piste.getPositionY() - pointControle.getPositionY()),
					(pointControle.getWidth() + EtatPointControle.LARGEUR_IMG),
					(pointControle.getHeight() + EtatPointControle.HAUTEUR_IMG),
					null
			);
		}
	}
}
