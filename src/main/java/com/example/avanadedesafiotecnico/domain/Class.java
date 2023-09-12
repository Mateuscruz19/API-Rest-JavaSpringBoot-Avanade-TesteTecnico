package com.example.avanadedesafiotecnico.domain;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "class")
@Table(name = "class")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Class {
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
    private int type;
}


