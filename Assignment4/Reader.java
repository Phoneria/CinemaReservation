import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Reader {
    //It reads file and convert them an array

    private String[] readFile;

    public String[] getReadFile() {
        return readFile;
    }

    public void setReadFile(String[] readFile) {
        this.readFile = readFile;
    }

    public Reader(String path) {
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();

            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))){
                results[i++] = line;
            }
            setReadFile(results);
        } catch (IOException e) {
            e.printStackTrace();
            setReadFile(null);
        }
    }// It reads file and put elements into the readlie array
}
