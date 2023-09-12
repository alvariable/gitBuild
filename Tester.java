import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tester {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Index init = new Index();
        init.initializeProject();
        init.addBlob(".\\testFile.txt");
        init.addBlob(".\\file.txt");

        init.removeBlob(".\\file.txt");
    }
}
