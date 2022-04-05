import control.ControlAccueil;
import model.EtatAccueil;
import view.FenetreAccueil;
import view.VueAccueil;

/**
 * Lancement de l'interface graphique
 */
public class Main {
	public static void main(String[] args) {
		EtatAccueil etatAccueil = new EtatAccueil();
		ControlAccueil controleAccueil = new ControlAccueil(etatAccueil);
		VueAccueil vueAccueil = new VueAccueil(controleAccueil);
		FenetreAccueil fenetreAccueil = new FenetreAccueil(vueAccueil, etatAccueil);
	}
}