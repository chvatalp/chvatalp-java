import java.util.ArrayList;
import java.util.Scanner;

public class GreatestInList {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Integer> numbersList = new ArrayList<>();

        while (true) {
            int number = Integer.parseInt(reader.nextLine());
            if (number == -1) {
                break;
            }
            numbersList.add(number);
        }

        int greatestNumber = numbersList.get(0);
        for (int num : numbersList) {
            if (num > greatestNumber) {
                greatestNumber = num;
            }
        }

        System.out.println("The greatest number is " + greatestNumber);
    }
}
