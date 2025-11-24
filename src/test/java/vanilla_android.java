import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class vanilla_android {
    public static String userName = System.getenv("LT_USERNAME") == null ? "YOUR_USERNAM" // Add username here
            : System.getenv("LT_USERNAME");
    public static String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "YOUR_ACCESS_KEY" // Add accessKey here
            : System.getenv("LT_ACCESS_KEY");

    private static AppiumDriver driver;

    public static void main(String args[]) throws MalformedURLException, InterruptedException {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "Pixel Tablet");
            capabilities.setCapability("platformVersion", "13");
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", "lt://APP10160301691761838297670235"); // Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("build", "Tripad-lambda Error");
            capabilities.setCapability("autoAcceptAlerts", false);
            capabilities.setCapability("autoGrantPermissions",false);
            capabilities.setCapability("name", "Sample Test Java");
            capabilities.setCapability("console", true);
            capabilities.setCapability("privateCloud",true);
            capabilities.setCapability("udid","3414105H804K24");
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("browserName","Chrome");
            capabilities.setCapability("devicelog", true);
            capabilities.setCapability("dedicatedProxy",true);
            capabilities.setCapability("deviceOrientation","PORTRAIT");
            capabilities.setCapability("geoLocation","US");
            capabilities.setCapability("language","en");
            capabilities.setCapability("liveVideo",true);
            capabilities.setCapability("locale","US");


            driver = new AppiumDriver(
                    new URL("https://" + userName + ":" + accessKey + "@mobile-hub.lambdatest.com/wd/hub"),
                    capabilities);

            MobileElement color = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/color"));
            color.click();

            MobileElement text = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/Text"));
            // Changes the text to proverbial
            text.click();

            // toast is visible
            MobileElement toast = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/toast"));
            toast.click();

            // notification is visible
            MobileElement notification = (MobileElement) driver
                    .findElement(MobileBy.id("com.lambdatest.proverbial:id/notification"));
            notification.click();

            // Open the geolocation page
            MobileElement geo = (MobileElement) driver
                    .findElement(MobileBy.id("com.lambdatest.proverbial:id/geoLocation"));
            geo.click();
            Thread.sleep(5000);

            // takes back to home page
            MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Home");

            driver.navigate().back();
            Thread.sleep(2000);

            // Takes to speed test page
            MobileElement speedtest = (MobileElement) driver
                    .findElement(MobileBy.id("com.lambdatest.proverbial:id/speedTest"));
            speedtest.click();
            Thread.sleep(5000);

            driver.navigate().back();

            // Opens the browser
            MobileElement browser = (MobileElement) driver.findElement(MobileBy.AccessibilityId("Browser"));
            browser.click();

            MobileElement url = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/url"));
            url.sendKeys("https://www.lambdatest.com");
            MobileElement find = (MobileElement) driver.findElement(MobileBy.id("com.lambdatest.proverbial:id/find"));
            find.click();

        } catch (AssertionError a) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=failed");
            a.printStackTrace();
        }
        // The driver.quit statement is required, otherwise the test continues to
        // execute, leading to a timeout.
        driver.quit();
    }
}
