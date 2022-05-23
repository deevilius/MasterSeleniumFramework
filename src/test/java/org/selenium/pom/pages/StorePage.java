package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.time.Duration;

public class StorePage extends BasePage {

    private final By searchField = By.id("woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header");
    private final By infoText = By.cssSelector(".woocommerce-info");
    private ProductThumbnail productThumbnail;

    public StorePage(WebDriver driver) {
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    public StorePage search(String txt) {
        enterTextInSearchField(txt).clickSearchButton();
        return this;
    }

    private StorePage enterTextInSearchField(String txt) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField)).sendKeys(txt);
        return this;
    }

    private StorePage clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        wait.until(ExpectedConditions.titleContains("Search Results"));
        return this;
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    public String getTextOfInfoText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(infoText)).getText();
    }

    private By getProductElement(String productName) {
        return By.xpath("//h2[normalize-space()='" + productName + "']");
    }

    public ProductPage clickProduct(String productName) {
        By productHeadline = getProductElement(productName);
        wait.until(ExpectedConditions.elementToBeClickable(productHeadline)).click();
        return new ProductPage(driver);
    }
}
