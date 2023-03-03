package lt.pasakinskas.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

public class MinesweeperApplication {

	public static void main(String[] args) {
		var game = new Game();
		game.getState();

		var lives = 1;

		Scanner scanner = new Scanner(System.in);

		while (lives >= 1) {
			System.out.println();
			game.getState();
			System.out.println("spekite kur bomba");
			var guess = scanner.nextLine();
			var guessCoordinates = guess.split(",");
			var x = Integer.valueOf(guessCoordinates[0]);
			var y = Integer.valueOf(guessCoordinates[1]);

			game.guess(x, y);
		}
	}

}
