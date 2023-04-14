package vidmot.nodes;

import presenter.TimerPresenter;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import vidmot.UpphafController;

public class TimerNode extends StackPane {
    private AnimationTimer timer;
    private TimerPresenter timerPresenter;
    private Rectangle background;
    private Label label;

    public TimerNode(TimerPresenter timerPresenter) {
        background = new Rectangle(80, 40);
        if(UpphafController.hallo==2){
            background.setFill(Color.HOTPINK);
        }
        else if(UpphafController.hallo==1){
            background.setFill(Color.GREEN);
        } else if(UpphafController.hallo==3){
            background.setFill(Color.LIGHTBLUE);}
        else background.setFill(new Color(146.0/255, 230.0/255, 129.0/255, 1.0));
        label = new Label();
        label.setFont(new Font("Impact", 20));
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
