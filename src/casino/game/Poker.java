package casino.game;

import casino.InputChecker;
import casino.Printer;
import casino.player.ComPlayer;
import casino.player.Player;
import casino.player.UserPlayer;
import util.manager.Console;
import util.playingcard.CardStock;
import util.playingcard.PlayingCard;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Poker implements Game {
    private CardStock stock = CardStock.createStock(CardStock.POKER_STRENGTH);
    private UserPlayer user = new UserPlayer(stock);
    private ComPlayer com = new ComPlayer(stock);

    private int betChip = 0;

    private boolean flag = true;

    public void playGame() {
        while (flag) {
            user.plusPoker();
            runPoker();
        }
    }

    private void runPoker() {
        Console.clear();

        Printer.println("ポーカーを始めます。");
        stock.shuffle();

        distribute(user);
        distribute(com);

        user.handSort();
        com.handSort();

        showHand(user);

        bet();

        handChange();

        battle();

        result();

        user.retAllCards();
        com.retAllCards();

        askContinue();
    }

    private void distribute(Player player) {
        while (player.handSize() < 5) {
            player.draw();
        }
    }

    private void showHand(Player player) {
        if (player instanceof UserPlayer) {
            System.out.println("あなたの手札: ");
        }
        if (player instanceof ComPlayer) {
            System.out.println("コンピュータの手札: ");
        }

        List<PlayingCard> cards = player.getAllCards();
        for (PlayingCard card : cards) {
            System.out.print(card + "  ");
        }
    }

    private void bet() {
        System.out.println("チップをベットします。");
        betChip = inputBetChips();
    }

    private int inputBetChips() {
        while (true) {
            int num = inputBetNum();

            if (num > 0 && num < user.haveChips()) {
                return num;
            }
            else {
                if (num <= 0) {
                    System.err.println("掛け金は1以上の値を指定してください。");
                }
                else {
                    System.err.println("そんなにたくさんチップをもっていません。");
                }
            }
        }
    }

    private int inputBetNum() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("何枚ベットしますか？\n> ");
                String num = sc.nextLine();

                return Integer.parseInt(num);
            }
            catch (NumberFormatException e) {
                System.err.println("整数で入力してください。");
            }
        }

    }

    private void handChange() {
        List<PlayingCard> cards = user.getAllCards();

        while (user.handSize() > 0) {
            Console.clear();

            System.out.println("手札 : ");

            for (int i = 0; i < cards.size(); i++ ) {
                System.out.println((i + 1) + ": " + cards.get(i));
            }

            System.out.println("入れ替える手札を選んでください。");
            int no = inputInt();

            //TODO: ここも直せよ？
            if (no == -1) {
                break;
            }

            user.retCard(no - 1);
        }
        distribute(user);
    }

    private int inputInt() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("> ");
                String select = sc.nextLine();

                //TODO: バグに気づいてとりあえずで直しただけなのでたぶんくそ状態
                int no = select.equals("") ? -1 : Integer.parseInt(select);

                if (no > 0 && no <= user.handSize() || no == -1) {
                    return no;
                }
                else {
                    System.out.println("その番号のカードは存在しません。");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("数字で入力してください。");
            }
        }
    }

    private void battle() {
        Console.clear();
        Printer.dot(3);
        Printer.println("いざ、勝負！");

        PokerRoll userRoll = new PokerRoll(user.getAllCards());
        PokerRoll comRoll = new PokerRoll(com.getAllCards());

        int userScore = userRoll.judgeRoll().getRoll();
        int comScore = comRoll.judgeRoll().getRoll();

        showResult(user, userRoll);
        showResult(com, comRoll);

        String result = userScore == comScore ? "引き分け" :
                                userScore > comScore ? "あなたの勝ち" : "あなたの負け";
        Printer.println(result);
    }

    private void showResult(Player player, PokerRoll roll) {
        showHand(player);
        System.out.println("役: " + roll);
        System.out.println();
    }

    private void result() {

    }

    private void askContinue() {
        Scanner sc = new Scanner(System.in);

        String ans;
        do {
            System.out.print("もう一度プレイしますか？ (yes[y] or no[n])\n> ");
            ans = sc.nextLine();
        }
        while (InputChecker.checkedYesOrNo(ans, Pattern.compile("yes|y|no|n",
                                                                    Pattern.CASE_INSENSITIVE)));

        if (ans.matches("no|No|nO|NO|n|N")) {
            flag = false;
        }
    }
}
