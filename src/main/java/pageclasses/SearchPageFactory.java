package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by energetic on 17.05.2016.
 */
public class SearchPageFactory {

    WebDriver driver;

    @FindBy(id="tab-hotel-tab")
    WebElement hotelTab;

    @FindBy(id="hotel-destination")
    WebElement hotelDestination;

    @FindBy(id="hotel-checkin")
    WebElement hotelCheckin;

    @FindBy(id="hotel-checkout")
    WebElement hotelCheckout;

    @FindBy(id="search-button")
    WebElement searchButton;

    public SearchPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickHotelTab(){
        driver.findElement(By.id("tab-hotel-tab")).click();
        hotelTab.click();
    }

    public void setHotelDestination(String destination){
        hotelDestination.sendKeys(destination);
    }

    public void setHotelCheckin(String checkin) {
        hotelCheckin.sendKeys(checkin);
    }

    public void setHotelCheckout(String checkout) {
        hotelCheckout.sendKeys(checkout);
    }

    public void clickSearchButton(){
        searchButton.click();;
    }
}
