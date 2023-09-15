package com.example.avanadedesafiotecnico.DTOs;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.entities.Turn;
import lombok.Data;

@Data
public class StartBattleResponseDTO {
    private int userDiceResult;
    private int enemyDiceResult;
    private String message;
    private Turn turn;
}
