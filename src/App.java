import java.io.IOException;
import java.util.Hashtable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// fx:controller="MainSceneController"

//might add a spending history graph in the future

 
public class App extends Application {

    private static Stage stg;

    @Override        
    public void start(Stage primaryStage) {
        stg = primaryStage;
        Parent root;


        try {
            root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Stark Bank Simulation");
            primaryStage.setScene(scene);
            primaryStage.show();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

    
    }

    public void changeScene(String fxml) throws IOException {
            Parent pane = FXMLLoader.load(getClass().getResource(fxml));
            stg.getScene().setRoot(pane);
    }

    public void changeSceneUsername(String fxml, String uName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = loader.load();
        afterLogin controller = loader.getController();
        controller.setData(uName);
        stg.getScene().setRoot(pane);
        controller.getData();
    }

    public void changeSceneLogins(String fxml, Hashtable<String, String> logins) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = loader.load();
        createAccount controller = loader.getController();
        controller.setData(logins);
        stg.getScene().setRoot(pane);
        
    }

    public void changeSceneAccount(String fxml, String username, String password, Hashtable<String, String> logins) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = loader.load();
        accountCreation controller = loader.getController();
        controller.setData(username, password, logins);
        stg.getScene().setRoot(pane);
        
    }
 
 public static void main(String[] args) {
        launch(args);
    }
}