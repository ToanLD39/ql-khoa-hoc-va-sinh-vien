package com.session2.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.session2.model.Student;

public class StudentDAO extends BaseDAO {
    private static final String GET_ALL_STUDENTS_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students";
    private static final String LOG_IN_QUERY = "SELECT 1 FROM students WHERE email = ? AND password = ?";
    private static final String GET_STUDENT_BY_ID_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students WHERE id = ?";
    private static final String GET_STUDENTS_BY_NAME_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students WHERE name ILIKE ?";
    private static final String GET_STUDENTS_BY_EMAIL_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students WHERE email ILIKE ?";
    private static final String CREATE_STUDENT_QUERY = "INSERT INTO students (name, dob, email, sex, phone, password, create_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_STUDENT_QUERY = "UPDATE students SET name = ?, dob = ?, email = ?, sex = ?, phone = ?, password = ? WHERE id = ?";
    private static final String SORT_STUDENTS_BY_ID_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students ORDER BY id ASC";
    private static final String SORT_STUDENTS_BY_NAME_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students ORDER BY name ASC";
    private static final String SORT_STUDENTS_DESC_BY_ID_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students ORDER BY id DESC";
    private static final String SORT_STUDENTS_DESC_BY_NAME_QUERY = "SELECT id, name, dob, email, sex, phone, password, create_at FROM students ORDER BY name DESC";
    private static final String UPDATE_PASSWORD_BY_ID_QUERY = "UPDATE students SET password = ? WHERE id = ?";

    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(GET_ALL_STUDENTS_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob"));
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                result.add(student);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error fetching all students: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return result;
    }

    public boolean logIn(String email, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(LOG_IN_QUERY);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection(connection);
        }
    }

    public Student getStudentById(Integer id) {
        Student student = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(GET_STUDENT_BY_ID_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob"));
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error fetching student by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return student;
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(GET_STUDENTS_BY_NAME_QUERY);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob"));
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                result.add(student);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error fetching students by name: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return result;
    }

    public List<Student> getStudentsByEmail(String email) {
        List<Student> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(GET_STUDENTS_BY_EMAIL_QUERY);
            ps.setString(1, "%" + email + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob"));
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                result.add(student);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error fetching students by email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return result;
    }

    public Boolean createStudent(Student student) {
        Connection connection = null;
        PreparedStatement ps = null;
        Boolean isCreated = false;

        try {
            connection = this.getConnection();
            int index = 1;
            ps = connection.prepareStatement(CREATE_STUDENT_QUERY);
            ps.setString(index++, student.getName());
            ps.setDate(index++, new java.sql.Date(student.getDob().getTime()));
            ps.setString(index++, student.getEmail());
            ps.setBoolean(index++, student.getSex());
            ps.setString(index++, student.getPhone());
            ps.setString(index++, student.getPassword());
            ps.setTimestamp(index++, new java.sql.Timestamp(System.currentTimeMillis()));
            int rowsAffected = ps.executeUpdate();
            isCreated = rowsAffected > 0 ? true : false;
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error creating student: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return isCreated;
    }

    public Boolean updateStudent(int id, Student student) {
        Connection connection = null;
        PreparedStatement ps = null;
        Boolean isUpdated = false;

        try {
            connection = this.getConnection();
            int index = 1;
            ps = connection.prepareStatement(UPDATE_STUDENT_QUERY);
            ps.setString(index++, student.getName());
            ps.setDate(index++, new java.sql.Date(student.getDob().getTime()));
            ps.setString(index++, student.getEmail());
            ps.setBoolean(index++, student.getSex());
            ps.setString(index++, student.getPhone());
            ps.setString(index++, student.getPassword());
            ps.setInt(index++, id);
            int rowsAffected = ps.executeUpdate();
            isUpdated = rowsAffected > 0 ? true : false;
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return isUpdated;
    }

    public List<Student> sortStudents(String sortBy, String order) {
        List<Student> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "";

        if (sortBy.equalsIgnoreCase("name") && order.equalsIgnoreCase("asc")) {
            query = SORT_STUDENTS_BY_NAME_QUERY;
        } else if (sortBy.equalsIgnoreCase("id") && order.equalsIgnoreCase("asc")) {
            query = SORT_STUDENTS_BY_ID_QUERY;
        } else if (sortBy.equalsIgnoreCase("name") && order.equalsIgnoreCase("desc")) {
            query = SORT_STUDENTS_DESC_BY_NAME_QUERY;
        } else if (sortBy.equalsIgnoreCase("id") && order.equalsIgnoreCase("desc")) {
            query = SORT_STUDENTS_DESC_BY_ID_QUERY;
        } else {
            throw new IllegalArgumentException("Invalid sort parameters");
        }

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob"));
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                result.add(student);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error sorting students: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return result;
    }

    public Boolean updatePasswordById(Integer id, String newPassword) {
        Connection connection = null;
        PreparedStatement ps = null;
        Boolean isUpdated = false;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(UPDATE_PASSWORD_BY_ID_QUERY);
            ps.setString(1, newPassword);
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();
            isUpdated = rowsAffected > 0 ? true : false;
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error updating password: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return isUpdated;
    }
}
