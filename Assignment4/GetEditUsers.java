import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GetEditUsers {

    static TableView<EditUserClass>table;

    static String name = GetLogIn.name;
    public static void showScreen(Stage window){
        window.getIcons().add(new Image("logo.png"));
        window.setTitle(CreateExtractor.getDataExtractor().getTitle());
        Button backButton = new Button("< Back");
        Button memberButton = new Button("Promote/Demote Club Member");
        Button adminButton = new Button("Promote/Demote Admin");

        backButton.setOnAction(event -> {
            GetAdminFilm.showScreen(window);


        });
        memberButton.setOnAction(event -> {


            String userName =table.getSelectionModel().getSelectedItems().get(0).getName();
            EditUserClass.setMember(userName);
            deleteAndAddButton(userName);



        });
        adminButton.setOnAction(event ->{

            String userName =table.getSelectionModel().getSelectedItems().get(0).getName();
            EditUserClass.setAdmin(userName);
            deleteAndAddButton(userName);
        });

        HBox hBox= new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(0,0,0,0));
        hBox.getChildren().addAll(backButton,memberButton,adminButton);

        TableColumn<EditUserClass,String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(200);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<EditUserClass,String> clubMemberColumn = new TableColumn<>("Club Member");
        clubMemberColumn.setMinWidth(200);
        clubMemberColumn.setCellValueFactory(new PropertyValueFactory<>("member"));


        TableColumn<EditUserClass,String> adminColumn = new TableColumn<>("Admin");
        adminColumn.setMinWidth(200);
        adminColumn.setCellValueFactory(new PropertyValueFactory<>("admin"));


        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(usernameColumn,clubMemberColumn,adminColumn);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(table,hBox);

        Scene scene= new Scene(vBox,750,750);

        Label label = new Label("No user available in the database!");
        GridPane pane = new GridPane();
        pane.getChildren().addAll(label);
        pane.setAlignment(Pos.CENTER);

        if(CreateExtractor.getDataExtractor().getUsers().size()<2){
            scene = new Scene(pane,750,750);
        }

        window.setScene(scene);
        window.show();
        BackUpWriter.showScreen(window);
    }
    public static ObservableList<EditUserClass> getProduct(){

        ArrayList<String> userNames = new ArrayList<>();
        ArrayList<ArrayList<String>> futures = new ArrayList<>();
        ArrayList<String> valuesOfUser = CreateExtractor.getDataExtractor().getUsers().get(name);
        for(String s : CreateExtractor.getDataExtractor().getUsers().keySet()){
            if(s.equals(name)){
                continue;
            }
            userNames.add(s);
        }
        for(ArrayList<String> s : CreateExtractor.getDataExtractor().getUsers().values()){
            if(s.equals(valuesOfUser)){
                continue;
            }
            futures.add(s);
        }



        ObservableList<EditUserClass> products = FXCollections.observableArrayList();
        for(int i = 0;i<userNames.size();i++){
            products.add(new EditUserClass(userNames.get(i),futures.get(i).get(1),futures.get(i).get(2)));
        }


        return products;
    }
    public static void deleteAndAddButton(String name){
        ObservableList<EditUserClass> productSelected,allProducts;
        allProducts=table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        productSelected.forEach(allProducts::remove);


        EditUserClass newProduct = new EditUserClass(name,CreateExtractor.getDataExtractor().getUsers().get(name).get(1),CreateExtractor.getDataExtractor().getUsers().get(name).get(2));
        table.getItems().add(0,newProduct);
    }

}
