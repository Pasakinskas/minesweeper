package lt.pasakinskas.minesweeper;

public class Square {
  private boolean explodes;
  private int number;

  public Square(boolean explodes, int number) {
    this.explodes = explodes;
    this.number = number;
  }

  public boolean isExplodes() {
    return explodes;
  }

  public void setExplodes(boolean explodes) {
    this.explodes = explodes;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }
}
