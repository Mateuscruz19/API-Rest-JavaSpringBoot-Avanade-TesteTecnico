package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.domain.Character;
import com.example.avanadedesafiotecnico.domain.CharacterRequestDTO;
import com.example.avanadedesafiotecnico.domain.ClassType;

import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character")
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public ResponseEntity findAllCharacters() {
        var allPersonagem = characterRepository.findAll();
        return ResponseEntity.ok(allPersonagem);
    }

    @PostMapping
    public ResponseEntity<String> createPersonagem(@RequestBody CharacterRequestDTO characterRequestDTO) {
        var character = new Character();
        character.setName(character.getName());
        character.setClass_id(character.getClass_id());
        characterRepository.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonagem(@PathVariable Long id) {
        characterRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
