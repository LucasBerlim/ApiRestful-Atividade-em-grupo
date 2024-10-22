![Logo do Projeto](https://github.com/LucasBerlim/ApiRestful-Atividade-em-grupo/blob/main/logopata.png)

# Pata Mágica - Seu Shopping Pet

🐾 **Bem-vindo ao Pata Mágica!** 🐾

Prepare-se para uma experiência encantadora no seu e-commerce favorito de produtos pet! Criado com todo o carinho e dedicação como parte do Projeto Final da disciplina de API RESTful da Residência de Software, o **Pata Mágica - Seu Shopping Pet** vem para transformar a vida dos nossos amigos peludos!

Prepare-se para momentos de muita fofura e diversão! Afinal, sabemos que quem manda na casa são eles, não é mesmo? 🐶🐱

### Sobre o Projeto

Este projeto foi desenvolvido com entusiasmo e zelo pela equipe, sob a orientação da brilhante professora Jacqueline Oliveira. Nosso objetivo é criar uma plataforma robusta e moderna, onde cada pet encontre o que há de melhor e mais encantador.

**Integrantes do Projeto**:
- **Brenda Chaves Barbatti**
- **Eduarda Pinho dos Santos**
- **Lucas Branco Berlim da Cunha**
- **Michele Moreira da Silva**

### Tecnologias Utilizadas

Utilizamos uma combinação poderosa de tecnologias para garantir a melhor performance e segurança:
- **Java com Spring Boot**: Para a criação de uma API robusta e eficiente.
- **Flyway**: Para o controle de versão do banco de dados.
- **JWT**: Para autenticação e controle de acesso seguro.
- **DTOs**: Para uma transferência de dados eficaz entre as camadas da aplicação.
- **Swagger**: Para documentação clara e acessível da API.
- **ViaCep**: Para encontrar localidades através do CEP.
- **Trello**: Para gestão de tarefas e acompanhamento do progresso.

### Regras de Negócio

- **Cálculo de Valores**: No cadastro de um novo pedido, calculamos os valores bruto e líquido dos produtos:
  - valor bruto = preço venda * quantidade
  - valor líquido = valor bruto – valor desconto (calculado aplicando o percentual de desconto sobre o valor bruto)
- **Valor Total do Pedido**: Calculamos e armazenamos o valor total do pedido como a soma dos valores líquidos dos itens do pedido.
- **Endereço do Cliente**: Obtivemos os dados do endereço do cliente a partir de uma API externa de consulta de CEP.

### Regras de Negócio - Desejáveis/Opcionais

- **Data Retroativa**: Não permitimos o cadastro de um pedido com data retroativa à atual.
- **Descrição do Produto**: Não permitimos o cadastro de um produto com descrição idêntica a uma já existente.
- **CPF Único**: Não permitimos o cadastro de diferentes clientes com o mesmo CPF.
- **E-mail Único**: Não permitimos o cadastro de diferentes clientes com o mesmo endereço de e-mail.

### ✅ Requisitos solicitados:

- **Banco de Dados**: Criamos o banco de dados e as tabelas de acordo com o DER fornecido, utilizando scripts definidos com o Flyway.
- **Estrutura/Relacionamento**: Utilizamos a estrutura e os relacionamentos entre as entidades conforme descritos no DER.
- **Endpoints Funcionais**: Todos os endpoints da API estão funcionais, realizando os processos para os quais foram desenvolvidos.
- **Métodos CRUD**: Implementamos métodos CRUD para todos os recursos da API, utilizando DTOs para a transição dos dados.
- **Classes de Serviço**: Utilizamos classes de serviço para evitar acoplamento com Repository e classes de negócio no controlador.
- **Relatório de Pedidos**: Criamos um relatório de pedidos que inclui:
  - id do pedido
  - data do pedido
  - valor total
  - itens do pedido: código e nome do produto, preço unitário, quantidade, percentual de desconto e valor líquido.
- **Tratamento de Exceções**: Identificamos e tratamos exceções de item não encontrado em todos os métodos CRUD, com mensagens personalizadas.
- **Validações**: Implementamos validações para prevenir erros de cadastro, com mensagens personalizadas.
- **Envio de E-mail**: Cada novo pedido cadastrado envia um e-mail contendo o relatório de pedido.
- **Autenticação e Controle de Acesso**: Implementamos autenticação e controle de acesso à API utilizando Spring Security + JWT.

### 🛠️ Organização do Código

- `/src/main/java`: Contém o código-fonte da API
- `/src/main/resources`: Contém os recursos de configuração e scripts de banco de dados
- `/docs`: Documentação adicional do projeto

### Contato

Em caso de dúvidas ou sugestões, entre em contato com um dos integrantes do projeto:
- **Brenda Chaves Barbatti**: [LinkedIn](https://github.com/brendachavesb)
- **Eduarda Pinho dos Santos**: [LinkedIn](https://github.com/EduardaPinh0)
- **Lucas Branco Berlim da Cunha**: [LinkedIn](https://github.com/LucasBerlim)
- **Michele Moreira da Silva**: [LinkedIn](https://github.com/eumichelems)
