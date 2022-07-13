import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetNormalHall {
    public static void showScreen(Stage window){
        Integer row =GetNormalMedia.row;
        Integer column =GetNormalMedia.column;
        String filmName = GetNormalFilm.getNameOfTheFilm();
        String chosenHall = GetNormalMedia.chosenHall + " ";
        String filmDuration = " ( " +CreateExtractor.getDataExtractor().getFilms().get(filmName).get(1) +" minutes "+" ) ";
        Integer price = GetNormalMedia.price;
        Integer chosenRow=0;
        Integer chosenColumn=0;



        Label topString = new Label(chosenHall + filmName + filmDuration);

        Hall[][] hall =  new Hall[row][column];
        Button button ;

        window.getIcons().add(new Image("logo.png"));
        topString.setPadding(new Insets(0,0,0,column*37));


        button = new Button("< Back");
        button.setOnAction(event -> {
            GetNormalMedia.showScreen(window);
        });

        Label boughtLabel = new Label("Each seat is " + price + " TL for nonmembers " + price*(100-CreateExtractor.getDataExtractor().getDiscount())/100 + " TL for members" );

        boughtLabel.setPadding(new Insets(0,0,0,column*37));

        GridPane pane = new GridPane();

        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++){
                pane.add(hall[i][j] = new Hall(), j, i);
            }
        }


        VBox allBoxes = new VBox();
        allBoxes.getChildren().addAll(topString,pane,boughtLabel,button);

        Scene scene = new Scene(allBoxes,(column*100)+100,(row*100)+100);// It adjusting dimensions according to rows and columns
        window.setScene(scene);
        window.show();

        BackUpWriter.showScreen(window);
    }
    public static class Hall extends Pane {
        public Hall(){
            ImageView logo;

            this.setPrefSize(500, 500);
            logo = new ImageView("empty_seat.png");
            logo.setFitWidth(75);
            logo.setFitHeight(75);
            this.setOnMouseClicked(event -> System.out.println("Clicked"));
            this.getChildren().add(new Button("",logo));

        }
        private void handleMouseClick() {
            ImageView logo;
            logo = new ImageView("reserved_seat.png");
        }

    }
}
