package com.example.avanadedesafiotecnico.controllers;

import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.payloads.response.AttackResponse;
import com.example.avanadedesafiotecnico.payloads.response.DamageResponse;
import com.example.avanadedesafiotecnico.payloads.response.StartBattleResponse;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.Collator;
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
        String starter = null;
        if (enemyDice > userDice) {
            starter = "INIMIGO";
        } else {
            starter = "ALIADO";
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
        battle.setWho_damage("NONE");

        if(starter.equals("INIMIGO")) {
            battle.setWho_turn("INIMIGO");
        } else {
            battle.setWho_turn("ALIADO");
        }
        try {
            battleRepository.save(battle);

            var response = new StartBattleResponse();
            response.setUserDiceResult(userDice);
            response.setEnemyDiceResult(enemyDice);
            response.setMessage("O " + starter + " comeca atacando! ");
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

        if("FINALIZADO".equals(battle.get().getStatus())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A batalha ja foi finalizada!");
        }

        var battleOptional = battle.get();

        if (!"ATAQUE".equals(battleOptional.getTurn_type())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao estamos no round de ataque!");
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
        battleOptional.setWho_damage(battleOptional.getWho_turn());

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

        if("FINALIZADO".equals(battle.get().getStatus())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A batalha ja foi finalizada!");
        }

        var battleOptional = battle.get();

        if (!"DEFESA".equals(battleOptional.getTurn_type())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao estamos no round de defesa!");
        }

        int defenseDice = (int) (Math.random() * 12) + 1;

        Long character;
        if ("ALIADO".equals(battleOptional.getWho_turn())) {
            character = battleOptional.getCharacter_id();
        } else {
            character = battleOptional.getMonster_id();
        }

        Long classId = characterRepository.findById(character).get().getClass_id();
        int characterDefense = classRepository.findById(classId).get().getDefense();
        int characterAgility = classRepository.findById(classId).get().getAgility();
        int atual_defence_number = defenseDice + characterDefense + characterAgility;

        battleOptional.setAtual_defense_number(atual_defence_number);
        battleOptional.setTurn_type("DANO");

        int atual_attack_number = battleOptional.getAtual_attack_number();
        boolean whoWins = atual_attack_number > atual_defence_number;
        String whoTurn = battleOptional.getWho_turn();

        if(!whoWins){
            if(Objects.equals(whoTurn, "ALIADO")){
                battleOptional.setWho_turn("INIMIGO");
            } else {
                battleOptional.setWho_turn("ALIADO");
            }
            battleOptional.setTurn_type("ATAQUE");
            battleOptional.setAtual_attack_number(0);
            battleOptional.setAtual_defense_number(0);
            battleOptional.setTurn(battleOptional.getTurn() + 1);
            battleOptional.setWho_damage(battleOptional.getWho_damage());

            battleRepository.save(battleOptional);

            var response =  new AttackResponse();
            response.setMessage("O " + whoTurn + " conseguiu defender o ataque, o dano nao sera calculado, hora do ataque!");
            response.setBattle(battleOptional);
            return ResponseEntity.ok(response);
        }

        try {
            battleRepository.save(battleOptional);
            var response = new AttackResponse();
            response.setMessage("O dado de defesa ficou em: " + atual_defence_number + "perdendo para o ataque de: " + atual_attack_number + " agora e a vez do " + battleOptional.getWho_turn() + " calcular o dano!");
            response.setBattle(battleOptional);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atacar, tente novamente.");
        }
    }

    @PostMapping("/damage/{id}")
    public ResponseEntity calculateDamage(@PathVariable Long id) {
        var battle = battleRepository.findById(id);

        if (battle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batalha não encontrada!");
        }

        if("FINALIZADO".equals(battle.get().getStatus())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A batalha ja foi finalizada!");
        }

        var battleOptional = battle.get();

        if (!"DANO".equals(battleOptional.getTurn_type())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao estamos no round de dano!");
        }


        Long characterId;
        if ("ALIADO".equals(battleOptional.getWho_turn())) {
            characterId = battleOptional.getCharacter_id();
        } else {
            characterId = battleOptional.getMonster_id();
        }

        int characterStrength;
        int quantity_data;
        int data_faces;

        var characterClass = characterRepository.findById(characterId).get().getClass_id();
        var characterClassOptional = classRepository.findById(characterClass);
        if (characterClassOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classe do personagem não encontrada!");
        }

        var characterStatus = characterClassOptional.get();
        characterStrength = characterStatus.getStrength();
        quantity_data = characterStatus.getQuantity_data();
        data_faces = characterStatus.getData_faces();


        int damage = 0;
        for (int i = 0; i < quantity_data; i++) {
            int dice = (int) (Math.random() * data_faces) + 1;
            damage += dice;
        }
        damage += characterStrength;

        if (damage < 0){
            damage = 0;
        }

        int enemyLife = battleOptional.getEnemy_life();
        int characterLife = battleOptional.getCharacter_life();

        if ("ALIADO".equals(battleOptional.getWho_damage())) {
            enemyLife -= damage;
            if (enemyLife < 0){
                enemyLife = 0;
            }
            battleOptional.setEnemy_life(enemyLife);
        } else {
            characterLife -= damage;
            if (characterLife < 0){
                enemyLife = 0;
            }
            battleOptional.setCharacter_life(characterLife);
        }

        battleOptional.setTurn_type("ATAQUE");
        battleOptional.setAtual_attack_number(0);
        battleOptional.setAtual_defense_number(0);
        battleOptional.setTurn(battleOptional.getTurn() + 1);
        battleOptional.setWho_damage("NONE");

        try {
            var response = new DamageResponse();
            if(enemyLife <= 0 || characterLife <= 0){
                battleOptional.setStatus("FINALIZADO");
               battleRepository.save(battleOptional);
                response.setMessage("O dano calculado foi de: " + damage + " agora o " + battleOptional.getWho_turn() + " tem " + battleOptional.getCharacter_life() + " de vida e o inimigo tem " + battleOptional.getEnemy_life() + " de vida! A batalha foi finalizada!");
                response.setBattle(battleOptional);
                return ResponseEntity.ok(response);
            }
          battleRepository.save(battleOptional);
            response.setMessage("O dano calculado foi de: " + damage + " agora o " + battleOptional.getWho_turn() + " tem " + battleOptional.getCharacter_life() + " de vida e o inimigo tem " + battleOptional.getEnemy_life() + " de vida!");
            response.setBattle(battleOptional);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao calcular o dano, tente novamente.");
        }
    }

}

