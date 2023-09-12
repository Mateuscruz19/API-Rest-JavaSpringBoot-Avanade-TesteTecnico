package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassRepository classRepository;
    @GetMapping
    public ResponseEntity findAllClasses() {
        var allClasses = classRepository.findAll();
        return ResponseEntity.ok(allClasses);
    }
}
