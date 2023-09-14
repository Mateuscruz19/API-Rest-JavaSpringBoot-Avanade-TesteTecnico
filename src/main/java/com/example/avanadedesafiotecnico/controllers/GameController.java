package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.payloads.response.AttackResponse;
import com.example.avanadedesafiotecnico.payloads.response.StartBattleResponse;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


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
        battle.setTurn_type("ATAQUE");
        battle.setAtual_attack_number(0);
        battle.setAtual_defense_number(0);

        if(starter == "INIMIGO") {
            battle.setWho_turn("INIMIGO");
        } else {
            battle.setWho_turn("ALIADO");
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

    @PostMapping("/attack/{id}")
    public ResponseEntity attack(@PathVariable Long id) {
        var battle = battleRepository.findById(id);

        if (battle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batalha nao encontrada!");
        }

        var battleOptional = battle.get();

        if ("DEFESA".equals(battleOptional.getTurn_type())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao e o round de ataque!");
        }

        int attackDice = (int) (Math.random() * 12) + 1;

        Long character;
        if ("ALIADO".equals(battleOptional.getWho_turn())) {
            character = battleOptional.getCharacter_id();
        } else {
            character = battleOptional.getMonster_id();
        }

        Long classId = characterRepository.findById(character).get().getClass_id();
        int characterStrangth = classRepository.findById(classId).get().getStrength();
        int characterAgility = classRepository.findById(classId).get().getAgility();
        int atual_attack_number = attackDice + characterStrangth + characterAgility;

        battleOptional.setAtual_attack_number(atual_attack_number);
        battleOptional.setTurn_type("DEFESA");

        if ("ALIADO".equals(battleOptional.getWho_turn())) {
            battleOptional.setWho_turn("INIMIGO");
        } else {
            battleOptional.setWho_turn("ALIADO");
        }

        try {
            battleRepository.save(battleOptional);
            var response = new AttackResponse();
            response.setMessage("O dado de ataque ficou em: " + atual_attack_number + " agora e a vez do " + battleOptional.getWho_turn() + " defender!");
            response.setBattle(battleOptional);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atacar, tente novamente.");
        }
    }


    @PostMapping("/defense/{id}")
    public ResponseEntity defense(@PathVariable Long id) {
        var battle = battleRepository.findById(id);

        if (battle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batalha nao encontrada!");
        }

        var battleOptional = battle.get();

        if (Objects.equals(battleOptional.getWho_turn(), "ATAQUE") || Objects.equals(battleOptional.getTurn_type(), "DANO")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao e o round de defesa!");
        }
            return ResponseEntity.ok("Defense");
    }
    @PostMapping("/damageCalculation")
    public ResponseEntity damageCalculation(@RequestParam("attackerId") Long attackerId, @RequestParam("defenderId") Long defenderId) {
        // Implemente o cálculo de dano com base nos IDs dos personagens envolvidos.
        // Calcule o dano de acordo com as regras do jogo.
        // Atualize os Pontos de Vida (PV) do defensor e registre o resultado no histórico.
        return ResponseEntity.ok("Damage Calculation");
    }
}

