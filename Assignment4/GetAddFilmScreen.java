import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetAddFilmScreen {
    public static void showScreen(Stage window){


        window.getIcons().add(new Image("logo.png"));
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());

        Label label = new Label("Please give name , relative path of the trailer and duration of the film");
        Button backButton = new Button("< BACK");
        Button okButton = new Button("OK");

        HBox hbox1 = new HBox(25);
        Label name = new Label("Name:           ");
        TextField nameInput= new TextField();
        nameInput.setPromptText("Name");
        hbox1.getChildren().addAll(name,nameInput);

        HBox hbox2 = new HBox(25);
        Label trailer = new Label("Trailer(Path): ");
        TextField trailerInput= new TextField();
        trailerInput.setPromptText("Trailer");
        hbox2.getChildren().addAll(trailer,trailerInput);


        HBox hbox3 = new HBox(25);
        Label duration = new Label("Duration(m): ");
        TextField durationInput= new TextField();
        durationInput.setPromptText("Duration");
        hbox3.getChildren().addAll(duration,durationInput);


        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(0,0,0,50));
        vBox.getChildren().addAll(hbox1,hbox2,hbox3);

        Label errorLabel = new Label();

        okButton.setOnAction(event -> {
            String message= CreateExtractor.getDataExtractor().addFilms(nameInput.getText(),trailerInput.getText(),durationInput.getText());

            if(message.equals("SUCCESS: Film added successfully")){
                nameInput.setText("");
                trailerInput.setText("");
                durationInput.setText("");
            }

            errorLabel.setText(message);



        });
        backButton.setOnAction(event ->{

            GetAdminFilm.showScreen(window);
        });




        HBox hBox4 = new HBox(185);
        hBox4.getChildren().addAll(backButton,okButton);
        hBox4.setPadding(new Insets(0,0,0,50));

        HBox errorBox = new HBox();
        errorBox.setPadding(new Insets(0,0,0,50));
        errorBox.getChildren().add(errorLabel);


        GridPane gridPane= new GridPane();
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(label,0,0);
        GridPane.setConstraints(vBox,0,1);
        GridPane.setConstraints(hBox4,0,2);
        GridPane.setConstraints(errorBox,0,3);
        gridPane.getChildren().addAll(label,vBox ,hBox4,errorBox);

        Scene scene= new Scene(gridPane,500,500);
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
