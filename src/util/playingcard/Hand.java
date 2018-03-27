package util.playingcard;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<PlayingCard> cards = new ArrayList<>();

    public Hand() {
    }

    public void add(PlayingCard card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public PlayingCard get(int idx) {
        return cards.get(idx);
    }

    public PlayingCard give(int idx) {
        PlayingCard card = cards.get(idx);
        cards.remove(idx);
        return card;
    }

    public void remove(int index) {
        cards.remove(index);
    }

    public void sort() {
        cards.sort(PlayingCard.getSorter());
    }

    public List<PlayingCard> getList() {
        return cards;
    }
}
