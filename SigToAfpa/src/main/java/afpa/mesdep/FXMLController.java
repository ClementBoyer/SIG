package afpa.mesdep;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FXMLController implements Initializable {
    
    @FXML
    private Button button;
    @FXML
    private Label affiche;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("    _________            _ __    __   ___ \n" +
                           "   / ____/ (_)___ ____  (_) /_  / /__/__ \\\n" +
                           "  / __/ / / / __ `/ _ \\/ / __ \\/ / _ \\/ _/\n" +
                           " / /___/ / / /_/ /  __/ / /_/ / /  __/_/  \n" +
                           "/_____/_/_/\\__, /\\___/_/_.___/_/\\___(_)   \n" +
                           "          /____/  ____  ___   _____       \n" +
                           "  ____  __  __   / __ \\/   | / ___/       \n" +
                           " / __ \\/ / / /  / /_/ / /| | \\__ \\        \n" +
                           "/ /_/ / /_/ /  / ____/ ___ |___/ /        \n" +
                           "\\____/\\__,_/  /_/   /_/  |_/____/         \n" +
                           "                                          ");
        
        
        //connect();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Déclaration de la page à consulter
        driver.get("https://sig.ville.gouv.fr/adresses/recherche");
        

        // Find the text input element by its name
        WebElement codePostal = driver.findElement(By.id("code_postal"));
        WebElement nomCommune = driver.findElement(By.id("nom_commune"));
        WebElement numAdresse = driver.findElement(By.id("num_adresse"));
        WebElement nomVoie = driver.findElement(By.id("nom_voie"));
        WebElement rechercher = driver.findElement(By.id("search_zus_form"));
        

        // Enter something to search for
        codePostal.sendKeys("80000");
        nomCommune.sendKeys("Amiens");
        numAdresse.sendKeys("3");
        nomVoie.sendKeys("gambetta");

        // Now submit the form. WebDriver will find the form for us from the element
        rechercher.submit();
        
        //recherche des sorties
        WebElement reponse = driver.findElement(By.className("red"));




        if (reponse.isDisplayed())
        affiche.setText("Cest OK!!!");
        
        
        //Close the browser
        driver.quit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
