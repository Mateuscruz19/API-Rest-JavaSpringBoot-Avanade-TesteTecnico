package com.example.avanadedesafiotecnico.domain.personagem;

import com.example.avanadedesafiotecnico.domain.personagem.PersonagemType;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "personagems")
@Table(name = "personagems")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Personagem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int vida;
    private int forca;
    private int defesa;
    private int agilidade;
    private int quantidadeDados;
    private int facesDoDado;
    @Enumerated(EnumType.STRING)
    private PersonagemType tipo;

    public Personagem() {

    }
}


