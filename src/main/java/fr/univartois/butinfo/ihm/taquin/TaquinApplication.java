/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * (c) 2022 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.taquin;

import java.io.IOException;

import fr.univartois.butinfo.ihm.taquin.controller.TaquinController;
import fr.univartois.butinfo.ihm.taquin.model.Taquin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe TaquinApplication est la classe principale du jeu du Taquin fonctionnant
 * avec JavaFX.
 *
 * @author Theo Journee
 *
 * @version 0.1.0
 */
public class TaquinApplication extends Application {

    /**
     * Cette methode permet d'initialiser l'affichage de la fenetre de l'application.
     *
     * @param stage La fenetre (initialement vide) de l'application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Il faut d'abord recuperer la description de la vue (au format FXML).
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/taquin.fxml"));
        Parent viewContent = fxmlLoader.load();

        // Ensuite, on la place dans une Scene...
        Scene scene = new Scene(viewContent);
        // que l'on place elle-meme dans la fenetre.
        stage.setScene(scene);

        // On peut ensuite donner un titre à la fenetre.
        stage.setTitle("Taquin");

        // On cree enfin le jeu, et on l'associe au controleur.
        TaquinController controller = fxmlLoader.getController();
        Taquin taquin = new Taquin(4);
        controller.setModel(taquin);
        controller.setScene(scene);
        taquin.setController(controller);
        taquin.startGame();
    
        // Enfin, on affiche la fenetre.
        stage.show();
    }

    /**
     * Cette methode execute l'application JavaFX.
     *
     * @param args Les arguments de la ligne de commande (dont on ne tient pas compte).
     *
     * @see #launch(String...)
     */
    public static void main(String[] args) {
        launch();
    }

}
