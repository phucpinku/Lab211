package laptoprammanagement;

import java.util.Scanner;
import java.util.regex.Pattern;

public class myTool {

    public static final Scanner sc = new Scanner(System.in);

    public static int Choose(Object... opts) {

        int N = opts.length;

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + opts[i].toString());
        }

        return readInt("Please choose an option: 1..." + N + ": ");
    }

    public static String readStr(String prompt) {
        System.out.print(prompt + ": ");
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
                System.err.println("Please enter a valid number");
            }
        } while (!inputPattern.matcher(input).find());

        input = input.replaceAll("\\D+", "");

        return (int) Math.floor(Double.parseDouble(input));
    }

    public static String generateCode(String prefix, int length, int curNumber) {
        String formatStr = "%0" + length + "d";
        return prefix + String.format(formatStr, curNumber);
    }

    public static String generateCodeFromStr(String type) {
        int curNum = readInt("Enter CODE number", "CODE number");
        return generateCode("RAM_" + type + "_", 4, curNum);
    }

    public static void searchMenu(RAMList ramList, Scanner sc) {
        int searchChoice;
        do {
            System.out.println("1. Search by type");
            System.out.println("2. Search by bus speed");
            System.out.println("3. Search by brand");
            System.out.println("4. Back to main menu");
            System.out.print("Enter choice: ");

            searchChoice = sc.nextInt();
            sc.nextLine();

            switch (searchChoice) {
                case 1:
                    ramList.searchByType();
                    break;
                case 2:
                    ramList.searchByBus();
                    break;
                case 3:
                    ramList.searchByBrand();
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (searchChoice != 4);
    }
}
