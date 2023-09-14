CREATE TABLE battle (
                         id SERIAL PRIMARY KEY,
                         character_id INT NOT NULL,
                         monster_id INT DEFAULT 1 NOT NULL,
                         character_life INT NOT NULL,
                         enemy_life INT DEFAULT 42 NOT NULL,
                         who_starts VARCHAR(10) NOT NULL,
                         turn INT DEFAULT 1 NOT NULL,
                         turn_type VARCHAR(10) NOT NULL,
                         status VARCHAR(15) DEFAULT 'EM_ANDAMENTO'
);

SELECT * FROM battle;

INSERT INTO battle (character_id, monster_id, character_life, enemy_life, who_starts, turn, turn_type, status)
VALUES (2, 1, 20, 42, 'INIMIGO', 1, 'ATAQUE', 'EM_ANDAMENTO');

DROP TABLE battle;
DELETE FROM battle where id = 8;
DROP TYPE fighters;
DROP TYPE turn_type;

