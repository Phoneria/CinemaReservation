import java.io.FileWriter;
import java.io.IOException;


public class Writer {
    // It writes file to the path txt
    private FileWriter fileWriter;

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public Writer(String path){

        try{
            fileWriter = new FileWriter(path);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
