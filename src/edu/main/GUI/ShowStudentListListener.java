package edu.main.GUI;

import com.sun.istack.NotNull;
import edu.dao.SinhVienDao;
import edu.pojo.SinhVien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 18/06/2020 - 9:22 PM
 * @Description
 **/
public class ShowStudentListListener implements ActionListener {

    private final JComboBox<String> baseClass;
    private final JComboBox<String> subClass;

    public ShowStudentListListener(JComboBox<String> baseClass, JComboBox<String> subClass) {
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        List<SinhVien> sinhVienList;

        if (subClass == null)
            sinhVienList = SinhVienDao.layDanhSachSinhVien((String) baseClass.getSelectedItem(), null);
        else
            sinhVienList = SinhVienDao.layDanhSachSinhVien((String) baseClass.getSelectedItem(),
                    (String) subClass.getSelectedItem());

        if (sinhVienList == null)
            JOptionPane.showMessageDialog(new JFrame(), "Chưa tồn tại danh sách sinh viên của lớp này");

        // Create table
        String[] columnNames = {"STT", "MSSV" , "Họ tên", "Giới tính", "CMND"};

        Object[][] data = new Object[sinhVienList.size()][];
        for (int i = 0; i < sinhVienList.size(); i++) {
            data[i] = sinhVienList.get(i).toArray(i + 1);
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(TeacherGUI.PANEL_WIDTH, TeacherGUI.PANEL_HEIGHT * 2));

        JPanel jPanel = new JPanel(new GridLayout(0, 1));

        String className = (String) baseClass.getSelectedItem();
        int type = TeacherGUI.TABLE_BASE_CLASS;

        if (subClass != null) {
            className += "-" + subClass.getSelectedItem();
            type = TeacherGUI.TABLE_SUBJECT_CLASS;
        }


        jPanel.add(new JLabel("Tên lớp: " + className));
        jPanel.add(new JLabel("Số lượng: " + data.length));

        TeacherGUI.setTableView(scrollPane, table, jPanel, type);
    }
}
