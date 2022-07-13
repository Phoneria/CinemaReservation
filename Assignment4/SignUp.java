import javafx.scene.control.TextField;
/*
It returns message according to input given in signUP stage
 */
public class SignUp {
    public static String signUP(TextField username,TextField password,TextField confirmaitionPassword){

        String pass =password.getText();
        String cPass = confirmaitionPassword.getText();
        String name = username.getText();

        String message = " ";
        if((name.equals("")||name.equals(" "))){
            message = "ERROR: Username cannot be empty";ErrorSound.clicked();
        }
        else if(pass.equals("")||pass.equals(" ")){
            message = "ERROR: Password connot be empty";ErrorSound.clicked();
        }
        else if(cPass.equals("")||cPass.equals(" ")){
            message = "ERROR: Confirmation password can not be empty";
            ErrorSound.clicked();
        }
        else if(!pass.equals(cPass)){
            message = "ERROR: Passwords do not match";ErrorSound.clicked();
        }


        else if(CreateExtractor.getDataExtractor().getUsers().containsKey(name)){
            message = "ERROR: This user is already exist";ErrorSound.clicked();
        }
        else {
            message = "SUCCESS: You have succefully registered with your new credentials";
            CreateExtractor.getDataExtractor().setUsers(name,Base64Thing.hashPassword(pass));

        }

        return message;
    }
}
