package tests.day13;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
public class C03_FileExistTest {
    @Test
    public void fileExistTest(){
        // projemizde pom.xml oldugunu test edin
        String pomPath=System.getProperty("user.dir") + "\\pom.xml";
        Assert.assertTrue(Files.exists(Paths.get(pomPath)));
    }
}