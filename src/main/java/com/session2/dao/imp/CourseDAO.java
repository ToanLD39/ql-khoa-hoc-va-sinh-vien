package com.session2.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.session2.dao.ICourseDAO;
import com.session2.model.Course;

public class CourseDAO extends GenericDAO implements ICourseDAO {
    private static final String GET_ALL_COURSE_QUERY = "SELECT id, name, duration, instructor, create_at FROM course ORDER BY id ASC";
    private static final String GET_COURSE_BY_ID_QUERY = "SELECT id, name, duration, instructor, create_at FROM course WHERE id = ?";
    private static final String CREATE_COURSE_QUERY = "INSERT INTO course (name, duration, instructor) VALUES (?, ?, ?)";
    private static final String UPDATE_COURSE_QUERY = "UPDATE course SET name = ?, duration = ?, instructor = ? WHERE id = ?";
    private static final String SEARCH_COURSE_BY_NAME_QUERY = "SELECT id, name, duration, instructor, create_at FROM course WHERE name ILIKE ?";
    private static final String SORT_COURSES_BY_NAME_QUERY = "SELECT id, name, duration, instructor, create_at FROM course ORDER BY name ASC";
    private static final String SORT_COURSES_BY_ID_QUERY = "SELECT id, name, duration, instructor, create_at FROM course ORDER BY id ASC";
    private static final String SORT_COURSES_DESC_BY_NAME_QUERY = "SELECT id, name, duration, instructor, create_at FROM course ORDER BY name DESC";
    private static final String SORT_COURSES_DESC_BY_ID_QUERY = "SELECT id, name, duration, instructor, create_at FROM course ORDER BY id DESC";

    public List<Course> getAllCourse() {
        List<Course> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(GET_ALL_COURSE_QUERY);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getTimestamp("create_at"));
                result.add(course);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error fetching all courses: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return result;
    }

    public Course getCourseById(Integer id) {
        Course course = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(GET_COURSE_BY_ID_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getTimestamp("create_at"));
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error fetching course by ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return course;
    }

    public Boolean createCourse(Course course) {
        Connection connection = null;
        PreparedStatement ps = null;
        Boolean isCreated = false;

        try {
            connection = this.getConnection();
            int index = 1;
            ps = connection.prepareStatement(CREATE_COURSE_QUERY);
            ps.setString(index++, course.getName());
            ps.setInt(index++, course.getDuration());
            ps.setString(index++, course.getInstructor());
            int rowsAffected = ps.executeUpdate();
            isCreated = rowsAffected > 0 ? true : false;
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error creating course: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return isCreated;
    }

    public Boolean updateCourse(Integer id, Course course) {
        Connection connection = null;
        PreparedStatement ps = null;
        Boolean isUpdated = false;

        try {
            connection = this.getConnection();
            int index = 1;
            ps = connection.prepareStatement(UPDATE_COURSE_QUERY);
            ps.setString(index++, course.getName());
            ps.setInt(index++, course.getDuration());
            ps.setString(index++, course.getInstructor());
            ps.setInt(index++, id);
            int rowsAffected = ps.executeUpdate();
            isUpdated = rowsAffected > 0 ? true : false;
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return isUpdated;
    }

    public List<Course> searchCourseByName(String name) {
        List<Course> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(SEARCH_COURSE_BY_NAME_QUERY);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getTimestamp("create_at"));
                result.add(course);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error searching courses by name: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return result;
    }

    public List<Course> sortCourses(String sortBy, String order) {
        List<Course> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "";

        if (sortBy.equalsIgnoreCase("name") && order.equalsIgnoreCase("asc")) {
            query = SORT_COURSES_BY_NAME_QUERY;
        } else if (sortBy.equalsIgnoreCase("id") && order.equalsIgnoreCase("asc")) {
            query = SORT_COURSES_BY_ID_QUERY;
        } else if (sortBy.equalsIgnoreCase("name") && order.equalsIgnoreCase("desc")) {
            query = SORT_COURSES_DESC_BY_NAME_QUERY;
        } else if (sortBy.equalsIgnoreCase("id") && order.equalsIgnoreCase("desc")) {
            query = SORT_COURSES_DESC_BY_ID_QUERY;
        } else {
            throw new IllegalArgumentException("Invalid sort parameters");
        }

        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(rs.getTimestamp("create_at"));
                result.add(course);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("Error sorting courses: " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return result;
    }
}
