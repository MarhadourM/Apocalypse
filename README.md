# Apocalypse 🎲

Jeu de survie mobile pour Android, développé en **Kotlin** dans le cadre du BUT Informatique.

Le monde s'est effondré : votre objectif est de **survivre le plus longtemps possible** en gérant votre vie, votre faim et votre soif, jour après jour.

## Principe du jeu

- Le joueur possède trois jauges : **Vie**, **Satiété** et **Hydratation**. Si la faim ou la soif tombent à 0, il perd de la vie à chaque tour ; si la vie tombe à 0, la partie est perdue.
- Chaque **jour**, il faut accomplir **4 actions**. Pour chacune, on **lance deux dés en secouant le téléphone** (accéléromètre) pour atteindre la valeur cible de l'action (0 à 12).
- Une action réussie donne un **bonus** (vie, nourriture, eau), une action ratée inflige un **malus**.
- Au début de chaque jour, le joueur choisit entre **deux cartes bonus** de rareté *Commune*, *Rare* ou *Légendaire*.
- Un malus de fin de journée rend chaque jour un peu plus difficile.

## Fonctionnalités

- Lancer de dés au **capteur de mouvement** (secouer le téléphone)
- Système de **cartes bonus** aléatoires avec rareté
- Jauges animées pour la vie, la satiété et l'hydratation
- Introduction narrative, tutoriel des règles et écran de crédits
- Écrans de victoire et de défaite
- Sauvegarde du nombre de parties jouées
- Interface plein écran, responsive, avec effet de verre dépoli (BlurView)

## Stack technique

| | |
|---|---|
| Langage | Kotlin |
| Plateforme | Android (minSdk 24, targetSdk 36) |
| Build | Gradle (Kotlin DSL) |
| UI | Vues XML, Material Components, ConstraintLayout, BlurView |
| Capteurs | Accéléromètre (`SensorManager`) |

## Architecture

```
app/src/main/java/com/example/apocalypse/
├── Game/            # Logique métier : Partie (singleton), Joueur, Jour, Action,
│                    # Bonus/Malus, Carte, CartePicker, Dés, État du jeu
├── MainActivity.kt  # Menu principal
├── IntroGameView.kt # Introduction et règles
├── LancerDesSensor.kt # Lancer de dés via l'accéléromètre
├── ActionView.kt, ChoixBonusView.kt
└── *Dialog.kt       # Histoire, fin de partie, crédits
```

La logique du jeu (`Game/`) est séparée de l'affichage : une instance unique de `Partie` pilote l'état du jeu (`EtatJeu`) et les activités se contentent de l'afficher.

## Lancer le projet

1. Cloner le dépôt
   ```bash
   git clone https://github.com/MarhadourM/Apocalypse.git
   ```
2. Ouvrir le dossier dans **Android Studio**
3. Lancer l'application sur un émulateur ou un téléphone Android (un vrai téléphone est conseillé pour secouer les dés 📱)

## Crédits

Jeu créé par **Matthieu Marhadour** — BUT Informatique.
Images de fond : Pinterest (D Maxwell, Gyllene, halfOf333).
