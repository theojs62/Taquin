/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.taquin.controller;

import java.net.URL;



import fr.univartois.butinfo.ihm.taquin.model.Grid;
import fr.univartois.butinfo.ihm.taquin.model.ITaquinController;
import fr.univartois.butinfo.ihm.taquin.model.Taquin;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/**
 * La classe TaquinController propose un controleur permettant de gerer un jeu du Taquin
 * presente à l'utilisateur sous la forme d'une interface graphique JavaFX.
 *
 * @author  Journee Theo 
 * @version 0.1.0
 */
public class TaquinController implements ITaquinController {
	
	
	
    /**
     * Le label affichant le nombre de deplacements realises par l'utilisateur.
     */
    @FXML
    private Label nbMoves;

    /**
     * La grille affichant les boutons permettant de jouer au Taquin.
     */
    @FXML
    private GridPane gridPane;

    /**
     * Les boutons representant les tuiles deu jeu du Taquin.
     */
    private Button[][] buttons;

    /**
     * Le modele du Taquin avec lequel ce controleur interagit.
     */
    private Taquin taquin;
   
   private Scene scene;
   
    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.ihm.taquin.model.ITaquinController#setModel(fr.univartois.
     * butinfo.ihm.taquin.model.Taquin)
     */
    @Override
    public void setModel(Taquin taquin) {
        this.taquin = taquin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * fr.univartois.butinfo.ihm.taquin.model.ITaquinController#initGrid(fr.univartois.
     * butinfo.ihm.taquin.model.Grid)
     */
    @Override
    public void initGrid(Grid grid) {
    	buttons = new Button[grid.size()][grid.size()];
    	 for (int i = 0; i < grid.size(); i++) {
             for (int j = 0; j < grid.size(); j++) {
            	 int value =grid.get(i, j).getValue();
            	 buttons[i][j]= new Button();
                 buttons[i][j]= createButton(i,j,value);
                 gridPane.add(buttons[i][j], j, i);
                 buttons[i][j].setPrefHeight(100);
                 buttons[i][j].setPrefWidth(100);
                // buttons[i][j].textProperty().bind(grid.get(i, j).getValueProperty().asString());
                 grid.get(i, j).getValueProperty().isNotEqualTo(0);
                 buttons[i][j].visibleProperty().bind(grid.get(i, j).getValueProperty().isNotEqualTo(0));
                 buttons[i][j].setBackground(createBackground(grid.get(i, j).getValue()));
                 Button currentButton= buttons[i][j];
                 grid.get(i, j).getValueProperty().addListener(
                		    (p, o, n) -> currentButton.setBackground(createBackground(n)));

             }}
    }
    private Button createButton(int row,int column,int value) {
    	Button button = new Button();
    	//button.setText(Integer.toString(value));
    	//button.setOnAction(e -> taquin.push(row,column));
    	 button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> taquin.push(row,column));
    	return button;
    	
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see fr.univartois.butinfo.ihm.taquin.model.ITaquinController#updateMoves(int)
     */
    @Override
    public void updateMoves(IntegerProperty nbMoves) {
    	this.nbMoves.textProperty().bind(nbMoves.asString());

    }


    /*
     * (non-Javadoc)
     * 
     * @see fr.univartois.butinfo.ihm.taquin.model.ITaquinController#startGame()
     */
    @Override
    public void startGame() {
        int size = buttons.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setDisable(false);
            }
        }
    }

    /**
     * Redemarre le jeu affiché sur la vue.
     */
    @FXML
    public void restart() {
        taquin.restartGame();
    }

    /*
     * (non-Javadoc)
     * 
     * @see fr.univartois.butinfo.ihm.taquin.model.ITaquinController#endGame()
     */
    @Override
    public void endGame() {
        int size = buttons.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttons[i][j].setDisable(true);
            }
        }
    }

    /**
     * Determine l'arrière-plan de la tuile ayant l'indice donne.
     *
     * @param index L'indice de la tuile.
     *
     * @return L'arrière-plan associé à la tuile.
     */
    private Background createBackground(Number index) {
        URL urlImage = getClass().getResource("../view/images/iut-" + index + ".jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(urlImage.toExternalForm(), 100, 100, true, false),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }

	public void setScene(Scene scene) {
		this.scene = scene;
		scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
		    if (e.getCode() == KeyCode.UP) {
		        taquin.pushUp();
		    } else if (e.getCode() == KeyCode.LEFT) {
		        taquin.pushLeft();
		    } else if (e.getCode() == KeyCode.DOWN) {
		        taquin.pushDown();
		    } else if (e.getCode() == KeyCode.RIGHT) {
		        taquin.pushRight();
		    }
		    e.consume();
		});

	}
	@FunctionalInterface
	public interface ChangeListener<T> {
	    void changed(ObservableValue<? extends T> observable,
	                 T oldValue, T newValue);
	}


}
