package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Génère et met à jour une liste d'obstacles
 */
public class EtatObstacle {
	
	/* Nombre d'images d'obstacles différents */
	public static final int NB_OBSTACLES = 5;
	
	/* Nombre d'obstacles sur la fenêtre */
	public static final int NB_MAX_OBSTACLES = 2;
	
	/* Valeur à incrémenter à la valeur en ordonnée du dernier obstacle de la piste lors de l'ajout d'un nouvel obstacle */
	public static final int ESPACEMENT_Y = ((Piste.HAUTEUR_MAX - Piste.HAUTEUR_MIN) / NB_MAX_OBSTACLES);
	
	/* Permet de récupérer la position de la piste */
	private Piste piste;
	
	/* Permet de récupérer la position du personnage */
	private EtatNaruto etatNaruto;
	
	/* Permet de décrémenter la vitesse lorsque le personnage est touché par un obstacle */
	private Vitesse vitesse;
	
	/* Permet de décrémenter le score lorsque le personnage est touché par un obstacle */
	private Score score;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/* Liste contenant des obstacles */
	private ArrayList<Obstacle> liste;
	
	/* Générateur de valeurs aléatoires */
	private Random rand;
	
	/* Chemins vers les images des obstacles */
	private String[][] ressources;
	
	/**
	 * Constructeur de la classe
	 * @param piste
	 * @param etatNaruto
	 * @param vitesse
	 * @param score
	 * @param finPartie
	 */
	public EtatObstacle(Piste piste, EtatNaruto etatNaruto, Vitesse vitesse, Score score, FinPartie finPartie) {
		this.piste = piste;
		this.vitesse = vitesse;
		this.etatNaruto = etatNaruto;
		this.score = score;
		this.finPartie = finPartie;
		
		this.liste = new ArrayList<Obstacle>();
		this.rand = new Random();
		this.ressources = new String[][] { {"obstacles/fuma_shuriken", "4"}, {"obstacles/kunai_explosif", "2"}, {"obstacles/kunai", "4"}, {"obstacles/shuriken", "3"}, {"obstacles/shurikens", "3"} };
		
		this.fillListe();
	}
	
	/**
	 * Remplit la liste de premiers obstacles
	 */
	private void fillListe() {
		int y = Piste.HAUTEUR_MAX;
		String[] chemin;
		
		for(int i=0 ; i<NB_MAX_OBSTACLES ; i++) {
			chemin = this.generatePath();
			this.liste.add(new Obstacle(chemin[0], Integer.parseInt(chemin[1]), y, this.etatNaruto, this.piste, this.vitesse, this.score, this.finPartie));
			y -= ESPACEMENT_Y;
		}
	}
	
	/**
	 * Retire le premier élément de la liste d'obstacles
	 */
	private void removeObstacle() {
		if((this.liste.size() > 0) && (this.liste.get(0).getPositionY() + this.piste.getPositionY()) > Piste.HAUTEUR_MAX) {
			this.liste.remove(0);
		}
	}
	
	/**
	 * Ajoute un nouvel obstacle au sein de la liste
	 */
	private void addObstacle() {
		if( (this.liste.get(this.liste.size()-1).getPositionY() + this.piste.getPositionY()) > (Piste.HAUTEUR_MIN + ESPACEMENT_Y) ) {
			String[] chemin = this.generatePath();
			this.liste.add(new Obstacle(chemin[0], Integer.parseInt(chemin[1]), (Piste.HAUTEUR_MIN - this.piste.getPositionY()), this.etatNaruto, this.piste, this.vitesse, this.score, this.finPartie));
		}
	}
	
	/**
	 * Retourne un chemin vers une image choisi aléatoirement
	 * @return un chemin vers une image
	 */
	private String[] generatePath() {
		return this.ressources[this.rand.nextInt(NB_OBSTACLES)];
	}
	
	/**
	 * Retourne la liste d'obstacles sous forme de tableau
	 * @return un tableau composée d'obstacles
	 */
	public Obstacle[] getObstacles() {
		Obstacle[] obstacles;
		
		this.removeObstacle();
		this.addObstacle();
		
		obstacles = new Obstacle[this.liste.size()];
		
		for(int i=0 ; i<obstacles.length ; i++) {
			obstacles[i] = this.liste.get(i);
		}
		
		return obstacles;
	}
}
