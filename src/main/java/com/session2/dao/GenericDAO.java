package com.session2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.session2.common.Constant;

public class GenericDAO extends BaseDAO {
    private static final String DELETE_COURSE_QUERY = "DELETE FROM course WHERE id = ?";
    private static final String DELETE_STUDENT_QUERY = "DELETE FROM student WHERE id = ?";

    public Boolean deleteEntity(int id, String entity) {
        Connection connection = null;
        PreparedStatement ps = null;
        Boolean isDeleted = false;

        try {
            connection = this.getConnection();
            
            switch (entity) {
                case Constant.ENTITY_COURSE:
                    ps = connection.prepareStatement(DELETE_COURSE_QUERY);
                    break;
                case Constant.ENTITY_STUDENT:
                    ps = connection.prepareStatement(DELETE_STUDENT_QUERY);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid entity type: " + entity);
            }
            
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            isDeleted = rowsAffected > 0;
            connection.commit();
            
        } catch (SQLException e) {
            System.err.println("Error deleting " + entity + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
        }
        return isDeleted;
    }
}
