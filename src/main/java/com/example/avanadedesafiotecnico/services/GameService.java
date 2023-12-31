package com.example.avanadedesafiotecnico.services;

import com.example.avanadedesafiotecnico.DTOs.BattleResponseDTO;
import com.example.avanadedesafiotecnico.DTOs.StartBattleResponseDTO;
import com.example.avanadedesafiotecnico.entities.Battle;
import com.example.avanadedesafiotecnico.entities.Character;
import com.example.avanadedesafiotecnico.entities.Class;
import com.example.avanadedesafiotecnico.entities.Turn;
import com.example.avanadedesafiotecnico.repositories.BattleRepository;
import com.example.avanadedesafiotecnico.repositories.CharacterRepository;
import com.example.avanadedesafiotecnico.repositories.ClassRepository;
import com.example.avanadedesafiotecnico.repositories.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

import static com.example.avanadedesafiotecnico.services.utils.RandomUtils.*;

    @Service
    public class GameService {
        private final CharacterRepository characterRepository;
        private final ClassRepository classRepository;
        private final BattleRepository battleRepository;
        private final TurnRepository turnRepository;

        public GameService(CharacterRepository characterRepository, ClassRepository classRepository, BattleRepository battleRepository, TurnRepository turnRepository) {
            this.characterRepository = characterRepository;
            this.classRepository = classRepository;
            this.battleRepository = battleRepository;
            this.turnRepository = turnRepository;
        }

        public ResponseEntity startGame(Long id) {
            var characterOptional = characterRepository.findById(id);
            var orcOptional = characterRepository.findById(1L);

            if (characterOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse Id nao pertence a nenhum personagem!");
            }

            Character character = characterOptional.get();
            Character orc = orcOptional.orElse(null);

            assert orc != null;
            if (character.getId() == orc.getId()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Voce nao pode escolher o Rei Orc(seu adversario) como personagem, escolha o seu personagem!");
            }

            int userDice = (int) (Math.random() * 20) + 1;
            int enemyDice = (int) (Math.random() * 20) + 1;

            int starter;
            if (enemyDice > userDice) {
                starter = 2;
            } else {
                starter = 1;
            }

            var characterClassOptional = classRepository.findById(character.getClass_id());
            var characterClass = characterClassOptional.get();
            int characterLife = characterClass.getLife();

            int orcLife = classRepository.findById(orc.getClass_id()).get().getLife();

            Turn turn = new Turn();
            turn.setEnemyLife(orcLife);
            turn.setCharacterLife(characterLife);
            turn.setTurnType(1);
            turn.setAtualAttackNumber(0);
            turn.setAtualDefenseNumber(0);
            turn.setWhoDamage(3);

            Battle battle = new Battle();
            battle.setCharacterId(id);
            battle.setMonsterId(1L);
            battle.setStatus(1);
            battle.setWhoStarts(starter);
            battle.setTurnNumber(1);
            turn.setWhoTurn(starter);
            turn.setTurnNumber(1);

            battle.setTurn(turn);

            try {
                turnRepository.save(turn);
                battleRepository.save(battle);

                StartBattleResponseDTO response = new StartBattleResponseDTO();
                response.setUserDiceResult(userDice);
                response.setEnemyDiceResult(enemyDice);
                response.setBattle(battle);
                response.setMessage(getStartMessage(starter));

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao iniciar a luta, tente novamente.");
            }
        }

        public ResponseEntity attack(Long id) {
            var battle = battleRepository.findById(id);

            if (battle.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batalha nao encontrada!");
            }

            var battleOptional = battle.get();
            var turn = battleOptional.getTurn();

            if (turn.getTurnType() != 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao e o round de ataque!");
            }

            if (battleOptional.getStatus() == 2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A batalha ja foi finalizada!");
            }
            int attackDice = (int) (Math.random() * 12) + 1;

            Long character;
            if (turn.getWhoTurn() == 1) {
                character = battleOptional.getCharacterId();
            } else {
                character = battleOptional.getMonsterId();
            }

            Long classId = characterRepository.findById(character).get().getClass_id();
            int characterStrangth = classRepository.findById(classId).get().getStrength();
            int characterAgility = classRepository.findById(classId).get().getAgility();
            int atual_attack_number = attackDice + characterStrangth + characterAgility;

            turn.setAtualAttackNumber(atual_attack_number);
            turn.setTurnType(2);

            if (turn.getWhoTurn() == 1) {
                turn.setWhoTurn(2);
            } else {
                turn.setWhoTurn(1);
            }

            battleOptional.setTurn(turn);

            try {
                battleRepository.save(battleOptional);
                turnRepository.save(turn);

                var response = new BattleResponseDTO();
                if (turn.getWhoTurn() == 1) {
                    response.setMessage("O dado de ataque ficou em: " + atual_attack_number + " agora e a vez do aliado defender!");
                } else {
                    response.setMessage("O dado de ataque ficou em: " + atual_attack_number + " agora e a vez do inimigo defender!");
                }
                response.setBattle(battleOptional);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atacar, tente novamente.");
            }
        }

        public ResponseEntity defense(Long id) {
            var battle = battleRepository.findById(id);

            if (battle.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batalha nao encontrada!");
            }

            var battleOptional = battle.get();
            var turn = battleOptional.getTurn();

            if (battleOptional.getStatus() == 2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A batalha ja foi finalizada!");
            }

            if (turn.getTurnType() != 2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao e o round de defesa!");
            }

            int defenseDice = (int) (Math.random() * 12) + 1;

            Long character;
            if (turn.getWhoTurn() == 1) {
                character = battleOptional.getCharacterId();
            } else {
                character = battleOptional.getMonsterId();
            }


            Long classId = characterRepository.findById(character).get().getClass_id();
            int characterDefense = classRepository.findById(classId).get().getDefense();
            int characterAgility = classRepository.findById(classId).get().getAgility();
            int atual_defence_number = defenseDice + characterDefense + characterAgility;

            turn.setAtualDefenseNumber(atual_defence_number);

            int atual_attack_number = turn.getAtualAttackNumber();
            boolean userWins = atual_attack_number > atual_defence_number;
            int whoTurn = turn.getWhoTurn();

            if (!userWins) {
                if (whoTurn == 1) {
                    turn.setWhoTurn(2);
                } else {
                    turn.setWhoTurn(1);
                }
                turn.setTurnType(1);
                turn.setAtualAttackNumber(0);
                turn.setAtualDefenseNumber(0);
                battleOptional.setTurnNumber(battleOptional.getTurnNumber() + 1);
                turn.setWhoDamage(turn.getWhoTurn());

                battleOptional.setTurn(turn);

                turnRepository.save(turn);
                battleRepository.save(battleOptional);

                var response = new BattleResponseDTO();
                response.setMessage("O " + whoTurn + " conseguiu defender o ataque, o dano nao sera calculado,proximo turno, hora do ataque!");
                response.setBattle(battleOptional);
                return ResponseEntity.ok(response);
            }

            turn.setTurnType(3);
            battleOptional.setTurn(turn);

            try {
                battleRepository.save(battleOptional);
                turnRepository.save(turn);

                var response = new BattleResponseDTO();
                if (turn.getWhoTurn() == 1) {
                    response.setMessage("O dado de defesa ficou em: " + atual_defence_number + "perdendo para o ataque de: " + atual_attack_number + " agora e a vez do aliado calcular o dano!");
                } else {
                    response.setMessage("O dado de defesa ficou em: " + atual_defence_number + "perdendo para o ataque de: " + atual_attack_number + " agora e a vez do inimigo calcular o dano!");
                }

                response.setBattle(battleOptional);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atacar, tente novamente.");
            }
        }

        public ResponseEntity calculateDamage(Long id) {
            var battle = battleRepository.findById(id);

            if (battle.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batalha nao encontrada!");
            }

            var battleOptional = battle.get();
            var turn = battleOptional.getTurn();

            if(battleOptional.getStatus() == 2){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A batalha ja foi finalizada!");
            }

            if (turn.getTurnType() != 3) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao e o round de calculo de dano!");
            }


            Long characterId;
            if (turn.getWhoTurn() == 1) {
                characterId = battleOptional.getCharacterId();
            } else {
                characterId = battleOptional.getMonsterId();
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

            if (damage < 0) {
                damage = 0;
            }

            int enemyLife = turn.getEnemyLife();
            int characterLife = turn.getCharacterLife();

            if (turn.getWhoDamage() == 1) {
                enemyLife -= damage;
                if (enemyLife < 0) {
                    enemyLife = 0;
                }
                turn.setEnemyLife(enemyLife);
            } else {
                characterLife -= damage;
                if (characterLife < 0) {
                    enemyLife = 0;
                }
                turn.setCharacterLife(characterLife);
            }

            turn.setTurnType(1);
            turn.setAtualAttackNumber(0);
            turn.setAtualDefenseNumber(0);
            battleOptional.setTurnNumber(battleOptional.getTurnNumber() + 1);
            turn.setWhoDamage(3);
            battleOptional.setTurn(turn);

            try {
                var response = new BattleResponseDTO();
                if (enemyLife <= 0 || characterLife <= 0) {
                    battleOptional.setStatus(2);

                    battleRepository.save(battleOptional);
                    turnRepository.save(turn);

                    response.setMessage("O dano calculado foi de: " + damage + " agora o aliado tem " + turn.getCharacterLife() + " de vida e o inimigo tem " + turn.getEnemyLife() + " de vida! A batalha foi finalizada!");
                    response.setBattle(battleOptional);
                    return ResponseEntity.ok(response);
                }
                battleRepository.save(battleOptional);
                turnRepository.save(turn);

                response.setMessage("O dano calculado foi de: " + damage + " agora o aliado tem " + turn.getCharacterLife() + " de vida e o inimigo tem " + turn.getEnemyLife() + " de vida!Hora do ataque!");
                response.setBattle(battleOptional);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao calcular o dano, tente novamente.");
            }
        }
    };

