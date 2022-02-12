package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
//  Go to amazon.com
//  To choose one of the elements from the dropdown;
//  1. Locate the dropdown element and assign it to a variable
//  2. Build an object from Select class and write the web element that we locate as parameter
//  (By using the select object) We can choose one of the dropdown options with the
//  help of 3 available methods. These 3 methods that help us to choose are viod.
//  Therefore, they will not return anything but choose.
//  If we want to write the selected option value;
public class C02_HandleDropdown {

    WebDriver driver;

    @BeforeMethod
    public void setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(11));
    }

    @Test
    public void dropdownTest () throws InterruptedException {
        //  To choose one of the elements from the dropdown;
        // 1. Locate the dropdown element and assign it to a variable
        driver.get("https://amazon.com");
        WebElement dropdowmElement = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        //  2. Build an object from Select class and write the webelement that we locate as parameter
        Select select = new Select(dropdowmElement);
        //  (By using the select object) We can choose one of the dropdown options with the
        //  help of 3 available methods. These 3 methods that help us to choose are void.
        //  Therefore, they will not return anything but choose.
        select.selectByIndex(3);
        //  If we want to write the selected option value;
        System.out.println(select.getFirstSelectedOption().getText());  //  Baby
        Thread.sleep(3000);

        select.selectByVisibleText("Deals");
        Thread.sleep(3000);

        select.selectByValue("search-alias=arts-crafts-intl-ship");

        List<WebElement> optionList = select.getOptions();
        for (WebElement eachoption : optionList) {
            System.out.println(eachoption.getText());
        }
    }
    @AfterMethod
    public void tearDown () {
        driver.close();
    }
}