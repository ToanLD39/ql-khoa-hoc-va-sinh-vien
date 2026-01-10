package com.session2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO extends BaseDAO {
    private static final String LOG_IN_QUERY = "SELECT 1 FROM admin WHERE username = ? AND password = ?";

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
}
