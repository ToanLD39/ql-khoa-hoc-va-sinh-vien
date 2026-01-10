package com.session2.service;

public interface IAdminService {
    Boolean login(String username, String password);
    Boolean isFirstLogin(String username, String password);
    Boolean changePassword(String newPassword);
}
