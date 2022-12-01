/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.taquin.model;

import javafx.beans.property.IntegerProperty;

/**
 * L'interface ITaquinController définit le contrat qui doit être respecté par n'importe
 * quel contrôleur du jeu du Taquin.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public interface ITaquinController {

    /**
     * Modifie la façade du jeu du Taquin avec laquelle ce contrôleur interagit.
     *
     * @param taquin Le façade du jeu du Taquin avec laquelle interagir.
     */
    void setModel(Taquin taquin);

    /**
     * Initialise la grille du Taquin affichée par ce contrôleur.
     *
     * @param grid La grille du jeu.
     */
    void initGrid(Grid grid);

    /**
     * Met à jour l'affichage du nombre de déplacements.
     *
     * @param nbMoves Le nombre de déplacements.
     */
    void updateMoves(IntegerProperty nbMoves);

    /**
     * Prépare une nouvelle partie sur la vue.
     */
    void startGame();

    /**
     * Termine la partie en cours sur la vue.
     */
    void endGame();

}
