package vidmot.nodes;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import vidmot.UpphafController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import vidmot.MediaManager;
import vidmot.UpphafController;

import java.io.IOException;
import java.util.Optional;

public class TitleBarNode extends AnchorPane{
    private AnchorPane root;
    private Pane fxTitleLBarLogo;
    private  Label fxTitleLBarText;
    private MenuButton fxTitleLBarMenu;
    private Button fxTitleLBarClose;
    private ToggleButton fxTitleLBarFullScreen;
    private Button fxTitleLBarMinimize;
    private static final int HEIGHT = 30, MAX_WIDTH = 1050, MIN_WIDTH = 700;
    private Font font = Font.font("Trebuchet MS", FontWeight.BOLD, 18);


    public TitleBarNode() {
        root = new AnchorPane();
        root.setPrefSize(700, 30);
        //build buttons
        buildCloseButton();
        buildFullScreenButton();
        buildMinimizeButton();
        buildThemeMenu();

        getChildren().add(root);
        root.getChildren().add(fxTitleLBarMenu);
        root.getChildren().add(fxTitleLBarClose);
        root.getChildren().add(fxTitleLBarFullScreen);
        root.getChildren().add(fxTitleLBarMinimize);
        //close

        //full screen

        //hide


        //Theme menu

        //text
        fxTitleLBarText = new Label("Skák & Mát");
        fxTitleLBarText.setTextAlignment(TextAlignment.CENTER);
        fxTitleLBarText.setFont(font);
        fxTitleLBarText.setLayoutX(40);
        fxTitleLBarText.setLayoutY(0);

        fxTitleLBarText.textFillProperty().set(Color.web("#ccc9c6"));
        root.getChildren().add(fxTitleLBarText);
        //logo
        fxTitleLBarLogo = new Pane();
        fxTitleLBarLogo.getStyleClass().add("fxpedIcon");
        fxTitleLBarLogo.setLayoutX(0);
        fxTitleLBarLogo.setLayoutY(0);
        root.getChildren().add(fxTitleLBarLogo);

        //set sizes
        fxTitleLBarLogo.setPrefSize(30, 30);
        fxTitleLBarText.setPrefSize(93, 30);
        fxTitleLBarClose.setPrefSize(50, 30);
        fxTitleLBarMenu.setPrefSize(93, 30);
        fxTitleLBarFullScreen.setPrefSize(50, 30);
        fxTitleLBarMinimize.setPrefSize(50, 30);

        //set anchors
        AnchorPane.setRightAnchor(fxTitleLBarClose, 0.0);
        AnchorPane.setRightAnchor(fxTitleLBarFullScreen, 50.0);
        AnchorPane.setRightAnchor(fxTitleLBarMinimize, 100.0);
        AnchorPane.setRightAnchor(fxTitleLBarMenu, 150.0);
    }
        //builders
        private void buildCloseButton() {
        fxTitleLBarClose = new Button();
        fxTitleLBarClose.setLayoutX(650);
        fxTitleLBarClose.setLayoutY(0);
        fxTitleLBarClose.getStyleClass().add("fxcloseButton");

        fxTitleLBarClose.setOnAction(actionEvent -> {
            if (!actionEvent.isConsumed()) {
                actionEvent.consume();
        Stage scene = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene.close();
        }
        });
        }

        private void buildFullScreenButton() {
        fxTitleLBarFullScreen = new ToggleButton();
        fxTitleLBarFullScreen.setLayoutX(600);
        fxTitleLBarFullScreen.setLayoutY(0);
        fxTitleLBarFullScreen.getStyleClass().add("fxfullscreenButton");

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

        private void buildMinimizeButton() {
        fxTitleLBarMinimize = new Button();
        fxTitleLBarMinimize.setLayoutX(550);
        fxTitleLBarMinimize.setLayoutY(0);
        fxTitleLBarMinimize.getStyleClass().add("fxminimizeButton");


        fxTitleLBarMinimize.setOnAction(actionEvent -> {
            if (!actionEvent.isConsumed()) {
                actionEvent.consume();
                Stage scene = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene.setIconified(true);
            }
        });
        }
        private void buildThemeMenu() {
        fxTitleLBarMenu = new MenuButton();
        fxTitleLBarMenu.setLayoutX(457);
        fxTitleLBarMenu.setLayoutY(0);
        fxTitleLBarMenu.setText("Þemar");
        fxTitleLBarMenu.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 16));
        fxTitleLBarMenu.setTextFill(Color.web("#4d4d4d"));
        fxTitleLBarMenu.getStyleClass().add("fxthemeMenu");
        }
    }

