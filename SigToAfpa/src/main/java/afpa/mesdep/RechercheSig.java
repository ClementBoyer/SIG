package afpa.mesdep;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Class Recherche Sig
 * Permet d'effectuer la recherche sur le site SIG.
 * 
 * @author Caumartin François-Régis
 * @author Boyer Clément
 * @author Sipiere Germain
 */
public class RechercheSig 
{
    private String numero;
    private String adresse;
    private String codepostal;
    private String ville;
    private String eligible;

    public String getEligible() {
        return eligible;
    }
    
    /**
     * Constructor with 4 @param for SIG website
     * 
     * @param Numero
     * @param Adresse
     * @param Codepostal
     * @param Ville 
     */
    
    public RechercheSig(String Numero,String Adresse,String Codepostal,String Ville) 
    {
        this.numero=Numero;
        this.adresse=Adresse;
        this.codepostal=Codepostal;
        this.ville=Ville;
    }
    
    public String recherche()
    {
        String eligible;
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
        codePostal.sendKeys(codepostal);
        nomCommune.sendKeys(ville);
        numAdresse.sendKeys(numero);
        nomVoie.sendKeys(adresse);

        // Now submit the form. WebDriver will find the form for us from the element
        rechercher.submit();
        
        //recherche des sorties
        
        boolean flag= false;
        try {
            WebElement reponseRed = driver.findElement(By.className("red"));
            flag = false;
        }
        catch (Exception eRed)
        {   
        }
        try {
            WebElement reponseGreen = driver.findElement(By.className("green"));
            flag = true;
        }
        catch (Exception eGreen)
        {
        }
        if(flag)
        {
            eligible = "1";
        }
        else
        {
            eligible = "0";  
        }
        
        //Close the browser
        driver.quit();
        
        return eligible;
    }
    
}
