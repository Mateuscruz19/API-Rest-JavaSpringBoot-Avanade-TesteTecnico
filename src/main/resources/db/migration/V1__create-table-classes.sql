CREATE TYPE classe_type AS ENUM (
    'GUERREIRO',
    'BARBARO',
    'CAVALEIRO',
    'ORC',
    'GIGANTE',
    'LOBISOMEN'
);


CREATE TYPE classe_faccao AS ENUM (
    'HEROI',
    'MONSTRO'
);

CREATE TABLE classe (
      id SERIAL PRIMARY KEY,
      nome VARCHAR(255),
      vida INT,
      forca INT,
      defesa INT,
      agilidade INT,
      quantidade_dados INT,
      faces_do_dado INT,
      tipo classe_type,
      faccao classe_faccao
);