import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    //private WebDriver driver;

    //@BeforeAll
    //static void setUpAll() {


    //  WebDriverManager.chromedriver().setup();
    //}
    // @BeforeEach
    //void setUp() {
    //  ChromeOptions options = new ChromeOptions();
    //options.addArguments("--disable-dev-shm-usage");
    //options.addArguments("--no-sandbox");
    //options.addArguments("--headless");
    // driver = new ChromeDriver(options);
    //}

    //@AfterEach
    //void tearDown() {
    //  driver.quit();
    //driver = null;
    //}
    @Test
    void test() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        String planningDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[placeholder=\"Дата встречи\"]").setValue(planningDate);
        $("[placeholder=\"Город\"]").setValue("Москва");
        $("[name='phone']").setValue("+79228267474");
        $("[name='name']").setValue("Иванов Иван");
        $("[data-test-id='agreement']").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $x("//div[text()= 'Успешно!']").should(Condition.visible, Duration.ofSeconds(15));
        $x("//div[contains(text(), 'Встреча успешно забронирована')]").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}
