package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;
import java.util.Random;

public class GameController {
    public ImageView myWeaponImageView;
    public ImageView botWeaponImageView;


    private final Computer computer = new Computer();

    public Button rockButton;
    public Button paperButton;
    public Button scissorsButton;
    public AnchorPane RockPaperScissorsAnchorpane;

    private String myWeapon;
    private String botWeapon;



    public void weaponButtonClicked(ActionEvent actionEvent) {
        //holt sich das was auf dem Button steht
        myWeapon = ((Button) actionEvent.getSource()).getId();

        Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfMe())));
        myWeaponImageView.setImage(myImage);

        //in weapon steht nun die ausgewählte Waffe (Rock) (Paper) (Scissors)
        botWeapon = computer.chosenWeapon();

        progressBar();

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
            return 1; // -> I Win
        }else if(myWeapon.equals("rockButton") && botWeapon.equals("Rock") ||
                myWeapon.equals("scissorsButton") && botWeapon.equals("Scissors") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Paper")) {
            return 2; // -> Its a Draw
        }else{
            return 3; // -> The Bot Won
        }
    }

    public void resultOfMatch(){
        Image botImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfBot())));
        botWeaponImageView.setImage(botImage);



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

    public synchronized void progressBar(){
        ProgressBar progressbar = new ProgressBar();
        progressbar.setVisible(true);
        RockPaperScissorsAnchorpane.getChildren().add(progressbar);
        progressbar.setPrefWidth(80);
        progressbar.setPrefHeight(20);
        progressbar.setLayoutY((RockPaperScissorsAnchorpane.getPrefHeight()/3)*2);
        progressbar.setLayoutX((RockPaperScissorsAnchorpane.getPrefWidth()/2)-40);
        progressbar.setProgress(0);

        Thread thread = new Thread(() -> {
            int progresstime = 1000 + new Random().nextInt(3000);

            try {
                long startTime = System.currentTimeMillis();
                long endTime = startTime + progresstime;
                System.out.println("Time: " + progresstime); //to find out how long it took

                while (System.currentTimeMillis() < endTime) {
                    double progress = (double) (System.currentTimeMillis() - startTime) / progresstime;
                    Platform.runLater(() -> progressbar.setProgress(progress));

                    // CHANGE: for a smooth animation later
                    Thread.sleep(20);
                }

                // Finish up
                Platform.runLater(() -> {
                    progressbar.setProgress(1.0); //that the progressbar has to be visually full
                    progressbar.setVisible(false);
                    resultOfMatch(); // Call the result method here!
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

}

    