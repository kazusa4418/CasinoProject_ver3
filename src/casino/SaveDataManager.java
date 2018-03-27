package casino;

import casino.player.PlayData;
import util.manager.Console;
import util.save.SaveFile;
import util.save.SaveFileManager;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SaveDataManager {
    private static SaveDataManager instance = new SaveDataManager();

    private SaveFileManager<PlayData> SFManager = new SaveFileManager<>("saves");
    private PlayData data;

    private SaveDataManager() {
    }

    public static SaveDataManager getInstance() {
        return instance;
    }

    void setNewData() {
        Console.clear();

        Scanner sc = new Scanner(System.in);

        String fileName;
        Printer.println("新規プレイデータを作成します。");
        do {
            System.out.print("セーブファイル名を入力してください\n> ");
            fileName = sc.nextLine();
        }
        while (!(InputChecker.checkedFileName(fileName) &&
                                           checkUseable(fileName)));

        String userName;
        do {
            System.out.print("ユーザー名を入力してください\n> ");
            userName = sc.nextLine();
        }
        while (InputChecker.checkedUserName(userName, Pattern.compile("[a-z0-9]+",
                                                                Pattern.CASE_INSENSITIVE)));

        data = PlayData.createNewPlayData("saves/" + fileName + ".dat", userName);
        save();
        Printer.println("新規プレイデータを作成しました。");
    }

    private boolean checkUseable(String fileName) {
        if (!SFManager.exist(fileName)) {
            return true;
        }
        else {
            System.out.println("そのファイル名は既に使われています。");
            return false;
        }
    }

    void setLoadData() {
        Console.clear();

        List<SaveFile> files = SFManager.files();

        if (files.isEmpty()) {
            System.out.println("セーブデータがありません");
            setNewData();
        }
        else {
            System.out.println("セーブデータをロードします。\n");
            System.out.println("セーブデータ一覧:");

            SFManager.showFileNames();
            System.out.print("どのセーブデータをロードしますか？");
            int no = inputInt(files.size());

            data = SFManager.load(no - 1);
            Printer.println("セーブデータをロードしました。");
        }
    }

    private int inputInt(int to) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("> ");
                String no = sc.nextLine();
                int i = Integer.parseInt(no);

                if (i >= 1 && i <= to) {
                    return i;
                }
                else {
                    System.out.println("その番号のセーブデータはありません。");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("整数で入力してください。");
            }
        }
    }

    public PlayData getPlayData() {
        return data;
    }

    public void save() {
        SFManager.save(data);
    }
}
