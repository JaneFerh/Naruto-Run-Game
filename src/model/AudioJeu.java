package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*permet de jouer un son par rapport aux evenements du joueur:
 *  s'il cogne un obstcale, s'il depasse un point de controle, ou s'il perd */

public class AudioJeu {
	/* permet de charger l'audio avant la lecture */
	private Clip clip;


    /*
	 * Constructeur de la classe
	 */
	public AudioJeu(File son) {
		try {
			/*format des audios fournit*/
			AudioInputStream audioEvenement = AudioSystem.getAudioInputStream(son);
		    
			this.clip  = AudioSystem.getClip();
		    
		    /*permet d'ouvrir le clip avec le format et les données audios fourni*/
		    this.clip.open(audioEvenement);
		    
		   } catch(Exception e){
			   e.printStackTrace();
				}
	}
	
	/*permet de retourner l'audio */
	public Clip getClip() {
		return clip;
	}
	
	/*methode pour commencer la lecture de l'audio*/
	public void play()
	{
		this.clip.start();
	}
	
	/*methode pour stoper la lecture de l'audio*/
	public void stop()
	{
		this.clip.stop();
	}

	/* lecture du son  */
	public static void playSound(File musique) {
		AudioJeu audioJeu  = new AudioJeu(musique);
		audioJeu.play();
	}
}
