package casino.game;

import util.playingcard.*;

import java.util.Arrays;
import java.util.List;

class PokerRoll {
    private List<PlayingCard> cards;
    private final int HAND_SIZE = 5;
    private int[] numStrength = new int[HAND_SIZE];
    private int[] overlap = new int[CardNumber.values().length - 1];

    private int score = 0;
    private final int JOKER_NUMBER;

    PokerRoll(List<PlayingCard> cards) {
        this.cards = cards;
        JOKER_NUMBER = jokerCount();
        setNumberArray();
        overlapCardCheck();
        Arrays.sort(overlap);
        addJokerToOverlap();
    }

    PokerRoll judgeRoll() {
        score = 1;
        judgeRoyalStraightFlush();
        judgeTwoPair();
        judgeFullHouse();
        judgeFourCard();
        judgeFiveCard();
        return this;
    }

    int getRoll() {
        return score;
    }

    @Override
    public String toString() {
        switch (score) {
            case 1:
                return "ハイカード";
            case 2:
                return "ワンペア";
            case 3:
                return "ツーペア";
            case 4:
                return "スリーカード";
            case 5:
                return "ストレート";
            case 6:
                return "フラッシュ";
            case 7:
                return "フルハウス";
            case 8:
                return "フォーカード";
            case 9:
                return "ストレートフラッシュ";
            case 10:
                return "ロイヤルストレートフラッシュ";
            case 11:
                return "ファイブカード";
            default:
                throw new RollNotFoundException();
        }
    }

    private int jokerCount() {
        int jokerNumber = 0;
        for (PlayingCard card : cards)
            if (card.isJOKER())
                jokerNumber++;
        return jokerNumber;
    }

    private void setNumberArray() {
        for (int i = 0; i < HAND_SIZE; i++)
            numStrength[i] = cards.get(i).getNumber().strength();
    }

    //TODO: もっといい実装がありそうなので書き直す
    private void overlapCardCheck() {
        for (int i = 0; i < overlap.length; i++)
            for (int num : numStrength) {
                if (num == (i + 1))
                    overlap[i]++;
            }
    }

    private void addJokerToOverlap() {
        overlap[overlap.length - 1] += JOKER_NUMBER;
    }

    private boolean judgeFlush() {
        for (int i = 1; i < HAND_SIZE; i++)
            if (cards.get(0).getSuit() != cards.get(i).getSuit()
                    && cards.get(i).getSuit() != CardSuit.JOKER)
                return false;
        setScore(6);
        return true;
    }

    private boolean judgeStraight() {
        int maxIndex = HAND_SIZE - JOKER_NUMBER - 1;
        if (numStrength[maxIndex] - numStrength[0] < 5 && distinctCheck()) {
            setScore(5);
            return true;
        }
        return false;
    }

    private boolean distinctCheck() {
        for (int i = 0; i < HAND_SIZE - JOKER_NUMBER; i++)
            for (int j = i + 1; j < HAND_SIZE - JOKER_NUMBER; j++)
                if (numStrength[i] == numStrength[j])
                    return false;
        return true;
    }


    private boolean judgeStraightFlush() {
        if (judgeStraight() && judgeFlush()) {
            setScore(9);
            return true;
        }
        return false;
    }

    //TODO: アルゴリズムが思いつき次第書き直せ
    private void judgeRoyalStraightFlush() {
        if (judgeStraightFlush()) {
            for (int i = 0; i < HAND_SIZE; i++)
                if (numStrength[0] == 10)
                    setScore(10);
        }
    }

    private boolean judgeOnePair() {
        if (overlap[overlap.length - 1] == 2) {
            setScore(2);
            return true;
        }
        return false;
    }

    private void judgeTwoPair() {
        if (judgeOnePair())
            if (overlap[overlap.length - 2] == 2) {
                setScore(3);
            }
    }

    private boolean judgeThreeCard() {
        if (overlap[overlap.length - 1] == 3) {
            setScore(4);
            return true;
        }
        return false;
    }

    private void judgeFullHouse() {
        if (judgeThreeCard())
            if (overlap[overlap.length - 2] == 2) {
                setScore(7);
            }
    }

    private void judgeFourCard() {
        if (overlap[overlap.length - 1] == 4)
            setScore(8);
    }

    private void judgeFiveCard() {
        if (overlap[overlap.length - 1] == 5)
            setScore(11);
    }

    private void setScore(int score) {
        if (this.score < score) this.score = score;
    }
}

class RollNotFoundException extends RuntimeException {
    RollNotFoundException() {
        super();
    }
}
