package todosfx;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Todos application.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class TodosFX extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane page = (AnchorPane) FXMLLoader.load(TodosFX.class.getResource("TodosFX.fxml"));
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Todos");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(TodosFX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
