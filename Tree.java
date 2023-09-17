import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;

public class Tree {

    String indexPath = "./index";
    File indexFile = new File(indexPath);

    String hashName;
    File tree;
    String treePath;

    // create file for tree
    public Tree() throws Exception {
        tree = new File("Tree");
        if (!tree.exists()) {
            tree.createNewFile();
        }
        treePath = tree.getPath();
    }

    public void add(String str) throws Exception { // adds this to THE TREE FILE outside objects folder

        // if last no newline

        FileWriter fw = new FileWriter(tree);

        // check if file empty
        if (tree.length() == 0)
            fw.append(str);
        else
            fw.append("\n" + str);

        fw.close();
    }

}
