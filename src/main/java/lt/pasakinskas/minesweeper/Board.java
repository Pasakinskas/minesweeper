package lt.pasakinskas.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
  private List<List<Square>> table;

  public Board(int boardHeight, int boardWidth) {
    this.table = new ArrayList<>();
    populate(boardHeight, boardWidth);
  }

  public void populate(int height, int width) {

    for (var i = 0; i < height; i++) {
      var row = new ArrayList<Square>();
      for (var j = 0; j < width; j++) {
        row.add(new Square(i, j, false, -1));
      }
      table.add(row);
    }
  }

  public void revealSquare(int x, int y) {
    var square = getSquareIfExists(x, y);
    var squareNeighbors = findSquareNeighbors(x, y)
      .stream().filter(Square::isExplodes)
      .toList();

    square.setNumber(squareNeighbors.size());
  }

  public void placeBomb(int x, int y) {
    this.table.get(x).get(y).setExplodes(true);
  }

  public boolean checkIfBomb(int x, int y) {
    return this.table.get(x).get(y).isExplodes();
  }

  public void print() {
    table.forEach(row -> {
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

  public List<Square> findSquareNeighbors(int x, int y) {
    var neighbors = new ArrayList<Square>();

    Collections.addAll(neighbors,
      getSquareIfExists(x - 1, y - 1),
      getSquareIfExists(x - 1, y),
      getSquareIfExists(x, y - 1),
      getSquareIfExists(x + 1, y + 1),
      getSquareIfExists(x + 1, y),
      getSquareIfExists(x, y + 1),
      getSquareIfExists(x - 1, y + 1),
      getSquareIfExists(x + 1, y - 1)
    );

    return neighbors.stream().filter(Objects::nonNull).toList();
  }

  private Square getSquareIfExists(int x, int y) {
    try {
      return this.table.get(x).get(y);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }
}
