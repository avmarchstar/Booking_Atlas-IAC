package com.booking.tests;

import com.booking.pages.HomePage;
import com.booking.pages.SearchResultsPage;
import helpers.DataConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultTest extends BaseTest {

    /**
     * Checking the expected result in the search results:
     * Dates from 2022-12-01 to 2022-12-30
     * and destination New York
     * Entering data for a test using a DataProvider
     */

    @Test(testName = "Search_Destination_Dates", dataProviderClass = helpers.DataTest.class, dataProvider = "DataSearch")
    public void searchDestinationDatesTest(String city, String checkin_date, String chekout_date, String month_when) {

        HomePage home_page = new HomePage(driver);
        home_page.setCalendarDateWhen(checkin_date, chekout_date, month_when);
        home_page.setDestinationCity(city);
        home_page.getSearchButton().click();

        //      Checking address in the result set

        SearchResultsPage search_result_page = new SearchResultsPage(driver);
        List<WebElement> result_list = search_result_page.getResultSet();
        result_list.forEach(we -> Assert.assertTrue(we.findElement(By.cssSelector("[data-testid='address']")).getText().contains("New York")));

        //      Checking selected dates

        Assert.assertEquals(DataConverter.getDateAndFormat(search_result_page.getDateStart()), checkin_date);
        Assert.assertEquals(DataConverter.getDateAndFormat(search_result_page.getDateFinish()), chekout_date);

    }



    /**
     * Checking the expected result in the search results:
     * Lowest price first
     */

    @Test(testName = "Search_Lowest_Price", dataProviderClass = helpers.DataTest.class, dataProvider = "DataSearch")
    public void searchLowestPriceTest(String city, String chekin_date, String chekout_date, String month_when) {

        HomePage home_page = new HomePage(driver);
        home_page.setCalendarDateWhen(chekin_date, chekout_date, month_when);
        home_page.setDestinationCity(city);
        home_page.getSearchButton().click();


        SearchResultsPage search_results_page = new SearchResultsPage(driver);

        if (search_results_page.isElementExisting(search_results_page.getSortedDropdownButton())) {
            search_results_page.getSortedDropdownButton().click();
        }
        search_results_page.getLowestPriceButton().click();

        List<WebElement> result_list = search_results_page.getResultSet();
        List<Integer> sorted_prices,
                prices = DataConverter.getPricesFromSearchSet(result_list);
                sorted_prices = new ArrayList<>(prices);

        Collections.sort(sorted_prices);

//        Check whether the sorted list matches the received list

           Assert.assertEquals(sorted_prices, prices);

    }
}
