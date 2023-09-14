package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.payloads.response.StartBattleResponse;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private BattleRepository battleRepository;

    @PostMapping("/start/{id}")
    public ResponseEntity startGame(@PathVariable Long id) {
        var characterOptional = characterRepository.findById(id);
        var orcOptional = characterRepository.findById(1L);

        if (characterOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
        }

        var character = characterOptional.get();
        var orc = orcOptional.orElse(null);

        assert orc != null;
        if (character.getId() == orc.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce nao pode escolher o Rei Orc(seu adversario) como personagem, escolha o seu personagem!");
        }

        int userDice = (int) (Math.random() * 20) + 1;
        int enemyDice = (int) (Math.random() * 20) + 1;
        String status = null;
        String starter = null;
        if (enemyDice > userDice) {
            starter = "INIMIGO";
            status = "Defenda-se!";
        } else {
            starter = "ALIADO";
            status = "Ataque!";
        }

        var characterClassOptional = classRepository.findById(character.getClass_id());
        if (characterClassOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classe do personagem nao encontrada!");
        }

        var characterClass = characterClassOptional.get();
        int characterLife = characterClass.getLife();

        Battle battle = new Battle();
        battle.setCharacter_id(id);
        battle.setMonster_id(1L);
        battle.setEnemy_life(42);
        battle.setCharacter_life(characterLife);
        battle.setWho_starts(starter);
        battle.setTurn(1);
        battle.setStatus("EM_ANDAMENTO");


        if(starter == "INIMIGO") {
            battle.setTurn_type("DEFESA");
        } else {
            battle.setTurn_type("ATAQUE");
        }
        try {
            battleRepository.save(battle);

            var response = new StartBattleResponse();
            response.setUserDiceResult(userDice);
            response.setEnemyDiceResult(enemyDice);
            response.setMessage("O " + starter + " comeca o jogo! " + status);
            response.setBattle(battle);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao iniciar a luta, tente novamente.");
        }
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

