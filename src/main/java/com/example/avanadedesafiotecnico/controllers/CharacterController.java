package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.entities.Character;
import com.example.avanadedesafiotecnico.payloads.request.CharacterRequest;
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

    @GetMapping("/{id}")
    public ResponseEntity findCharacterById(@PathVariable Long id) {
        var character = characterRepository.findById(id);
        if (character.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(character.get());
    }

    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody CharacterRequest characterRequestDTO) {
        var character = new Character();
        character.setName(characterRequestDTO.getName());
        character.setClass_id(characterRequestDTO.getClass_id());

        characterRepository.save(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCharacter(@PathVariable Long id) {
        characterRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCharacters(@PathVariable Long id, @RequestBody CharacterRequest characterRequestDTO) {
        var character = characterRepository.findById(id);
        if (character.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        character.get().setName(characterRequestDTO.getName());
        character.get().setClass_id(characterRequestDTO.getClass_id());

        characterRepository.save(character.get());

        return ResponseEntity.noContent().build().ok(character.get());
    }
}
