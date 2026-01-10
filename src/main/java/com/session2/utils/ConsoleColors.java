package com.session2.utils;

import com.session2.model.Course;
import com.session2.model.Student;
import com.session2.model.Enrollment;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Utility class để thêm màu sắc cho console output
 */
public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";

    // Regular Colors
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";

    // Background
    public static final String BLACK_BG = "\033[40m";
    public static final String RED_BG = "\033[41m";
    public static final String GREEN_BG = "\033[42m";
    public static final String YELLOW_BG = "\033[43m";
    public static final String BLUE_BG = "\033[44m";
    public static final String PURPLE_BG = "\033[45m";
    public static final String CYAN_BG = "\033[46m";
    public static final String WHITE_BG = "\033[47m";

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";
    public static final String RED_BRIGHT = "\033[0;91m";
    public static final String GREEN_BRIGHT = "\033[0;92m";
    public static final String YELLOW_BRIGHT = "\033[0;93m";
    public static final String BLUE_BRIGHT = "\033[0;94m";
    public static final String PURPLE_BRIGHT = "\033[0;95m";
    public static final String CYAN_BRIGHT = "\033[0;96m";
    public static final String WHITE_BRIGHT = "\033[0;97m";

    /**
     * In text với màu sắc
     */
    public static void print(String color, String text) {
        System.out.print(color + text + RESET);
    }

    /**
     * In text với màu sắc và xuống dòng
     */
    public static void println(String color, String text) {
        System.out.println(color + text + RESET);
    }

    /**
     * In header với background màu và chữ bold
     */
    public static void printHeader(String text) {
        System.out.println();
        System.out.println(CYAN_BOLD + "╔" + "═".repeat(text.length() + 2) + "╗" + RESET);
        System.out.println(CYAN_BOLD + "║ " + WHITE_BOLD + text + CYAN_BOLD + " ║" + RESET);
        System.out.println(CYAN_BOLD + "╚" + "═".repeat(text.length() + 2) + "╝" + RESET);
        System.out.println();
    }

    /**
     * In menu item
     */
    public static void printMenuItem(String number, String text) {
        System.out.println(YELLOW_BOLD + number + ". " + WHITE + text + RESET);
    }

    /**
     * In thông báo thành công
     */
    public static void printSuccess(String text) {
        System.out.println(GREEN_BOLD + "✓ " + text + RESET);
    }

    /**
     * In thông báo lỗi
     */
    public static void printError(String text) {
        System.out.println(RED_BOLD + "✗ " + text + RESET);
    }

    /**
     * In thông báo cảnh báo
     */
    public static void printWarning(String text) {
        System.out.println(YELLOW_BOLD + "⚠ " + text + RESET);
    }

    /**
     * In thông báo thông tin
     */
    public static void printInfo(String text) {
        System.out.println(BLUE_BOLD + "ℹ " + text + RESET);
    }

    /**
     * In prompt input
     */
    public static void printPrompt(String text) {
        System.out.print(CYAN + "➤ " + text + RESET);
    }

    /**
     * In separator/divider
     */
    public static void printSeparator() {
        System.out.println(CYAN + "═".repeat(50) + RESET);
    }

    /**
     * In box với text
     */
    public static void printBox(String text) {
        int length = text.length() + 4;
        System.out.println(PURPLE_BOLD + "┌" + "─".repeat(length - 2) + "┐" + RESET);
        System.out.println(PURPLE_BOLD + "│ " + WHITE_BRIGHT + text + PURPLE_BOLD + " │" + RESET);
        System.out.println(PURPLE_BOLD + "└" + "─".repeat(length - 2) + "┘" + RESET);
    }

    /**
     * Clear console screen
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Delay execution
     */
    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * In danh sách khóa học
     */
    public static void printCourseList(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            printWarning("Không có dữ liệu!");
            return;
        }

        TablePrinter table = new TablePrinter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        table.setHeaders("ID", "Tên khóa học", "Thời lượng (giờ)", "Giảng viên", "Ngày tạo")
                .setAlignments(
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.LEFT,
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.LEFT,
                        TablePrinter.Alignment.CENTER);

        for (Course course : courses) {
            table.addRow(
                    course.getId(),
                    course.getName(),
                    course.getDuration(),
                    course.getInstructor(),
                    course.getCreateAt() != null ? dateFormat.format(course.getCreateAt()) : "");
        }

        table.print();
        printInfo("Tổng số khóa học: " + courses.size());
    }

    /**
     * In chi tiết một khóa học
     */
    public static void printCourseDetails(Course course) {
        if (course == null) {
            printWarning("Không có dữ liệu!");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        System.out.println();
        System.out.println(CYAN_BOLD + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(
                CYAN_BOLD + "║" + WHITE_BOLD + "      THÔNG TIN CHI TIẾT KHÓA HỌC     " + CYAN_BOLD + "║" + RESET);
        System.out.println(CYAN_BOLD + "╚══════════════════════════════════════╝" + RESET);
        System.out.println();

        System.out.println(YELLOW_BOLD + "  ID:              " + WHITE + course.getId() + RESET);
        System.out.println(YELLOW_BOLD + "  Tên khóa học:    " + WHITE + course.getName() + RESET);
        System.out.println(YELLOW_BOLD + "  Thời lượng:      " + WHITE + course.getDuration() + " giờ" + RESET);
        System.out.println(YELLOW_BOLD + "  Giảng viên:      " + WHITE + course.getInstructor() + RESET);
        System.out.println(YELLOW_BOLD + "  Ngày tạo:        " + WHITE +
                (course.getCreateAt() != null ? dateFormat.format(course.getCreateAt()) : "N/A") + RESET);
        System.out.println();
        System.out.println(CYAN + "═".repeat(40) + RESET);
        System.out.println();
    }

    /**
     * In danh sách học viên
     */
    public static void printStudentList(List<Student> students) {
        if (students == null || students.isEmpty()) {
            printWarning("Không có dữ liệu!");
            return;
        }

        TablePrinter table = new TablePrinter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        table.setHeaders("ID", "Họ và tên", "Ngày sinh", "Giới tính", "Email", "Số điện thoại")
                .setAlignments(
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.LEFT,
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.LEFT,
                        TablePrinter.Alignment.CENTER);

        for (Student student : students) {
            table.addRow(
                    student.getId(),
                    student.getName(),
                    student.getDob() != null ? dateFormat.format(student.getDob()) : "",
                    student.getSex() != null ? (student.getSex() ? "Nam" : "Nữ") : "",
                    student.getEmail(),
                    student.getPhone());
        }

        table.print();
        printInfo("Tổng số học viên: " + students.size());
    }

    /**
     * In chi tiết một học viên
     */
    public static void printStudentDetails(Student student) {
        if (student == null) {
            printWarning("Không có dữ liệu!");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println();
        System.out.println(CYAN_BOLD + "╔══════════════════════════════════════╗" + RESET);
        System.out.println(
                CYAN_BOLD + "║" + WHITE_BOLD + "      THÔNG TIN CHI TIẾT HỌC VIÊN      " + CYAN_BOLD + "║" + RESET);
        System.out.println(CYAN_BOLD + "╚══════════════════════════════════════╝" + RESET);
        System.out.println();

        System.out.println(YELLOW_BOLD + "  ID:              " + WHITE + student.getId() + RESET);
        System.out.println(YELLOW_BOLD + "  Họ và tên:       " + WHITE + student.getName() + RESET);
        System.out.println(YELLOW_BOLD + "  Ngày sinh:       " + WHITE +
                (student.getDob() != null ? dateFormat.format(student.getDob()) : "N/A") + RESET);
        System.out.println(YELLOW_BOLD + "  Giới tính:       " + WHITE +
                (student.getSex() != null ? (student.getSex() ? "Nam" : "Nữ") : "N/A") + RESET);
        System.out.println(YELLOW_BOLD + "  Email:           " + WHITE + student.getEmail() + RESET);
        System.out.println(YELLOW_BOLD + "  Số điện thoại:   " + WHITE + student.getPhone() + RESET);
        System.out.println();
        System.out.println(CYAN + "═".repeat(40) + RESET);
        System.out.println();
    }

    /**
     * In danh sách đăng ký học
     */
    public static void printEnrollmentList(List<Enrollment> enrollments) {
        if (enrollments == null || enrollments.isEmpty()) {
            printWarning("Không có dữ liệu!");
            return;
        }

        TablePrinter table = new TablePrinter();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        table.setHeaders("ID", "ID Học viên", "ID Khóa học", "Ngày đăng ký", "Trạng thái")
                .setAlignments(
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.CENTER,
                        TablePrinter.Alignment.CENTER);

        for (Enrollment enrollment : enrollments) {
            table.addRow(
                    enrollment.getId(),
                    enrollment.getStudentId(),
                    enrollment.getCourseId(),
                    enrollment.getRegisterAt() != null ? dateFormat.format(enrollment.getRegisterAt()) : "",
                    enrollment.getStatus() != null ? enrollment.getStatus() : "");
        }

        table.print();
        printInfo("Tổng số đăng ký: " + enrollments.size());
    }

    /**
     * In bảng tùy chỉnh
     * Ví dụ: printTable(new String[]{"Cột 1", "Cột 2"},
     * new Object[][]{{"Giá trị 1", "Giá trị 2"}})
     */
    public static void printTable(String[] headers, Object[][] data) {
        if (headers == null || data == null) {
            printWarning("Không có dữ liệu!");
            return;
        }

        TablePrinter table = new TablePrinter();
        table.setHeaders(headers);

        for (Object[] row : data) {
            table.addRow(row);
        }

        table.print();
    }
}
