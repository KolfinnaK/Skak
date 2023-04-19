package vidmot.nodes;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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

    private HBox fxTakkaGeymsla;

    private Pane fxTitleLBarLogo;

    private double xOffset = 0.0, yOffset = 0.0;

    private final MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();


    public TopBarNode() {
        root = new HBox();
        root.setMinSize(700, 76);
        root.setMaxSize(1050, 114);
        root.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setPadding(new Insets(2, 20, 20, 16));
        //build buttons
        //Home button
        buildTakkaGeymsla();

        getChildren().add(root);
        //heimatakki
        //hljodtakki
        HBox.setHgrow(fxTakkaGeymsla, Priority.ALWAYS);
        //Title
        fxTitleLBarLogo = new Pane();
        fxTitleLBarLogo.setMinSize(238, 54);
        fxTitleLBarLogo.setMaxSize(357, 81);
        fxTitleLBarLogo.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        fxTitleLBarLogo.getStyleClass().add("fxTopBarLogo");
        fxTitleLBarLogo.autosize();

        HBox.setHgrow(fxTitleLBarLogo, Priority.ALWAYS);
        root.getChildren().addAll(fxTakkaGeymsla, fxTitleLBarLogo);

        /*
        AnchorPane.setRightAnchor(fxTitleLBarLogo, 32.0);
        AnchorPane.setBottomAnchor(fxTitleLBarLogo, 15.0);
        AnchorPane.setTopAnchor(fxTitleLBarLogo, 16.0);
         */
    }
    private void buildTakkaGeymsla() {
        buildHomeButton();
        buildSoundButton();

        fxTakkaGeymsla = new HBox();
        fxTakkaGeymsla.setAlignment(Pos.CENTER_LEFT);
        fxTakkaGeymsla.setSpacing(15);
        fxTakkaGeymsla.setMinSize(426, 54);
        fxTakkaGeymsla.setMaxSize(639, 81);
        fxTakkaGeymsla.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);


        fxTakkaGeymsla.getChildren().add(fxHeimaTakki);
        fxTakkaGeymsla.getChildren().add(fxHljodtakkiToggle);
        fxHljodtakkiToggle.setMinSize(45,45);
        fxHljodtakkiToggle.setMaxSize(45,45);

        fxHeimaTakki.setMinSize(45,45);
        fxHeimaTakki.setMaxSize(45,45);

    }

    private void buildHomeButton() {
        fxHeimaTakki = new Button();
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
                        addDraggableNode(root);
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
    private void addDraggableNode(final Node node) {

        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                    xOffset = me.getSceneX();
                    yOffset = me.getSceneY();
                }
            }
        });

        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (me.getButton() != MouseButton.MIDDLE) {
                    node.getScene().getWindow().setX(me.getScreenX() - xOffset);
                    node.getScene().getWindow().setY(me.getScreenY() - yOffset);
                }
            }
        });
    }

}
