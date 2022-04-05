package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.AudioJeu;
import model.EtatNaruto;
import model.FinPartie;
import view.Affichage;

/**
 * Provoque la modification de la valeur en abscisse du personnage lorsque
 * la touche directionnelle gauche ou droite est appuyée/enfoncée
 */
public class ControlPersonnage extends Thread implements KeyListener {
	
	/* Temps de repos */
	public static final int PAUSE = 50;
	
	/* Permet de modifier la valeur en abcisse du personnage */
	private EtatNaruto etatNaruto;
	
	/* Permet de déterminer la fin de partie */
	private FinPartie finPartie;

	/**
	 * Constructeur de la classe
	 * @param affichage
	 * @param etatNaruto
	 * @param finPartie
	 */
	public ControlPersonnage(Affichage affichage, EtatNaruto etatNaruto, FinPartie finPartie) {
		this.etatNaruto = etatNaruto;
		this.finPartie = finPartie;
		
		affichage.addKeyListener(this);
		
		this.start();
	}
	
	/**
	 * Lorsque la touche directionnelle de gauche ou de droite est
	 * enfoncée, le personnage se déplace vers la direction indiquée
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		// Flèche directionnelle gauche enfoncée
		if(keyCode == KeyEvent.VK_LEFT) {
			this.etatNaruto.left = true;
		}
		// Flèche directionnelle droite enfoncée
		else if(keyCode == KeyEvent.VK_RIGHT) {
			this.etatNaruto.right = true;
		}
	}
	
	/**
	 * Lorsque la touche directionnelle de gauche ou de droite est
	 * appuyée, le personnage se déplace vers la direction indiquée
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		// Flèche directionnelle gauche appuyée
		if(keyCode == KeyEvent.VK_LEFT) {
			this.etatNaruto.left = true;
		}
		// Flèche directionnelle droite appuyée
		else if(keyCode == KeyEvent.VK_RIGHT) {
			this.etatNaruto.right = true;
		}
	}
	
	/**
	 * Lorsque la touche directionnelle de gauche ou de droite est
	 * relâchée, le personnage cesse de se déplacer
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		// Flèche directionnelle gauche relâchée
		if(keyCode == KeyEvent.VK_LEFT) {
			this.etatNaruto.left = false;
		}
		// Flèche directionnelle droite relâchée
		else if(keyCode == KeyEvent.VK_RIGHT) {
			this.etatNaruto.right = false;
		}
	}
	
	/**
	 * Déplace le personnage vers la direction de la touche directionnelle
	 * appuyée/enfoncée ou cesse son déplacement
	 */
	@Override
	public void run() {
		while(!this.finPartie.gameOver()) {
			
			// Déplacement vers la gauche
			if(this.etatNaruto.left) {
				this.etatNaruto.moveToLeft();
			}
			
			// Déplacement vers la droite
			else if(this.etatNaruto.right) {
				this.etatNaruto.moveToRight();
			}
			
			try {
				Thread.sleep(PAUSE);
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}