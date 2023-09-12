package com.example.avanadedesafiotecnico.payloads.request;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CharacterRequest{
    private String name;
    private long class_id;
}

//public record CharacterRequestDTO(
//        String name,
//        int class_id
//) {}
