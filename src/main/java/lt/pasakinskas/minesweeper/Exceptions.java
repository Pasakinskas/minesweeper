package lt.pasakinskas.minesweeper;

class InvalidSquareSelectedException extends RuntimeException {
  public InvalidSquareSelectedException(int x, int y) {
    super("invalid square " + x + "," + y);
  }
}

class BombActivatedException extends RuntimeException {
  public BombActivatedException() {
    super("BOOM! You have stepped on a bomb");
    }
}

