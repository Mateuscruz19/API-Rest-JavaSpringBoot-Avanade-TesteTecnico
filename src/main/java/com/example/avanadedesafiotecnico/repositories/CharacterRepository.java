package com.example.avanadedesafiotecnico.repositories;

import com.example.avanadedesafiotecnico.entities.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {}
