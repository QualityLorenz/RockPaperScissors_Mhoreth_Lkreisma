
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

    // ImageViews für die Anzeige der Waffen
    public ImageView myWeaponImageView;
    public ImageView botWeaponImageView;

    @FXML
    private ChoiceBox<String> musicChoiceBox; // Musik-Auswahlbox
    private MediaPlayer mediaPlayer; // aktueller Musikplayer

    private final Computer computer = new Computer(); // Bot-Logik

    // Buttons für die Waffenwahl
    public Button rockButton;
    public Button paperButton;
    public Button scissorsButton;
    public Button wellButton;

    public AnchorPane RockPaperScissorsAnchorpane;

    public Label displayWinner; // Hauptcontainer für das Spiel
    private String myWeapon; // vom Spieler gewählte Waffe
    private String botWeapon; // vom Bot gewählte Waffe

    public Label scoreLabel;
    public Label highscoreLabel;

    Highscore score = new Highscore();
    private int count = 0;

    public void initialize() {

        highscoreLabel.setText(String.valueOf(score.getHighscore()));
        // Musikdateien zur ChoiceBox hinzufügen
        musicChoiceBox.getItems().addAll(
                "Gelbton - Losing control.mp3",
                "Mii_Lobbymusic.mp3",
                "Push_Push_Push.mp3"
        );

        // Listener: wird ausgelöst, wenn der Benutzer eine neue Musik auswählt
        musicChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) return;

            // Stoppe vorherige Musik, falls vorhanden
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }



            // Stoppe vorherige Musik, falls vorhanden
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }

            // Lade die neue Musikdatei aus dem Ressourcenpfad
            String resourcePath = "/htl/steyr/rockpaperscissors_mhoreth_lkreisma/music/" + newValue;
            URL url = getClass().getResource(resourcePath);
            if (url == null) {
                System.err.println("Resource nicht gefunden: " + resourcePath);
                return;
            }

            // Starte neuen MediaPlayer mit der ausgewählten Musik
            Media sound = new Media(url.toExternalForm());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.5); // Lautstärke auf 50 %
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Endlosschleife
            mediaPlayer.play();
        });
    }

    public void weaponButtonClicked(ActionEvent actionEvent) {
        botWeaponImageView.setImage(null);
        displayWinner.setText(null);
        // Hole die ID des gedrückten Buttons (z. B. "rockButton")
        myWeapon = ((Button) actionEvent.getSource()).getId();

        // Setze das Bild für die eigene Waffe
        Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfMe())));
        myWeaponImageView.setImage(myImage);

        // Bot wählt zufällig eine Waffe
        botWeapon = computer.chosenWeapon();

        // Starte die Fortschrittsanzeige
        progressBar();
    }

    public String pictureOfBot(){
        return switch(botWeapon){
            case "Rock" -> "pictures/Rock.png";
            case "Paper" -> "pictures/Paper.png";
            case "Scissors" -> "pictures/Scissors.png";
            case "Well" -> "pictures/brunnen.png";
            default -> null;
        };
    }

    // Liefert den Bildpfad für die eigene Waffe basierend auf Button-ID
    public String pictureOfMe() {
        return switch (myWeapon) {
            case "rockButton" -> "pictures/Rock.png";
            case "paperButton" -> "pictures/Paper.png";
            case "scissorsButton" -> "pictures/Scissors.png";
            case "wellButton" -> "pictures/brunnen.png";
            default -> null;
        };
    }

    // Berechnet den Gewinner des Spiels
    public int winnerOfMatch() {
        if (myWeapon.equals("rockButton") && botWeapon.equals("Scissors") ||
                myWeapon.equals("scissorsButton") && botWeapon.equals("Paper") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Rock") ||

                myWeapon.equals("wellButton") && botWeapon.equals("Rock") ||
                myWeapon.equals("wellButton") && botWeapon.equals("Scissors")) {
            return 1; // Spieler gewinnt
        } else if (myWeapon.equals("rockButton") && botWeapon.equals("Rock") ||
                myWeapon.equals("scissorsButton") && botWeapon.equals("Scissors") ||
                myWeapon.equals("paperButton") && botWeapon.equals("Paper") ||
                myWeapon.equals("wellButton") && botWeapon.equals("Well")) {
            return 2; // Unentschieden
        } else {
            return 3; // Bot gewinnt
        }
    }

    // Zeigt das Ergebnis des Spiels an
    public void resultOfMatch() {
        Image botImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pictureOfBot())));
        botWeaponImageView.setImage(botImage);



        if(winnerOfMatch() == 1){
                displayWinner.setText("! YOU WON !");
                ++count; //akuteller score wird erhoet
                if(count > score.getHighscore()){
                    score.setHighscore(count); //Wenn der aktuelle score hoeher als der alte highscore ist wird die .txt + label aktualisiert
                    highscoreLabel.setText(String.valueOf(score.getHighscore()));

                }
        }else if(winnerOfMatch() == 2){
            displayWinner.setText("! ITS A DRAW !");
        }else if(winnerOfMatch() == 3){
            displayWinner.setText("! THE BOT WON !");
            count = 0;
        }else{
            //to prevent any errors if winnerOfMatch smaller than 1 or larger than 3
            System.out.println("ERROR, somethings not working");
        }

        scoreLabel.setText(String.valueOf(count));
    }


    // Fortschrittsbalken mit Zufallsdauer und Animation
    public synchronized void progressBar() {
        ProgressBar progressbar = new ProgressBar();
        progressbar.setVisible(true);
        RockPaperScissorsAnchorpane.getChildren().add(progressbar);
        progressbar.setPrefWidth(80);
        progressbar.setPrefHeight(20);
        progressbar.setLayoutY((RockPaperScissorsAnchorpane.getPrefHeight()/3)*2);
        progressbar.setLayoutX((RockPaperScissorsAnchorpane.getPrefWidth()/2)-40);
        progressbar.setProgress(0);

        // Animation in separatem Thread
        Thread thread = new Thread(() -> {
            int progresstime = 1000 + new Random().nextInt(3000); // Zufällige Dauer

            try {
                long startTime = System.currentTimeMillis();
                long endTime = startTime + progresstime;

                while (System.currentTimeMillis() < endTime) {
                    double progress = (double) (System.currentTimeMillis() - startTime) / progresstime;
                    Platform.runLater(() -> progressbar.setProgress(progress));
                    Thread.sleep(20); // flüssige Animation
                }

                // Fortschrittsbalken ausblenden und Ergebnis anzeigen
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

    // Alternative Methode zum Befüllen der ChoiceBox (nicht verwendet, da initialize es schon macht)
    public void displayChoiceBox() {
        musicChoiceBox.getItems().add("Gelbton - Losing control.mp3");
        musicChoiceBox.getItems().add("Mii_Lobbymusic.mp3");
        musicChoiceBox.getItems().add("Push_Push_Push.mp3");
    }
}

    