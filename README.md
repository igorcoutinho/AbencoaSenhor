[![Build Status](https://travis-ci.com/igorcoutinho/AbencoaSenhor.svg?branch=master)](https://travis-ci.com/igorcoutinho/AbencoaSenhor)
# Restful API utilizando spring boot e java 8

Para que a API execute sem problema será necessario:

- Uma IDE que rode o jdk8 , eu utilizei o spring boot tools 4.
- Ter o maven instalado na máquina.
- o java 8 instalado.
- O postman também pode ser utiliazado para fazer as requisiçoes, pórem todas elas podem ser encontradas no link mais a baixo.

Para importar o projeto: 
Import > Maven > Existing maven projects > next > Seleciona o caminho onde está o projeto > finish.
É imporante que espere um pouco antes de executar a aplicação, pois alguns pacotes poderão ser baixados.
Para executar o projeto clica no nome da aplicação com o botão direito > Run AS> 9 Spring boot App


Todas as funcionalidades podem ser acessadas através de : 
http://localhost:8080/swagger-ui.html

A swagger ui(interface) foi impletada para melhor utilização e menor confusão na hora de inserir registros.

Em atividade-controler:
/api/atividades (POST)
É possivel cadastrar as tarefas com suas respectivas atividades
/api/atividades/tarefa/{tarefaId} (GET)
É possivel listar todas as atividades de uma respectiva tarefa
/api/atividades/{id} (DELETE)
É possível excluir uma atividade passando seu respecitivo id
/api/atividades/{id} (PUT)
Faz atualização de dados, seja ele tarefa ou atividade e ambos.

Em usuarios-controller:
É possível adicionar um usuário

Os logs podem ser observados dentro de um arquivo no projeto chamado: my.log
E também clicando no "build" (icone verde) logo em cima do título.

Também foram realizados criados alguns testes unitários.
Estes podem ser encontrados na pasta do projeto;
Diretorio: src/test/java
com.checklist.api.services
TarefaServiceTest.Java
UsuarioServiceTeste.java
Para executa-los é so clicar sobre o arquivo com o botão direito > Run as > Junit Test





