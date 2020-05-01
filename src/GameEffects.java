import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
Static Methods of common game effects.
 */
public class GameEffects {
    //Plays a click sound effect
    public static void playClickSound() {
        try {
            FileInputStream fileInputStream = new FileInputStream("Music/button.mp3");
            Player player = new Player(fileInputStream);
            player.play();

        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }

    }

    public static void playMusic() {

        // music.play();
        /*
        TODO: Make music play
         */

    }

    public static void stopMusic() {
        // music.pause();
        /*
        TODO: Make music stop
         */
    }

}
