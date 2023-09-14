package com.example.avanadedesafiotecnico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "battle")
@Table(name = "battle")
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
    private int character_life;
    private int  enemy_life;
    private String who_starts;
    private int atual_attack_number;
    private int atual_defense_number;
    private int turn;
    private String who_turn;
    private String who_damage;
    private String turn_type;
    private String status;
}
