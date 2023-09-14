package com.example.avanadedesafiotecnico.payloads.response;

import com.example.avanadedesafiotecnico.entities.Battle;
import lombok.Data;

@Data
public class AttackResponse {
    String message;
    Battle battle;
}
