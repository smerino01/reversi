package edu.metrostate.ics425.reversi.team_smsl.model;

import java.io.Serializable;

/**
 * @author skylar
 *
 */
public class Game implements Serializable {
	
	enum Disk {
		LIGHT, DARK;
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
			{40, 49, 58}, 
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
			{61, 54, 47},
			{2, 9, 16},
			{3, 10, 17, 24},
			{4, 11, 18, 25, 32},
			{5, 12, 19, 26, 33, 40},
			{6, 13, 20, 27, 34, 41, 48},
			{7, 14, 21, 28, 35, 42, 49, 56},
			{15, 22, 29, 36, 43, 50, 57},
			{23, 30, 37, 44, 51, 58},
			{31, 38, 45, 52, 59}, 
			{39, 46, 53, 60},
			{47, 54, 61}
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
	}
	
	private void flipDisks(int loc) {
		// get all rows
		for (Rows rows : Rows.values()) {
			// for each row
			for (int i=0; i<rows.rows.length; i++) {
				// for each space
				for (int j=0; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc) {
						switch (rows) {
						case HORIZONTAL:
							int left = checkLeft(loc);
							int right = checkRight(loc);
							
							if (left != loc) {
								// flip disks from left to loc
								for (int iter=left; iter<loc; iter++) {
									disks[iter] = currentPlayer;
								}
							} 
							if (right != loc) {
								// flip disks from loc to right
								for (int iter=loc; iter<=right; iter++) {
									disks[iter] = currentPlayer;
								}
							}
						case VERTICAL:
							int up = checkUp(loc);
							int down = checkDown(loc);
							
							if (up != loc) {
								// flip disks from up to loc
								for (int iter=up; iter<loc; iter+=8) {
									disks[iter] = currentPlayer;
								}
							}
							if (down != loc) {
								// flip disks from loc to down
								for (int iter=loc; iter<=down; iter+=8) {
									disks[iter] = currentPlayer;
								}
							}
						case DIAGONAL:
							int upLeft = checkUpLeft(loc);
							int upRight = checkUpRight(loc);
							int downLeft = checkDownLeft(loc);
							int downRight = checkDownRight(loc);
							
							if (upLeft != loc) {
								// flip disks from upLeft to loc
								for (int iter=upLeft; iter<loc; iter+=9) {
									disks[iter] = currentPlayer;
								}
							}
							if (upRight != loc) {
								// flip disks from upRight to loc
								for (int iter=upRight; iter<loc; iter+=7) {
									disks[iter] = currentPlayer;
								}
							}
							if (downLeft != loc) {
								// flip disks from loc to downLeft
								for (int iter=loc; iter<=downLeft; iter+=7) {
									disks[iter] = currentPlayer;
								}
							}
							if (downRight != loc) {
								// flip disks from loc to downRight
								for (int iter=loc; iter<=downRight; iter+=9) {
									disks[iter] = currentPlayer;
								}
							}
						default:
							// no valid rows
						}
					}
				}
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
	 * Returns the disks on the board
	 * 
	 * @return the disks
	 */
	public Disk[] getDisks() {
		return disks.clone();
	}
	
	/**
	 * Returns the score of the player
	 * 
	 * @param player
	 * @return score of the player
	 */
	public int getScore(Disk player) {
		int score = 0;
		for (Disk disk : disks) {
			if (disk == player) { score++; }
		}
		return score;
	}
	
	/**
	 * Sets current player to next player
	 */
	public void nextPlayer() {
		this.currentPlayer = (getCurrentPlayer() == Disk.DARK) ? Disk.LIGHT : Disk.DARK;
	}
	
	/**
	 * Places disk on specified location
	 * If game is not over, set to next player's turn
	 * 
	 * @param loc location of the disk
	 */
	public void placeDisk(int loc) {
		// TODO add loc validation
		if (isValidMove(loc)) {
			disks[loc] = currentPlayer;
			flipDisks(loc);
			nextPlayer();							
		}
	}

	private int checkLeft(int loc) {
		for (Rows rows : Rows.values()) {
			for (int i=0; i<rows.rows.length; i++) {
				for (int j=0; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i][j-1])) {
						if (disks[rows.rows[i][j-1]] != null) {
							return checkLeft(rows.rows[i][j-1]);					
						} else {
							return rows.rows[i][j-1];
						}					
					}
				}
			}
		}
		return loc;
	}
	
	private int checkRight(int loc) {
		for (Rows rows : Rows.values()) {
			for (int i=0; i<rows.rows.length; i++) {
				for (int j=0; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i-1][j])) {
						if (disks[rows.rows[i][j+1]] != null) {
							return checkRight(rows.rows[i][j+1]);
						} else {
							return rows.rows[i][j+1];
						}
					}
				}
			}
		}
		return loc;
	}
		
	private int checkUp(int loc) {
		for (Rows rows : Rows.values()) {
			for (int i=1; i<rows.rows.length; i++) {
				for (int j=0; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i-1][j])) {
						if (disks[rows.rows[i-1][j]] != null) {
							return checkUp(rows.rows[i-1][j]);
						} else {
							return rows.rows[i-1][j];
						}
					}
				}
			}
		}
		return loc;
	}
	
	private int checkDown(int loc) {
		// rows.rows[i+1][j]
		for (Rows rows : Rows.values()) {
			for (int i=0; i<rows.rows.length; i++) {
				for (int j=0; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i+1][j])) {
						if (disks[rows.rows[i+1][j]] != null) {
							return checkDown(rows.rows[i+1][j]);
						} else {
							return rows.rows[i+1][j];
						}
					}
				}
			}
		}
		return loc;
	}
	private int checkUpLeft(int loc) {
		// rows.rows[i-1][j-1]
		for (Rows rows : Rows.values()) {
			for (int i=1; i<rows.rows.length; i++) {
				for (int j=1; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i-1][j-1])) {
						if (disks[rows.rows[i-1][j-1]] != null) {
							return checkUpLeft(rows.rows[i-1][j-1]);
						} else {
							return rows.rows[i-1][j-1];
						}
					}
				}
			}
		}
		return loc;
	}
	private int checkUpRight(int loc) {
		// rows.rows[i-1][j+1]
		for (Rows rows : Rows.values()) {
			for (int i=1; i<rows.rows.length; i++) {
				for (int j=0; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i-1][j+1])) {
						if (disks[rows.rows[i-1][j+1]] != null) {
							return checkUpRight(rows.rows[i-1][j+1]);
						} else {
							return rows.rows[i-1][j+1];
						}
					}
				}
			}
		}
		return loc;
	}
	private int checkDownLeft(int loc) {
		// rows.rows[i+1][j-1]
		for (Rows rows : Rows.values()) {
			for (int i=0; i<rows.rows.length; i++) {
				for (int j=1; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i+1][j-1])) {
						if (disks[rows.rows[i+1][j-1]] != null) {
							return checkDownLeft(rows.rows[i+1][j-1]);
						} else {
							return rows.rows[i+1][j-1];
						}
					}
				}
			}
		}
		return loc;
	}
	private int checkDownRight(int loc) {
		// rows.rows[i+1][j+1]
		for (Rows rows : Rows.values()) {
			for (int i=0; i<rows.rows.length; i++) {
				for (int j=0; j<rows.rows[i].length; j++) {
					if (rows.rows[i][j] == loc && isOnBoard(rows.rows[i+1][j+1])) {
						if (disks[rows.rows[i+1][j+1]] != null) {
							return checkDownRight(rows.rows[i+1][j+1]);
						} else {
							return rows.rows[i+1][j+1];
						}
					}
				}
			}
		}
		return loc;
	}
	private boolean isEmpty(Disk disk) {
		return (disk == null);
	}
	private boolean isOnBoard(int space) {
		return space >= 0 && space < NUM_DISKS;
	}
	// TODO evaluate if game is over
	private boolean isOver() {
		for (Disk disk : disks) {
			if (disk == null) {
				return false;
			}
		}
		return true;
	}
	private boolean isValidMove(int loc) {
		return !isOver() && isOnBoard(loc) && isEmpty(disks[loc]);
	}
}