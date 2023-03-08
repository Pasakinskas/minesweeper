package lt.pasakinskas.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Board {

  private List<List<Square>> state;

  public Board(int boardHeight, int boardWidth, int numberOfBombs) {
    this.state = new ArrayList<>();
    populate(boardHeight, boardWidth);
    placeBombs(boardHeight, boardWidth, numberOfBombs);
  }

  public List<List<Square>> getState() {
    return state;
  }

  public void revealSquare(int x, int y) {
    var square= getSquare(x, y);
    var squareNeighbors = findSquareNeighbors(x, y)
      .stream()
      .filter(Objects::nonNull)
      .filter(Square::isExplodes)
      .toList();

    square.setNumber(squareNeighbors.size());
  }

  public boolean isBoardSquareExplosive(int x, int y) {
    return this.state.get(x).get(y).isExplodes();
  }

  public void placeBombs(int boardHeight, int boardWidth, int numberOfBombs) {
    var placedBombs = 0;

    while (placedBombs < numberOfBombs) {
      var randomHeight = (int) Math.floor(Math.random() * boardHeight);
      var randomWidth = (int) Math.floor(Math.random() * boardWidth);

      var containsBomb = isBoardSquareExplosive(randomWidth, randomHeight);

      if (!containsBomb) {
        placeBomb(randomWidth, randomHeight);
        placedBombs++;
      }
    }
  }

  public void placeBomb(int x, int y) {
    this.state.get(x).get(y).setExplodes(true);
  }

  public void populate(int height, int width) {
    for (var i = 0; i < height; i++) {
      var row = new ArrayList<Square>();
      for (var j = 0; j < width; j++) {
        row.add(new Square(false, -1));
      }
      state.add(row);
    }
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

  public Square getSquare(int x, int y) {
    var square = getSquareIfExists(x, y);
    if (Objects.isNull(square)) {
      throw new InvalidSquareSelectedException(x, y);
    }
    return square;
  }

  public Square getSquareIfExists(int x, int y) {
    try {
      return this.state.get(x).get(y);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }
}
