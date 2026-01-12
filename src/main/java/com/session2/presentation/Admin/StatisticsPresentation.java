package com.session2.presentation.Admin;

import java.util.List;
import java.util.Scanner;

import com.session2.dao.IStatisticsDAO;
import com.session2.dao.imp.StatisticsDAO;
import com.session2.model.Course;
import com.session2.presentation.Common.PressToBack;
import com.session2.utils.ConsoleColors;

import response.CourseStudentRest;

public class StatisticsPresentation {
    Scanner scanner;
    private IStatisticsDAO statisticsDAO = new StatisticsDAO();

    public StatisticsPresentation() {
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        int choice;
        do {
            System.out.println();
            ConsoleColors.printBox("QUẢN LÝ ĐĂNG KÝ KHÓA HỌC");
            System.out.println();
            ConsoleColors.printMenuItem("1", "Thống kê tổng số lượng khóa học và học viên");
            ConsoleColors.printMenuItem("2", "Thống kê học viên theo từng khóa học");
            ConsoleColors.printMenuItem("3", "Top 5 khóa học đông học viên nhất");
            ConsoleColors.printMenuItem("4", "Liệt kê khóa học có trên 10 học viên");
            ConsoleColors.printMenuItem("5", "Quay về menu chính");

            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        statisticsTotalCourseAndStudent();
                        break;
                    case 2:
                        statisticsStudentWithCourse();
                        break;
                    case 3:
                        top5CoursesHaveMoreStudents();
                        break;
                    case 4:
                        top10CoursesHaveMoreStudents();
                        break;
                    case 5:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
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

    private void statisticsTotalCourseAndStudent() {
        ConsoleColors.printBox("THỐNG KÊ TỔNG SỐ LƯỢNG KHÓA HỌC VÀ HỌC VIÊN");
        System.out.println();
        List<Integer> counts = statisticsDAO.countCourseAndStudent();

        if (counts != null && counts.size() >= 2) {
            int courseCount = counts.get(0);
            int studentCount = counts.get(1);

            ConsoleColors.printInfo("Tổng số khóa học: " + ConsoleColors.CYAN_BOLD + courseCount + ConsoleColors.RESET);
            ConsoleColors
                    .printInfo("Tổng số học viên: " + ConsoleColors.CYAN_BOLD + studentCount + ConsoleColors.RESET);
            System.out.println();
            ConsoleColors.printSuccess("Thống kê hoàn tất!");
        } else {
            ConsoleColors.printError("Không thể lấy dữ liệu thống kê!");
        }
        PressToBack.pressToBack();
    }

    private void statisticsStudentWithCourse() {
        ConsoleColors.printBox("THỐNG KÊ HỌC VIÊN THEO TỪNG KHÓA HỌC");
        System.out.println();
        List<CourseStudentRest> statistics = statisticsDAO
                .getCourseStudentStatistics();

        if (statistics != null && !statistics.isEmpty()) {
            System.out.printf("%-30s %-20s%n", "Tên Khóa Học", "Số Lượng Học Viên");
            ConsoleColors.printSeparator();
            for (CourseStudentRest stat : statistics) {
                System.out.printf("%-30s %-20d%n", stat.getCourseName(), stat.getStudentCount());
            }
            System.out.println();
            ConsoleColors.printSuccess("Thống kê hoàn tất!");
        } else {
            ConsoleColors.printError("Không thể lấy dữ liệu thống kê!");
        }
        PressToBack.pressToBack();
    }

    private void top5CoursesHaveMoreStudents() {
        ConsoleColors.printBox("TOP 5 KHÓA HỌC ĐÔNG HỌC VIÊN NHẤT");
        System.out.println();
        List<Course> statistics = statisticsDAO
                .getTop5CoursesHaveMoreStudent();

        ConsoleColors.printCourseList(statistics);
        PressToBack.pressToBack();
    }

    private void top10CoursesHaveMoreStudents() {
        ConsoleColors.printBox("KHÓA HỌC CÓ TRÊN 10 HỌC VIÊN");
        System.out.println();
        List<Course> statistics = statisticsDAO
                .getCourseHaveMore10Students();

        ConsoleColors.printCourseList(statistics);
        PressToBack.pressToBack();
    }

}
