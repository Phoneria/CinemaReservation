import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GetRemoveHall {
    public static void showScreen(Stage window){


        ChoiceBox<String> halls =new ChoiceBox<>();
        Scene scene;
        String key = null;
        String filmName =GetAdminFilm.getNameOfTheFilm();

        window.getIcons().add(new Image("logo.png"));
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        Label label = new Label("Select the film that you desire to remove from "+ filmName +" and then click OK");
        Button backButton = new Button("< BACK");
        Button okButton = new Button("OK");



        for(ArrayList<String> s : CreateExtractor.getDataExtractor().getHalls().get(filmName)){
            halls.getItems().add(s.get(0));
        }

        if (!halls.getItems().isEmpty()) {
            key = halls.getItems().get(0);
        }
        halls.setValue(key);




        okButton.setOnAction(event -> {
            CreateExtractor.getDataExtractor().deleteHalls(filmName,halls.getValue());
            halls.getItems().remove(halls.getValue());
            halls.getSelectionModel().selectFirst();



        });
        backButton.setOnAction(event -> {

            GetAdminMedia.showScreen(window);
        });




        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(backButton,okButton);
        hBox1.setPadding(new Insets(0,0,0,0));

        HBox sectionBox = new HBox();
        sectionBox.setAlignment(Pos.CENTER);
        sectionBox.getChildren().add(halls);

        GridPane gridPane= new GridPane();
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(0,0,0,25));
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(sectionBox,0,1);
        GridPane.setConstraints(hBox1,0,2);
        gridPane.getChildren().addAll(label,sectionBox ,hBox1);

        scene= new Scene(gridPane,600,600);
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
