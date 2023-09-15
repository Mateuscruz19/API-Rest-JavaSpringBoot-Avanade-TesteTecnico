package com.example.avanadedesafiotecnico.DTOs;

import com.example.avanadedesafiotecnico.entities.Character;
import lombok.Data;

@Data
public class CreateCharacterResponseDTO {
    private String message;
    private Character character;
}
