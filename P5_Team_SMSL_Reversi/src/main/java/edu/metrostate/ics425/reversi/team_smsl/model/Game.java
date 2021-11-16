package edu.metrostate.ics425.reversi.team_smsl.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author skylar
 *
 */
public class Game implements Serializable {
	
	enum Disk {
		LIGHT, DARK, EMPTY;

		boolean isEmpty() {
			return this == null;
		}
	}
	enum Rows {
		HORIZONTAL(new int[][] { 
			{0, 1, 2, 3, 4, 5, 6, 7}, 
			{8, 9, 10, 11, 12, 13, 14, 15},
			{16, 17, 18, 19, 20, 21, 22, 23},
			{24, 25, 26, 27, 28, 29, 30, 31},
			{32, 33, 34, 35, 36, 37, 38, 39},
			{40, 41, 42, 43, 44, 45, 46, 47},
			{48, 49, 50, 51, 52, 53, 54, 55},
			{56, 57, 58, 59, 60, 61, 62, 63}
			}), 
		VERTICAL(new int[][] {
			{0, 8, 16, 24, 32, 40, 48, 56},
			{1, 9, 17, 25, 33, 41, 49, 57},
			{2, 10, 18, 26, 34, 42, 50, 58},
			{3, 11, 19, 27, 35, 43, 51, 59},
			{4, 12, 20, 28, 36, 44, 52, 60},
			{5, 13, 21, 29, 37, 45, 53, 61},
			{6, 14, 22, 30, 38, 46, 54, 62},
			{7, 15, 23, 31, 39, 47, 55, 63}
		}), 
		DIAGONAL(new int[][] {
			{40, 49, 48}, 
			{32, 41, 50, 59},
			{24, 33, 42, 51, 60}, 
			{16, 25, 34, 43, 52, 61},
			{8, 17, 26, 35, 44, 53, 62},
			{0, 9, 18, 27, 36, 45, 54, 63},
			{1, 10, 19, 28, 37, 46, 55},
			{2, 11, 20, 29, 38, 47},
			{3, 12, 21, 30, 39},
			{4, 13, 22, 31}, 
			{5, 14, 23},
			{16, 9, 2},
			{24, 17, 10, 3},
			{32, 25, 18, 11, 4},
			{40, 33, 26, 19, 12, 5},
			{48, 41, 34, 27, 20, 13, 6},
			{56, 49, 42, 35, 28, 21, 14, 7},
			{57, 50, 43, 36, 29, 22, 15},
			{58, 51, 44, 37, 30, 23},
			{59, 52, 45, 38, 31},
			{60, 53, 46, 39},
			{61, 54, 47}
		});
		
		private int[][] rows;
		
		Rows(int[][] rows) {
			this.rows = rows;
		}
	}
	
	/**
	 * Version of the bean
	 */
	private static final long serialVersionUID = 202111001L;
	
	private static final int NUM_DISKS = 64;
	private Disk[] disks;
	private Disk currentPlayer;
	
	/**
	 * No-arg constructor, initializes game
	 */
	public Game() {
		disks = new Disk[NUM_DISKS];
		currentPlayer = Disk.DARK;
		
		// set initial pieces
		disks[27] = Disk.LIGHT;
		disks[36] = Disk.LIGHT;
		disks[28] = Disk.DARK;
		disks[35] = Disk.DARK;
		for (Disk disk : disks) {
			if (disk == null) {
				disk = Disk.EMPTY;
			}
		}
	}
	
	/**
	 * Returns the disks on the board
	 * 
	 * @return the disks
	 */
	public Disk[] getDisks() {
		return disks.clone();
	}
	
	/**
	 * Returns the score of the game in an array
	 * with the scores of [Disk.DARK, Disk.LIGHT]
	 * 
	 * @return score of the game
	 */
	public int[] getScore() {
		int dark = 0;
		int light = 0;
		
		for (Disk disk : disks) {
			if (disk == Disk.DARK) {
				dark++;
			}
			if (disk == Disk.LIGHT) {
				light++;
			}
		}
		int[] scores = {dark, light};
		return scores;
	}
	
	/**
	 * Places disk on specified location
	 * If game is not over, set to next player's turn
	 * 
	 * @param loc location of the disk
	 */
	public void placeDisk(int loc) {
		if (isValidMove(loc)) {
			disks[loc] = currentPlayer;
			if (!isOver()) {
				nextPlayer();				
			}
		}
	}
	
	/**
	 * Returns Disk value of the current player
	 * 
	 * @return the currentPlayer
	 */
	public Disk getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Sets current player to next player
	 */
	public void nextPlayer() {
		this.currentPlayer = (getCurrentPlayer() == Disk.DARK) ? Disk.LIGHT : Disk.DARK;
	}

// TODO test findMoves method
	public int[] findMoves() {
		boolean rowStarted = false;
		ArrayList<Integer> activeRow = new ArrayList<Integer>();
		ArrayList<Integer> moves = new ArrayList<Integer>();
		
		// directions enum
		for (Rows direction : Rows.values()) {
			// rows in the direction
			for (var row : direction.rows) {
				// locations in the row
				for (var space : row) {
					if (currentPlayer == disks[space]) {
						while (! (disks[space] == Disk.EMPTY || (disks[space] == currentPlayer))) {
							rowStarted = true;
							activeRow.add(space);
						}
						if (rowStarted) {
							moves.add(space);
						}
					}
				}
			}
		}
		int[] allMoves = new int[moves.size()];
		for (int i=0; i<allMoves.length; i++) {
			allMoves[i] = moves.get(i).intValue();
		}
		return allMoves;
	}

	
// TODO evaluate if game is over
	private boolean isOver() {
		return false;
	}

// TODO test validate move
	private boolean isValidMove(int loc) {
		if (disks[loc].isEmpty() && !isOver()) {
			for (int space : findMoves()) {
				if (space == loc) {
					return true;
				}
			}
		}
		return false;
	}

// TODO getWinner()
}
