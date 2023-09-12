CREATE TABLE character (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    class_id INTEGER NOT NULL
);


SELECT * FROM character;

DROP TABLE character;
