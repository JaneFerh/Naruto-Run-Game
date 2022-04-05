package model;

import javax.swing.JOptionPane;

import control.ControlFenetrePrincipale;
import control.ControlPersonnage;
import view.Affichage;
import view.FenetreAccueil;
import view.FenetrePrincipale;
import view.VueDecor;
import view.VueNaruto;
import view.VueObstacle;
import view.VuePiste;
import view.VuePointControle;
import view.VueProfilNaruto;

/**
 * Détermine les actions effectuées aux boutons de la fenêtre d'accueil et
 * permet de lancement de la fenêtre de jeu, l'affichage des scores et l'arrêt
 * de l'application
 */
public class EtatAccueil {
	
	/* Fenêtre à fermer ou à rendre invisible */
	private FenetreAccueil fenetreAccueil;
	
	/**
	 * Récupère la fenêtre à fermer ou à rendre invisible
	 * @param fenetreAccueil fenêtre à fermer ou à rendre invisible
	 */
	public void setFrame(FenetreAccueil fenetreAccueil) {
		this.fenetreAccueil = fenetreAccueil;
	}
	
	/**
	 * Ouvre une nouvelle fenêtre permettant au joueur de jouer
	 */
	public void start() {
		
		// Affichage du guide du jeu
		String message = 
				"Afin d'entamer un nouvel entraînement pour Naruto, Kakashi a décidé de tout d'abord tester ses aptitudes.\n"
				+ "Pour cela, il a mis en place un parcours rempli d'obstacles sur lequel Naruto devra tenir le plus longtemps possible.\n\n"
				+ "Aidez Naruto à esquiver le plus d'obstacles possibles et à atteindre Kakashi dans le temps imparti !";
		
		JOptionPane option = new JOptionPane();
		int retour = option.showConfirmDialog(null, message, "Guide du jeu", JOptionPane.DEFAULT_OPTION);
		
		// Lorsque la touche "OK" est cliquée, on affiche la fenêtre de jeu
		if(retour == JOptionPane.OK_OPTION) {

			this.fenetreAccueil.setVisible(false); // La fenêtre d'accueil est rendue invisible
			
			// Score du joueur
			Score score = new Score();
			// Vitesse du personnage
			Vitesse vitesse = new Vitesse();
			// Temps restant
			Decompte decompte = new Decompte(vitesse);
			// Détermine la fin de partie
			FinPartie finPartie = new FinPartie(score, vitesse, decompte);
			// Détermine les mouvement qu'effectue le personnage
			EtatNaruto etatNaruto = new EtatNaruto(finPartie);
			// Détermine les points de la piste
			Piste piste = new Piste(etatNaruto, vitesse);
			// Décale l'emplacement des points de la piste
			Avancer avancer = new Avancer(piste, score, finPartie);
			// Génère un point de contrôle à un certain score
			EtatPointControle etatPointControle = new EtatPointControle(piste, score, decompte, finPartie);
			// Génère des obstacles aléatoires
			EtatObstacle etatObstacle = new EtatObstacle(piste, etatNaruto, vitesse, score, finPartie);
			// Détermine l'image affichée par le profil du personnage
			EtatProfilNaruto etatProfilNaruto = new EtatProfilNaruto(etatNaruto, etatPointControle, finPartie);
			// Génère des décors à l'horizon
			EtatDecorHorizon etatDecorHorizon = new EtatDecorHorizon(finPartie);
			// Affiche le personnage
			VueNaruto vueNaruto = new VueNaruto(etatNaruto);
			// Affiche les obstacles
			VueObstacle vueObstacle = new VueObstacle(etatObstacle, piste);
			// Affiche les décors
			VueDecor vueDecor = new VueDecor(etatDecorHorizon);
			// Affiche la piste
			VuePiste vuePiste = new VuePiste(piste);
			// Affiche le point de contrôle
			VuePointControle vuePointControle = new VuePointControle(etatPointControle, piste);
			// Affiche le profil du personnage
			VueProfilNaruto vueProfilNaruto = new VueProfilNaruto(etatProfilNaruto);
			// Affiche le composant contenant le jeu
			Affichage affichage = new Affichage(decompte, score, vitesse, vueNaruto, vueObstacle, vueDecor, vuePiste, vuePointControle, vueProfilNaruto, finPartie);
			// Provoque le déplacement du personnage
			ControlPersonnage controlPersonnage = new ControlPersonnage(affichage, etatNaruto, finPartie);
			// Fenetre sur lequel figure le jeu
			FenetrePrincipale fenetrePrincipale = new FenetrePrincipale(this.fenetreAccueil, affichage);
			// Provoque la fermeture de la fenêrte principale et l'affichage de la fenêrtre d'accueil
			ControlFenetrePrincipale controlFenetrePrincipale = new ControlFenetrePrincipale(this.fenetreAccueil, fenetrePrincipale, finPartie);
		}
	}
	
	/**
	 * Affiche le score des meilleurs joueurs
	 * @throws IOException 
	 */
	public void score() {
		//affichage des 10 meilleures scores
		HighScore hs = new HighScore(new String[]{"Nom","Score"},
					    new int[][]{{1,0},{0,1}},10,"MeilleureScore.txt"," :---: ");
			 StringBuilder sb = new StringBuilder("<html>");
			 for(int i=0;i<hs.getNbLines();i++) {
			        sb.append("<font size=10>").append("<center>")
			          .append(hs.getLigne(i)[0] ).append(" ").append(hs.getLigne(i)[1])
			          .append("</center></font><br>");
			 }
			 String content = sb.append("</html>").toString();
			JOptionPane.showMessageDialog(null, content, "Les 10 Meilleures Scores", JOptionPane.PLAIN_MESSAGE);
		
	}
	
	/**
	 * Termine le programme
	 */
	public void quit() {
		System.exit(0);
	}
}