package vidmot;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import presenter.GameMediator;
import vidmot.nodes.GameNode;
import vidmot.nodes.TopBarNode;

public class ChessBoardScene extends AnchorPane {
    private TopBarNode topBar;
    @FXML
    private AnchorPane root;
    private Pane game;


    public ChessBoardScene(GameMediator gameMediator) {
        super(new AnchorPane());
        initializeComponents();
        constructSceneGraph(gameMediator);
        buildComponents();
    }

    private void initializeComponents(){
        root = new AnchorPane();

    }

    private void constructSceneGraph(GameMediator gameMediator) {
        game = new GameNode(gameMediator);
        root.getChildren().add(game);
        AnchorPane.setTopAnchor(game, 0.0);
        AnchorPane.setBottomAnchor(game, 0.0);
        AnchorPane.setLeftAnchor(game, 0.0);
        AnchorPane.setRightAnchor(game, 0.0);
    }

    private void buildComponents() {
        buildBoardComponent();
    }

    private void buildBoardComponent() {
        AnchorPane parent = (AnchorPane) game.getParent();
        game.minWidthProperty().bind(parent.widthProperty());
        game.maxWidthProperty().bind(parent.widthProperty());
    }
}
