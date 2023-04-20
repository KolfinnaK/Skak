package vidmot.nodes;

import javafx.event.ActionEvent;
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

public class TitleBarNode extends AnchorPane{
    private AnchorPane root;
    private Pane fxTitleLBarLogo;
    private  Label fxTitleLBarText;
    private MenuButton fxTitleLBarMenu;
    private Button fxTitleLBarClose;
    private ToggleButton fxTitleLBarFullScreen;
    private Button fxTitleLBarMinimize;
    private Font font = Font.font("Trebuchet MS", FontWeight.BOLD, 18);


    //Býr til titleBar(veit ekki hvað þetta heitir á íslesnku) fyrir forritið
    public TitleBarNode() {
        //Búa til root sem verður AnchorPane
        root = new AnchorPane();
        root.setPrefSize(700, 30);

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
        private void buildCloseButton() {
        //Byggja takkan til að loka glugganum
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
        private void buildFullScreenButton() {
        //Byggja takkan til að fullskjáa gluggann
        fxTitleLBarFullScreen = new ToggleButton();
        fxTitleLBarFullScreen.setLayoutX(600);
        fxTitleLBarFullScreen.setLayoutY(0);
        fxTitleLBarFullScreen.getStyleClass().add("fxfullscreenButton");

        //setja event handler fyrir takkan
        fxTitleLBarFullScreen.setOnAction(actionEvent -> {
            if (!actionEvent.isConsumed()) {
                actionEvent.consume();
                {
                    Stage scene = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    if (scene.getWidth() == 1050 && scene.getHeight() == 780) {
                        scene.setWidth(700);
                        scene.setHeight(530);
                        fxTitleLBarFullScreen.setSelected(false);
                        root.setPrefSize(700, 30);

                    } else {

                        scene.setWidth(1050);
                        scene.setHeight(780);
                        fxTitleLBarFullScreen.setSelected(true);
                        root.setPrefSize(1050, 30);

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
    //Byggja þema Takkan til að halda um valmöguleikana fyrir ChessBoardScene
    private void buildThemeMenu() {
        fxTitleLBarMenu = new MenuButton();
        fxTitleLBarMenu.setLayoutX(457);
        fxTitleLBarMenu.setLayoutY(0);
        fxTitleLBarMenu.setText("Þema");
        fxTitleLBarMenu.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 16));
        fxTitleLBarMenu.setTextFill(Color.web("#4d4d4d"));
        fxTitleLBarMenu.getStyleClass().add("fxthemeMenu");
        //byggja þema valmöguleikana fyrir ChessBoardScene
        MenuItem fxSkyjad = new MenuItem("Skýjað");
        MenuItem fxKlassik = new MenuItem("Klassískt");
        MenuItem fxKandifloss = new MenuItem("Kandífloss");
        MenuItem fxHitabeltis = new MenuItem("Hitabeltis");
        fxTitleLBarMenu.getItems().addAll(fxSkyjad, fxKlassik, fxKandifloss, fxHitabeltis);

        //refrence fyiri þema handlerana
        fxKlassik.setOnAction(this::fxKlassikHandler);
        fxSkyjad.setOnAction(this::fxSkyjadHandler);
        fxKandifloss.setOnAction(this::fxKandiflossHandler);
        fxHitabeltis.setOnAction(this::fxHitabeltisHandler);

    }
    //handlers til að breyta þemu í ChessBoarScene
    //Kandifloss handler
    private void fxKandiflossHandler(ActionEvent actionEvent) {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            {
                String newStylesheet = UpphafController.class.getResource("stylesheets/cottoncandy-styles.css").toExternalForm();
                if (!UpphafController.selectedStylesheet.equals(newStylesheet)) {
                    getScene().getStylesheets().remove(UpphafController.selectedStylesheet);
                    getScene().getStylesheets().add(newStylesheet);
                    UpphafController.selectedStylesheet = newStylesheet;
                }
            }
        }
    }
    //Skýjad handler
    private void fxSkyjadHandler(ActionEvent actionEvent) {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            {
                String newStylesheet = UpphafController.class.getResource("stylesheets/cloud-styles.css").toExternalForm();
                if (!UpphafController.selectedStylesheet.equals(newStylesheet)) {
                    getScene().getStylesheets().remove(UpphafController.selectedStylesheet);
                    getScene().getStylesheets().add(newStylesheet);
                    UpphafController.selectedStylesheet = newStylesheet;
                }
            }
        }
    }
    //Klassík handler
    private void fxKlassikHandler(ActionEvent actionEvent) {
        if (!actionEvent.isConsumed()) {
            actionEvent.consume();
            {
                String newStylesheet = UpphafController.class.getResource("stylesheets/classic-styles.css").toExternalForm();
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
            String newStylesheet = UpphafController.class.getResource("stylesheets/tropical-styles.css").toExternalForm();
                if (!UpphafController.selectedStylesheet.equals(newStylesheet)) {
                    getScene().getStylesheets().remove(UpphafController.selectedStylesheet);
                    getScene().getStylesheets().add(newStylesheet);
                    UpphafController.selectedStylesheet = newStylesheet;
                }
            }
        }
    }
}

