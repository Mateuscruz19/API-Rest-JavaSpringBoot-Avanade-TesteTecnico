package com.example.avanadedesafiotecnico.services.utils;

import com.example.avanadedesafiotecnico.entities.Class;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;

public class RandomUtils {
    private static ClassRepository classRepository;

    public static int rollDice() {
        return (int) (Math.random() * 20) + 1;
    }

    public static Class getClassById(Long classId) {
        return classRepository.findById(classId).orElse(null);
    }

    public static String getStartMessage(int starter) {
        return (starter == 1) ? "O jogador comeca atacando!" : "O inimigo comeca atacando!";
    }
}