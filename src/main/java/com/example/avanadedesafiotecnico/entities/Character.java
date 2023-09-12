package com.example.avanadedesafiotecnico.entities;




import jakarta.persistence.*;
import lombok.*;
@Entity(name = "character")
@Table(name = "character")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int class_id;

}


