package edu.metrostate.ics425.reversi.team_smsl.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics425.reversi.team_smsl.model.Game.Disk;

class GameTest {
	Game game = new Game();

	@Test
	void testInitialBoard() {
		Disk[] disks = game.getDisks();
		assertEquals(Disk.LIGHT, disks[27]);
		assertEquals(Disk.LIGHT, disks[36]);
		assertEquals(Disk.DARK, disks[28]);
		assertEquals(Disk.DARK, disks[35]);
	}
	
	@Test
	void testGetScore() {
		assertEquals(2, game.getDarkScore());
		assertEquals(2, game.getLightScore());
	}
	@Test 
	void testPlaceDisk() {
		assertEquals(Disk.DARK, game.getCurrentPlayer());
		game.placeDisk(43);
		Disk[] board = game.getDisks();
		assertEquals(Disk.DARK, board[43]);
	}
	
	@Test
	void testFlipDisks() {
		game.placeDisk(26);
		assertEquals(Disk.DARK, game.getDisks()[26]);
		assertEquals(Disk.DARK, game.getDisks()[27]);
	}
}
