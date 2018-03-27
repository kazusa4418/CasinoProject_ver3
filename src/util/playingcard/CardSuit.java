package util.playingcard;

public enum CardSuit {
    CLOVER("♣"),
    DIAMOND("♦"),
    HEART("♥"),
    SPADE("♠"),
    JOKER("JOKER");

    private String suit;

    CardSuit(String suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return this.suit;
    }

    public int strength() {
        return this.ordinal();
    }
}
