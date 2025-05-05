package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Gerenciador de instâncias do WebDriver.
 * Utiliza ThreadLocal para suportar execução paralela segura,
 * garantindo que cada thread tenha sua própria instância de navegador.
 */
public class DriverManager {

    // Cada thread terá sua própria instância de WebDriver
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    /**
     * Cria e associa uma nova instância do ChromeDriver à thread atual.
     * Deve ser chamado antes de qualquer operação que utilize o WebDriver.
     */
    public static void createDriver() {
        driverThread.set(new ChromeDriver());
    }

    /**
     * Retorna a instância atual do WebDriver associada à thread em execução.
     * @return WebDriver da thread atual
     */
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    /**
     * Finaliza e remove a instância do WebDriver associada à thread atual.
     * Garante liberação adequada de recursos.
     */
    public static void quitDriver() {
        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();       // Encerra o navegador
            driverThread.remove(); // Remove a instância da thread
        }
    }
}
