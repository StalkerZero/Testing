import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Test
    @DisplayName("Test1 - Неправильные почта и пароль")
    void test1() {
        Data.connect();
        String result = Data.start("proverkadlyatesta1", "proverkadlyatesta1");
        assertSame("Пароль или почта введены неверно", result);
    }
    @Test
    @DisplayName("Test2 - Неправильная почта")
    void test2() {
        String result = Data.start("proverkadlyatesta2", "TestOfSelenium0");
        assertSame("Пароль или почта введены неверно", result);
    }
    @Test
    @DisplayName("Test3 - Неправильный пароль")
    void test3() {
        String result = Data.start("testertestov0@rambler.ru", "proverkadlyatesta3");
        assertSame("Пароль или почта введены неверно", result);
    }
    @Test
    @DisplayName("Test4 - Всё верно")
    void test4() {
        String result = Data.start("testertestov0@rambler.ru", "TestOfSelenium0");
        assertSame("Успешный вход в аккаунт", result);
        Data.webDriver.quit();
    }
    @Test
    @DisplayName("Test5 - Всё верно, но аккаунт заблокирован")
    void test5() {
        Data.connect();
        String result = Data.start("testertestov@rambler.ru", "TestOfSelenium0");
        assertSame("Ваш профиль заблокирован", result);
        Data.webDriver.quit();
    }
}