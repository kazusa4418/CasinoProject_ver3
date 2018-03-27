package util.playingcard;

import java.util.Comparator;

@SuppressWarnings("unused")
public class PlayingCard implements Comparable<PlayingCard> {
    private final CardNumber number;
    private final CardSuit suit;

    private CardStock stock;

    public PlayingCard(CardNumber number, CardSuit suit) {
        this(number, suit, null);
    }

    public PlayingCard(CardNumber number, CardSuit suit, CardStock stock) {
        checkArgument(number, suit);
        this.number = number;
        this.suit = suit;
        this.stock = stock;
    }

    private void checkArgument(CardNumber number, CardSuit suit) {
        if ((number.equals(CardNumber.JOKER) && !suit.equals(CardSuit.JOKER)) ||
                (!number.equals(CardNumber.JOKER) && suit.equals(CardSuit.JOKER))) {
            throw new IllegalArgumentException();
        }
    }

    public int strength() {
        if (stock == null) {
            return number.strength() * 10 + suit.strength();
        }
        else {
            return stock.getStrengthHashMap().get(number) * 10 + suit.strength();
        }
    }

    public boolean isJOKER() {
        return number.equals(CardNumber.JOKER) && suit.equals(CardSuit.JOKER);
    }


    public CardNumber getNumber() {
        return number;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public String toString() {
        return isJOKER() ? "JOKER" : number + " " + suit;
    }

    void setStock(CardStock stock) {
        this.stock = stock;
    }

    public int compareTo(PlayingCard other) {
        return Integer.compare(this.strength(), other.strength());
    }

    public static Comparator<PlayingCard> getSorter() {
        return new Sorter();
    }
}

class Sorter implements Comparator<PlayingCard> {
    public int compare(PlayingCard card1, PlayingCard card2) {
        return Integer.compare(card1.strength(), card2.strength());
    }
}
