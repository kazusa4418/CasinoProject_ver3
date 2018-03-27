package util.save;

import java.io.Serializable;

public abstract class SaveData implements Serializable {
    protected String path;

    public String getFilePath() {
        return path;
    }

    public void setFilePath(String path) {
        this.path = path;
    }
}
