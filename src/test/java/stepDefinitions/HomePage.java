package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import support.BrowserActions;
import support.Constants;

import static pages.HomePage.*;
import static support.WaitUtils.waitForSeconds;

/**
 * Classe responsável pelos steps da funcionalidade de pesquisa no blog Agibank.
 * Contém as ações que simulam o comportamento do usuário e as validações relacionadas à página inicial do blog.
 */
public class HomePage {

    private final BrowserActions browser = BrowserActions.getInstance();

    /**
     * Acessa a página inicial do blog Agibank e aguarda o carregamento do elemento principal.
     */
    @Given("Que eu estou na pagina inicial do blog agibank")
    public void acessarPaginaInicialDoBlogAgibank() {
        browser.navigateTo(Constants.BASE_URL);
        browser.waitForElement(imgAgiBlog);
    }

    /**
     * Realiza uma pesquisa no campo de busca do blog, abrindo o campo caso ainda não esteja visível.
     * @param conteudoPesquisa conteúdo a ser pesquisado
     */
    @When("Eu realizo a pesquisa de {string} no campo de busca")
    public void realizarPesquisaNoCampoDeBusca(String conteudoPesquisa) {
        final int maxTentativas = 3;
        int tentativas = 0;

        browser.setTemporaryTimeout(1); // Timeout reduzido para tentativas rápidas de clique

        // Tenta abrir o campo de busca até que fique clicável ou atinja o limite de tentativas
        while (tentativas < maxTentativas && !browser.isElementClickable(txtSearch)) {
            browser.click(btnOpenSearchTxt);
            waitForSeconds(1);
            tentativas++;
        }

        browser.resetTimeout(); // Restaura o timeout original após as tentativas

        // Se o campo ainda não estiver disponível após as tentativas, lança exceção
        if (!browser.isElementClickable(txtSearch)) {
            throw new RuntimeException("Campo de busca não foi exibido após " + maxTentativas + " tentativas.");
        }

        // Preenche o campo e realiza a pesquisa
        browser.type(txtSearch, conteudoPesquisa);
        browser.click(btnSearchAction);
    }

    /**
     * Valida que a seção de resultados encontrados está visível após a pesquisa.
     */
    @Then("Valide que e apresentada uma secao informado resultados encontrados para a palavra pesquisada")
    public void validarSecaoDeResultadosEncontrados() {
        waitForSeconds(1);
        browser.validateElementExists(txtResultadosEncontrados, "Resultados encontrados");
    }

    /**
     * Valida que são exibidos artigos relacionados à palavra pesquisada.
     */
    @Then("Valide que e apresentada uma secao com artigos relacionados a palavra pesquisada")
    public void validarSecaoDeArtigosRelacionados() {
        waitForSeconds(1);
        browser.validateElementExists(listArtigos, "Artigos relacionados");
    }
}
