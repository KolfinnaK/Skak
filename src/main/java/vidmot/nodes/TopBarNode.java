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


public class TopBarNode extends Pane {
    private HBox root;
    private Button fxHeimaTakki;
    private ToggleButton fxHljodtakkiToggle;
    private HBox fxTakkaGeymsla;
    private HBox fxLogoGeymsla;
    private Pane fxTopBarLogo;
    private double xOffset = 0.0, yOffset = 0.0;
    private final MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();


    public TopBarNode() {
        setMinHeight(76);
        setMaxHeight(114);

        root = new HBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(0, 0, 0, 0));
        root.setSpacing(0);
        root.setFillHeight(true);
        root.setPickOnBounds(true);
        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());
        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());

        buildTakkaGeymsla();
        byggjaLogoGeymslu();

        getChildren().add(root);
        HBox.setHgrow(fxTakkaGeymsla, Priority.ALWAYS);
        HBox.setHgrow(fxLogoGeymsla, Priority.ALWAYS);

        root.getChildren().addAll(fxTakkaGeymsla, fxLogoGeymsla);

        fxLogoGeymsla.prefWidthProperty().bind(root.widthProperty().multiply(0.82));
        fxLogoGeymsla.prefHeightProperty().bind(root.heightProperty());

        fxTakkaGeymsla.prefWidthProperty().bind(root.widthProperty().multiply(0.18));
        fxTakkaGeymsla.prefHeightProperty().bind(root.heightProperty());
    }
    //byggja Hbox fyrir takka
    private void buildTakkaGeymsla() {
        buildHomeButton();
        buildSoundButton();
        Region fxTakkaRegion1 = new Region();
        Region fxTakkaRegion2 = new Region();

        //setja upp Hboxið
        fxTakkaGeymsla = new HBox();
        fxTakkaGeymsla.setAlignment(Pos.CENTER_LEFT);
        fxTakkaGeymsla.setFillHeight(true);
        fxTakkaGeymsla.setPickOnBounds(true);
        fxTakkaGeymsla.setSpacing(0);
        fxTakkaGeymsla.setPadding(new Insets(4, 0, 28, 0));
        fxTakkaGeymsla.getChildren().addAll(fxTakkaRegion1, fxHeimaTakki, fxTakkaRegion2, fxHljodtakkiToggle);

        //gera tvö region svo að við getum notað hgrow á takkana
        fxTakkaRegion1.setMinSize(22, 44);
        fxTakkaRegion1.setMaxSize(33, 66);
        fxTakkaRegion2.setMinSize(16, 44);
        fxTakkaRegion2.setMaxSize(24, 66);
        fxTakkaRegion1.setPickOnBounds(true);
        fxTakkaRegion2.setPickOnBounds(true);

        //setja stærðir á takkana
        fxHljodtakkiToggle.setMinSize(44,44);
        fxHljodtakkiToggle.setMaxSize(66,66);
        fxHeimaTakki.setMinSize(44,44);
        fxHeimaTakki.setMaxSize(66,66);
        fxHljodtakkiToggle.setPickOnBounds(true);
        fxHeimaTakki.setPickOnBounds(true);

        //setja hgrow á takkana
        HBox.setHgrow(fxTakkaRegion1, Priority.ALWAYS);
        HBox.setHgrow(fxTakkaRegion2, Priority.ALWAYS);
        HBox.setHgrow(fxHeimaTakki, Priority.ALWAYS);
        HBox.setHgrow(fxHljodtakkiToggle, Priority.ALWAYS);
    }
    //byggja Hbox fyrir logo
    private void byggjaLogoGeymslu() {
        fxTopBarLogo = new Pane();
        Region fxLogoRegion1 = new Region();
        Region fxLogoRegion2 = new Region();

        fxLogoGeymsla = new HBox();
        fxLogoGeymsla.setAlignment(Pos.TOP_CENTER);
        fxLogoGeymsla.setFillHeight(true);
        fxLogoGeymsla.setPickOnBounds(true);
        fxLogoGeymsla.setSpacing(0);
        fxLogoGeymsla.setPadding(new Insets(0, 0, 0, 0));
        fxLogoGeymsla.getChildren().addAll(fxLogoRegion1, fxTopBarLogo, fxLogoRegion2);

        fxLogoRegion1.setMinSize(322, 76);
        fxLogoRegion1.setMaxSize(483, 114);
        fxLogoRegion2.setMinSize(14, 76);
        fxLogoRegion2.setMaxSize(21, 114);
        fxLogoRegion1.setPickOnBounds(true);
        fxLogoRegion2.setPickOnBounds(true);

        fxTopBarLogo.setMinSize(238, 76);
        fxTopBarLogo.setMaxSize(357, 114);
        fxTopBarLogo.setPickOnBounds(true);
        fxTopBarLogo.getStyleClass().add("fxTopBarLogo");

        HBox.setHgrow(fxTopBarLogo, Priority.ALWAYS);
        HBox.setHgrow(fxLogoRegion1, Priority.ALWAYS);
        HBox.setHgrow(fxLogoRegion2, Priority.ALWAYS);
    }

    //byggja heimatakkann
    private void buildHomeButton() {
        fxHeimaTakki = new Button();
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
    //byggja takka fyrir hljóð
    private void buildSoundButton() {
        fxHljodtakkiToggle = new ToggleButton();
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
    //svo hægt er að færa custom gluggann
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
