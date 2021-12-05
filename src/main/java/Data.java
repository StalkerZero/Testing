import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class Data {
    static String mail;//testertestov0@rambler.ru
    static String password; //TestOfSelenium0
    static WebDriver webDriver;


    public static void start(String mail, String password){
        Data.mail = mail;
        Data.password = password;

        try {
            webDriver.get("https://www.rambler.ru/");
//            webDriver.manage().window().maximize();
//            System.out.println("\n");

            Thread.sleep(500);
            login();

            if (!test1()) throw new Exception("Пароль или почта введены неверно");
            if (test2()) throw new Exception("Ваш профиль заблокирован");

            System.out.println("Успешный вход в аккаунт");
        } catch (IllegalStateException e) {
            System.out.println("Не получилось подключиться к Chromedriver.exe");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n");

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static WebDriver connect(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        return new ChromeDriver();
    }

    static void login() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.findElement(By.className("rui__2FTrL")).click();

        Thread.sleep(2000);
        /*for (int i=1; i<webDriver.findElements(By.tagName("iframe")).size(); i++){
            System.out.println(webDriver.switchTo().frame(i).findElements(By.id("login")).size());
            System.out.println(webDriver.findElements(By.tagName("iframe")).size());
        }*/
        webDriver.switchTo().frame(webDriver.findElements(By.tagName("iframe")).size()-1);

        Thread.sleep(200);
        webDriver.findElement(By.id("login")).sendKeys(mail);

        Thread.sleep(200);
        webDriver.findElement(By.id("password")).sendKeys(password);

        Thread.sleep(200);
        webDriver.findElement(By.className("rui-Button-content")).click();
    }

    static boolean test1() throws InterruptedException {
        Thread.sleep(300);
        return webDriver.findElements(By.className("rui-FieldStatus-error")).isEmpty();
    }

    static boolean test2() throws InterruptedException {
        Thread.sleep(300);
        return Objects.equals(webDriver.getTitle(), "Восстановление доступа к профилю");
    }
}
