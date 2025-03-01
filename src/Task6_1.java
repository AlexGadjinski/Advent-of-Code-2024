import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task6_1 {
    private static final int LENGTH = 130;
    private static char[][] matrix;
    private static int guardRow = -1;
    private static int guardCol = -1;
    private static String direction = "";

    public static void main(String[] args) throws IOException {
        fill();
        findGuardDetails();
        walk();
        System.out.println(getVisitedPositions());
    }

    private static int getVisitedPositions() {
        int result = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'X') {
                    result++;
                }
            }
        }
        return result;
    }

    private static void walk() {
        switch (direction) {
            case "up" -> {
                goUp();
                if (guardRow == 0) {
                    matrix[guardRow][guardCol] = 'X';
                    return;
                }
                rotate();
                walk();
            }
            case "right" -> {
                goRight();
                if (guardCol == matrix[0].length - 1) {
                    matrix[guardRow][guardCol] = 'X';
                    return;
                }
                rotate();
                walk();
            }
            case "down" -> {
                goDown();
                if (guardRow == LENGTH - 1) {
                    matrix[guardRow][guardCol] = 'X';
                    return;
                }
                rotate();
                walk();
            }
            case "left" -> {
                goLeft();
                if (guardCol == 0) {
                    matrix[guardRow][guardCol] = 'X';
                    return;
                }
                rotate();
                walk();
            }
        }
    }

    private static void goLeft() {
        for (int i = guardCol - 1; i >= 0; i--) {
            if (matrix[guardRow][i] == '#') {
                return;
            }
            matrix[guardRow][guardCol--] = 'X';
            matrix[guardRow][guardCol] = '<';
        }
    }

    private static void goDown() {
        for (int i = guardRow + 1; i < LENGTH; i++) {
            if (matrix[i][guardCol] == '#') {
                return;
            }
            matrix[guardRow++][guardCol] = 'X';
            matrix[guardRow][guardCol] = 'v';
        }
    }

    private static void goRight() {
        for (int i = guardCol + 1; i < matrix[i].length; i++) {
            if (matrix[guardRow][i] == '#') {
                return;
            }
            matrix[guardRow][guardCol++] = 'X';
            matrix[guardRow][guardCol] = '>';
        }
    }

    private static void goUp() {
        for (int i = guardRow - 1; i >= 0; i--) {
            if (matrix[i][guardCol] == '#') {
                return;
            }
            matrix[guardRow--][guardCol] = 'X';
            matrix[guardRow][guardCol] = '^';
        }
    }

    private static void rotate() {
        direction = switch (direction) {
            case "up" -> "right";
            case "right" -> "down";
            case "down" -> "left";
            case "left" -> "up";
            default -> throw new IllegalArgumentException("Invalid direction provided");
        };
    }

    private static void findGuardDetails() {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                char symbol = matrix[i][j];
                switch (symbol) {
                    case '<' -> direction = "left";
                    case '>' -> direction = "right";
                    case '^' -> direction = "up";
                    case 'v' -> direction = "down";
                }
                guardRow = i;
                guardCol = j;
                if (!direction.equals("")) {
                    return;
                }
            }
        }
    }

    private static void fill() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        matrix = new char[LENGTH][];

        for (int i = 0; i < LENGTH; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }
    }
}
