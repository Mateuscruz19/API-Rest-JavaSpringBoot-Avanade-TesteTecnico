package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ClassRepository classRepository;

    @PostMapping("/start")
    public void startGame() {
        System.out.println("Game started");
    }
    @PostMapping("/attack")
    public void attack() {
        System.out.println("Attack");
    }
    @PostMapping("/defense")
    public void defense() {
        System.out.println("Defense");
    }
    @PostMapping("/damageCalculation")
    public void damageCalculation() {
        System.out.println("Damage Calculation");
    }
}
