package com.session2.service.imp;

import com.session2.dao.imp.StudentDAO;
import com.session2.service.IStudentService;

public class StudentService implements IStudentService {
    private StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    public Boolean login(String email, String password) {
        return studentDAO.logIn(email, password);
    }
}
