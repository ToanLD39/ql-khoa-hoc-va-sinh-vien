package com.session2.presentation.Common;

import java.util.Scanner;

import com.session2.utils.ConsoleColors;

public class ConfirmDelelte {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean confirmAction(String message) {
        String confirmation;
        do {
            ConsoleColors.printWarning(message + " (Y/N): ");
            confirmation = scanner.nextLine().trim().toUpperCase();

            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                ConsoleColors.printError("Vui lòng nhập Y hoặc N!");
            }
        } while (true);
    }
}
