package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.EtatProfilNaruto;

/**
 * Dessine le profil du personnage au sein du composant
 */
public class VueProfilNaruto extends Thread {
	
	/* Permet de récupérer l'état du profil du personnage */
	private EtatProfilNaruto etatProfilNaruto;
	
	/* Liste contenant des images du profil du personnage */
	private ArrayList<Image> images;
	
	/**
	 * Constructeur de la classe
	 * @param etatProfilNaruto
	 */
	public VueProfilNaruto(EtatProfilNaruto etatProfilNaruto) {
		this.etatProfilNaruto = etatProfilNaruto;
		
		this.images = new ArrayList<Image>();
		
		this.loadImages();
	}
	
	/**
	 * Récupère les images du profil du personnage
	 */
	public void loadImages() {
		for(int i=1 ; i<=EtatProfilNaruto.NB_IMAGES ; i++) {
			try {
				this.images.add(ImageIO.read(new File("ressources/naruto_profil/naruto_profil_"+i+".png")));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Dessine le profil du personnage
	 * @param pinceau
	 */
	public void drawProfilNaruto(Graphics pinceau) {
		pinceau.drawImage(this.images.get(this.etatProfilNaruto.getEtat()), (Affichage.LARGEUR_COMPOSANT/2 - this.images.get(0).getWidth(null)), 0, this.images.get(0).getWidth(null), this.images.get(0).getHeight(null), null);
	}
}
