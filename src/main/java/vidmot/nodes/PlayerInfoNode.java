package vidmot.nodes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PlayerInfoNode extends Pane {
    private HBox root;
    private TimerNode timer;
    private Label playerName;

    public PlayerInfoNode(TimerNode timer, String playerName) {
        this.timer = timer;
        this.playerName = new Label(playerName);
        this.playerName.setPadding(new Insets(0, 10, 0, 10));
        this.playerName.setFont(new Font("Impact", 20));
        root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());
        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());
        root.getChildren().addAll(timer, this.playerName);
        getChildren().add(root);
    }
}
