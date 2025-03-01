import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int counter = 0;
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            List<Integer> numbers = Arrays.stream(input.split(" "))
                    .map(Integer::parseInt).collect(Collectors.toList());

            if (isDecreasing(numbers) || isIncreasing(numbers)) {
                counter++;
            } else {
                for (int i = 0; i < numbers.size(); i++) {
                    Integer element = numbers.remove(i);
                    if (isDecreasing(numbers) || isIncreasing(numbers)) {
                        counter++;
                        break;
                    }
                    numbers.add(i, element);
                }
            }
        }

        System.out.println(counter);
    }

    private static boolean isIncreasing(List<Integer> nums) {
        int currentNum = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            int newNum = nums.get(i);
            if (newNum > currentNum && (newNum - currentNum >= 1 && newNum - currentNum <= 3)) {
                currentNum = newNum;
                continue;
            }
            return false;
        }
        return true;
    }

    private static boolean isDecreasing(List<Integer> nums) {
        int currentNum = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            int newNum = nums.get(i);
            if (newNum < currentNum && (currentNum - newNum >= 1 && currentNum - newNum <= 3)) {
                currentNum = newNum;
                continue;
            }
            return false;
        }
        return true;
    }
}
