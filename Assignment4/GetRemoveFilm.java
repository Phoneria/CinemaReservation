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

import java.util.Optional;

public class GetRemoveFilm {
    public static void showScreen(Stage window){


        ChoiceBox<String> films =new ChoiceBox<>();
        Scene scene;
        String key= null;

        window.getIcons().add(new Image("logo.png"));
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        Label label = new Label("Select the film that you desire to remove and then click OK");
        Button backButton = new Button("< BACK");
        Button okButton = new Button("OK");



        for(String s : CreateExtractor.getDataExtractor().getFilms().keySet()){
            films.getItems().add(s);
        }
        Optional<String> firstKey = CreateExtractor.getDataExtractor().getFilms().keySet().stream().findFirst();
        if (firstKey.isPresent()) {
            key = firstKey.get();
        }
        films.setValue(key);

        okButton.setOnAction(event -> {

            CreateExtractor.getDataExtractor().deleteFilms(films.getValue());
            films.getItems().remove(films.getValue());
            films.getSelectionModel().selectFirst();




        });
        backButton.setOnAction(event -> {

            GetAdminFilm.showScreen(window);
        });




        HBox hBox1 = new HBox(10);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(backButton,okButton);
        hBox1.setPadding(new Insets(0,0,0,0));



        GridPane gridPane= new GridPane();
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(films,0,1);
        GridPane.setConstraints(hBox1,0,2);
        gridPane.getChildren().addAll(label,films ,hBox1);

        scene= new Scene(gridPane,500,500);
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
