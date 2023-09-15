package com.example.avanadedesafiotecnico.DTOs;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.entities.Turn;
import lombok.Data;

@Data
public class BattleResponseDTO {
    private String message;
    private Battle battle;
}
