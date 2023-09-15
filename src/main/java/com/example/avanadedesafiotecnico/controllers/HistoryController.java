package com.example.avanadedesafiotecnico.controllers;


import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import com.example.avanadedesafiotecnico.repositories.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private TurnRepository turnRepository;


    @GetMapping("/battles")
    public ResponseEntity<Page<Battle>> findAllBattles() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Battle> battlesPage = battleRepository.findAll(pageRequest);
        return ResponseEntity.ok(battlesPage);
    }

    @GetMapping("/battles/{id}")
    public ResponseEntity findBattleById(@PathVariable Long id) {
        var battle = battleRepository.findById(id);
        if (battle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhuma batalha!");
        }
        return ResponseEntity.ok(battle);
    }

    @GetMapping("/battles/log")
    public ResponseEntity findBattleLog() {
        var turn = turnRepository.findAll();
        if (turn.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma batalha encontrada!");
        }
        return ResponseEntity.ok(turn);
    }

}
