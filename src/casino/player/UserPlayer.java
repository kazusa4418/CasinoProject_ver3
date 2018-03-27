package casino.player;

import casino.SaveDataManager;
import util.playingcard.CardStock;

public class UserPlayer extends Player {
    private PlayData data = SaveDataManager.getInstance().getPlayData();

    public UserPlayer() {
        super();
    }

    public UserPlayer(CardStock stock) {
        super(stock);
    }

    public long haveChips() {
        return data.getChip();
    }

    public void plusPoker() {
        data.plusPokerCount();
    }
}
