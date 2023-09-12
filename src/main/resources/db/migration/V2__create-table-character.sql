CREATE TABLE character (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    class_id INTEGER NOT NULL CHECK (class_id >= 1 AND class_id <= 6)
);


SELECT * FROM character;

DROP TABLE character;
