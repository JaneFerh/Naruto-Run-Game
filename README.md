# Naruto-Run-Game

*Programmation concurrente et interfaces interactives - L3 Informatique parcours MIAGE*

Jeu vidéo de type *course de voiture* (inspiré des années 80) dans l'univers de *Naruto Shippuden*.

#### Description:

Afin d'entamer un nouvel entraînement pour Naruto, Kakashi a décidé de tout d'abord tester ses aptitudes. Pour cela, il a mis en place un parcours rempli d'obstacles sur lequel Naruto devra tenir le plus longtemps possible.
Aidez Naruto à esquiver le plus d'obstacles possibles et à rattraper Kakashi dans le temps imparti.

#### Prérequis:

Java avec un IDE

#### Mode d'emploi:

Importez le projet dans votre IDE, sélectionnez la classe Main à la racine du projet puis *Run as Java Application*.

En jeu:
- Déplacez votre personnage à l'aide des touches directionnelles gauche et droite.
- La vitesse de votre personnage est gérée automatiquement à partir de sa proximité par rapport à la piste. Ainsi, plus votre personnage est éloigné de la piste, plus celui-ci perdra progressivement de la vitesse. Si sa vitesse atteint zéro, la partie est terminée.
- Vous disposez d'un temps imparti pour atteindre des points de contrôle (représentés par le personnage de *Kakashi*) qui vous permettront de vous ajouter du temps. Si le temps imparti atteint zéro, la partie est terminée.
- Des obstacles se présenteront sur votre route (représentés par des shurikens, des kunais, ...). À chaque obstacles heurtés, la vitesse de votre personnage diminuera.
- But du jeu: avoir le score le plus élevé en fin de partie.
