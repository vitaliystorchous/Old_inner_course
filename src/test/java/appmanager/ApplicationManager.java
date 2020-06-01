package appmanager;

import com.sun.media.sound.SF2Modulator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ApplicationManager {

    private final Properties properties;
    public HomepageHelper homepageHelper;
    private WebDriver wd;
    private String browser;
    private ProductHelper productHelper;
    private CartButtonHelper cartButtonHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.CHROME)) {
                File file = new File("src/test/resources/drivers/chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                File file = new File("src/test/resources/drivers/geckodriver.exe");
                System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                File file = new File("src/test/resources/drivers/IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                wd = new InternetExplorerDriver();
            } else if (browser.equals(BrowserType.EDGE)) {
                File file = new File("src/test/resources/drivers/msedgedriver.exe");
                System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
                wd = new EdgeDriver();
            } else if (browser.equals(BrowserType.OPERA_BLINK)) {
                File file = new File("src/test/resources/drivers/operadriver.exe");
                System.setProperty("webdriver.opera.driver", file.getAbsolutePath());
                wd = new OperaDriver();
            } else if (browser.equals("mobile")) {
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", System.getProperty("deviceName", "iPhone 6"));

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                wd = new ChromeDriver(chromeOptions);
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "ANY")));
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));

        homepageHelper = new HomepageHelper(wd);
        productHelper = new ProductHelper(wd);
        cartButtonHelper = new CartButtonHelper(wd);
    }

    public WebDriver getDriver() { return wd;}

    public void stop() {
        wd.quit();
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }

    public Dimension getWindowSize() {
        return wd.manage().window().getSize();
    }

    public Dimension getViewportSize() {
        Long width = (Long) ((JavascriptExecutor) wd).executeScript(" return document.documentElement.clientWidth;");
        Long height = (Long) ((JavascriptExecutor) wd).executeScript(" return document.documentElement.clientHeight;");
        return new Dimension(width.intValue(), height.intValue());
    }

    public HomepageHelper homepage() {
        return homepageHelper;
    }

    public ProductHelper product() {
        return productHelper;
    }

    public CartButtonHelper cartButton() {
        return cartButtonHelper;
    }

    public void refresh() {
        wd.navigate().refresh();
    }
}
