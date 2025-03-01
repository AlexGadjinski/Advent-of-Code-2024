import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task4_2 {
    private static final int LENGTH = 140;
    private static char[][] matrix;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        matrix = new char[LENGTH][];

        for (int i = 0; i < LENGTH; i++) {
            matrix[i] = reader.readLine().toCharArray();
        }

        getTotalCount();
        System.out.println(result);
    }

    private static void getTotalCount() {
        for (int row = 0; row < LENGTH; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 'A') {
//                    if (horizontalVerticalX(row, col)) {
//                        result++;
//                    }
                    if (diagonalsX(row, col)) {
                        result++;
                    }
                }
            }
        }
    }

    private static boolean diagonalsX(int row, int col) {
        int row1 = row - 1;
        int row2 = row + 1;
        int col1 = col - 1;
        int col2 = col + 1;

        if (indexOutOfBounds(row1, row2, col1, col2)) {
            return false;
        }

        char leftTop = matrix[row1][col1];
        char rightTop = matrix[row1][col2];
        char leftUnder = matrix[row2][col1];
        char rightUnder = matrix[row2][col2];

        if (leftTop == 'M') {
            if (rightUnder == 'S') {
               if (leftUnder == 'M' && rightTop == 'S') {
                   return true;
               }
               return leftUnder == 'S' && rightTop == 'M';
            }
            return false;
        } else if (leftTop == 'S') {
            if (rightUnder == 'M') {
                if (leftUnder == 'M' && rightTop == 'S') {
                    return true;
                }
                return leftUnder == 'S' && rightTop == 'M';
            }
            return false;
        }
        return false;
    }


    private static boolean horizontalVerticalX(int row, int col) {
        int row1 = row - 1;
        int row2 = row + 1;
        int col1 = col - 1;
        int col2 = col + 1;

        if (indexOutOfBounds(row1, row2, col1, col2)) {
            return false;
        }

        if (matrix[row1][col] == 'M') {
            if (matrix[row2][col] == 'S') {
                if (matrix[row][col1] == 'M' && matrix[row][col2] == 'S') {
                    return true;
                }
                return matrix[row][col1] == 'S' && matrix[row][col2] == 'M';
            }
            return false;
        } else if (matrix[row1][col] == 'S') {
            if (matrix[row2][col] == 'M') {
                if (matrix[row][col1] == 'M' && matrix[row][col2] == 'S') {
                    return true;
                }
                return matrix[row][col1] == 'S' && matrix[row][col2] == 'M';
            }
            return false;
        }
        return false;
    }

    private static boolean indexOutOfBounds(int row1, int row2, int col1, int col2) {
        return row1 < 0 || row2 == LENGTH || col1 < 0 || col2 == matrix[0].length;
    }
}
