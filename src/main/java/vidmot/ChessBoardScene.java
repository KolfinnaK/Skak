package vidmot;

import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import presenter.GameMediator;
import vidmot.nodes.GameNode;
import vidmot.nodes.TitleBarNode;
import vidmot.nodes.TopBarNode;

public class ChessBoardScene extends Scene {
    private TopBarNode topBar;
    private TitleBarNode titleBar;
    private VBox root;
    private Pane game;
    private double xOffset = 0.0, yOffset = 0.0;


    public ChessBoardScene(GameMediator gameMediator) {
        super(new VBox());
        initializeComponents();
        constructSceneGraph(gameMediator);
        buildComponents();
    }

    private void initializeComponents(){
        root = (VBox) getRoot();
        root.getStylesheets().add(UpphafController.class.getResource("stylesheets/cloud-styles.css").toExternalForm());
        titleBar = new TitleBarNode();
        VBox.setVgrow(titleBar, Priority.NEVER);
        topBar = new TopBarNode();
        VBox.setVgrow(topBar, Priority.ALWAYS);
        topBar.getStyleClass().add("fxTopBar");
        titleBar.getStyleClass().add("fxtitleBar");
        addDraggableNode(root);
    }

    private void constructSceneGraph(GameMediator gameMediator) {
        game = new GameNode(gameMediator);
        root.getChildren().addAll(titleBar, topBar, game);

    }

    private void buildComponents() {
        buildBoardComponent();
    }

    private void buildBoardComponent() {
        VBox parent = (VBox) game.getParent();
        VBox.setVgrow(game, Priority.ALWAYS);
        game.minWidthProperty().bind(parent.widthProperty());
        game.minWidthProperty().bind(parent.widthProperty());
        game.setMinHeight(424);
        game.setMaxHeight(636);
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
