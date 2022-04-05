package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import control.ControlAccueil;
import control.ControlSouris;

/**
 * Composant contenant un fond d'écran et des boutons permettant d'accéder à la suite de l'application
 */
public class VueAccueil extends JPanel {
	
	/* Valeur en abscisse du premier point permettant l'affichage du fond */
	public static final int POSITION_INITIALE_X = 0;
	
	/* Valeur en ordonnée du premier point permettant l'affichage du fond */
	public static final int POSITION_INITIALE_Y = 0;
	
	/* Contrôleur qui effectue une action lorsqu'un bouton est cliqué */
	private ControlAccueil controlAccueil;
	
	/* Image en fond */
	private BufferedImage imageFond;
	
	/* Image du curseur */
	private BufferedImage imageCurseur;
	
	/**
	 * Constructeur de la classe
	 * @param controlAccueil
	 */
	public VueAccueil(ControlAccueil controlAccueil) {
		this.controlAccueil = controlAccueil;
		
		this.setPreferredSize(new Dimension(Affichage.LARGEUR_COMPOSANT, Affichage.HAUTEUR_COMPOSANT)); // Taille du composant
		this.setLayout(new GridBagLayout()); // On associe une mise en page
		
		this.loadImages();
		this.initializeButtons(new String[] { "Start", "Score", "Quit" });
	}
	
	/**
	 * Récupération des images
	 */
	private void loadImages() {
		try {
			this.imageFond = ImageIO.read(new File("ressources/home/home.jpg"));
			this.imageCurseur = ImageIO.read(new File("ressources/home/cursor.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ajoute au sein du composant des boutons dont les intitulés sont en arguments
	 * @param nomBoutons tableau regroupant le nom des boutons à créer
	 */
	private void initializeButtons(String[] nomBoutons) {
		JButton curseurBouton, nomBouton;
		int i;
		
		for(i=0 ; i<nomBoutons.length ; i++) {
			curseurBouton = new JButton();
			curseurBouton.setPreferredSize(new Dimension(this.imageCurseur.getWidth(), this.imageCurseur.getHeight()));
			this.disableOptions(curseurBouton);
			this.add(curseurBouton, this.setGridBagConstraints(0, i, new Insets(20,0,0,0)));
			
			nomBouton = new JButton(nomBoutons[i]);
			nomBouton.setForeground(Color.BLACK);
			nomBouton.setFont(new Font("8-bit Limit R (BRK)", Font.BOLD, 30));
			this.disableOptions(nomBouton);
			this.add(nomBouton, this.setGridBagConstraints(1, i, new Insets(20,0,0,80)));
			
			nomBouton.addMouseListener(new ControlSouris(curseurBouton, this.imageCurseur));
			nomBouton.addActionListener(this.controlAccueil);
		}
	}
	
	/**
	 * Désactive les options qui permettent de voir le contour, le fond et le focus du bouton
	 * @param bouton bouton sur lequel doivent être désactivées les options
	 */
	private void disableOptions(JButton bouton) {
		bouton.setContentAreaFilled(false);
		bouton.setBorderPainted(false);
		bouton.setFocusPainted(false);
	}
	
	/**
	 * Retourne une mise en page
	 * @param gridx numéro de la colonne occupée
	 * @param gridy numéro de la ligne occupée
	 * @param insets espace autour du composant
	 * @return une mise en page
	 */
	private GridBagConstraints setGridBagConstraints(int gridx, int gridy, Insets insets) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = insets;
		return gbc;
	}
	
	/**
	 * Affiche une image en fond
	 */
	@Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		super.paintComponent(secondPinceau);
		pinceau.drawImage(this.imageFond, POSITION_INITIALE_X, POSITION_INITIALE_Y, Affichage.LARGEUR_COMPOSANT, Affichage.HAUTEUR_COMPOSANT, null);
	}
}
