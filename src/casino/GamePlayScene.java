package casino;

import casino.game.Game;

public class GamePlayScene implements Scene {
    private SceneManager scene_manager = SceneManager.getInstance();
    private GameManager game_manager = GameManager.getInstance();
    private SaveDataManager data_manager = SaveDataManager.getInstance();

    public void proc() {
        Game game = game_manager.get();
        game.playGame();

        data_manager.save();
        scene_manager.set(SceneManager.GAME_SELECT_SCENE);
    }
}
