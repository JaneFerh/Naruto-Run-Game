package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Decompte;
import model.FinPartie;
import model.Score;
import model.Vitesse;

/**
 * Composant sur lequel est dessiné un horizon, des décors, une piste, un personnage, le profil
 * du personnage, sa vitesse, le score du joueur et le temps restant au joueur pour atteindre le
 * prochain point de contrôle
 */
public class Affichage extends JPanel implements Runnable {
	
	/* Temps avant d'effectuer une mise à jour */
	public static final int PAUSE = 50;
	
	/* Largeur du composant */
	public static final int LARGEUR_COMPOSANT = 1000;
	
	/* Hauteur du composant */
	public static final int HAUTEUR_COMPOSANT = 600;
	
	/* Coordonnée en abscisse du premier point permettant le traçage de l'horizon */
	public static final int COORD_X_HORIZON = 0;
	
	/* Coordoonée en ordonnée des deux points permettant le traçage de l'horizon */
	public static final int COORD_Y_HORIZON = HAUTEUR_COMPOSANT/3;
	
	/* Espacement entre le milieu du composant et l'une des lignes verticales de la piste (afin de ne pas supperposer les deux lignes) */
	public static final int ESPACEMENT_PISTE = 35;
	
	/* Position en ordonnée du texte de la vitesse */
	public static final int POSITION_TXT_VITESSE = 60;
	
	/* Position en ordonnée du texte du score */
	public static final int POSITION_TXT_SCORE = 80;
	
	/* Position en ordonnée du texte du temps */
	public static final int POSITION_TXT_TEMPS = 100;
	
	/* Permet d'afficher le temps restant */
	private Decompte decompte;
	
	/* Permet d'afficher le score du joueur */
	private Score score;
	
	/* Permet d'afficher la vitesse du personnage */
	private Vitesse vitesse;
	
	/* Permet de dessiner le personnage */
	private VueNaruto vueNaruto;
	
	/* Permet de dessiner les obstacles */
	private VueObstacle vueObstacle;
	
	/* Permet de dessiner les décors et le fond d'écran de l'horizon */
	private VueDecor vueDecor;
	
	/* Permet de dessiner la piste */
	private VuePiste vuePiste;
	
	/* Permet de dessiner le point de contrôle */
	private VuePointControle vuePointControle;
	
	/* Permet de dessiner le profil du personnage */
	private VueProfilNaruto vueProfilNaruto;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/**
	 * Constructeur de la classe
	 * @param decompte
	 * @param score
	 * @param vitesse
	 * @param vueNaruto
	 * @param vueObstacle
	 * @param vueDecor
	 * @param vuePiste
	 * @param vuePointControle
	 * @param vueProfilNaruto
	 * @param finPartie
	 */
	public Affichage(Decompte decompte, Score score, Vitesse vitesse, VueNaruto vueNaruto, VueObstacle vueObstacle, VueDecor vueDecor, VuePiste vuePiste, VuePointControle vuePointControle, VueProfilNaruto vueProfilNaruto, FinPartie finPartie) {
		this.decompte = decompte;
		this.score = score;
		this.vitesse = vitesse;
		this.vueNaruto = vueNaruto;
		this.vueObstacle = vueObstacle;
		this.vueDecor = vueDecor;
		this.vuePiste = vuePiste;
		this.vuePointControle = vuePointControle;
		this.vueProfilNaruto = vueProfilNaruto;
		this.finPartie = finPartie;
		
		this.setPreferredSize(new Dimension(LARGEUR_COMPOSANT, HAUTEUR_COMPOSANT)); // Taille préférée
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		(new Thread(this)).start();
	}
	
	/**
	 * Dessine l'ensemble des éléments du jeu
	 * @param pinceau
	 */
	@Override
	public void paint(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();
		super.paint(secondPinceau);
		
		Graphics2D pinceau2D = (Graphics2D) secondPinceau;
		
		this.vueDecor.drawGrass(pinceau2D); // Pelouse
		this.vuePiste.drawPiste(pinceau2D); // Piste
		this.vueDecor.drawBackground(pinceau2D); // Image de fond de l'horizon
		this.vueDecor.drawDecorsHorizon(pinceau2D); // Décors de l'horizon
		this.vuePointControle.drawCheckpoint(pinceau2D); // Point de contrôle
		this.vueObstacle.drawObstacles(pinceau2D); // Obstacles
		
		this.drawVitesse(pinceau2D); // Vitesse du personnage
		this.drawScore(pinceau2D); // Score du joueur
		this.drawTimer(pinceau2D); // Temps restant
		
		this.vueProfilNaruto.drawProfilNaruto(pinceau2D); // Profil du personnage
		this.vueNaruto.drawNaruto(pinceau2D); // Personnage
	}
	
	/**
	 * Affiche la vitesse du personnage
	 * @param pinceau
	 */
	private void drawVitesse(Graphics pinceau) {
		pinceau.setFont(new Font("8-bit Limit R (BRK)", Font.BOLD, 15));
		pinceau.drawString("Vitesse - " + this.vitesse.getVitesse(), (LARGEUR_COMPOSANT/2), POSITION_TXT_VITESSE);
	}
	
	/**
	 * Affiche le score du joueur
	 * @param pinceau
	 */
	private void drawScore(Graphics pinceau) {
		pinceau.setFont(new Font("8-bit Limit R (BRK)", Font.BOLD, 15));
		pinceau.drawString("Score \t - " + this.score.getScore() + "pts", (LARGEUR_COMPOSANT/2), POSITION_TXT_SCORE);
	}
	
	/**
	 * Affiche le temps restant au joueur pour atteindre le prochain point de contrôle
	 * @param pinceau
	 */
	private void drawTimer(Graphics pinceau) {
		pinceau.setFont(new Font("8-bit Limit R (BRK)", Font.BOLD, 15));
		pinceau.drawString("Temps \t - " + this.decompte.getTime() + "s", (LARGEUR_COMPOSANT/2), POSITION_TXT_TEMPS);
	}
	
	/**
	 * Met à jour l'affichage tant que la partie n'est pas terminée
	 */
	@Override
	public void run() {
		
		while(!this.finPartie.gameOver()) {
			
			this.revalidate(); // Forcer la mise à jour
			this.repaint(); // Mettre à jour
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		this.repaint();
		this.finPartie.showGameOver(); // Affichage du score du joueur
	}
}