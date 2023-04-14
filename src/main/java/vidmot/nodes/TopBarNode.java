package vidmot.nodes;

//import com.chess.view.scenes.HomeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.*;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import vidmot.MediaManager;

import java.io.IOException;

import java.util.Optional;

import static vidmot.UpphafController.isBot;


public class TopBarNode extends Pane {
    private final HBox root;
    private Button homeButton;
    private Button soundButton;
    private MenuBar themeMenu;
    private final MediaPlayer mediaPlayer = MediaManager.getMediaPlayer();
    private String selectedStylesheet = "";
    private static final int TOP_BAR_HEIGHT = 40;
    private static final int TOP_BAR_WIDTH = 618;
    private static final String TOP_BAR_STYLE = "-fx-background-color: #0f4519;", //dökkgrænn
            HIGHLIGHTED_TOP_BAR_BUTTON_STYLE = "-fx-background-color: #d6d6d6;",
            HOME_ICON_FILE_PATH = "file:./src/main/resources/vidmot/images/home_icon.png",
            PLAY_FILE_PATH = "file:./src/main/resources/vidmot/images/play.png",
            MUTE_FILE_PATH = "file:./src/main/resources/vidmot/images/mute.png",
                    TITLE_COLOR = "-fx-text-fill: #d6d6d6;";
    private static final Insets TOP_BAR_PADDING = new Insets(5, 5, 5, 5);



