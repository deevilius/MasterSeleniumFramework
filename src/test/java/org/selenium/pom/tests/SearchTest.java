package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch() {
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
    }

    @Test
    public void searchWithExactMatch() {
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchFor + "”");
    }

    @Test
    public void searchNonExistingProduct() {
        String searchFor = "nonExistentProduct";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        Assert.assertEquals(storePage.getTextOfInfoText(), "No products were found matching your selection.");
    }
}
