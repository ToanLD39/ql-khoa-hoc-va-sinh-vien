package com.session2.utils;

/**
 * Utility class để thêm màu sắc cho console output
 */
public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";
    
    // Regular Colors
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    
    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";
    
    // Background
    public static final String BLACK_BG = "\033[40m";
    public static final String RED_BG = "\033[41m";
    public static final String GREEN_BG = "\033[42m";
    public static final String YELLOW_BG = "\033[43m";
    public static final String BLUE_BG = "\033[44m";
    public static final String PURPLE_BG = "\033[45m";
    public static final String CYAN_BG = "\033[46m";
    public static final String WHITE_BG = "\033[47m";
    
    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";
    public static final String RED_BRIGHT = "\033[0;91m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String YELLOW_BRIGHT = "\033[0;93m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String PURPLE_BRIGHT = "\033[0;95m";
    public static final String CYAN_BRIGHT = "\033[0;96m";
    public static final String WHITE_BRIGHT = "\033[0;97m";
    
    /**
     * In text với màu sắc
     */
    public static void print(String color, String text) {
        System.out.print(color + text + RESET);
    }
    
    /**
     * In text với màu sắc và xuống dòng
     */
    public static void println(String color, String text) {
        System.out.println(color + text + RESET);
    }
    
    /**
     * In header với background màu và chữ bold
     */
    public static void printHeader(String text) {
        System.out.println(CYAN_BOLD + "╔" + "═".repeat(text.length() + 2) + "╗" + RESET);
        System.out.println(CYAN_BOLD + "║ " + WHITE_BOLD + text + CYAN_BOLD + " ║" + RESET);
        System.out.println(CYAN_BOLD + "╚" + "═".repeat(text.length() + 2) + "╝" + RESET);
    }
    
    /**
     * In menu item
     */
    public static void printMenuItem(String number, String text) {
        System.out.println(YELLOW_BOLD + number + ". " + WHITE + text + RESET);
    }
    
    /**
     * In thông báo thành công
     */
    public static void printSuccess(String text) {
        System.out.println(GREEN_BOLD + "✓ " + text + RESET);
    }
    
    /**
     * In thông báo lỗi
     */
    public static void printError(String text) {
        System.out.println(RED_BOLD + "✗ " + text + RESET);
    }
    
    /**
     * In thông báo cảnh báo
     */
    public static void printWarning(String text) {
        System.out.println(YELLOW_BOLD + "⚠ " + text + RESET);
    }
    
    /**
     * In thông báo thông tin
     */
    public static void printInfo(String text) {
        System.out.println(BLUE_BOLD + "ℹ " + text + RESET);
    }
    
    /**
     * In prompt input
     */
    public static void printPrompt(String text) {
        System.out.print(CYAN + "➤ " + text + RESET);
    }
    
    /**
     * In separator/divider
     */
    public static void printSeparator() {
        System.out.println(CYAN + "═".repeat(50) + RESET);
    }
    
    /**
     * In box với text
     */
    public static void printBox(String text) {
        int length = text.length() + 4;
        System.out.println(PURPLE_BOLD + "┌" + "─".repeat(length - 2) + "┐" + RESET);
        System.out.println(PURPLE_BOLD + "│ " + WHITE_BRIGHT + text + PURPLE_BOLD + " │" + RESET);
        System.out.println(PURPLE_BOLD + "└" + "─".repeat(length - 2) + "┘" + RESET);
    }
    
    /**
     * Clear console screen
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Delay execution
     */
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
