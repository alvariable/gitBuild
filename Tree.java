import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

public class Tree {

    String indexPath = "./index";
    File indexFile = new File(indexPath);

    String hashName;
    File tree;
    String treePath;

    // create file for tree
    public Tree() throws IOException {
        tree = new File("Tree");
        initializeTree();
    }

    public Tree(String pathName) throws IOException {
        tree = new File(pathName);

    }

    public void initializeTree() throws IOException {
        if (!tree.exists()) {
            tree.createNewFile();
        }
        treePath = tree.getPath();
        System.out.println(treePath.toString());
    }

    public void add(String str) throws Exception { // adds this to THE TREE FILE outside objects folder

        // if last no newline
        System.out.println("does tree exist? " + tree.exists());
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

    public void eraseTreeContents() throws IOException, URISyntaxException {
        tree.delete();
        tree.createNewFile();
        PrintWriter pw = new PrintWriter(tree);
        pw.print("");
        pw.close();
        // FileUtil.writeFile("", treePath);
    }

    public String addDirectory(String directoryPath) throws Exception {
        // empty folder
        // if folder is empty, do NOTHING
        System.out.println(".");
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        if (fileList == null)
            return FileUtil.getHash("");
        System.out.println("there are/is " + fileList.length + " files in this directory");
        for (File subfile : fileList) {
            String entryString;
            String fn = subfile.getName();
            if (subfile.isDirectory()) {
                System.out.println("This is a directory " + fn);
                Tree temp = new Tree("tempTree");
                temp.eraseTreeContents();
                String hash = temp.addDirectory(directoryPath + "/" + fn);
                entryString = "tree : " + hash + " : " + fn;
            } else {
                System.out.println("This is a blob: " + fn);
                // Blob temp = new Blob(fn);
                Blob temp = new Blob(directoryPath + "/" + fn);
                entryString = "blob : " + temp.getSHA1() + " : " + fn;
            }
            System.out.println("This is entry string: " + entryString);
            add(entryString);
            // System.out.println(entryString);
        }
        return generateBlob();
        // folder with two files
        // folder with two files, empty folder
        // folder with two files, folder with two files

    }

    public String addDirectoryOld(String directoryPath) throws Exception {
        //
        File directory = new File(directoryPath);
        if (directory.listFiles() != null) {
            for (File subfile : directory.listFiles()) {
                String fn = subfile.getName();
                if (subfile.isDirectory()) {
                    // if subfile is a directory recursively calls itself
                    String entryForm = "tree : " + addDirectory(fn) + " : " + fn;
                    add(entryForm);
                } else {
                    Blob temp = new Blob(fn);
                    String entryFormal = "blob : " + temp.getSHA1() + " : " + fn;
                    add(entryFormal);
                }
                return generateBlob();
            }
        }
        return FileUtil.getHash("");
    }

    public String generateBlob() throws Exception { // no delete old blob
        // String content;
        BufferedReader bf = new BufferedReader(new FileReader(tree));
        StringBuilder contents = new StringBuilder();
        while (bf.ready()) {
            contents.append((char) bf.read());
        }
        bf.close();
        String content = FileUtil.readFile(tree);
        String treeHash = FileUtil.getHash(content);
        Blob b = new Blob("Tree");
        b.add("./objects/");
        return treeHash;
    }

    public String getContents() throws IOException, URISyntaxException {
        return FileUtil.readFile(tree);
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

    public String toString() {
        try {
            return FileUtil.readFile(tree);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return hashName;
    }

    public void checkout(String shaOfCommit) throws IOException {
        // locates the commit from sha in objects folder

        BufferedReader commitReader = new BufferedReader(new FileReader("objects/" + shaOfCommit));
        // goes to first line of commit file to get tree hash
        String treeHash = commitReader.readLine();
        commitReader.close();
        recreateDirectory("", treeHash);
        // locates the tree
        // gets sha of first entry
        // if the entry begins w "blob", get the contents of the file by locating it in
        // the objects folder
        // recreates the file with the name and the contents
        // if the entry begins w "tree", calls checkout with the sha in the entryline
        //

    }

    private void checkoutComplete(String directoryHash) {

    }

    public void recreateDirectory(String directoryPrefix, String directoryHash) throws Exception {
        File treeReferenceFile = new File("objects/" + directoryHash);
        BufferedReader treeReader = new BufferedReader(new FileReader(treeReferenceFile));
        while (treeReader.ready()) {
            String line = treeReader.readLine();
            // if line begins with "tree:" ->
            // recreateDirectory(directoryPrefix/nameofCurrentDirectory, hash)
            // otehrwise just recreate a blob
        }
        treeReader.close();
        File recreatedFile = new File(directoryPrefix + "/" + directoryHash);
        // go thru each line individually, if it' a directory tehn call direcotry
        // otherwise i recreate the blob with the prefix of the directorty name

        // if isDirectory -> recreateDirectory(directoryPath)
        // create new directory (directoryRrefix + "/" + directoryPath);
        //

    }
}
