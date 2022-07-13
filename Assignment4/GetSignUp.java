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

public class GetSignUp {
    public static void showScreen(Stage window){


        Label errorLabel = new Label("");
        Label label = new Label("    Welcome to the HUCS Cinema Resevation System!\n  Please enter your credentials below and click LOG IN\n" +
                "You can create new account by clicking SIGN UP button");

        HBox hbox1 = new HBox(25);
        Label username = new Label("Username: ");
        TextField nameInput= new TextField();
        nameInput.setPromptText("Username");
        hbox1.getChildren().addAll(username,nameInput);

        HBox hbox2 = new HBox(25);
        Label password = new Label("Password: ");
        TextField passwordInput= new TextField();
        passwordInput.setPromptText("Password");
        hbox2.getChildren().addAll(password,passwordInput);


        HBox hbox4 = new HBox(25);
        Label confirmationPassword = new Label("Password: ");
        TextField confirmaationPasswordInput= new TextField();
        confirmaationPasswordInput.setPromptText("Confirmation Password");
        hbox4.getChildren().addAll(confirmationPassword,confirmaationPasswordInput);



        Button signUpButton = new Button("SIGN UP");
        signUpButton.setOnAction(event -> {

            String message = SignUp.signUP(nameInput,passwordInput,confirmaationPasswordInput);

            if (message.equals(" ")){
                errorLabel.setText("You can pass");
            }
            else {

                errorLabel.setText(message);
                nameInput.setText("");
                passwordInput.setText("");
                confirmaationPasswordInput.setText("");
            }


        });

        Button logInButton =new Button("LOG IN");
        logInButton.setOnAction(event ->{
            GetLogIn.showScreen(window);
        });

        HBox hbox3 = new HBox(150);
        hbox3.getChildren().addAll(logInButton,signUpButton);



        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(50,50,50,50));
        vBox.getChildren().addAll(hbox1,hbox2,hbox4,hbox3);

        GridPane gridPane = new GridPane();


        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(label,0, 0);
        GridPane.setConstraints(vBox,0,1);
        GridPane.setConstraints(errorLabel,0,2);
        gridPane.getChildren().addAll(label,vBox,errorLabel);

        Scene scene = new Scene(gridPane,500,500);
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        window.getIcons().add(new Image("logo.png"));
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
