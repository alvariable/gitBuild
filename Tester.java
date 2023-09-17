import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tester {
    public static void main(String[] args) throws Exception, IOException, NoSuchAlgorithmException {
        Index init = new Index();
        init.initializeProject();
        // init.addBlob("testFile.txt");
        // init.addBlob("file.txt");

        // init.removeBlob("file.txt");

        // Tree tree = new Tree();
        // tree.initializeTree();

        // tree.add("thing : 2093103821038210");
        // // tree.remove("thing : 2093103821038210");
        // tree.generateBlob();

        System.out.println("test done");
    }
}
