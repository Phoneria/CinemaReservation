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

public class GetLogIn {


    static String name="";
    static Boolean isAdmin=false;
    static Boolean isUserExist = false;// If user exist it will continue. If it is not, there will be error in label
    static Scene loginPageScene;
    static GridPane gridPane;



    public static void setName(String name) {
        GetLogIn.name = name;
    }
    public static void setAdmin(Boolean admin) {
        isAdmin = admin;
    }



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

        Button changePass  = new Button("Change Password");
        changePass.setVisible(false);
        changePass.setOnAction(event -> {
            GetChangePassword.showScreen(window);
        });


        Button signUpButton = new Button("SIGN UP");
        signUpButton.setOnAction(event -> {

            GetSignUp.showScreen(window);
        });

        Button logInButton =new Button("LOG IN");
        logInButton.setOnAction(event -> {
            isUserExist = LogIn.logIn(nameInput.getText(),Base64Thing.hashPassword(passwordInput.getText()));


            if(isUserExist){

                setName(nameInput.getText());



                if(CreateExtractor.getDataExtractor().getUsers().get(name).get(2).equals("true")){
                    setAdmin(true);
                }
                errorLabel.setText("            Logging In");
                setName(nameInput.getText());

                if(IsAdmin.isAdmin(nameInput.getText()).equals("user")){
                    GetNormalFilm.showScreen(window);
                }
                else if(IsAdmin.isAdmin(nameInput.getText()).equals("admin")){
                    GetAdminFilm.showScreen(window);
                }
                else {
                    System.out.println("No information");
                }
            }
            else {
                ErrorSound.clicked();
                changePass.setVisible(true);
                errorLabel.setText("            ERROR: There is no such credential");
                nameInput.setText("");
                passwordInput.setText("");

            }




        });



        HBox hbox3 = new HBox(150);
        hbox3.getChildren().addAll(signUpButton,logInButton);



        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(50,50,50,50));
        vBox.getChildren().addAll(hbox1,hbox2,hbox3);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setConstraints(label,0, 0);
        GridPane.setConstraints(vBox,0,1);
        GridPane.setConstraints(errorLabel,0,2);
        GridPane.setConstraints(changePass,0,3);
        gridPane.getChildren().addAll(label,vBox,errorLabel,changePass);

        loginPageScene = new Scene(gridPane,500,500);
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        window.getIcons().add(new Image("logo.png"));
        window.setScene(loginPageScene);
        window.show();
        BackUpWriter.showScreen(window);
    }
}
