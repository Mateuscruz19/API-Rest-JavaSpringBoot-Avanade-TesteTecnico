package com.example.avanadedesafiotecnico.DTOS;

import java.util.ArrayList;
import java.util.List;


class Personagem {
    private final String nome;
    private final int vida;
    private final int forca;
    private final int defesa;
    private final int agilidade;
    private final int quantidadeDados;
    private final int facesDoDado;

    public Personagem(String nome,
                      int vida,
                      int forca,
                      int defesa,
                      int agilidade,
                      int quantidadeDados,
                      int facesDoDado) {
        this.nome = nome;
        this.vida = vida;
        this.forca = forca;
        this.defesa = defesa;
        this.agilidade = agilidade;
        this.quantidadeDados = quantidadeDados;
        this.facesDoDado = facesDoDado;
    }
}
class PersonagensDisponiveis {
    private final List<Personagem> personagens;

    public PersonagensDisponiveis() {

    personagens = new ArrayList<>();
    personagens.add(new Personagem("Guerreiro", 20, 7, 5, 6, 1, 12));
    personagens.add(new Personagem("Bárbaro", 21, 10, 2, 5, 2, 8));
    personagens.add(new Personagem("Cavaleiro", 26, 6, 8, 3, 2, 6));
    personagens.add(new Personagem("Orc", 42, 7, 1, 2, 3, 4));
    personagens.add(new Personagem("Gigante", 34, 10, 4, 4, 2, 6));
    personagens.add(new Personagem("Lobisomen", 34, 7, 4, 7, 2, 4));
    }

    public List<Personagem> getAllPersonagens() {
        return personagens;
    }

}


