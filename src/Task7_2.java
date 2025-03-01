import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Task7_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long result = 0;

        while (true) {
            String input = reader.readLine();
            if (input.equals("end")) {
                break;
            }

            long testResult = Long.parseLong(input.split(":")[0]);
            long[] tokens = Arrays.stream(input.split(":")[1].trim().split("\\s+"))
                    .mapToLong(Long::parseLong).toArray();

            if (canBeEvaluated(testResult, tokens, tokens.length - 1)) {
                result += testResult;
            }
        }
        System.out.println(result);
    }

    private static boolean canBeEvaluated(long result, long[] tokens, int index) {
        if (index == 0) {
            return result == tokens[0];
        }

        long number = tokens[index--];

        boolean canBeSubtracted = canBeEvaluated(result - number, tokens, index);
        boolean canBeDivided = result % number == 0 && canBeEvaluated(result / number, tokens, index);

        int modDivider = (int) Math.pow(10, (number + "").length());

        boolean canBeConcatenated = result % modDivider == number &&
                (canBeEvaluated(result / modDivider, tokens, index));

        return canBeSubtracted || canBeDivided || canBeConcatenated;
    }
}
