package support;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Classe utilitária que centraliza ações comuns de interação com o navegador usando Selenium WebDriver.
 * Implementada como Singleton Thread-Safe via ThreadLocal para suportar execução paralela.
 */
public class BrowserActions {

    private static final int DEFAULT_TIMEOUT = 15;

    private static final ThreadLocal<BrowserActions> instance =
            ThreadLocal.withInitial(() -> new BrowserActions(DriverManager.getDriver()));

    private final WebDriver driver;
    private final ThreadLocal<Integer> currentTimeout = ThreadLocal.withInitial(() -> DEFAULT_TIMEOUT);

    /**
     * Construtor privado utilizado pelo singleton.
     */
    private BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Retorna a instância atual da classe para a thread em execução.
     */
    public static BrowserActions getInstance() {
        return instance.get();
    }

    /**
     * Remove a instância associada à thread atual (importante para testes paralelos).
     */
    public static void resetInstance() {
        instance.remove();
    }

    /**
     * Define um timeout temporário (em segundos) para ações específicas.
     */
    public void setTemporaryTimeout(int seconds) {
        currentTimeout.set(seconds);
    }

    /**
     * Restaura o timeout para o valor padrão.
     */
    public void resetTimeout() {
        currentTimeout.set(DEFAULT_TIMEOUT);
    }

    /**
     * Cria uma instância de WebDriverWait com o timeout atual.
     */
    private WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(currentTimeout.get()));
    }

    /**
     * Navega para uma URL específica.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Retorna a instância atual do WebDriver.
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Realiza um clique em um elemento localizado pelo seletor.
     */
    public void click(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Preenche um campo de texto localizado, limpando-o previamente.
     */
    public void type(By locator, String text) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Aguarda até que o elemento esteja visível e o retorna.
     */
    public WebElement waitForElement(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Verifica se um elemento está clicável (visível e habilitado).
     */
    public boolean isElementClickable(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            return element.isDisplayed() && element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Valida se um elemento está presente na página dentro do timeout atual.
     * Caso contrário, falha o teste com uma mensagem amigável.
     */
    public void validateElementExists(By locator, String elementName) {
        int timeoutInSeconds = currentTimeout.get();
        long endTime = System.currentTimeMillis() + (timeoutInSeconds * 1000L);
        boolean found = false;

        while (System.currentTimeMillis() < endTime) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty()) {
                found = true;
                break;
            }

            try {
                Thread.sleep(500); // Espera entre tentativas
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Assert.fail("Erro: thread foi interrompida durante a espera.");
            }
        }

        if (!found) {
            Assert.fail("Erro: o elemento \"" + elementName + "\" não foi encontrado após " + timeoutInSeconds + " segundos.");
        }
    }
}
