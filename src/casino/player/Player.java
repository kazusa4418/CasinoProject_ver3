package casino.player;

import util.playingcard.CardStock;
import util.playingcard.Hand;
import util.playingcard.PlayingCard;

import java.util.List;

public abstract class Player {
    private Hand hand;
    private CardStock stock;

    Player() {
        hand = new Hand();
    }

    Player(CardStock stock) {
        hand = new Hand();
        this.stock = stock;
    }

    public void draw() {
        hand.add(stock.getCard());
    }

    public void retCard(int idx) {
        PlayingCard card = hand.give(idx);
        stock.insert(card);
    }

    public void retAllCards() {
        while (hand.size() > 0) {
            retCard(0);
        }
    }

    public void discard(int index) {
        hand.remove(index);
    }

    public void handSort() {
        hand.sort();
    }

    public List<PlayingCard> getAllCards() {
        return hand.getList();
    }

    public int handSize() {
        return hand.size();
    }
}
