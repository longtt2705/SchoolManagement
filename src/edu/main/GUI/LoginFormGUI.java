package edu.main.GUI;

import edu.dao.TaiKhoanDao;
import edu.main.MainProgram;
import edu.pojo.TaiKhoan;
import org.jboss.jandex.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 26/06/2020 - 10:10 AM
 * @Description
 **/
public class LoginFormGUI implements ActionListener {

    private static JFrame frame;
    private static JPanel container;
    private static JTextField userName;
    private static JPasswordField password;

    public static void createAndShowGUI() {

        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Quản Lý Lớp Học");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the window to be visible as the default to be false
        frame.setVisible(true);

        // Create Panel
        container = new JPanel();
        container.setPreferredSize(new Dimension(200, 200));
        container.setLayout(new GridLayout(0, 1));

        // Set up the title for panels
        container.setBorder(BorderFactory.createTitledBorder("Đăng nhập"));
        frame.setContentPane(container);

        // Components
        userName = new JTextField();
        password = new JPasswordField();
        // Buttons
        JButton submitButton = new JButton("Đăng nhập");

        // add components
        container.add(new JLabel("Tên đăng nhập:"));
        container.add(userName);
        container.add(new JLabel("Mật khẩu:"));
        container.add(password);
        container.add(submitButton);
        submitButton.addActionListener(new LoginFormGUI());

        // Set window fits components
        frame.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        TaiKhoan taiKhoan = TaiKhoanDao.layTaiKhoan(new TaiKhoan(userName.getText(), String.valueOf(password.getPassword())));

        if (taiKhoan != null) {
            if (!taiKhoan.getLoaiTaiKhoan())
                MainProgram.invokeGUI(MainProgram.ViewLevel.TEACHER);
            else
                MainProgram.invokeGUI(MainProgram.ViewLevel.STUDENT);

            MainProgram.setCurrentAccount(taiKhoan);
            frame.dispose();

        }
        else {
            JOptionPane.showMessageDialog(null,"Tên đăng nhập hoặc mật khẩu không đúng",
                    "Login error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
