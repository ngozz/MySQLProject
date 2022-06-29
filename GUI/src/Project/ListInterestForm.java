package Project;

import com.mysql.jdbc.Connection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ListInterestForm extends JFrame implements ActionListener {
    private JLabel jlStartDate;
    private JTextField jtfStartDate;
    private JLabel jlEndDate;
    private JTextField jtfEndDate;
    private JButton jbtnList;
    private JButton jbtnInterest;
    private Container c;

    public ListInterestForm() {
        setTitle("Feature Form");
        setBounds(500, 150, 530, 180);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        jlStartDate = new JLabel("Nhập ngày (DD/MM/YYYY):");
        jlStartDate.setFont(new Font("Arial", Font.PLAIN, 15));
        jlStartDate.setSize(200, 30);
        jlStartDate.setLocation(40, 30);
        c.add(jlStartDate);

        jtfStartDate = new JTextField();
        jtfStartDate.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfStartDate.setSize(100, 30);
        jtfStartDate.setLocation(250, 30);
        c.add(jtfStartDate);

        jlEndDate = new JLabel("-");
        jlEndDate.setFont(new Font("Arial", Font.PLAIN, 15));
        jlEndDate.setSize(150, 30);
        jlEndDate.setLocation(360, 30);
        c.add(jlEndDate);

        jtfEndDate= new JTextField();
        jtfEndDate.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfEndDate.setSize(100, 30);
        jtfEndDate.setLocation(375, 30);
        c.add(jtfEndDate);

        jbtnList = new JButton("Thống kê");
        jbtnList.setFont(new Font("Arial", Font.PLAIN, 15));
        jbtnList.setSize(100, 30);
        jbtnList.setLocation(130, 85);
        jbtnList.addActionListener(this);
        c.add(jbtnList);

        jbtnInterest = new JButton("Tính lãi");
        jbtnInterest.setFont(new Font("Arial", Font.PLAIN, 15));
        jbtnInterest.setSize(100, 30);
        jbtnInterest.setLocation(280, 85);
        jbtnInterest.addActionListener(this);
        c.add(jbtnInterest);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtnList) {
            setVisible(false);
            Connection conn;
            ResultSet rs;
            JFrame frame = new JFrame("Dữ liệu bảng");
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
            frame.setSize(780, 500);

            DefaultTableModel defaultTableModel = new DefaultTableModel();
            JTable table = new JTable(defaultTableModel);
            table.setPreferredScrollableViewportSize(new Dimension(700, 100));
            table.setFillsViewportHeight(true);
            frame.add(new JScrollPane(table));

            defaultTableModel.addColumn("ID");
            defaultTableModel.addColumn("Người mua");
            defaultTableModel.addColumn("Người thụ hưởng");
            defaultTableModel.addColumn("Giá trị (nghìn VNĐ)");
            defaultTableModel.addColumn("Ngày bắt đầu");
            defaultTableModel.addColumn("Thời hạn");
            defaultTableModel.addColumn("Loại hợp đồng");
            try {
                //Chuyển util.Date sang sql.Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date startDate = sdf.parse(jtfStartDate.getText());
                java.util.Date endDate = sdf.parse(jtfEndDate.getText());
                java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
                java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());


                String dbURL = "jdbc:mysql://localhost/mysql_db";
                String username = "root";
                String password = "hieu";
                conn = (Connection) DriverManager.getConnection(dbURL, username, password);

                String sql = "SELECT * FROM `oopproject` WHERE `Thời gian` BETWEEN ? AND ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setDate(1, sqlStartDate);
                preparedStatement.setDate(2, sqlEndDate);

                rs = preparedStatement.executeQuery();

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "Không có dữ liệu!");
                }

                while (rs.next()) {
                    //Lấy dữ liệu từ database,tạm lưu lại và thêm vào bảng
                    int id = rs.getInt("ID");
                    String buyer = rs.getString("Người mua");
                    String beneficiary = rs.getString("Người thụ hưởng");
                    double value = rs.getDouble("Giá trị");
                    Date date = rs.getDate("Thời gian");
                    String time = rs.getString("Thời hạn");
                    String type = rs.getString("Loại hợp đồng");

                    defaultTableModel.addRow(new Object[]{id, buyer, beneficiary, value, date, time, type});//Adding row in Table
                    frame.setVisible(true);
                    frame.validate();
                }
                conn.close();
            } catch (SQLException | ParseException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại",
                        "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == jbtnInterest) {
            setVisible(false);
            Connection conn;
            ResultSet rs;
            double totalInterest = 0;
            try {
                //Chuyển util.Date sang sql.Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date startDate = sdf.parse(jtfStartDate.getText());
                java.util.Date endDate = sdf.parse(jtfEndDate.getText());
                java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
                java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

                String dbURL = "jdbc:mysql://localhost/mysql_db";
                String username = "root";
                String password = "hieu";
                conn = (Connection) DriverManager.getConnection(dbURL, username, password);

                String sql = "SELECT * FROM `oopproject` WHERE `Thời gian` BETWEEN ? AND ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setDate(1, sqlStartDate);
                preparedStatement.setDate(2, sqlEndDate);

                rs = preparedStatement.executeQuery();

                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "Không có dữ liệu!");
                }

                while (rs.next()) {
                    double value = rs.getDouble("Giá trị");
                    String type = rs.getString("Loại hợp đồng");

                    if (type.equals("Cơ bản"))
                        totalInterest += (value * 0.1);
                    else totalInterest += (value * 0.17 + 1000);
                }
                if (totalInterest < 1000) {
                    JOptionPane.showMessageDialog(null, "Tiền lãi: " + totalInterest + " nghìn VND");
                }
                else if (totalInterest < 1000000 && totalInterest > 1000) {
                    JOptionPane.showMessageDialog(null, "Tiền lãi: " + totalInterest/1000.0 + " triệu VND");
                }
                conn.close();

            } catch (SQLException | ParseException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại",
                        "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
