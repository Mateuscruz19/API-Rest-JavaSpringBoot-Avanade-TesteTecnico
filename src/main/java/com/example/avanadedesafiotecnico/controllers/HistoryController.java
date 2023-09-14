package com.example.avanadedesafiotecnico.controllers;


import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private BattleRepository battleRepository;

    @GetMapping("/battles")
    public ResponseEntity<Page<Battle>> findAllBattles() {
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Battle> battlesPage = battleRepository.findAll(pageRequest);
        return ResponseEntity.ok(battlesPage);
    }

    @GetMapping("/battles/{id}")
    public ResponseEntity findBattleById(Long id) {
        try {
            var battle = battleRepository.findById(id);
            return ResponseEntity.ok(battle.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batalha nao encontrada!");
        }
    }

}
