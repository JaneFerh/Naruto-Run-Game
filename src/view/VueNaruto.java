package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import model.EtatNaruto;

/**
 * Dessine le personnage au sein du composant
 */
public class VueNaruto {
	
	/* Permet de récupérer la position et l'état du personnage */
	private EtatNaruto etatNaruto;
	
	/* Liste d'images représentatives du personnage se déplaçant vers la gauche */
	private ArrayList<Image> left;
	
	/* Liste d'images représentatives du personnage se déplaçant vers le milieu */
	private ArrayList<Image> middle;
	
	/* Liste d'images représentatives du personnage se déplaçant vers la droite */
	private ArrayList<Image> right;
	
	/**
	 * Constructeur de la classe
	 * @param etatNaruto
	 */
	public VueNaruto(EtatNaruto etatNaruto) {
		this.etatNaruto = etatNaruto;
		
		this.left = new ArrayList<Image>();
		this.middle = new ArrayList<Image>();
		this.right = new ArrayList<Image>();
		
		this.fillList();
	}
	
	/**
	 * Remplit les listes d'images du personnage
	 */
	private void fillList() {
		for(int i=1 ; i<=EtatNaruto.NB_IMG ; i++) {
			try {
				this.left.add(ImageIO.read(new File("ressources/naruto_run/toLeft/naruto_left_"+i+".png")));
				this.middle.add(ImageIO.read(new File("ressources/naruto_run/toMiddle/naruto_middle_"+i+".png")));
				this.right.add(ImageIO.read(new File("ressources/naruto_run/toRight/naruto_right_"+i+".png")));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Dessine au sein du composant le personnage
	 * @param pinceau
	 */
	public void drawNaruto(Graphics pinceau) {
		Image image;
		
		if(this.etatNaruto.left) {
			image = this.left.get(this.etatNaruto.getEtat());
		}
		else if(this.etatNaruto.right) {
			image = this.right.get(this.etatNaruto.getEtat());
		}
		else {
			image = this.middle.get(this.etatNaruto.getEtat());
		}
		
		pinceau.drawImage(image, this.etatNaruto.getPositionX(), EtatNaruto.POSITION_INITIALE_Y, EtatNaruto.LARGEUR_IMG, EtatNaruto.HAUTEUR_IMG, null);
	}
}