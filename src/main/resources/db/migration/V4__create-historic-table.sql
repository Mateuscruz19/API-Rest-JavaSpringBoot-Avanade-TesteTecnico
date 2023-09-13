CREATE TABLE battles (
    id SERIAL PRIMARY KEY,
    character_id INT,
    monster_id INT,
    characterLife INT,
    enemyLife INT,
    whoStarts fighters,
    turn INT,
    turn_type turn_type,
    result battle_result
);

CREATE TYPE battle_result AS ENUM (
    'LUTANDO',
    'VITORIA',
    'DERROTA'
    );

CREATE TYPE fighters AS ENUM (
    'INIMIGO',
    'ALIADO'
    );

CREATE TYPE turn_type AS ENUM (
    'ATAQUE',
    'DEFESA'
    );

SELECT * FROM battles;

INSERT INTO battles (character_id, monster_id, characterLife, enemyLife, whoStarts, turn, turn_type, result)
VALUES (2, 1, 20, 42, 'INIMIGO', 1, 'ATAQUE', 'LUTANDO');

DROP TABLE battles;
