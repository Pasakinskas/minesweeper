package lt.pasakinskas.minesweeper;

public class Game {

  private Board board;
  private Display display;
  private Controls controls;

  public Game(Board board, Display display, Controls controls) {
    this.board = board;
    this.display = display;
    this.controls = controls;
  }


  public void tick() {
    display.showBoardState();
    display.askForInput();

    var userInput = controls.getInput();
    guess(userInput.getX(), userInput.getY());

    display.showBoardState();
  }

  public void init() {
    while (true) {
      tick();
    }
  }

  public void guess(int x, int y) {
    var explode = this.board.isBoardSquareExplosive(x, y);
    if (explode) {
      throw new BombActivatedException();
    } else {
      this.board.revealSquare(x, y);
    }
  }
}
