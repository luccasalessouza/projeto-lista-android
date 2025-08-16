# Planejador de Viagens 

## 📖 Introdução

Este aplicativo é um planejador de atividades de viagem, desenvolvido como uma solução para um teste prático de estágio em desenvolvimento Android com Kotlin. O objetivo é permitir que os usuários criem, organizem e visualizem suas atividades de viagem de forma simples e eficiente, com um foco principal na qualidade do código, arquitetura e boas práticas de desenvolvimento.

## ✨ Funcionalidades

A aplicação cumpre todos os requisitos propostos no teste:

- **Registro de Atividades:**
  - Adicionar, editar e remover atividades.
  - Cada atividade contém um título (obrigatório), uma descrição (opcional) e uma prioridade (obrigatória).
  - A data e hora da última modificação são registradas e atualizadas automaticamente.
- **Visualização Inteligente:**
  - A tela principal exibe uma lista com todas as atividades.
  - A lista é ordenada por **prioridade** (Alta > Média > Baixa) e, dentro da mesma prioridade, pela **data de modificação** (da mais recente para a mais antiga).
  - Campos opcionais (como a descrição) só são exibidos se estiverem preenchidos.
- **Testes Unitários:**
  - O projeto inclui testes unitários para o `ViewModel` da tela de edição, validando a lógica de negócio de forma isolada.

## 🏛️ Arquitetura e Tecnologias

O projeto foi construído seguindo as melhores práticas e as tecnologias mais modernas recomendadas pela Google para o desenvolvimento Android.

### Arquitetura MVVM (Model-View-ViewModel)

A aplicação utiliza a arquitetura MVVM para uma clara separação de responsabilidades:

- **UI (View):** Construída com **Jetpack Compose**, esta camada é responsável apenas por exibir o estado fornecido pelo ViewModel e notificar o ViewModel sobre as interações do usuário.
- **ViewModel:** Contém a lógica da UI, gerencia o estado da tela e sobrevive a mudanças de configuração. Serve como intermediário entre a UI e a camada de dados.
- **Repository:** Implementa o padrão de repositório, servindo como uma fonte de verdade única para os dados da aplicação e abstraindo a sua origem (neste caso, o banco de dados local).
- **Data (Model):** Camada de persistência de dados, implementada com a biblioteca **Room**.

### 🛠️ Tech Stack

- **Linguagem:** [Kotlin](https://kotlinlang.org/) (100%)
- **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Arquitetura:** MVVM
- **Injeção de Dependência:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Programação Assíncrona:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-guide.html)
- **Programação Reativa:** [Kotlin Flow](https://kotlinlang.org/kotlinx.coroutines/MVI-flow.html)
- **Persistência de Dados:** [Room](https://developer.android.com/training/data-storage/room)
- **Testes Unitários:** [JUnit](https://junit.org/junit4/) & [MockK](https://mockk.io/)
- **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

## 📂 Estrutura do Projeto

O código-fonte está organizado em pacotes que refletem a arquitetura da aplicação:

- `com.example.trip.data`: Contém o `Repository`, `DAO`, `Database`, `Entity` e os conversores do Room.
- `com.example.trip.di`: Módulo do Hilt para a injeção de dependência.
- `com.example.trip.domain`: Contém os modelos de dados "puros" da aplicação.
- `com.example.trip.navigation`: Define o grafo de navegação com o Navigation Compose.
- `com.example.trip.ui`: Contém os componentes de UI (telas, eventos, ViewModels).

## 🚀 Como Executar a Aplicação

1.  Clone este repositório.
2.  Abra o projeto na versão mais recente do Android Studio.
3.  Aguarde a sincronização do Gradle e o download das dependências.
4.  Execute a aplicação em um emulador ou dispositivo físico (API 26+).

## ✅ Como Executar os Testes

Os testes unitários podem ser executados diretamente no Android Studio:

1.  Navegue até `app/src/test/java/com/example/trip/ui/feature/addedit/`.
2.  Clique com o botão direito no arquivo `AddEditTravelActivityViewModelTest.kt`.
3.  Selecione a opção "Run 'AddEditTravelActivityViewModelTest'".
4.  Os resultados serão exibidos no painel "Run".

## 📝 Decisões de Projeto

- **Escolha da Arquitetura MVVM:** Esta arquitetura foi escolhida por ser o padrão recomendado pela Google, facilitando a separação de responsabilidades e a criação de um código testável e de fácil manutenção.
- **Injeção de Dependência com Hilt:** O Hilt foi utilizado para gerenciar as dependências de forma automática, o que reduz o código boilerplate e torna a substituição de componentes para testes (como o uso de mocks) um processo trivial.
- **Programação Reativa com Flow:** O uso do Flow do Kotlin Coroutines permite que a UI reaja automaticamente a alterações no banco de dados, garantindo que a lista de atividades esteja sempre atualizada sem a necessidade de consultas manuais.
- **Documentação KDoc e Testes Unitários:** Foi tomada a decisão de incluir documentação completa e testes unitários como demonstração de um foco em qualidade, legibilidade e manutenção do software a longo prazo, práticas essenciais no desenvolvimento profissional.
