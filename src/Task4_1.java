import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4_1 {
    private static final int LENGTH = 140;
    private static char[][] matrix;
    private static final Pattern pattern = Pattern.compile("XMAS");

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        matrix = new char[LENGTH][];

        for (int i = 0; i < LENGTH; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }

        System.out.println(getTotalCount());
    }

    private static int getTotalCount() {
        return getHorizontalCount() +
                getVerticalCount() +
                getDiagonalCount();
    }

    private static int getDiagonalCount() {
        int result = 0;

        int rows = LENGTH;
        int columns = matrix[0].length;

        for (int i = rows - 1; i >= 0; i--) {
            StringBuilder diagonal = new StringBuilder();
            int row = i;
            for (int col = 0; col < columns; col++) {
                diagonal.append(matrix[row][col]);
                row++;
                if (row == rows) {
                    break;
                }
            }
            result += getMatches(diagonal.toString());
        }
        for (int i = 1; i < columns; i++) {
            StringBuilder diagonal = new StringBuilder();
            int col = i;
            for (int row = 0; row < rows; row++) {
                diagonal.append(matrix[row][col]);
                col++;
                if (col == columns) {
                    break;
                }
            }
            result += getMatches(diagonal.toString());
        }
        for (int i = 0; i < rows; i++) {
            StringBuilder diagonal = new StringBuilder();
            int col = 0;
            for (int row = i; row >= 0; row--) {
                diagonal.append(matrix[row][col]);
                col++;
                if (col == columns) {
                    break;
                }
            }
            result += getMatches(diagonal.toString());
        }
        for (int i = 1; i < columns; i++) {
            StringBuilder diagonal = new StringBuilder();
            int col = i;
            for (int row = rows - 1; row >= 0; row--) {
                diagonal.append(matrix[row][col]);
                col++;
                if (col == columns) {
                    break;
                }
            }
            result += getMatches(diagonal.toString());
        }
        return result;
    }

    private static int getVerticalCount() {
        int result = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            StringBuilder column = new StringBuilder();
            for (int row = 0; row < LENGTH; row++) {
                column.append(matrix[row][col]);
            }
            result += getMatches(column.toString());
        }

        return result;
    }

    private static int getHorizontalCount() {
        int result = 0;
        for (int i = 0; i < LENGTH; i++) {
            String line = toString(matrix[i]);
            result += getMatches(line);
        }
        return result;
    }

    private static int getMatches(String line) {
        int result = 0;
        Matcher matcher1 = pattern.matcher(line);
        Matcher matcher2 = pattern.matcher(new StringBuilder(line).reverse().toString());

        while (matcher1.find()) {
            result++;
        }
        while (matcher2.find()) {
            result++;
        }
        return result;
    }

    private static String toString(char[] matrix) {
        return Arrays.toString(matrix)
                .replace(", ", "")
                .replace("[", "")
                .replace("]", "");
    }
}
