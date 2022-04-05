package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import view.Affichage;

/**
 * Génère une liste de points permettant le traçage de la piste et détermine si le personnage est positionné
 * sur la piste et incrémente la vitesse du personnage en conséquence
 */
public class Piste {
	
	/* Valeur en abscisse la plus petite que puisse prendre un point de la piste */
	public static final int LARGEUR_MIN = Affichage.ESPACEMENT_PISTE;
	
	/* Valeur en abscisse la plus grande que puisse prendre un point de la piste */
	public static final int LARGEUR_MAX = (Affichage.LARGEUR_COMPOSANT - Affichage.ESPACEMENT_PISTE);
	
	/* Valeur en ordonnée la plus petite que puisse prendre un point de la piste */
	public static final int HAUTEUR_MIN = Affichage.COORD_Y_HORIZON;
	
	/* Valeur en ordonnée la plus grande que puisse prendre un point de la piste */
	public static final int HAUTEUR_MAX = Affichage.HAUTEUR_COMPOSANT;
	
	/* Valeur à ajouter/soustraire à la valeur en abscisse des nouveaux points pour déterminer l'intervalle de valeurs que puisse prendre ces nouveaux points */
	public static final int ESPACEMENT_X = 150;
	
	/* Valeur à incrémenter à la valeur en ordonnée du dernier point de la piste lors de l'ajout d'un nouveau point */
	public static final int ESPACEMENT_Y = (Affichage.HAUTEUR_COMPOSANT - Affichage.COORD_Y_HORIZON)/5;
	
	/* Valeur permettant de comptabiliser le fait que le personnage est sur la piste lorsqu'il l'est à moitié */
	public static final float MARGE = (EtatNaruto.LARGEUR_IMG);
	
	/* Nombre de déplacements qu'effectue le personnage entre deux points successifs de la piste (avec la valeur initiale de déplacement du personnage) */
	public static final int NOMBRE_DEPLACEMENT = 8;
	
	/* Valeur initiale de déplacement du personnage */
	public static final int DEPLACEMENT = ESPACEMENT_Y/NOMBRE_DEPLACEMENT;
	
	/* Permet de récupérer la position du personnage */
	private EtatNaruto etatNaruto;
	
	/* Permet de modifier la vitesse du personnage */
	private Vitesse vitesse;
	
	/* Liste de points permettant la représentation de la piste */
	private ArrayList<Point> liste;
	
	/* Générateur de valeurs aléatoires */
	private Random rand;
	
	/* Valeur de décalage à ajouter à la piste pour simuler le déplacement en avant du personnage */
	private int positionY;
	
	/* Nombre de pas entre deux points successifs de la piste qui varie en fonction de la position du personnage */
	private float deplacementParLigne;
	
	/**
	 * Constructeur de la classe
	 * @param etatNaruto
	 * @param vitesse
	 */
	public Piste(EtatNaruto etatNaruto, Vitesse vitesse) {
		this.etatNaruto = etatNaruto;
		this.vitesse = vitesse;
		
		this.liste = new ArrayList<Point>();
		this.rand = new Random();
		this.positionY = 0;
		this.deplacementParLigne = 0;
		
		this.fillListe();
	}
	
	/**
	 * Remplit la liste de premiers points formant une ligne droite
	 */
	private void fillListe() {
		int y = HAUTEUR_MAX, x = (Affichage.LARGEUR_COMPOSANT/2);
		
		while(y >= HAUTEUR_MIN) {
			this.liste.add(new Point(x, y));
			y -= ESPACEMENT_Y;
		}
	}
	
	/**
	 * Détermine la valeur en abscisse la plus petite que puisse prendre le point suivant
	 * @param x la valeur en abscisse du dernier point
	 * @return la valeur en abscisse la plus petite
	 */
	private int getXMin(int x) {
		if( (x - ESPACEMENT_X) >= LARGEUR_MIN ) return (x - ESPACEMENT_X);
		else return LARGEUR_MIN;
	}
	
	/**
	 * Détermine la valeur en abscisse la plus grande que puisse prendre le point suivant
	 * @param x la valeur en abscisse du dernier point
	 * @return la valeur en abscisse la plus grande
	 */
	private int getXMax(int x) {
		if( (x + ESPACEMENT_X) <= LARGEUR_MAX ) return (x + ESPACEMENT_X);
		else return LARGEUR_MAX;
	}
	
