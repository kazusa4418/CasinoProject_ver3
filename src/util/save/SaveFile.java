package util.save;

import java.io.*;

public class SaveFile {
    private File file;

    SaveFile(String path) {
        file = new File(path);
    }

    SaveFile(File file) {
        this.file = file;
    }

    public Object load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public void save(Object data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
            oos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getName() {
        String fileName = file.getName();
        int dotIdx = fileName.lastIndexOf(".");
        return fileName.substring(0, dotIdx);
    }
}
