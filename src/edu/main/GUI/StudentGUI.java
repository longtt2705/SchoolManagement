package edu.main.GUI;

import edu.dao.DanhSachHocDao;
import edu.dao.TaiKhoanDao;
import edu.main.MainProgram;
import edu.pojo.DanhSachHoc;
import edu.pojo.LopMonHoc;
import edu.pojo.SinhVien;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 26/06/2020 - 1:42 PM
 * @Description
 **/
public class StudentGUI {

    public static void createAndShowGUI() {

        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Quản Lý Lớp Học");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the window to be visible as the default to be false
        frame.setVisible(true);

        // Create Panel
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Set up the title for panels
        container.setBorder(BorderFactory.createTitledBorder("Sinh Viên"));
        frame.setContentPane(container);

        // Components
        // Buttons
        JButton logoutButton = new JButton("Đăng xuất");
        JButton changePassButton = new JButton("Đổi mật khẩu");
        logoutButton.addActionListener(al -> {
            MainProgram.invokeGUI(MainProgram.ViewLevel.LOGIN);
            frame.dispose();
        });

        // Temp panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(changePassButton);
        panel.add(logoutButton);


        // current student
        SinhVien sinhVien = TaiKhoanDao.laySinhVienTuongUng(MainProgram.getCurrentAccount());
        assert sinhVien != null;

        // add components
        container.add(new JLabel("MSSV: " + sinhVien.getMaSinhVien()));
        container.add(new JLabel("Họ tên: " + sinhVien.getHoTen()));
        container.add(new JLabel("Giới tính: " + sinhVien.getGioiTinh()));
        container.add(new JLabel("Chứng minh nhân dân: " + sinhVien.getChungMinhThu()));
        container.add(panel);

        // Create table
        String[] columnNames = {"STT", "Mã lớp" , "Tên lớp", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả"};
        List<DanhSachHoc> danhSachHocList = DanhSachHocDao.layDanhSachHocDangThamGia(sinhVien.getMaSinhVien());

        assert danhSachHocList != null;
        Object[][] data = new Object[danhSachHocList.size()][];
        for (int i = 0; i < danhSachHocList.size(); i++) {

            data[i] = danhSachHocList.get(i).toArray(i + 1);
            LopMonHoc lopMonHoc = danhSachHocList.get(i).getLopMonHoc();
            data[i][1] = lopMonHoc.getLopSinhHoat().getMaLop() + "-" + lopMonHoc.getMonHoc().getMaMon();
            data[i][2] = lopMonHoc.getMonHoc().getTenMon();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        container.add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(500, 150));

        // Set window fits components
        frame.pack();
    }
}