	/**
	 * Détermine si le personnage est positionné sur la piste
	 * @return vrai si le personnage est positionné sur la piste, faux sinon
	 */
	private boolean isOnTheTrack() {
		// Le personnage est postionné entre le deuxième et troisième point de la piste
		int coordPisteX = this.getPositionXOnTrack(this.liste.get(1), this.liste.get(2));
		
		return (
			(this.etatNaruto.getPositionX() >= (float)(coordPisteX - Affichage.ESPACEMENT_PISTE - MARGE)) &&
			((this.etatNaruto.getPositionX() + EtatNaruto.LARGEUR_IMG) <= (float)(coordPisteX + Affichage.ESPACEMENT_PISTE + MARGE))
		);
	}
	
	/**
	 * Retire le premier point de la liste
	 */
	private void removePoint() {
		// Si l'avant dernier point de la liste est hors de la fenêtre, on retire le premier point
		if( (this.liste.size() > 1) && (this.liste.get(1).y + this.positionY > HAUTEUR_MAX) ) {
			this.liste.remove(0);
		}
	}
	
	/**
	 * Ajoute un nouveau point en fin de liste
	 */
	private void addPoint() {
		Point dernierPoint = this.liste.get(this.liste.size()-1);
		
		// Si le dernier point de la liste est sous la ligne de l'horizon, on ajoute un nouveau point
		if((this.liste.size() > 0) && (dernierPoint.y + this.positionY > HAUTEUR_MIN)) {
			
			this.liste.add(
					new Point(
							(this.getXMin(dernierPoint.x) + this.rand.nextInt(this.getXMax(dernierPoint.x) - this.getXMin(dernierPoint.x))),
							(dernierPoint.y - ESPACEMENT_Y)));
		}
	}
	
	/**
	 * Modifie le dernier point de la liste afin de la positionner sur la ligne de l'horizon 
	 * @param points tableau de points à fixer
	 */
	private void fixPoint(Point[] points) {
		int taille = points.length;
		
		if(points[taille-1].y < Affichage.COORD_Y_HORIZON) {
			points[taille-1] = new Point(this.getPositionXOnTrack(points[taille-2], points[taille-1]), (Affichage.COORD_Y_HORIZON - this.positionY) );
		}
	}
	
	/**
	 * Retourne la valeur de décalage à ajouter à la piste (pour simuler l'avancement du personnage)
	 * @return la valeur de décalage
	 */
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Retourne un tableau de points à partir de la liste de points
	 * @return un tableau de points
	 */
	public Point[] getPiste() {
		Point[] points;
		int i;
		
		this.removePoint();
		this.addPoint();
		
		points = new Point[this.liste.size()];
		
		for(i=0 ; i<this.liste.size() ; i++) {
			points[i] = this.liste.get(i);
		}
		
		this.fixPoint(points);
		
		return points;
	}
	
	/**
	 * Détermine la valeur en abscisse d'un point du segment reliant les deux points en arguments
	 * @param pointDebut premier point du segment
	 * @param pointFin dernier point du segment
	 * @return la valeur en abscisse d'un point du segment reliant les deux points en arguments
	 */
	public int getPositionXOnTrack(Point pointDebut, Point pointFin) {
		float a, b, x;
		
		if(pointDebut.x == pointFin.x) {
			x = (float) pointFin.x;
		}
		else {
			a = (float) (pointFin.y - pointDebut.y) / (pointFin.x - pointDebut.x);
			b = (float) pointDebut.y - (a * pointDebut.x); // On calcule b
			x = (float) ((pointDebut.y - ((float) this.deplacementParLigne * DEPLACEMENT)) - b) / a;
		}
		
		return Math.round(x); // Arrondir le résultat
	}
	
	/**
	 * Augmente la valeur de décalage en fonction de la valeur de la vitesse
	 */
	public void setPositionY() {
		
		if(this.isOnTheTrack()) {
			this.vitesse.accelerate();
		}
		else {
			this.vitesse.decelerate();
		}
			
		this.deplacementParLigne += ((float)this.vitesse.getVitesse()/DEPLACEMENT) - 1;
			
		if(this.deplacementParLigne == NOMBRE_DEPLACEMENT) {
			this.deplacementParLigne = 0;
		}
		else if(this.deplacementParLigne > NOMBRE_DEPLACEMENT) {
			this.deplacementParLigne = ((float)this.deplacementParLigne - NOMBRE_DEPLACEMENT);
		}
		
		this.deplacementParLigne += 1;
		
		this.positionY += this.vitesse.getVitesse();
	}
}