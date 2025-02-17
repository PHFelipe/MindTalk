# MindTalk

## 📌 Sobre o Projeto
O **MindTalk** é uma aplicação desenvolvida para atendimento psicológico online via videochamada. Seu diferencial é a **integração dos batimentos cardíacos do paciente à sala de vídeo**, permitindo análises emocionais mais precisas pelo psicólogo.

Este projeto foi desenvolvido como parte da disciplina **Padrões de Projetos** no curso de **Engenharia da Computação** do **IFPB**.

## 🎯 Objetivos
### Objetivo Principal
- Diminuir o distanciamento entre psicólogo e paciente por meio da exibição dos batimentos cardíacos em tempo real durante a sessão de vídeo.

### Objetivos Específicos
- Criar um ambiente virtual seguro para sessões de terapia.
- Implementar o monitoramento da frequência cardíaca do paciente.

## 💡 Motivação e Relevância
A **psicoterapia online** tem se tornado cada vez mais popular, especialmente após a pandemia. No entanto, desafios como a falta de comunicação não verbal e a dificuldade na análise emocional do paciente ainda persistem.

A integração dos **batimentos cardíacos** oferece uma ferramenta objetiva para medir o estado emocional e o impacto das intervenções durante a sessão.

## 🔗 Padrões de Projeto Utilizados
O projeto foi estruturado seguindo diversos padrões de projeto para garantir organização e escalabilidade:

- **Factory Method** - Construção
- **Façade** - Interface
- **Adapter** - Interface
- **Singleton** - Responsabilidade
- **Observer** - Responsabilidade
- **Command** - Operação

## 📌 Requisitos
### ✅ Funcionais
- **RF-01** - Cadastrar Psicólogo
- **RF-02** - Cadastrar Paciente
- **RF-03** - Listar Pacientes
- **RF-04** - Fazer login
- **RF-05** - Fazer logout
- **RF-06** - Criar sala virtual de videochamada
- **RF-07** - Notificar o paciente sobre a criação da sala virtual
- **RF-08** - Entrar na sala de vídeo chamada (Paciente)
- **RF-09** - Integrar dados de batimentos cardíacos em tempo real à videochamada
- **RF-10** - Finalizar sala virtual de chamada de vídeo

### ⚙️ Não Funcionais
- **RNF-01** - Interface Amigável ao Usuário
- **RNF-02** - Uso de Microsserviços

## 🔧 Protótipo e Implementação
Atualmente, o sistema funciona via **terminal de comando**, desenvolvido em **Java**, com interface gráfica prevista para futuras versões.

### 🚀 Fluxo de Funcionamento
1. O psicólogo se cadastra.
2. O psicólogo faz login.
3. O psicólogo pode cadastrar um novo paciente.
4. O psicólogo cria uma sala virtual e seleciona o paciente para atendimento.
5. O paciente recebe uma notificação sobre a criação da sala.
6. O paciente faz login, ativa o wearable e entra na sala.
7. Os batimentos cardíacos do paciente são capturados e exibidos ao psicólogo em tempo real.
8. A chamada é encerrada.

## 🎬 Demonstrações em Vídeo
- **Vídeo promocional:** Apresenta a proposta do MindTalk e seus benefícios.  
  [![Vídeo Promocional](https://img.youtube.com/vi/GYyF-jgAvTo/0.jpg)](https://www.youtube.com/watch?v=GYyF-jgAvTo)

- **Vídeo técnico sobre padrões de projeto:** Explica as implementações dos padrões no sistema.  
  [![Vídeo Técnico](https://img.youtube.com/vi/qQme6rxKqkk/0.jpg)](https://www.youtube.com/watch?v=qQme6rxKqkk)

- **Vídeo de usabilidade:** Demonstra a execução do sistema no terminal.  
  [![Vídeo de Usabilidade](https://img.youtube.com/vi/vHCG9Txc9HE/0.jpg)](https://www.youtube.com/watch?v=vHCG9Txc9HE)

## 🔮 Trabalhos Futuros
- Desenvolvimento de uma **interface gráfica amigável**.
- Integração com **sensores reais** de batimentos cardíacos.

## 📝 Conclusão
O **MindTalk** propõe uma inovação relevante para a psicoterapia online, combinando **tecnologia e análise emocional**. A utilização de padrões de projeto contribuiu para a **organização e flexibilidade** do desenvolvimento, tornando o sistema mais modular e escalável.

## 👨‍💻 Desenvolvedores

- Andreza Costa dos Santos

- Pedro Medeiros das Chagas

- Paulo Henrique dos Santos Felipe
---
