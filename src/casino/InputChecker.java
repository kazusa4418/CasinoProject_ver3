package casino;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {
    public static boolean checkedFileName(String fileName) {
        String[] ngWord = { "/", "<", ">", "*", "?", "\"", "|", ":", ";" };

        for (String word : ngWord) {
            if (fileName.contains(word)) {
                System.out.println("文字 : " + word + " はファイル名に使用できません。");
                return false;
            }
        }
        if (fileName.length() <= 0 || fileName.length() > 215) {
            System.out.println("ファイル名は半角で1~215文字まで使用できます。");
            return false;
        }
        return true;
    }

    public static boolean checkedUserName(String userName, Pattern pt) {
        Matcher mt = pt.matcher(userName);

        if (!mt.matches()) {
            System.out.println("ローマ字か数字のみで入力してください。");
            return true;
        }
        if (userName.length() > 30) {
            System.out.println("30文字以下で入力してください。");
        }
        return false;
    }

    public static boolean checkedYesOrNo(String ans, Pattern pt) {
        Matcher mt = pt.matcher(ans);

        if (!mt.matches()) {
            System.out.println("入力が正しくありません。");
            return true;
        }
        return false;
    }
}
