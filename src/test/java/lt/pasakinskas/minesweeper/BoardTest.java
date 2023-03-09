package lt.pasakinskas.minesweeper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class BoardTest {

  @Nested
  @DisplayName("new Board")
  class ConstructorTests {

    @Test
    public void whenBoardCreated_populatesAnArrayListMatrixWithSquares() {
      Board board = new Board(5, 5, 5);
      var boardState = board.getState();

      assertEquals(boardState.size(), 5);
      boardState.forEach(row -> assertEquals(row.size(), 5));
    }

    @Test
    public void whenBoardCreated_addsCorrectNumberOfBombs() {
      Board board = new Board(1, 1, 1);
      assertTrue(board.getState().get(0).get(0).isExplodes());
    }

    @Test
    public void givenBoardCreated_whenNoBombsNeedToBePlaced_NoBombsArePresent() {
      Board board = new Board(1, 1, 0);

      var bombs = board.getState()
        .stream().filter(row -> {
          var explosiveSquares = row.stream().filter(Square::isExplodes).toList();
          return !explosiveSquares.isEmpty();
        }).toList();

      assertTrue(bombs.isEmpty());
    }
  }

  @Nested
  @DisplayName("Find Square Neighbors")
  class FindSquareNeighborsTests {

    Board board = new Board(5, 5, 5);

    @Test
    public void whenMiddleSquareSelected_returnsEightNeighbors() {
      List<Square> neighbors = board.findSquareNeighbors(2,2);

      assertEquals(8, neighbors.size());
    }

    @Test
    public void whenEdgeSquareSelected_returnsFiveNeighbors() {
      List<Square> neighbors = board.findSquareNeighbors(0,2);

      assertEquals(5, neighbors.size());
    }

    @Test
    public void whenCornerSquareSelected_returnsThreeNeighbors() {
      List<Square> neighbors = board.findSquareNeighbors(0,0);

      assertEquals(3, neighbors.size());
    }
  }
}
