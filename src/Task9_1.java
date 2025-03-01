import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Task9_1 {
    public static void main(String[] args) throws IOException {
        char[] arr = getIndividualBlocks();
        moveFiles(arr);
        long result = getResult(arr);
        System.out.println(result);
    }

    private static long getResult(char[] arr) {
        long result = 0;
        int bound = Arrays.toString(arr)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", "")
                .indexOf(".");
        for (int i = 0; i < bound; i++) {
            result += Integer.parseInt(arr[i] + "") * (long) i;
        }
        return result;
    }

    private static void moveFiles(char[] arr) {
        int indexToPut = Arrays.toString(arr)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", "")
                .indexOf(".");

        for (int indexToGet = arr.length - 1; indexToGet >= indexToPut ; indexToGet--) {
            if (arr[indexToGet] == '.') {
                continue;
            }

            char symbol = arr[indexToGet];
            arr[indexToGet] = '.';
            arr[indexToPut] = symbol;
            for (int j = indexToPut + 1; j < arr.length; j++) {
                if (arr[j] == '.') {
                    indexToPut = j;
                    break;
                }
            }
        }
    }

    private static char[] getIndividualBlocks() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        int arrSize = 0;
        for (int i = 0; i < input.length(); i++) {
            arrSize += Integer.parseInt(input.charAt(i) + "");
        }

        char[] arr = new char[arrSize];
        int id = 0;
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            int num = Integer.parseInt(input.charAt(i) + "");
            if (i % 2 == 0) {
                for (int j = 0; j < num; j++) {
                    arr[index++] = (id + "").charAt(0);
                }
                id++;
            } else {
                for (int j = 0; j < num; j++) {
                    arr[index++] = '.';
                }
            }
        }
        return arr;
    }
}
