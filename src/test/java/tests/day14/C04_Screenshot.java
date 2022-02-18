package tests.day14;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;
import java.io.File;
/*
1.adim: screenshot almak icin obje olusturalim ve deger olarak driver'imizi atayalim
        deger olarak driver'i kabul etmesi icin casting yapmamiz gerekir
2.adim: tum sayfanin screenshot'ini almak icin bir File olusturalim ve dosya yolunu belirtelim
3.adim: olusturdugumuz file ile takescreenshot objesini iliskilendirelim
        eger spesifik bir webelementin screenshot'ini almak istiyorsaniz
 */
public class C04_Screenshot extends TestBase {

    @Test
    public void screenTest(){

        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//div[text()='Jag godkänner']")).click();
        // 1.adim : screenshot almak icin obje olusturalim ve deger olarak driver'imizi atayalim
        // deger olarak driver'i kabul etmesi icin casting yapmamiz gerekir
        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2.adim : tum sayfanin screenshot'ini almak icin bir File olusturalim ve dosya yolunu belirtelim
        File tumSayfaSS = new File("tumSayfa.png");

        // 3.adim : olusturdugumuz file ile takescreenshot objesini iliskilendirelim
        tumSayfaSS = tss.getScreenshotAs(OutputType.FILE);

        // eger spesifik bir webelementin screenshot'ini almak istiyorsaniz
        WebElement logoElementi = driver.findElement(By.xpath("(//img[@alt='Google'])[1]"));
        File logoResmi = new File("logo.png");
        logoResmi=logoElementi.getScreenshotAs(OutputType.FILE);
    }
}