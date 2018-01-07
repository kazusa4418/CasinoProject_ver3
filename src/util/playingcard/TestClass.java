package util.playingcard;

@SuppressWarnings("unused")
class TestClass {
    private void unused() {
        CardStock cs = new CardStock();
        cs.initialize();

        Hand hand = new Hand();
        distribute(hand);

        hand.sort();
    }

    private void distribute(Hand hand) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                hand.add(new PlayingCard(i, j));
        }
    }
}