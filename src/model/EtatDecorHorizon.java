package model;

import java.util.ArrayList;

/**
 * Génère une liste de décors positionnés au niveau de l'horizon
 */
public class EtatDecorHorizon {
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;
	
	/* Liste contenant des décors */
	private ArrayList<Decor> decors;
	
	/**
	 * Constructeur de la classe
	 * @param finPartie
	 */
	public EtatDecorHorizon(FinPartie finPartie) {
		this.finPartie = finPartie;
		
		this.decors = new ArrayList<Decor>();
		this.fillDecors();
	}
	
	/**
	 * Remplit la liste de décors
	 */
	private void fillDecors() {
		this.decors.add(new Choji(this.finPartie));
		this.decors.add(new Gai(this.finPartie));
		this.decors.add(new DeidaraTobi(this.finPartie));
	}
	
	/**
	 * Retourne un tableau de décors
	 * @return un tableau de décors
	 */
	public Decor[] getDecorsHorizon() {
		Decor[] decors = new Decor[this.decors.size()];
		
		for(int i=0 ; i<decors.length ; i++) {
			decors[i] = this.decors.get(i);
		}
		
		return decors;
	}
}
