package com.session2.presentation;

import com.session2.presentation.Admin.CoursePresentation;
import com.session2.presentation.Admin.EnrollmentPresentation;
import com.session2.presentation.Admin.StatisticsPresentation;
import com.session2.presentation.Admin.StudentPresentation;
import com.session2.utils.ConsoleColors;
import java.util.Scanner;

public class AdminPresentation {
    private Scanner scanner;
    private CoursePresentation coursePresentation;
    private StudentPresentation studentPresentation;
    private EnrollmentPresentation enrollmentPresentation;
    private StatisticsPresentation statisticsPresentation;

    public AdminPresentation() {
        this.scanner = new Scanner(System.in);
        this.coursePresentation = new CoursePresentation();
        this.studentPresentation = new StudentPresentation();
        this.enrollmentPresentation = new EnrollmentPresentation();
        this.statisticsPresentation = new StatisticsPresentation();
    }

    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printHeader("     MENU ADMIN      ");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Quản lý khóa học");
            ConsoleColors.printMenuItem("2", "Quản lý học viên");
            ConsoleColors.printMenuItem("3", "Quản lý đăng ký học");
            ConsoleColors.printMenuItem("4", "Thống kê học viên theo khóa học");
            ConsoleColors.printMenuItem("5", "Đăng xuất");
            ConsoleColors.printSeparator();
            ConsoleColors.printPrompt("Chọn thông tin cần sửa: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        ConsoleColors.clearScreen();
                        coursePresentation.display();
                        break;
                    case 2:
                        ConsoleColors.clearScreen();
                        studentPresentation.display();
                        break;
                    case 3:
                        ConsoleColors.clearScreen();
                        enrollmentPresentation.display();
                        break;
                    case 4:
                        ConsoleColors.clearScreen();
                        statisticsPresentation.display();
                        break;
                    case 5:
                        ConsoleColors.printSuccess("Đăng xuất thành công!");
                        ConsoleColors.delay(300);
                        ConsoleColors.clearScreen();
                        break;
                    default:
                        ConsoleColors.printError("Lựa chọn không hợp lệ!");
                        ConsoleColors.delay(300);
                        ConsoleColors.clearScreen();
                }
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Vui lòng nhập số!");
                choice = -1;
            }
        } while (choice != 5);
    }
}
