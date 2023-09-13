package com.example.avanadedesafiotecnico.payloads.response;

import com.example.avanadedesafiotecnico.entities.Character;
import lombok.Data;

@Data
public class CreateCharacterResponse {
    private String message;
    private Character character;
}
