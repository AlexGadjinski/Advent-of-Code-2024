import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Task1 {
    public static void main(String[] args) throws IOException {
        List<Integer> input = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = reader.readLine();
            if (line.equals("end")) {
                break;
            }
            input.addAll(Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList());
        }

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            if (i % 2 == 0) {
                leftList.add(input.get(i));
            } else {
                rightList.add(input.get(i));
            }
        }
//        Collections.sort(leftList);
//        Collections.sort(rightList);
//
        long totalDistance = 0;
//
//        while (!leftList.isEmpty()) {
//            totalDistance += Math.abs(leftList.remove(0) - rightList.remove(0));
//        }
//
//        System.out.println(totalDistance);

        totalDistance = leftList.stream()
                .mapToLong(leftEl -> {
                    long count = rightList.stream().filter(rightEl -> Objects.equals(rightEl, leftEl)).count();
                    return leftEl * count;
                }).sum();
        System.out.println(totalDistance);
    }
}
