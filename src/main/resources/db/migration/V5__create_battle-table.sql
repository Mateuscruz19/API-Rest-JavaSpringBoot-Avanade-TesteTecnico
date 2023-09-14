CREATE TABLE battle (
                         id SERIAL PRIMARY KEY,
                         character_id INT NOT NULL,
                         monster_id INT DEFAULT 1 NOT NULL,
                         who_starts VARCHAR(10) NOT NULL,
                         turn INT NOT NULL,
                         status VARCHAR(15) DEFAULT 'EM_ANDAMENTO'
);

CREATE TABLE turn (
                        id SERIAL PRIMARY KEY,
                        turn INT NOT NULL,
                        FOREIGN KEY (turn) REFERENCES battle (turn),
                        character_life INT NOT NULL,
                        enemy_life INT DEFAULT 42 NOT NULL,
                        who_turn VARCHAR(10) NOT NULL,
                        turn_type VARCHAR(10) NOT NULL,
                        atual_attack_number INT DEFAULT 1 NOT NULL,
                        atual_defense_number INT DEFAULT 1 NOT NULL,
                        turn_number INT NOT NULL,
                        who_damage VARCHAR(10) NOT NULL,
                        battle_id INT NOT NULL,
                        FOREIGN KEY (battle_id) REFERENCES battle (id)
    );


SELECT * FROM battle;

INSERT INTO battle (character_id, monster_id, character_life, enemy_life, who_starts, turn,who_turn,who_damage, turn_type, status)
VALUES (2, 1, 20, 42, 'INIMIGO', 1, 'INIMIGO','NONE', 'ATAQUE', 'EM_ANDAMENTO');

DROP TABLE battle;
DELETE FROM battle where id = 8;
DROP TYPE fighters;
DROP TYPE turn_type;

