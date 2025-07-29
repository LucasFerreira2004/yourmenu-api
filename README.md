# YourMenu API
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

## 📋 Descrição do Sistema

YourMenu é um sistema web desenvolvido para facilitar a gestão de pedidos online de pequenos restaurantes e lanchonetes. Seu principal propósito é oferecer aos administradores uma plataforma simples e personalizável, permitindo que configurem seu próprio ambiente de pedidos sem depender de terceiros ou soluções complexas.

Com YourMenu, o administrador pode cadastrar e organizar pratos no cardápio, definindo descrições, imagens, categorias e tamanhos. Também é possível personalizar informações do restaurante, como endereço, taxa de entrega, identidade visual, horários de funcionamento e gerar um link público exclusivo para que seus clientes possam acessar e realizar pedidos.

Na parte de gestão de pedidos, o sistema permite que o cliente visualize os pratos disponíveis, faça seu pedido e acompanhe seu status. Já o administrador pode gerenciar todos os pedidos recebidos, atualizando seus status conforme o andamento da preparação, destacando novos pedidos e encerrando-os manualmente quando necessário.

Além disso, o sistema conta com autenticação de administradores, garantindo controle de acesso, e foi projetado com foco na praticidade, tanto para quem administra quanto para o cliente final.

## 🛠️ Tecnologias e Ferramentas

### Backend
- **Java 17** - Linguagem principal
- **Spring Boot 3.4.4** - Framework para desenvolvimento de aplicações
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados principal
- **H2 Database** - Banco de dados para testes
- **Flyway** - Migração de banco de dados
- **JWT (Auth0)** - Autenticação baseada em tokens
- **MapStruct** - Mapeamento de objetos
- **Lombok** - Redução de código boilerplate
- **SpringDoc OpenAPI** - Documentação da API (Swagger)
- **AWS SDK S3** - Upload de imagens para Amazon S3
- **Google libphonenumber** - Validação de números de telefone

### Testes
- **JUnit 5** - Framework de testes
- **Mockito** - Mocking para testes
- **Spring Security Test** - Testes de segurança

### DevOps e Infraestrutura
- **Docker** - Containerização
- **Docker Compose** - Orquestração de containers
- **Maven** - Gerenciamento de dependências
- **Git** - Controle de versão

## 🏗️ Arquitetura

### Padrões Arquiteturais
- **REST API** - Arquitetura RESTful
- **Layered Architecture** - Separação em camadas (Controller, Service, Repository)
- **DTO Pattern** - Transferência de dados entre camadas
- **Mapper Pattern** - Conversão entre entidades e DTOs
- **Repository Pattern** - Abstração da camada de dados
- **Service Layer** - Lógica de negócio
- **Exception Handling** - Tratamento centralizado de exceções

### Estrutura do Projeto
```
src/main/java/com/yourmenu/yourmenu_api/
├── administrator/          # Gestão de administradores
├── auth/                  # Autenticação e autorização
├── businessHours/         # Horários de funcionamento
├── category/              # Categorias de pratos
├── deliveryZone/          # Zonas de entrega
├── dish_sizeOptions/      # Pratos e opções de tamanho
├── order/                 # Gestão de pedidos
├── order_client/          # Dados do cliente do pedido
├── orderAdress/           # Endereço de entrega
├── orderItem/             # Itens do pedido
├── restaurant/            # Gestão de restaurantes
├── restaurantAddress/     # Endereço do restaurante
└── shared/               # Utilitários compartilhados
    ├── awss3/            # Integração com AWS S3
    ├── globalExceptions/  # Exceções globais
    ├── notations/         # Anotações customizadas
    └── utils/            # Utilitários
```

## 🔧 Funcionalidades Implementadas

### Autenticação e Autorização
- ✅ Registro de administradores
- ✅ Login com JWT
- ✅ Controle de acesso baseado em roles
- ✅ Filtros de segurança customizados

### Gestão de Restaurantes
- ✅ Cadastro de restaurantes
- ✅ Upload de imagens (perfil e banner)
- ✅ Configuração de horários de funcionamento
- ✅ Gestão de endereços
- ✅ Controle de status (aberto/fechado)
- ✅ Geração de links públicos

### Gestão de Cardápio
- ✅ Categorias de pratos
- ✅ Cadastro de pratos com imagens
- ✅ Opções de tamanho e preços
- ✅ Associação prato-tamanho
- ✅ Controle de disponibilidade

### Sistema de Pedidos
- ✅ Criação de pedidos
- ✅ Gestão de status (novo, em preparo, pronto, entregue)
- ✅ Dados do cliente (nome, telefone)
- ✅ Endereço de entrega
- ✅ Itens do pedido com quantidades
- ✅ Cálculo de valores
- ✅ Resumo de pedidos por data

### Zonas de Entrega
- ✅ Cadastro de zonas de entrega
- ✅ Configuração de taxas por zona
- ✅ Validação de CEP

### Integração com AWS S3
- ✅ Upload de imagens para S3
- ✅ Configuração de imagens padrão
- ✅ Gerenciamento de URLs de imagens

## 📊 Banco de Dados

### Principais Entidades
- **Administrator** - Administradores do sistema
- **Restaurant** - Restaurantes cadastrados
- **RestaurantAddress** - Endereços dos restaurantes
- **BusinessHours** - Horários de funcionamento
- **Category** - Categorias de pratos
- **Dish** - Pratos do cardápio
- **SizeOption** - Opções de tamanho
- **DishSizeOption** - Associação prato-tamanho
- **Order** - Pedidos
- **OrderClient** - Dados do cliente
- **OrderAddress** - Endereço de entrega
- **OrderItem** - Itens do pedido
- **DeliveryZone** - Zonas de entrega

