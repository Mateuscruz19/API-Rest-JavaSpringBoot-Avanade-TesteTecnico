CREATE TABLE battle (
                        id SERIAL PRIMARY KEY UNIQUE,
                        character_id INT NOT NULL,
                        monster_id INT DEFAULT 1 NOT NULL,
                        who_starts INT NOT NULL,
                        turn INT NOT NULL,
                        status INT DEFAULT 1
);


INSERT INTO battle (character_id, monster_id, who_starts, turn) VALUES (2, 1, 1, 1);


SELECT * FROM battle;


