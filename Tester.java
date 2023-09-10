import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tester {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Index init = new Index();
        init.initializeProject();
        init.addBlob("/Users/ardendoyle/Documents/personal/coding/Honors Topics/gitBuild/testFile.txt");
        // init.addBlob("/Users/ardendoyle/Documents/personal/coding/Honors
        // Topics/gitBuild/file.txt");
        ;
        init.removeBlob("/Users/ardendoyle/Documents/personal/coding/Honors Topics/gitBuild/file.txt");
    }
}
