package afpa.mesdep.Model;

import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Class StagiaireDAO
 * Permet de gérer les actions avec la BDD
 * 
 * @author Caumartin François-Régis
 * @author Boyer Clément
 * @author Sipiere Germain
 */

public class StagiaireDAO 
{
    /**
     * Fonction lecture
     * Permet de faire une liste des stagiaires
     * 
     * @param site
     * @return List Stagiaire
     * @throws IOException 
     */
    
    public List<Stagiaire> lecture (String site) throws IOException
    {
        String urlString = site;
        BufferedReader reader = null;
        List<Stagiaire> res = new ArrayList<Stagiaire>();
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            buffer.toString();
            Gson gson = new Gson();
            Stagiaire [] liste = gson.fromJson(buffer.toString(), Stagiaire[].class);
            for (Stagiaire jj : liste) 
            {
                res.add(jj);
            }
        } catch (Exception er) {

            System.out.println("ARGHHH !!!");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return res;
    }
    
    public void update(Stagiaire stagiaire,String url)
    {
        String eligible = stagiaire.getEligible();
        String id = stagiaire.getId();
        
        //connect();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Déclaration de la page à consulter
        driver.get(url+"/X23k76efv/"+id+"/"+eligible);
        
         //Close the browser
        driver.quit();
    }
    
    
     
    
}
