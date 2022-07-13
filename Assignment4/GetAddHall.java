import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetAddHall {
    public static void showScreen(Stage window){


        String film = GetAdminFilm.getNameOfTheFilm();
        String duration =" ( "+CreateExtractor.getDataExtractor().getFilms().get(film).get(1) +" minutes "+" ) ";
        ChoiceBox<Integer> rowNumbers = new ChoiceBox<>() ;
        ChoiceBox<Integer> columnNumbers = new ChoiceBox<>();

        window.getIcons().add(new Image("logo.png"));

        for(int i =3;i<11;i++){
            rowNumbers.getItems().add(i);
        }
        rowNumbers.setValue(3);

        for(int i =3;i<11;i++){
            columnNumbers.getItems().add(i);
        }
        columnNumbers.setValue(3);

        Label errorLabel = new Label("");

        Label label = new Label(film+duration);



        HBox rowSection = new HBox(25);
        Label row = new Label("Row:         ");
        rowSection.getChildren().addAll(row,rowNumbers);
        rowSection.setPadding(new Insets(10,0,0,0));

        HBox columnSection = new HBox(25);
        Label column = new Label("Column:   ");
        columnSection.getChildren().addAll(column,columnNumbers);

        HBox nameSection = new HBox(25);
        Label name = new Label("Name:  ");
        TextField nameInput= new TextField();
        nameInput.setPromptText("Name");
        nameSection.getChildren().addAll(name,nameInput);

        HBox priceSection = new HBox(25);
        Label price = new Label("Price:    ");
        TextField priceInput= new TextField();
        priceInput.setPromptText("Price");
        priceSection.getChildren().addAll(price,priceInput);

        Button backButton = new Button("< Back");
        backButton.setOnAction(event -> {
            GetAdminMedia.showScreen(window);

        });

        Button okButton =new Button("OK");
        okButton.setOnAction(event -> {
            if(nameInput.getText().isEmpty()){
                ErrorSound.clicked();
                errorLabel.setText("ERROR : Hall name could not be empty!");
                nameInput.setText("");
                priceInput.setText("");

            }
            else if(priceInput.getText().isEmpty()){
                ErrorSound.clicked();
                errorLabel.setText("ERROR : Price could not be empty!");
                nameInput.setText("");
                priceInput.setText("");
            }
            else if (!IsThisInteger.isInt(priceInput.getText())){ErrorSound.clicked();
                errorLabel.setText("ERROR : Price must be integer");
                nameInput.setText("");
                priceInput.setText("");
            }
            else {
                errorLabel.setText("SUCCESS : Hall successfully created!");
                CreateExtractor.getDataExtractor().addHall(film,nameInput.getText(),priceInput.getText(), String.valueOf(rowNumbers.getValue()), String.valueOf(columnNumbers.getValue()));
                nameInput.setText("");
                priceInput.setText("");
            }




        });


        HBox botSection = new HBox(150);
        botSection.getChildren().addAll(backButton,okButton);



        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label,rowSection,columnSection,nameSection,priceSection,botSection);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(label,0, 0);
        GridPane.setConstraints(vBox,0,1);
        GridPane.setConstraints(errorLabel,0,2);
        gridPane.getChildren().addAll(label,vBox,errorLabel);

        Scene scene= new Scene(gridPane,500,500);
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
