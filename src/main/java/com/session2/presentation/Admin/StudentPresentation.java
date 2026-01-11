package com.session2.presentation.Admin;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.session2.dao.IStudentDAO;
import com.session2.dao.imp.StudentDAO;
import com.session2.model.Student;
import com.session2.presentation.Common.ConfirmDelelte;
import com.session2.presentation.Common.PressToBack;
import com.session2.service.IStudentService;
import com.session2.service.imp.StudentService;
import com.session2.utils.ConsoleColors;

public class StudentPresentation {
    private Scanner scanner;
    private IStudentService studentService;
    private PressToBack pressToBack;

    public StudentPresentation() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
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
                        ConsoleColors.clearScreen();
                        displayStudents();
                        break;
                    case 2:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        addStudent();
                        break;
                    case 3:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        updateStudent();
                        break;
                    case 4:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        deleteStudent();
                        break;
                    case 5:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        searchStudent();
                        break;
                    case 6:
                        ConsoleColors.delay(500);
                        ConsoleColors.clearScreen();
                        sortStudent();
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

    private void displayStudents() {
        ConsoleColors.printHeader("DANH SÁCH HỌC VIÊN");
        List<Student> students = this.studentService.getAllStudents();
        ConsoleColors.printStudentList(students);
        pressToBack.pressToBack();
    }

