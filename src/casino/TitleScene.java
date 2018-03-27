package casino;

class TitleScene implements Scene {
    private SceneManager scene_manager = SceneManager.getInstance();
    private SaveDataManager data_manager = SaveDataManager.getInstance();

    public void proc() {
        TitleMenu menu = new TitleMenu();

        System.out.println("カジノへようこそ。");
        TitleCommand command = menu.show().select();

        switch (command) {
            case BEGINNING:
                data_manager.setNewData();
                break;
            case CONTINUE:
                data_manager.setLoadData();
                break;
            case GAME_END:
                exit();
        }

        scene_manager.set(SceneManager.GAME_SELECT_SCENE);
    }

    private void exit() {
        Printer.println("ゲームを終了します");
        System.exit(0);
    }
}
