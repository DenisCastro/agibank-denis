# agibank-denis

1. Clone o repositório:
   1. git clone https://github.com/DenisCastro/agibank-denis.git
2. Abra o projeto em uma IDE compatível (preferencialmente IntelliJ IDEA).
3. Importe as dependências do Maven:
   1. Clique com o botão direito no arquivo pom.xml 
   2. Selecione "Download Sources and Documentation" 
   3. Aguarde a finalização do carregamento
4. Execute a classe de testes:
   1. Navegue até o diretório: src/test/java/runners 
   2. Se o JDK não estiver configurado, configure a versão 19 do JDK 
   3. Execute a classe TestRunner.java (botão direito > Run)
5. Acompanhe a execução dos cenários:
   1. Os testes serão executados conforme os arquivos .feature e os steps definidos
6. Visualize os resultados:
   1. Acompanhe os logs e resultados diretamente na aba "Run" da IDE (console de execução) 
   2. Relatórios adicionais podem estar disponíveis no diretório target/, como cucumber-report.html (se configurado)