import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ErrorSound {

    public static void clicked(){

        String path = CreateExtractor.path+"effects\\error.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
