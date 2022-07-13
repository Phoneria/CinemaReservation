import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.awt.*;
import java.util.Collection;
import javafx.application.Application;
import javafx.stage.Stage;

public class ConfirmBox {
    static boolean answer;


    public static boolean display(){
        Stage window  = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);// We are gonna block other windows until it has closed
        window.setTitle("Save Changes");
        window.setMinWidth(250);
        Label label = new Label("Do you want to save changes ? ");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(event -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(event -> {
            answer = false;
            window.close();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.getIcons().add(new Image("logo.png"));
        window.showAndWait();
        return answer;
        }
}

