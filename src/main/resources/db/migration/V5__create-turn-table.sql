CREATE TABLE turn (
                      id SERIAL PRIMARY KEY,
                      turn_number INT DEFAULT 1 NOT NULL,
                      character_life INT NOT NULL,
                      enemy_life INT DEFAULT 42 NOT NULL,
                      who_turn INT NOT NULL,
                      turn_type INT NOT NULL,
                      atual_attack_number INT DEFAULT 1 NOT NULL,
                      atual_defense_number INT DEFAULT 1 NOT NULL,
                      who_damage INT NOT NULL
);


SELECT * FROM turn;

-- Path: src\main\resources\db\migration\V6__create-attack-table.sql