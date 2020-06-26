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
 * @Date 26/06/2020 - 7:47 PM
 * @Description
 **/
public class ChangePasswordListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JPasswordField oldPass = new JPasswordField();
        JPasswordField newPass = new JPasswordField();

        panel.add(new JLabel("Nhập mật khẩu cũ"));
        panel.add(oldPass);
        panel.add(new JLabel("Nhập mật khẩu mới"));
        panel.add(newPass);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Đổi mật khẩu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String oldPassString = String.valueOf(oldPass.getPassword());
            String newPassString = String.valueOf(newPass.getPassword());

            if (!oldPassString.equals(MainProgram.getCurrentAccount().getMatKhau())) {
                JOptionPane.showMessageDialog(new JFrame(),"Mật khẩu cũ không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (oldPassString.equals(newPassString)) {
                JOptionPane.showMessageDialog(new JFrame(),"Mật khẩu mới phải khác cũ ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TaiKhoan taiKhoan = MainProgram.getCurrentAccount();
            taiKhoan.setMatKhau(newPassString);
            TaiKhoanDao.capNhatTaiKhoan(taiKhoan);

        }
    }
}
