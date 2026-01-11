package com.session2.presentation.Common;

import java.util.Scanner;

import com.session2.utils.ConsoleColors;

public class PressToBack {
    private static final Scanner scanner = new Scanner(System.in);

    public static void pressToBack() {
        do {
            ConsoleColors.printPrompt("Nhấn 'B' để quay lại: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("B")) {
                ConsoleColors.clearScreen();
                break;
            }
            ConsoleColors.printError("Lựa chọn không hợp lệ!");
        } while (true);
    }
}
