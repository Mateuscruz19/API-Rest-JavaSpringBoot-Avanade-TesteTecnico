package com.example.avanadedesafiotecnico.services.utils;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindBattleByIdService {

    private final BattleRepository battleRepository;

    @Autowired
    public FindBattleByIdService(BattleRepository battleRepository) {
        this.battleRepository = battleRepository;
    }

    public Battle findBattleById(Long id) {
        return battleRepository.findById(id)
                .orElse(null);
    }
}
