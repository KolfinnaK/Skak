package vidmot.nodes;

//import com.chess.view.scenes.HomeScene;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;


import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import vidmot.MediaManager;
import vidmot.UpphafController;

import java.io.IOException;
import java.util.Optional;

import static vidmot.UpphafController.isBot;


public class TopBarNode extends AnchorPane {

    private AnchorPane root;
    private Button fxHeimaTakki;

    private ToggleButton fxHljodtakkiToggle;

    private Font font = Font.font("Trebuchet MS", FontWeight.BOLD, 35);

    private final MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();

    private static final Insets TOP_BAR_PADDING = new Insets(10, 20, 10, 40);
    private static final int MAX_HEIGHT = 780, MAX_WIDTH = 1050, MIN_HEIGHT = 530, MIN_WIDTH = 700;




    public TopBarNode() {
        setMinHeight(76);
        setMaxHeight(76);

        root = new AnchorPane();
        root.getStylesheets().add(UpphafController.class.getResource("stylesheets/cloud-styles.css").toExternalForm());
        root.setMinWidth(700);
        root.setMaxHeight(76);
        root.setMinHeight(76);
        root.setMaxWidth(700);
        root.setPadding(TOP_BAR_PADDING);
        root.getStyleClass().add("fxTopBar");

        //build buttons
        buildHomeButton();
        buildSoundButton();
        //Home button
        getChildren().add(root);
        root.getChildren().add(fxHeimaTakki);
        //heimatakki
        fxHeimaTakki.minHeightProperty().bind(root.heightProperty().subtract(root.getPadding().getBottom() + root.getPadding().getTop()));
        fxHeimaTakki.maxHeightProperty().bind(fxHeimaTakki.minHeightProperty());
        fxHeimaTakki.minWidthProperty().bind(fxHeimaTakki.minHeightProperty());
        fxHeimaTakki.maxWidthProperty().bind(fxHeimaTakki.minHeightProperty());
        fxHeimaTakki.setLayoutX(20);
        fxHeimaTakki.setLayoutY(10);
        fxHeimaTakki.setMnemonicParsing(false);
        fxHeimaTakki.getStyleClass().add("fxheimaTakki");
        //hljodtakki
        root.getChildren().add(fxHljodtakkiToggle);

        //Title
        Label title = new Label("Skák & Mát");
        title.setFont(font);

        title.setTextAlignment(TextAlignment.CENTER);
        title.minWidthProperty().bind(root.widthProperty().subtract(fxHeimaTakki.widthProperty().multiply(2)));
        title.maxWidthProperty().bind(root.widthProperty().subtract(fxHeimaTakki.widthProperty().multiply(2)));
        title.setLayoutX(460);
        title.setLayoutY(15);
        fxHljodtakkiToggle.setPrefSize(45,45);

        fxHeimaTakki.setPrefSize(45,45);

        root.getChildren().add(title);
    }

    private void buildHomeButton() {
        fxHeimaTakki = new Button();
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
        fxHljodtakkiToggle.minHeightProperty().bind(root.heightProperty().subtract(root.getPadding().getBottom() + root.getPadding().getTop()));
        fxHljodtakkiToggle.maxHeightProperty().bind(fxHljodtakkiToggle.minHeightProperty());
        fxHljodtakkiToggle.minWidthProperty().bind(fxHljodtakkiToggle.minHeightProperty());
        fxHljodtakkiToggle.maxWidthProperty().bind(fxHljodtakkiToggle.minHeightProperty());
        fxHljodtakkiToggle.setLayoutX(80);
        fxHljodtakkiToggle.setLayoutY(10);
        fxHeimaTakki.setMnemonicParsing(false);

        fxHljodtakkiToggle.getStyleClass().add("fxTopBarHljod");



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
