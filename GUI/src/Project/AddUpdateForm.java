package Project;

import com.mysql.jdbc.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddUpdateForm extends JFrame implements ActionListener {
    private Container c;
    private JLabel jlId;
    private JTextField jtfId;
    private JLabel jlBuyer;
    private JTextField jtfBuyer;
    private JLabel jlBeneficiary;
    private JTextField jtfBeneficiary;
    private JLabel jlValue;
    private JTextField jtfValue;
    private JLabel jlDate;
    private JTextField jtfDate;
    private JLabel jlTime;
    private JTextField jtfTime;
    private JLabel jlType;
    private JComboBox jcbType;
    private JButton jbtnAdd;
    private JButton jbtnReset;
    private JButton jbtnUpdate;

    public AddUpdateForm() {
        setTitle("Contract Form");
        setBounds(500, 150, 500, 480);
        setResizable(false);
        setVisible(true);

        c = getContentPane();
        c.setLayout(null);

        jlId = new JLabel("ID (Sửa theo ID):");
        jlId.setFont(new Font("Arial", Font.PLAIN, 15));
        jlId.setSize(150, 30);
        jlId.setLocation(40, 30);
        c.add(jlId);

        jtfId = new JTextField();
        jtfId.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfId.setSize(190, 30);
        jtfId.setLocation(250, 30);
        c.add(jtfId);

        jlBuyer = new JLabel("Người mua:");
        jlBuyer.setFont(new Font("Arial", Font.PLAIN, 15));
        jlBuyer.setSize(150, 30);
        jlBuyer.setLocation(40, 80);
        c.add(jlBuyer);

        jtfBuyer = new JTextField();
        jtfBuyer.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfBuyer.setSize(190, 30);
        jtfBuyer.setLocation(250, 80);
        c.add(jtfBuyer);

        jlBeneficiary = new JLabel("Người thụ hưởng:");
        jlBeneficiary.setFont(new Font("Arial", Font.PLAIN, 15));
        jlBeneficiary.setSize(150, 30);
        jlBeneficiary.setLocation(40, 130);
        c.add(jlBeneficiary);

        jtfBeneficiary = new JTextField();
        jtfBeneficiary.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfBeneficiary.setSize(190, 30);
        jtfBeneficiary.setLocation(250, 130);
        c.add(jtfBeneficiary);

        jlValue = new JLabel("Giá trị hợp đồng (nghìn VNĐ):");
        jlValue.setFont(new Font("Arial", Font.PLAIN, 15));
        jlValue.setSize(200, 30);
        jlValue.setLocation(40, 180);
        c.add(jlValue);

        jtfValue = new JTextField();
        jtfValue.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfValue.setSize(190, 30);
        jtfValue.setLocation(250, 180);
        c.add(jtfValue);

        jlDate = new JLabel("Ngày bắt đầu (DD/MM/YYYY):");
        jlDate.setFont(new Font("Arial", Font.PLAIN, 15));
        jlDate.setSize(200, 30);
        jlDate.setLocation(40, 230);
        c.add(jlDate);

        jtfDate = new JTextField();
        jtfDate.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfDate.setSize(190, 30);
        jtfDate.setLocation(250, 230);
        c.add(jtfDate);

        jlTime = new JLabel("Thời hạn:");
        jlTime.setFont(new Font("Arial", Font.PLAIN, 15));
        jlTime.setSize(150, 30);
        jlTime.setLocation(40, 280);
        c.add(jlTime);

        jtfTime = new JTextField();
        jtfTime.setFont(new Font("Arial", Font.PLAIN, 15));
        jtfTime.setSize(190, 30);
        jtfTime.setLocation(250, 280);
        c.add(jtfTime);

        jlType = new JLabel("Loại hợp đồng:");
        jlType.setFont(new Font("Arial", Font.PLAIN, 15));
        jlType.setSize(150,30);
        jlType.setLocation(40,330);
        c.add(jlType);

        jcbType = new JComboBox(new String[]{"Cơ bản", "Nâng cao"});
        jcbType.setFont(new Font("Arial", Font.PLAIN, 15));
        jcbType.setSize(150,30);
        jcbType.setLocation(250,330);
        c.add(jcbType);

        jbtnAdd = new JButton("Thêm");
        jbtnAdd.setFont(new Font("Arial", Font.PLAIN, 15));
        jbtnAdd.setSize(90, 20);
        jbtnAdd.setLocation(100, 390);
        jbtnAdd.addActionListener(this);
        c.add(jbtnAdd);

        jbtnUpdate = new JButton("Sửa");
        jbtnUpdate.setFont(new Font("Arial", Font.PLAIN, 15));
        jbtnUpdate.setSize(90, 20);
        jbtnUpdate.setLocation(200, 390);
        jbtnUpdate.addActionListener(this);
        c.add(jbtnUpdate);

        jbtnReset = new JButton("Reset");
        jbtnReset.setFont(new Font("Arial", Font.PLAIN, 15));
        jbtnReset.setSize(90, 20);
        jbtnReset.setLocation(300, 390);
        jbtnReset.addActionListener(this);
        c.add(jbtnReset);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtnAdd) {
            Connection conn;
            setVisible(false);
            try {
                //Chuyển util.Date sang sql.Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date = sdf.parse(jtfDate.getText());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                String dbURL = "jdbc:mysql://localhost/mysql_db";
                String username = "root";
                String password = "hieu";
                conn = (Connection) DriverManager.getConnection(dbURL, username, password);

                String sql = "INSERT INTO `oopproject`(`ID`, `Người mua`, `Người thụ hưởng`, `Giá trị`, `Thời gian`, `Thời hạn`, `Loại hợp đồng`) " +
                        "VALUES (?,?,?,?,?,?,?)";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.parseInt(jtfId.getText()));
                preparedStatement.setString(2, jtfBuyer.getText());
                preparedStatement.setString(3, jtfBeneficiary.getText());
                preparedStatement.setDouble(4, Double.parseDouble(jtfValue.getText()));
                preparedStatement.setDate(5, sqlDate);
                preparedStatement.setString(6, jtfTime.getText());
                preparedStatement.setString(7, (String) jcbType.getSelectedItem());

                preparedStatement.executeUpdate();
                conn.close();
                JOptionPane.showMessageDialog(null, "Thêm hợp đồng thành công");

            } catch (SQLException | ParseException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại",
                        "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (e.getSource() == jbtnUpdate) {
            Connection conn;
            setVisible(false);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date = sdf.parse(jtfDate.getText());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                String dbURL = "jdbc:mysql://localhost/mysql_db";
                String username = "root";
                String password = "hieu";
                conn = (Connection) DriverManager.getConnection(dbURL, username, password);

                String sql = "UPDATE `oopproject` SET `Người mua`=?,`Người thụ hưởng`=?," +
                        "`Giá trị`=?,`Thời gian`=?,`Thời hạn`=?,`Loại hợp đồng`=? WHERE ID = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(7, Integer.parseInt(jtfId.getText()));
                preparedStatement.setString(1, jtfBuyer.getText());
                preparedStatement.setString(2, jtfBeneficiary.getText());
                preparedStatement.setDouble(3, Double.parseDouble(jtfValue.getText()));
                preparedStatement.setDate(4, sqlDate);
                preparedStatement.setString(5, jtfTime.getText());
                preparedStatement.setString(6, (String) jcbType.getSelectedItem());

                preparedStatement.executeUpdate();
                conn.close();
                JOptionPane.showMessageDialog(null, "Sửa hợp đồng thành công");

            } catch (SQLException | ParseException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại",
                        "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (e.getSource() == jbtnReset) {
            jtfId.setText("");
            jtfBuyer.setText("");
            jtfBeneficiary.setText("");
            jtfValue.setText("");
            jtfTime.setText("");
            jcbType.setSelectedIndex(0);
        }
    }
}

