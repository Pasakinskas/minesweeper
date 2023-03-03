package lt.pasakinskas.minesweeper;

public class Game {
  private final static int GAME_BOARD_HEIGHT = 5;
  private final static int GAME_BOARD_WIDTH = 5;
  private final static int NUMBER_OF_BOMBS = 5;

  private Board board;

  public Game() {
    this.board = new Board(GAME_BOARD_HEIGHT, GAME_BOARD_WIDTH);
    placeBombs();
  }

  public void guess(int x, int y) {
    var explode = this.board.checkIfBomb(x, y);
    if (explode) {
      throw new RuntimeException("BOOM");
    } else {
      this.board.revealSquare(x, y);
    }
  }

  private void placeBombs() {
    var numberOfBombs = 0;

    while (numberOfBombs < NUMBER_OF_BOMBS) {
      var randomHeight = (int) Math.floor(Math.random() * GAME_BOARD_HEIGHT);
      var randomWidth = (int) Math.floor(Math.random() * GAME_BOARD_WIDTH);

      var containsBomb = board.checkIfBomb(randomWidth, randomHeight);

      if (!containsBomb) {
        board.placeBomb(randomWidth, randomHeight);
        numberOfBombs++;
      }
    }
  }

  public void getState() {
    this.board.print();
  }

}
