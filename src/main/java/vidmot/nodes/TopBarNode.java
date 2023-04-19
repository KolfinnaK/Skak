package vidmot.nodes;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import vidmot.MediaManager;
import vidmot.UpphafController;

import java.io.IOException;
import java.util.Optional;

import static vidmot.UpphafController.isBot;


public class TopBarNode extends HBox {

    private HBox root;
    private Button fxHeimaTakki;
    private ToggleButton fxHljodtakkiToggle;

    private HBox fxTakkaGeymsla

    private Pane fxTitleLBarLogo;
    private Font font = Font.font("Trebuchet MS", FontWeight.BOLD, 35);

    private final MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();





    public TopBarNode() {
        root = new HBox();
        root.setPrefSize(700, 76);



        //build buttons
        buildHomeButton();
        buildSoundButton();
        //Home button
        getChildren().add(root);
        root.getChildren().add(fxHeimaTakki);
        //heimatakki

        //hljodtakki
        root.getChildren().add(fxHljodtakkiToggle);

        //Title
        fxTitleLBarLogo = new Pane();
        fxTitleLBarLogo.setLayoutX(412);
        fxTitleLBarLogo.setLayoutY(16);
        fxTitleLBarLogo.setPrefSize(256, 45);
        fxTitleLBarLogo.getStyleClass().add("fxTopBarLogo");

        fxHljodtakkiToggle.setMinSize(45,45);
        fxHljodtakkiToggle.setMaxSize(45,45);

        fxHeimaTakki.setMinSize(45,45);
        fxHeimaTakki.setMaxSize(45,45);

        root.getChildren().add(fxTitleLBarLogo);
        /*
        AnchorPane.setRightAnchor(fxTitleLBarLogo, 32.0);
        AnchorPane.setBottomAnchor(fxTitleLBarLogo, 15.0);
        AnchorPane.setTopAnchor(fxTitleLBarLogo, 16.0);
         */
        fxTakkaGeymsla = new HBox();
        fxTakkaGeymsla.setAlignment(Pos.CENTER_Le);
    }

    private void buildHomeButton() {
        fxHeimaTakki = new Button();
        fxHeimaTakki.setLayoutX(20);
        fxHeimaTakki.setLayoutY(10);
        fxHeimaTakki.setMnemonicParsing(false);
        fxHeimaTakki.getStyleClass().add("fxheimaTakki");
        // add image to the button


        fxHeimaTakki.setOnAction(actionEvent -> {
            if (!actionEvent.isConsumed()) {
                actionEvent.consume();


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ertu viss?");
                alert.setHeaderText(null);
                alert.setContentText("Vilt þú fara til baka á upphafsskjá og hreinsa þema?");

                ButtonType yesButton = new ButtonType("Já", ButtonBar.ButtonData.OK_DONE);
                ButtonType noButton = new ButtonType("Nei", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(yesButton, noButton);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == yesButton) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/vidmot/upphaf-view.fxml"));
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(UpphafController.selectedStylesheet);
                        stage.setScene(scene);
                        stage.show();
                        isBot = 0;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void buildSoundButton() {
        fxHljodtakkiToggle = new ToggleButton(null);
        fxHljodtakkiToggle.setLayoutX(80);
        fxHljodtakkiToggle.setLayoutY(10);
        fxHeimaTakki.setMnemonicParsing(false);
        fxHljodtakkiToggle.getStyleClass().add("fxTopBarHljod");
        if (mediaPlayer.isMute()){
            fxHljodtakkiToggle.setSelected(true);
        }

        fxHljodtakkiToggle.setOnAction(actionEvent -> {
            if (!actionEvent.isConsumed()) {
                actionEvent.consume();
                {
                    mediaPlayer.setMute(!mediaPlayer.isMute());
                }
            }
        });
    }
}
