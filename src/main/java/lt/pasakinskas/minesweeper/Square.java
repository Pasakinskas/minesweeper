package lt.pasakinskas.minesweeper;

public class Square {
  private int x;
  private int y;
  private boolean explodes;
  private int number;

  public Square(int x, int y, boolean explodes, int number) {
    this.x = x;
    this.y = y;
    this.explodes = explodes;
    this.number = number;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
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
