package com.session2.presentation.Admin;

import java.io.Console;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.session2.common.Constant;
import com.session2.model.Course;
import com.session2.model.Student;
import com.session2.presentation.Common.ConfirmDelelte;
import com.session2.presentation.Common.PressToBack;
import com.session2.service.ICourseService;
import com.session2.service.IEnrollmentService;
import com.session2.service.imp.CourseService;
import com.session2.service.imp.EnrollmentService;
import com.session2.utils.ConsoleColors;

import response.StudentRest;

public class EnrollmentPresentation {
    Scanner scanner;

    private ICourseService courseService = new CourseService();
    private IEnrollmentService enrollmentService = new EnrollmentService();

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
            ConsoleColors.printMenuItem("4", "Duyệt sinh viên đăng ký khóa học");
            ConsoleColors.printMenuItem("5", "Quay về menu chính");

            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        courseDisplay();
                        break;
                    case 2:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        addEnrollment();
                        break;
                    case 3:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        deleteEnrollment();
                        break;
                    case 4:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        approveEnrollment();
                        break;
                    case 5:
                        break;
                    default:
                        ConsoleColors.printError("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Vui lòng nhập số!");
                choice = -1;
            }
        } while (choice != 5);
    }

    public void courseDisplay() {
        ConsoleColors.printHeader("DANH SÁCH KHÓA HỌC");
        List<Course> courses = this.courseService.getAllCourses();
        ConsoleColors.printCourseList(courses);
        System.out.println();
        ConsoleColors.printPrompt("Nhập ID khóa học để xem học viên đăng ký hoặc 'q' để quay lại: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int courseId = Integer.parseInt(input);
            List<Student> students = this.enrollmentService.getStudentsByCourseId(courseId);
            if (students != null && !students.isEmpty()) {
                ConsoleColors.printHeader("DANH SÁCH HỌC VIÊN KHÓA HỌC ID: " + courseId);
                ConsoleColors.printStudentList(students);
            } else {
                ConsoleColors.printError("Không có học viên nào trong khóa học này!");
            }
        } catch (NumberFormatException e) {
            ConsoleColors.printError("ID không hợp lệ!");
            ConsoleColors.delay(500);
        }
        PressToBack.pressToBack();
    }

    private void addEnrollment() {
        ConsoleColors.printHeader("THÊM HỌC VIÊN VÀO KHÓA HỌC");

        try {
            ConsoleColors.printPrompt("Nhập ID học viên: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            ConsoleColors.printPrompt("Nhập ID khóa học: ");
            int courseId = Integer.parseInt(scanner.nextLine());

            boolean success = this.enrollmentService.enrollCourse(studentId, courseId, Constant.SUCCESS);
            if (success) {
                ConsoleColors.printSuccess("Thêm học viên vào khóa học thành công");
                ConsoleColors.delay(500);
            } else {
                ConsoleColors.printError("Thêm học viên vào khóa học thất bại!");
                ConsoleColors.delay(500);
            }
        } catch (NumberFormatException e) {
            ConsoleColors.printError("ID không hợp lệ!");
            ConsoleColors.delay(500);
        }
        ConsoleColors.clearScreen();
    }

    private void deleteEnrollment() {
        ConsoleColors.printHeader("DANH SÁCH KHÓA HỌC");
        List<Course> courses = this.courseService.getAllCourses();
        ConsoleColors.printCourseList(courses);
        System.out.println();

        ConsoleColors.printPrompt("Nhập ID khóa học để xem học viên đăng ký hoặc 'q' để quay lại: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int courseId = Integer.parseInt(input);
            List<Student> students = this.enrollmentService.getStudentsByCourseId(courseId);

            if (students == null || students.isEmpty()) {
                ConsoleColors.printError("Không có học viên nào trong khóa học này!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
                return;
            }

            ConsoleColors.printHeader("DANH SÁCH HỌC VIÊN KHÓA HỌC ID: " + courseId);
            ConsoleColors.printStudentList(students);
            System.out.println();

            ConsoleColors.printHeader("XÓA HỌC VIÊN KHỎI KHÓA HỌC");
            ConsoleColors.printPrompt("Nhập ID học viên: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            boolean isEnrolled = students.stream().anyMatch(s -> s.getId() == studentId);
            if (!isEnrolled) {
                ConsoleColors.printError("Học viên không có trong khóa học này!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
                return;
            }

            Student student = students.stream()
                    .filter(s -> s.getId() == studentId)
                    .findFirst()
                    .orElse(null);

            Course course = courses.stream()
                    .filter(c -> c.getId() == courseId)
                    .findFirst()
                    .orElse(null);

            System.out.println();
            ConsoleColors.printWarning("Thông tin đăng ký sẽ bị xóa:");
            if (student != null) {
                System.out.println("Học viên: " + student.getName() + " (ID: " + student.getId() + ")");
            }
            if (course != null) {
                System.out.println("Khóa học: " + course.getName() + " (ID: " + course.getId() + ")");
            }
            System.out.println();

            if (ConfirmDelelte.confirmAction("Bạn có chắc chắn muốn xóa học viên khỏi khóa học này?")) {
                Boolean isDeleted = this.enrollmentService.deleteEnrollment(studentId, courseId);
                if (isDeleted) {
                    ConsoleColors.printSuccess("Xóa học viên khỏi khóa học thành công!");
                    ConsoleColors.delay(500);
                    ConsoleColors.clearScreen();
                } else {
                    ConsoleColors.printError("Xóa học viên khỏi khóa học thất bại!");
                    ConsoleColors.delay(500);
                    ConsoleColors.clearScreen();
                }
            } else {
                ConsoleColors.printSuccess("Đã hủy bỏ thao tác xóa!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
            }

        } catch (NumberFormatException e) {
            ConsoleColors.printError("ID không hợp lệ!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
        }
    }

    private void approveEnrollment() {
        ConsoleColors.printHeader("DANH SÁCH KHÓA HỌC");
        List<Course> courses = this.courseService.getAllCourses();
        ConsoleColors.printCourseList(courses);
        System.out.println();

        ConsoleColors.printPrompt("Nhập ID khóa học để xem học viên đăng ký hoặc 'q' để quay lại: ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("q")) {
            return;
        }

        try {
            int courseId = Integer.parseInt(input);
            List<StudentRest> students = this.enrollmentService.getEnrollmentsByStatusAndStudentId(courseId);

            if (students == null || students.isEmpty()) {
                ConsoleColors.printError("Không có học viên nào trong khóa học này!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
                return;
            }

            List<StudentRest> registeredStudents = students.stream()
                    .filter(s -> s.getStatus().equals(Constant.REGISTERED))
                    .collect(Collectors.toList());

            ConsoleColors.printHeader("DANH SÁCH HỌC VIÊN KHÓA HỌC ID: " + courseId);
            ConsoleColors.printStudentRestList(registeredStudents);
            System.out.println();

            ConsoleColors.printHeader("DUYỆT ĐĂNG KÝ HỌC VIÊN");
            ConsoleColors.printStudentList(this.enrollmentService.getStudentsByCourseId(courseId));
            ConsoleColors.printPrompt("Nhập ID học viên: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            boolean isEnrolled = students.stream().anyMatch(s -> s.getId() == studentId);
            if (!isEnrolled) {
                ConsoleColors.printError("Học viên không có trong khóa học này!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
                return;
            }

            if (ConfirmDelelte.confirmAction("Bạn có chắc chắn muốn duyệt học viên vào khóa học này?")) {
                Boolean isSuccess = this.enrollmentService.enrollCourse(studentId, courseId,
                        Constant.SUCCESS);
                if (isSuccess) {
                    ConsoleColors.printSuccess("Duyệt học viên vào khóa học thành công!");
                    ConsoleColors.delay(500);
                    ConsoleColors.clearScreen();
                } else {
                    ConsoleColors.printError("Duyệt học viên vào khóa học thất bại!");
                    ConsoleColors.delay(500);
                    ConsoleColors.clearScreen();
                }
            } else {
                ConsoleColors.printSuccess("Đã hủy bỏ thao tác duyệt!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
            }

        } catch (NumberFormatException e) {
            ConsoleColors.printError("ID không hợp lệ!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
        }
    }
}
