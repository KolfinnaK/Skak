package vidmot.nodes;

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import presenter.TimerPresenter;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TimerNode extends StackPane {
    private AnimationTimer timer;
    private Rectangle background;
    private TimerPresenter timerPresenter;
    private Label label;

    private Font font = Font.font("Trebuchet MS", FontWeight.BOLD, 35);

    public TimerNode(TimerPresenter timerPresenter) {
        background = new Rectangle(80, 40);
        background.setFill(Color.rgb(0, 0, 0, 0.0));
        background.getStyleClass().add("fxTimerBackground");
        label = new Label();
        label.setFont(font);

        this.timerPresenter = timerPresenter;
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                label.setText(getTimerText(timerPresenter.getTimeRemaining()));
            }
        };
        timer.start();
        getChildren().addAll(background, label);
    }

    public static String getTimerText(long remainingMillis) {
        long remainingSeconds = (remainingMillis / 1000) % 60;
        long remainingMinutes = (remainingMillis / 1000) / 60;
        String remainingMinutesText = "", remainingSecondsText = "";
        if (remainingMinutes < 10)
            remainingMinutesText += "0";
        if (remainingSeconds < 10)
            remainingSecondsText += "0";
        remainingMinutesText += remainingMinutes;
        remainingSecondsText += remainingSeconds;
        return remainingMinutesText + ":" + remainingSecondsText;
    }
}
