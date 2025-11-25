package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void rockButtonClicked(ActionEvent actionEvent) {
    }

    public void paperButtonClicked(ActionEvent actionEvent) {
    }

    public void scissorsButtonClicked(ActionEvent actionEvent) {
    }
}
