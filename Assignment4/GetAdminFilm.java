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

public class GetAdminFilm {
    static String nameOfTheFilm = " ";

    public static String getNameOfTheFilm() {
        return nameOfTheFilm;
    }

    public static void setNameOfTheFilm(String name) {
        nameOfTheFilm = name;
    }

    public static void showScreen(Stage window){
        String userName=GetLogIn.name;

        ChoiceBox<String> films =new ChoiceBox<>();

        String key = null;


        window.getIcons().add(new Image("logo.png"));

        String message = "(Admin)";

        if(CreateExtractor.getDataExtractor().getUsers().get(userName).get(2).equals("true")){
            message = "(Admin-Club Member)";
        }


        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        Label label = new Label("                   Welcome "+ userName+ message +"!"+"\n             You can either select film below or do edits.");
        Button logOutButton = new Button("LOG OUT");
        Button okButton = new Button("OK");
        Button addFilmButton = new Button("Add Film");
        Button removeFilmButton = new Button("Remove Film");
        Button editUsers = new Button("Edit Users");

        HBox adminButtonsBox = new HBox(10);
        adminButtonsBox.setPadding(new Insets(0,40,0,40));
        adminButtonsBox.getChildren().addAll(addFilmButton,removeFilmButton,editUsers);

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
            GetAdminMedia.showScreen(window);
            }
        });
        logOutButton.setOnAction(event -> {
            GetLogIn.showScreen(window);
        });
        addFilmButton.setOnAction(event -> {
            GetAddFilmScreen.showScreen(window);
        });
        removeFilmButton.setOnAction(event -> {
            GetRemoveFilm.showScreen(window);
        });
        editUsers.setOnAction(event -> {
            GetEditUsers.showScreen(window);
        });

        HBox hBox =new HBox(10);
        hBox.getChildren().addAll(films,okButton);

        HBox hBox1 = new HBox(300);
        Label label1 = new Label(" ");
        hBox1.getChildren().addAll(label1,logOutButton);

        GridPane gridPane= new GridPane();
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(hBox,0,1);
        GridPane.setConstraints(adminButtonsBox,0,2);
        GridPane.setConstraints(hBox1,0,3);
        GridPane.setConstraints(errorLabel,0,4);




        gridPane.getChildren().addAll(label,hBox,adminButtonsBox ,hBox1,errorLabel);

        Scene scene= new Scene(gridPane,500,500);
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }

}
