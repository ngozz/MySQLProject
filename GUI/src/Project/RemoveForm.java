package Project;

import com.mysql.jdbc.Connection;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveForm {
    public RemoveForm(int removeID) {
        Connection conn;
        try {
            String dbURL = "jdbc:mysql://localhost/mysql_db";
            String username = "root";
            String password = "hieu";
            conn = (Connection) DriverManager.getConnection(dbURL, username, password);

            String sql = "DELETE FROM `oopproject` WHERE ID = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, removeID);

            preparedStatement.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(null, "Xoá hợp đồng thành công");

        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại",
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
}
