package qa.nchunakova;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FillFormTests {

    @BeforeAll
    static void SetUp(){
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1980x1080";
    }

    @Test
    void fillFormTest(){
        String firstName = "Sia";
        String lastName = "Bamberg";
        String userEmail = "sia@bamberg.com";
        String gender = "Female";
        String userNumber = "0123456789";
        String currentAddress = "My street 2/1";

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1992");
        $(byText("9")).click();

        $("#subjectsInput").setValue("E").pressEnter(); // todo edit subjects
        $(byText("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("images/0.png"); // file to upload from resources

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $(byText("Rajasthan")).click();
        $("#city").click();
        $(byText("Jaiselmer")).click();

        $("#submit").click();

        //Assertion block

    }
}
