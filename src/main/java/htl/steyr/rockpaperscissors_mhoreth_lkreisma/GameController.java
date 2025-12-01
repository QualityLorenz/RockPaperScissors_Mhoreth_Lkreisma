package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class GameController {
    public ImageView myWeaponImageView;
    public ImageView botWeaponImageView;
    public ImageView stoneImageView;
    public ImageView paperImageView;
    public ImageView scissorsImageView;

    private final Computer computer = new Computer();

    public Button rockButton;
    public Button paperButton;
    public Button scissorsButton;

    private String myWeapon;
    private String botWeapon;
    private String weapon;


    public void weaponButtonClicked(ActionEvent actionEvent) {
        //holt sich das was auf dem Button steht
        myWeapon = ((Button) actionEvent.getSource()).getId();

        //in weapon steht nun die ausgewählte Waffe (Rock) (Paper) (Scissors)
        botWeapon = computer.chosenWeapon();


        Image botImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfBot())));
        botWeaponImageView.setImage(botImage);
        Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfMe())));
        myWeaponImageView.setImage(myImage);

        if(winnerOfMatch()){
            System.out.println("! YOU WON !");
        }else{
            if(myWeapon.equals(botWeapon)){
                System.out.println("It's a draw!");
            }else {
            System.out.println("! THE BOT WON :( !");
            }
        }




    }

    public String pictureOfBot(){
        return switch(botWeapon){
            case "Rock" -> "pictures/Rock.png";
            case "Paper" -> "pictures/Paper.png";
            case "Scissors" -> "pictures/Scissors.png";
            default -> null;
        };
    }
    public String pictureOfMe(){
        System.out.println(myWeapon);
        return switch(myWeapon){
            case "rockButton" -> "pictures/Rock.png";
            case "paperButton" -> "pictures/Paper.png";
            case "scissorsButton" -> "pictures/Scissors.png";
            default -> null;
        };
    }




    public boolean winnerOfMatch() {
        if (myWeapon.equals("Rock") && botWeapon.equals("Scissors") ||
                myWeapon.equals("Scissors") && botWeapon.equals("Paper") ||
                myWeapon.equals("Paper") && botWeapon.equals("Rock")){
            return true;
        }else{
            return false;
        }
    }

    }

    