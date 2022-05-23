package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class ProductPage extends BasePage {

    private final By title = By.cssSelector(".product_title.entry-title");
    private final By addToCartButton = By.name("add-to-cart");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage load() {
        load("/product");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    public String getTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    private ProductPage clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        return this;
    }

}
