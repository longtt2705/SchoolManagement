package edu.main.GUI;

import edu.dao.LopSinhHoatDao;
import edu.pojo.LopSinhHoat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 14/06/2020 - 5:24 PM
 * @Description
 **/
public class AddBaseClassListener implements ActionListener {

    List<String> baseClassNames;
    JComboBox<String> linkedComboBox;

    public AddBaseClassListener(String[] base, JComboBox<String> linkedComboBox) {

        baseClassNames = new ArrayList<>(Arrays.asList(base));
        this.linkedComboBox = linkedComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        while (true) {

            String baseClassName = JOptionPane.showInputDialog("Nhập tên lớp: ");
            if (baseClassName == null)
                return;

            JFrame frame = new JFrame();

            if (baseClassName.compareTo("") == 0) {

                JOptionPane.showMessageDialog(frame,
                        "Tên không hợp lệ",
                        "Duplicate value error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if (baseClassNames.contains(baseClassName)) {

                JOptionPane.showMessageDialog(frame,
                        "Tên này đã tồn tại. Vui lòng nhập tên khác",
                        "Duplicate value error",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                LopSinhHoatDao.themLopSinhHoat(baseClassName);
                JOptionPane.showMessageDialog(frame, "Thêm lớp thành công");
                TeacherGUI.updateBaseClassComboBox(linkedComboBox);
                linkedComboBox.setSelectedIndex(linkedComboBox.getItemCount() - 1);
                break;
            }

        }

    }
}
