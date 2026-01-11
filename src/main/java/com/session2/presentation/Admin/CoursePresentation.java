package com.session2.presentation.Admin;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.session2.model.Course;
import com.session2.presentation.Common.PressToBack;
import com.session2.service.ICourseService;
import com.session2.service.imp.CourseService;
import com.session2.utils.ConsoleColors;

public class CoursePresentation {
    private Scanner scanner;
    private ICourseService courseService;

    private PressToBack pressToBack;

    public CoursePresentation() {
        this.scanner = new Scanner(System.in);
        this.courseService = new CourseService();
    }

    public void display() {
        int choice;
        do {
            ConsoleColors.printHeader("COURSE MANAGEMENT");
            ConsoleColors.printMenuItem("1", "Hiển thị danh sách khóa học");
            ConsoleColors.printMenuItem("2", "Thêm mới khóa học");
            ConsoleColors.printMenuItem("3", "Chỉnh sửa thông tin khóa học");
            ConsoleColors.printMenuItem("4", "Xóa khóa học");
            ConsoleColors.printMenuItem("5", "Tìm kiếm theo tên");
            ConsoleColors.printMenuItem("6", "Sắp xếp theo tên hoặc id");
            ConsoleColors.printMenuItem("7", "Quay về menu chính");
            System.out.println();
            ConsoleColors.printSeparator();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        ConsoleColors.clearScreen();
                        displayCourses();
                        break;
                    case 2:
                        ConsoleColors.clearScreen();
                        addCourse();
                        break;
                    case 3:
                        ConsoleColors.clearScreen();
                        editCourse();
                        break;
                    case 4:
                        ConsoleColors.clearScreen();
                        deleteCourse();
                        break;
                    case 5:
                        ConsoleColors.clearScreen();
                        searchCourseByName();
                        break;
                    case 6:
                        ConsoleColors.clearScreen();
                        sortCourses();
                        break;
                    case 7:
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

    public void displayCourses() {
        ConsoleColors.printHeader("DANH SÁCH KHÓA HỌC");

        List<Course> courses = this.courseService.getAllCourses();
        ConsoleColors.printCourseList(courses);
        do {
            ConsoleColors.printPrompt("Nhấn 'B' để quay lại: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("B")) {
                ConsoleColors.clearScreen();
                break;
            } else {
                ConsoleColors.printError("Lựa chọn không hợp lệ!");
            }
        } while (true);
    }

    private void addCourse() {
        ConsoleColors.printHeader("THÊM MỚI KHÓA HỌC");

        ConsoleColors.printPrompt("Name: ");
        String name = scanner.nextLine();

        ConsoleColors.printPrompt("Duration: ");
        int duration = Integer.parseInt(scanner.nextLine());

        ConsoleColors.printPrompt("Instructor: ");
        String instructor = scanner.nextLine();

        Course course = new Course();
        course.setName(name);
        course.setDuration(duration);
        course.setInstructor(instructor);

        Boolean isCreated = this.courseService.createCourse(course);
        if (isCreated) {
            ConsoleColors.printSuccess("Thêm khóa học thành công!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
            return;
        }
        ConsoleColors.printError("Thêm khóa học thất bại!");
        ConsoleColors.delay(500);
        ConsoleColors.clearScreen();
    }

    private void editCourse() {
        List<Course> courses = this.courseService.getAllCourses();
        ConsoleColors.printCourseList(courses);

        ConsoleColors.printPrompt("Nhập ID khóa học cần chỉnh sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Course course = this.courseService.getCourseById(id);
        if (course == null) {
            ConsoleColors.printError("Khóa học với ID " + id + " không tồn tại!");
            return;
        }

        ConsoleColors.printHeader("CHỈNH SỬA THÔNG TIN KHÓA HỌC");

        ConsoleColors.printCourseDetails(course);

        editCourseDetails(id, course);
    }

    private void editCourseDetails(Integer id, Course course) {
        int choice;
        boolean isEdited = false;

        do {
            ConsoleColors.printMenuItem("1", "Sửa tên khóa học");
            ConsoleColors.printMenuItem("2", "Sửa thời luợng khóa học");
            ConsoleColors.printMenuItem("3", "Sửa giảng viên khóa học");
            ConsoleColors.printMenuItem("4", "Sửa tất cả thông tin khóa học");
            ConsoleColors.printMenuItem("5", "Hủy bỏ và quay lại");
            ConsoleColors.printSeparator();
            ConsoleColors.printPrompt("Chọn thông tin cần sửa: ");

            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ConsoleColors.printPrompt("Name (" + course.getName() + "): ");
                    String name = scanner.nextLine();
                    if (!name.isEmpty()) {
                        course.setName(name);
                    }
                    checkUpdateSuccess(this.courseService.updateCourse(id, course));
                    break;
                case 2:
                    ConsoleColors.printPrompt("Duration (" + course.getDuration() + "): ");
                    String durationStr = scanner.nextLine();
                    if (!durationStr.isEmpty()) {
                        int duration = Integer.parseInt(durationStr);
                        course.setDuration(duration);
                    }
                    checkUpdateSuccess(this.courseService.updateCourse(id, course));
                    break;
                case 3:
                    ConsoleColors.printPrompt("Instructor (" + course.getInstructor() + "): ");
                    String instructor = scanner.nextLine();
                    if (!instructor.isEmpty()) {
                        course.setInstructor(instructor);
                    }
                    checkUpdateSuccess(this.courseService.updateCourse(id, course));
                    break;
                case 4:
                    ConsoleColors.printPrompt("Name (" + course.getName() + "): ");
                    name = scanner.nextLine();
                    if (!name.isEmpty()) {
                        course.setName(name);
                    }

                    ConsoleColors.printPrompt("Duration (" + course.getDuration() + "): ");
                    durationStr = scanner.nextLine();
                    if (!durationStr.isEmpty()) {
                        int duration = Integer.parseInt(durationStr);
                        course.setDuration(duration);
                    }

                    ConsoleColors.printPrompt("Instructor (" + course.getInstructor() + "): ");
                    instructor = scanner.nextLine();
                    if (!instructor.isEmpty()) {
                        course.setInstructor(instructor);
                    }

                    checkUpdateSuccess(this.courseService.updateCourse(id, course));
                    break;
                case 5:
                    ConsoleColors.clearScreen();
                    break;
                default:
                    ConsoleColors.printError("Lựa chọn không hợp lệ!");
                    continue;
            }
        } while (choice != 5);
    }

    private Boolean checkUpdateSuccess(Boolean isEdited) {
        if (isEdited) {
            ConsoleColors.printSuccess("Cập nhật khóa học thành công!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
            return true;
        }
        ConsoleColors.printError("Cập nhật khóa học thất bại!");
        ConsoleColors.clearScreen();
        ConsoleColors.delay(500);
        return false;
    }

    private void deleteCourse() {
        List<Course> courses = this.courseService.getAllCourses();
        ConsoleColors.printCourseList(courses);

        ConsoleColors.printPrompt("Nhập ID khóa học cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean isDeleted = this.courseService.deleteCourse(id);
        if (isDeleted) {
            ConsoleColors.printSuccess("Xóa khóa học thành công!");
            return;
        }
        ConsoleColors.printError("Không tồn tại khóa học!");
        ConsoleColors.delay(500);
        ConsoleColors.clearScreen();
    }

    private void searchCourseByName() {
        ConsoleColors.printHeader("TÌM KIẾM KHÓA HỌC THEO TÊN");

        ConsoleColors.printPrompt("Nhập tên khóa học cần tìm: ");
        String name = scanner.nextLine();

        List<Course> courses = this.courseService.searchCourseByName(name);

        ConsoleColors.printCourseList(courses);

        pressToBack.pressToBack();
    }

    private void sortCourses() {
        ConsoleColors.printHeader("SẮP XẾP KHÓA HỌC");

        ConsoleColors.printMenuItem("1", "Sắp xếp theo tên (A-Z)");
        ConsoleColors.printMenuItem("2", "Sắp xếp theo tên (Z-A)");
        ConsoleColors.printMenuItem("3", "Sắp xếp theo ID (Tăng dần)");
        ConsoleColors.printMenuItem("4", "Sắp xếp theo ID (Giảm dần)");
        ConsoleColors.printSeparator();
        ConsoleColors.printPrompt("Chọn phương thức sắp xếp: ");

        int choice = Integer.parseInt(scanner.nextLine());
        List<Course> courses = new ArrayList();
        switch (choice) {
            case 1:
                courses = this.courseService.getCoursesSort("name", "ASC");
                break;
            case 2:
                courses = this.courseService.getCoursesSort("name", "DESC");
                break;
            case 3:
                courses = this.courseService.getCoursesSort("id", "ASC");
                break;
            case 4:
                courses = this.courseService.getCoursesSort("id", "DESC");
                break;
            default:
                ConsoleColors.printError("Lựa chọn không hợp lệ!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
                return;
        }

        ConsoleColors.printCourseList(courses);

        do {
            ConsoleColors.printPrompt("Nhấn 'B' để quay lại: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("B")) {
                ConsoleColors.clearScreen();
                break;
            }
            ConsoleColors.printError("Lựa chọn không hợp lệ!");
        } while (true);
    }
}
