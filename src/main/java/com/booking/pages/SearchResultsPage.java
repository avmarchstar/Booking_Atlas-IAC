package com.booking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(css = "div[data-testid='property-card']")
    List<WebElement> result_set;

    @FindBy(css = "[data-testid='date-display-field-start']")
    WebElement date_start;

    @FindBy(css = "[data-testid='date-display-field-end']")
    WebElement date_finish;

    @FindBy(css = "[data-testid='sorters-dropdown-trigger']")
    WebElement sorted_dropdown_button;

    @FindBy(css = "[data-id='price']")
    WebElement lowest_price_button;


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getResultSet() {
        return waitWebElement(result_set);

    }

    public WebElement getDateStart() {
        return waitWebElement(date_start);
    }

    public WebElement getDateFinish() {
        return waitWebElement(date_finish);
    }

    public WebElement getLowestPriceButton() {
        return waitWebElement(lowest_price_button);
    }

    public WebElement getSortedDropdownButton() {
        return sorted_dropdown_button;
    }
}

