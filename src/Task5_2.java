import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task5_2 {
    private static List<Integer> rules = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long result = 0;

        List<List<Integer>> updates = new ArrayList<>();

        while (true) {
            String input = reader.readLine();
            if (input.equals("end")) {
                break;
            }

            int[] nums = Arrays.stream(input.split("\\|")).mapToInt(Integer::parseInt).toArray();
            rules.add(nums[0]);
            rules.add(nums[1]);
        }

        while (true) {
            String input = reader.readLine();
            if (input.equals("end")) {
                break;
            }

            boolean ruleIsViolated = false;
            List<Integer> update = Arrays.stream(input.split(","))
                    .map(Integer::parseInt).collect(Collectors.toList());
            for (int i = 0; i < update.size(); i++) {
                int currentNum = update.get(i);
                for (int j = 0; j < i; j++) {
                    int numBefore = update.get(j);
                    if (isRuleViolated(numBefore, currentNum)) {
                        ruleIsViolated = true;
                        break;
                    }
                }
                if (ruleIsViolated) {
                    break;
                }
                for (int j = i + 1; j < update.size(); j++) {
                    int numAfter = update.get(j);
                    if (isRuleViolated(currentNum, numAfter)) {
                        ruleIsViolated = true;
                        break;
                    }
                }
                if (ruleIsViolated) {
                    break;
                }
            }
            if (ruleIsViolated) {
                order(update);
                updates.add(update);
            }
        }

        for (List<Integer> update : updates) {
            result += update.get(update.size() / 2);
        }
        System.out.println(result);
    }

    private static void order(List<Integer> update) {
        for (int i = 0; i < update.size(); i++) {
            boolean isRuleViolated = false;
            int currentNum = update.get(i);
            for (int j = 0; j < i; j++) {
                int numBefore = update.get(j);
                if (isRuleViolated(numBefore, currentNum)) {
                    int index1 = update.indexOf(numBefore);
                    int index2 = update.indexOf(currentNum);
                    update.set(index1, currentNum);
                    update.set(index2, numBefore);
                    isRuleViolated = true;
                    break;
                }
            }
            if (isRuleViolated) {
                i = -1;
                continue;
            }
            for (int j = i + 1; j < update.size(); j++) {
                int numAfter = update.get(j);
                if (isRuleViolated(currentNum, numAfter)) {
                    int index1 = update.indexOf(currentNum);
                    int index2 = update.indexOf(numAfter);
                    update.set(index1, numAfter);
                    update.set(index2, currentNum);
                    isRuleViolated = true;
                    break;
                }
            }
            if (isRuleViolated) {
                i = -1;
            }
        }
    }

    private static boolean isRuleViolated(int numBefore, int currentNum) {
        for (int i = 0; i < rules.size(); i += 2) {
            if (rules.get(i) == currentNum && rules.get(i + 1) == numBefore) {
                return true;
            }
        }
        return false;
    }
}
