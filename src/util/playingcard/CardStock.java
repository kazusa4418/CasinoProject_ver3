package util.playingcard;

import java.util.*;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class CardStock {
    private List<PlayingCard> cards = new ArrayList<>();

    private Map<CardNumber, Integer> numStrength;

    public static final int DEFAULT_STRENGTH = 1;
    public static final int POKER_STRENGTH = 2;
    public static final int BLACKJACK_STRENGTH = 3;
    public static final int PRESIDENT_STRENGTH = 4;

    private CardStock(){
    }

    //TODO: もっと良いメソッド名を考えましょうね。峯島くん。
    public static CardStock createDefaultStock() {
        CardStock newStock = createStock();
        newStock.setStrength(DEFAULT_STRENGTH);
        return newStock;
    }

    public static CardStock createStock(int strength) {
        CardStock newStock = createDefaultStock();
        newStock.setStrength(strength);
        return newStock;
    }

    public static CardStock createStock(Map<CardNumber, Integer> map) {
        CardStock newStock = createDefaultStock();
        newStock.setStrength(map);
        return newStock;
    }

    private static CardStock createStock() {
        /* 1~13までの数字と4種のマークのすべての組み合わせ分のカードを作成し、
         * それに加えて２枚のJOKERを追加したストックを作成する
         */
        CardStock stock = new CardStock();

        CardNumber[] numbers = CardNumber.values();
        CardSuit[] suits = CardSuit.values();
        for (int i = 1; i < numbers.length; i++ ) {
            for (int j = 0; j < suits.length - 1; j++ ) {
                stock.insert(new PlayingCard(numbers[i], suits[j], stock));
            }
        }
        for (int i = 0; i < 2; i++) {
            stock.insert(new PlayingCard(CardNumber.JOKER, CardSuit.JOKER, stock));
        }
        return stock;
    }


    Map<CardNumber, Integer> getStrengthHashMap() {
        return numStrength;
    }

    public void setStrength(int strength) {
        switch (strength) {
            case 1:
                numStrength = HashMapProvider.DEFAULT.getStrengthMap();
                break;
            case 2:
                numStrength = HashMapProvider.POKER.getStrengthMap();
                break;
            case 3:
                numStrength = HashMapProvider.BLACKJACK.getStrengthMap();
                break;
            case 4:
                numStrength = HashMapProvider.PRESIDENT.getStrengthMap();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void setStrength(Map<CardNumber, Integer> map) {
        this.numStrength = map;
    }

    public PlayingCard getCard() {
        PlayingCard card = cards.get(0);
        cards.remove(0);
        return card;
    }

    public void insert(PlayingCard card) {
        card.setStock(this);
        cards.add(card);
    }

    public void sort() {
        cards.sort(Comparator.comparingInt(PlayingCard::strength));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int getJokerNum() {
        return (int) cards.stream().filter(PlayingCard::isJOKER).count();
    }

    public Stream stream() {
        return cards.stream();
    }
}

enum HashMapProvider {
    DEFAULT(1),
    POKER(2),
    BLACKJACK(3),
    PRESIDENT(4);

    private Map<CardNumber, Integer> map;

    HashMapProvider(int no) {
        map = createHashMap(no);
    }

    private Map<CardNumber, Integer> createHashMap(int no) {
        switch (no) {
            case 1:
                return createDEFAULT_Map();
            case 2:
                return createPOKER_Map();
            case 3:
                return createBLACKJACK_Map();
            case 4:
                return createPRESIDENT_Map();
            default:
                throw new IllegalArgumentException();
        }
    }

    private Map<CardNumber, Integer> createDEFAULT_Map() {
        HashMap<CardNumber, Integer> map = new HashMap<>();
        CardNumber[] numbers = CardNumber.values();

        for (int i = 1; i < numbers.length; i++ ) {
            map.put(numbers[i], i);
        }
        map.put(numbers[0], numbers.length);
        return map;
    }

    private Map<CardNumber, Integer> createPOKER_Map() {
        CardNumber[] numbers = CardNumber.values();

        Map<CardNumber, Integer> map = new HashMap<>();
        for (int i = 2; i < numbers.length; i++ ) {
            map.put(numbers[i], i);
        }
        map.put(numbers[1], numbers.length);
        map.put(numbers[0], numbers.length + 1);
        return map;
    }

    private Map<CardNumber, Integer> createBLACKJACK_Map() {
        CardNumber[] numbers = CardNumber.values();

        Map<CardNumber, Integer> map = new HashMap<>();
        for (int i = 1; i < numbers.length; i++ ) {
            map.put(numbers[i], i);
        }
        map.put(numbers[0], null);
        return map;
    }

    private Map<CardNumber, Integer> createPRESIDENT_Map() {
        CardNumber[] numbers = CardNumber.values();

        Map<CardNumber, Integer> map = new HashMap<>();
        for (int i = 3; i < numbers.length; i++ ) {
            map.put(numbers[i], i);
        }
        map.put(numbers[1], numbers.length);
        map.put(numbers[2], numbers.length + 1);
        map.put(numbers[3], numbers.length + 2);
        return map;
    }

    public Map<CardNumber, Integer> getStrengthMap() {
        return map;
    }
}
