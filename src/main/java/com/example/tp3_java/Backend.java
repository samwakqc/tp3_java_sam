package com.example.tp3_java;

import java.util.Random;

/**
 *
 * Classe qui aura pour fonction de regrouper toutes les méthodes/fonctions permettant au jeu de fonctionner.
 *
 * Nom de la classe : Backend
 * Description du programme : Le but de chaque joueur est de couler tous les bateaux de l’autre joueur. Chaque
 * joueur joue tour à tour en proposant une position où lancer une torpille pour toucher un bateau adverse en indiquant une position sur la grille
 * (par exemple « B3 ») et l’adversaire répond « Touché » si la torpille touche un bateau, « Coulé » si l’adversaire touche un bateau et le coule
 *
 * Date de remise : 19 avril 2023
 *
 * @author Samael Ross
 * @version 1.0.0
 * @since 8 avril 2023
 *
 */
public class Backend
{
    /**
     * Constructeur d'un objet random permettant d'utiliser les méthodes dans la bibliothèque Random
     */
    public static Random rand = new Random();

    /**
     * Fonction permettant de randomiser un numéro entre a (inclus) et b (exclus)
     */
    public static int randRange(int a, int b)
    {
        return rand.nextInt(b-a)+a;
    }
        /**
         * Fonction permettant de déterminer s'il est possible ou non, de placer un bateau d'un certaine taille, à une coordonnés x et y ainsi que de sa direction soit horizontal ou vertical
         *
         * @param grille grille de l'ordinateur ou de l'utilisateur
         * @param l le numero de ligne (compris entre 0 et 9) - horizontal
         * @param c le numero de colonne (compris entre 0 et 9) - vertical
         * @param d un entier codant une direction (1 pour horizontal et 2 pour vertical)
         * @param t un entier décrivant le type du bateau
         * @return vrai si on peut mettre le bateau sur les cases correspondantes, faux s'il est impossible de placer le bateau
         */
        public static boolean posOk(int [][]grille, int l, int c, int d, int t)
        {
            // Si le bateau est un torpilleur alors
            if(t == 1)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(d == 1)
                {
                    // Si le maximum ne dépasse pas le tableau alors
                    if(c-1 < 10 && c-1 >= 0)
                    {
                        // Vérifie si l'emplacement actuelle ainsi que l'emplacement à gauche sont libre alors
                        if(grille[l][c] == 0 && grille[l][c-1] == 0)
                        {
                            return true;
                        }
                        else return false;
                    }
                }
                else if(d == 2)
                {
                    // Verifie si le maximum ne dépasse pas le tableau
                    if(l+1 < 10 && l+1 >= 0)
                    {
                        // Vérifie si l'emplacement actuelle ainsi que les emplacements nécessaires en bas sont libre alors.
                        if(grille[l][c] == 0 && grille[l+1][c] == 0)
                        {
                            return true;
                        }
                        else return false;
                    }
                }
            }

            // Si le bateau est un sous-marin ou un contre-torpilleurs
            if(t == 2 || t == 3)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(d == 1)
                {
                    // Verifie si le maximum ne dépasse pas le tableau
                    if (c - 2 < 10 && c - 2 >= 0) {
                        // Vérifie si l'emplacement actuelle ainsi que l'emplacement à gauche sont libre alors
                        if (grille[l][c] == 0 && grille[l][c - 1] == 0 && grille[l][c - 2] == 0) {
                            return true;
                        } else return false;
                    }
                }

