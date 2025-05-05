Feature: Blog Agibank

  Scenario: Realizar pesquisa no portal agibank e validar que e apresentada uma tela com os resultados da palavra pesquisada
    Given Que eu estou na pagina inicial do blog agibank
    When Eu realizo a pesquisa de "Teste" no campo de busca
    Then Valide que e apresentada uma secao informado resultados encontrados para a palavra pesquisada

  Scenario: Realizar pesquisa de artigos e validar que foram apresentados artigos relacionados a palavra pesquisada
    Given Que eu estou na pagina inicial do blog agibank
    When Eu realizo a pesquisa de "Teste" no campo de busca
    Then Valide que e apresentada uma secao com artigos relacionados a palavra pesquisada



