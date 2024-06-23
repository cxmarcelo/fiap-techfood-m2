# TechFood - Sistema de Autoatendimento para FastFoods

## Visão Geral

O TechFood é um sistema de autoatendimento desenvolvido para Fastfoods , com o objetivo de otimizar o processo de pedidos, pagamento, preparação e entrega de comida.   
O sistema oferece aos clientes uma interface para personalizar seus pedidos e acompanhar o progresso deles em tempo real.   
Também fornece aos administradores ferramentas para gerenciar os clientes, os produtos e os pedidos.

## Funcionalidades Principais

- **Pedido Personalizado:** Os clientes podem criar pedidos personalizados, escolhendo entre uma variedade de itens, como lanches, acompanhamentos, bebidas e sobremesas.
- **Pagamento Integrado:** Integração com o Mercado Pago, permitindo que os clientes efetuem o pagamento de seus pedidos através de um QRCode.
- **Acompanhamento de Pedido:** Os clientes podem acompanhar o status de seus pedidos em tempo real, desde o momento em que são recebidos até estarem prontos para retirada.
- **Gerenciamento Administrativo:** Os administradores têm acesso a um painel de controle para gerenciar clientes, produtos, categorias e pedidos em andamento.

## Principais Tecnologias Utilizadas

- **Kotlin**
- **PostgreSQL**
- **Clean Architecture**
- **Docker**
- **Swagger**
- **Spring**
- **Maven**

## Documentação

TODO  
[Event Storming](https://miro.com/app/board/uXjVPtIvRFs=/)


## APIs Disponíveis

O TechFood expõe as seguintes APIs para integração:

- **Cadastro do Cliente:** API para cadastrar novos clientes no sistema.
- **Identificação do Cliente via CPF:** API para identificar clientes existentes utilizando o CPF.
- **Gerenciamento de Produtos:** APIs para criar, editar e remover produtos do menu, além de buscar produtos por categoria.
- **Checkout:** API para o checkout de pedidos, enviando os produtos escolhidos para a fila de preparação.
- **Acompanhamento de Pedidos:** API para listar os pedidos em andamento e o tempo de espera de cada pedido.

## Iniciando

Enviar modificações para a branch main requer:

- Um PR aprovado (por membros do time e alguns serviços automatizados);
- Passar na formatação e nos testes de segurança;
- Passar nos testes de unidade e de integração;

No merging todas as mudanças será automaticamente integradas pelo Github Actions.

## Como Executar

Para executar o sistema, siga as instruções abaixo:

1. Certifique-se de ter o Docker e o Docker Compose instalados em seu computador.
2. Clone o repositório, no terminal executando o comando: `$ git clone https://github.com/priscilasanfer/fiap-techfood.git`
3. Entre na pasta do projeto: `$ cd fiap-techfood`
4. Build o projeto rodando o comando `$ mvn install -DskipTests`
5. Execute o comando `$ docker compose up --build -d` para subir o ambiente completo em modo detached.
6. Acesse a documentação da API através do Swagger para começar a interagir com o sistema.

## Acessando Swagger

Para acessar o Swagger utilize a url [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## Ambiente de desenvolvimento

O ambiente de desenvolvimento já sobe com alguns dados no banco, isso facilita o processo de teste do código. 

Para subir o ambiente de desenvolvimento utilize o comando: `sudo docker compose -f docker-compose-dev.yml up`

Se for necessário fazer um reset no ambiente, pode usar o comando: `sudo docker compose -f docker-compose-dev.yml down --rmi all`.