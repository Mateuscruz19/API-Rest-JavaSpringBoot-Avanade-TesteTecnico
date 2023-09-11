package com.example.avanadedesafiotecnico.domain.classes;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "classes")
@Table(name = "classe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int vida;
    private int forca;
    private int defesa;
    private int agilidade;
    private int quantidadeDados;
    private int facesDoDado;
    @Enumerated(EnumType.STRING)
    private ClasseType tipo;
    @Enumerated(EnumType.STRING)
    private ClasseFaccao faccao;


}