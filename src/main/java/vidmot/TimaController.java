package vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
//<<<<<<< Updated upstream
//=======
import javafx.scene.control.Label;
//>>>>>>> Stashed changes
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import presenter.GameMediator;
import presenter.MediatorConstructionFlags;

/******************************************************************************
 *  Nafn    : Lilja Kolbrún Schopka
 *  T-póstur: lks17@hi.is
 *
 *  Lýsing  :
 *
 *
 *
 *
 *****************************************************************************/
public class TimaController extends UpphafController {
    @FXML
    private Label klukka;
    @FXML
    private Label klukka2;
    @FXML
    private Button fxHomeButton;
    @FXML
    private Button fxHljodtakki;
    public static int timiEftirShared;
    public static int timiEftir2Shared;

    private Timeline timeline1;
    private Timeline timeline2;
    private int duration;
    private String bot;
    private boolean erKlukka1adTeljaNidur=true;
    private MediatorConstructionFlags constructionFlag;
    private UpphafController upphafController = (UpphafController) ViewSwitcher.lookup(View.UPPHAFSSENA);
    private ErfidleikaController erfidleikaController = (ErfidleikaController) ViewSwitcher.lookup(View.ERFIDLEIKASENA);


    public void fx1minHandler(ActionEvent actionEvent) {
        timiEftirShared=60;
        timiEftir2Shared=60;
        duration = 60000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 600, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 600, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }

        //ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx3minHandler(ActionEvent actionEvent) {
        timiEftirShared=180;
        timiEftir2Shared=180;
        duration = 180000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 600, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 600, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }


        //ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx5minHandler(ActionEvent actionEvent) {
        timiEftirShared=300;
        timiEftir2Shared=300;
        duration = 300000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 600, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 600, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }


        //ViewSwitcher.switchTo(View.SKAKSENA);
    }

    public void fx10minHandler(ActionEvent actionEvent) {
        timiEftirShared=600;
        timiEftir2Shared=600;
        duration = 600000;

        if(bot == null) {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 500, new GameMediator(MediatorConstructionFlags.TIMED_LOCAL, duration)));
        }
        else{
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new ChessBoardScene(600, 500, new GameMediator(MediatorConstructionFlags.TIMED_AI, duration, bot)));
        }

    }

    public void initialize(){
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

        bot = erfidleikaController.getBot();
    }

    /*public static int getTimiEftirShared() {
        return timiEftirShared;
    }

    public static int getTimiEftir2Shared() {
        return timiEftir2Shared;
    }*/

    public int getDuration() {
        return duration;
    }

    public void setBot(String bot) {
        this.bot = bot;
    }

    public void setConstructionFlag(MediatorConstructionFlags constructionFlag) {
        this.constructionFlag = constructionFlag;
    }

}


