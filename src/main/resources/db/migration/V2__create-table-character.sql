CREATE TABLE character (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    class_id INTEGER NOT NULL
);

insert into character (name, class_id) values ('Rei Orc', 4);

SELECT * FROM character;

