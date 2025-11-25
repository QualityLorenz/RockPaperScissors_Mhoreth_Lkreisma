package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import java.util.concurrent.ThreadLocalRandom;

public class Computer {

    public int chosenWeapon(){

        return ThreadLocalRandom.current().nextInt(1, 3 + 1);
    }
}
