package linea;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Linea {
    public static List<ArrayList<Character>> columns;
    public static int rows;

    public Strategy strategyType;
    public List<Strategy> strategies = List.of(new StrategyA(),
            new StrategyB(),
            new StrategyC());

    public static TurnStatus playerTurn;

    public Linea(int rows, int column, char victorySet) {
        this.columns = Stream.generate(ArrayList<Character>::new)
                .limit(column)
                .collect(Collectors.toList());

        this.rows = rows;
        strategyType = strategies.stream()
                .filter(each -> each.getStrategyType() == victorySet)
                .findAny()
                .get();
        playerTurn = new RedTurn();
    }

    public void playRedAt(int column) {
        playerTurn.playRedAt();
        if (column > columns.size() || column < 1) {
            throw new RuntimeException("Column out of range");
        }
        if (columns.get(column - 1).size() == rows) {
            throw new RuntimeException("Column full");
        }
        columns.get(column - 1).add('R');
        playerTurn = new BlueTurn();
    }

    public void playBlueAt(int column) {
        playerTurn.playBlueAt();
        if (column > columns.size() || column < 1) {
            throw new RuntimeException("Column out of range");
        }
        if (columns.get(column - 1).size() == rows) {
            throw new RuntimeException("Column full");
        }
        columns.get(column - 1).add('B');
        playerTurn = new RedTurn();
    }

    public String show() {
        String result = IntStream.range(0, rows)
                .mapToObj(i -> {
                    int rowIndex = rows - 1 - i;
                    String row = columns.stream()
                            .map(sublista -> rowIndex < sublista.size() ? " '" + sublista.get(rowIndex) + "' " : " ' ' ")
                            .collect(Collectors.joining());
                    return "|" + row + "|";
                })
                .collect(Collectors.joining("\n"));
        return result;
    }

    public boolean finished() {
        return strategyType.finished();
    }

    public static boolean StrategyA(char playerToken) {

        //  Vertical
        for (int column = 0; column < columns.size(); column++) {

            List<Character> currentColumn = columns.get(column);

            if (vertical (currentColumn, playerToken)) {
                System.out.println("Player " + playerToken + " wins!");
                return true;
            }
        }

        //  Horizontal
        for (int row = 0; row < rows; row++) {
            if (horizontal (playerToken,row)) {
                System.out.println("Player " + playerToken + " wins!");
                return true;
            }
        }

        return false;
    }

    public static boolean StrategyB(char playerToken) {

        for (int column = 0; column < columns.size() - 3; column++) {
            if (diagonalDescendant(column,playerToken)) {
                System.out.println("Player " + playerToken + " wins!");
                return true;
            }
            if (diagonalAscendant(playerToken,column)){
                System.out.println("Player " + playerToken + " wins!");
                return true;
            }
        }
        return false;
    }

    public static boolean Tie() {
        if (columns.stream().allMatch(list -> list.size() == rows)){
            System.out.println("There has been a tie!");
            playerTurn = new GameOver();
            return true;
        }
        return false;
    }

    private static boolean horizontal(char playerToken, int row) {
        int horizontalCounter = 0;
        for (int column = 0; column < columns.size(); column++) {
            List<Character> currentColumn = columns.get(column);
            char token = currentColumn.size() > row ? currentColumn.get(row) : ' ';

            if (token == playerToken) {
                horizontalCounter++;
                if (horizontalCounter == 4) {
                    playerTurn = new GameOver();
                    return true;
                }
            } else {
                horizontalCounter = 0;
            }
        }
        return false;
    }

    private static boolean vertical (List<Character> currentColumn, char playerToken) {

        int verticalCounter = 0;
        for (char token : currentColumn) {

            if (token == playerToken) {
                verticalCounter++;

                if (verticalCounter == 4) {
                    playerTurn = new GameOver();
                    return true;
                }
            } else {
                verticalCounter = 0;
            }
        }
        return false;

    }

    private static boolean diagonalAscendant(char playerToken, int column) {
        for (int row = 3; row < rows; row++) {
            if (columns.get(column).size() > row &&
                    columns.get(column + 1).size() > row - 1 &&
                    columns.get(column + 2).size() > row - 2 &&
                    columns.get(column + 3).size() > row - 3) {
                char c1 = columns.get(column).get(row);
                char c2 = columns.get(column + 1).get(row - 1);
                char c3 = columns.get(column + 2).get(row - 2);
                char c4 = columns.get(column + 3).get(row - 3);
                if (c1 == playerToken && c2 == playerToken && c3 == playerToken && c4 == playerToken) {
                    playerTurn = new GameOver();
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean diagonalDescendant(int column, char playerToken) {
        for (int row = 0; row < rows - 3; row++) {
            if (columns.get(column).size() > row &&
                    columns.get(column + 1).size() > row + 1 &&
                    columns.get(column + 2).size() > row + 2 &&
                    columns.get(column + 3).size() > row + 3) {
                char c1 = columns.get(column).get(row);
                char c2 = columns.get(column + 1).get(row + 1);
                char c3 = columns.get(column + 2).get(row + 2);
                char c4 = columns.get(column + 3).get(row + 3);
                if (c1 == playerToken && c2 == playerToken && c3 == playerToken && c4 == playerToken) {
                    playerTurn = new GameOver();
                    return true;
                }
            }
        }
        return false;
    }}