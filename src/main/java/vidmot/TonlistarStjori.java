package vidmot;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TonlistarStjori {
    private static Media media;
    private static MediaPlayer mediaPlayer;

    //nær í hljóðskrána og spilar hana
    static {
        media = new Media(TonlistarStjori.class.getResource("tonlist/music.wav").toExternalForm());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