### Migrações
O projeto utiliza Flyway para controle de versão do banco de dados, com migrações numeradas sequencialmente (V1, V2, etc.).

## 🚀 Como Executar

### Pré-requisitos
- Java 17
- Maven 3.8+
- Docker e Docker Compose
- PostgreSQL (para produção)

### Configuração de Ambiente

1. **Clone o repositório**
```bash
git clone <repository-url>
cd yourmenu-api
```

2. **Configure as variáveis de ambiente**
Crie um arquivo `.env` na raiz do projeto:
```env
# Database
POSTGRES_DB=yourmenu
POSTGRES_USER=postgres
POSTGRES_PASSWORD=12345678

# JWT
JWT_SECRET=sua-chave-secreta-jwt

# AWS S3
AWS_REGION=us-east-1
AWS_S3_BUCKET=seu-bucket-s3
AWS_ACCESS_KEY=sua-access-key
AWS_SECRET_KEY=sua-secret-key

# PgAdmin
PGADMIN_DEFAULT_EMAIL=admin@admin.com
PGADMIN_DEFAULT_PASSWORD=admin

# Imagens padrão S3
S3_DEFAULT_CAPA_RESTAURANT=https://seu-bucket.s3.amazonaws.com/default-capa.jpg
S3_DEFAULT_ICON_RESTAURANT=https://seu-bucket.s3.amazonaws.com/default-icon.jpg
S3_DEFAULT_VISUAL_DISH=https://seu-bucket.s3.amazonaws.com/default-dish.jpg

# Profile
APP_PROFILE=dev
```

3. **Execute com Docker (Desenvolvimento)**
```bash
cd docker
docker-compose -f docker-compose.dev.yaml up -d
```

4. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```

### Endpoints Principais

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Base**: http://localhost:8080
- **PgAdmin**: http://localhost:5050

## 📚 Documentação da API

A documentação completa da API está disponível através do Swagger UI em:
```
http://localhost:8080/swagger-ui.html
```

### Principais Endpoints

#### Autenticação
- `POST /auth/register` - Registro de administrador
- `POST /auth/login` - Login de administrador

#### Restaurantes
- `POST /restaurant` - Criar restaurante
- `GET /restaurant/{restaurantId}` - Buscar restaurante
- `PUT /restaurant/{restaurantId}` - Atualizar restaurante
- `PATCH /restaurant/is-open/{restaurantId}` - Abrir/fechar restaurante

#### Categorias
- `POST /restaurant/{restaurantId}/category` - Criar categoria
- `GET /restaurant/{restaurantId}/category` - Listar categorias
- `PUT /restaurant/{restaurantId}/category/{categoryId}` - Atualizar categoria

#### Pratos
- `POST /restaurant/{restaurantId}/category/{categoryId}/dish` - Criar prato
- `GET /restaurant/{restaurantId}/dish/{dishId}` - Buscar prato
- `GET /restaurant/{restaurantId}/category/{categoryId}/dish` - Listar pratos por categoria

#### Pedidos
- `POST /restaurant/{restaurantId}/order` - Criar pedido
- `GET /restaurant/{restaurantId}/order` - Listar pedidos
- `PATCH /restaurant/{restaurantId}/order/{orderId}` - Atualizar status do pedido

## 🧪 Testes

### Executar Testes
```bash
./mvnw test
```

### Cobertura de Testes
O projeto inclui testes unitários para:
- Serviços de negócio
- Validações
- Mappers
- Controllers

## 🔒 Segurança

- **JWT Authentication** - Tokens para autenticação
- **Spring Security** - Configuração de segurança
- **CORS** - Configuração para requisições cross-origin
- **Validação de Dados** - Bean Validation
- **Controle de Acesso** - Endpoints protegidos e públicos

## 📦 Deploy

### Docker
```bash
# Build da imagem
docker build -t yourmenu-api .

# Executar container
docker run -p 8080:8080 yourmenu-api
```

### Docker Compose (Staging)
```bash
cd docker
docker-compose -f docker-compose.staging.yaml up -d
```

## 🤝 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 👥 Equipe

Desenvolvido como parte do Projeto Integrado em Engenharia de Software II | Universidade Federal do Ceará (UFC)
Contruidores:
- [Lucas Ferreira](https://github.com/LucasFerreira2004) | Backend
- [Francisco Kauan](https://github.com/kauanper) | Backend
- [Miquéias Bento](https://github.com/MiqueiasBento) | Backend
- [Antônio Rewelli](https://github.com/RewelliOliveira) | Frontend
- [Julian Cardoso](https://github.com/Julian-Cardoso) | Frontend
- [Luis Eduardo](https://github.com/LuisDallas1) | Frontend

## 🖼️ Interface

A interface do projeot está em um respositório separado, no [repositório do link](https://github.com/RewelliOliveira/your-menu) e pode ser executado com o comando:  
`npm install` e `npm run dev`

Há também o protótipo da interface, feito no [Figma](https://www.figma.com/design/Kbxt2pNzqnuj2fJzky0gly/Projeto-Integrado-II?node-id=30-5&t=zy381Ko9TPET0cTB-1).

---

**YourMenu** - Simplificando a gestão de pedidos para pequenos restaurantes 🍽️ 
