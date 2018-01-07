package util.playingcard;

import java.util.ArrayList;
import java.util.Collections;

public class CardStock extends ArrayList<PlayingCard> {
    private final int JOKER_NUMBER;

    public CardStock() {
        this(2);
    }

    public CardStock(int jokerNum) {
        createStock(jokerNum);
        JOKER_NUMBER = jokerNum;
    }

    private void createStock(int jokerNum) {
        //52枚分のカードの各インスタンスを作成し、リストに格納する
        CardNumber[] numbers = CardNumber.values();
        CardSuit[] suits = CardSuit.values();
        for (int i = 1; i < numbers.length; i++ ) {
            for (int j = 1; j < suits.length; j++ ) {
                this.add(new PlayingCard(numbers[i], suits[j]));
            }
        }
        for (int i = 0; i < jokerNum; i++) {
            this.add(new PlayingCard(CardNumber.JOKER, CardSuit.JOKER));
        }
    }

    public PlayingCard takeCard() {
        PlayingCard card = this.get(0);
        this.remove(0);
        return card;
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public void initialize() {
        this.clear();
        createStock(JOKER_NUMBER);
    }
}
