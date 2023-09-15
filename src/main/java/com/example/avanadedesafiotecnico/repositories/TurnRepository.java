package com.example.avanadedesafiotecnico.repositories;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.entities.Turn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnRepository extends JpaRepository<Turn, Long> { }
