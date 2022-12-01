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
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe Tile represente une tuile de la grille du jeu du Taquin.
 *
 * @author Theo Journee
 *
 * @version 0.1.0
 */
public class Tile {

    /**
     * La valeur de cette tuile.
     */
    private final IntegerProperty value;

    /**
     * Construit une nouvelle instance de Tile.
     *
     * @param value La valeur initiale de la tuile.
     */
    public Tile(int value) {
    	 this.value = new SimpleIntegerProperty(value);
    }

    /**
     * Modifie la valeur de cette tuile.
     *
     * @param value La nouvelle valeur de la tuile.
     */
    public void setValue(int value) {
       this.value.set(value);
    }

    /**
     * Donne la valeur de cette tuile.
     *
     * @return La valeur de cette tuile.
     */
    public int getValue() {
       return this.value.get();
    }
    
    public IntegerProperty getValueProperty() {
    	return value;
    }

    /**
     * echange la valeur de cette tuile avec celle d'une tuile donnee.
     *
     * @param other La tuile avec laquelle echanger la valeur.
     */
    public void exchange(Tile other) {
        int tmp = other.getValue();
        other.setValue(getValue());
        this.setValue(tmp);
    }

}
