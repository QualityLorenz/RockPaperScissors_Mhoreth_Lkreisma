package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Rock! Paper! Scissors!");
        stage.setScene(scene);

/*
        Image rock = new Image(Objects.requireNonNull(getClass().getResourceAsStream("pictures/Stone.png")));
        ImageView rockView = new ImageView(rock);

        rockView.setFitHeight(100);
        rockView.setFitWidth(100);

        Button rockButton = new Button();

        rockButton.setGraphic(rockView);
 */

        stage.show();

    }
}
