package vidmot.nodes;

//import com.chess.view.scenes.HomeScene;
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
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import vidmot.TimaController;
import vidmot.View;
import vidmot.ViewSwitcher;

import java.io.IOException;

import java.util.Optional;


public class TopBarNode extends Pane {
    private HBox root;
    private Button homeButton;
    private static final int TOP_BAR_HEIGHT = 40;
    private static final String TOP_BAR_STYLE = "-fx-background-color: #0f4519;", //dökkgrænn
            HIGHLIGHTED_TOP_BAR_BUTTON_STYLE = "-fx-background-color: #d6d6d6;",
            HOME_ICON_FILE_PATH = "file:./src/main/resources/vidmot/images/home_icon.png",
                    TITLE_COLOR = "-fx-text-fill: #d6d6d6;";
    private static final Insets TOP_BAR_PADDING = new Insets(5, 5, 5, 5);
    private TimaController timaController = (TimaController) ViewSwitcher.lookup(View.TIMAMORK);

    public TopBarNode() {
        setMinHeight(TOP_BAR_HEIGHT);
        setMaxHeight(TOP_BAR_HEIGHT);

        root = new HBox();

        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());
        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());
        root.setStyle(TOP_BAR_STYLE);
        root.setPadding(TOP_BAR_PADDING);
        root.setAlignment(Pos.CENTER_LEFT);
        buildHomeButton();
        getChildren().add(root);
        root.getChildren().add(homeButton);
        Label title = new Label("Skák");
        title.setFont(new Font("Impact", 20));
        title.setStyle(TITLE_COLOR);
        title.setAlignment(Pos.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);
        title.minWidthProperty().bind(root.widthProperty().subtract(homeButton.widthProperty().multiply(2)));
        title.maxWidthProperty().bind(root.widthProperty().subtract(homeButton.widthProperty().multiply(2)));
        HBox.setHgrow(title, Priority.ALWAYS);
        root.getChildren().add(title);
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
        imageView.minWidth(homeButton.getWidth());
        imageView.maxWidth(homeButton.getWidth());
        imageView.minHeight(homeButton.getHeight());
        imageView.maxHeight(homeButton.getHeight());
        homeButton.setGraphic(imageView);

        homeButton.setOnMouseClicked(mouseEvent -> {

            //ViewSwitcher.switchTo(View.UPPHAFSSENA);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

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
                    homeButton.getScene().getStylesheets().clear();
                    ViewSwitcher.switchTo(View.UPPHAFSSENA);
                    timaController.setBot("");
                }
            }
            //Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            //stage.setScene(new HomeScene(stage.getScene().getWidth(), stage.getScene().getHeight()));
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/upphaf-view.fxml"));
            try {
                Parent root = loader.load();
                Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        });
        homeButton.setOnMouseEntered(mouseEvent -> homeButton.setStyle(HIGHLIGHTED_TOP_BAR_BUTTON_STYLE));
        homeButton.setOnMouseExited(mouseEvent -> homeButton.setStyle(TOP_BAR_STYLE));
    }
}
