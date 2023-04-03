package presenter;

import vinnsla.util.Colors;

public class GameMediator implements Mediator {
    private BoardPresenter boardPresenter;
    private TimerPresenter currentTimerPresenter, whiteTimerPresenter, blackTimerPresenter;
    private NotationPresenter notationPresenter;

    public GameMediator(MediatorConstructionFlags flag, int duration, String botName) {
        if (flag == MediatorConstructionFlags.TIMED_LOCAL)
            this.boardPresenter = new LocalBoardPresenter(this);
        else if (flag == MediatorConstructionFlags.TIMED_AI)
            this.boardPresenter = new AIBoardPresenter(this, botName);
        this.whiteTimerPresenter = new TimerPresenter(duration + 1000, Colors.WHITE, this);
        this.blackTimerPresenter = new TimerPresenter(duration, Colors.BLACK, this);
        currentTimerPresenter = whiteTimerPresenter;
        currentTimerPresenter.start();
        notationPresenter = new NotationPresenter();
    }

    public GameMediator(MediatorConstructionFlags flag, int duration) {
        this(flag, duration, "Erfitt");
    }

    public GameMediator(MediatorConstructionFlags flag) {
        this(flag, 10 * 60 * 1000);
    }

    public BoardPresenter getBoardPresenter() {
        return boardPresenter;
    }

    public TimerPresenter getWhiteTimerPresenter() {
        return whiteTimerPresenter;
    }

    public TimerPresenter getBlackTimerPresenter() {
        return blackTimerPresenter;
    }

    public NotationPresenter getGameNotationPresenter() {
        return notationPresenter;
    }

    @Override
    public void notify(Object o) {
        if (o instanceof BoardPresenter) {
            BoardPresenter boardPresenter = (BoardPresenter) o;
            if (boardPresenter.isPlayerInCheckmate() || boardPresenter.isGameInStalemate()) {
                whiteTimerPresenter.gameIsOver();
                blackTimerPresenter.gameIsOver();
                notationPresenter.addToMovementNotations(boardPresenter.getMovementNotations().get(boardPresenter.getMovementNotations().size() - 1));
                notationPresenter.gameIsOver();
                return;
            } else if (boardPresenter.getCurrentPlayersColor() == Colors.WHITE && currentTimerPresenter != whiteTimerPresenter) {
                currentTimerPresenter.stop();
                currentTimerPresenter = whiteTimerPresenter;
                currentTimerPresenter.start();
            } else if (boardPresenter.getCurrentPlayersColor() == Colors.BLACK && currentTimerPresenter != blackTimerPresenter) {
                currentTimerPresenter.stop();
                currentTimerPresenter = blackTimerPresenter;
                currentTimerPresenter.start();
            }
            notationPresenter.addToMovementNotations(boardPresenter.getMovementNotations().get(boardPresenter.getMovementNotations().size() - 1));
        } else if (o instanceof TimerPresenter) {
            TimerPresenter timerPresenter = (TimerPresenter) o;
            if (timerPresenter.isGameOver()) {
                boardPresenter.timerRanOutForColor(timerPresenter.getColor());
                notationPresenter.gameIsOver();
            }
        } else {
            throw new IllegalArgumentException("Mediator notifier isn't a valid sender.");
        }
    }
}
