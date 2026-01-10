package com.session2.presentation;

import com.session2.service.IAdminService;
import com.session2.service.IStudentService;
import com.session2.service.imp.AdminService;
import com.session2.service.imp.StudentService;
import com.session2.utils.ConsoleColors;
import java.util.Scanner;

public class MainMenu {
    private IAdminService adminService;
    private IStudentService studentService;

    private Scanner scanner;

    private AdminPresentation adminPresentation;
    private StudentPresentation studentPresentation;
    
    public MainMenu() {
        this.adminService = new AdminService();
        this.studentService = new StudentService();

        this.scanner = new Scanner(System.in);

        this.adminPresentation = new AdminPresentation();
        this.studentPresentation = new StudentPresentation();

    }
    
    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printHeader("HỆ THỐNG QUẢN LÝ ĐÀO TẠO");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Đăng nhập với tư cách Quản trị viên");
            ConsoleColors.printMenuItem("2", "Đăng nhập với tư cách Học viên");
            ConsoleColors.printMenuItem("3", "Thoát");
            System.out.println();
            ConsoleColors.printSeparator();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        studentLogin();
                        break;
                    case 3:
                        ConsoleColors.printSuccess("Cảm ơn bạn đã sử dụng hệ thống!");
                        break;
                    default:
                        ConsoleColors.printError("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Vui lòng nhập số!");
                choice = 0;
            }
        } while (choice != 3);
    }
    
    private void adminLogin() {
        System.out.println();
        ConsoleColors.printBox("ĐĂNG NHẬP QUẢN TRỊ VIÊN");
        System.out.println();
        ConsoleColors.printPrompt("Tên đăng nhập: ");
        String username = scanner.nextLine();
        ConsoleColors.printPrompt("Mật khẩu: ");
        String password = scanner.nextLine();
        
        // Kiểm tra đăng nhập với database thông qua AdminService
        Boolean isAuthenticated = adminService.login(username, password);
        
        if (isAuthenticated) {
            ConsoleColors.printSuccess("Đăng nhập thành công!");
            ConsoleColors.delay(1000); // Đợi 1 giây
            ConsoleColors.clearScreen(); // Xóa màn hình
            adminPresentation.display();
        } else {
            ConsoleColors.printError("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
    }
    
    private void studentLogin() {
        System.out.println();
        ConsoleColors.printBox("ĐĂNG NHẬP HỌC VIÊN");
        System.out.println();
        ConsoleColors.printPrompt("Mã học viên: ");
        String studentId = scanner.nextLine();
        ConsoleColors.printPrompt("Mật khẩu: ");
        String password = scanner.nextLine();
        
        Boolean isAuthenticated = studentService.login(studentId, password);
        
        if (isAuthenticated) {
            ConsoleColors.printSuccess("Đăng nhập thành công!");
            ConsoleColors.delay(1000);
            ConsoleColors.clearScreen();
            studentPresentation.display();
        } else {
            ConsoleColors.printError("Mã học viên hoặc mật khẩu không đúng!");
        }
    }
    
    public void close() {
        scanner.close();
    }
}
