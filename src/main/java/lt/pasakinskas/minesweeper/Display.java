package lt.pasakinskas.minesweeper;

import java.util.stream.Collectors;

public class Display {

  private Board board;

  public Display(Board board) {
    this.board = board;
  }

  public void showBoardState() {
    board.getState().forEach(row -> {
      var rowRepresentation = row.stream().map(square -> {
        if (square.getNumber() == -1) {
          return "[-]";
        } else {
          return "[" + square.getNumber() + "]";
        }
      }).collect(Collectors.joining(" "));
      System.out.println(rowRepresentation);
    });
  }

  public void askForInput() {
    System.out.println();
    System.out.println("spekite kur bomba");
  }
}
