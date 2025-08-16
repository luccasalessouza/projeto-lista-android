# Planejador de Viagens (Teste Prático de Estágio)

## 📖 Introdução

Este aplicativo é um planejador de atividades de viagem, desenvolvido como uma solução para um teste prático de estágio em desenvolvimento Android com Kotlin. O objetivo é permitir que os usuários criem, organizem e visualizem suas atividades de viagem de forma simples e eficiente, com um foco principal na qualidade do código, arquitetura e boas práticas de desenvolvimento.

## 📝 Premissas e Foco do Projeto

Para a construção deste projeto, parti de algumas premissas importantes, alinhadas com o enunciado do teste que valoriza a sintaxe e a estrutura do código:

-   **Foco na Arquitetura, Não no Design:** A prioridade máxima foi a construção de uma base de código robusta, bem arquitetada, documentada e testável. Por isso, a interface do usuário foi mantida limpa e funcional, utilizando os componentes padrão do Material Design, sem foco em animações complexas ou um design altamente customizado. Acredito que demonstrar um sólido entendimento de arquitetura de software era mais importante neste contexto.

-   **Nomenclatura "Activity":** É importante ressaltar que o uso do termo `Activity` em classes como `TravelActivity`, `TravelActivityViewModel`, etc., refere-se ao conceito de "atividade de viagem" do domínio do problema. Esta nomenclatura **não tem relação** com a classe `Activity` do framework Android, que é um componente de sistema operacional. A escolha foi feita para manter a clareza e a consistência com o domínio da aplicação.

-   **Ambiente de Desenvolvimento:** Assumi que o projeto seria avaliado em um ambiente de desenvolvimento padrão (Android Studio atualizado), justificando o uso de tecnologias modernas do ecossistema Android.

## ✨ Funcionalidades

A aplicação cumpre e expande os requisitos propostos no teste:

-   **Registro Completo de Atividades:**
    -   Adicionar, editar e remover atividades.
    -   Cada atividade contém um título (obrigatório), uma descrição (opcional) e uma prioridade (obrigatória).
    -   A data e hora da última modificação são registradas e atualizadas automaticamente.
-   **Visualização Inteligente:**
    -   A tela principal exibe uma lista com todas as atividades.
    -   A lista é ordenada por **prioridade** (Alta > Média > Baixa) e, dentro da mesma prioridade, pela **data de modificação** (da mais recente para a mais antiga).
    -   Campos opcionais (como a descrição) só são exibidos se estiverem preenchidos.
-   **Cobertura de Testes Unitários (Não-UI):**
    -   **Testes para ViewModels:** Validação da lógica de negócio, como o tratamento de eventos (salvar, deletar, carregar dados) e a gestão de estado.
    -   **Testes para o Repository:** Garantia de que o mapeamento de dados entre as camadas de domínio e de dados está correto.
    -   **Testes para os Converters:** Verificação da lógica de conversão de tipos de dados para o banco de dados.

## 🏛️ Arquitetura e Tecnologias

O projeto foi construído seguindo as melhores práticas e as tecnologias mais modernas recomendadas pela Google para o desenvolvimento Android.

### Arquitetura MVVM (Model-View-ViewModel)

A aplicação utiliza a arquitetura MVVM para uma clara separação de responsabilidades:

-   **UI (View):** Construída com **Jetpack Compose**, esta camada é responsável apenas por exibir o estado fornecido pelo ViewModel e notificar o ViewModel sobre as interações do usuário.
-   **ViewModel:** Contém a lógica da UI, gerencia o estado da tela e sobrevive a mudanças de configuração. Serve como intermediário entre a UI e a camada de dados.
-   **Repository:** Implementa o padrão de repositório, servindo como uma fonte de verdade única para os dados da aplicação e abstraindo a sua origem (neste caso, o banco de dados local).
-   **Data (Model):** Camada de persistência de dados, implementada com a biblioteca **Room**.

### 🛠️ Tech Stack

-   **Linguagem:** [Kotlin](https://kotlinlang.org/) (100%)
-   **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
-   **Arquitetura:** MVVM
-   **Injeção de Dependência:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
-   **Programação Assíncrona:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-guide.html)
-   **Programação Reativa:** [Kotlin Flow](https://kotlinlang.org/kotlinx.coroutines/MVI-flow.html)
-   **Persistência de Dados:** [Room](https://developer.android.com/training/data-storage/room)
-   **Testes Unitários:** [JUnit](https://junit.org/junit4/) & [MockK](https://mockk.io/)
-   **Navegação:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

## 📂 Estrutura do Projeto

O código-fonte está organizado em pacotes que refletem a arquitetura da aplicação:

-   `com.example.trip.data`: Contém o `Repository`, `DAO`, `Database`, `Entity` e os conversores do Room.
-   `com.example.trip.di`: Módulo do Hilt para a injeção de dependência.
-   `com.example.trip.domain`: Contém os modelos de dados "puros" da aplicação.
-   `com.example.trip.navigation`: Define o grafo de navegação com o Navigation Compose.
-   `com.example.trip.ui`: Contém os componentes de UI (telas, eventos, ViewModels).

## 🚀 Como Executar a Aplicação

1.  Clone este repositório.
2.  Abra o projeto na versão mais recente do Android Studio.
3.  Aguarde a sincronização do Gradle e o download das dependências.
4.  Execute a aplicação em um emulador ou dispositivo físico (API 26+).

## ✅ Como Executar os Testes

1.  Navegue até a pasta `app/src/test/java/com/example/trip/`.
2.  Clique com o botão direito na pasta `trip`.
3.  Selecione a opção "Run Tests in 'com.example.trip'".
4.  Os resultados serão exibidos no painel "Run".

## 📝 Decisões de Projeto

-   **Escolha da Arquitetura MVVM:** Esta arquitetura foi escolhida por ser o padrão recomendado pela Google, facilitando a separação de responsabilidades e a criação de um código testável e de fácil manutenção.

-   **Injeção de Dependência com Hilt:** O Hilt foi utilizado para gerenciar as dependências de forma automática. Esta decisão foi fundamental para desacoplar as camadas e permitir a implementação de testes unitários eficazes, onde componentes reais (como o `Repository`) podem ser facilmente substituídos por versões "mock" ou falsas.

-   **Programação Reativa com Flow:** O uso do Flow do Kotlin Coroutines permite que a UI reaja automaticamente a alterações no banco de dados, garantindo que a lista de atividades esteja sempre atualizada sem a necessidade de consultas manuais.

-   **Estratégia de Testes Abrangente:** Embora não fosse obrigatório, decidi incluir uma suíte de testes que cobre várias camadas da aplicação. Acredito que testar o código é uma prática fundamental para garantir a qualidade. Os testes implementados validam a lógica dos `ViewModels`, a correta manipulação de dados no `Repository` e a precisão da conversão de dados, demonstrando um compromisso com a robustez e a manutenibilidade do software.
