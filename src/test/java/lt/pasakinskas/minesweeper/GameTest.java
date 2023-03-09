package lt.pasakinskas.minesweeper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GameTest {

  Board board;
  Display display;
  Controls controls;
  Game game;

  @BeforeEach
  public void setup() {
    this.controls = mock(Controls.class);
    this.display = mock(Display.class);
    this.board = mock(Board.class);

    this.game = new Game(board, display, controls);

    when(controls.getInput()).thenReturn(new UserInput(1, 1));
  }

  @Nested
  @DisplayName("Tick")
  class Tick {
    @Test
    public void whenGameTicks_displayDependencyDisplaysState() {
      game.tick();

      Mockito.verify(display).showBoardState();
      Mockito.verify(display, Mockito.times(1)).askForInput();
    }

    @Test
    public void whenGameTicks_aGuessIsMadeWithControlParams() {
      when(board.isBoardSquareExplosive(1,1)).thenReturn(false);

      game.tick();

      Mockito.verify(board).isBoardSquareExplosive(1, 1);
    }
  }

  @Nested
  @DisplayName("Guess")
  class Guess {

    @Test
    public void whenGuessIsMade_andItsNotABomb_itDelegatesToBoard() {
      when(board.isBoardSquareExplosive(1,1)).thenReturn(false);

      game.guess(1,1);
      Mockito.verify(board, Mockito.times(1)).revealSquare(1, 1);
    }

    @Test
    public void whenGuessIsMade_andItsABomb_itExplodes() {
      when(board.isBoardSquareExplosive(1,1)).thenReturn(true);

      assertThrows(BombActivatedException.class, () -> game.guess(1,1));
    }
  }
}
