package linea;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LineaTest {

    @Test
    public void test01GameStartsFineWithStrategyA() {
        Linea game = createGame('A');
        assertEquals(6, game.rows);
        assertEquals(7, game.columns.size());
        assertEquals('A', game.strategyType.getStrategyType());
        assertFalse(game.finished());
    }

    @Test
    public void test02GameStartsFineWithStrategyB() {
        Linea game = new Linea(9, 2, 'B');
        assertEquals(9, game.rows);
        assertEquals(2, game.columns.size());
        assertEquals('B', game.strategyType.getStrategyType());
        assertFalse(game.finished());
    }

    @Test
    public void test03GameStartsFineWithStrategyC() {
        Linea game = new Linea(51, 11, 'C');
        assertEquals(51, game.rows);
        assertEquals(11, game.columns.size());
        assertEquals('C', game.strategyType.getStrategyType());
        assertFalse(game.finished());
    }

    @Test
    public void test04RedPlayerCanThrow() {
        Linea game = createGame('C');
        game.playRedAt(5);
        assertEquals('R', game.columns.get(4).get(0));
    }

    @Test
    public void test05BluePlayerCanThrow() {
        Linea game = createGame('C');
        game.playRedAt(5);
        game.playBlueAt(3);
        assertEquals('B', game.columns.get(2).get(0));
    }

    @Test
    public void test06TokensStackUp() {
        Linea game = createGame('C');
        game.playRedAt(5);
        game.playBlueAt(5);
        game.playRedAt(5);
        assertEquals('R', game.columns.get(4).get(0));
        assertEquals('B', game.columns.get(4).get(1));
        assertEquals('R', game.columns.get(4).get(2));
    }

    @Test
    public void test07TokensCantGoOffLimitsRows() {
        Linea game = new Linea(4, 6, 'A');
        game.playRedAt(4);
        game.playBlueAt(1);
        game.playRedAt(4);
        game.playBlueAt(1);
        game.playRedAt(4);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(1);
        assertThrows(RuntimeException.class, () -> game.playRedAt(4));
    }

    @Test
    public void test08TokensCantGoOffLimitsColumns() {
        Linea game = new Linea(4, 6, 'A');
        assertThrows(RuntimeException.class, () -> game.playRedAt(7));
    }

    @Test
    public void test09RedStartsTheGame() {
        Linea game = createGame('C');
        assertThrows(RuntimeException.class, () -> game.playBlueAt(4));
    }

    @Test
    public void test10RedCantPlayTwice() {
        Linea game = createGame('C');
        game.playRedAt(1);
        assertThrows(RuntimeException.class, () -> game.playRedAt(4));
    }

    @Test
    public void test11BlueCantPlayTwice() {
        Linea game = createGame('C');
        game.playRedAt(1);
        game.playBlueAt(2);
        assertThrows(RuntimeException.class, () -> game.playBlueAt(2));
    }

    @Test
    public void test12VerticalWinWorksInStrategyA() {
        Linea gameRed = verticalWinForRed('A');
        assertTrue(gameRed.finished());
        Linea gameBlue = verticalWinForBlue('A');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test13VerticalWinWorksInStrategyC() {
        Linea gameRed = verticalWinForRed('C');
        assertTrue(gameRed.finished());
        Linea gameBlue = verticalWinForBlue('C');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test14VerticalWinDoesntWorkInStrategyB() {
        Linea gameRed = verticalWinForRed('B');
        assertFalse(gameRed.finished());
        Linea gameBlue = verticalWinForBlue('B');
        assertFalse(gameBlue.finished());
    }

    @Test
    public void test15HorizontalWinWorksInStrategyA() {
        Linea gameRed = horizontalWinForRed('A');
        assertTrue(gameRed.finished());
        Linea gameBlue = horizontalWinForBlue('A');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test16HorizontalWinWorksInStrategyC() {
        Linea gameRed = horizontalWinForRed('C');
        assertTrue(gameRed.finished());
        Linea gameBlue = horizontalWinForBlue('C');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test17HorizontalWinDoesntWorkInStrategyB() {
        Linea gameRed = horizontalWinForRed('B');
        assertFalse(gameRed.finished());
        Linea gameBlue = horizontalWinForBlue('B');
        assertFalse(gameBlue.finished());
    }

    @Test
    public void test18DiagonalAscendantWinWorksInStrategyB() {
        Linea gameRed = DiagonalAscendantWinForRed('B');
        assertTrue(gameRed.finished());
        Linea gameBlue = DiagonalAscendantWinForBlue('B');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test19DiagonalAscendantWinWorksInStrategyC() {
        Linea gameRed = DiagonalAscendantWinForRed('C');
        assertTrue(gameRed.finished());
        Linea gameBlue = DiagonalAscendantWinForBlue('C');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test20DiagonalAscendantWinDoesntWorkInStrategyA() {
        Linea gameRed = DiagonalAscendantWinForRed('A');
        assertFalse(gameRed.finished());
        Linea gameBlue = DiagonalAscendantWinForBlue('A');
        assertFalse(gameBlue.finished());
    }

    @Test
    public void test21DiagonalDescendantWinWorksInStrategyB() {
        Linea gameRed = DiagonalDescendantWinForRed('B');
        assertTrue(gameRed.finished());
        Linea gameBlue = DiagonalDescendantWinForBlue('B');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test22DiagonalDescendantWinWorksInStrategyC() {
        Linea gameRed = DiagonalDescendantWinForRed('C');
        assertTrue(gameRed.finished());
        Linea gameBlue = DiagonalDescendantWinForBlue('C');
        assertTrue(gameBlue.finished());
    }

    @Test
    public void test23DiagonalDescendantWinDoesntWorkInStrategyA() {
        Linea gameRed = DiagonalDescendantWinForRed('A');
        assertFalse(gameRed.finished());
        Linea gameBlue = DiagonalDescendantWinForBlue('A');
        assertFalse(gameBlue.finished());
    }

    @Test
    public void test24CantKeepPlayingIfGameEndsByHorizontal() {
        Linea game = horizontalWinForRed('A');
        game.finished();
        assertThrows(RuntimeException.class, () -> game.playBlueAt(2));
    }

    @Test
    public void test25CantKeepPlayingIfGameEndsByVertical() {
        Linea game = verticalWinForBlue('C');
        game.finished();
        assertThrows(RuntimeException.class, () -> game.playRedAt(2));
    }

    @Test
    public void test26CantKeepPlayingIfGameEndsByDiagonalAscendant() {
        Linea game = DiagonalAscendantWinForRed('C');
        game.finished();
        assertThrows(RuntimeException.class, () -> game.playRedAt(2));
    }

    @Test
    public void test27CantKeepPlayingIfGameEndsByDiagonalDescendant() {
        Linea game = DiagonalDescendantWinForBlue('C');
        game.finished();
        assertThrows(RuntimeException.class, () -> game.playRedAt(2));
    }

    @Test
    public void test28TieIsDetectedInStrategyA() {
        Linea game = TiedBoard('A');
        assertTrue(game.finished());
    }

    @Test
    public void test29TieIsDetectedInStrategyB() {
        Linea game = TiedBoard('B');
        assertTrue(game.finished());
    }

    @Test
    public void test30TieIsDetectedInStrategyC() {
        Linea game = TiedBoard('C');
        assertTrue(game.finished());
    }

    private static Linea createGame(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        return game;
    }

    private static Linea verticalWinForRed(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(5);
        game.playBlueAt(1);
        game.playRedAt(5);
        game.playBlueAt(1);
        game.playRedAt(5);
        game.playBlueAt(1);
        game.playRedAt(5);
        return game;
    }

    private static Linea verticalWinForBlue(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(2);
        game.playBlueAt(5);
        game.playRedAt(1);
        game.playBlueAt(5);
        game.playRedAt(3);
        game.playBlueAt(5);
        game.playRedAt(1);
        game.playBlueAt(5);
        return game;
    }

    private static Linea horizontalWinForBlue(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(5);
        return game;
    }

    private static Linea horizontalWinForRed(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(2);
        game.playBlueAt(1);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);
        game.playBlueAt(2);
        game.playRedAt(5);
        return game;
    }

    private static Linea DiagonalAscendantWinForRed(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(1);
        game.playBlueAt(4);
        game.playRedAt(4);
        game.playBlueAt(5);
        game.playRedAt(6);
        game.playBlueAt(5);
        game.playRedAt(6);
        game.playBlueAt(5);
        game.playRedAt(5);
        return game;
    }

    private static Linea DiagonalAscendantWinForBlue(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);
        game.playBlueAt(6);
        game.playRedAt(4);
        game.playBlueAt(4);
        game.playRedAt(5);
        game.playBlueAt(1);
        game.playRedAt(5);
        game.playBlueAt(1);
        game.playRedAt(5);
        game.playBlueAt(5);
        return game;
    }

    private static Linea DiagonalDescendantWinForRed(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(3);
        game.playBlueAt(4);
        game.playRedAt(4);
        game.playBlueAt(6);
        game.playRedAt(5);
        return game;
    }

    private static Linea DiagonalDescendantWinForBlue(char strategyType) {
        Linea game = new Linea(6, 7, strategyType);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(3);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(4);
        game.playBlueAt(4);
        game.playRedAt(6);
        game.playBlueAt(5);
        return game;
    }

    private static Linea TiedBoard(char strategyType) {
        Linea game = new Linea(3, 3, strategyType);
        game.playRedAt(1);
        game.playBlueAt(1);
        game.playRedAt(1);
        game.playBlueAt(2);
        game.playRedAt(2);
        game.playBlueAt(2);
        game.playRedAt(3);
        game.playBlueAt(3);
        game.playRedAt(3);
        return game;
    }
}