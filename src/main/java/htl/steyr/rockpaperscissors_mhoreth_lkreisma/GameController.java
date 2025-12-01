package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
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


    public void weaponButtonClicked(ActionEvent actionEvent) throws IOException {
        //holt sich das was auf dem Button steht
        myWeapon = ((Button) actionEvent.getSource()).getId();

        //in weapon steht nun die ausgewählte Waffe (Rock) (Paper) (Scissors)
        botWeapon = computer.chosenWeapon();



        Image botImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfBot())));
        botWeaponImageView.setImage(botImage);
        Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfMe())));
        myWeaponImageView.setImage(myImage);

        if(winnerOfMatch() == 1){
            System.out.println("! YOU WON !");
        }else if(winnerOfMatch() == 2){
            System.out.println("! ITS A DRAW !");
        }else if(winnerOfMatch() == 3){
            System.out.println("! THE BOT WON !");
        }else{
            //to prevent any errors if winnerOfMatch smaller than 1 or larger than 3
            System.out.println("ERROR, somethings not working");
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




    public int winnerOfMatch() {
        if (myWeapon.equals("rockButton") && botWeapon.equals("Scissors") ||
                myWeapon.equals("scissorsButton") && botWeapon.equals("Paper") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Rock")){
            return 1;
        }else if(myWeapon.equals("rockButton") && botWeapon.equals("Rock") ||
                myWeapon.equals("scissorsButton") && botWeapon.equals("Scissors") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Paper")) {
            return 2;
        }else{
            return 3;
        }
    }

    }

    