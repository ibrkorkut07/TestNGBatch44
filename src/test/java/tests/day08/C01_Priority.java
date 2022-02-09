package tests.day08;

import org.testng.annotations.Test;

public class C01_Priority {

    @Test (priority = 3)
    public void youtubetest () {
        System.out.println("Youtube");
    }
    @Test (priority = 1)
    public void amazontest () {
        System.out.println("amazon");
    }
    @Test (priority = 2)
    public void bestbuytest () {
        System.out.println("bestbuy");
    }
}
