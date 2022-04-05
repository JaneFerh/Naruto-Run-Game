package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Provoque l'affichage d'une image au sein d'un bouton lorsque la souris survole le composant associé à ce contrôleur
 */
public class ControlSouris implements MouseListener {
	
	/* Bouton dans lequel sera affichée l'image */
	private JButton bouton;
	
	/* Image à afficher */
	private BufferedImage image;
	
	/**
	 * Constructeur de la classe
	 * @param bouton
	 * @param image
	 */
	public ControlSouris(JButton bouton, BufferedImage image) {
		this.bouton = bouton;
		this.image = image;
	}
	
	/**
	 * Lorsque le composant associé à ce contrôleur est survolé, le bouton en argument de la classe affiche une image
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		this.bouton.setIcon(new ImageIcon(this.image)); // ajoute l'image
	}
	
	/**
	 * Lorsque le composant associé à ce contrôleur n'est plus survolé, le bouton en argument de la classe n'affiche plus d'image
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		this.bouton.setIcon(null); // retire l'image
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}	
}
