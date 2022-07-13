import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class BackUpWriter {
    public static void backUpWriter() {
        Writer writer = new Writer("backup.dat");
        PrintWriter printWriter= new PrintWriter(writer.getFileWriter());

        for(String s : CreateExtractor.getDataExtractor().getUsers().keySet()){
            printWriter.println("user" + "\t" + s + "\t" + CreateExtractor.getDataExtractor().getUsers().get(s).get(0) + "\t" + CreateExtractor.getDataExtractor().getUsers().get(s).get(1) + "\t" + CreateExtractor.getDataExtractor().getUsers().get(s).get(2));
        }
        for(String s: CreateExtractor.getDataExtractor().getFilms().keySet()){
            printWriter.println("film" + "\t" + s + "\t" + CreateExtractor.getDataExtractor().getFilms().get(s).get(0) + "\t" + CreateExtractor.getDataExtractor().getFilms().get(s).get(1) );
        }
        for(String s: CreateExtractor.getDataExtractor().getHalls().keySet()){
            for(int i = 0;i<CreateExtractor.getDataExtractor().getHalls().get(s).size();i++){
                printWriter.println("hall" + "\t" + s + "\t" + CreateExtractor.getDataExtractor().getHalls().get(s).get(i).get(0) + "\t" + CreateExtractor.getDataExtractor().getHalls().get(s).get(i).get(1) + "\t" + CreateExtractor.getDataExtractor().getHalls().get(s).get(i).get(2)+ "\t" + CreateExtractor.getDataExtractor().getHalls().get(s).get(i).get(3));

            }
        }

        for(HashMap<String, ArrayList<String>> s : CreateExtractor.getDataExtractor().getSeatsList()){
            for(String key: CreateExtractor.getDataExtractor().getFilms().keySet()){
                if(s.containsKey(key)){
                    printWriter.println("seat"+ "\t"+ key +"\t"+s.get(key).get(0)+"\t"+  s.get(key).get(1)+"\t"+  s.get(key).get(2)+"\t"+  s.get(key).get(3)+"\t"+  s.get(key).get(4));
                }
            }

        }


        try {
            writer.getFileWriter().close();
        } catch (IOException e) {
            e.printStackTrace();

        }



    }
    public static void showScreen(Stage window){
        window.setOnCloseRequest(event ->{
            Boolean answer= ConfirmBox.display();
            if(answer){
                backUpWriter();
                window.close();
            }
        } );

        window.show();

    }

}
