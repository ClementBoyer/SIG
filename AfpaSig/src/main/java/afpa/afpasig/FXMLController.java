package afpa.afpasig;

import static afpa.afpasig.PompeSig.connect;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    private Label label;
    @FXML
    private JFXButton Connection;
    @FXML
    private Label duText;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connection(ActionEvent event) {
        String os = connect();
        duText.setText("Os détecté : " + os + "\n Driver selectionné");
    }
}
