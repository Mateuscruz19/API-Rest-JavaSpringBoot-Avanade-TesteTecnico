# API-Rest-JavaSpringBoot-Avanade-TesteTecnico

## Descrição
API Rest desenvolvida em Java com Spring Boot para o teste tecnico da Avanade.
Contexto: Voce esta em uma batalha em um RPG D&D e precisa derrotar o grande Orc Boss. Para isso, voce precisa criar um personagem e escolher uma classe para ele. Apos isso, voce podera iniciar a batalha e realizar ataques e defesas para derrotar o Orc Boss.

OBS: A batalha é automatica, voce apenas escolhe as ações e o sistema realiza os calculos.
OBS2: Realize as migrations antes de iniciar a aplicação.

## Tecnologias
- Java
- Spring Boot
- JPA
- Hibernate
- PostgreSQL
- Gradle
- Lombok
- Swagger
- JUnit


    Rotas

    GET /classes
    -Mostra todas as classes disponiveis

    GET /classes/{id}
    -Mostra uma classe especifica se existir.



    POST /character
    -Cria um personagem com os parametros passados no body da requisição.
    Body esperados: ("name","class_id")

    GET /character
    -Mostra todos os personagens criados.

    GET /character/{id}
    -Mostra um personagem especifico se existir.

    DELETE /character/{id}
    -Deleta um personagem especifico se existir.

    PUT /character/{id}
    -Atualiza um personagem especifico se existir.



    POST /game/start/{id}
    -Inicia um jogo com o personagem especificado se existir.

    POST /game/attack/{id}
    -Realiza um ataque da batalha especificada se existir.
    OBS(VOCE BATALHA COM O ID DO TURNO E NAO DO PERSONAGEM)

    POST /game/defense/{id}
    -Realiza uma defesa da batalha especificada se existir.
    OBS(VOCE BATALHA COM O ID DO TURNO E NAO DO PERSONAGEM)

    POST /game/damage/{id}
    -Calcula o dano do round da batalha especificada se existir.
    OBS(VOCE BATALHA COM O ID DO TURNO E NAO DO PERSONAGEM)


    GET /history/battles
    -Mostra todos os historicos de batalhas.
    GET /history/game/{id}
    -Mostra um historico de batalha especifico se existir.
    GET /history/turn/{id}
    -Mostra um historico de turno especifico se existir.