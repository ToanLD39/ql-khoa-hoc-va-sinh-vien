package com.session2.presentation;

import com.session2.utils.ConsoleColors;
import java.util.Scanner;

public class StudentPresentation {
    private Scanner scanner;
    
    public StudentPresentation() {
        this.scanner = new Scanner(System.in);
    }
    
    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printHeader("MENU HỌC VIÊN");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Xem thông tin cá nhân");
            ConsoleColors.printMenuItem("2", "Xem khóa học đã đăng ký");
            ConsoleColors.printMenuItem("3", "Đăng ký khóa học mới");
            ConsoleColors.printMenuItem("4", "Hủy đăng ký khóa học");
            ConsoleColors.printMenuItem("5", "Xem danh sách khóa học có sẵn");
            ConsoleColors.printMenuItem("0", "Đăng xuất");
            System.out.println();
            ConsoleColors.printSeparator();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        viewProfile();
                        break;
                    case 2:
                        viewEnrolledCourses();
                        break;
                    case 3:
                        enrollInCourse();
                        break;
                    case 4:
                        cancelEnrollment();
                        break;
                    case 5:
                        viewAvailableCourses();
                        break;
                    case 0:
                        ConsoleColors.printSuccess("Đăng xuất thành công!");
                        break;
                    default:
                        ConsoleColors.printError("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Vui lòng nhập số!");
                choice = -1;
            }
        } while (choice != 0);
    }
    
    private void viewProfile() {
        System.out.println();
        ConsoleColors.printBox("THÔNG TIN CÁ NHÂN");
        System.out.println();
        ConsoleColors.printInfo("Mã học viên: SV001");
        ConsoleColors.printInfo("Họ và tên: Nguyễn Văn A");
        ConsoleColors.printInfo("Email: student@example.com");
        ConsoleColors.printInfo("Số điện thoại: 0123456789");
        System.out.println();
        ConsoleColors.printWarning("(Dữ liệu mẫu - Chức năng đang được phát triển)");
    }
    
    private void viewEnrolledCourses() {
        System.out.println();
        ConsoleColors.printBox("KHÓA HỌC ĐÃ ĐĂNG KÝ");
        System.out.println();
        ConsoleColors.println(ConsoleColors.GREEN, "1. Java Programming - Đang học");
        ConsoleColors.println(ConsoleColors.BLUE, "2. Database Design - Hoàn thành");
        System.out.println();
        ConsoleColors.printWarning("(Dữ liệu mẫu - Chức năng đang được phát triển)");
    }
    
    private void enrollInCourse() {
        System.out.println();
        ConsoleColors.printBox("ĐĂNG KÝ KHÓA HỌC");
        viewAvailableCourses();
        ConsoleColors.printPrompt("\nNhập mã khóa học muốn đăng ký: ");
        String courseId = scanner.nextLine();
        
        // TODO: Gọi service để đăng ký khóa học
        ConsoleColors.printSuccess("Đăng ký thành công!");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void cancelEnrollment() {
        System.out.println();
        ConsoleColors.printBox("HỦY ĐĂNG KÝ");
        viewEnrolledCourses();
        ConsoleColors.printPrompt("\nNhập mã khóa học muốn hủy: ");
        String courseId = scanner.nextLine();
        
        ConsoleColors.printWarning("Bạn có chắc chắn muốn hủy đăng ký? (Y/N): ");
        String confirm = scanner.nextLine();
        
        if ("Y".equalsIgnoreCase(confirm)) {
            // TODO: Gọi service để hủy đăng ký
            ConsoleColors.printSuccess("Hủy đăng ký thành công!");
            ConsoleColors.printWarning("(Chức năng đang được phát triển)");
        } else {
            ConsoleColors.printInfo("Đã hủy thao tác.");
        }
    }
    
    private void viewAvailableCourses() {
        System.out.println();
        ConsoleColors.printBox("KHÓA HỌC CÓ SẴN");
        System.out.println();
        ConsoleColors.println(ConsoleColors.CYAN, "1. KH001 - Java Programming");
        ConsoleColors.println(ConsoleColors.CYAN, "2. KH002 - Database Design");
        ConsoleColors.println(ConsoleColors.CYAN, "3. KH003 - Web Development");
        ConsoleColors.println(ConsoleColors.CYAN, "4. KH004 - Mobile App Development");
        System.out.println();
        ConsoleColors.printWarning("(Dữ liệu mẫu - Chức năng đang được phát triển)");
    }
}
