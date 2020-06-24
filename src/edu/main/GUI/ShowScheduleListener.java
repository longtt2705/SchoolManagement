package edu.main.GUI;

import edu.dao.LopMonHocDao;
import edu.pojo.MonHoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 22/06/2020 - 8:31 PM
 * @Description
 **/
public class ShowScheduleListener implements ActionListener {

    private final JComboBox<String> baseClass;

    public ShowScheduleListener(JComboBox<String> baseClass) {
        this.baseClass = baseClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String[] columnNames = {"STT", "Mã môn", "Tên môn", "Phòng học"};
        List<MonHoc> monHocList;

        monHocList = LopMonHocDao.layDanhSachMonHoc((String) baseClass.getSelectedItem());

        if (monHocList == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Chưa tồn tại thời khóa biểu của lớp này");
            return;
        }

        Object[][] data = new Object[monHocList.size()][];
        for (int i = 0; i < monHocList.size(); i++) {
            data[i] = monHocList.get(i).toArray(i + 1);
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(TeacherGUI.PANEL_WIDTH, TeacherGUI.PANEL_HEIGHT * 2));

        JPanel jPanel = new JPanel(new GridLayout(0, 1));
        jPanel.add(new JLabel("Thời khóa biểu của lớp: " + baseClass.getSelectedItem()));

        TeacherGUI.setTableView(scrollPane, table, jPanel, false);
    }
}
