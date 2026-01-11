package com.session2.service;

import java.util.List;

import com.session2.model.Student;

public interface IStudentService {
    Boolean login(String email, String password);

    Boolean addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    Boolean updateStudent(int id, Student student);

    Boolean deleteStudent(int id);

    List<Student> sortStudent(String sortBy, String order);

    List<Student> getStudentsByName(String name);

    List<Student> getStudentsByEmail(String email);

    List<Student> getStudentsById(int courseId);

    Student getStudentByEmail(String email);
}
