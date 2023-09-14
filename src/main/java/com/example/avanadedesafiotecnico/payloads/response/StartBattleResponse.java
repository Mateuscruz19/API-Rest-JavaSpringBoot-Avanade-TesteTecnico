package com.example.avanadedesafiotecnico.payloads.response;

import com.example.avanadedesafiotecnico.entities.Battle;
import lombok.Data;

@Data
public class StartBattleResponse {
    private int userDiceResult;
    private int enemyDiceResult;
    private String message;
    private Battle battle;
}
