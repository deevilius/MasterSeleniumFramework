package org.selenium.pom.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

import java.sql.SQLOutput;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("billing_first_name");
    private final By lastNameField = By.id("billing_last_name");
    private final By addressLineOneField = By.id("billing_address_1");
    private final By billingCityField = By.id("billing_city");
    private final By billingPostCodeField = By.id("billing_postcode");
    private final By billingEmailField = By.id("billing_email");
    private final By placeOrderButton = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHereToLoginLink = By.className("showlogin");
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginLink = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDropDown = By.id("billing_country");
    private final By stateDropDown = By.id("billing_state");
    private final By directBankTransferRadioButton = By.id("payment_method_bacs");
    private final By productName = By.cssSelector("td[class='product-name']");
    private final By errorText = By.cssSelector("ul[role='alert']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load("/checkout");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
        e.clear();
        e.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
        /*Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(countryDropDown)));
        select.selectByVisibleText(countryName);*/
        By alternateCountryDropDown = By.id("select2-billing_country-container");
        wait.until(ExpectedConditions.elementToBeClickable(alternateCountryDropDown)).click();
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='India']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        return this;
    }

    public CheckoutPage selectState(String stateName) {
        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(stateDropDown)));
        select.selectByVisibleText(stateName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneField));
        e.clear();
        e.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityField));
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostCodeField));
        e.clear();
        e.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email) {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailField));
        e.clear();
        e.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountry())
                .enterAddressLineOne(billingAddress.getAddressLineOne())
                .enterCity(billingAddress.getCity())
                .selectState(billingAddress.getState())
                .enterPostCode(billingAddress.getPostalCode())
                .enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderButton).click();
        return this;
    }

    public String getNotice() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return this;
    }

    public CheckoutPage selectDirectBankTransfer() {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioButton));
        if (!e.isSelected()) {
            e.click();
        }
        return this;
    }

    private CheckoutPage waitForLoginButtonToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginLink));
        return this;
    }

    public CheckoutPage login(User user) {
        return enterUserName(user.getUsername())
                .enterPassword(user.getPassword())
                .clickLoginButton().waitForLoginButtonToDisappear();
    }

    public CheckoutPage failedLogin(User user) {
        return enterUserName(user.getUsername())
                .enterPassword(user.getPassword() + "failed")
                .clickLoginButton();
    }

    public String getProductName() throws Exception {
        int i = 5;
        while (i > 0) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
            } catch (StaleElementReferenceException e) {
                System.out.println("NOT FOUND. TRYING AGAIN" + e);
            }
            Thread.sleep(5000);
            i--;
        }
        throw new Exception("Element not found");
    }

    public String getErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorText)).getText();
    }

}
