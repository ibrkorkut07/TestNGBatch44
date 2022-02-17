package tests.day11;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.Set;
public class C02_WindowHandle {
    // ● Tests package’inda yeni bir class olusturun: WindowHandle2
    WebDriver driver;
    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void windowHandleTest(){
        // https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        // Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement sayfadakiYaziElementi=driver.findElement(By.tagName("h3"));
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(sayfadakiYaziElementi.getText(), "Opening a new window","sayfadaki yazi beklenenden farkli");
        // Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        softAssert.assertEquals(driver.getTitle(),"The Internet","sayfa title'i beklenenden farkli");
        // Click Here butonuna basın.
        // soruda windowhandle degerini bilmedigim bir window aciliyor
        // o sayfanin window handle degerini bulmak icin gectigim sayfalardaki
        // window handle degerlerini kaydetmeliyim
        String windowHandleDegeri1=driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();
        // Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        // once acilan yeni sayfanin handle degerini elde etmeliyim
        // once tum handle degerlerini alip bir set'e koyalim
        Set<String> handleDegerleriSeti= driver.getWindowHandles();
        String windowHandleDegeri2="";
        for (String each:handleDegerleriSeti
        ) {
            if (!each.equals(windowHandleDegeri1)){
                windowHandleDegeri2=each;
            }
        }
        // artik yeni sayfaya gecis yapabilirim
        driver.switchTo().window(windowHandleDegeri2);
        softAssert.assertEquals(driver.getTitle(),"New Window","yeni sayfa title'i istenenden farkli");
        //● Sayfadaki textin “New Window” olduğunu doğrulayın.
        WebElement ikinciSayfadakiYaziElementi=driver.findElement(By.tagName("h3"));
        softAssert.assertEquals(ikinciSayfadakiYaziElementi.getText(),"New Window","Ikinci sayfadaki yazi istenenden farkli");
        //● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(windowHandleDegeri1);
        softAssert.assertEquals(driver.getTitle(),"The Internet","ilk sayfanin title'i beklenen gibi degil");
        softAssert.assertAll();
    }
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
}