    public TopBarNode() {
        setMinHeight(TOP_BAR_HEIGHT);
        setMaxHeight(TOP_BAR_HEIGHT);
        setMinWidth(TOP_BAR_WIDTH);
        setMaxWidth(TOP_BAR_WIDTH);

        root = new HBox();

        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());
        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());
        root.setStyle(TOP_BAR_STYLE);
        root.setPadding(TOP_BAR_PADDING);
        root.setAlignment(Pos.CENTER_LEFT);
        buildHomeButton();
        buildSoundButton();
        getChildren().add(root);
        root.getChildren().add(homeButton);
        root.getChildren().add(soundButton);
        //Label title = new Label("Skák");
        //title.setFont(new Font("Impact", 20));
        //title.setStyle(TITLE_COLOR);
        //title.setAlignment(Pos.TOP_CENTER);
        //title.setTextAlignment(TextAlignment.CENTER);
        //HBox.setMargin(title, new Insets(0, 0, 0, -100));
        //title.minWidthProperty().bind(root.widthProperty().subtract(homeButton.widthProperty()));
        //title.maxWidthProperty().bind(root.widthProperty().subtract(homeButton.widthProperty()));
        //HBox.setHgrow(title, Priority.ALWAYS);
        //root.getChildren().add(title);

        //buildThemeMenu();
        //root.getChildren().add(themeMenu);
    }

    private void buildThemeMenu() {
        themeMenu = new MenuBar();
        themeMenu.setLayoutX(80.0);
        themeMenu.setLayoutY(13.0);
        themeMenu.setMinHeight(0.0);
        themeMenu.setMinWidth(0.0);
        themeMenu.setPickOnBounds(false);
        themeMenu.setPrefHeight(28.0);
        themeMenu.setPrefWidth(43.0);
        themeMenu.getStylesheets().add("/vidmot/stylesheets/upphaf-styles.css");

        Menu fxTheme = new Menu();
        fxTheme.setText("Þema");

        MenuItem fxClassicItem = new MenuItem();
        fxClassicItem.setText("Klassískt");
        fxClassicItem.setOnAction(event -> fxClassicHandler(event));

        MenuItem fxCottonCandyItem = new MenuItem();
        fxCottonCandyItem.setText("Cotton candy");
        fxCottonCandyItem.setOnAction(event -> fxCottonCandyHandler(event));

        MenuItem fxTropicalItem = new MenuItem();
        fxTropicalItem.setText("Tropical");
        fxTropicalItem.setOnAction(event -> fxTropicalHandler(event));

        fxTheme.getItems().addAll(fxClassicItem, fxCottonCandyItem, fxTropicalItem);

        themeMenu.getMenus().add(fxTheme);

        themeMenu.setPadding(new Insets(-5.0, -5.0, 0.0, 0.0));

    }

    public void fxClassicHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/classic-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            homeButton.getScene().getStylesheets().remove(selectedStylesheet);
            homeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    public void fxCottonCandyHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/cottoncandy-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            homeButton.getScene().getStylesheets().remove(selectedStylesheet);
            homeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    public void fxTropicalHandler(ActionEvent actionEvent) {
        String newStylesheet = getClass().getResource("stylesheets/tropical-styles.css").toExternalForm();
        if (!selectedStylesheet.equals(newStylesheet)) {
            homeButton.getScene().getStylesheets().remove(selectedStylesheet);
            homeButton.getScene().getStylesheets().add(newStylesheet);
            selectedStylesheet = newStylesheet;
        }
    }

    private void buildHomeButton() {
        homeButton = new Button();
        homeButton.minHeightProperty().bind(root.heightProperty().subtract(root.getPadding().getBottom() + root.getPadding().getTop()));
        homeButton.maxHeightProperty().bind(homeButton.minHeightProperty());
        homeButton.minWidthProperty().bind(homeButton.minHeightProperty());
        homeButton.maxWidthProperty().bind(homeButton.minHeightProperty());
        homeButton.setStyle(root.getStyle());

        // add image to the button
        ImageView imageView = new ImageView(new Image(HOME_ICON_FILE_PATH, 20, 20, false, false));
        imageView.setFitWidth(30);
        imageView.setFitHeight(25);
        homeButton.setGraphic(imageView);


        homeButton.setOnMouseClicked(mouseEvent -> {

            Stage stage = (Stage) homeButton.getScene().getWindow();

            if (!mouseEvent.isConsumed()) {
                mouseEvent.consume();

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
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vidmot/upphaf-view.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
                        stage.setScene(scene);
                        isBot = 0;
                    } catch (IOException e) {
                        e.printStackTrace();

                }
            }

        }});
            homeButton.setOnMouseEntered(mouseEvent -> homeButton.setStyle(HIGHLIGHTED_TOP_BAR_BUTTON_STYLE));
            homeButton.setOnMouseExited(mouseEvent -> homeButton.setStyle(TOP_BAR_STYLE));
    }

    private void buildSoundButton() {
        soundButton = new Button();
        soundButton.minHeightProperty().bind(root.heightProperty().subtract(root.getPadding().getBottom() + root.getPadding().getTop()));
        soundButton.maxHeightProperty().bind(soundButton.minHeightProperty());
        soundButton.minWidthProperty().bind(soundButton.minHeightProperty());
        soundButton.maxWidthProperty().bind(soundButton.minHeightProperty());
        soundButton.setStyle(root.getStyle());

        // add image to the button
        ImageView imageView = new ImageView(new Image(PLAY_FILE_PATH, 20, 20, false, false));
        imageView.setFitWidth(25);
        imageView.setFitHeight(30);

        soundButton.setGraphic(imageView);

        // add event handler to play sound when button is clicked
        soundButton.setOnMouseClicked(mouseEvent -> {
            mediaPlayer.setMute(!mediaPlayer.isMute());

            ImageView image;
            if (mediaPlayer.isMute()) {
                image = new ImageView(MUTE_FILE_PATH);
                image.setFitHeight(30);
                image.setFitWidth(25);
                image.setPreserveRatio(true);
            } else {
                image = new ImageView(PLAY_FILE_PATH);
                image.setFitHeight(30);
                image.setFitWidth(25);
                image.setPreserveRatio(true);
            }
            soundButton.setGraphic(image);
        });

        soundButton.setOnMouseEntered(mouseEvent -> soundButton.setStyle(HIGHLIGHTED_TOP_BAR_BUTTON_STYLE));
        soundButton.setOnMouseExited(mouseEvent -> soundButton.setStyle(TOP_BAR_STYLE));
    }
}
