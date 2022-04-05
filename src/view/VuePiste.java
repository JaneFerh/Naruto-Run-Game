package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Path2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Piste;

/**
 * Dessine la piste au sein du composant
 */
public class VuePiste {

	/* Permet de récupérer les points de la piste */
	private Piste piste;
	
	/* Permet d'utiliser une image en couleur de pinceau */
	private TexturePaint texture;
	
	/**
	 * Constructeur de la classe
	 * @param piste
	 */
	public VuePiste(Piste piste) {
		this.piste = piste;
		
		// Récupération de l'image
		try {
			this.texture = new TexturePaint(ImageIO.read(new File("ressources/decors/track_texture.png")), new Rectangle(0, 0, 50, 50));
		}
		catch(IOException e) {
			this.texture = null;
			e.printStackTrace();
		}
	}
	
	/**
	 * Dessine la piste
	 * @param pinceau2D
	 */
	public void drawPiste(Graphics2D pinceau2D) {
		Point[] points = this.piste.getPiste(); // On récupère le tableau de points permettant le traçage de la piste
		Path2D path = new Path2D.Double();
		
		// S'il n'y a eu une d'error lors de la récupération de l'image
		if(this.texture != null) {
			pinceau2D.setPaint(texture); // On utilise l'image comme couleur
		}
		else {
			pinceau2D.setColor(new Color(241, 189, 136)); // On utilise une couleur par défaut
		}
		
		pinceau2D.setStroke(new BasicStroke(70, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Epaississement du trait
		
		// Parcourt de chaque point et traçage de la piste
		for(int i=1 ; i<points.length ; i++) {
			path.moveTo((double) points[i-1].x, (double) (points[i-1].y + this.piste.getPositionY()));
			path.lineTo((double) points[i].x, (double) (points[i].y + this.piste.getPositionY()));
		}
		
		path.closePath();
		pinceau2D.draw(path);
		
		// On remet les paramètres par défaut
		pinceau2D.setColor(Color.BLACK);
		pinceau2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	}
	
}