package com.session2.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.session2.dao.IStatisticsDAO;
import com.session2.model.Course;

import response.CourseStudentRest;

public class StatisticsDAO extends BaseDAO implements IStatisticsDAO {
    private static final String STATISTICS_COUNT_COURSE_AND_STUDENT = "SELECT COUNT(DISTINCT c.id) AS course_count, COUNT(s.id) AS student_count FROM courses c LEFT JOIN student_courses sc ON c.id = sc.course_id LEFT JOIN students s ON sc.student_id = s.id;";
    private static final String STATISTICS_COURSE_STUDENT = "SELECT c.name AS course_name, COUNT(s.id) AS student_count FROM courses c LEFT JOIN student_courses sc ON c.id = sc.course_id LEFT JOIN students s ON sc.student_id = s.id GROUP BY c.id, c.name;";
    private static final String TOP_5_COURSES_HAVE_MORE_STUDENTS = "SELECT c.* FROM courses c LEFT JOIN student_courses sc ON c.id = sc.course_id GROUP BY c.id ORDER BY COUNT(sc.student_id) DESC LIMIT 5;";
    private static final String COURSES_HAVE_MORE_10_STUDENTS = "SELECT c.* FROM courses c LEFT JOIN student_courses sc ON c.id = sc.course_id GROUP BY c.id HAVING COUNT(sc.student_id) > 10;";

    public List<Integer> countCourseAndStudent() {
        List<Integer> counts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(STATISTICS_COUNT_COURSE_AND_STUDENT);
            rs = ps.executeQuery();
            if (rs.next()) {
                counts.add(rs.getInt("course_count"));
                counts.add(rs.getInt("student_count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return counts;
    }

    public List<CourseStudentRest> getCourseStudentStatistics() {
        List<CourseStudentRest> statistics = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(STATISTICS_COURSE_STUDENT);
            rs = ps.executeQuery();
            while (rs.next()) {
                CourseStudentRest statistic = new CourseStudentRest();
                statistic.setCourseName(rs.getString("course_name"));
                statistic.setStudentCount(rs.getInt("student_count"));
                statistics.add(statistic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return statistics;
    }

    public List<Course> getTop5CoursesHaveMoreStudent() {
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(TOP_5_COURSES_HAVE_MORE_STUDENTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return courses;
    }

    public List<Course> getCourseHaveMore10Students() {
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(COURSES_HAVE_MORE_10_STUDENTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return courses;
    }

}
