package com.example.tp3_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * Classe qui aura pour fonction de regrouper toutes les méthodes/fonctions permettant d'afficher et de faire fonctionner le programme
 *
 * Nom de la classe : HelloController2
 * Description du programme : Permet d'afficher les éléments du jeu en graphique (JavaFX) permet aussi de jouer en tappant la grille du joueur
 * adverse pour lancer une torpille.
 *
 * Date de remise : 19 avril 2023
 *
 * @author Samael Ross
 * @version 1.0.0
 * @since 8 avril 2023
 *
 */
public class HelloController2
{

    static double startX;
    static double startY;
    static double endX;
    static double endY;
    @FXML
    GridPane gridEnemy;
    Media explosion = new Media(new File("src/main/resources/com/example/tp3_java/explosionaudio.mp3").toURI().toString());
    Media splash = new Media(new File("src/main/resources/com/example/tp3_java/splashaudio.mp3").toURI().toString());
    MediaPlayer mediaPlayerExplosion = new MediaPlayer(explosion);
    MediaPlayer mediaPlayerSplash = new MediaPlayer(splash);

    Media win = new Media(new File("src/main/resources/com/example/tp3_java/win.mp3").toURI().toString());

    Media loss = new Media(new File("src/main/resources/com/example/tp3_java/loss.mp3").toURI().toString());
    MediaPlayer mediaPlayerWin = new MediaPlayer(win);
    MediaPlayer mediaPlayerLoss = new MediaPlayer(loss);

    Image explosionImage = new Image("src/main/resources/com/example/tp3_java/explosion.gif");
    ImageView explosionView = new ImageView(explosionImage);

    static Rotate rotationVertical = new Rotate(90, 0, 0);
    static Rotate rotationHorizontal = new Rotate(270, 0, 0);

    /**
     * Constructeur du tableau pour conserver les directions des bateaux
     *
     */
     public static int []tabDirectionBateau = new int[5];
    /**
     *  Constructeur de la grille du joueur
     */
    public static int [][]grilleJeu = new int [10][10];

    /**
     *  Constructeur de la grille de l'ordinateur
     */
    public static int [][]grilleOrdi = new int [10][10];

    /**
     *  Constructeur des coordonnés X des bateaux du joueur pour permettre de les mettre sur l'autre scene
     */
    public static Integer []coordXbateauJoueur = new Integer[5];

    /**
     *  Constructeur des coordonnés Y des bateaux du joueur pour permettre de les mettre sur l'autre scene
     */
    public static Integer []coordYbateauJoueur = new Integer[5];

    /**
     *
     * Permet de quitter l'application lorsque cette fonction est appelée
     *
     */
    @FXML
    protected void onQuitter()
    {
        System.exit(0);
    }

