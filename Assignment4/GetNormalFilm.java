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

public class GetNormalFilm {

    static String nameOfTheFilm = " ";

    public static String getNameOfTheFilm() {
        return nameOfTheFilm;
    }

    public static void setNameOfTheFilm(String nameOfTheFilm) {
        GetNormalFilm.nameOfTheFilm = nameOfTheFilm;
    }

    public static void showScreen(Stage window){
        String userName= GetLogIn.name;

        ChoiceBox<String> films =new ChoiceBox<>();
        Scene scene;
        String key =" ";
        String message = "";
        if(CreateExtractor.getDataExtractor().getUsers().get(userName).get(1).equals("true")){
            message = "(Club Member)!";
        }
        window.getIcons().add(new Image("logo.png"));
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        Label label = new Label("                            Welcome "+ userName+message+"\n         Select a film and then click OK to continue");
        Button logOutButton = new Button("LOG OUT");
        Button okButton = new Button("OK");


        for(String s : CreateExtractor.getDataExtractor().getFilms().keySet()){
            films.getItems().add(s);
        }
        Optional<String> firstKey = CreateExtractor.getDataExtractor().getFilms().keySet().stream().findFirst();
        if (firstKey.isPresent()) {
            key = firstKey.get();
        }
        films.setValue(key);

        Label errorLabel = new Label();
        okButton.setOnAction(event -> {
            if(CreateExtractor.getDataExtractor().getFilms().isEmpty()){
                errorLabel.setText("There are no films in this cinema");
            }
            else {
                setNameOfTheFilm(films.getValue());// It changes film according to chosen film
                GetNormalMedia.showScreen(window);
            }

        });
        logOutButton.setOnAction(event -> {
            GetLogIn.showScreen(window);
        });


        HBox hBox =new HBox(10);
        hBox.getChildren().addAll(films,okButton);

        HBox hBox1 = new HBox(300);
        Label label1 = new Label(" ");
        hBox1.getChildren().addAll(label1,logOutButton);

        GridPane gridPane= new GridPane();
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(hBox,0,1);
        GridPane.setConstraints(hBox1,0,2);
        GridPane.setConstraints(errorLabel,0,4);

        gridPane.getChildren().addAll(label,hBox,hBox1,errorLabel);

        scene= new Scene(gridPane,500,500);
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
