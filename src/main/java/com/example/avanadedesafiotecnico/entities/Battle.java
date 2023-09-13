package com.example.avanadedesafiotecnico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "battles")
@Table(name = "battles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long character_id;
    private Long monster_id;
    private int characterLife;
    private int enemyLife;
    @Enumerated(EnumType.STRING)
    private Fighters whoStarts;
    private int turn;
    @Enumerated(EnumType.STRING)
    private Turn_type turn_type;
    @Enumerated(EnumType.STRING)
    private Battle_result result;

}
