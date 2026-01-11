package com.session2.service;

import java.util.List;

import com.session2.model.Student;

import response.StudentRest;

public interface IEnrollmentService {
    List<Student> getStudentsByCourseId(Integer courseId);

    Boolean enrollCourse(Integer studentId, Integer courseId, String status);

    Boolean deleteEnrollment(Integer studentId, Integer courseId);

    List<StudentRest> getEnrollmentsByStatusAndStudentId(Integer courseId);
}
