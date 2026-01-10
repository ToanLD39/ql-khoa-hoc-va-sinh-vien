package com.session2.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO extends BaseDAO {
    private static final String LOG_IN_QUERY = "SELECT 1 FROM admin WHERE username = ? AND password = ?";
    private static final String CHECK_FIRST_LOGIN_QUERY = "SELECT is_change_password FROM admin WHERE username = ? AND password = ?";
    private static final String CHANGE_PASSWORD_QUERY = "UPDATE admin SET password = ?, is_change_password = true WHERE username = 'admin'";

    public boolean login(String username, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(LOG_IN_QUERY);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection(connection);
        }
    }

    public Boolean isFirstLogin(String username, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(CHECK_FIRST_LOGIN_QUERY);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return true;
            }
            return rs.getBoolean("is_change_password");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection(connection);
        }
    }

    public boolean changePassword(String newPassword) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = this.getConnection();
            ps = connection.prepareStatement(CHANGE_PASSWORD_QUERY);
            ps.setString(1, newPassword);
            int rowsAffected = ps.executeUpdate();
            connection.commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection(connection);
        }
    }
}
