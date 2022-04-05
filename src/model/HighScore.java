package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import view.Affichage;

/* cree un meilleure score et le charge a partir  d'un fichier, le ficchier contiendra au maximum 10 lignes*/

public class HighScore {

	/*permet de recuperer le score courant*/
     private Vector<Comparable[]> highScoreCourant = null;
	 
	 /* Le nom des colonnes du highScore*/
	  private String[] tableauNoms;
	  
	 /*tableau des comparaison effectuant les comparaison à effectuer sur les les 2 colonnes*/
	  private int[][] tableauComparaisons;
	  
	  /*fichier qui contient les meilleures score*/
	  private String fichier;
	  
	  /*delimiteur entre nom et score des joueurs*/
	  private String delimiteur ;
	  
	  /*donnen l'etat du fichier*/
	  private String etatFichier = "Normal";
	  
	  /*Le nombre de lignes maximum dans l'highScore*/
	  public int nbMax;
	  
	     /*
		 * Constructeur de la classe
		 */
	  public HighScore(String[] Noms, int[][] Comparaisons, int nbMax, String Fichier, String Delim) {
		  this.tableauNoms = Noms;
		  this.tableauComparaisons = Comparaisons;
		  this.highScoreCourant = new Vector<Comparable[]>();
	      this.nbMax = nbMax;
	      this.fichier = Fichier;
	      this.delimiteur = Delim;
	    /*charge la liste des 10 meilleures score*/
	    charge();
	  }

	 //
	  public HighScore(String[] Nom, int[][] Comparaisons, int nbMmax){
	    this(Nom,Comparaisons, 10, "meilleureScore.txt", ":--:");
	  }
	  
	  /**
	   * Ajout de la ligne dans le fichier 
	   * Valeur de retour: Le numéro de la ligne dans le fichier. -1 si elle n'a pas été ajouter.
	   */
	  
	  public int addLigne(Comparable[] chaine){
		  /* chaine correspond aux scores a inserer dans le fichier*/
	    int place = -1;
	    int num_comparaison = 0;
	    for(int i = 0; i < highScoreCourant.size(); i++){
	      if(num_comparaison > 0)
	      {
	        for(int j = 0; j<num_comparaison; j++)
	        {
	          if (chaine[tableauComparaisons[j][0]].compareTo(highScoreCourant.get(i)[tableauComparaisons[j][0]]) != 0)
	          {
	            place = i;
	            j = num_comparaison;
	            i = highScoreCourant.size(); 
	          }
	        }
	      }
	      if(place != -1)
	        continue;
	      if (chaine[tableauComparaisons[num_comparaison][0]].compareTo(highScoreCourant.get(i)[tableauComparaisons[num_comparaison][0]]) == 0)
	      {
	        num_comparaison++;
	        if(num_comparaison == tableauComparaisons.length)
	        {
	          place = i;
	          i = highScoreCourant.size(); 
	        }else
	          i--;
	      }
	      else
	      if (tableauComparaisons[num_comparaison][1] == 0) 
	      { // de maniere décroissante
	        if (chaine[tableauComparaisons[num_comparaison][0]].compareTo(highScoreCourant.get(i)[tableauComparaisons[num_comparaison][0]]) > 0) 
	        {
	          place = i;
	          i = highScoreCourant.size(); 
	        }
	      }
	      else 
	      { // ordre croissant
	        if (chaine[tableauComparaisons[num_comparaison][0]].compareTo(highScoreCourant.get(i)[tableauComparaisons[num_comparaison][0]]) < 0)
	        {
	          place = i;
	          i = highScoreCourant.size(); 
	        }
	      }
	    }
	    if(place == -1)
	    {
	        place = highScoreCourant.size();
	    }

	    highScoreCourant.add(place, chaine);
	    while(highScoreCourant.size() > nbMax){
	    	highScoreCourant.remove(highScoreCourant.size()-1); 
	    	}
	    if(place >= highScoreCourant.size())
	      place = -1;

	    if(place != -1 && etatFichier == "Normal")
	      enregistrement();
	    return place;
	  }
	  
