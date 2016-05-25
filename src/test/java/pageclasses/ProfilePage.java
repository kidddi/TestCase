package pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by energetic on 17.05.2016.
 */
public class ProfilePage {

    WebDriver driver;
    static Logger log = Logger.getLogger(String.valueOf(ProfilePage.class));

    @FindBy(id="topLoginLink")
    WebElement loginLink;

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPass")
    WebElement userPass;

    @FindBy(id="se_userLogin")
    WebElement loginButton;

    public ProfilePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Page created");
    }

    public void clickLoginLink(){
        loginLink.click();
        log.warn("Login link is clicked");
    }

    public void setUserEmail(String destination){
        userEmail.sendKeys(destination);
    }

    public void setUserPass(String checkin) {
        userPass.sendKeys(checkin);
    }

    public void clickLiginButton(){
        loginButton.click();
        log.warn("SearchButton is clicked");
    }
    public void clearAll(){
        userEmail.clear();
        userPass.clear();
    }
}
