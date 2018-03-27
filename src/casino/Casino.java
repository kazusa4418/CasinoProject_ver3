package casino;

public class Casino {

    private SceneManager manager = SceneManager.getInstance();

    Casino() {
        manager.set(SceneManager.TITLE_SCENE);
    }

    void run() {
        while (true) {
            Scene scene = manager.get();
            scene.proc();
        }
    }
}

