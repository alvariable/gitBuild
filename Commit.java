import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commit {
    String author, summary, date, name, treeSHA, prevComm, fileHash;
    String parentSHA = "";
    File head = new File("HEAD");

    Tree origin;
    // String treeSHA = origin.getHash();

    File commit;
    String commitPath = "./objects/";

    public Commit(String author, String summary) throws Exception {
        File folder = new File(commitPath);
        if (!folder.exists())
            folder.mkdirs();
        this.author = author;
        this.summary = summary;
        createDate();
        // treeSHA = createTree();
        initializeCommit();

    }

    public Commit(String SHA, String author, String summary) throws Exception {
        File folder = new File(commitPath);
        if (!folder.exists())
            folder.mkdirs();
        this.author = author;
        this.summary = summary;
        this.parentSHA = SHA;
        createDate();
        // treeSHA = createTree();
        initializeCommit();
        addCurrentCommitToPreviousCommit();

    }

    private void initializeCommit() throws Exception {
        // if (!commit.exists()) {
        // commit.createNewFile();
        // }
        origin = new Tree();
        treeSHA = origin.generateBlob();
        String fileContents = write();
        fileHash = FileUtil.getHash(fileContents);
        File file = new File(commitPath + "/" + fileHash);
        if (!file.exists())
            file.createNewFile();
        FileUtil.writeFile(fileContents, commitPath + "/" + fileHash);
        updateHead();
    }

    private void updateHead() throws IOException, URISyntaxException {
        if (!head.exists())
            head.createNewFile();
        FileUtil.writeFile(fileHash, "HEAD");
    }

    public String write() throws Exception {
        // initializeCommit();
        StringBuilder sb = new StringBuilder("");

        sb.append(treeSHA + "\n"); // 1st line: SHA1 of a Tree object (never can be null)
        sb.append(parentSHA + "\n"); // 2nd line: SHA1 of a file location of prev commit (can be blank)
        // File index = origin.getIndex();
        // String extSHA = this.createHash(this.readFile(index));
        sb.append("\n"); // 3rd line: SHA1 of a file location of next commit (can be blank)
        sb.append(author + "\n");// 4th line is author
        sb.append(date + "\n");// 5th line is date
        sb.append(summary); // 6th line is summary
        // initializeCommit();
        // name = createHash(sb.toString());
        return sb.toString();
        // commit = new File(name, commitPath);

        // FileWriter fw = new FileWriter(commit, true);
        // fw.append(sb.toString());
        // fw.close();
    }

    public void addCurrentCommitToPreviousCommit() throws IOException, URISyntaxException {
        // finds previous commit file from parent hash
        File previousCommit = new File(commitPath + "/" + parentSHA);
        StringBuilder prevCommitContentsWithNewLine = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(previousCommit));
        int lineNumber = 0;
        while (br.ready()) {
            lineNumber += 1;
            if (lineNumber > 1)
                prevCommitContentsWithNewLine.append("\n");
            prevCommitContentsWithNewLine.append(br.readLine());
            if (lineNumber == 3) {
                prevCommitContentsWithNewLine.append(fileHash);
            }
        }
        FileUtil.writeFile(prevCommitContentsWithNewLine.toString(), commitPath + "/" + parentSHA);
        br.close();
        // reads file
        // rewrites file with same content as before + current commit shah in third line
    }

    public String readFile(File fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder string = new StringBuilder();
        while (reader.ready()) {
            string.append((char) reader.read());
        }
        reader.close();
        return string.toString();
    }

    public String getHash() throws Exception {
        return fileHash;
    }

    public String getDate() {
        return date;
    }

    private void createDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = new Date();
        date = formatter.format(today);
    }

    public String getFileName() {
        return name;
    }

    private String createTree() throws Exception {
        origin = new Tree();
        return origin.generateBlob();
    }
}
