//Written by Lê Duy Hiếu
package Project;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class GUI extends JFrame {
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private TableRowSorter sorter;

    public static void main(String[] args) {
        new GUI();
    }

    //Kiểm tra xem jtextfield, jpasswordfield có rỗng không
    public boolean isTextFieldEmpty(JTextField textField) {
        if (textField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Chưa nhập username");
            return true;
        }
        else return false;
    }

    public boolean isPasswordFieldEmpty(JPasswordField passwordField) {
        if (passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Chưa nhập password");
            return true;
        }
        else return false;
    }

    //Kiểm tra username và password
    public boolean LoginChecker(JTextField textField, JPasswordField passwordField) {
        String username = textField.getText();
        String password = String.valueOf(passwordField.getPassword());
        if ((username.equals("root")) && (password.equals("hieu"))) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "Đăng nhập thất bại. Vui lòng thử lại");
            return false;
        }
    }

    public GUI()  {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JFrame frame = new JFrame();
        frame.setTitle("Login form");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        lbUsername = new JLabel("Username:");
        lbUsername.setBounds(100, 8, 70, 20);
        panel.add(lbUsername);

        txtUsername = new JTextField(15);
        txtUsername.setBounds(100, 27, 193, 28);
        panel.add(txtUsername);

        lbPassword = new JLabel("Password");
        lbPassword.setBounds(100, 55, 70, 20);
        panel.add(lbPassword);

        txtPassword = new JPasswordField(15);
        txtPassword.setBounds(100, 75, 193, 28);
        panel.add(txtPassword);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBounds(140, 120, 100, 25);
        panel.add(btnLogin);

        frame.setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!((isTextFieldEmpty(txtUsername)) && (isPasswordFieldEmpty(txtPassword)))) {
                    if (LoginChecker(txtUsername, txtPassword)) login();
                }
            }
        });

        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!((isTextFieldEmpty(txtUsername)) && (isPasswordFieldEmpty(txtPassword)))) {
                        if (LoginChecker(txtUsername, txtPassword)) login();
                    }
                }
            }
        });

        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!((isTextFieldEmpty(txtUsername)) && (isPasswordFieldEmpty(txtPassword)))) {
                        if (LoginChecker(txtUsername, txtPassword)) login();
                    }
                }
            }
        });
    }

    private void login() {
        JFrame frame1 = new JFrame("Quản lý hợp đồng");
        frame1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        frame1.setSize(300, 150);
        frame1.setLocation(610,300);

        JButton jbtnAdd = new JButton("Thêm/Sửa");
        JButton jbtnDelete = new JButton("Xoá");
        JButton jbtnList = new JButton("Thống kê/Tính lãi");
        JButton jbtnPrint = new JButton("In bảng");

        frame1.add(jbtnAdd);
        frame1.add(jbtnDelete);
        frame1.add(jbtnList);
        frame1.add(jbtnPrint);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);

        jbtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUpdateForm frameAdd = new AddUpdateForm();
            }
        });

        jbtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeIDString = JOptionPane.showInputDialog("Nhập ID hợp đồng cần xoá");
                if (removeIDString != null) {
                    int removeID = Integer.parseInt(removeIDString);
                    new RemoveForm(removeID);
                }
            }
        });

        jbtnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListInterestForm frameAdd = new ListInterestForm();
            }
        });

        jbtnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table();
            }
        });
    }

    private void table() {
        //Tạo bảng
        JFrame frame2 = new JFrame("Dữ liệu bảng");
        frame2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        frame2.setSize(780, 500);

        DefaultTableModel defaultTableModel = new DefaultTableModel();
        JTable table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(700, 100));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));

        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Người mua");
        defaultTableModel.addColumn("Người thụ hưởng");
        defaultTableModel.addColumn("Giá trị (nghìn VNĐ)");
        defaultTableModel.addColumn("Ngày bắt đầu");
        defaultTableModel.addColumn("Thời hạn");
        defaultTableModel.addColumn("Loại hợp đồng");

        //Tạo chức năng tìm kiếm
        JLabel jlSearch = new JLabel("Tìm kiếm:");
        JTextField jtfSearch = new JTextField(30);
        frame2.add(jlSearch);
        frame2.add(jtfSearch);
        sorter = new TableRowSorter<>(defaultTableModel);
        table.setRowSorter(sorter);

        // Xét cả 3 trường hợp là thêm, xoá, sửa text rồi lọc
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jtfSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jtfSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jtfSearch.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });

        //viết code kết nối cơ sở dữ liệu và kiểm tra đăng nhập
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            String dbURL = "jdbc:mysql://localhost/mysql_db";
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            conn = (Connection) DriverManager.getConnection(dbURL, username, password);

            // Câu lệnh Xem dữ liệu
            String sql = "select * from oopproject";
            // Tạo đối tượng thực thi câu lệnh Select
            st = (Statement) conn.createStatement();
            // Thực thi
            rs = st.executeQuery(sql);

            // Nếu không có dữ liệu trong bảng
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
                frame2.setVisible(true);
                frame2.validate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại",
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
}
