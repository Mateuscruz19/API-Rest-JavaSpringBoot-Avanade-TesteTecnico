CREATE TYPE class_type AS ENUM (
    'GUERREIRO',
    'BARBARO',
    'CAVALEIRO',
    'ORC',
    'GIGANTE',
    'LOBISOMEN'
);

CREATE TABLE class (
      id SERIAL PRIMARY KEY,
      type classe_type,
      life INT,
      strength INT,
      defense INT,
      agility INT,
      quantity_data INT,
      data_faces INT
);

SELECT * FROM class;

