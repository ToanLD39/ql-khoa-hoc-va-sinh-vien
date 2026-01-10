package com.session2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.session2.model.Course;

public class EnrollmentDAO extends BaseDAO {
    private static final String GET_ENROLLMENTS_BY_STUDENT_ID_QUERY = 
        "SELECT e.id, e.student_id, e.course_id, e.enroll_date, c.name AS course_name "
        + "FROM course c "
        + "JOIN enrollment e ON e.course_id = c.id "
        + "WHERE e.student_id = ?";
    
    private static final String ENROLL_COURSE_QUERY = "INSERT INTO enrollment (student_id, course_id, registered_at, status) VALUES (?, ?, NOW(), ?)";
    
    private static final String UPDATE_ENROLLMENT_STATUS_QUERY = "UPDATE enrollment SET status = ? WHERE id = ?";

    private static final String GET_ENROLLMENT_BY_STATUS_QUERY = "SELECT e.id, e.student_id, e.course_id, e.enroll_date, c.name AS course_name "
        + "FROM course c "
        + "JOIN enrollment e ON e.course_id = c.id "
        + "WHERE e.status = ? AND e.student_id = ? ";

    private static final String DELETE_ENROLLMENT_BY_STUDENT_ID_QUERY = "DELETE FROM enrollment WHERE student_id = ?";

    public List<Course> getEnrollmentsByStudentId(Integer studentId) {
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(GET_ENROLLMENTS_BY_STUDENT_ID_QUERY);
            ps.setInt(1, studentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setName(rs.getString("course_name"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return courses;
    }
    
    public boolean enrollCourse(Integer studentId, Integer courseId, String status) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(ENROLL_COURSE_QUERY);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setString(3, status);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection(connection);
        }
    }

    public boolean updateEnrollmentStatus(Integer enrollmentId, String status) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(UPDATE_ENROLLMENT_STATUS_QUERY);
            ps.setString(1, status);
            ps.setInt(2, enrollmentId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection(connection);
        }
    }
}
