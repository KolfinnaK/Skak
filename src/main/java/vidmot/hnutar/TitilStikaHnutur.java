package vidmot.hnutar;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import vidmot.UpphafController;

import java.util.Objects;

public class TitilStikaHnutur extends Pane{
    private AnchorPane root;
    private Pane fxTitleLBarLogo;
    private Label fxTitleLBarText;
    private MenuButton fxTitleLBarMenu;
    private Button fxTitleLBarClose;
    private ToggleButton fxTitleLBarFullScreen;
    private Button fxTitleLBarMinimize;
    private Font font = Font.font("Trebuchet MS", FontWeight.BOLD, 18);

    //Býr til titil stiku fyrir skak senuna
    public TitilStikaHnutur() {
        setMinHeight(30);
        setMaxHeight(30);
        //Búa til root sem verður AnchorPane
        root = new AnchorPane();
        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());
        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());

        //láta forritið búa til takkana
        buildCloseButton();
        buildFullScreenButton();
        buildMinimizeButton();
        buildThemeMenu();

        //setja takkana á root
        getChildren().add(root);
        root.getChildren().add(fxTitleLBarMenu);
        root.getChildren().add(fxTitleLBarClose);
        root.getChildren().add(fxTitleLBarFullScreen);
        root.getChildren().add(fxTitleLBarMinimize);

        //texti hliðin á logo
        fxTitleLBarText = new Label("Skák & Mát");
        fxTitleLBarText.setTextAlignment(TextAlignment.CENTER);
        fxTitleLBarText.setFont(font);
        fxTitleLBarText.setLayoutX(40);
        fxTitleLBarText.setLayoutY(0);
        fxTitleLBarText.textFillProperty().set(Color.web("#ccc9c6"));
        root.getChildren().add(fxTitleLBarText);

        //logo(pedIcon)
        fxTitleLBarLogo = new Pane();
        fxTitleLBarLogo.getStyleClass().add("fxpedIcon");
        fxTitleLBarLogo.setLayoutX(0);
        fxTitleLBarLogo.setLayoutY(0);
        root.getChildren().add(fxTitleLBarLogo);

        //ákveða stærðir
        fxTitleLBarLogo.setPrefSize(30, 30);
        fxTitleLBarText.setPrefSize(93, 30);
        fxTitleLBarClose.setPrefSize(50, 30);
        fxTitleLBarMenu.setPrefSize(93, 30);
        fxTitleLBarFullScreen.setPrefSize(50, 30);
        fxTitleLBarMinimize.setPrefSize(50, 30);

        //setja anchors
        AnchorPane.setRightAnchor(fxTitleLBarClose, 0.0);
        AnchorPane.setRightAnchor(fxTitleLBarFullScreen, 50.0);
        AnchorPane.setRightAnchor(fxTitleLBarMinimize, 100.0);
        AnchorPane.setRightAnchor(fxTitleLBarMenu, 150.0);
    }
    //byggja takka fyrir Title Bar

    //Byggja takkan til að loka glugganum
    private void buildCloseButton() {
    fxTitleLBarClose = new Button();
    fxTitleLBarClose.setLayoutX(650);
    fxTitleLBarClose.setLayoutY(0);
    fxTitleLBarClose.getStyleClass().add("fxcloseButton");

    //setja event handler fyrir takkan
    fxTitleLBarClose.setOnAction(actionEvent -> {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            Stage scene = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene.close();
        }});
    }
    //Byggja takkan til að fullskjáa gluggann
    private void buildFullScreenButton() {
    fxTitleLBarFullScreen = new ToggleButton();
    fxTitleLBarFullScreen.setLayoutX(600);
    fxTitleLBarFullScreen.setLayoutY(0);

    //gá hvort glugginn sé fullskjár og setja takkan á rétta stöðu
    fxTitleLBarFullScreen.getStyleClass().add("fxfullscreenButton");
    if(!Objects.equals(UpphafController.BreiddScenu, "700")){
        fxTitleLBarFullScreen.setSelected(true);
    }
    //setja event handler fyrir takkan
    fxTitleLBarFullScreen.setOnAction(actionEvent -> {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();{
            Stage scene = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            if (scene.getWidth() == 1050 && scene.getHeight() == 780) {
                scene.setWidth(700);
                scene.setHeight(530);
                UpphafController.BreiddScenu = "700";
                fxTitleLBarFullScreen.setSelected(false);
            } else {
                scene.setWidth(1050);
                scene.setHeight(780);
                fxTitleLBarFullScreen.setSelected(true);
                UpphafController.BreiddScenu = "1050";
                    }
                }
            }
        });
    }
     //Byggja takkan til að minnka gluggann
    private void buildMinimizeButton() {
    fxTitleLBarMinimize = new Button();
    fxTitleLBarMinimize.setLayoutX(550);
    fxTitleLBarMinimize.setLayoutY(0);
    fxTitleLBarMinimize.getStyleClass().add("fxminimizeButton");

    //setja event handler fyrir takkan
    fxTitleLBarMinimize.setOnAction(actionEvent -> {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            Stage scene = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene.setIconified(true);
            }
        });
    }
    //Byggja þema Takkan til að halda um valmöguleikana fyrir SkakBordSenaController
    private void buildThemeMenu() {
        fxTitleLBarMenu = new MenuButton();
        fxTitleLBarMenu.setLayoutX(457);
        fxTitleLBarMenu.setLayoutY(0);
        fxTitleLBarMenu.setText("Þema");
        fxTitleLBarMenu.setAlignment(Pos.TOP_RIGHT);
        fxTitleLBarMenu.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 16));
        fxTitleLBarMenu.setTextFill(Color.web("#4d4d4d"));
        fxTitleLBarMenu.getStyleClass().add("fxthemeMenu");

        //byggja þema valmöguleikana fyrir SkakBordSenaController
        MenuItem fxSkyjad = new MenuItem("Skýjað");
        MenuItem fxKlassik = new MenuItem("Klassískt");
        MenuItem fxHitabeltis = new MenuItem("Hitabeltis");
        fxTitleLBarMenu.getItems().addAll(fxSkyjad, fxKlassik, fxHitabeltis);

        //refrence fyiri þema handlerana
        fxKlassik.setOnAction(this::fxKlassikHandler);
        fxSkyjad.setOnAction(this::fxSkyjadHandler);
        fxHitabeltis.setOnAction(this::fxHitabeltisHandler);
    }
    //handlers til að breyta þemu í ChessBoarScene

    //Skýjad handler
    private void fxSkyjadHandler(ActionEvent actionEvent) {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            {
                String newStylesheet = UpphafController.class.getResource("stylesheets/skyjad-still.css").toExternalForm();
                if (!UpphafController.selectedStylesheet.equals(newStylesheet)) {
                    getScene().getStylesheets().remove(UpphafController.selectedStylesheet);
                    getScene().getStylesheets().add(newStylesheet);
                    UpphafController.selectedStylesheet = newStylesheet;
                }
            }
        }
    }
    //Klassískt handler
    private void fxKlassikHandler(ActionEvent actionEvent) {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            {
                String newStylesheet = UpphafController.class.getResource("stylesheets/klassiskt-still.css").toExternalForm();
                if (!UpphafController.selectedStylesheet.equals(newStylesheet)) {
                    getScene().getStylesheets().remove(UpphafController.selectedStylesheet);
                    getScene().getStylesheets().add(newStylesheet);
                    UpphafController.selectedStylesheet = newStylesheet;
                }
            }
        }
    }
    //Hitabeltis handler
    private void fxHitabeltisHandler(ActionEvent actionEvent) {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            {
            String newStylesheet = UpphafController.class.getResource("stylesheets/hitabeltis-still.css").toExternalForm();
                if (!UpphafController.selectedStylesheet.equals(newStylesheet)) {
                    getScene().getStylesheets().remove(UpphafController.selectedStylesheet);
                    getScene().getStylesheets().add(newStylesheet);
                    UpphafController.selectedStylesheet = newStylesheet;
                }
            }
        }
    }
}

