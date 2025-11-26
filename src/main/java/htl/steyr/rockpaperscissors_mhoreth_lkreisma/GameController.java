package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class GameController {
    public ImageView myWeaponImageView;
    public ImageView botWeaponImageView;
    public Button scissorsButton;
    public Button paperButton;
    public Button rockButton;
    private String weapon;

    public void weaponButtonClicked(ActionEvent actionEvent) {
        //holt sich das was auf dem Button steht
        weapon = ((Button) actionEvent.getSource()).getText();
        //in weapon steht nun die ausgewählte Waffe (Rock) (Paper) (Scissors)
    }

}

    