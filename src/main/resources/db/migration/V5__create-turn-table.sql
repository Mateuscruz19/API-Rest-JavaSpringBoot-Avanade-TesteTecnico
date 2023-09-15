CREATE TABLE turn (
                      id SERIAL PRIMARY KEY,
                      battle_id INT NOT NULL,
                      FOREIGN KEY (battle_id) REFERENCES battle (id),
                      turn INT DEFAULT 1 NOT NULL,
                      character_life INT NOT NULL,
                      enemy_life INT DEFAULT 42 NOT NULL,
                      who_turn INT NOT NULL,
                      turn_type INT NOT NULL,
                      atual_attack_number INT DEFAULT 1 NOT NULL,
                      atual_defense_number INT DEFAULT 1 NOT NULL,
                      who_damage INT NOT NULL
);

INSERT INTO turn (turn, character_life, enemy_life, who_turn, turn_type, atual_attack_number, atual_defense_number, who_damage, battle_id) VALUES (1, 100, 42, 1, 1, 1, 1, 1, 1);

SELECT * FROM turn;


-- Path: src\main\resources\db\migration\V6__create-attack-table.sql