package util.playingcard;

import java.util.Comparator;

public class CardSorterOnPoker implements Comparator<PlayingCard> {
    public int compare(PlayingCard card1, PlayingCard card2) {
        if (card1.getNum() == CardNumber.JOKER)
            return 1;
        if (card2.getNum() == CardNumber.JOKER)
            return -1;
        if (card1.getNum() == CardNumber.num1)
            return 1;
        if (card2.getNum() == CardNumber.num1)
            return -1;
        return Integer.compare(card1.strength(), card2.strength());
    }
}
