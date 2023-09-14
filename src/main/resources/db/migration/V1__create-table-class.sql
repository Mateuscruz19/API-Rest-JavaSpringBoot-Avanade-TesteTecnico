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
      life INT NOT NULL,
      strength INT NOT NULL,
      defense INT NOT NULL,
      agility INT NOT NULL,
      quantity_data INT NOT NULL,
      data_faces INT NOT NULL
);

SELECT * FROM class;

