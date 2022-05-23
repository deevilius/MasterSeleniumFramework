package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.ProductPage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

    @Test
    public void navigateFromHomeToStoreUsingMainMenu() {
        StorePage storePage = new HomePage(getDriver())
                .load()
                .getHeaderComponent()
                .navigateToStoreUsingMenu();

        Assert.assertEquals(storePage.getTitle(), "Store");
    }

    @Test
    public void navigateFromStoreToProduct() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new StorePage(getDriver()).load().clickProduct(product.getName());
        Assert.assertEquals(productPage.getTitle(), product.getName());
    }

/*    @Test
    public void navigateFromHomeToFeaturedProduct() throws IOException {
        Product product = new Product(1215);
        ProductPage productPage = new HomePage(getDriver()).load().navigateToProductPageUsingFeaturedProduct(product.getName());
        Assert.assertEquals(productPage.getTitle(), product.getName());
    }*/
}
