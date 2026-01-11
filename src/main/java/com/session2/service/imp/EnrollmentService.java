package com.session2.service.imp;

import java.util.List;

import com.session2.dao.IEnrollmentDAO;
import com.session2.dao.imp.EnrollmentDAO;
import com.session2.model.Student;
import com.session2.service.IEnrollmentService;

import response.CourseRest;
import response.StudentRest;

public class EnrollmentService implements IEnrollmentService {

    private IEnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public List<Student> getStudentsByCourseId(Integer courseId) {
        return this.enrollmentDAO.getStudentsByCourseId(courseId);
    }

    public Boolean enrollCourse(Integer studentId, Integer courseId, String status) {
        return this.enrollmentDAO.enrollCourse(studentId, courseId, status);
    }

    public Boolean deleteEnrollment(Integer studentId, Integer courseId) {
        return this.enrollmentDAO.deleteEnrollment(studentId, courseId);
    }

    public List<StudentRest> getEnrollmentsByStatusAndStudentId(Integer courseId) {
        return this.enrollmentDAO.getEnrollmentsByStatusAndStudentId(courseId);
    }

    public List<CourseRest> getEnrollmentsByStudentId(Integer studentId) {
        return this.enrollmentDAO.getEnrollmentsByStudentId(studentId);
    }

}
