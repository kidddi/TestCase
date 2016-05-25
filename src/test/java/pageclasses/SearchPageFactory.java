package pageclasses;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.apache.log4j.Logger;

/**
 * Created by energetic on 17.05.2016.
 */
public class SearchPageFactory {

    WebDriver driver;
    static Logger log = Logger.getLogger(String.valueOf(SearchPageFactory.class));

    @FindBy(id="tab-hotel-tab")
    WebElement hotelTab;

    @FindBy(id="hotel-destination")
    WebElement hotelDestination;

    @FindBy(id="hotel-checkin")
    WebElement hotelCheckin;

    @FindBy(id="hotel-checkout")
    WebElement hotelCheckout;

    @FindBy(id="hotel-add-flight-checkbox")
    WebElement addFlight;

    @FindBy(id="hotel-flight-origin")
    WebElement flightOrigin;


    @FindBy(id="search-button")
    WebElement searchButton;

    public SearchPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log.info("Page created");
    }

    public void clickHotelTab(){
        hotelTab.click();
        log.warn("Hotel Tab is clicked");
    }

    public void clickAddFlight(){
        addFlight.click();
        log.warn("AddFlight is clicked");
    }

    public void setHotelDestination(String destination){
        hotelDestination.sendKeys(destination);
    }

    public void setHotelCheckin(String checkin) {
        hotelCheckin.sendKeys(checkin);
    }

    public void setHotelCheckout(String checkout) {
        hotelCheckout.clear();
        hotelCheckout.sendKeys(checkout);
    }

    public void setFlight(String flight) {
        flightOrigin.sendKeys(flight);
        log.warn("Dallas is added");
    }


    public void clickSearchButton(){
        searchButton.click();
        log.warn("SearchButton is clicked");
    }
}
