package com.session2.presentation.Admin;

import java.util.Scanner;

import com.session2.utils.ConsoleColors;

public class StudentPresentation {
    Scanner scanner;

    public StudentPresentation() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printBox("QUẢN LÝ HỌC VIÊN");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Hiển thị danh sách học viên");
            ConsoleColors.printMenuItem("2", "Thêm mới học viên");
            ConsoleColors.printMenuItem("3", "Chỉnh sửa thông tin học viên");
            ConsoleColors.printMenuItem("4", "Xóa học viên");
            ConsoleColors.printMenuItem("5", "Tìm kiếm theo tên, email hoặc id");
            ConsoleColors.printMenuItem("6", "Sắp xếp theo tên hoặc id");
            ConsoleColors.printMenuItem("7", "Quay về menu chính");
            
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
                        // searchStudent();
                        break;
                    case 6:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        // sortStudent();
                        break;
                    case 7:
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
