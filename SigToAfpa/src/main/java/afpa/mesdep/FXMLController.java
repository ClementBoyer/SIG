package afpa.mesdep;

import afpa.mesdep.Model.Stagiaire;
import afpa.mesdep.Model.StagiaireDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 * Controller de la vue Scene
 * 
 * @author Caumartin François-Régis
 * @author Boyer Clément
 * @author Sipiere Germain
 */

public class FXMLController implements Initializable {
    
    @FXML
    private TableColumn nomColumn;
    @FXML
    private TableColumn prenomColumn;
    @FXML
    private TableColumn numColumn;
    @FXML
    private TableColumn adresseColumn;
    @FXML
    private TableColumn villeColumn;
    @FXML
    private TableColumn codepostalColumn;
    @FXML
    private TableColumn eligibleColumn;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private TableView listStagiaire;
    @FXML
    private ToggleButton buttonClub;
    @FXML
    private ToggleButton buttonTransition;
    
    ObservableList <Stagiaire> resultatdata;
    
    /**
     * Fonction pour avoir la date du jour
     * @return date du jour
     */
    
    public String aujourdhui() {
     return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
}
    
    /**
     * Called when the user clicks on the one button.
     * Permet de mettre à jour le champs eligible et date eligible avec l'objet RechercheSIG d'un Stagiaire.
     * 
     * @param event 
     */
    
    @FXML
    private void handleButtonOne(ActionEvent event) {
        
        StagiaireDAO sdao = new StagiaireDAO();
        // Si Stagiaire est selectionné
        if(!listStagiaire.getSelectionModel().isEmpty())
        {
            String eligible;
            Stagiaire s = resultatdata.get(listStagiaire.getSelectionModel().getSelectedIndex());
            if(s.getNumero()!=null && s.getNumero().length() !=0 && s.getAdresse() != null && s.getAdresse().length() !=0 && s.getCode_postal() != null && s.getCode_postal().length() !=0 && s.getVille() != null && s.getVille().length() !=0 )
            {
                RechercheSig sSig = new RechercheSig(s.getNumero(),s.getAdresse(),s.getCode_postal(),s.getVille());
                eligible=sSig.recherche();
                s.setEligible(eligible);
                s.setDateEligible(aujourdhui());
                if (buttonClub.isSelected())
                {
                    String site = "https://www.clubalternactifs.fr/index.php/api/update/";
                    sdao.update(s,site);
                }
                else if(buttonTransition.isSelected())
                {
                    String site = "https://www.transitionjeunes.fr/index.php/api/update/";
                    sdao.update(s,site);        
                }    
            }
            else
            {
                //Message error data
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Données non valide");
                alert.setHeaderText("Merci de corriger les informations sur la base de données");
                alert.setContentText("Probléme de lecture pour : "+s.getNom());
                alert.showAndWait();
            }
            
        }
        //Si Stagiaire n'est pas selectionné
        else
        {
            //Message error if not selection
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Problème sélection");
            alert.setContentText("Merci de sélectionner une personne ");
            alert.showAndWait();   
        }
        listStagiaire.refresh();
    }
    
    /**
     * Called when the user clicks on the all button.
     * Permet de mettre à jour le champs eligible et date eligible avec l'objet RechercheSIG de tous les Stagiaires.
     * 
     * @param event 
     */
    
