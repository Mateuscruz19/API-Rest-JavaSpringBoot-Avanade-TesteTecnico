package com.example.avanadedesafiotecnico;

import com.example.avanadedesafiotecnico.controllers.CharacterController;
import com.example.avanadedesafiotecnico.entities.Character;
import com.example.avanadedesafiotecnico.DTOs.CharacterRequestDTO;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class CharacterControllerTest {

    private CharacterController characterController;

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private ClassRepository classRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        characterController = new CharacterController(characterRepository, classRepository);
    }

    @Test
    public void testFindCharacterById() {
        Long characterId = 1L;
        Character character = new Character();
        character.setId(characterId);

        when(characterRepository.findById(characterId)).thenReturn(Optional.of(character));

        ResponseEntity responseEntity = characterController.findCharacterById(characterId);

        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert ((Character) responseEntity.getBody()).getId().equals(characterId);

        verify(characterRepository, times(1)).findById(characterId);
    }

    @Test
    public void testCreateCharacter() {
        CharacterRequestDTO requestDTO = new CharacterRequestDTO();
        requestDTO.setName("CharacterName");
        requestDTO.setClass_id(1L);

        when(classRepository.findById(requestDTO.getClass_id())).thenReturn(Optional.of(new com.example.avanadedesafiotecnico.entities.Class()));

        ResponseEntity responseEntity = characterController.createCharacter(requestDTO);

        assert responseEntity.getStatusCode() == HttpStatus.CREATED;

        verify(characterRepository, times(1)).save(any(Character.class));
    }


}
