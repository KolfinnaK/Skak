package vidmot;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import presenter.GameMediator;
import vidmot.hnutar.GameNode;
import vidmot.hnutar.TitilStikaHnutur;
import vidmot.hnutar.ToppBordaHnutur;

/**
 *  SkakBordSenaController er controller klasi sem stýrir UI hlutum leikborðsins.
 */

public class SkakBordSenaController extends Scene {
    private ToppBordaHnutur topBar;
    private TitilStikaHnutur titleBar;
    private VBox root;
    private Pane game;
    private double xOffset = 0.0, yOffset = 0.0;
    private static final int MAX_HEIGHT = 780, MAX_WIDTH = 1050, MIN_HEIGHT = 530, MIN_WIDTH = 700;

    /**
     Smiður fyrir nýjan SkakBordSenaController hlut með tilgreindum GameMediator hlut.
     @param gameMediator a GameMediator hlutur sem að stjórnar leikjafræðinni.
     */

    public SkakBordSenaController(GameMediator gameMediator) {
        super(new VBox());
        initializeComponents();
        constructSceneGraph(gameMediator);
        buildComponents();
    }

    /**
     Upphafstillir UI hlutum fyrir leikborðið.
     */
    private void initializeComponents(){
        root = (VBox) getRoot();
        titleBar = new TitilStikaHnutur();
        topBar = new ToppBordaHnutur();

        root.setFillWidth(true);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.setFillWidth(true);
        root.setMinWidth(MIN_WIDTH);
        root.setMinHeight(MIN_HEIGHT);
        root.setMaxHeight(MAX_HEIGHT);
        root.setMaxWidth(MAX_WIDTH);

        titleBar = new TitilStikaHnutur();
        topBar.minWidthProperty().bind(widthProperty());
        topBar.maxWidthProperty().bind(widthProperty());
        topBar.setMinHeight(30);
        topBar.setMaxHeight(30);
        titleBar.getStyleClass().add("fxtitleBar");

        topBar = new ToppBordaHnutur();
        topBar.minWidthProperty().bind(widthProperty());
        topBar.maxWidthProperty().bind(widthProperty());
        topBar.setMinHeight(76);
        topBar.setMaxHeight(114);
        topBar.getStyleClass().add("fxTopBar");
        VBox.setVgrow(topBar, Priority.ALWAYS);

        dragaSkjaHandler(titleBar);

    }
    /**

     Smiður fyrir SceneGraph fyrir leikborðið
     @param gameMediator GameMediator hlutur sem stýrir leikjafræðinni.
     */
    private void constructSceneGraph(GameMediator gameMediator) {
        game = new GameNode(gameMediator);
        root.getChildren().addAll(titleBar, topBar, game);

    }
    /**
    Býr til UI hlutina fyrir leikborðið.
     */
    private void buildComponents() {
        buildBoardComponent();
    }

    /**
     Býr til UI leikborðs hlutinn.
     */
    private void buildBoardComponent() {
        VBox parent = (VBox) game.getParent();
        VBox.setVgrow(game, Priority.ALWAYS);
        game.setMinWidth(700);
        game.setMaxWidth(1050);
        game.setMinHeight(424);
        game.setMaxHeight(636);
    }

    /**

     Setur upp handler til að draga tilgreindan hnút (e. node).
     @param node hnúturinn sem handlerinn er settur á.
     */
    private void dragaSkjaHandler(final Node node) {
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
