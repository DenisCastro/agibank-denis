package support;

/**
 * Classe utilitária para esperas explícitas usando Thread.sleep.
 * Deve ser usada com cautela, apenas em situações onde o uso de esperas do Selenium não é suficiente.
 */
public class WaitUtils {
    public static void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
