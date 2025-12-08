package htl.steyr.rockpaperscissors_mhoreth_lkreisma;

import java.util.concurrent.ThreadLocalRandom;

public class Computer {

    public String chosenWeapon() {

        return switch (ThreadLocalRandom.current().nextInt(1, 4 + 1)) {
            case 1 -> "Rock";
            case 2 -> "Paper";
            case 3 -> "Scissors";
            case 4 -> "Well";
            default -> chosenWeapon();
        };

    }
}
