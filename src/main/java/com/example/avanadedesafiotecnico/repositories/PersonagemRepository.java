package com.example.avanadedesafiotecnico.repositories;

import com.example.avanadedesafiotecnico.domain.personagem.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
       List<Personagem> createPersonagem();
       Optional<Personagem> findPersonagemById(Long id);
       List<Personagem> findAllPersonagem();
       Optional<Personagem> deletePersonagem();
       Optional<Personagem> updatePersonagem();
}
