package vidmot.nodes;

import presenter.NotationPresenter;
import vidmot.Observer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NotationNode extends ScrollPane implements Observer {
    private int notationCount;
    private NotationPresenter notationPresenter;
    private VBox root;

    public NotationNode(NotationPresenter notationPresenter) {
        notationCount = 0;
        this.notationPresenter = notationPresenter;
        notationPresenter.attach(this);
        root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        getChildren().add(root);
        setContent(root);
    }

    @Override
    public void update() {
        if (notationPresenter.getSizeOfMovementNotations() < notationCount)
            throw new RuntimeException("Notation count is off.");

        if (notationPresenter.isGameOver()) {
            if (root.getChildren().size() != 0 && ((HBox) root.getChildren().get(root.getChildren().size() - 1)).getChildren().size() == 2) {
                HBox row = new HBox();
                row.getChildren().add(new Label("1-0"));
                root.getChildren().add(row);
            } else if (root.getChildren().size() == 0 || ((HBox) root.getChildren().get(root.getChildren().size() - 1)).getChildren().size() == 3) {
                HBox row = new HBox();
                row.getChildren().add(new Label("0-1"));
                root.getChildren().add(row);
            }
            return;
        }

        if (root.getChildren().size() == 0 || ((HBox) root.getChildren().get(root.getChildren().size() - 1)).getChildren().size() == 3) {
            HBox row = new HBox();
            Label numberLabel = new Label(root.getChildren().size() + 1 + ". ");
            numberLabel.setMinWidth(30);
            numberLabel.setMaxWidth(30);
            row.getChildren().add(numberLabel);
            Label label = new Label(notationPresenter.getLastMovement());
            label.setMinWidth(50);
            label.setMaxWidth(50);
            row.getChildren().add(label);
            root.getChildren().add(row);
        } else {
            ((HBox) root.getChildren().get(root.getChildren().size() - 1)).getChildren().add(new Label(notationPresenter.getLastMovement()));
        }

        notationCount++;
    }
}