    /**
     *
     * Permet de recommencer le placement des bateaux
     * @throws IOException
     */
    @FXML
    protected void onRecommencer() throws IOException
    {
        // Remet la scene du début pour recommencer de zero
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("placebateau.fxml")));
        Scene scene = new Scene(root, 800, 600);

        Stage window = (Stage) bateau2.getScene().getWindow();
        window.setScene(scene);

        // Remet l'index de typeBateau à 1
        typeBateau = 1;

        // Remet la grille du joueur a 0
        for (int i = 0; i < grilleJeu.length; i++)
        {
            for (int j = 0; j < grilleJeu[i].length; j++)
            {
                grilleJeu[i][j] = 0;
            }
        }

        // Clear les rotations des bateaux pour éviter tout bug
        bateau2.getTransforms().clear();
        bateau3.getTransforms().clear();
        bateau31.getTransforms().clear();
        bateau4.getTransforms().clear();
        bateau5.getTransforms().clear();
    }

    @FXML
    Sphere ballPane;
    @FXML
    GridPane gridEnemy1;

    /**
     *
     * But de la fonction est de permettre de changer de scene pour passer au jeu.
     * @throws Exception
     *
     */
    @FXML
    protected void onJouer() throws Exception
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("jeu.fxml")));
        Scene scene = new Scene(root, 800, 600);

        Stage window = (Stage) bateau2.getScene().getWindow();
        window.setScene(scene);
        GridPane rootGridEnemy = (GridPane) root.lookup("#gridEnemy1");
        GridPane rootGridJoueur = (GridPane) root.lookup("#gridEnemy");
        gridEnemy1 = rootGridEnemy;
        gridEnemy = rootGridJoueur;
        placementBateauJoueur();
    }

    protected void timeline()
    {
        // Création de la timeline pour animer le déplacement de la balle
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    // À l'instant initial, la balle est en position de départ
                    ballPane.setTranslateX(startX);
                    ballPane.setTranslateY(startY);
                }),
                new KeyFrame(Duration.seconds(2), e -> {
                    // Après 2 secondes, la balle est en position d'arrivée
                    ballPane.setTranslateX(endX);
                    ballPane.setTranslateY(endY);
                })
        );

        timeline.play();
    }

    /**
     *
     * Fonction qui permet de transferer les bateaux de la scene 2 a la scene 3
     *
     */
    protected void placementBateauJoueur()
    {
        for(int i = 0; i < 5; i++)
        {
            System.out.println("TABLEAU x : " + coordXbateauJoueur[i] + "  y : " + coordYbateauJoueur[i]);
        }

        typeBateau = 1;
        Backend.initGrilleOrdi(grilleOrdi);
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("Grille ordi");
        Backend.afficherGrille(grilleOrdi);


        while(typeBateau != 6)
        {
            // Afficher les bateaux de l'ordinateur à l'aide d'image
            switch(typeBateau)
            {
                case 1:
                    if(tabDirectionBateau[0] == 1)
                    {
                        //bateau2.getTransforms().add(rotationHorizontal);
                        gridEnemy1.add(bateau2, (coordXbateauJoueur[0] - 1), coordYbateauJoueur[0]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[0], coordXbateauJoueur[0], typeBateau, tabDirectionBateau[0]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    if(tabDirectionBateau[0] == 2)
                    {
                        //bateau2.getTransforms().add(rotationVertical);
                        gridEnemy1.add(bateau2, (coordXbateauJoueur[0] + 1), coordYbateauJoueur[0]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[0], coordXbateauJoueur[0], typeBateau, tabDirectionBateau[0]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    break;
                case 2:
                    if(tabDirectionBateau[1] == 1)
                    {
                       // bateau3.getTransforms().add(rotationHorizontal);
                        gridEnemy1.add(bateau3, (coordXbateauJoueur[1] - 2), coordYbateauJoueur[1]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[1], coordXbateauJoueur[1], typeBateau, tabDirectionBateau[1]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    if(tabDirectionBateau[1] == 2)
                    {
                        //bateau3.getTransforms().add(rotationVertical);
                        gridEnemy1.add(bateau3, (coordXbateauJoueur[1] + 1), coordYbateauJoueur[0]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[1], coordXbateauJoueur[1], typeBateau, tabDirectionBateau[1]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    break;
                case 3:
                    if(tabDirectionBateau[2] == 1)
                    {
                        //bateau31.getTransforms().add(rotationHorizontal);
                        gridEnemy1.add(bateau31, (coordXbateauJoueur[2] - 2), coordYbateauJoueur[2]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[2], coordXbateauJoueur[2], typeBateau, tabDirectionBateau[2]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    if(tabDirectionBateau[2] == 2)
                    {
                       // bateau31.getTransforms().add(rotationVertical);
                        gridEnemy1.add(bateau31, (coordXbateauJoueur[2] + 1), coordYbateauJoueur[2]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[2], coordXbateauJoueur[2], typeBateau, tabDirectionBateau[2]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    break;
                case 4:
                    if(tabDirectionBateau[3] == 1)
                    {
                       // bateau4.getTransforms().add(rotationHorizontal);
                        gridEnemy1.add(bateau4, (coordXbateauJoueur[3] - 3), coordYbateauJoueur[3]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[3], coordXbateauJoueur[3], typeBateau, tabDirectionBateau[3]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    if(tabDirectionBateau[3] == 2)
                    {
                       // bateau4.getTransforms().add(rotationVertical);
                        gridEnemy1.add(bateau4, (coordXbateauJoueur[3] + 1), coordYbateauJoueur[3]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[3], coordXbateauJoueur[3], typeBateau, tabDirectionBateau[3]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    break;
                case 5:
                    if(tabDirectionBateau[4] == 1)
                    {
                        //bateau5.getTransforms().add(rotationHorizontal);
                        gridEnemy1.add(bateau5, (coordXbateauJoueur[4] - 4), coordYbateauJoueur[4]);
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[4], coordXbateauJoueur[4], typeBateau, tabDirectionBateau[4]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    if(tabDirectionBateau[4] == 2)
                    {
                       // bateau5.getTransforms().add(rotationVertical);
                        gridEnemy1.add(bateau5, (coordXbateauJoueur[4] + 1), (coordYbateauJoueur[4]));
                        Backend.initGrilleJeu(grilleJeu, coordYbateauJoueur[4], coordXbateauJoueur[4], typeBateau, tabDirectionBateau[4]);
                        Backend.afficherGrille(grilleJeu);
                        typeBateau = typeBateau + 1;
                    }
                    break;
            }
        }
    }

    /**
     *
     * Fonction qui permet de tiré une "torpille" sur les bateaux adverse et déterminer si manqué ou touché
     * @param e Recoit les informations vis-à-vis le click sur la grille
     * @throws Exception
     */
    @FXML
    protected void clickGridJeu(MouseEvent e) throws Exception
    {
        // Arrête le son pour permettre qu'au prochain touché/manqué le son recommence
        mediaPlayerExplosion.stop();
        mediaPlayerSplash.stop();

        Integer x = null;
        Integer y = null;

        x = grid.getColumnIndex((Node) e.getTarget());
        y = grid.getRowIndex((Node) e.getTarget());

        if(grid.getColumnIndex((Node) e.getTarget()) == null)
        {
            x = 0;
            y = grid.getRowIndex((Node) e.getTarget());
        }

        if(grid.getRowIndex((Node) e.getTarget()) == null)
        {
            x = grid.getColumnIndex((Node) e.getTarget());
            y = 0;
        }

        if(grid.getColumnIndex((Node) e.getTarget()) == null && grid.getRowIndex((Node) e.getTarget()) == null)
        {
            x = 0;
            y = 0;
        }

        System.out.println("x : " + x + "  y : " + y);

        if(tourJoueur == true)
        {
            // Si mouvement retourne vrai (il a touché un bateau alors)
            if(Backend.mouvement(grilleOrdi, y, x))
            {
                Pane pane = (Pane) gridEnemy.getChildren().get((y * 10) + x);
                pane.setStyle("-fx-background-color: red;");
                mediaPlayerExplosion.play();
                tourJoueur = false;
                double startX = gridEnemy1.getTranslateX();
                double startY = gridEnemy1.getTranslateY();
                double endX = gridEnemy.getLayoutX() + (100 * x); // colonne 2
                double endY = gridEnemy.getLayoutY() + (100 * y); // ligne 3
                timeline();
                jeu();
            }
            else
            {
                mediaPlayerSplash.play();
                Pane pane = (Pane) gridEnemy.getChildren().get((y * 10) + x);
                pane.setStyle("-fx-background-color: blue;");
                double startX = gridEnemy1.getTranslateX();
                double startY = gridEnemy1.getTranslateY();
                double endX = gridEnemy.getLayoutX() + (100 * x); // colonne 2
                double endY = gridEnemy.getLayoutY() + (100 * y); // ligne 3
                timeline();
                tourJoueur = false;
                jeu();
            }
        }
        else
        {
            System.out.println("Ce n'est pas ton tour");
        }
    }

    @FXML
    Text textWin;
    @FXML
    GridPane grid;
    @FXML
    ImageView bateau2;
    @FXML
    ImageView bateau3;
    @FXML
    ImageView bateau31;
    @FXML
    ImageView bateau4;
    @FXML
    ImageView bateau5;
    /**
     *
     * But de la fonction est de placer les bateaux en fonction du clique
     * @param e Recoit tous les informations vis-à-vis le clique de souris tel que sa position
     * @throws Exception
     */
    @FXML
    protected void clickGrid(MouseEvent e) throws Exception
    {
        Integer x = null;
        Integer y = null;

        x = grid.getColumnIndex((Node) e.getTarget());
        y = grid.getRowIndex((Node) e.getTarget());

        if(grid.getColumnIndex((Node) e.getTarget()) == null)
        {
            x = 0;
            y = grid.getRowIndex((Node) e.getTarget());
        }

         if(grid.getRowIndex((Node) e.getTarget()) == null)
         {
             x = grid.getColumnIndex((Node) e.getTarget());
             y = 0;
         }

         if(grid.getColumnIndex((Node) e.getTarget()) == null && grid.getRowIndex((Node) e.getTarget()) == null)
         {
             x = 0;
             y = 0;
         }
         System.out.println("x : " + x + "  y : " + y);

         // Placement des images de bateaux
         if(direction == 1)
         {
             if(Backend.posOk(grilleJeu, y,x, 1, typeBateau))
             {
                 switch(typeBateau)
                 {
                     case 1:
                         grid.add(bateau2, (x-1), y);
                         Backend.initGrilleJeu(grilleJeu,y,x,typeBateau,1);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[0] = x;
                         coordYbateauJoueur[0] = y;
                         tabDirectionBateau[0] = direction;
                         break;
                     case 2:
                         grid.add(bateau3, (x-2), y);
                         Backend.initGrilleJeu(grilleJeu,y,x,typeBateau,1);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[1] = x;
                         coordYbateauJoueur[1] = y;
                         tabDirectionBateau[1] = direction;
                         break;
                     case 3:
                         grid.add(bateau31, (x-2), y);
                         Backend.initGrilleJeu(grilleJeu,y,x,typeBateau,1);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[2] = x;
                         coordYbateauJoueur[2] = y;
                         tabDirectionBateau[2] = direction;
                         break;
                     case 4:
                         grid.add(bateau4, (x-3), y);
                         Backend.initGrilleJeu(grilleJeu,y,x,typeBateau,1);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[3] = x;
                         coordYbateauJoueur[3] = y;
                         tabDirectionBateau[3] = direction;
                         break;
                     case 5:
                         grid.add(bateau5, (x-4), y);
                         Backend.initGrilleJeu(grilleJeu,y,x,typeBateau,1);
                         Backend.afficherGrille(grilleJeu);
                         coordXbateauJoueur[4] = x;
                         coordYbateauJoueur[4] = y;
                         tabDirectionBateau[4] = direction;
                         onJouer();
                         break;
                 }
             }
         }
         if(direction == 2)
         {
             if (Backend.posOk(grilleJeu, y, x, 2, typeBateau))
             {
                 switch (typeBateau)
                 {
                     case 1:
                         grid.add(bateau2, (x+1), (y));
                         Backend.initGrilleJeu(grilleJeu, y, x, typeBateau, 2);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[0] = x;
                         coordYbateauJoueur[0] = y;
                         tabDirectionBateau[0] = direction;
                         break;
                     case 2:
                         grid.add(bateau3, (x+1), (y));
                         Backend.initGrilleJeu(grilleJeu, y, x, typeBateau, 2);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[1] = x;
                         coordYbateauJoueur[1] = y;
                         tabDirectionBateau[1] = direction;
                         break;
                     case 3:
                         grid.add(bateau31, (x+1), (y));
                         Backend.initGrilleJeu(grilleJeu, y, x, typeBateau, 2);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[2] = x;
                         coordYbateauJoueur[2] = y;
                         tabDirectionBateau[2] = direction;
                         break;
                     case 4:
                         grid.add(bateau4, (x+1), (y));
                         Backend.initGrilleJeu(grilleJeu, y, x, typeBateau, 2);
                         Backend.afficherGrille(grilleJeu);
                         typeBateau = typeBateau + 1;
                         coordXbateauJoueur[3] = x;
                         coordYbateauJoueur[3] = y;
                         tabDirectionBateau[3] = direction;
                         break;
                     case 5:
                         grid.add(bateau5, (x+1), (y));
                         Backend.initGrilleJeu(grilleJeu, y, x, typeBateau, 2);
                         Backend.afficherGrille(grilleJeu);
                         coordXbateauJoueur[4] = x;
                         coordYbateauJoueur[4] = y;
                         tabDirectionBateau[4] = direction;
                         onJouer();
                         break;
                 }
             }
         }
             else
             {
                 System.out.println("Pas de place");
             }
    }

    @FXML
    ImageView btnDirection;
    @FXML
    Text textDirection;

    /**
     *
     * Fonction qui permet de changer la direction des bateaux horizontalement  ou verticalementn
     */
    @FXML
    protected void changeDirection()
    {
        switch(direction)
        {
            case 1:
                direction = 2;
                textDirection.setText("Vertical");
                //Rotate rotationVertical = new Rotate(90, bateau2.getBoundsInLocal().getWidth() / 2, bateau2.getBoundsInLocal().getHeight() / 2);
                Rotate rotationVertical = new Rotate(90, 0, 0);
                switch (typeBateau)
                {
                    case 1:
                        bateau2.getTransforms().add(rotationVertical);
                        bateau3.getTransforms().add(rotationVertical);
                        bateau31.getTransforms().add(rotationVertical);
                        bateau4.getTransforms().add(rotationVertical);
                        bateau5.getTransforms().add(rotationVertical);
                        break;
                    case 2:
                        bateau3.getTransforms().add(rotationVertical);
                        bateau31.getTransforms().add(rotationVertical);
                        bateau4.getTransforms().add(rotationVertical);
                        bateau5.getTransforms().add(rotationVertical);
                        break;
                    case 3:
                        bateau31.getTransforms().add(rotationVertical);
                        bateau4.getTransforms().add(rotationVertical);
                        bateau5.getTransforms().add(rotationVertical);
                        break;
                    case 4:
                        bateau4.getTransforms().add(rotationVertical);
                        bateau5.getTransforms().add(rotationVertical);
                        break;
                    case 5:
                        bateau5.getTransforms().add(rotationVertical);
                        break;
                }
            break;
            case 2:
                direction = 1;
                textDirection.setText("Horizontal");
                //Rotate rotationHorizontal = new Rotate(270, bateau2.getBoundsInLocal().getWidth() / 2, bateau2.getBoundsInLocal().getHeight() / 2);
                switch (typeBateau)
                {
                    case 1:
                        bateau2.getTransforms().add(rotationHorizontal);
                        bateau3.getTransforms().add(rotationHorizontal);
                        bateau31.getTransforms().add(rotationHorizontal);
                        bateau4.getTransforms().add(rotationHorizontal);
                        bateau5.getTransforms().add(rotationHorizontal);
                        break;
                    case 2:
                        bateau3.getTransforms().add(rotationHorizontal);
                        bateau31.getTransforms().add(rotationHorizontal);
                        bateau4.getTransforms().add(rotationHorizontal);
                        bateau5.getTransforms().add(rotationHorizontal);
                        break;
                    case 3:
                        bateau31.getTransforms().add(rotationHorizontal);
                        bateau4.getTransforms().add(rotationHorizontal);
                        bateau5.getTransforms().add(rotationHorizontal);
                        break;
                    case 4:
                        bateau4.getTransforms().add(rotationHorizontal);
                        bateau5.getTransforms().add(rotationHorizontal);
                        break;
                    case 5:
                        bateau5.getTransforms().add(rotationHorizontal);
                        break;
                }
            break;
        }
    }


    /**
     *
     * Fonction qui s'occupe de generer les tirs de torpilles de l'ordinateur
     */
    protected void tourOrdi()
    {
        int[] numGenererOrdi = new int[2];
        int colonne;
        int ligne;

        Backend.tirOrdinateur(numGenererOrdi);
        ligne = numGenererOrdi[0];
        colonne = numGenererOrdi[1];

        // Si mouvement retourne vrai (il a touché un bateau alors)
        if(Backend.mouvement(grilleJeu, ligne, colonne))
        {
            Pane pane = (Pane) gridEnemy1.getChildren().get((ligne * 10) + colonne);
            pane.setStyle("-fx-background-color: red;");
            mediaPlayerExplosion.play();
            gridEnemy.add(explosionView, ligne, colonne);
            tourJoueur = true;
        }
        else
        {
            mediaPlayerSplash.play();
            Pane pane = (Pane) gridEnemy1.getChildren().get((ligne * 10) + colonne);
            pane.setStyle("-fx-background-color: blue;");
            tourJoueur = true;
        }

    }

    /**
     *
     * Fonction qui permet de faire fonctionner le jeu en alternant les tours des joueurs
     */
    protected void jeu()
    {
        if (!Backend.vainqueur(grilleJeu) && !Backend.vainqueur(grilleOrdi)) {
            if (tourJoueur == false) {
                tourOrdi();
                //System.out.println("C'est a ton tour");
            }

            if (tourJoueur == true) {
                //System.out.println("C'est ton tour");
            }
        }
        if (Backend.vainqueur(grilleOrdi))
        {
            mediaPlayerWin.play();
            textWin.setText("Partie terminer!     Vainqueur : Joueur");
        }

        if (Backend.vainqueur(grilleJeu))
        {
            mediaPlayerLoss.play();
            textWin.setText("Partie terminer!     Vainqueur : Ordinateur");
        }
    }

    /**
     *
     * Permet d'aller chercher la variable de triche pour savoir si elle est active ou pas
     * @param etat variable déterminant true or false de la triche
     */
    public static void getTriche(boolean etat)
    {
        tricheActive = etat;
    }
    static boolean tourJoueur = true;
    static int typeBateau = 1;
    static int direction = 1;

    static boolean tricheActive = false;


}
