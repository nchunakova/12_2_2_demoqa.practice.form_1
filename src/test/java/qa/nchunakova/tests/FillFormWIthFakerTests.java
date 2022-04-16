package qa.nchunakova.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static qa.nchunakova.utils.RandomUtils.getRandomEmail;
import static qa.nchunakova.utils.RandomUtils.getRandomString;

public class FillFormWIthFakerTests {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = "Female",
            userNumber = "0123456789",
            currentAddress = faker.rickAndMorty().quote();

//    String expectedFullName = format("%s %s", firstName, lastName); // to connect two strings

    @BeforeAll
    static void SetUp(){
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1980x1080";
    }

    @Test
    void fillFormTest(){
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2008");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        // ^ let choose one 30th among two elements, unlike $(byText("30")).click();

        $("#subjectsInput").setValue("E").pressEnter(); // todo edit subjects
        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("images/gymnocalycium-monvillei-mm814.jpg"); // file to upload from resources
        //$("#uploadPicture").uploadFile(new File("src/test/resources/images/gymnocalycium-monvillei-mm814.jpg")); // indicate file path from src

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $(byText("Rajasthan")).click();
        $("#city").click();
        $(byText("Jaiselmer")).click();

        $("#submit").click();

        //Assertions block
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text(firstName),
                text(lastName), text(gender), text(userNumber), text("30 July,2008"), text("English"),
                text("Reading"), text("gymnocalycium-monvillei-mm814.jpg"), text(currentAddress),
                text("Rajasthan Jaiselmer"));
        // todo add more text checks
        //$(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName));
        //$(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(lastName));
        //$(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));

        //$("#closeLargeModal").click();
        //$(".modal-content").shouldBe(hidden);
    }
}
