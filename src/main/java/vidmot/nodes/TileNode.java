package vidmot.nodes;

import vinnsla.util.Pieces;
import presenter.BoardPresenter;
import presenter.TilePresenter;
import vidmot.Observer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class TileNode extends StackPane implements Observer {
    private int row, col;
    private String style;
    private ImageView imageView;
    private TilePresenter tilePresenter;
    private BoardPresenter gamePresenter;

    public TileNode(int row, int col, TilePresenter spacePresenter, BoardPresenter gamePresenter) {
        setUpState(row, col, spacePresenter, gamePresenter);
        addImageView();
        addRank();
        addFile();
        setUpEventHandlers();
        update();
        spacePresenter.attach(this);
    }

    private void setUpState(int row, int col, TilePresenter spacePresenter, BoardPresenter gamePresenter) {
        this.row = row;
        this.col = col;
        this.gamePresenter = gamePresenter;
        this.tilePresenter = spacePresenter;
    }

    private void addImageView() {
        imageView = new ImageView();
        imageView.fitHeightProperty().bind(minHeightProperty());
        imageView.fitWidthProperty().bind(minWidthProperty());
        getChildren().add(imageView);
    }

    private void addRank() {
        if (row != 7)
            return;

        Label rank = new Label(getRank(col));
        rank.setFont(new Font(9));
        rank.setPadding(new Insets(0, 2, 2, 0));
        StackPane.setAlignment(rank, Pos.BOTTOM_RIGHT);
        getChildren().add(rank);
    }

    private String getRank(int row) {
        return String.valueOf((char) ('a' + row));
    }

    private void addFile() {
        if (col != 0)
            return;

        Label file = new Label(getFile(row));
        file.setFont(new Font(9));
        file.setPadding(new Insets(2, 0, 0, 2));
        StackPane.setAlignment(file, Pos.TOP_LEFT);
        getChildren().add(file);
    }

    private String getFile(int col) {
        return String.valueOf(8 - col);
    }

    private void setUpEventHandlers() {
        setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gamePresenter.click(row, col);
            }
        });

        setOnMouseEntered(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                gamePresenter.hoverInTo(row, col);
            }
        });

        setOnMouseExited(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseEvent.consume(); gamePresenter.hoverOutOf(row, col);
            }
        });
    }

    public Image getImage(Pieces piece) {
        String path = "file:./src/main/java/vidmot/resources/";
        if (piece == Pieces.BLACK_KING)
            path += "black_king.png";
        else if (piece == Pieces.BLACK_QUEEN)
            path += "black_queen.png";
        else if (piece == Pieces.BLACK_ROOK)
            path += "black_rook.png";
        else if (piece == Pieces.BLACK_BISHOP)
            path += "black_bishop.png";
        else if (piece == Pieces.BLACK_KNIGHT)
            path += "black_knight.png";
        else if (piece == Pieces.BLACK_PAWN)
            path += "black_pawn.png";
        else if (piece == Pieces.WHITE_KING)
            path += "white_king.png";
        else if (piece == Pieces.WHITE_QUEEN)
            path += "white_queen.png";
        else if (piece == Pieces.WHITE_ROOK)
            path += "white_rook.png";
        else if (piece == Pieces.WHITE_BISHOP)
            path += "white_bishop.png";
        else if (piece == Pieces.WHITE_KNIGHT)
            path += "white_knight.png";
        else if (piece == Pieces.WHITE_PAWN)
            path += "white_pawn.png";
        else
            return null;

        try {
            return new Image(path);
        } catch (Exception e) {
            throw new RuntimeException("No image found for file path: " + path);
        }
    }

    /*
     *  Updates the style and image of the node based on the state of the ChessBoardTilePresenter.
     *
     *  This is called whenever the state of the ChessBoardTilePresenter is changed.
     */
    @Override
    public void update() {
        style = tilePresenter.getStyle();
        getChildren().remove(imageView);
        addImageView();
        imageView.setImage(getImage(tilePresenter.getPiece()));
        getStyleClass().clear();
        getStyleClass().add(style);
    }
}