                // Si le bateau doit être positionné verticalement alors
                else if(d == 2)
                {
                    // Verifie si le maximum ne dépasse pas le tableau
                    if(l+2 < 10 && l+2 >= 0)
                    {
                        // Vérifie si l'emplacement actuelle ainsi que les emplacements nécessaires en bas sont libres alors.
                        if(grille[l][c] == 0 && grille[l+1][c] == 0 && grille[l+2][c] == 0)
                        {
                            return true;
                        }
                        else return false;
                    }
                }
            }


            // Si le bateau est un croiseur alors
            if (t == 4)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(d == 1)
                {
                    // Si l'emplacement maximal ne dépasse pas 10 alors
                    if(c-3 < 10 && c-3 >= 0)
                    {
                        // Vérifie si l'emplacement actuelle ainsi que les emplacements nécessaires à gauche sont libres alors
                        if(grille[l][c] == 0 && grille[l][c-1] == 0 && grille[l][c-2] == 0 && grille[l][c-3] == 0)
                        {
                            return true;
                        }
                        else return false;
                    }
                }

                // Si le bateau doit être positionné verticalement alors
                else if(d == 2)
                {
                    // Si l'emplacement maximal ne dépasse pas 10 alors
                    if(l+3 < 10 && l+3 >= 0)
                    {
                        // Vérifie si l'emplacement actuelle ainsi que les emplacements nécessaires en bas sont libre alors.
                        if(grille[l][c] == 0 && grille[l + 1][c] == 0 && grille[l + 2][c] == 0 && grille[l + 3][c] == 0)
                        {
                            return true;
                        }
                        else return false;
                    }
                }
            }

            // Si le bateau est un porte-avions
            if(t==5)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(d == 1)
                {
                    // Si l'emplacement maximal ne dépasse pas 10 alors
                    if(c-4 < 10 && c-4 >= 0)
                    {
                        // Vérifie si l'emplacement actuelle ainsi que les emplacements nécessaires à gauche sont libres alors
                        if(grille[l][c] == 0 && grille[l][c-1] == 0 && grille[l][c-2] == 0 && grille[l][c-3] == 0 && grille[l][c-4] == 0)
                        {
                            return true;
                        }
                        else return false;
                    }
                }

                // Si le bateau doit être positionné verticalement alors
                else if(d == 2)
                {
                    // Si l'emplacement maximal ne dépasse pas 10 alors
                    if(l+4 < 10 && l+4 >= 0)
                    {
                        // Vérifie si l'emplacement actuelle ainsi que les emplacements nécessaires en bas sont libre alors.
                        if (grille[l][c] == 0 && grille[l + 1][c] == 0 && grille[l + 2][c] == 0 && grille[l + 3][c] == 0 && grille[l + 4][c] == 0)
                        {
                            return true;
                        }
                        else return false;
                    }
                }
            }
            return false;
        }

    /**
     *
     * Fonction qui permet d'initialiser aléatoirement des bateaux dans la grille de l'ordinateur
      * @param grilleOrdi recoit la grille de l'ordinateur
     */
    public static void initGrilleOrdi(int [][]grilleOrdi)
    {
        int numeroLigne;
        int numeroColonne;
        int numeroDirection;
        int typeBateau = 1;

        int bateauPlace = 0;


        // Tant qu'il n'a pas placé 5 bateaux on boucle
        while(bateauPlace != 5)
        {
            numeroLigne = randRange(0,10);
            numeroColonne = randRange(0,10);
            //numeroDirection = randRange(1,3);
            numeroDirection = 1;

            // Permet d'incrémenter le type de bateau pour que tous les bateaux soient placé une fois
            if(bateauPlace == 1)
            {
                typeBateau = typeBateau + 1;
            }
            if(bateauPlace == 2)
            {
                typeBateau = typeBateau + 1;
            }
            if(bateauPlace == 3)
            {
                typeBateau = typeBateau + 1;
            }
            if(bateauPlace == 4)
            {
                typeBateau = typeBateau + 1;
            }

            // Tant que les positions ne sont pas disponibles
            while(!posOk(grilleOrdi, numeroLigne, numeroColonne, numeroDirection, typeBateau))
            {
                numeroLigne = randRange(0,10);
                numeroColonne = randRange(0,10);
                numeroDirection = randRange(1,3);
            }

            // Si le bateau est un torpilleur alors
            if(typeBateau == 1)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(numeroDirection == 1)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 5;
                    grilleOrdi[numeroLigne][numeroColonne-1] = 5;
                    bateauPlace = bateauPlace + 1;
                }

                // Si le bateau doit être positionné verticalement alors
                if(numeroDirection == 2)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 5;
                    grilleOrdi[numeroLigne+1][numeroColonne] = 5;
                    bateauPlace = bateauPlace + 1;
                }
            }

            // Si le bateau est un sous-marin alors
            if(typeBateau == 2)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(numeroDirection == 1)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 4;
                    grilleOrdi[numeroLigne][numeroColonne-1] = 4;
                    grilleOrdi[numeroLigne][numeroColonne-2] = 4;
                    bateauPlace = bateauPlace + 1;
                }

                // Si le bateau doit être positionné verticalement alors
                if(numeroDirection == 2)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 4;
                    grilleOrdi[numeroLigne+1][numeroColonne] = 4;
                    grilleOrdi[numeroLigne+2][numeroColonne] = 4;
                    bateauPlace = bateauPlace + 1;
                }
            }

            // Si le bateau est un contre-torpilleurs alors
            if(typeBateau == 3)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(numeroDirection == 1)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 3;
                    grilleOrdi[numeroLigne][numeroColonne-1] = 3;
                    grilleOrdi[numeroLigne][numeroColonne-2] = 3;
                    bateauPlace = bateauPlace + 1;
                }

                // Si le bateau doit être positionné verticalement alors
                if(numeroDirection == 2)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 3;
                    grilleOrdi[numeroLigne+1][numeroColonne] = 3;
                    grilleOrdi[numeroLigne+2][numeroColonne] = 3;
                    bateauPlace = bateauPlace + 1;
                }
            }

            // Si le bateau est un croiseur alors
            if(typeBateau == 4)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(numeroDirection == 1)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 2;
                    grilleOrdi[numeroLigne][numeroColonne-1] = 2;
                    grilleOrdi[numeroLigne][numeroColonne-2] = 2;
                    grilleOrdi[numeroLigne][numeroColonne-3] = 2;
                    bateauPlace = bateauPlace + 1;
                }

                // Si le bateau doit être positionné verticalement alors
                if(numeroDirection == 2)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 2;
                    grilleOrdi[numeroLigne+1][numeroColonne] = 2;
                    grilleOrdi[numeroLigne+2][numeroColonne] = 2;
                    grilleOrdi[numeroLigne+3][numeroColonne] = 2;
                    bateauPlace = bateauPlace + 1;
                }
            }

            // Si le bateau est un porte avion alors
            if(typeBateau == 5)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(numeroDirection == 1)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 1;
                    grilleOrdi[numeroLigne][numeroColonne-1] = 1;
                    grilleOrdi[numeroLigne][numeroColonne-2] = 1;
                    grilleOrdi[numeroLigne][numeroColonne-3] = 1;
                    grilleOrdi[numeroLigne][numeroColonne-4] = 1;
                    bateauPlace = bateauPlace + 1;
                }

                if(numeroDirection == 2)
                {
                    grilleOrdi[numeroLigne][numeroColonne] = 1;
                    grilleOrdi[numeroLigne+1][numeroColonne] = 1;
                    grilleOrdi[numeroLigne+2][numeroColonne] = 1;
                    grilleOrdi[numeroLigne+3][numeroColonne] = 1;
                    grilleOrdi[numeroLigne+4][numeroColonne] = 1;
                    bateauPlace = bateauPlace + 1;
                }
            }
        }
    }

    /**
     *
     * Fonction (back-end) permettant de placer des bateaux dans la grille Joueur selon ses parametres choisi par l'utilisateur
     * @param grilleJeu La grille en question
     * @param ligne La ligne ou placer le bateau
     * @param numColonne La colonne ou placer le bateau
     * @param typeBateau Le type de bateau
     * @param direction La direction du bateau
     */
        public static void initGrilleJeu(int [][]grilleJeu, int ligne, int numColonne, int typeBateau, int direction)
        {
            // Si le bateau est un torpilleur alors
            if(typeBateau == 1)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(direction == 1)
                {
                    grilleJeu[ligne][numColonne] = 5;
                    grilleJeu[ligne][numColonne-1] = 5;
                }

                // Si le bateau doit être positionné verticalement alors
                if(direction == 2)
                {
                    grilleJeu[ligne][numColonne] = 5;
                    grilleJeu[ligne+1][numColonne] = 5;
                }
            }

            // Si le bateau est un sous-marin alors
            if(typeBateau == 2)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(direction == 1)
                {
                    grilleJeu[ligne][numColonne] = 4;
                    grilleJeu[ligne][numColonne-1] = 4;
                    grilleJeu[ligne][numColonne-2] = 4;
                }

                // Si le bateau doit être positionné verticalement alors
                if(direction == 2)
                {
                    grilleJeu[ligne][numColonne] = 4;
                    grilleJeu[ligne+1][numColonne] = 4;
                    grilleJeu[ligne+2][numColonne] = 4;
                }
            }

            // Si le bateau est un contre-torpilleurs alors
            if(typeBateau == 3)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(direction == 1)
                {
                    grilleJeu[ligne][numColonne] = 3;
                    grilleJeu[ligne][numColonne-1] = 3;
                    grilleJeu[ligne][numColonne-2] = 3;
                }

                // Si le bateau doit être positionné verticalement alors
                if(direction == 2)
                {
                    grilleJeu[ligne][numColonne] = 3;
                    grilleJeu[ligne+1][numColonne] = 3;
                    grilleJeu[ligne+2][numColonne] = 3;
                }
            }

            // Si le bateau est un croiseur alors
            if(typeBateau == 4)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(direction == 1)
                {
                    grilleJeu[ligne][numColonne] = 2;
                    grilleJeu[ligne][numColonne-1] = 2;
                    grilleJeu[ligne][numColonne-2] = 2;
                    grilleJeu[ligne][numColonne-3] = 2;
                }

                // Si le bateau doit être positionné verticalement alors
                if(direction == 2)
                {
                    grilleJeu[ligne][numColonne] = 2;
                    grilleJeu[ligne+1][numColonne] = 2;
                    grilleJeu[ligne+2][numColonne] = 2;
                    grilleJeu[ligne+3][numColonne] = 2;
                }
            }

            // Si le bateau est un porte avion alors
            if(typeBateau == 5)
            {
                // Si le bateau doit être positionné horizontalement alors
                if(direction == 1)
                {
                    grilleJeu[ligne][numColonne] = 1;
                    grilleJeu[ligne][numColonne-1] = 1;
                    grilleJeu[ligne][numColonne-2] = 1;
                    grilleJeu[ligne][numColonne-3] = 1;
                    grilleJeu[ligne][numColonne-4] = 1;
                }

                if(direction == 2)
                {
                    grilleJeu[ligne][numColonne] = 1;
                    grilleJeu[ligne+1][numColonne] = 1;
                    grilleJeu[ligne+2][numColonne] = 1;
                    grilleJeu[ligne+3][numColonne] = 1;
                    grilleJeu[ligne+4][numColonne] = 1;
                }
            }
        }

    /**
     * Fonction permettant d'afficher la grille de l'ordinateur ainsi que celle de l'utilisateur
     * @param grilleJeu Recoit une grille transferer par variable pour pouvoir afficher celle-ci (ordinateur ou joueur pas d'importance)
     */
    public static void afficherGrille(int [][]grilleJeu)
    {
        int colonne = 0;
        char lettre = 65;
        int ascii = (int) lettre;


        // Print les colonnes (A,B,C,D,E,F,G,H,I,J)
        System.out.print("   ");
        for (int i = 0; i <= 9; i++)
        {
            System.out.print(lettre);
            System.out.print(" ");
            ascii = ascii + 1;
            lettre = (char) ascii;
        }

        System.out.println("");

        // Print les lignes (1,2,3,4,5,6,7,8,9,10)
        for(int indexLigne = 1; indexLigne <= 10; indexLigne++)
        {
            if(indexLigne < 10)
            {
                System.out.print(" " + indexLigne + " ");
            }
            else System.out.print(indexLigne + " ");
            for (int index = 0; index <= 9; index++)
            {
                System.out.print(grilleJeu[indexLigne-1][index]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

    /**
     * Fonction qui permet de terminer si la partie est fini (déterminer par les chiffres retrouvés dans la grille)
     *
     * @param grille recoit la grille de joueur ou bien celle de l'ordinateur
     * @return retourne vrai si tous les bateaux sont coulés dans la grille , retourne faux s'il contient toujours des bateaux dans la grille
     */
    public static boolean vainqueur(int[][] grille)
    {
        int valeur = 0;
        for (int i = 0; i < 10; i++)
        {
            for (int i2 = 0; i2 < 10 ; i2++)
            {
                valeur = grille[i][i2];
                if(valeur == 1 || valeur == 2 || valeur == 3 || valeur == 4 || valeur == 5)
                {
                    // Retourne faux, car il contient toujours des bateaux dans la grille
                    //System.out.println("Toujours en jeu");
                    return false;
                }
            }
        }
        // Retourne vrai, car il ne contient pas de bateaux dans la grille
        //System.out.println("Parti fini");
        return true;
    }

    /**
     * Fonction qui retourne un tableau contenant deux entiers tirés au hasard entre 0 et 9
     * @return retourne un tableau avec deux numéros générer aléatoirement
     */
    public static int[] tirOrdinateur(int[] tir)
    {
        // Génere les nombres
        tir[0] = randRange(0,10);
        tir[1] = randRange(0,10);

        return tir;
    }

    /**
     * Fonction qui affiche soit touché ou bien coulé lorsque l'utilisateur tir une torpille
     *
     * @param grille grille de l'ordinateur ou bien celle du joueur
     * @param numLignes numéro de ligne désiré y
     * @param numColonne numéro de colonne désiré x
     */
    public static boolean mouvement(int [][]grille, int numLignes, int numColonne)
    {
        // Si la valeur de la case choisie est différente de 0 alors
        if(grille[numLignes][numColonne] != 0)
        {
            // La torpille a touché un bateau
            //System.out.println("TOUCHE !");

            // On place le numéro 6 pour dire que le bateau a été touché
            grille[numLignes][numColonne] = 6;
            return true;

            /*
            // Vérifie si la torpille a coulé le bateau présent
            if(couler(grille, valeurBateau))
            {
                System.out.println("COULÉ !");
            }*/
        }

        // Sinon la torpille est tombé dans l'eau
        else
        {
            //System.out.println("MANQUE !");
            return false;
        }
    }
}
