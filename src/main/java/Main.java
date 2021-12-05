public class Main {
    public static void main(String[] args) {
        Data.webDriver = Data.connect();
        System.out.println("Test1 - Неправильные почта и пароль:");
        Data.start("proverkadlyatesta1", "proverkadlyatesta1");

        System.out.println("Test2 - Неправильная почта:");
        Data.start("proverkadlyatesta2", "TestOfSelenium0");

        System.out.println("Test3 - Неправильный пароль:");
        Data.start("testertestov0@rambler.ru", "proverkadlyatesta3");

        System.out.println("Test4 - Всё верно:");
        Data.start("testertestov0@rambler.ru", "TestOfSelenium0");

        Data.webDriver.quit();
        Data.webDriver = Data.connect();

        System.out.println("Test5 - Всё верно, но аккаунт заблокирован:");
        Data.start("testertestov@rambler.ru", "TestOfSelenium0");
        Data.webDriver.quit();
    }
}

