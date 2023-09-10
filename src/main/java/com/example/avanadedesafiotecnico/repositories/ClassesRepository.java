package com.example.avanadedesafiotecnico.repositories;

import com.example.avanadedesafiotecnico.domain.classes.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classes, Long> {
   List<Classes> findAllClasses();
}
