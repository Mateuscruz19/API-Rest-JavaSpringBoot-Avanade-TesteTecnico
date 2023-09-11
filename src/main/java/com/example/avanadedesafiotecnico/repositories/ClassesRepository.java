package com.example.avanadedesafiotecnico.repositories;


import com.example.avanadedesafiotecnico.domain.classes.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassesRepository extends JpaRepository<Classe, Long> {}
