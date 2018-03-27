package casino;

import util.menu.Menu;

public class GameSelectMenu extends Menu<GameTitleCommand> {
    protected void addMenu() {
        add(1, "ポーカー");
        add(2, "ブラックジャック");
        add(3, "ばばぬき");
        add(4, "タイトルに戻る");
    }

    public GameTitleCommand callMethod(int no) {
        switch (no) {
            case 1:
                return GameTitleCommand.POKER;
            case 2:
                return GameTitleCommand.BLACKJACK;
            case 3:
                return GameTitleCommand.OLDMAID;
            case 4:
                return GameTitleCommand.BACK_TITLE;
            default:
                throw new IllegalArgumentException();
        }
    }
}

enum GameTitleCommand {
    POKER,
    BLACKJACK,
    OLDMAID,
    BACK_TITLE
}
