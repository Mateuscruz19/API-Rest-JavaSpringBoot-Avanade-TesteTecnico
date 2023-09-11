package com.example.avanadedesafiotecnico.domain.personagem;


import com.example.avanadedesafiotecnico.domain.classes.Classe;
import com.example.avanadedesafiotecnico.domain.classes.ClasseType;
import jakarta.persistence.*;
import lombok.*;
@Entity(name = "personagens")
@Table(name = "personagens")
@Data
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Personagem {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private ClasseType classe;
    public Personagem() {

    }
}


