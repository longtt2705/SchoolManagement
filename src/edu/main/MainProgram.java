package edu.main;

import edu.main.GUI.LoginFormGUI;
import edu.main.GUI.TeacherGUI;
import edu.pojo.TaiKhoan;

/**
 * logic.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 11/06/2020 - 10:25 AM
 * @Description
 **/

public class MainProgram {

    private static TaiKhoan currentAccount;

    public enum ViewLevel {
        TEACHER,
        STUDENT,
        LOGIN
    }

    public static void main(String[] args) {

        invokeGUI(ViewLevel.LOGIN);
    }

    public static void invokeGUI(ViewLevel viewLevel) {

        switch (viewLevel) {
            case LOGIN -> javax.swing.SwingUtilities.invokeLater(LoginFormGUI::createAndShowGUI);
            case TEACHER -> javax.swing.SwingUtilities.invokeLater(TeacherGUI::createAndShowGUI);
            case STUDENT -> javax.swing.SwingUtilities.invokeLater(TeacherGUI::createAndShowGUI);
        }

    }

    public static TaiKhoan getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(TaiKhoan currentAccount) {
        MainProgram.currentAccount = currentAccount;
    }
}
