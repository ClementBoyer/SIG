package afpa.afpasig;

import static afpa.afpasig.PompeSig.connect;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Sig vers Afpa");
        stage.setScene(scene);
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
        
        
        connect();
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




        System.out.println(reponse.isDisplayed());
        
        
        //Close the browser
        driver.quit();
    }

}
