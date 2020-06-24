package edu.main.GUI;

import edu.dao.DanhSachHocDao;
import edu.dao.LopMonHocDao;
import edu.pojo.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 22/06/2020 - 11:34 PM
 * @Description
 **/

public class DeleteStudentListener implements ActionListener {

    private JComboBox<String> baseClass;
    private JComboBox<String> subClass;

    public DeleteStudentListener(JComboBox<String> baseClass, JComboBox<String> subClass) {
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (TeacherGUI.getTypeTable() != TeacherGUI.TABLE_SUBJECT_CLASS) {
            JOptionPane.showMessageDialog(new JFrame(),"Bạn cần chọn một sinh viên của lớp môn học",
                    "Delete error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable currentTable = TeacherGUI.getTableView();
        int column = 1; // mssv
        int row = currentTable.getSelectedRow();
        String value = currentTable.getModel().getValueAt(row, column).toString();

        String baseClassName = (String) baseClass.getSelectedItem();
        String subClassName = (String) subClass.getSelectedItem();

        LopMonHoc lopMonHoc =  LopMonHocDao.layLopMonHoc(new LopMonHoc(new LopSinhHoat(baseClassName), new MonHoc(subClassName)));
        SinhVien sinhVien = new SinhVien(value); // mssv

        DanhSachHocDao.xoaDanhSachHoc(new DanhSachHoc(sinhVien, lopMonHoc));
    }
}
