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

    private JPanel container;
    private JFrame frame;
    private JComboBox<String> baseClass;
    private JComboBox<String> subClass;

    public ShowStudentListListener(JFrame frame, JPanel container, @NotNull JComboBox<String> baseClass, JComboBox<String> subClass) {
        this.frame = frame;
        this.container = container;
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (subClass == null) {
            SinhVienDao.layDanhSachSinhVien((String) baseClass.getSelectedItem(), null);

            String[] columnNames = {"STT", "MSSV" , "Họ tên", "Giới tính", "CMND"};
            List<SinhVien> sinhVienList;

            if (subClass != null)
                sinhVienList = SinhVienDao.layDanhSachSinhVien((String)
                        baseClass.getSelectedItem(), (String) subClass.getSelectedItem());
            else
                sinhVienList = SinhVienDao.layDanhSachSinhVien((String)
                        baseClass.getSelectedItem(), null);

            if (sinhVienList == null) {
                JOptionPane.showMessageDialog(new JFrame(),"Chưa tồn tại danh sách sinh viên của lớp này");
            }

            Object[][] data = new Object[sinhVienList.size()][];
            for (int i = 0; i < sinhVienList.size(); i++) {
                data[i] = sinhVienList.get(i).toArray(i + 1);
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            table.setFillsViewportHeight(true);
            scrollPane.setPreferredSize(new Dimension(TeacherGUI.PANEL_WIDTH, TeacherGUI.PANEL_HEIGHT * 2));

            if (TeacherGUI.table != null)
                container.remove(TeacherGUI.table);
            container.add(scrollPane);
            TeacherGUI.table = scrollPane;

            container.repaint();
            container.revalidate();
            frame.pack();
        }
    }
}
