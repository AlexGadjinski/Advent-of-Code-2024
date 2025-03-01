import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        regex for 1. exercise
//        mul\({1}[0-9]{1,3}[,]{1}[0-9]{1,3}[\)]{1}

//        regex for 2. exercise
//        don't[(]{1}[)]{1}
//        do[(]{1}[)]{1}
//        (mul\([0-9]{1,3},[0-9]{1,3}[)])|(don't[(]{1}[)]{1})|(do[(]{1}[)]{1})

        int result = 0;

        boolean readEnabled = true;
        while (true) {
            String input = reader.readLine();
            if (input.equals("end")) {
                break;
            }

//            Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}[)]");
//            Matcher matcher = pattern.matcher(input);
//
//            while (matcher.find()) {
//                String[] arr = matcher.group().split(",");
//                int num1 = Integer.parseInt(arr[0].replace("mul(", ""));
//                int num2 = Integer.parseInt(arr[1].replace(")", ""));
//                result += num1 * num2;
//            }

//            Combine the three regular expressions into a single one using the alternation operator (|).
//            This allows you to search for any of the patterns in a single pass.
            Pattern pattern = Pattern.compile("(mul\\([0-9]{1,3},[0-9]{1,3}[)])|(don't[(][)])|(do[(][)])");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String matched = matcher.group();
                if (matched.equals("don't()")) {
                    readEnabled = false;
                } else if (matched.equals("do()")) {
                    readEnabled = true;
                } else if (readEnabled) {
                    String[] arr = matcher.group().split(",");
                    int num1 = Integer.parseInt(arr[0].replace("mul(", ""));
                    int num2 = Integer.parseInt(arr[1].replace(")", ""));
                    result += num1 * num2;
                }
            }
        }
        System.out.println(result);
    }
}
