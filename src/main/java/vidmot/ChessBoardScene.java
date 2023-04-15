package vidmot;

import javafx.application.Application;
import javafx.stage.Stage;


import presenter.GameMediator;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import vidmot.nodes.GameNode;
import vidmot.nodes.TopBarNode;

public class ChessBoardScene extends Scene {
    private VBox root;
    private TopBarNode topBar;
    private Pane game;

    public ChessBoardScene(double width, double height, GameMediator gameMediator) {
        super(new VBox(), width, height);
        initializeComponents();
        constructSceneGraph(gameMediator);
        buildComponents();
    }

    private void initializeComponents() {
        root = (VBox) getRoot();
        topBar = new TopBarNode();
        topBar.minWidthProperty().bind(widthProperty());
        topBar.maxWidthProperty().bind(widthProperty());
        topBar.setMaxHeight(40);
        topBar.setMinHeight(40);
        if(UpphafController.thema == 2){
        root.setStyle("-fx-background-color: #facff5;");}
        else if(UpphafController.thema == 1){
            root.setStyle("-fx-background-color: #d6d6d6;");}
        else if(UpphafController.thema == 3){
            root.setStyle("-fx-background-color: #a6e2f7");}
    }

    private void constructSceneGraph(GameMediator gameMediator) {
        game = new GameNode(gameMediator);
        root.getChildren().addAll(topBar, game);
    }

    private void buildComponents() {
        buildBoardComponent();
    }

    private void buildBoardComponent() {
        VBox parent = (VBox) game.getParent();
        VBox.setVgrow(game, Priority.ALWAYS);
        game.minWidthProperty().bind(parent.widthProperty());
        game.maxWidthProperty().bind(parent.widthProperty());
    }
}
