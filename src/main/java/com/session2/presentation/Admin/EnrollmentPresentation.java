package com.session2.presentation.Admin;

import java.util.Scanner;

import com.session2.utils.ConsoleColors;

public class EnrollmentPresentation {
    Scanner scanner;

    public EnrollmentPresentation() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printBox("QUẢN LÝ ĐĂNG KÝ KHÓA HỌC");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Hiển thị học viên theo từng khóa học");
            ConsoleColors.printMenuItem("2", "Thêm học viên vào khóa học");
            ConsoleColors.printMenuItem("3", "Xóa học viên khỏi khóa học");
            ConsoleColors.printMenuItem("4", "Quay về menu chính");
            
            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        ConsoleColors.printInfo("Danh sách học viên:");
                        // TODO: Gọi service để lấy danh sách học viên
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
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
