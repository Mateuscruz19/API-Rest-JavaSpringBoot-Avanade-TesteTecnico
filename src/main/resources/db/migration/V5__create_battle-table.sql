CREATE TABLE battle (
                        id SERIAL PRIMARY KEY UNIQUE,
                        turn_id INT NOT NULL,
                        FOREIGN KEY (turn_id) REFERENCES turn (id),
                        character_id INT NOT NULL,
                        monster_id INT DEFAULT 1 NOT NULL,
                        who_starts INT NOT NULL,
                        turn_number INT NOT NULL,
                        status INT DEFAULT 1
);


SELECT * FROM battle;



