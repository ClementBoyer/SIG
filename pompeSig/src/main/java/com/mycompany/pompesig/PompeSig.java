/*
 *  !!!   IT WORKS     !!!!
 * !!!! On my machine  !!!!
 */

package com.mycompany.pompesig;

/**
 * Créé le 17 oct. 2018 à 14:00:48
 * @author Germain SIPIERE
 *         Clément BOYER
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
public class PompeSig {

    /**
     * @param args the command line arguments
     */
    
    // TODO mettre cette fonction dans un objet
    public static void connect(){
        // RECUPERATION de L'OS
        System.out.println(System.getProperty("os.name"));
        String os =System.getProperty("os.name");
        
        // Indique le chemin pour le chromedriver
        // en fonction de l'os
        if (os.equalsIgnoreCase("mac os x")){
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        } else if (os.equalsIgnoreCase("windows 10")){
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
    }
    
    public static void main(String[] args) {
        
        
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
