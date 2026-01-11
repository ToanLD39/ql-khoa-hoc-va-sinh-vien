package com.session2.service.imp;

import java.util.List;

import com.session2.common.Constant;
import com.session2.dao.imp.StudentDAO;
import com.session2.model.Student;
import com.session2.service.IStudentService;

public class StudentService implements IStudentService {
    private StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public Boolean login(String email, String password) {
        return studentDAO.logIn(email, password);
    }

    public Boolean addStudent(Student student) {
        return studentDAO.createStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public Boolean updateStudent(int id, Student student) {
        return studentDAO.updateStudent(id, student);
    }

    public Boolean deleteStudent(int id) {
        return studentDAO.deleteEntity(id, Constant.ENTITY_STUDENT);
    }

    public List<Student> sortStudent(String sortBy, String order) {
        return studentDAO.sortStudents(sortBy, order);
    }

    public List<Student> getStudentsByName(String name) {
        return studentDAO.getStudentsByName(name);
    }

    public List<Student> getStudentsByEmail(String email) {
        return studentDAO.getStudentsByEmail(email);
    }

    public List<Student> getStudentsById(int courseId) {
        return studentDAO.getStudentsById(courseId);
    }

    public Student getStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }
}
