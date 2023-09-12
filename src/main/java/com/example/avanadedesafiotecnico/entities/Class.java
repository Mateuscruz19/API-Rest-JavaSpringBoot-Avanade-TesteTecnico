package com.example.avanadedesafiotecnico.entities;
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
    @Enumerated(EnumType.STRING)
    private ClassType type;
    private int life;
    private int strength;
    private int defense;
    private int agility;
    private int quantity_data;
    private int data_faces;
}
