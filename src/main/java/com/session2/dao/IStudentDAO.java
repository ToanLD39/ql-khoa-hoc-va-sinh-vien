package com.session2.dao;

import java.util.List;

import com.session2.model.Student;

public interface IStudentDAO extends IGenericDAO {
    List<Student> getAllStudents();

    boolean logIn(String email, String password);

    Student getStudentById(Integer id);

    List<Student> getStudentsByName(String name);

    List<Student> getStudentsByEmail(String email);

    Boolean createStudent(Student student);

    Boolean updateStudent(int id, Student student);

    List<Student> sortStudents(String sortBy, String order);

    Boolean updatePasswordById(Integer id, String newPassword);

    Student getStudentByEmail(String email);

    Boolean loginById(int studentId, String newPassword);

    Boolean changePassword(int studentId, String newPassword);

}
