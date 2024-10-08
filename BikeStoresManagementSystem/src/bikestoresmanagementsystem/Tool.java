package BikeStoresManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Tool {

    public static final Scanner sc = new Scanner(System.in);

    public static int int_menu(ArrayList<String> opts) {

        int N = opts.size();

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + opts.get(i));
        }

        String inputString;
        Pattern inputPattern = Pattern.compile("\\d+");

        do {
            System.out.print("Please choose an option: 1..." + N + ": ");
            inputString = readStr();
            if (!inputPattern.matcher(inputString).find()) {
                System.err.println("Please enter a digit!!");
            }
        } while (!inputPattern.matcher(inputString).find());

        return Integer.parseInt(inputString);
    }

    public static String readStr(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    public static String readStr() {
        return sc.nextLine().trim();
    }

    public static int readInt(String prompt, String type) {
        Pattern inputPattern = Pattern.compile("\\d+");
        String input;
        do {
            input = readStr(prompt);
            if (!inputPattern.matcher(input).find()) {
                System.err.println("Please enter a valid " + type);
            }
        } while (!inputPattern.matcher(input).find());
        input = input.replaceAll("\\D+", "");
        return (int) Math.floor(Double.parseDouble(input));
    }

    public static int readInt(String prompt) {
        Pattern inputPattern = Pattern.compile("\\d+");
        String input;
        do {
            input = readStr(prompt);
            if (!inputPattern.matcher(input).find()) {
                System.out.println("Please enter a valid number");
            }
        } while (!inputPattern.matcher(input).find());
        input = input.replaceAll("\\D+", "");
        return (int) Math.floor(Double.parseDouble(input));
    }

    /**
     * Automatically generates an increasing code.
     *
     * @param prefix    Ex: P
     * @param length    Ex: 7
     * @param curNumber Ex: 123
     * @return Ex: P0000123
     */
    public static String generateCode(String prefix, int length, int curNumber) {
        String formatStr = "%0" + length + "d";
        return prefix + String.format(formatStr, curNumber);
    }

    public static String generateCodeFromStr() {
        int curNum = readInt("Enter ID number", "ID number");
        return generateCode("P", 3, curNum);
    }
}