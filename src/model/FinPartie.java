package model;

import java.io.File;

import javax.swing.JOptionPane;

/**
 * Détermine la fin de partie lorsque la vitesse du personnage ou le temps restant atteint zéro
 * et affiche le score final du joueur
 */
public class FinPartie {

	/* Permet de récupérer le score du joueur */
	private Score score;
	
	/* Permet de récupérer la vitesse du personnage */
	private Vitesse vitesse;
	
	/* Permet de récupérer le temps restant */
	private Decompte decompte;
	
	/* Détermine lorsque la fenêtre de jeu se ferme avant d'avoir terminé la partie */
	private boolean isClosing;
	
	/* Nombre de points de contrôle atteints */
	private int nombrePointsControle;
	
	/* Nombre d'obstacles touchés */
	private int nombreObstacles;
	
	/*permet de recuperer le meilleure score*/
	private HighScore hs;
	
	/* Permet de récupérer les points permettant le traçage de la piste */
	public Piste piste;
	/**
	 * Constructeur de la classe
	 * @param score
	 * @param vitesse
	 * @param decompte
	 */
	public FinPartie(Score score, Vitesse vitesse, Decompte decompte) {
		this.score = score;
		this.vitesse = vitesse;
		this.decompte = decompte;
		
		this.isClosing = false;
		this.nombrePointsControle = 0;
		this.nombreObstacles = 0;
	}
	
	/**
	 * Détermine que la fenêtre de jeu a été fermée avant que la partie ne se termine
	 */
	public void setIsClosing() {
		this.isClosing = true;
	}
	
	/**
	 * Incrémente le nombre de points de contrôle atteints
	 */
	public void increaseCheckpoints() {
		this.nombrePointsControle++;
	}
	
	/**
	 * Incrémente le nombre d'obstacles touchés
	 */
	public void increaseObstacles() {
		this.nombreObstacles++;
	}
	
	/**
	 * Détermine la fin de partie lorsque la vitesse du personnage ou le temps restant
	 * atteint zéro
	 * @return vrai si le temps restant ou la vitesse du personnage a atteint zéro, faux sinon
	 */
	public boolean gameOver() {
		return (this.isClosing || this.vitesse.speedAtZero() || this.decompte.runOutOfTime());
	}
	
	/**
	 * Affiche le score du joueur lorsque la partie se termine correctement
	 */
	public void showGameOver() {
		//AudioJeu.playSound("ressources/Son/partiePerdue.wav");
		if(!this.isClosing) {
			/*  HighScore hs = new HighScore()
			 * permet de cr�er un meilleure score � deux colonnes (Nom du joueur et don Score) qui sera tri� d'abord sur la colonne
			 * Score(colonne 1) en d�croissant et si 2 scores sont identiques, alors il sera tri�
			 * sur la premi�re colonne(colonne 0) en croissant pour ces 2 on a (int[][]({1,0},{0,1})).
			 * L'highScore comportera au maximum 10 lignes et sera enregistr� dans un fichier "MeilleureScore.txt" 
			 * 
			 */
			AudioJeu.playSound(new File("ressources/Son/partiePerdue.wav"));
		 hs = new HighScore(new String[]{"Nom","Score"},
				    new int[][]{{1,0},{0,1}},10,"MeilleureScore.txt"," :---: ");
		/*pour recuperer le nom de joueur*/
		 String NOM = JOptionPane.showInputDialog("Entrez votre NOM");
		 int nouveauScore = this.score.getScore();
		 hs.addLigne(new Comparable[]{NOM ,nouveauScore});
		 StringBuilder sb = new StringBuilder("<html>");
		 for(int i=0;i<hs.getNbLines();i++) {
		        sb.append("<font size=10 color=").append("><center>")
		          .append(hs.getLigne(i)[0] ).append(" ").append(hs.getLigne(i)[1])
		          .append("</center></font><br>");
		 }

		 /*message de fin de partie*/
		 String message = "Votre Score: " + this.score.getScore() + "\n"
					+"> Bonus " + Score.CHECKPOINT + "pts (points de contrôle atteints): +" + (this.nombrePointsControle * Score.CHECKPOINT)+"\n"
					+"> Malus " + Score.OBSTACLE + "pts (obstacles touchés): -" + (this.nombreObstacles * Score.OBSTACLE);
	
		 JOptionPane.showMessageDialog(null, message, "Vous avez perdu !", JOptionPane.INFORMATION_MESSAGE);
		}
	
	}
}
