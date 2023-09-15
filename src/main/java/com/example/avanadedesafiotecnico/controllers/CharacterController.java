package com.example.avanadedesafiotecnico.controllers;
import com.example.avanadedesafiotecnico.entities.Character;
import com.example.avanadedesafiotecnico.DTOs.CharacterRequestDTO;
import com.example.avanadedesafiotecnico.DTOs.CreateCharacterResponseDTO;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public ResponseEntity createCharacter(@RequestBody CharacterRequestDTO characterRequest) {
       var classExists = classRepository.findById(characterRequest.getClass_id());

       if (classExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhuma classe existente!");
       }

       if (characterRequest.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome do personagem nao pode ser vazio!");
       }

       try {
           var character = new Character();
           character.setName(characterRequest.getName());
           character.setClass_id(characterRequest.getClass_id());
           characterRepository.save(character);

           var response = new CreateCharacterResponseDTO();
           response.setMessage("Personagem criado com sucesso!");
           response.setCharacter(character);

           return ResponseEntity.status(HttpStatus.CREATED).body(response);
       } catch (DataAccessException e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar seu personagem, tente novamente.");
       }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCharacter(@PathVariable Long id) {
        var character = characterRepository.findById(id);

        if (character.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
        }

        if(character.get().getId() == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce nao pode deletar o Rei Orc!Ele é seu inimigo.");
        }

        try{
            characterRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Seu personagem foi deletado com sucesso!");
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar seu personagem, tente novamente.");
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity updateCharacters(@PathVariable Long id, @RequestBody CharacterRequestDTO characterRequestDTO) {
        var characterExists = characterRepository.findById(id);
        if (characterExists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
        }

        if(characterExists.get().getId() == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce nao pode atualizar o Rei Orc!Ele é seu inimigo.");
        }

        try{
            characterExists.get().setName(characterRequestDTO.getName());
            characterExists.get().setClass_id(characterRequestDTO.getClass_id());
            characterRepository.save(characterExists.get());

            var response = new CreateCharacterResponseDTO();
            response.setMessage("Personagem atualizado com sucesso!");
            response.setCharacter(characterExists.get());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar seu personagem, tente novamente.");
        }

    }
}
