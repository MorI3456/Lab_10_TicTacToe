import java.util.Scanner;

public class SafeInput {

    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int input = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            if (console.hasNextInt()) {
                input = console.nextInt();
                if (input >= low && input <= high) {
                    valid = true;
                } else {
                    System.out.println("Error: input must be between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Error: invalid input. Please enter an integer.");
                console.next(); // Clear the invalid input
            }
        }
        return input;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        String response;
        do {
            System.out.print(prompt);
            response = console.next().trim().toLowerCase();
        } while (!response.equals("y") && !response.equals("n"));
        return response.equals("y");
    }
}
