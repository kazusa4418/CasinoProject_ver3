package casino;

import util.menu.Menu;

public class TitleMenu extends Menu<TitleCommand> {
    protected void addMenu() {
        add(1, "はじめから");
        add(2, "つづきから");
        add(3, "ゲーム終了");
    }

    public TitleCommand callMethod(int id) {
        switch (id) {
            case 1:
                return TitleCommand.BEGINNING;
            case 2:
                return TitleCommand.CONTINUE;
            case 3:
                return TitleCommand.GAME_END;
            default:
                throw new IllegalArgumentException();
        }
    }
}

enum TitleCommand {
    BEGINNING,
    CONTINUE,
    GAME_END
}
