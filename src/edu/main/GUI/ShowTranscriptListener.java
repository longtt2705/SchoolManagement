package edu.main.GUI;

import edu.dao.DanhSachHocDao;
import edu.dao.LopMonHocDao;
import edu.dao.LopSinhHoatDao;
import edu.dao.SinhVienDao;
import edu.pojo.*;
import org.hibernate.engine.jdbc.BlobProxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 24/06/2020 - 10:11 PM
 * @Description
 **/
public class ShowTranscriptListener implements ActionListener {

    private final JComboBox<String> baseClass;
    private final JComboBox<String> subClass;

    public ShowTranscriptListener(JComboBox<String> baseClass, JComboBox<String> subClass) {
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Create table
        String[] columnNames = {"STT", "MSSV" , "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Kết quả"};
        LopMonHoc lopMonHoc = new LopMonHoc(new LopSinhHoat((String) baseClass.getSelectedItem()), new MonHoc((String) subClass.getSelectedItem()));

        List<DanhSachHoc> danhSachHocList = DanhSachHocDao.layDanhSachHoc(Objects.requireNonNull(LopMonHocDao.layLopMonHoc(lopMonHoc)));

        assert danhSachHocList != null;
        if (danhSachHocList.size() == 0) {
            JOptionPane.showMessageDialog(new JFrame(),"Chưa có danh sách đăng ký học", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[][] data = new Object[danhSachHocList.size()][];
        int passStudentCounter = 0;
        for (int i = 0; i < danhSachHocList.size(); i++) {
            data[i] = danhSachHocList.get(i).toArray(i + 1);
            if (danhSachHocList.get(i).getKetQua()) {
                ++passStudentCounter;
            }
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(TeacherGUI.PANEL_WIDTH, TeacherGUI.PANEL_HEIGHT * 2));

        JPanel jPanel = new JPanel(new GridLayout(0, 1));

        String className = baseClass.getSelectedItem() + "-" + subClass.getSelectedItem();
        int type = TeacherGUI.TABLE_TRANSCRIPT;

        jPanel.add(new JLabel("Tên lớp: " + className));
        jPanel.add(new JLabel("Số lượng: " + data.length));
        jPanel.add(new JLabel("Số lượng đậu: " + passStudentCounter));

        float passRatio = Math.round(passStudentCounter/(float)data.length * 100);
        jPanel.add(new JLabel("Phần trăm đậu: " +  passRatio + "%"));

        TeacherGUI.setTableView(scrollPane, table, jPanel, type);
    }
}
