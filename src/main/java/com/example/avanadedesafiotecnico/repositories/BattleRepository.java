package com.example.avanadedesafiotecnico.repositories;

import com.example.avanadedesafiotecnico.entities.Battle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {}
