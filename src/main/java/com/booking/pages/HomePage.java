package com.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(id = "ss")
    WebElement destination_city;

    @FindBy(className = "xp__dates-inner")
    WebElement calendar;

    @FindBy(className = "bui-calendar__month")
    WebElement calendar_month_when;
    WebElement checkin_date;
    WebElement checkout_date;

    @FindBy(css = "div[data-bui-ref='calendar-next']")
    WebElement calendar_pagination_next;

    @FindBy(className = "sb-searchbox__button")
    WebElement search_button;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSearchButton() {
        return waitWebElement(search_button);
    }

    public void setDestinationCity(String city) {
        waitWebElement(destination_city).sendKeys(city);
    }

    public void setCalendarDateWhen(String checkin_day, String checkout_day, String month_when) {
        calendar.click();

        while (!calendar_month_when.getText().contains(month_when)) {
            calendar_pagination_next.click();
        }

        checkin_date = driver.findElement(By.cssSelector("td.bui-calendar__date[data-date='" + checkin_day + "']"));
        checkout_date = driver.findElement(By.cssSelector("td.bui-calendar__date[data-date='" + checkout_day + "']"));
        checkin_date.click();
        checkout_date.click();
    }
}
