package casino;

public class SceneManager {
    /* -------------------- INSTANCE -------------------- */
    private static SceneManager instance = new SceneManager();

    public static SceneManager getInstance() {
        return instance;
    }
    /* -------------------------------------------------- */

    public static final int TITLE_SCENE       = 1;
    public static final int GAME_SELECT_SCENE = 2;
    public static final int GAME_PLAY_SCENE   = 3;

    private Scene now_scene;

    private SceneManager(){
    }

    public Scene get() {
        return now_scene;
    }

    public void set(Scene scene) {
        now_scene = scene;
    }

    public void set(int flag) {
        flagCheck(flag);

        switch (flag) {
            case TITLE_SCENE:
                now_scene = new TitleScene();
                break;
            case GAME_SELECT_SCENE:
                now_scene = new GameSelectScene();
                break;
            case GAME_PLAY_SCENE:
                now_scene = new GamePlayScene();
                break;
        }
    }

    private void flagCheck(int flag) {
        if (flag <= 0 || flag > 3) {
            throw new IllegalArgumentException();
        }
    }
}

interface Scene {
    void proc();
}
