package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.domain.personagem.Personagem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/personagem")
public class PersonagemController {
    @GetMapping
    public ResponseEntity findAllPersonagem() {
        return ResponseEntity.ok("Funcionou");
    }
}
