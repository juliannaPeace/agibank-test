# Processamento de Arquivos (Teste AgiBank)
O teste consiste em um sistema de análise de dados, onde o mesmo deve importar lotes de arquivos, ler e analisar os dados e produzir um relatório.

o arquivo segue o seguinte padrão:

001ç1234567891234çPedroç50000 <br/>
001ç3245678865434çPauloç40000.99 <br/>
002ç2345675434544345çJose da SilvaçRural <br/>
002ç2345675433444345çEduardo PereiraçRural <br/>
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro <br/>
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo <br/>

#### 001  - Dados do Vendedor ####
#### 002  - Dados do Cliente ####
#### 003  - Dados da Venda ####

O que o sistema deve produzir:

● Quantidade de clientes no arquivo de entrada <br/>
● Quantidade de vendedor no arquivo de entrada <br/>
● ID da venda mais cara <br/>
● O pior vendedor <br/>

Os sistema lê dados localizados em %HOMEPATH%/data/in, e gera os dados de processamento no diretório: %HOMEPATH%/data/out. <br/>
*Obs: Ao executar o sistema, o mesmo cria, caso não exista, os direrórios listados acima.*

### Para o build da aplicação, execute o seguinte comando :
```sh
$ mvn clean package
```
### Para rodar a aplicação em Docker, execute o seguinte comando:
```sh
$ docker-compose up
```
### Para rodar a aplicação de forma manual, execute o seguinte comando:
```sh
$ java -jar nomedoarquivogerado.jar
```
## Modos de testar a aplicação:
1 - Através do endereço localizado em http://localhost:8080 
<br/> Você terá acesso a um botão de upload em lote.
Também poderá visualizar uma tabela contendo todos os arquivos que foram processados e o resultado desse processamento.

![alt text](https://raw.githubusercontent.com/juliannaPeace/agibank-test/main/upload_file.jpeg)


2 - Colocando os arquivos de forma manual, dentro do diretório %HOMEPATH%/data/in.

A cada 1 minuto o processo é rodado para verificar se existem arquivos .dat dentro do diretório padrão e então os dados são processados e salvos
dentro do diretório %HOMEPATH%/data/out.


**Tecnologias utilizadas**
- Java 11
- Spring Framework
- Docker
- Thymeleaf
- Lombok
- Maven
