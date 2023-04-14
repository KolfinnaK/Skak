package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import presenter.GameMediator;
import presenter.MediatorConstructionFlags;
import presenter.TimerPresenter;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/* erum ekki að nota þetta

public class SkakController extends UpphafController {

    @FXML
    public Label klukka1;
    @FXML
    public Label klukka2;
    @FXML
    public Button fxHomeButton;
    @FXML
    public Button fxHljodtakki;
    public int timiEftir;
    public int timiEftir2;
    private Timeline timeline1;
    private Timeline timeline2;
    private boolean erKlukka1adTeljaNidur=true;
    private TimaController timaController = (TimaController) ViewSwitcher.lookup(View.TIMAMORK);

    private String bot;
    private boolean isLocalTime;
    private int duration;
    private MediatorConstructionFlags constructionFlag;

    public String getBot() {
        return bot;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

    public void setLocalTime(boolean localTime) {
        isLocalTime = localTime;
    }

    //public void setConstructionFlag(MediatorConstructionFlags constructionFlag) {
      //  this.constructionFlag = constructionFlag;
   // }

    public void initialize() {
        ImageView homeIcon = new ImageView(new Image(getClass().getResource("images/home_icon.png").toExternalForm()));
        homeIcon.setFitWidth(30);
        homeIcon.setFitHeight(25);
        homeIcon.setPreserveRatio(true);
        fxHomeButton.setGraphic(homeIcon);

        ImageView playIcon = new ImageView(new Image(getClass().getResource("images/play.png").toExternalForm()));
        playIcon.setFitHeight(30);
        playIcon.setFitWidth(25);
        playIcon.setPreserveRatio(true);
        fxHljodtakki.setGraphic(playIcon);

        duration = timaController.getDuration();
        //GameMediator leikur = new GameMediator(constructionFlag, duration, bot);


        timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(1), this::updateTimer1));
        timeline1.setCycleCount(Timeline.INDEFINITE);
        timeline1.play();

        timeline2=new Timeline(
                new KeyFrame(Duration.seconds(1), this::updateTimer2));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.play();
    }

    public void updateTimer1(ActionEvent event) {
        klukka1.setText(String.format("%02d:%02d", timiEftir / 60, timiEftir % 60));
        if (erKlukka1adTeljaNidur) {
            timiEftir--;
        }
        if (timiEftir == 0) {
            timeline1.stop();
            klukka1.setText(String.format("%02d:%02d", 0, 0));
        }
    }
    public void updateTimer2(ActionEvent event){
        klukka2.setText(String.format("%02d:%02d", timiEftir2 / 60, timiEftir2 % 60));
        if(!erKlukka1adTeljaNidur){
           timiEftir2--;}
        if(timiEftir2==0){
            timeline2.stop();
            klukka2.setText(String.format("%02d:%02d",0, 0)); //eh hluta vegna þá fer hann í 00:02 og svo beint í 0
            //ef þessi seinasta lína er ekki þá fer hann bar í 00:01 og stoppar þar

        }
    }

    public void clickHandler(MouseEvent mouseEvent) {
    }

    public void svissaKlukkum(ActionEvent actionEvent) {
        if(erKlukka1adTeljaNidur){
            timeline1.stop();
            timeline2.play();
        }
        else{
            timeline2.stop();
            timeline1.play();
        } erKlukka1adTeljaNidur=!erKlukka1adTeljaNidur;
    }


}

 */
