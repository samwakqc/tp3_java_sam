package com.example.tp3_java;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 *
 * Classe qui a pour but de controler les actions de l'introduction du programme
 *
 * Nom de la classe : HelloController
 * Description du programme : Le but de ce "programme" est de permettre a l'utilisateur de commencer une partie ou bien de quitter le programme.
 * De plus, le programme permet aussi a l'utilisateur de cocher la case tricher ou de la décocher.
 *
 * Date de remise : 19 avril 2023
 *
 * @author Samael Ross
 * @version 1.0.0
 * @since 8 avril 2023
 *
 */
public class HelloController
{
    public boolean tricheActive = false;

     /**
     * Constructeur de checkBox pour pouvoir travailler avec le checkBox et ses fonctions
     */
    @FXML
    private CheckBox checkBoxTriche;

     /**
     * Constructeur de Button pour pouvoir travailler avec les fonctions de button
     */
    @FXML
    Button btnSwitch;

     /**
     * Permet a l'utilisateur de quitter le jeu
     */
    @FXML
    protected void onQuitter() {
        System.exit(0);
    }

    @FXML
     /**
     *
     * Permet a l'utilisateur de passer de la page d'acceuil à celle pour placer les bateaux
     * @see https://www.youtube.com/watch?v=qnwBZveyUtA
     *
     */
    protected void onStart() throws Exception
    {
        System.out.println("La partie va commencer");

        // Permet de switch de scene
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("placebateau.fxml")));
        Scene scene = new Scene(root, 800, 600);

        HelloController2.getTriche(tricheActive);
        Stage window = (Stage) btnSwitch.getScene().getWindow();
        window.setScene(scene);
    }

    /**
     *
     * Permet de verifier si le mode triche est activé via le checkBox
     *
     */
    @FXML
    protected void checkBoxOnTriche()
    {
        if (checkBoxTriche.isSelected() && tricheActive == false)
        {
            System.out.println("Triche on");
            tricheActive = true;
        }
        else
        {
            System.out.println("Triche off");
            tricheActive = false;
        }
    }


    /**
     *
     * Permet de vérifier si le mode triche est activé via le menu
     *
     */
    @FXML
    protected void onTriche()
    {
        if (tricheActive == false) {
            System.out.println("Triche on");
            tricheActive = true;
            checkBoxTriche.setSelected(true);
        } else {
            System.out.println("Triche off");
            tricheActive = false;
            checkBoxTriche.setSelected(false);
        }
    }
}