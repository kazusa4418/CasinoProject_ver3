package casino;

import casino.game.BlackJack;
import casino.game.Game;
import casino.game.OldMaid;
import casino.game.Poker;

public class GameManager {
    private static GameManager instance = new GameManager();

    public static GameManager getInstance() {
        return instance;
    }

    private Game game;

    public static final int POKER_GAME     = 1;
    public static final int BLACKJACK_GAME = 2;
    public static final int OLDMAID_GAME   = 3;

    public Game get() {
        return game;
    }

    public void set(Game game) {
        this.game = game;
    }

    public void set(int flag) {
        rangeCheck(flag);

        switch (flag) {
            case POKER_GAME:
                game = new Poker();
                break;
            case BLACKJACK_GAME:
                game = new BlackJack();
                break;
            case OLDMAID_GAME:
                game = new OldMaid();
                break;
        }
    }

    private void rangeCheck(int flag) {
        if (flag <= 0 || flag > 3) {
            throw new IllegalArgumentException();
        }
    }
}