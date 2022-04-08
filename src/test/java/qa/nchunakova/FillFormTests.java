package qa.nchunakova;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;

public class FillFormTests {

    @BeforeAll
    static void SetUp(){
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1980x1080";
    }

    @Test
    void fillFormTest(){
        open("/automation-practice-form");
    }
}
