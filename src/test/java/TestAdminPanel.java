import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.NoSuchElementException;

public class TestAdminPanel {
    private WebDriver driver;
    @BeforeMethod
    public void startBrowser()  {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\selenium\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void ClickAllAdminPanelTest() {
        driver.get("http://localhost/litecart/admin/login.php?redirect_url=%2Flitecart%2Fadmin%2F");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        int menuSize = driver.findElements(By.id("app-")).size();
        for (int i = 0; i < menuSize; i++) {
            driver.findElements(By.id("app-")).get(i).click();
            Assert.assertTrue(isElementPresent("h1"));
            int sizeSubMenu;
            try {
                sizeSubMenu = getWebElements("[id=box-apps-menu] ul li").size();
            } catch (NoSuchElementException ex) {
                sizeSubMenu = 0;
            }
            if (sizeSubMenu > 0) {
                for (int j = 0; j < sizeSubMenu; j++) {
                    getWebElements("[id=box-apps-menu] ul li").get(j).click();
                    Assert.assertTrue(isElementPresent("h1"));
                }
            }
        }
    }

    //Ожидания видимости элемента на странице
    public void waitElementVisibility(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator))));
    }

    //метод ожидания кликабельности элемента
    public void waitElementToBeClickable(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.cssSelector(locator))));
    }

    public WebElement getWebElement(String locator) {
        waitElementVisibility(locator);
        return driver.findElement(By.cssSelector(locator));
    }
    //получение WebElement по локатору
    public List<WebElement> getWebElements(String locator) {
        return driver.findElements(By.cssSelector(locator));
    }
    public boolean isElementPresent(String locator){
        try {
            getWebElement(locator);
            return true;
        } catch (TimeoutException ex){
            return false;
        }
    }
    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }
}
