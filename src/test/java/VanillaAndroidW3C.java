import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class VanillaAndroidW3C {

    public static String userName = System.getenv("LT_USERNAME") == null ? "YOUR_USERNAME" : System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_ACCESS_KEY" : System.getenv("LT_ACCESS_KEY");

    public static String appId = "lt://APP10160622431767769936744209";   // your app ID
    public static String gridUrl = "mobile-hub.lambdatest.com";

    private AndroidDriver driver;

    @BeforeClass
    @Parameters({"device", "version", "platform"})
    public void setUp(String device, String version, String platform) throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Map<String, Object> ltOptions = new HashMap<>();

        // *** W3C lt:options ***
        ltOptions.put("w3c", true);
        ltOptions.put("build", "mmt new issue lambda hook");
        ltOptions.put("name", "Sample Test Java W3C");
        ltOptions.put("platformName", platform);
        ltOptions.put("deviceName", device);
        ltOptions.put("platformVersion", version);
        ltOptions.put("isRealMobile", true);
        ltOptions.put("app", appId);
//        ltOptions.put("deviceOrientation", "PORTRAIT");
        ltOptions.put("autoAcceptAlerts", true);
        ltOptions.put("autoGrantPermissions", true);
        ltOptions.put("console", true);
        ltOptions.put("devicelog", true);
        ltOptions.put("visual", true);
        ltOptions.put("network", false);
//        ltOptions.put("browserName", "Chrome");
        ltOptions.put("timezone","Kolkata");
        ltOptions.put("region","ap");
//        ltOptions.put("privateCloud", true);
//        ltOptions.put("udid", "3414105H804K24");
//        ltOptions.put("geoLocation", "US");
        ltOptions.put("language", "en");
        ltOptions.put("locale", "en");
//        ltOptions.put("liveVideo", true);
//        ltOptions.put("dedicatedProxy", true);

        // Add lt:options to capabilities
        capabilities.setCapability("lt:options", ltOptions);

        // Create driver
        String hub = String.format("https://%s:%s@%s/wd/hub", userName, accessKey, gridUrl);
        driver = new AndroidDriver(new URL(hub), capabilities);
    }

    @Test
    public void sampleTest() throws Exception {
    Thread.sleep(5000);
        System.out.println("session created");
//        MobileElement color = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/color"));
//        color.click();
//
//        MobileElement text = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/Text"));
//        text.click();
//
//        MobileElement toast = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/toast"));
//        toast.click();
//
//        MobileElement notification = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/notification"));
//        notification.click();
//
//        MobileElement geo = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/geoLocation"));
//        geo.click();
//        Thread.sleep(3000);
//
//        driver.navigate().back();
//        Thread.sleep(2000);
//
//        MobileElement speedtest = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/speedTest"));
//        speedtest.click();
//        Thread.sleep(3000);
//        driver.navigate().back();
//
//        // Browser test
//        MobileElement browser = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Browser"));
//        browser.click();
//
//        MobileElement url = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/url"));
//        url.sendKeys("https://www.lambdatest.com");
//
//        MobileElement find = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/find"));
//        find.click();




        ((JavascriptExecutor) driver)
                .executeScript("lambda-testCase-start={app lunch}");

        Thread.sleep(1000);

        driver.removeApp("com.Ios.MMT");
        ((JavascriptExecutor) driver)
                .executeScript("lambda-testCase-end={app lunch}");

        // SECOND APP
        ((JavascriptExecutor) driver)
                .executeScript("lambda-testCase-start={second app biometric start}");

        Map<String, Object> data = new HashMap<>();
        data.put("appUrl", "lt://APP10160622431767769936744209");
        data.put("retainData", true);
        data.put("resignApp",false);

        driver.executeScript("lambda-install-app", data);
        Thread.sleep(1000);
        driver.activateApp("com.Ios.MMT");

        ((JavascriptExecutor) driver)
                .executeScript("lambda-testCase-end={second app biometric start}");

        Thread.sleep(8000);

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
            driver.quit();
        }
    }
}
