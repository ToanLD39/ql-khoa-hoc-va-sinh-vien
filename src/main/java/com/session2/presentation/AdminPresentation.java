package com.session2.presentation;

import com.session2.utils.ConsoleColors;
import java.util.Scanner;

public class AdminPresentation {
    private Scanner scanner;
    
    public AdminPresentation() {
        this.scanner = new Scanner(System.in);
    }
    
    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printHeader("MENU ADMIN");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Quản lý khóa học");
            ConsoleColors.printMenuItem("2", "Quản lý học viên");
            ConsoleColors.printMenuItem("3", "Quản lý đăng ký học");
            ConsoleColors.printMenuItem("4", "Thống kê học viên theo khóa học");
            ConsoleColors.printMenuItem("0", "Đăng xuất");
            System.out.println();
            ConsoleColors.printSeparator();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        manageStudents();
                        break;
                    case 2:
                        manageCourses();
                        break;
                    case 3:
                        manageEnrollments();
                        break;
                    case 4:
                        viewStatistics();
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
    
    private void manageStudents() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printBox("QUẢN LÝ HỌC VIÊN");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Xem danh sách học viên");
            ConsoleColors.printMenuItem("2", "Thêm học viên mới");
            ConsoleColors.printMenuItem("3", "Cập nhật thông tin học viên");
            ConsoleColors.printMenuItem("4", "Xóa học viên");
            ConsoleColors.printMenuItem("5", "Tìm kiếm học viên");
            ConsoleColors.printMenuItem("0", "Quay lại");
            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        ConsoleColors.printInfo("Danh sách học viên:");
                        // TODO: Gọi service để lấy danh sách học viên
                        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
                        break;
                    case 2:
                        addStudent();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        searchStudent();
                        break;
                    case 0:
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
    
    private void addStudent() {
        System.out.println();
        ConsoleColors.printBox("THÊM HỌC VIÊN MỚI");
        System.out.println();
        ConsoleColors.printPrompt("Họ và tên: ");
        String name = scanner.nextLine();
        ConsoleColors.printPrompt("Email: ");
        String email = scanner.nextLine();
        ConsoleColors.printPrompt("Số điện thoại: ");
        String phone = scanner.nextLine();
        
        // TODO: Gọi service để thêm học viên
        ConsoleColors.printSuccess("Thêm học viên thành công!");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void updateStudent() {
        System.out.println();
        ConsoleColors.printBox("CẬP NHẬT HỌC VIÊN");
        System.out.println();
        ConsoleColors.printPrompt("Nhập mã học viên cần cập nhật: ");
        String studentId = scanner.nextLine();
        
        // TODO: Gọi service để cập nhật học viên
        ConsoleColors.printSuccess("Cập nhật học viên thành công!");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void deleteStudent() {
        System.out.println();
        ConsoleColors.printBox("XÓA HỌC VIÊN");
        System.out.println();
        ConsoleColors.printPrompt("Nhập mã học viên cần xóa: ");
        String studentId = scanner.nextLine();
        
        // TODO: Gọi service để xóa học viên
        ConsoleColors.printSuccess("Xóa học viên thành công!");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void searchStudent() {
        System.out.println();
        ConsoleColors.printBox("TÌM KIẾM HỌC VIÊN");
        System.out.println();
        ConsoleColors.printPrompt("Nhập từ khóa (tên hoặc mã): ");
        String keyword = scanner.nextLine();
        
        // TODO: Gọi service để tìm kiếm học viên
        ConsoleColors.printInfo("Kết quả tìm kiếm:");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void manageCourses() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printBox("QUẢN LÝ KHÓA HỌC");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Xem danh sách khóa học");
            ConsoleColors.printMenuItem("2", "Thêm khóa học mới");
            ConsoleColors.printMenuItem("3", "Cập nhật thông tin khóa học");
            ConsoleColors.printMenuItem("4", "Xóa khóa học");
            ConsoleColors.printMenuItem("0", "Quay lại");
            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        ConsoleColors.printInfo("Danh sách khóa học:");
                        // TODO: Gọi service để lấy danh sách khóa học
                        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
                        break;
                    case 2:
                        addCourse();
                        break;
                    case 3:
                        updateCourse();
                        break;
                    case 4:
                        deleteCourse();
                        break;
                    case 0:
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
    
    private void addCourse() {
        System.out.println();
        ConsoleColors.printBox("THÊM KHÓA HỌC MỚI");
        System.out.println();
        ConsoleColors.printPrompt("Tên khóa học: ");
        String courseName = scanner.nextLine();
        ConsoleColors.printPrompt("Mô tả: ");
        String description = scanner.nextLine();
        
        // TODO: Gọi service để thêm khóa học
        ConsoleColors.printSuccess("Thêm khóa học thành công!");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void updateCourse() {
        System.out.println();
        ConsoleColors.printBox("CẬP NHẬT KHÓA HỌC");
        System.out.println();
        ConsoleColors.printPrompt("Nhập mã khóa học cần cập nhật: ");
        String courseId = scanner.nextLine();
        
        // TODO: Gọi service để cập nhật khóa học
        ConsoleColors.printSuccess("Cập nhật khóa học thành công!");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void deleteCourse() {
        System.out.println();
        ConsoleColors.printBox("XÓA KHÓA HỌC");
        System.out.println();
        ConsoleColors.printPrompt("Nhập mã khóa học cần xóa: ");
        String courseId = scanner.nextLine();
        
        // TODO: Gọi service để xóa khóa học
        ConsoleColors.printSuccess("Xóa khóa học thành công!");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void manageEnrollments() {
        System.out.println();
        ConsoleColors.printBox("QUẢN LÝ ĐĂNG KÝ");
        System.out.println();
        ConsoleColors.printInfo("Xem danh sách đăng ký học:");
        // TODO: Gọi service để lấy danh sách đăng ký
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
    
    private void viewStatistics() {
        System.out.println();
        ConsoleColors.printBox("THỐNG KÊ");
        System.out.println();
        ConsoleColors.printInfo("Tổng số học viên: (đang cập nhật)");
        ConsoleColors.printInfo("Tổng số khóa học: (đang cập nhật)");
        ConsoleColors.printInfo("Tổng số đăng ký: (đang cập nhật)");
        ConsoleColors.printWarning("(Chức năng đang được phát triển)");
    }
}
