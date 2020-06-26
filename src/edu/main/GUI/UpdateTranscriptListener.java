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
 * @Date 25/06/2020 - 5:04 PM
 * @Description
 **/
public class UpdateTranscriptListener implements ActionListener {

    private JComboBox<String> baseClass;
    private JComboBox<String> subClass;

    public UpdateTranscriptListener(JComboBox<String> baseClass, JComboBox<String> subClass) {
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (TeacherGUI.getTypeTable() != TeacherGUI.TABLE_TRANSCRIPT) {
            JOptionPane.showMessageDialog(new JFrame(),"Bạn cần chọn một dòng của bảng điểm",
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

        JTextField diemGK = new JTextField();
        JTextField diemCK = new JTextField();
        JTextField diemKhac = new JTextField();
        JTextField diemTong = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Lớp muốn đăng ký:"));

        panel.add(new JLabel("Điểm giữa kỳ:"));
        panel.add(diemGK);
        panel.add(new JLabel("Điểm cuối kỳ:"));
        panel.add(diemCK);
        panel.add(new JLabel("Điểm khác:"));
        panel.add(diemKhac);
        panel.add(new JLabel("Điểm tổng:"));
        panel.add(diemTong);

        int result = JOptionPane.showConfirmDialog(new JFrame(), panel,
                "Sửa điểm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String lopHocName = (String) baseClass.getSelectedItem();
            String monHocName = (String) subClass.getSelectedItem();

            DanhSachHoc danhSachHoc = new DanhSachHoc();
            SinhVien sinhVien = SinhVienDao.layThongTinSinhVienByMSSV(mssv);
            LopSinhHoat lopSinhHoat = LopSinhHoatDao.layLopSinhHoat(lopHocName);
            MonHoc monHoc = MonHocDao.layThongTinMonHocByMaMon(monHocName);

            danhSachHoc.setSinhVien(sinhVien);
            LopMonHoc lopMonHoc = LopMonHocDao.layLopMonHoc(new LopMonHoc(lopSinhHoat, monHoc));
            danhSachHoc.setLopMonHoc(lopMonHoc);

            danhSachHoc = DanhSachHocDao.layDanhSachHoc(danhSachHoc);
            if (danhSachHoc == null)
                return;

            try {

                danhSachHoc.setDiemGK(Float.parseFloat(diemGK.getText()));
                danhSachHoc.setDiemCK(Float.parseFloat(diemCK.getText()));
                danhSachHoc.setDiemKhac(Float.parseFloat(diemKhac.getText()));
                danhSachHoc.setDiemTong(Float.parseFloat(diemTong.getText()));
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi nhập dữ liệu",
                        "Input error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DanhSachHocDao.capNhatDanhSachHoc(danhSachHoc);
            JOptionPane.showMessageDialog(new JFrame(),"Cập nhật điểm thành công");
        }
    }
}
