package casino;

import util.manager.Console;

public class GameSelectScene implements Scene {
    private SceneManager scene_manager = SceneManager.getInstance();
    private GameManager game_manager = GameManager.getInstance();

    public void proc() {
        Console.clear();
        GameSelectMenu menu = new GameSelectMenu();

        System.out.println("遊ぶゲームを選んでください。");
        GameTitleCommand command = menu.show().select();

        switch (command) {
            case POKER:
                game_manager.set(GameManager.POKER_GAME);
                break;
            case BLACKJACK:
                game_manager.set(GameManager.BLACKJACK_GAME);
                break;
            case OLDMAID:
                game_manager.set(GameManager.OLDMAID_GAME);
                break;
            case BACK_TITLE:
                backToTitle();
                return;
        }

        scene_manager.set(SceneManager.GAME_PLAY_SCENE);
    }
    private void backToTitle() {
        scene_manager.set(SceneManager.TITLE_SCENE);
    }
}
