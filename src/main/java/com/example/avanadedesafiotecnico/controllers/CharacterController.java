package com.example.avanadedesafiotecnico.controllers;
import com.example.avanadedesafiotecnico.entities.Character;
import com.example.avanadedesafiotecnico.payloads.request.CharacterRequest;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/character")
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ClassRepository classRepository;
    @GetMapping
    public ResponseEntity findAllCharacters() {
        var allPersonagem = characterRepository.findAll();
        return ResponseEntity.ok(allPersonagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity findCharacterById(@PathVariable Long id) {
        var character = characterRepository.findById(id);
        if (character.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
        }
        return ResponseEntity.ok(character.get());
    }

    @PostMapping
    public ResponseEntity createCharacter(@RequestBody CharacterRequest characterRequestDTO) {
       var classExists = classRepository.findById(characterRequestDTO.getClass_id());
        System.out.println(classExists);

       if (classExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhuma classe existente!");
       }

       if (characterRequestDTO.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome do personagem nao pode ser vazio!");
       }

       var character = new Character();
       character.setName(characterRequestDTO.getName());
       character.setClass_id(characterRequestDTO.getClass_id());

       characterRepository.save(character);

       return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCharacter(@PathVariable Long id) {
        var character = characterRepository.findById(id);
        if (character.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
        }
        characterRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCharacters(@PathVariable Long id, @RequestBody CharacterRequest characterRequestDTO) {
        var character = characterRepository.findById(id);
        if (character.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
        }

        character.get().setName(characterRequestDTO.getName());
        character.get().setClass_id(characterRequestDTO.getClass_id());

        characterRepository.save(character.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(character);
    }
}