	  /*
	   * Permet de récupérer la ligne du fichier
	   * valeur de retour Les éléments de la ligne.
	   */
	  public Comparable[] getLigne(int num_ligne)
	  {
	    if(num_ligne <= highScoreCourant.size())
	      return highScoreCourant.get(num_ligne);
	    return null;
	  }
	  
	  /*
	   * recupere le nombre de ligne du fichier MeilleureScore
	   */
	  public int getNbLines()
	  {
	    return highScoreCourant.size();
	  }
	  
	  /**
	   * Charge un meilleurescore à partir du fichier 
	   */
	 public void charge(){
		 // StringTokenizer: permet de diviser une chaine en plusieurs jeton
	    if(fichier.equals(""))
	    	highScoreCourant.removeAllElements();
	    etatFichier = "charge";
	    BufferedReader buffer = null;
	    try {
	    		buffer = new BufferedReader(new FileReader(fichier));
	    		String lecture = buffer.readLine();
	    		StringTokenizer stringTokenizer = new StringTokenizer(lecture,delimiteur,false);

	      if(stringTokenizer.countTokens() != this.tableauNoms.length)
	        throw new Exception("erreur du fichier !");
	      String[] classes = new String[this.tableauNoms.length];
	      int i = 0;
	      while(stringTokenizer.hasMoreTokens())
	      {
	        classes[i] = stringTokenizer.nextToken();
	        i++;
	      }

	      while (buffer.ready()) {
	        String ligne = buffer.readLine();
	        StringTokenizer stringTokenizerbis = new StringTokenizer(ligne,delimiteur,false);

	        if(stringTokenizerbis.countTokens() != this.tableauNoms.length)
	          throw new Exception("erreur Fichier!");

	        Comparable[] ligneFichier = new Comparable[this.tableauNoms.length];
	        i = 0;
	        while(stringTokenizerbis.hasMoreTokens())
	        {
	        ligneFichier[i] = (Comparable) Class.forName(classes[i]).getConstructor(new Class[]{new String().getClass()}).newInstance(new Object[]{new String(stringTokenizerbis.nextToken())});
	          i++;
	        }
	        addLigne(ligneFichier);
	      }
	      buffer.close();
	    }
	    catch (Exception e1)
	    {
	    	highScoreCourant.removeAllElements();
	      try {
	    	   	buffer.close();
	          } catch (Exception e){
	        	  e.printStackTrace();
	      		}
	    }
	    
	    etatFichier = "Normal";
	  }

	  /**
	   * Enregistre le meilleure score dans le fichier nommé par Fichier
	   */
	  public void enregistrement(){
	    if(fichier.equals(""))
	      return;
	    etatFichier = "Enregistre";
	    BufferedWriter bufferBis = null;
	    try {
	    	bufferBis = new BufferedWriter(new FileWriter(fichier));
	      if(highScoreCourant.size() > 0)
	      {
	    	  Comparable[] Valeurs = highScoreCourant.get(0);
	        for (int i = 0; i < Valeurs.length; i++) {
	        	bufferBis.write(delimiteur+Valeurs[i].getClass().getName());
	        }
	        bufferBis.newLine();
	      }
	      for(int i=0 ;i < highScoreCourant.size(); i++)
	      {
	        Comparable[] nombre = highScoreCourant.get(i);
	        String ligne = "";

	        for(int j=0;j<nombre.length;j++)
	        ligne += delimiteur + nombre[j].toString();
	        bufferBis.write(ligne);
	        bufferBis.newLine();
	      }
	      bufferBis.close();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      highScoreCourant.removeAllElements();
	      try {
	    	  bufferBis.close();
	      }
	      catch (IOException e1) {
	        e1.printStackTrace();
	      }
	    }
	    etatFichier = "Normal";
	  }

	}

