package casino.player;

import util.save.SaveData;

public class PlayData extends SaveData {
    private String userName;

    private long playTime;
    {
        playTime = 0;
    }

    private int playedPokerCounter;
    {
        playedPokerCounter = 0;
    }

    private int playedBlackJackCounter;
    {
        playedBlackJackCounter = 0;
    }

    private int playedOldMaidCounter;
    {
        playedOldMaidCounter = 0;
    }

    private long chip;
    {
        chip = 1000;
    }

    private PlayData(String path, String userName) {
        this.path = path;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void plusPlayTime(long time) {
        playTime += time;
    }

    public int getPokerCount() {
        return playedPokerCounter;
    }

    public void plusPokerCount() {
        playedPokerCounter++;
    }

    public int getBlackJackCount() {
        return playedBlackJackCounter;
    }

    public void plusBlackJackCount() {
        playedBlackJackCounter++;
    }

    public int getOldMaidCount() {
        return playedOldMaidCounter;
    }

    public void plusOldMaidCount() {
        playedOldMaidCounter++;
    }

    public long getChip() {
        return chip;
    }

    public void plusChip(int chips) {
        chip += chips;
    }

    public void minusChip(int chips) {
        chip -= chips;
    }

    public static PlayData createNewPlayData(String path, String userName) {
        return new PlayData(path, userName);
    }
}
