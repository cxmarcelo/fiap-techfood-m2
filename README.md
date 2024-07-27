# TechFood - Sistema de Autoatendimento para FastFoods
![Badge concluido](https://img.shields.io/static/v1?label=STATUS&message=Em%20Andamento&color=orange&style=for-the-badge)
## Visão Geral

O TechFood é um sistema de autoatendimento desenvolvido para Fastfoods , com o objetivo de otimizar o processo de pedidos, pagamento, preparação e entrega de comida.   
O sistema oferece aos clientes uma interface para personalizar seus pedidos e acompanhar o progresso deles em tempo real.   
Também fornece aos administradores ferramentas para gerenciar os clientes, os produtos e os pedidos.

Esta é a segunda versão deste projeto, onde houve a migração para Clean Architecture. A versão anterior [está disponível aqui.](https://github.com/RafaelRoseno/fiap-techfood)  

## Funcionalidades Principais

- **Pedido Personalizado:** Os clientes podem criar pedidos personalizados, escolhendo entre uma variedade de itens, como lanches, acompanhamentos, bebidas e sobremesas.
- **Pagamento Integrado:** Integração com o Mercado Pago, permitindo que os clientes efetuem o pagamento de seus pedidos através de um QRCode.
- **Acompanhamento de Pedido:** Os clientes podem acompanhar o status de seus pedidos em tempo real, desde o momento em que são recebidos até estarem prontos para retirada.
- **Gerenciamento Administrativo:** Os administradores têm acesso a um painel de controle para gerenciar clientes, produtos, categorias e pedidos em andamento.

## Principais Tecnologias Utilizadas

- **Java**
- **PostgreSQL**
- **Clean Architecture**
- **Docker**
- **Kubernetes**
- **Swagger**
- **Spring**
- **Maven**

## Documentação

//TODO  
[Event Storming](https://miro.com/app/board/uXjVPtIvRFs=/)


## APIs Disponíveis

O TechFood expõe as seguintes APIs para integração:

- **Cadastro do Cliente:** API para cadastrar novos clientes no sistema.
- **Identificação do Cliente via CPF:** API para identificar clientes existentes utilizando o CPF.
- **Gerenciamento de Produtos:** APIs para criar, editar e remover produtos do menu, além de buscar produtos por categoria.
- **Checkout:** API para o checkout de pedidos, enviando os produtos escolhidos para a fila de preparação.
- **Acompanhamento de Pedidos:** API para listar os pedidos em andamento e o tempo de espera de cada pedido.

//TODO

## Iniciando

Enviar modificações para a branch main requer:

- Um PR aprovado (por membros do time e alguns serviços automatizados);
- Passar na formatação e nos testes de segurança;
- Passar nos testes de unidade e de integração;

No merging todas as mudanças serão automaticamente integradas pelo Github Actions.

## Como Executar 
Para executar o sistema, siga as instruções abaixo:
### Clonando o Repositório
1. Clone o repositório executando o comando:  
`git clone https://github.com/cxmarcelo/fiap-techfood-m2.git`
2. Entre na pasta do projeto:  
`cd fiap-techfood-m2`

### Docker
1. Certifique-se de ter o Docker e o Docker Compose ***instalados e em execução*** em seu computador.
2. Construa o projeto executando o comando:  
    `mvn install -DskipTests`
3. Suba o ambiente completo em modo detached executando o comando:  
   `docker compose up --build -d`
4. Acesse a documentação da API através do Swagger para começar a interagir com o sistema.

### Kubernetes
1. Certifique-se de ter o Minikube e o Kubectl ***instalados e em execução*** em seu computador.
2. Configure o ambiente Kubernetes executando os comandos a seguir: 
   ``` 
   kubectl apply -f techfood-configmap.yaml  
   kubectl apply -f postgres-service.yaml  
   kubectl apply -f postgres-deployment.yaml  
   kubectl apply -f techfood-service.yaml  
   kubectl apply -f techfood-deployment.yaml 
   ```
3. [Windows ou Mac] Crie um túnel de conexão com a aplicação executando o comando:  
   `minikube service techfood-service`
4. Utilize o ip fornecido para acessar a aplicação. Exemplo:  
   `http://127.0.0.1:62248`
5. Acesse a documentação da API através do Swagger para começar a interagir com o sistema.

## Acessando Swagger

Para acessar o Swagger utilize a url:
#### Docker - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
#### Kubernetes [Windows ou Mac] - [http://{ip-do-tunel}:{porta}/swagger-ui/index.html]()
#### Kubernetes [Linux] - [http://{minikube-ip}:32080/swagger-ui/index.html]()

## Ambiente de desenvolvimento

O ambiente de desenvolvimento já sobe com alguns dados no banco, isso facilita o processo de teste do código. 

Para subir o ambiente de desenvolvimento utilize o comando: `sudo docker compose -f docker-compose-dev.yml up`

Se for necessário fazer um reset no ambiente, usar o comando: `sudo docker compose -f docker-compose-dev.yml down --rmi all`