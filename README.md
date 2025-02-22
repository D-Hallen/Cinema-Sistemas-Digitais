# Cinema-Sistemas-Digitais
 Esse é um projeto escolar, com objetivo de simular uma aplicação de gestão de cinema, incluindo a área de vendas e gestão interna.
## Para conexão na API do backend: ##

1. Garanta que seu compilador possua disponível o Maven, ele será utilizado para rodar a aplicação
2. Crie um arquivo `db-config.properties` na raiz do projeto, usando o modelo fornecido em `db-config.properties.example`.
3. Preencha com as credenciais do seu banco de dados.
5. Rode em seu compilador o comando `mvn spring-boot:run` para iniciar a aplicação.
6. Com isso, serão geradas as tabelas no banco de dados necessárias para rodar a aplicação e as funcionalidades já estarão disponíveis para uso.
7. Acesse `http://localhost:8080/swagger-ui/index.html` para visualizar todas as chamadas disponíveis na API.
8. Após iniciar a aplicação, caso queira testar a funcionalidade do RMI, na pasta de testes há um arquivo nomeado `RMIClientTest` nele, é feito as chamadas das entidades sem interferencia da API em si.
