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
    @Column(name = "character_id", nullable = false)
    private Long characterId;
    @Column(name = "monster_id", nullable = false)
    private Long monsterId;
    @Column(name = "who_starts", nullable = false, length = 10)
    private int whoStarts;
    @Column(name = "turn", nullable = false)
    private int turn;
    @Column(name = "status", length = 15, columnDefinition = "VARCHAR(15) DEFAULT 'EM_ANDAMENTO'")
    private int status;
}
