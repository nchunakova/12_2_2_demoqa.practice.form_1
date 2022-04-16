package qa.nchunakova.docs;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssExamples {
    public static void main(String[] args) {
        // pseudocode, just to keep templates for selectors
        // could be just txt file, but let's create them in one class, why not
        // useful link: https://devhints.io/xpath - Xpath cheatsheet

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $("[data-testid=email]").setValue("123");
        $(by("data-testid", "email")).setValue("123");

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email">
        $("[id=email]").setValue("123");
        $("#email").setValue("123");
        $(byId("email")).setValue("123");
        $(By.id("email")).setValue("123");
        $x("//*[@id='email']").setValue("123"); //Xpath слишком огромный
        $(byXpath("//*[@id='email']")).setValue("123"); //Xpath слишком огромный

        // <input type="email" class="inputtext login_form_input_box" name="email">
        $("[name=email]").setValue("123");
        $(byName("email")).setValue("123");

        // <input type="email" class="inputtext login_form_input_box">
        $("[class=login_form_input_box]").setValue("123");
        $(".login_form_input_box").setValue("123"); // class special feature
        $(".inputtext.login_form_input_box").setValue("123"); // class more precise address
        $(".input.inputtext.login_form_input_box").setValue("123");
        $x("//input[@class='login_form_input_box']").setValue("123"); // xpath
        $x("//input[@class='inputtext'][@class='login_form_input_box']").setValue("123"); // xpath 2 classes

        //^ no spase between classes, because they're on the same level
        //following examples for multy-level elements

        // <div class="inputtext">
        //       <input type="email" class="login_form_input_box">
        // </div>

        $(".inputtext .login_form_input_box").setValue("123"); // space after inputtext

        // <input type="email" class="inputtext login_form_input_box" name="email" id="email" data-testid="email">
        $(".input.inputtext.login_form_input_box#email[name=email][data-testid=email]").setValue("123");
        // example^ of the fullest description. unreal


        // text search
        // <div>Hello every.body</div>
        $x("//div[text()='Hello every.body']"); // full text
        $x("//div[contains(text(), 'llo every.bo')]"); // part text
        $x("//div[text()[contains(.,'llo every.bo')]]"); // part text var 2
        $(byText("Hello every.body")); // inner Selenide instrument full text
        $(withText("llo every.bo")); // inner Selenide instrument part text
    }
}
