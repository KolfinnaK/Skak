package vidmot.hnutar;

import presenter.GameMediator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameNode extends Pane {
    private static final double TIME_BOX_SPACE_PERCENTAGE = 0.4;
    private VBox infoBox;
    private HBox root;
    private BoardNode board;
    private TimaHnutur whiteTimer, blackTimer;
    private NotationNode notationNode;
    private GameMediator gameMediator;

    public GameNode(GameMediator gameMediator) {
        this.gameMediator = gameMediator;
        board = new BoardNode(gameMediator.getBoardPresenter());
        whiteTimer = new TimaHnutur(gameMediator.getWhiteTimerPresenter());
        blackTimer = new TimaHnutur(gameMediator.getBlackTimerPresenter());
        notationNode = new NotationNode(gameMediator.getGameNotationPresenter());
        blackTimer.getStyleClass().add("fxTimerBackground");
        whiteTimer.getStyleClass().add("fxTimerBackground");
        build();
    }


    public void build() {
        root = new HBox();
        getChildren().add(root);
        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());
        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());
        root.getStyleClass().add("fxSkakSenaBakkgrunnur");

        // add a pane for the board to live in, add a VBox for the timers
        board.minWidthProperty().bind(widthProperty().multiply(1 - TIME_BOX_SPACE_PERCENTAGE));
        board.maxWidthProperty().bind(widthProperty().multiply(1 - TIME_BOX_SPACE_PERCENTAGE));
        root.getChildren().add(board);

        infoBox = new VBox();
        root.getChildren().add(infoBox);
        infoBox.getStyleClass().add("fxInfoBoxBakgrunnur");
        PlayerInfoNode blackPlayerInfoNode = new PlayerInfoNode(blackTimer, "Svartur");
        blackPlayerInfoNode.minWidthProperty().bind(infoBox.widthProperty().multiply(0.975));
        blackPlayerInfoNode.maxWidthProperty().bind(infoBox.widthProperty().multiply(0.975));
        blackPlayerInfoNode.minHeightProperty().bind(infoBox.heightProperty().divide(8));
        blackPlayerInfoNode.maxHeightProperty().bind(infoBox.heightProperty().divide(8));
        infoBox.getChildren().add(blackPlayerInfoNode);
        infoBox.getChildren().add(notationNode);
        notationNode.minWidthProperty().bind(infoBox.widthProperty().multiply(4.0 / 5));
        notationNode.maxWidthProperty().bind(infoBox.widthProperty().multiply(4.0 / 5));
        PlayerInfoNode whitePlayerInfoNode = new PlayerInfoNode(whiteTimer, "Hvítur");
        whitePlayerInfoNode.minWidthProperty().bind(infoBox.widthProperty().multiply(1.012));
        whitePlayerInfoNode.maxWidthProperty().bind(infoBox.widthProperty().multiply(1.012));
        whitePlayerInfoNode.minHeightProperty().bind(infoBox.heightProperty().divide(8));
        whitePlayerInfoNode.maxHeightProperty().bind(infoBox.heightProperty().divide(8));
        infoBox.getChildren().add(whitePlayerInfoNode);
        infoBox.minHeightProperty().bind(root.heightProperty());
        infoBox.maxHeightProperty().bind(root.heightProperty());
        infoBox.setAlignment(Pos.CENTER_RIGHT);
        infoBox.paddingProperty().setValue(new Insets(50, 20, 50, 20));
        infoBox.minWidthProperty().bind(widthProperty().multiply(TIME_BOX_SPACE_PERCENTAGE));
        infoBox.maxWidthProperty().bind(widthProperty().multiply(TIME_BOX_SPACE_PERCENTAGE));

        VBox.setVgrow(notationNode, Priority.ALWAYS);
        HBox.setHgrow(board, Priority.ALWAYS);
    }
}