    @FXML
    private void handleButtonAll(ActionEvent event) {
        StagiaireDAO sdao = new StagiaireDAO();
        String eligible;
        String errorMessage = "";  
        for (Stagiaire ss : resultatdata) 
        {
            if (ss.getEligible() == null)
            {
                if(ss.getNumero()!=null && ss.getNumero().length() !=0 && ss.getAdresse() != null && ss.getAdresse().length() !=0 && ss.getCode_postal() != null && ss.getCode_postal().length() !=0 && ss.getVille() != null && ss.getVille().length() !=0 )
                {
                    RechercheSig sSigliste = new RechercheSig(ss.getNumero(),ss.getAdresse(),ss.getCode_postal(),ss.getVille());
                    eligible=sSigliste.recherche();
                    ss.setEligible(eligible);
                    ss.setDateEligible(aujourdhui());
                    if (buttonClub.isSelected())
                    {
                        String site = "https://www.clubalternactifs.fr/index.php/api/update/";
                        sdao.update(ss,site);
                    }
                    else if(buttonTransition.isSelected())
                    {
                        String site = "https://www.transitionjeunes.fr/index.php/api/update/";
                        sdao.update(ss,site);        
                    }    
                }
                else
                {
                    errorMessage += ss.getNom()+" \n";
                }    
            }
        }
        if (errorMessage.length() != 0) 
        {
             // Message if the fields are empty
            Alert alertfields = new Alert(Alert.AlertType.ERROR);
            alertfields.setTitle("Données non valide");
            alertfields.setHeaderText("Merci de corriger les informations sur la base de données");
            
            Label label = new Label("Probléme de lecture pour : ");
            TextArea textArea = new TextArea(errorMessage);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            
            alertfields.getDialogPane().setExpandableContent(expContent);
            alertfields.showAndWait();
        }
        listStagiaire.refresh();
    }
    
    /**
     * Called when the user clicks on the Clubalternactifs button.
     * Permet de charger les données dans la tableau.
     * 
     * @param event 
     */
    
    @FXML
    private void handleButtonClub(ActionEvent event)
    {
       StagiaireDAO sdao = new StagiaireDAO();
            
        try {
            final ObservableList<Stagiaire> data = FXCollections.observableArrayList(sdao.lecture("https://www.clubalternactifs.fr/index.php/api/liste/X23k76efv"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            numColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
            adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
            codepostalColumn.setCellValueFactory(new PropertyValueFactory<>("code_postal"));
            eligibleColumn.setCellValueFactory(new PropertyValueFactory<>("eligible"));
            eligibleColumn.setCellFactory( column -> {
                return new TableCell<Stagiaire, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        if (empty) {
                            setText("");
                        }
                        else {
                            String style = "-fx-alignment: CENTER-RIGHT;";
                            setText(item);
                            if (item == null) {
                                //setTextFill(Color.RED);
                                //setStyle("-fx-background-color: yellow");
                                setText("");
                            } else if (item.equals("0")) {
                                setTextFill(Color.RED);
                                style += "-fx-font-weight: bold";
                                setText("Non");
                            } else if(item.equals("1")) {
                                setTextFill(Color.GREEN);
                                style += "-fx-font-weight: bold";
                                setText("Oui");
                            }
                            setStyle(style);
                        }
                    }
                };
            });
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateEligible"));
            listStagiaire.setItems(data); 
            resultatdata=data;   
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Called when the user clicks on the Transitionjeunes button.
     * Permet de charger les données dans la tableau.
     * 
     * @param event 
     */
    
    @FXML
    private void handleButtonTransition(ActionEvent event)
    {
       StagiaireDAO sdao = new StagiaireDAO();
            
        try {
            final ObservableList<Stagiaire> data = FXCollections.observableArrayList(sdao.lecture("https://www.transitionjeunes.fr/index.php/api/liste/X23k76efv"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            numColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
            adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            villeColumn.setCellValueFactory(new PropertyValueFactory<>("ville"));
            codepostalColumn.setCellValueFactory(new PropertyValueFactory<>("code_postal"));
            eligibleColumn.setCellValueFactory(new PropertyValueFactory<>("eligible"));
            eligibleColumn.setCellFactory( column -> {
                return new TableCell<Stagiaire, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        if (empty) {
                            setText("");
                        }
                        else {
                            String style = "-fx-alignment: CENTER-RIGHT;";
                            setText(item);
                            if (item == null) {
                                //setTextFill(Color.RED);
                                //setStyle("-fx-background-color: yellow");
                                setText("");
                            } else if (item.equals("0")) {
                                setTextFill(Color.RED);
                                style += "-fx-font-weight: bold";
                                setText("non");
                            } else if(item.equals("1")) {
                                setTextFill(Color.GREEN);
                                style += "-fx-font-weight: bold";
                                setText("oui");
                            }
                            setStyle(style);
                        }
                    }
                };
            });
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateEligible"));
            listStagiaire.setItems(data);
            resultatdata=data;
             
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
          
         
    }    
}
