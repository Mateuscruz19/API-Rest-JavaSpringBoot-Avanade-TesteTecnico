package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.repositories.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClasseController {
    @Autowired
    private ClassesRepository classeRepository;
    @GetMapping
    public ResponseEntity findAllClasses() {
        var allClasses = classeRepository.findAll();
        return ResponseEntity.ok(allClasses);
    }
}
