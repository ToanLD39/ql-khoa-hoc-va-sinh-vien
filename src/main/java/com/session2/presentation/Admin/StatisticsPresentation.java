package com.session2.presentation.Admin;

import java.util.Scanner;

import com.session2.utils.ConsoleColors;

public class StatisticsPresentation {
    Scanner scanner;

    public StatisticsPresentation() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printBox("QUẢN LÝ ĐĂNG KÝ KHÓA HỌC");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Thống kê tổng số lượng khóa học và học viên");
            ConsoleColors.printMenuItem("2", "Thống kê học viên theo từng khóa học");
            ConsoleColors.printMenuItem("3", "Top 5 khóa học đông học viên nhất");
            ConsoleColors.printMenuItem("4", "Liệt kê khóa học có trên 10 học viên");
            ConsoleColors.printMenuItem("5", "Quay về menu chính");
            
            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        ConsoleColors.printInfo("Danh sách học viên:");
                        // TODO: Gọi service để lấy danh sách học viên
                        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
                        break;
                    case 2:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        // addStudent();
                        break;
                    case 3:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        // updateStudent();
                        break;
                    case 4:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        // deleteStudent();
                        break;
                    case 5:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        break;
                    default:
                        ConsoleColors.printError("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Vui lòng nhập số!");
                choice = -1;
            }
        } while (choice != 7);
    }
}
