/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.taquin.model;

import java.util.Random;

/**
 * La classe Grid represente la grille sur laquelle se joue le jeu du Taquin.
 *
 * @author Theo journee
 *
 * @version 0.1.0
 */
public class Grid {

    /**
     * Le genarateur de nombres aleatoires utilise pour melanger les tuiles sur cette
     * grille.
     */
    private static final Random RANDOM = new Random();

    /**
     * Le nombre de permutations à realiser au moment de melanger les tuiles sur la
     * grille.
     */
    private static final int NB_PERMUTATIONS = 1000;

    /**
     * La taille de la grille (en nombre de tuiles).
     */
    private final int size;

    /**
     * Les tuiles presentes sur cette grille.
     */
    private final Tile[][] allTiles;

    /**
     * La ligne  se trouve la tuile vide.
     */
    private int emptyRow;

    /**
     * La colonne se trouve la tuile vide.
     */
    private int emptyColumn;

    /**
     * Construit une nouvelle instance de Grid.
     * 
     * @param size La taille de la grille à construire.
     */
    public Grid(int size) {
        this.size = size;
        this.allTiles = new Tile[size][size];
        initialize();
    }

    /**
     * Initialise cette grille en creant les tuiles qu'elle contient.
     */
    private void initialize() {
        // On cree les tuiles dans l'orde.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = i * size + j + 1;
                if (value == size * size) {
                    value = 0;
                }
                allTiles[i][j] = new Tile(value);
            }
        }

        // On s'assure que la tuile vide est dans le coin inferieur droit.
        emptyRow = size - 1;
        emptyColumn = size - 1;
    }

    /**
     * Reinitialise cette grille en reordonnant les tuiles qu'elle contient.
     */
    public void reset() {
        // On remet les tuiles dans l'orde.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = i * size + j + 1;
                if (value == size * size) {
                    value = 0;
                }
                allTiles[i][j].setValue(value);
            }
        }

        // On s'assure que la tuile vide est dans le coin inferieur droit.
        emptyRow = size - 1;
        emptyColumn = size - 1;
    }

    /**
     * Donne la taille de la grille (en nombre de tuiles).
     *
     * @return La taille de la grille.
     */
    public int size() {
        return size;
    }

    /**
     * Vérifie si un indice donné se trouve bien sur la grille.
     *
     * @param i L'indice à vérifier.
     *
     * @return Si l'indice est bien sur cette grille.
     */
    public boolean checkIndex(int i) {
        return (0 <= i) && (i < size);
    }

    /**
     * Vérifie si une position donnée dans la grille est voisine de la tuile vide.
     *
     * @param row La ligne à vérifier.
     * @param column La colonne à vérifier.
     *
     * @return Si la position donnée est voisine de la tuile vide.
     */
    private boolean isNearEmpty(int row, int column) {
        if (!checkIndex(row) || !checkIndex(column)) {
            // La position est en dehors de la grille.
            return false;
        }

        // On compare la position avec celle de la tuile vide.
        return ((row - 1 == emptyRow) && (column == emptyColumn))
                || ((row + 1 == emptyRow) && (column == emptyColumn))
                || ((row == emptyRow) && (column - 1 == emptyColumn))
                || ((row == emptyRow) && (column + 1 == emptyColumn));
    }

    /**
     * Donne la tuile à la position demandée sur cette grille.
     *
     * @param row La ligne de la tuile à récupérer.
     * @param column La colonne de la tuile à récupérer.
     *
     * @return La tuile à la position demandée.
     */
    public Tile get(int row, int column) {
        return allTiles[row][column];
    }

    /**
     * Pousse la case à la position donnée dans l'emplacement vide.
     *
     * @param row La ligne de la case à pousser.
     * @param column La colonne de la case à pousser.
     *
     * @return Si un déplacement a effectivement eu lieu.
     */
    public boolean push(int row, int column) {
        if (isNearEmpty(row, column)) {
            Tile empty = allTiles[emptyRow][emptyColumn];
            Tile other = allTiles[row][column];
            empty.exchange(other);
            emptyRow = row;
            emptyColumn = column;
            return true;
        }

        return false;
    }

    /**
     * Pousse la case situé sous l'emplacement vide dans cet emplacement.
     *
     * @return Si un déplacement a effectivement eu lieu.
     */
    public boolean pushUp() {
        return push(emptyRow + 1, emptyColumn);
    }

    /**
     * Pousse la case situéz à gauche de l'emplacement vide dans cet emplacement.
     *
     * @return Si un déplacement a effectivement eu lieu.
     */
    public boolean pushRight() {
        return push(emptyRow, emptyColumn - 1);
    }

    /**
     * Pousse la case situe au dessus de l'emplacement vide dans cet emplacement.
     *
     * @return Si un déplacement a effectivement eu lieu.
     */
    public boolean pushDown() {
        return push(emptyRow - 1, emptyColumn);
    }

    /**
     * Pousse la case situe à droite de l'emplacement vide dans cet emplacement.
     *
     * @return Si un deplacement a effectivement eu lieu.
     */
    public boolean pushLeft() {
        return push(emptyRow, emptyColumn + 1);
    }

    /**
     * Melange les tuiles de cette grille, en respectant la regle du deplacement des
     * tuiles par l'intermédiaire de la tuile vide.
     *
     * Il est important de preciser que l'on ne peut pas juste mélanger les valeurs sans
     * respecter cette règle, car cela pourrait creer des combinaisons qu'il n'est pas
     * possible de resoudre.
     */
    public void shuffle() {
        for (int i = 0; i < NB_PERMUTATIONS; i++) {
            int row;
            int col;

            do {
                // On commence par choisir la ligne (relativement à la case vide).
                row = RANDOM.nextInt(-1, 2);

                if (row == 0) {
                    // La ligne est la meme que celle de l'emplacement vide.
                    // Il faut donc forcer le changement de colonne.
                    col = 2 * RANDOM.nextInt(2) - 1;

                } else {
                    // La colonne doit rester fixe.
                    col = 0;
                }

                // On calcule la position (absolue) de la tuile à deplacer.
                row += emptyRow;
                col += emptyColumn;

            } while (!checkIndex(col) || !checkIndex(row));

            // On pousse la tuile selectionnée.
            push(row, col);
        }
    }

    /**
     * Verifie si les tuiles sont ordonnees sur la grille.
     *
     * @return Si les tuiles sont ordonnees.
     */
    public boolean isOrdered() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int value = i * size + j + 1;
                if ((value != size * size) && (value != allTiles[i][j].getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

}