    private void addStudent() {
        ConsoleColors.printHeader("THÊM MỚI HỌC VIÊN");
        ConsoleColors.printPrompt("Name: ");
        String name = scanner.nextLine();
        ConsoleColors.printPrompt("DoB(yyyy-mm-dd): ");
        Date dob = Date.valueOf(scanner.nextLine());
        ConsoleColors.printPrompt("Email: ");
        String email = scanner.nextLine();
        Boolean sexValue;
        do {
            ConsoleColors.printPrompt("Sex(Male[M]/Female[F]): ");
            String sex = scanner.nextLine();
            if (sex.equalsIgnoreCase("M")) {
                sexValue = true;
                break;
            }
            if (sex.equalsIgnoreCase("F")) {
                sexValue = false;
                break;
            }
            ConsoleColors.printError("Lựa chọn không hợp lệ!");
        } while (true);
        ConsoleColors.printPrompt("Phone: ");
        String phone = scanner.nextLine();
        ConsoleColors.printPrompt("Password: ");
        String password = scanner.nextLine();
        Student student = new Student();
        student.setName(name);
        student.setDob(dob);
        student.setEmail(email);
        student.setSex(sexValue);
        student.setPhone(phone);
        student.setPassword(password);
        Boolean isAdded = this.studentService.addStudent(student);
        if (isAdded) {
            ConsoleColors.printSuccess("Thêm học viên thành công!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
            return;
        }
        ConsoleColors.printError("Thêm học viên thất bại!");
        ConsoleColors.delay(500);
        ConsoleColors.clearScreen();
    }

    private void updateStudent() {
        ConsoleColors.printHeader("CHỈNH SỬA THÔNG TIN HỌC VIÊN");
        ConsoleColors.printPrompt("Nhập ID học viên cần chỉnh sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Student existingStudent = this.studentService.getStudentById(id);
        if (existingStudent == null) {
            ConsoleColors.printError("Học viên không tồn tại!");
            ConsoleColors.delay(500);
            ConsoleColors.clearScreen();
            return;
        }

        int choice;
        boolean hasChanges = false;
        do {
            ConsoleColors.clearScreen();
            ConsoleColors.printHeader("CHỈNH SỬA THÔNG TIN HỌC VIÊN - ID: " + id);
            System.out.println();
            ConsoleColors.printMenuItem("1", "Sửa tên (" + existingStudent.getName() + ")");
            ConsoleColors.printMenuItem("2", "Sửa ngày sinh (" + existingStudent.getDob() + ")");
            ConsoleColors.printMenuItem("3", "Sửa email (" + existingStudent.getEmail() + ")");
            ConsoleColors.printMenuItem("4", "Sửa giới tính (" + (existingStudent.getSex() ? "Male" : "Female") + ")");
            ConsoleColors.printMenuItem("5", "Sửa số điện thoại (" + existingStudent.getPhone() + ")");
            ConsoleColors.printMenuItem("6", "Sửa mật khẩu");
            ConsoleColors.printMenuItem("7", "Lưu thay đổi và quay lại");
            ConsoleColors.printMenuItem("8", "Hủy bỏ và quay lại");
            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        ConsoleColors.printPrompt("Nhập tên mới: ");
                        String name = scanner.nextLine();
                        if (!name.isEmpty()) {
                            existingStudent.setName(name);
                            hasChanges = true;
                            ConsoleColors.printSuccess("Đã cập nhật tên!");
                            ConsoleColors.delay(300);
                        }
                        break;
                    case 2:
                        ConsoleColors.printPrompt("Nhập ngày sinh mới (yyyy-mm-dd): ");
                        String dobInput = scanner.nextLine();
                        if (!dobInput.isEmpty()) {
                            try {
                                Date dob = Date.valueOf(dobInput);
                                existingStudent.setDob(dob);
                                hasChanges = true;
                                ConsoleColors.printSuccess("Đã cập nhật ngày sinh!");
                                ConsoleColors.delay(300);
                            } catch (IllegalArgumentException e) {
                                ConsoleColors.printError("Định dạng ngày không hợp lệ!");
                                ConsoleColors.delay(500);
                            }
                        }
                        break;
                    case 3:
                        ConsoleColors.printPrompt("Nhập email mới: ");
                        String email = scanner.nextLine();
                        if (!email.isEmpty()) {
                            existingStudent.setEmail(email);
                            hasChanges = true;
                            ConsoleColors.printSuccess("Đã cập nhật email!");
                            ConsoleColors.delay(300);
                        }
                        break;
                    case 4:
                        Boolean sexValue;
                        do {
                            ConsoleColors.printPrompt("Nhập giới tính (M/F): ");
                            String sex = scanner.nextLine();
                            if (sex.isEmpty()) {
                                break;
                            }
                            if (sex.equalsIgnoreCase("M")) {
                                sexValue = true;
                                existingStudent.setSex(sexValue);
                                hasChanges = true;
                                ConsoleColors.printSuccess("Đã cập nhật giới tính!");
                                ConsoleColors.delay(300);
                                break;
                            }
                            if (sex.equalsIgnoreCase("F")) {
                                sexValue = false;
                                existingStudent.setSex(sexValue);
                                hasChanges = true;
                                ConsoleColors.printSuccess("Đã cập nhật giới tính!");
                                ConsoleColors.delay(300);
                                break;
                            }
                            ConsoleColors.printError("Lựa chọn không hợp lệ!");
                        } while (true);
                        break;
                    case 5:
                        ConsoleColors.printPrompt("Nhập số điện thoại mới: ");
                        String phone = scanner.nextLine();
                        if (!phone.isEmpty()) {
                            existingStudent.setPhone(phone);
                            hasChanges = true;
                            ConsoleColors.printSuccess("Đã cập nhật số điện thoại!");
                            ConsoleColors.delay(300);
                        }
                        break;
                    case 6:
                        ConsoleColors.printPrompt("Nhập mật khẩu mới: ");
                        String password = scanner.nextLine();
                        if (!password.isEmpty()) {
                            existingStudent.setPassword(password);
                            hasChanges = true;
                            ConsoleColors.printSuccess("Đã cập nhật mật khẩu!");
                            ConsoleColors.delay(300);
                        }
                        break;
                    case 7:
                        if (hasChanges) {
                            Boolean isUpdated = this.studentService.updateStudent(id, existingStudent);
                            if (isUpdated) {
                                ConsoleColors.printSuccess("Cập nhật thông tin học viên thành công!");
                                ConsoleColors.delay(500);
                                ConsoleColors.clearScreen();
                                return;
                            } else {
                                ConsoleColors.printError("Cập nhật thông tin học viên thất bại!");
                                ConsoleColors.delay(500);
                            }
                        } else {
                            ConsoleColors.printError("Không có thay đổi nào để lưu!");
                            ConsoleColors.delay(500);
                        }
                        ConsoleColors.clearScreen();
                        return;
                    case 8:
                        ConsoleColors.clearScreen();
                        return;
                    default:
                        ConsoleColors.printError("Lựa chọn không hợp lệ!");
                        ConsoleColors.delay(500);
                }
            } catch (NumberFormatException e) {
                ConsoleColors.printError("Vui lòng nhập số!");
                ConsoleColors.delay(500);
                choice = -1;
            }
        } while (choice != 7 && choice != 8);
    }

