import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class Data {
    static String mail;//testertestov0@rambler.ru
    static String password; //TestOfSelenium0
    static WebDriver webDriver;

    static By buttonLogin = By.className("rui__2FTrL");
    static By frame = By.tagName("iframe");
    static By login = By.id("login");
    static By pass = By.id("password");
    static By submit = By.className("rui-Button-content");
    static By error = By.className("rui-FieldStatus-error");


    public static String start(String mail, String password){
        Data.mail = mail;
        Data.password = password;

        try {
            webDriver.get("https://www.rambler.ru/");
//            webDriver.manage().window().maximize();

            Thread.sleep(500);
            login();

            if (!test1()) throw new Exception("Пароль или почта введены неверно");
            if (test2()) throw new Exception("Ваш профиль заблокирован");

            return "Успешный вход в аккаунт";
        } catch (IllegalStateException e) {
            return ("Не получилось подключиться к Chromedriver.exe");
        } catch (Exception e) {
            return (e.getMessage());
        }

        /*try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    static void connect(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    static void login() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.findElement(buttonLogin).click();

        Thread.sleep(2000);
        /*for (int i=1; i<webDriver.findElements(By.tagName("iframe")).size(); i++){
            System.out.println(webDriver.switchTo().frame(i).findElements(By.id("login")).size());
            System.out.println(webDriver.findElements(By.tagName("iframe")).size());
        }*/
        webDriver.switchTo().frame(webDriver.findElements(frame).size()-1);

        Thread.sleep(200);
        webDriver.findElement(login).sendKeys(mail);

        Thread.sleep(200);
        webDriver.findElement(pass).sendKeys(password);

        Thread.sleep(200);
        webDriver.findElement(submit).click();
    }

    static boolean test1() throws InterruptedException {
        Thread.sleep(300);
        return webDriver.findElements(error).isEmpty();
    }

    static boolean test2() throws InterruptedException {
        Thread.sleep(300);
        return Objects.equals(webDriver.getTitle(), "Восстановление доступа к профилю");
    }
}
