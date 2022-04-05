package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Decor;
import model.EtatDecorHorizon;

/**
 * Dessine le fond d'écran de l'horizon, la pelouse et les décors de l'horizon au sein du composant
 */
public class VueDecor {
	
	/* Permet de récupérer les décors de l'horizon */
	private EtatDecorHorizon etatDecorHorizon;
	
	/* Image du fond d'écran de l'horizon */
	private Image fond;
	
	/* Image de la pelouse */
	private Image pelouse;
	
	/**
	 * Constructeur de la classe
	 * @param etatDecorHorizon
	 */
	public VueDecor(EtatDecorHorizon etatDecorHorizon) {
		this.etatDecorHorizon = etatDecorHorizon;
		
		// Récupèration des images
		try {
			this.fond = ImageIO.read(new File("ressources/decors/background.png"));
			this.pelouse = ImageIO.read(new File("ressources/decors/grass.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Dessine le fond d'écran de l'horizon
	 * @param pinceau
	 */
	public void drawBackground(Graphics pinceau) {
		pinceau.drawImage(this.fond, 0, 0, Affichage.LARGEUR_COMPOSANT, Affichage.COORD_Y_HORIZON, null);
	}
	
	/**
	 * Dessine la pelouse
	 * @param pinceau
	 */
	public void drawGrass(Graphics pinceau) {
		pinceau.drawImage(this.pelouse, 0, Affichage.COORD_Y_HORIZON, Affichage.LARGEUR_COMPOSANT, (Affichage.HAUTEUR_COMPOSANT - Affichage.COORD_Y_HORIZON), null);
	}
	
	/**
	 * Dessine les décors de l'horizon
	 * @param pinceau
	 */
	public void drawDecorsHorizon(Graphics pinceau) {
		Decor[] decors = this.etatDecorHorizon.getDecorsHorizon();
		
		for(int i=0 ; i<decors.length ; i++) {
			pinceau.drawImage(decors[i].getImage(), decors[i].getPositionX(), decors[i].getPositionY(), decors[i].getWidth(), decors[i].getHeight(), null);
		}
	}
}