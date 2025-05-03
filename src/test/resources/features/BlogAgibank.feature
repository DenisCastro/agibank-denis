Feature: Blog Agibank

  # Este cenário testa a funcionalidade de busca do blog.
  # Ele realiza uma pesquisa pelo termo "Teste" e valida se os títulos dos resultados contêm a palavra pesquisada.
  Scenario: Realizar pesquisa de artigos e validar quais dos resultados contem a palavra pesquisada no titlo
    Given Que eu estou na pagina inicial do blog agibank
    When Eu realizo a pesquisa de "Teste" no campo de busca
    Then Valide se algum dos resultados apresentados contem a palavra pesquisada no titulo

    # Este cenário verifica o comportamento da busca quando o campo é deixado vazio.
  # O objetivo é garantir que o sistema retorna resultados padrão ou aleatórios mesmo sem input.
  Scenario: Realizar pesquisa com o campo de busca vazio
    Given Que eu estou na pagina inicial do blog agibank
    When Eu realizo a pesquisa de "" no campo de busca
    When Eu realizo a pesquisa no campo de busca sem informar conteudo
    Then Validar que são apresentados resultados aleatórios

  # Este cenário valida se os headers principais da página estão corretamente exibidos e escritos.
  # Também garante que a estrutura de navegação está visível e funcional.
  Scenario: Verificar a presença dos headers
    Given Que eu estou na pagina inicial do blog agibank
    When Garanta que a tela carregou completamente
    Then Eu devo ver os headers "Início", "Produtos", e "Contato"
    And Os headers devem estar visíveis e corretamente escritos
