package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.DTOs.BattleResponseDTO;
import com.example.avanadedesafiotecnico.DTOs.StartBattleResponseDTO;
import com.example.avanadedesafiotecnico.entities.Turn;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import com.example.avanadedesafiotecnico.repositories.TurnRepository;
import com.example.avanadedesafiotecnico.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private TurnRepository turnRepository;

    @Autowired
    private GameService gameService;
    @PostMapping("/start/{id}")
    public ResponseEntity startGame(@PathVariable Long id) {
        return gameService.startGame(id);
    }


    @PostMapping("/attack/{id}")
    public ResponseEntity attack(@PathVariable Long id) {
       return gameService.attack(id);
    }


//    @PostMapping("/defense/{id}")
//    public ResponseEntity defense(@PathVariable Long id) {
//    return gameService.defense(id);
//    }
//
//    @PostMapping("/damage/{id}")
//    public ResponseEntity calculateDamage(@PathVariable Long id) {
//        return gameService.calculateDamage(id);
//    }

}

