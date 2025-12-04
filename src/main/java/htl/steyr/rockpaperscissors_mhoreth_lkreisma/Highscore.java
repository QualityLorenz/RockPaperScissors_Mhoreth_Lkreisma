package htl.steyr.rockpaperscissors_mhoreth_lkreisma;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Highscore {

    private int highscore = 0;



    public int getHighscore(){
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            return Integer.parseInt(reader.readLine()); //reads the number thats in the .txt and returns it as integer
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void setHighscore(int newhighscore){


        try{
            this.highscore = newhighscore;
            writeHighscore();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void writeHighscore() throws IOException {
        FileWriter fileWriter = new FileWriter("highscore.txt");
        fileWriter.write(Integer.toString(highscore)); //rewrites the .txt
        fileWriter.close();
    }


}
