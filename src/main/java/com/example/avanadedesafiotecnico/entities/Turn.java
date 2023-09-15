package com.example.avanadedesafiotecnico.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "turn")
@Table(name = "turn")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "battle_id", referencedColumnName = "id", nullable = false)
    private Battle battle;
    @Column(name = "turn", nullable = false)
    private int turnNumber;
    @Column(name = "character_life", nullable = false)
    private int characterLife;
    @Column(name = "enemy_life", nullable = false)
    private int enemyLife;
    @Column(name = "who_turn", nullable = false, length = 10)
    private int whoTurn;
    @Column(name = "turn_type", nullable = false, length = 10)
    private int turnType;
    @Column(name = "atual_attack_number", nullable = false)
    private int atualAttackNumber;
    @Column(name = "atual_defense_number", nullable = false)
    private int atualDefenseNumber;
    @Column(name = "who_damage", nullable = false, length = 10)
    private int whoDamage;
}


