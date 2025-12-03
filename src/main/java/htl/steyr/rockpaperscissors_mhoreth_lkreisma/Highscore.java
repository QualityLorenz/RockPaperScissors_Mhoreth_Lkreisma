package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import javafx.scene.shape.Path;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Arrays;

public class Highscore {

    private int highscore = 1;
    private int currentScore = 3;


    public int getHighscore() {
        return highscore;
    }


    public void setHighscore(int newhighscore){


        try{
            highscore = newhighscore;
            writeHighscore();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public void writeHighscore() throws IOException {
        FileWriter fileWriter = new FileWriter("highscore.txt");
        fileWriter.write(Integer.toString(highscore));
        fileWriter.close();
    }


}
