package edu.main.GUI;

import edu.dao.LopMonHocDao;
import edu.dao.MonHocDao;
import edu.dao.SinhVienDao;
import edu.pojo.LopMonHoc;
import edu.pojo.MonHoc;
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
 * @Date 22/06/2020 - 8:31 PM
 * @Description
 **/
public class ShowScheduleListener implements ActionListener {

    private final JPanel container;
    private final JFrame frame;
    private final JComboBox<String> baseClass;

    public ShowScheduleListener(JPanel container, JFrame frame, JComboBox<String> baseClass) {
        this.container = container;
        this.frame = frame;
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

        if (TeacherGUI.table != null)
            container.remove(TeacherGUI.table);

        if (TeacherGUI.infoPanel != null)
            container.remove(TeacherGUI.infoPanel);

        JPanel jPanel = new JPanel(new GridLayout(0, 1));
        jPanel.add(new JLabel("Thời khóa biểu của lớp: " + baseClass.getSelectedItem()));

        container.add(jPanel);
        container.add(scrollPane);
        TeacherGUI.table = scrollPane;
        TeacherGUI.infoPanel = jPanel;

        container.repaint();
        container.revalidate();
        frame.pack();

    }
}
