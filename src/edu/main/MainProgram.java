package edu.main;

import edu.dao.SinhVienDao;
import edu.main.GUI.TeacherGUI;
import edu.pojo.SinhVien;

/**
 * logic.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 11/06/2020 - 10:25 AM
 * @Description
 **/
public class MainProgram {

    public static void main(String[] args) {

        SinhVien sv = SinhVienDao.layThongTinhSinhVien("1742001");

        if (sv == null) {
            System.out.println("LOL");
        } else {
            System.out.println(sv.getLopSinhHoat().getLopMonHocSet());
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TeacherGUI.createAndShowGUI();
            }
        });
    }
}
