import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task6_2 {
    private static final int LENGTH = 130;
    private static char[][] matrix;
    private static int guardRowStart = -1;
    private static int guardColStart = -1;
    private static int guardRowCurrent = -1;
    private static int guardColCurrent = -1;
    private static String direction = "";
    private static int obstacles = 0;

    public static void main(String[] args) throws IOException {
        fill();
        findGuardDetails();

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '#' && matrix[i][j] != '^') {
                    matrix[i][j] = '#';
                    walk(0);

                    matrix[i][j] = '.';
                    guardRowCurrent = guardRowStart;
                    guardColCurrent = guardColStart;
                    direction = "up";
                }
            }
        }
        System.out.println(obstacles);
    }

    private static void walk(int pathsTravelled) {
        if (pathsTravelled == 5000) {
            obstacles++;
            return;
        }
        switch (direction) {
            case "up" -> {
                goUp();
                if (guardRowCurrent == 0) {
                    return;
                }
                rotate();
                walk(++pathsTravelled);
            }
            case "right" -> {
                goRight();
                if (guardColCurrent == matrix[0].length - 1) {
                    return;
                }
                rotate();
                walk(++pathsTravelled);
            }
            case "down" -> {
                goDown();
                if (guardRowCurrent == LENGTH - 1) {
                    return;
                }
                rotate();
                walk(++pathsTravelled);
            }
            case "left" -> {
                goLeft();
                if (guardColCurrent == 0) {
                    return;
                }
                rotate();
                walk(++pathsTravelled);
            }
        }
    }

    private static void goLeft() {
        for (int i = guardColCurrent - 1; i >= 0; i--) {
            if (matrix[guardRowCurrent][i] == '#') {
                return;
            }
            guardColCurrent--;
        }
    }

    private static void goDown() {
        for (int i = guardRowCurrent + 1; i < LENGTH; i++) {
            if (matrix[i][guardColCurrent] == '#') {
                return;
            }
            guardRowCurrent++;
        }
    }

    private static void goRight() {
        for (int i = guardColCurrent + 1; i < matrix[0].length; i++) {
            if (matrix[guardRowCurrent][i] == '#') {
                return;
            }
            guardColCurrent++;
        }
    }

    private static void goUp() {
        for (int i = guardRowCurrent - 1; i >= 0; i--) {
            if (matrix[i][guardColCurrent] == '#') {
                return;
            }
            guardRowCurrent--;
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
                guardRowStart = i;
                guardColStart = j;
                if (!direction.equals("")) {
                    guardRowCurrent = guardRowStart;
                    guardColCurrent = guardColStart;
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
