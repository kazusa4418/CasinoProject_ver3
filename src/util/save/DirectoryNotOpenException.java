package util.save;

public class DirectoryNotOpenException extends RuntimeException {
    public DirectoryNotOpenException() {
        super("Failed to open the directory.");
    }

    public DirectoryNotOpenException(String msg) {
        super(msg);
    }
}
