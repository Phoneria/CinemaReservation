import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GetChangePassword {

    public static boolean isAdminTrue = false;
    public static boolean isMemberTrue = false;
    public static void showScreen(Stage window){
        HBox hbox1 = new HBox(25);
        Label username = new Label("What is your username:");
        TextField nameInput= new TextField();
        nameInput.setPromptText("Username");
        hbox1.getChildren().addAll(username,nameInput);

        HBox hbox2 = new HBox(50);
        Label admin = new Label("Are you an admin ?:");
        TextField adminInput= new TextField();
        adminInput.setPromptText("true/false");
        hbox2.getChildren().addAll(admin,adminInput);

        HBox hbox3 = new HBox(50);
        Label member = new Label("Are you a member :");
        TextField memberInput= new TextField();
        memberInput.setPromptText("true/false");
        hbox3.getChildren().addAll(member,memberInput);

        HBox hbox4 = new HBox(115);
        Label password = new Label("Password:");
        TextField passwordInput= new TextField();
        passwordInput.setPromptText("Password");
        hbox4.getChildren().addAll(password,passwordInput);


        Button okButton = new Button("OK");
        Button backButton = new Button("< Back");
        Label errorLabel = new Label();


        okButton.setOnAction(event -> {

            boolean isUserExist = LogIn.change(nameInput.getText());



            if(isUserExist) {
                if(passwordInput.getText().isEmpty()){
                    errorLabel.setText("ERROR: Password cannot be empty");
                    ErrorSound.clicked();
                }

                if(memberInput.getText().isEmpty()){
                    errorLabel.setText("ERROR: Member input cannot be empty");ErrorSound.clicked();
                }
                if(adminInput.getText().isEmpty()){
                    errorLabel.setText("ERROR: Admin input cannot be empty");ErrorSound.clicked();
                }


                if(!adminInput.getText().isEmpty()&& !memberInput.getText().isEmpty() && !passwordInput.getText().isEmpty() ){
                    if (CreateExtractor.getDataExtractor().getUsers().get(nameInput.getText()).get(1).equals(adminInput.getText())) {
                        isAdminTrue = true;
                    }
                    if (CreateExtractor.getDataExtractor().getUsers().get(nameInput.getText()).get(2).equals(memberInput.getText())) {
                        isMemberTrue = true;
                    }
                    if (isAdminTrue && isMemberTrue) {

                        CreateExtractor.getDataExtractor().getUsers().get(nameInput.getText()).set(0,Base64Thing.hashPassword(passwordInput.getText()));
                        errorLabel.setText("Password succesfully changed!!!");

                        adminInput.setText("");
                        memberInput.setText("");
                        nameInput.setText("");
                        passwordInput.setText("");

                    }
                    if(!isAdminTrue ){
                        errorLabel.setText("ERROR: Admin input is wrong");
                    }
                    if(!isMemberTrue ){
                        errorLabel.setText("ERROR: Member input is wrong");
                    }

                    else {
                        adminInput.setText("");
                        memberInput.setText("");
                        nameInput.setText("");
                        passwordInput.setText("");
                        errorLabel.setText("Your admin or member input is wrong");
                    }
                }
            }else {
                errorLabel.setText("There is no such user");
            }
        });

        backButton.setOnAction(event -> {
            GetLogIn.showScreen(window);
        });

        HBox buttons = new HBox(250);
        buttons.getChildren().addAll(backButton,okButton);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(hbox1,0, 0);
        GridPane.setConstraints(hbox2,0,1);
        GridPane.setConstraints(hbox3,0,2);
        GridPane.setConstraints(hbox4,0,3);
        GridPane.setConstraints(errorLabel,0,4);
        GridPane.setConstraints(buttons,0,5);
        gridPane.getChildren().addAll(hbox1,hbox2,hbox3,hbox4,errorLabel,buttons);

        Scene scene = new Scene(gridPane,500,500);
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        window.getIcons().add(new Image("logo.png"));
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
