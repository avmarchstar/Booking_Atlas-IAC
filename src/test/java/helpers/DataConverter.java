package helpers;

import com.booking.tests.BaseTest;
import com.booking.tests.SearchResultTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataConverter {

    private static final String FILE_PATH = "BookingComTestMethods.txt";

    //    return String date from WebElement on site
    public static String getDateAndFormat(WebElement date_elem) {

        String actual_date = date_elem.getText().split("\n")[1];

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = sdf.parse(actual_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("yyyy-MM-dd");

        return sdf.format(date);

    }

    //    return list of prices from site
    public static List<Integer> getPricesFromSearchSet(List<WebElement> webElementList) {

        List<Integer> prices = new ArrayList<>();
        webElementList.forEach(we -> prices.add(Integer.valueOf(we.findElement(By.cssSelector("[data-testid='price-and-discounted-price'] span:last-of-type")).getText().replaceAll("[^0-9]", ""))));

        return prices;
    }

    //     save TXT file with test methods' names
    public static void saveMethodsNameToFile(BaseTest testClass) {

        Class<? extends BaseTest> cls = testClass.getClass();
        StringJoiner string_for_txt = new StringJoiner(", ");
        Method[] methods = cls.getDeclaredMethods();
        int number_of_test_methods = 0;

        for (Method method : methods) {
            if (method.getAnnotation(Test.class) != null) {
                string_for_txt.add(method.getName());
                number_of_test_methods++;
            }
        }

        File output = new File(FILE_PATH);
        try {
            FileWriter writer = new FileWriter(output);
            writer.write("There are " + number_of_test_methods + " methods of test in project : " + string_for_txt);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SearchResultTest searchResultTest = new SearchResultTest();
        saveMethodsNameToFile(searchResultTest);

    }
}
