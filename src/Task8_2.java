import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task8_2 {
    private static int LENGTH = 50;
    private static char[][] nodeMatrix = new char[LENGTH][];
    private static char[][] matrix = new char[LENGTH][];

    //TODO: two antinodes on top of each other
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        fillMatrix(reader);
        findFirstFrequency();
        System.out.println(getNodes());
    }

    private static int getNodes() {
        int result = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < nodeMatrix[0].length; j++) {
                if (nodeMatrix[i][j] != '.') {
                    result++;
                }
            }
        }
        return result;
    }

    private static void findFirstFrequency() {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '.') {
                    findSecondFrequency(i, j, matrix[i][j]);
                }
            }
        }
    }

    private static void findSecondFrequency(int row, int col, char symbol) {
        int startCol = col + 1;
        for (int i = row; i < LENGTH; i++) {
            for (int j = startCol; j < matrix[0].length; j++) {
                if (matrix[i][j] == symbol) {
                    putNode(row, col, i, j);
                }
            }
            startCol = 0;
        }
    }

    private static void putNode(int row1, int col1, int row2, int col2) {
        // top
        int secondRow = row2;
        int firstRow = row1;
        int rowForNode = firstRow - (secondRow - firstRow);

        int secondCol = col2;
        int firstCol = col1;
        int colForNode = secondCol > firstCol ? firstCol - (secondCol - firstCol) : firstCol + (firstCol - secondCol);

        while (true) {
            if (!inBoundaries(rowForNode, colForNode) || rowForNode == firstRow || colForNode == firstCol) {
                break;
            }
            nodeMatrix[rowForNode][colForNode] = '#';

            // rowIndexChange
            secondRow = firstRow;
            firstRow = rowForNode;
            rowForNode = firstRow - (secondRow - firstRow);
            // colIndexChange
            secondCol = firstCol;
            firstCol = colForNode;
            colForNode = secondCol > firstCol ? firstCol - (secondCol - firstCol) : firstCol + (firstCol - secondCol);
        }

        // bottom
        secondRow = row2;
        firstRow = row1;
        rowForNode = secondRow + (secondRow - firstRow);

        secondCol = col2;
        firstCol = col1;
        colForNode = secondCol > firstCol ? secondCol - firstCol + secondCol : secondCol - (firstCol - secondCol);

        while (true) {
            if (!inBoundaries(rowForNode, colForNode) || rowForNode == firstRow || colForNode == firstCol) {
                break;
            }
            nodeMatrix[rowForNode][colForNode] = '#';

            // rowIndexChange
            firstRow = secondRow;
            secondRow = rowForNode;
            rowForNode = secondRow + (secondRow - firstRow);
            // colIndexChange
            firstCol = secondCol;
            secondCol = colForNode;
            colForNode = secondCol > firstCol ? secondCol - firstCol + secondCol : secondCol - (firstCol - secondCol);
        }
    }

    private static boolean inBoundaries(int row, int col) {
        return row >= 0 && row < LENGTH && col >= 0 && col < matrix[0].length;
    }

    private static void fillMatrix(BufferedReader reader) throws IOException {
        for (int i = 0; i < LENGTH; i++) {
            String input = reader.readLine();
            matrix[i] = input.toCharArray();
            nodeMatrix[i] = input.toCharArray();
        }
    }
}
