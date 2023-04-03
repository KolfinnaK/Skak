package vidmot.nodes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class TitleLabel extends Pane {
    private StackPane root;
    private Rectangle background;
    private Label title;

    public TitleLabel(String text) {
        root = new StackPane();
        background = new Rectangle();
        title = new Label(text);

        root.minWidthProperty().bind(widthProperty());
        root.maxWidthProperty().bind(widthProperty());
        root.minHeightProperty().bind(heightProperty());
        root.maxHeightProperty().bind(heightProperty());

        getChildren().add(root);
        root.getChildren().addAll(background, title);

        background.widthProperty().bind(root.widthProperty());
        background.heightProperty().bind(root.heightProperty());
        background.setFill(new Color(0.8275, 0.3176, 0.6, 0.5));

        title.setFont(new Font("Impact", 30));
        title.setAlignment(Pos.CENTER);
        title.setWrapText(true);
        title.setTextAlignment(TextAlignment.CENTER);
        title.maxWidthProperty().bind(background.widthProperty().subtract(40));
    }
}
