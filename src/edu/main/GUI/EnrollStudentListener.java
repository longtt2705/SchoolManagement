package edu.main.GUI;

import edu.dao.*;
import edu.pojo.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 24/06/2020 - 3:43 PM
 * @Description
 **/
public class EnrollStudentListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        if (TeacherGUI.getTypeTable() != TeacherGUI.TABLE_BASE_CLASS) {
            JOptionPane.showMessageDialog(new JFrame(),"Bạn cần chọn một sinh viên của lớp sinh hoạt",
                    "Select error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable currentTable = TeacherGUI.getTableView();
        int column = 1; // mssv
        int row = currentTable.getSelectedRow();
        String mssv;
        try {
            mssv = currentTable.getModel().getValueAt(row, column).toString();
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            JOptionPane.showMessageDialog(new JFrame(),"Bạn cần chọn một dòng",
                    "Select error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<LopMonHoc> lopMonHocList = DanhSachHocDao.layDanhSachLopMonHocChuaThamGia(mssv);
        if (lopMonHocList == null) {
            JOptionPane.showMessageDialog(null, "Sinh viên này đã tham gia tất cả các lớp học");
            return;
        }

        String[] tenLopMonHoc = new String[lopMonHocList.size()];
        int i = 0;
        for (LopMonHoc lopMonHoc : lopMonHocList) {
            tenLopMonHoc[i++] = lopMonHoc.getLopSinhHoat().getMaLop() + "-" + lopMonHoc.getMonHoc().getMaMon();
        }

        JComboBox<String> classComboBox = new JComboBox<>(tenLopMonHoc);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Lớp muốn đăng ký:"));
        panel.add(classComboBox);

        int result = JOptionPane.showConfirmDialog(new JFrame(), panel,
                "Đăng ký vào lớp", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String monHocName = ((String) classComboBox.getSelectedItem()).split("-")[1];
            String lopHocName = ((String) classComboBox.getSelectedItem()).split("-")[0];

            DanhSachHoc danhSachHoc = new DanhSachHoc();
            SinhVien sinhVien = SinhVienDao.layThongTinSinhVienByMSSV(mssv);
            LopSinhHoat lopSinhHoat = LopSinhHoatDao.layLopSinhHoat(lopHocName);
            MonHoc monHoc = MonHocDao.layThongTinMonHocByMaMon(monHocName);

            danhSachHoc.setSinhVien(sinhVien);
            LopMonHoc lopMonHoc = LopMonHocDao.layLopMonHoc(new LopMonHoc(lopSinhHoat, monHoc));
            danhSachHoc.setLopMonHoc(lopMonHoc);

            DanhSachHocDao.themDanhSachHoc(danhSachHoc);

            JOptionPane.showMessageDialog(new JFrame(),"Đăng ký thành công");
        }
    }
}
