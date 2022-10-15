package tools;

import java.util.Scanner;

public class MyTool {
    public static final String filename = "product.txt";
    public static final Scanner sc = new Scanner(System.in);

    public static int getInt(String mess) {
        while (true) {
            try {
                System.out.print(mess);
                int result = Integer.parseInt(sc.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a number!!!");
            }
        }
    }

    public static int getChoiceForSubMenu(String mess) {
        while (true) {
            try {
                System.out.print(mess);
                Double result = Double.parseDouble(sc.nextLine().trim());
                if (result == 5.1) {
                    return 1;
                } else if (result == 5.2) {
                    return 2;
                } else {
                    return 0;

                }
            } catch (NumberFormatException e) {
                System.err.println("Please input a number!!!");
            }
        }
    }

    public static int checkInputIntLimit(String mess, int min, int max) {
        // loop until user input correct
        while (true) {
            try {
                System.out.print(mess);
                int result = Integer.parseInt(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a integer number in rage [" + min + ", " + max + "]");
            }
        }
    }

    public static double checkInputDoubleLimit(String mess, int min, int max) {
        // loop until user input correct
        while (true) {
            try {
                System.out.print(mess);
                double result = Double.parseDouble(sc.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a real number in rage [" + min + ", " + max + "]");
            }
        }
    }

    public static String getString(String mess, String remindMess) {
        String input;
        while (true) {
            System.out.print(mess);
            input = sc.nextLine().trim();
            if (input.isEmpty() || input.length() == 0) {
                System.out.println(remindMess);
            } else {
                return input;
            }
        }
    }

    public static String getStringWithRegex(String mess, String remindMess, String regex) {
        while (true) {
            System.out.print(mess);
            String input = sc.nextLine();
            if (!input.matches(regex)) {
                System.out.println(remindMess);
                continue;
            }
            return input;
        }
    }

    public static String confirmAvailable() {
        while (true) {
            System.out.print("Input y/Y for Available and n/N for Not Available: ");
            String result = sc.nextLine();
            if (result.equalsIgnoreCase("Y")) {
                return "Available";
            } else if (result.equalsIgnoreCase("N")) {
                return "Not Available";
            }
            System.err.println("Please input y/Y or n/N !!!");
        }
    }

    public static String confirmAvailableForUpdate() {
        while (true) {
            System.out.print("Input y/Y for Available and n/N for Not Available: ");
            String result = sc.nextLine();
            if (result.equalsIgnoreCase("Y")) {
                return "Available";
            } else if (result.equalsIgnoreCase("N")) {
                return "Not Available";
            } else if (result.isEmpty()) {
                return "";
            }
            System.err.println("Please input y/Y or n/N !!!");
        }
    }

    public static boolean confirmYesNo(String mess) {
        while (true) {
            System.out.print(mess);
            String result = sc.nextLine();
            // check user input y/Y or n/N
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
        }
    }
}
