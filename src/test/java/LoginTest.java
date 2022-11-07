import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;

    @BeforeMethod
    public void startBrowser()  {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\selenium\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void taskFirst(){
        driver.get("https://ya.ru/");
    }


    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }
}