    private void deleteStudent() {
        ConsoleColors.printHeader("XÓA HỌC VIÊN");
        ConsoleColors.printPrompt("Nhập ID học viên cần xóa: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = this.studentService.getStudentById(id);

            if (student == null) {
                ConsoleColors.printError("Học viên không tồn tại!");
                ConsoleColors.delay(500);
                ConsoleColors.clearScreen();
                return;
            }

            // Hiển thị thông tin học viên
            System.out.println();
            ConsoleColors.printWarning("Thông tin học viên sẽ bị xóa:");
            System.out.println("ID: " + student.getId());
            System.out.println("Tên: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Ngày sinh: " + student.getDob());
            System.out.println();

            // Xác nhận xóa
            if (ConfirmDelelte.confirmAction("Bạn có chắc chắn muốn xóa học viên này?")) {
                Boolean isDeleted = this.studentService.deleteStudent(id);
                if (isDeleted) {
                    ConsoleColors.printSuccess("Xóa học viên thành công!");
                    ConsoleColors.delay(500);
                    ConsoleColors.clearScreen();
                } else {
                    ConsoleColors.printError("Xóa học viên thất bại!");
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

    private void searchStudent() {
        int choice;
        do {
            ConsoleColors.printHeader("TÌM KIẾM HỌC VIÊN");
            ConsoleColors.printMenuItem("1", "Tìm kiếm theo tên");
            ConsoleColors.printMenuItem("2", "Tìm kiếm theo email");
            ConsoleColors.printMenuItem("3", "Tìm kiếm theo ID");
            ConsoleColors.printMenuItem("4", "Quay lại");
            System.out.println();
            ConsoleColors.printPrompt("Nhập lựa chọn: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ConsoleColors.printPrompt("Nhập tên học viên cần tìm: ");
                    String name = scanner.nextLine();
                    List<Student> studentsByName = this.studentService.getStudentsByName(name);
                    ConsoleColors.printStudentList(studentsByName);
                    break;
                case 2:
                    ConsoleColors.printPrompt("Nhập email học viên cần tìm: ");
                    String email = scanner.nextLine();
                    List<Student> studentsByEmail = this.studentService.getStudentsByEmail(email);
                    ConsoleColors.printStudentList(studentsByEmail);
                    break;
                case 3:
                    ConsoleColors.printPrompt("Nhập ID học viên cần tìm: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    List<Student> studentsById = this.studentService.getStudentsById(id);
                    if (studentsById != null && !studentsById.isEmpty()) {
                        ConsoleColors.printStudentList(studentsById);
                    } else {
                        ConsoleColors.printError("Học viên không tồn tại!");
                    }
                    break;
                case 4:
                    break;
                default:
                    ConsoleColors.printError("Lựa chọn không hợp lệ!");
            }
        } while (choice != 4);

        pressToBack.pressToBack();
    }

    private void sortStudent() {
        ConsoleColors.printHeader("SẮP XẾP HỌC VIÊN");
        ConsoleColors.printMenuItem("1", "Sắp xếp theo ID (Tăng dần)");
        ConsoleColors.printMenuItem("2", "Sắp xếp theo ID (Giảm dần)");
        ConsoleColors.printMenuItem("3", "Sắp xếp theo Tên (A-Z)");
        ConsoleColors.printMenuItem("4", "Sắp xếp theo Tên (Z-A)");
        ConsoleColors.printPrompt("Chọn phương thức sắp xếp: ");

        try {
            int sortChoice = Integer.parseInt(scanner.nextLine());
            List<Student> allStudents = this.studentService.getAllStudents();

            switch (sortChoice) {
                case 1:
                    allStudents = this.studentService.sortStudent("id", "asc");
                    ConsoleColors.printSuccess("Danh sách học viên đã được sắp xếp theo ID:");
                    ConsoleColors.printStudentList(allStudents);
                    break;
                case 2:
                    allStudents = this.studentService.sortStudent("id", "desc");
                    ConsoleColors.printSuccess("Danh sách học viên đã được sắp xếp theo ID:");
                    ConsoleColors.printStudentList(allStudents);
                    break;
                case 3:
                    allStudents = this.studentService.sortStudent("name", "asc");
                    ConsoleColors.printSuccess("Danh sách học viên đã được sắp xếp theo Tên:");
                    ConsoleColors.printStudentList(allStudents);
                    break;
                case 4:
                    allStudents = this.studentService.sortStudent("name", "desc");
                    ConsoleColors.printSuccess("Danh sách học viên đã được sắp xếp theo Tên:");
                    ConsoleColors.printStudentList(allStudents);
                    break;
                default:
                    ConsoleColors.printError("Lựa chọn không hợp lệ!");
            }
        } catch (NumberFormatException e) {
            ConsoleColors.printError("Vui lòng nhập số!");
        }
        pressToBack.pressToBack();
    }
}
