package com.session2.presentation;

import com.session2.common.Constant;
import com.session2.dao.IEnrollmentDAO;
import com.session2.dao.imp.EnrollmentDAO;
import com.session2.presentation.Admin.CoursePresentation;
import com.session2.presentation.Common.PressToBack;
import com.session2.service.imp.StudentService;
import com.session2.service.IStudentService;
import com.session2.utils.ConsoleColors;

import response.CourseRest;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class StudentPresentation {
    private Scanner scanner;
    private CoursePresentation coursePresentation = new CoursePresentation();
    private IStudentService studentService = new StudentService();
    private IEnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public StudentPresentation() {
        this.scanner = new Scanner(System.in);
    }

    public void display(int studentId) {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printHeader("MENU HỌC VIÊN");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Xem danh sách khóa học");
            ConsoleColors.printMenuItem("2", "Đăng ký khóa học");
            ConsoleColors.printMenuItem("3", "Xem khóa học đã đăng ký");
            ConsoleColors.printMenuItem("4", "Hủy đăng ký");
            ConsoleColors.printMenuItem("5", "Đổi mật khẩu");
            ConsoleColors.printMenuItem("6", "Đăng xuất");
            System.out.println();
            ConsoleColors.printSeparator();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        ConsoleColors.clearScreen();
                        coursePresentation.displayCourses();
                        break;
                    case 2:
                        ConsoleColors.clearScreen();
                        enrollCourse(studentId);
                        break;
                    case 3:
                        ConsoleColors.clearScreen();
                        registeredDisplay(studentId);
                        PressToBack.pressToBack();
                        break;
                    case 4:
                        ConsoleColors.clearScreen();
                        cancelEnrollment(studentId);
                        break;
                    case 5:
                        ConsoleColors.clearScreen();
                        changePassword(studentId);
                        break;
                    case 6:
                        ConsoleColors.printSuccess("Đăng xuất thành công!");
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.display();
                        break;
                    default:
                        ConsoleColors.printError("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Vui lòng nhập số!");
                choice = -1;
            }
        } while (choice != 6);
    }

    private void enrollCourse(int studentId) {
        this.coursePresentation.displayCourses();
        ConsoleColors.printPrompt("Nhập mã khóa học muốn đăng ký: ");
        String courseId = scanner.nextLine();

        Boolean isEnrolled = this.enrollmentDAO.enrollCourse(studentId, Integer.parseInt(courseId),
                Constant.REGISTERED);

        if (isEnrolled) {
            ConsoleColors.printSuccess("Đăng ký khóa học thành công!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
            return;
        }

        ConsoleColors.printError("Đăng ký khóa học thất bại!");
        ConsoleColors.delay(500);
        ConsoleColors.clearScreen();
    }

    private void registeredDisplay(int studentId) {
        ConsoleColors.printBox("KHÓA HỌC ĐÃ ĐĂNG KÝ");
        System.out.println();
        List<CourseRest> coursesRest = this.enrollmentDAO.getEnrollmentsByStudentId(studentId);

        ConsoleColors.printEnrollmentList(coursesRest);
    }

    private void cancelEnrollment(int studentId) {
        System.out.println();
        ConsoleColors.printBox("HỦY ĐĂNG KÝ");

        List<CourseRest> coursesRest = this.enrollmentDAO.getEnrollmentsByStudentId(studentId);
        List<CourseRest> registeredStatusCourse = coursesRest.stream()
                .filter(cr -> cr.getStatus().equals(Constant.REGISTERED)).toList();

        ConsoleColors.printEnrollmentList(registeredStatusCourse);
        if (registeredStatusCourse.isEmpty()) {
            PressToBack.pressToBack();
            return;
        }

        ConsoleColors.printPrompt("\nNhập mã khóa học muốn hủy: ");
        int courseId = Integer.parseInt(scanner.nextLine());
        for (CourseRest cr : registeredStatusCourse) {
            if (cr.getId() != courseId) {
                ConsoleColors.printError("Khóa học không tồn tại trong danh sách đã đăng ký!");
                ConsoleColors.delay(1000);
                ConsoleColors.clearScreen();
                return;
            }
        }

        ConsoleColors.printWarning("Bạn có chắc chắn muốn hủy đăng ký? (Y/N): ");
        String confirm = scanner.nextLine();

        if ("Y".equalsIgnoreCase(confirm)) {
            Boolean isDeleted = this.enrollmentDAO.deleteEnrollment(studentId, courseId);
            if (isDeleted) {
                ConsoleColors.printSuccess("Hủy đăng ký thành công!");
                ConsoleColors.delay(600);
                ConsoleColors.printWarning("Bạn có muốn hủy đăng ký khóa học khác? (Y/N): ");
                String confirmNext = scanner.nextLine();
                if ("Y".equalsIgnoreCase(confirmNext)) {
                    ConsoleColors.clearScreen();
                    cancelEnrollment(studentId);
                }
                return;
            }
            ConsoleColors.delay(700);
            ConsoleColors.clearScreen();
            ConsoleColors.printError("Hủy đăng ký thất bại!");
        } else {
            ConsoleColors.delay(700);
            ConsoleColors.clearScreen();
            ConsoleColors.printInfo("Đã hủy thao tác.");
        }
    }

    private void changePassword(int studentId) {
        ConsoleColors.printBox("ĐỔI MẬT KHẨU");
        System.out.println();
        ConsoleColors.printPrompt("Mật khẩu cũ: ");
        String oldPassword = scanner.nextLine();
        Boolean isAuthenticated = studentService.loginById(studentId, oldPassword);
        if (!isAuthenticated) {
            ConsoleColors.printError("Mật khẩu cũ không đúng!");
            return;
        }

        ConsoleColors.printPrompt("Mật khẩu mới: ");
        String newPassword = scanner.nextLine();
        ConsoleColors.printPrompt("Xác nhận mật khẩu mới: ");
        String confirmPassword = scanner.nextLine();

        if (!newPassword.equals(confirmPassword)) {
            ConsoleColors.printError("Mật khẩu xác nhận không khớp!");
            return;
        }

        Boolean isChanged = studentService.changePassword(studentId, newPassword);
        if (isChanged) {
            ConsoleColors.printSuccess("Đổi mật khẩu thành công!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
            return;
        }
        ConsoleColors.printError("Đổi mật khẩu thất bại!");
        ConsoleColors.delay(500);
        ConsoleColors.clearScreen();
    }
}
