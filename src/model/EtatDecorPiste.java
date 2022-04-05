package model;

import java.util.ArrayList;
import java.util.Random;

/*public class EtatDecorPiste {

	public static final int PAUSE = 500;
	public static final int NB_TOTAL_DECORS_PISTE = 2;
	public static final int NB_MAX_DECORS_PISTE = 1;
	
	private Piste piste;
	private ArrayList<DecorHorizon> decors;
	private Random rand;
	
	public EtatDecorPiste(Piste piste) {
		this.piste = piste;
		this.decors = new ArrayList<DecorHorizon>();
		this.rand = new Random();
		this.fillDecors();
	}
	
	private void fillDecors() {
		int i;
		for(i=0 ; i<NB_MAX_DECORS_PISTE ; i++) {
			this.decors.add(this.generateDecor());
		}
	}
	
	private void removeDecor() {
		if(this.decors.size() > 0 && this.decors.get(0).isRemovable()) {
			this.decors.remove(0);
		}
	}
	
	private void addDecor() {
		System.out.println(this.decors.size());
		if(this.decors.size() < NB_MAX_DECORS_PISTE) {
			this.decors.add(this.generateDecor());
		}
	}
	
	private DecorHorizon generateDecor() {
		int random = this.rand.nextInt(NB_TOTAL_DECORS_PISTE);
		if(random == 0) return new Choji(this.piste);
		else return new Gai(this.piste);
	}
	
	public DecorHorizon[] getDecors() {
		DecorHorizon[] decors;
		int i;
		
		this.addDecor();
		this.removeDecor();
		
		decors = new DecorHorizon[this.decors.size()];
		
		for(i=0 ; i<decors.length ; i++) {
			decors[i] = this.decors.get(i);
		}
		
		return decors;
	}
}*/
