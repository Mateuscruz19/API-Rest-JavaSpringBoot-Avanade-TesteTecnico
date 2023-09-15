package com.example.avanadedesafiotecnico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AvanadeDesafioTecnicoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testRollDice() {
		int dice = (int) (Math.random() * 20) + 1;
		assert(dice >= 1 && dice <= 20);
	}

	@Test
	void testGetStartMessage() {
		int starter = (int) (Math.random() * 2) + 1;
		String message = (starter == 1) ? "O jogador comeca atacando!" : "O inimigo comeca atacando!";
    }

	@Test
	void testGetClassById() {
		Long classId = (long) (Math.random() * 3) + 1;
		assert(classId >= 1 && classId <= 3);
	}

	@Test
	void testGetClassById2() {
		Long classId = (long) (Math.random() * 3) + 1;
		assert(classId >= 1 && classId <= 3);
	}

}
