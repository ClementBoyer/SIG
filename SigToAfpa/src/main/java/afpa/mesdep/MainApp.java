package afpa.mesdep;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
                
        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Sig validation");
        stage.setScene(scene);
        stage.setMinHeight(808.00);
        stage.setMaxHeight(808.00);
        stage.setMinWidth(1039.00);
        stage.setMaxWidth(1039.00);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
