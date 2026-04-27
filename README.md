📱 Calculadora Uber

Aplicativo Android desenvolvido para auxiliar motoristas de aplicativo no controle financeiro diário e mensal, permitindo acompanhar ganhos, custos e lucro real de forma simples e prática.

🚀 Sobre o Projeto

A Calculadora Uber foi criada a partir de uma necessidade real do dia a dia como motorista de aplicativo: entender se a jornada de trabalho está sendo realmente lucrativa.

O aplicativo permite registrar ganhos e despesas, calcula automaticamente o lucro diário e o ganho por hora, além de armazenar o histórico para análise mensal.

🎯 Objetivo
Controlar ganhos diários de forma simples
Calcular lucro líquido real
Analisar desempenho por hora trabalhada
Registrar histórico mensal
Apoiar decisões financeiras no dia a dia
🧠 Problema Resolvido

Motoristas de aplicativo geralmente não possuem um controle estruturado de:

lucro real após custos
consumo de combustível
ganhos por hora
desempenho mensal

Este aplicativo resolve isso automatizando os cálculos e organizando os dados de forma simples e acessível.

🛠️ Tecnologias Utilizadas
Kotlin
Android SDK
Room Database
Coroutines (lifecycleScope / suspend functions)
ConstraintLayout
🏗️ Arquitetura do Projeto

O projeto segue uma estrutura simples baseada em separação de responsabilidades:

Activities → interface e interação com o usuário
Entity (SalvarDia) → modelagem dos dados
DAO (SalvarDao) → operações com o banco de dados
Room Database (SalvarBanco) → persistência local
📲 Funcionalidades
💰 Controle Financeiro
Registro de ganhos diários
Cálculo automático de lucro líquido
Ganho por hora trabalhada
⛽ Custos Operacionais
Combustível
Alimentação
Outros custos
Estimativa de manutenção por km
📊 Histórico
Salvamento local com Room Database
Consulta de registros diários
Resumo do mês (total acumulado)
🔧 Extras
Calculadora de etanol vs gasolina
Validação de campos
Limpeza de formulários
Navegação entre telas
🗄️ Persistência de Dados

O aplicativo utiliza Room Database para armazenamento local no dispositivo.

Estrutura do banco:
SalvarDia → entidade principal
SalvarDao → operações (insert e select)
SalvarBanco → configuração do banco de dados
🚧 Status do Projeto

Em desenvolvimento contínuo, com melhorias frequentes focadas em usabilidade, precisão dos cálculos e experiência do usuário.

💡 Melhorias Futuras
📊 Gráficos de desempenho mensal
☁️ Backup em nuvem
📄 Exportação de relatórios (PDF/Excel)
📱 Dashboard mais visual (UI/UX aprimorada)
🌙 Dark Mode
🧠 Otimização da lógica de cálculo
👨‍💻 Autor

Ricardo Avelino de Souza

Projeto pessoal desenvolvido para uso real no dia a dia como motorista de aplicativo e evolução em desenvolvimento Android.

📌 Destaques Técnicos

Este projeto demonstra experiência prática com:

Desenvolvimento Android nativo com Kotlin
Persistência local com Room Database
Programação assíncrona com Coroutines
Lógica de cálculo financeiro real
Navegação entre Activities
Validação de formulários
Estruturação de dados
🔥 Resumo para Recrutadores

Aplicativo funcional de controle financeiro para motoristas de aplicativo, com persistência local, cálculos automáticos e análise de desempenho diário e mensal.
