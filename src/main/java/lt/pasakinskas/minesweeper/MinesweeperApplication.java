package lt.pasakinskas.minesweeper;

import java.util.Scanner;

public class MinesweeperApplication {

	private final static int GAME_BOARD_HEIGHT = 5;
	private final static int GAME_BOARD_WIDTH = 5;
	private final static int NUMBER_OF_BOMBS = 5;

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		var board = new Board(GAME_BOARD_HEIGHT, GAME_BOARD_WIDTH, NUMBER_OF_BOMBS);
		var display = new Display(board);
		var controls = new Controls(scanner);

		var game = new Game(board, display, controls);

		game.init();
	}
}
