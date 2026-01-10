package com.session2.service.imp;

import com.session2.dao.AdminDAO;
import com.session2.service.IAdminService;

public class AdminService implements IAdminService {
    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
    }

    public Boolean login(String username, String password) {
        return adminDAO.login(username, password);
    }
}
