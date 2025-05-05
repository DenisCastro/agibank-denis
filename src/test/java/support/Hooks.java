package support;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.time.Duration;
import java.time.Instant;

/**
 * Classe de hooks do Cucumber.
 * Responsável por configurar o ambiente antes de cada cenário e finalizar os recursos ao término.
 */
public class Hooks {

    private Instant startTime;

    /**
     * Executado antes de cada cenário.
     * - Inicia o cronômetro para calcular a duração.
     * - Cria e configura a instância do WebDriver.
     */
    @Before
    public void setUp(Scenario scenario) {
        startTime = Instant.now();

        // Criação do driver e maximização da janela
        DriverManager.createDriver();
        BrowserActions.getInstance()
                .getDriver()
                .manage()
                .window()
                .maximize();

    }

    /**
     * Executado após cada cenário.
     * - Calcula e exibe a duração do cenário e seu status (sucesso/falha).
     * - Finaliza e limpa instâncias do WebDriver.
     */
    @After
    public void tearDown(Scenario scenario) {
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);

        String status = scenario.isFailed() ? "Falhou" : "Sucesso";

        // Log de resumo do cenário
        System.out.println("##########");
        System.out.printf(
                "Resultado: \033[1m%-8s\033[0m | Duração: %ds%n",
                status,
                duration.getSeconds()
        );
        System.out.println("##########");

        // Liberação de recursos
        DriverManager.quitDriver();
        BrowserActions.resetInstance();
    }
}
