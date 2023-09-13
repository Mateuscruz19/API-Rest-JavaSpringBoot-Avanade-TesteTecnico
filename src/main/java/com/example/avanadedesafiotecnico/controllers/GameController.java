package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.entities.Fighters;
import com.example.avanadedesafiotecnico.payloads.response.StartBattleResponse;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.avanadedesafiotecnico.entities.Fighters.ALIADO;
import static com.example.avanadedesafiotecnico.entities.Fighters.INIMIGO;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ClassRepository classRepository;

    @PostMapping("/start/{id}")
    public ResponseEntity startGame(@PathVariable Long id) {
        var character = characterRepository.findById(id);
        var orc = characterRepository.findById(1L);
        if (character.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
        }

        if (character.get().getId() == orc.get().getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce nao pode escolher o Rei Orc(seu adversario) como personagem, escolha o seu personagem!");
        }

        int userDice = (int) (Math.random() * 20) + 1;
        int enemyDice = (int) (Math.random() * 20) + 1;
        String status = null;
        Fighters starter = null;
        if (userDice > enemyDice) {
            starter = INIMIGO;
            status = "Defenda-se!";
        } else if (enemyDice >= userDice) {
            starter = ALIADO;
            status = "Ataque!";
        }

//        var responsee = new StringBuilder();
//        responsee.append("O dado do usuario deu: " + userDice + "\n");
//        responsee.append("O dado do inimigo deu: " + enemyDice + "\n");
//        responsee.append("O " + starter + " comeca o jogo! " + status + "\n");
//
        var response = new StartBattleResponse();
        response.setUserDiceResult(userDice);
        response.setEnemyDiceResult(enemyDice);
        response.setMessage("O " + starter + " comeca o jogo! " + status);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/attack")
    public ResponseEntity<String> attack(@RequestParam("attackerId") Long attackerId, @RequestParam("defenderId") Long defenderId) {
        // Implemente a lógica de ataque aqui com base nos IDs dos personagens envolvidos.
        // Calcule o resultado do ataque de acordo com as regras do jogo.
        // Atualize os Pontos de Vida (PV) do defensor e registre o resultado no histórico.
        return ResponseEntity.ok("Attack");
    }

    @PostMapping("/defense")
    public ResponseEntity<String> defense(@RequestParam("defenderId") Long defenderId) {
        // Implemente a lógica de defesa aqui com base no ID do personagem defensor.
        // Calcule a defesa do defensor de acordo com as regras do jogo.
        // Registre o resultado da defesa no histórico.
        return ResponseEntity.ok("Defense");
    }

    @PostMapping("/damageCalculation")
    public ResponseEntity<String> damageCalculation(@RequestParam("attackerId") Long attackerId, @RequestParam("defenderId") Long defenderId) {
        // Implemente o cálculo de dano com base nos IDs dos personagens envolvidos.
        // Calcule o dano de acordo com as regras do jogo.
        // Atualize os Pontos de Vida (PV) do defensor e registre o resultado no histórico.
        return ResponseEntity.ok("Damage Calculation");
    }
}

