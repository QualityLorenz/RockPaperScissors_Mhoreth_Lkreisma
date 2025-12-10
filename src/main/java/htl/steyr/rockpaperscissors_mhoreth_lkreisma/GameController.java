package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;
import java.util.Random;

public class GameController {

    // imageviews for displaying the weapons
    public ImageView myWeaponImageView;
    public ImageView botWeaponImageView;
    public Label MusicPlayerLabel;

    @FXML
    private ChoiceBox<String> musicChoiceBox;
    private MediaPlayer mediaPlayer;

    private final Computer computer = new Computer();

    //weapon buttons
    public Button rockButton;
    public Button paperButton;
    public Button scissorsButton;
    public Button wellButton;

    public AnchorPane RockPaperScissorsAnchorpane;

    //display weapon
    public Label displayWinner;
    private String myWeapon;
    private String botWeapon;


    //score variables
    Highscore highscore = new Highscore();
    private int currentScore = 0;
    public Label highscoreLabel;
    public Label scoreLabel;

    public void initialize() {

        highscoreLabel.setText(String.valueOf(highscore.getHighscore()));
        // add music files to the choicebox
        musicChoiceBox.getItems().addAll(
                "Gelbton - Losing control.mp3",
                "Mii_Lobbymusic.mp3",
                "Push_Push_Push.mp3"
        );

        // listener: triggered when the user selects new music
        musicChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) return;

            // stop previous music if available
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            // stop previous music if available
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            // load the new music file from the resource path
            String resourcePath = "/htl/steyr/rockpaperscissors_mhoreth_lkreisma/music/" + newValue;
            URL url = getClass().getResource(resourcePath);
            if (url == null) {
                System.err.println("resource not found: " + resourcePath);
                return;
            }

            // start new mediaplayer with the selected music
            Media sound = new Media(url.toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.18); // set volume to 18%
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // loop indefinitely
            mediaPlayer.play();
        });
    }

    public void weaponButtonClicked(ActionEvent actionEvent) {
        botWeaponImageView.setImage(null);
        displayWinner.setText(null);
        // get the id of the pressed button (e.g. "rockButton")
        myWeapon = ((Button) actionEvent.getSource()).getId();

        // set the image for the player's weapon
        Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfMe())));
        myWeaponImageView.setImage(myImage);

        // bot randomly chooses a weapon
        botWeapon = computer.chosenWeapon();

        // start the progress bar animation
        progressBar();
    }

    public String pictureOfBot() {
        return switch (botWeapon) {
            case "Rock" -> "pictures/Rock.png";
            case "Paper" -> "pictures/Paper.png";
            case "Scissors" -> "pictures/Scissors.png";
            case "Well" -> "pictures/Well.png";
            default -> null;
        };
    }

    // returns the image path for the player's weapon based on button id
    public String pictureOfMe() {
        return switch (myWeapon) {
            case "rockButton" -> "pictures/Rock.png";
            case "paperButton" -> "pictures/Paper.png";
            case "scissorsButton" -> "pictures/Scissors.png";
            case "wellButton" -> "pictures/Well.png";
            default -> null;
        };
    }

    // determines the winner of the match
    public int winnerOfMatch() {
        if (myWeapon.equals("rockButton") && botWeapon.equals("Scissors") ||
                myWeapon.equals("scissorsButton") && botWeapon.equals("Paper") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Rock") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Well") ||

                myWeapon.equals("wellButton") && botWeapon.equals("Rock") ||
                myWeapon.equals("wellButton") && botWeapon.equals("Scissors")) {
            return 1; // player wins
        } else if (myWeapon.equals("rockButton") && botWeapon.equals("Rock") ||
                myWeapon.equals("scissorsButton") && botWeapon.equals("Scissors") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Paper") ||
                myWeapon.equals("wellButton") && botWeapon.equals("Well")) {
            return 2; // draw
        } else {
            return 3; // bot wins
        }
    }

    // displays the result of the match
    public void resultOfMatch() {
        Image botImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfBot())));
        botWeaponImageView.setImage(botImage);

        if (winnerOfMatch() == 1) {
            displayWinner.setText("! YOU WON !");
            ++currentScore; // increase current score
            if (currentScore > highscore.getHighscore()) {
                highscore.setHighscore(currentScore); // if current score is higher than old highscore, update .txt + label
                highscoreLabel.setText(String.valueOf(highscore.getHighscore()));
            }
        } else if (winnerOfMatch() == 2) {
            displayWinner.setText("! ITS A DRAW !");
        } else if (winnerOfMatch() == 3) {
            displayWinner.setText("! THE BOT WON !");
            currentScore = 0;
        } else {
            // to prevent any errors if winnerOfMatch is less than 1 or greater than 3
            System.out.println("error, something is not working");
        }
        scoreLabel.setText(String.valueOf(currentScore));
    }

    // progress bar with random duration and animation
    public synchronized void progressBar() {
        ProgressBar progressbar = new ProgressBar();
        progressbar.setId("theProgressbar");
        progressbar.setVisible(true);
        RockPaperScissorsAnchorpane.getChildren().add(progressbar);
        progressbar.setPrefWidth(80);
        progressbar.setPrefHeight(20);
        progressbar.setLayoutY((RockPaperScissorsAnchorpane.getPrefHeight() / 3) * 2);
        progressbar.setLayoutX((RockPaperScissorsAnchorpane.getPrefWidth() / 2) - 40);

        progressbar.setProgress(0);

        // animation in a separate thread
        Thread thread = new Thread(() -> {
            int progresstime = 1000 + new Random().nextInt(3000); // random duration

            try {
                long startTime = System.currentTimeMillis();
                long endTime = startTime + progresstime;

                while (System.currentTimeMillis() < endTime) {
                    double progress = (double) (System.currentTimeMillis() - startTime) / progresstime;
                    Platform.runLater(() -> progressbar.setProgress(progress));
                    Thread.sleep(20); // smooth animation
                    rockButton.disableProperty();
                    scissorsButton.setDisable(true);
                    paperButton.setDisable(true);
                    wellButton.setDisable(true);
                }

                // hide progress bar and display result
                Platform.runLater(() -> {
                    progressbar.setProgress(1.0); // ensure progress bar is visually full
                    progressbar.setVisible(false);
                    rockButton.setDisable(false);
                    paperButton.setDisable(false);
                    scissorsButton.setDisable(false);
                    wellButton.setDisable(false);
                    resultOfMatch(); // call the result method here
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

}
