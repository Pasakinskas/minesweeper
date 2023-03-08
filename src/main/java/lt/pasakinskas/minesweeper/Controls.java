package lt.pasakinskas.minesweeper;

import java.util.Scanner;

public class Controls {

  private Scanner scanner;

  public Controls(Scanner scanner) {
    this.scanner = scanner;
  }

  public UserInput getInput() {
    var userInput = this.scanner.nextLine();
    var guessCoordinates = userInput.split(",");

    var x = Integer.parseInt(guessCoordinates[0]);
    var y = Integer.parseInt(guessCoordinates[1]);

    return new UserInput(x - 1, y - 1);
  }
}
