import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tree {
    /

    String indexPath = "./index";
    File indexFile = new File(indexPath);

    String hashName;
    File tree;
    String treePath;

    // create file for tree
    public Tree() {
        tree = new File("Tree");

    }

    public void initializeTree() throws Exception {
        if (!tree.exists()) {
            tree.createNewFile();
        }
        treePath = tree.getPath();
    }

    public void add(String str) throws Exception { // adds this to THE TREE FILE outside objects folder

        // if last no newline

        FileWriter fw = new FileWriter(tree, true);

        // check if file empty
        if (tree.length() == 0)
            fw.append(str);
        else
            fw.append("\n" + str);

        fw.close();
    }

    public void remove(String str) throws Exception { // removes this from THE TREE FILE

        BufferedReader bf = new BufferedReader(new FileReader(tree));
        StringBuilder text = new StringBuilder();

        // this will be put into new index file

        // locate
        while (bf.ready()) {
            // System.out.println("ready");
            String line = bf.readLine();
            // System.out.println(line.length());

            // locate name
            int i = line.indexOf(str);

            // builds string to put in new indexfile
            if (i < 0) {
                // System.out.println(line.substring(0, index - 1));
                if (text.length() == 0)
                    text.append(line);
                else
                    text.append("\n" + line);
            }
        }

        bf.close();

        tree.delete();
        tree.createNewFile();

        FileWriter fw = new FileWriter(tree);
        // System.out.println("write");
        fw.write(text.toString()); // account for "\n" at end

        fw.close();
    }

    public String addDirectory(String directoryPath) throws Exception {
        File directory = new File(directoryPath);
        for (File subfile : directory.listFiles()) {
            String fn = subfile.getName();
            if (subfile.isDirectory()) // if subfile is a directory recursively calls itself
            // recAddDir(directoryPath, fn);
            {

            } else { // otherwise adds subfile as blob to tree
                     // gets file name from File
                     //create new Blob
                     Blob temp = new Blob(fn);
                     String entryFormal = "blob : " + temp.getSHA1() + " : " + fn;
                     add(entryFormal);
                     //format the correct String
                     //call add method 
             //   add(fn);
            }
            return generateBlob();
        }
        // returns tree shah1
        return null;
    }

    private void recAddDir(Tree parent, String directoryPath) {
        Tree child = new Tree();
        child.addDirectory(directoryPath);
        Path path = Paths.get(directoryPath);
        File directory = new File(directoryPath);
        for (File subfile : directory.listFiles()) {
            // if subfile is a directory recursively calls itself
            // gets the directory and sets it to String
            recAddDir(child, "");
            // otherwise adds subfile as blob to tree
        }
        // parent.add(child); //child tree to parent
        // returns tree shah1

    }

    public String generateBlob() throws Exception { // no delete old blob
        // String content;
        BufferedReader bf = new BufferedReader(new FileReader(tree));
        StringBuilder contents = new StringBuilder();
        while (bf.ready()) {
            contents.append((char) bf.read());
        }
        bf.close();

        String treeHash = FileUtil.getHash(contents.toString());

        // // create new file in objects folder
        // File treeBlob = new File("./objects/", treeHash);
        // treeBlob.createNewFile();

        // // write into blob
        // FileWriter fw = new FileWriter(treeBlob);
        // fw.write(contents.toString());

        // fw.close();

        Blob b = new Blob("Tree");
        b.add("./objects/");
        return treeHash;
    }

    public String getHash() {
        if (hashName != null)
            return hashName;
        else
            return "";
    }

    public File getIndex() {
        return indexFile;
    }
}
