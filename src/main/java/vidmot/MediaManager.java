package vidmot;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaManager {
    private static Media media;
    private static MediaPlayer mediaPlayer;

    static {
        media = new Media(MediaManager.class.getResource("audio/music.wav").toExternalForm());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
