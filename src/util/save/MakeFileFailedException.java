package util.save;

public class MakeFileFailedException extends RuntimeException {
    public MakeFileFailedException() {
        super("Failed to create the file.");
    }

    public MakeFileFailedException(String msg) {
        super(msg);
    }
}
