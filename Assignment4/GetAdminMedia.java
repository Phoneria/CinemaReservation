import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;


import java.io.File;
import java.util.ArrayList;


public class GetAdminMedia {
    static String chosenHall = " ";
    static Integer row,column,price = 0;

    public static void showScreen(Stage window){

        String film = GetAdminFilm.nameOfTheFilm;
        String duration =" ( "+CreateExtractor.getDataExtractor().getFilms().get(film).get(1) +" minutes "+" ) ";
        Scene scene;
        ChoiceBox<String> halls =new ChoiceBox<>();
        String key =" ";
        if(CreateExtractor.getDataExtractor().getHalls().containsKey(film)){
            for(ArrayList<String> s : CreateExtractor.getDataExtractor().getHalls().get(film)){
                halls.getItems().add(s.get(0));
            }
        }
        if(!halls.getItems().isEmpty()){
            key = halls.getItems().get(0);
        }

        halls.setValue(key);

        window.getIcons().add(new Image("logo.png"));

        Label label = new Label(film + duration);
        label.setAlignment(Pos.CENTER);

        String path = CreateExtractor.getDataExtractor().getPathsWay().get(CreateExtractor.getDataExtractor().getFilms().get(film).get(0));
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        MediaView viewer = new MediaView(player);

        Button playStopButton = new Button(">");playStopButton.setMinWidth(50);
        Button goBackButton = new Button("<<");goBackButton.setMinWidth(50);
        Button goForwardButton = new Button(">>");goForwardButton.setMinWidth(50);
        Button toTheStartButton = new Button("|<<");toTheStartButton.setMinWidth(50);
        Button backButton = new Button("< Back");backButton.setMinWidth(50);
        Button addHallButton = new Button("Add Hall");addHallButton.setMinWidth(50);
        Button removeHallButton = new Button("Remove Hall");removeHallButton.setMinWidth(50);
        Button okButton = new Button("Ok");okButton.setMinWidth(50);

        Slider volume = new Slider(0,100,50);
        volume.setShowTickMarks(true);
        volume.setShowTickLabels(true);
        volume.setSnapToTicks(true);
        volume.setOrientation(Orientation.VERTICAL);
        volume.setValue(50);
        volume.setPrefWidth(150);
        volume.setMinWidth(30);
        volume.setMaxWidth(Region.USE_PREF_SIZE);
        player.volumeProperty().bind(volume.valueProperty().divide(100));

        VBox rightButtons = new VBox(10);
        rightButtons.getChildren().addAll(playStopButton,goBackButton ,goForwardButton,toTheStartButton,volume);
        rightButtons.setAlignment(Pos.CENTER);
        rightButtons.setPadding(new Insets(30,30,30,30));


        HBox bottomButtons = new HBox(30);
        bottomButtons.setPadding(new Insets(50,50,50,50));
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.getChildren().addAll(backButton, addHallButton, removeHallButton,halls, okButton);

        Label errorLabel = new Label();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(errorLabel);

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(bottomButtons,hBox);

        VBox labelPlusViewer = new VBox();
        labelPlusViewer.getChildren().addAll(label,viewer);
        labelPlusViewer.setAlignment(Pos.CENTER);



        BorderPane pane = new BorderPane();
        pane.setRight(rightButtons);
        pane.setCenter(labelPlusViewer);
        pane.setBottom(vBox);




        goBackButton.setOnAction(event -> {
            Duration time =player.currentTimeProperty().getValue();
            player.seek(Duration.millis(time.toMillis()-5000));

        });

        goForwardButton.setOnAction(event -> {
            Duration time =player.currentTimeProperty().getValue();
            player.seek(Duration.millis(time.toMillis()+5000));

        });

        playStopButton.setOnAction(event -> {
            if(playStopButton.getText().equals(">")){
                player.play();
                playStopButton.setText("||");

            }
            else {
                player.pause();
                playStopButton.setText(">");
            }
        });

        toTheStartButton.setOnAction(event -> {
            player.seek(Duration.seconds(0.0));
        });

        okButton.setOnAction(event -> {
            if(CreateExtractor.getDataExtractor().getHalls().get(film).size()!=0){
                player.stop();
                chosenHall = halls.getValue();
                price = Integer.valueOf(getFutures(chosenHall).get(1));
                row = Integer.valueOf(getFutures(chosenHall).get(2));
                column = Integer.valueOf(getFutures(chosenHall).get(3));

                GetAdminHall.showScreen(window);
            }
            else{
                errorLabel.setText("I am sorry!!! There is no hall for this movie");
            }

        });
        backButton.setOnAction(event -> {
            player.stop();
            GetAdminFilm.showScreen(window);

        });
        addHallButton.setOnAction(event -> {player.stop();
            GetAddHall.showScreen(window);
        });
        removeHallButton.setOnAction(event -> {player.stop();
            GetRemoveHall.showScreen(window);
        });



        scene = new Scene(pane, 1000, 1000);
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
    public static ArrayList<String> getFutures(String nameOfTheHall){
        ArrayList<ArrayList<String>> filmInfo = CreateExtractor.getDataExtractor().getHalls().get(GetAdminFilm.nameOfTheFilm);
        for(int i = 0;i<filmInfo.size();i++){
            if (filmInfo.get(i).get(0).equals(nameOfTheHall)) {
                return filmInfo.get(i) ;
            }
        }
        return null;
    }
}
