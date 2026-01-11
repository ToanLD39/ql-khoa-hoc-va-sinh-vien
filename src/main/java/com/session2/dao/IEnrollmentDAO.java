package com.session2.dao;

import java.util.List;

import com.session2.model.Course;
import com.session2.model.Student;

import response.CourseRest;
import response.StudentRest;

public interface IEnrollmentDAO {
    List<Student> getStudentsByCourseId(Integer courseId);

    Boolean enrollCourse(Integer studentId, Integer courseId, String status);

    Boolean deleteEnrollment(Integer studentId, Integer courseId);

    List<StudentRest> getEnrollmentsByStatusAndStudentId(Integer courseId);

    List<CourseRest> getEnrollmentsByStudentId(Integer studentId);
}
