package util.save;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveFileManager<E extends SaveData> {
    private List<SaveFile> files = new ArrayList<>();

    public SaveFileManager(String path) {
        File file = Paths.get(path).toFile();

        if (!file.exists()) {
            boolean flag = file.mkdir();

            if (!flag) {
                throw new MakeFileFailedException();
            }
        }

        if (!file.isDirectory()) {
            throw new DirectoryNotOpenException();
        }

        File[] listFiles = file.listFiles();
        
        if (listFiles == null) {
            System.err.println("IOError.");
            System.exit(1);
        }

        for (File aFile : listFiles) {
            files.add(new SaveFile(aFile));
        }
    }

    public List<SaveFile> files() {
        return files;
    }

    public void showFileNames() {
        for (int i = 1; i <= files.size(); i++ ) {
            System.out.println(i + ": " + files.get(i - 1).getName());
        }
    }

    @SuppressWarnings("unchecked")
    public E load(int no) {
        return (E) files.get(no).load();
    }

    public void save(E data) {
        String pathstr = data.getFilePath();
        SaveFile sf = new SaveFile(pathstr);
        sf.save(data);
    }

    public boolean exist(String name) {
        for (SaveFile file : files) {
            if (file.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
