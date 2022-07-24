package helpers;

import org.testng.annotations.DataProvider;


public class DataTest {

    @DataProvider(name = "DataSearch")
    public static Object[][] provider() {
        return new Object[][]{
                {"New York", "2022-12-01", "2022-12-30", "December"}
        };
    }
}
