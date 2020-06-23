package edu.main.GUI;

import edu.dao.LopMonHocDao;
import edu.dao.LopSinhHoatDao;
import edu.pojo.LopMonHoc;
import edu.pojo.LopSinhHoat;
import edu.pojo.MonHoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * edu.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 12/06/2020 - 9:16 AM
 * @Description
 **/
public class TeacherGUI {

    public static final int SPACE_SIZE = 5;
    public static final int TAB_SIZE = 4 * SPACE_SIZE;
    public static final int PANEL_HEIGHT = 100;
    public static final int PANEL_WIDTH = 450;

    private static JPanel container;
    private static JFrame frame;
    private static JComboBox<String> baseClass;
    private static JComboBox<String> subjectClass;

    public static JScrollPane table = null;
    public static JPanel infoPanel = null;

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Quản Lý Lớp Học");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the window to be visible as the default to be false
        frame.setVisible(true);

        // Create Panel
        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Set up the title for panels
        container.setBorder(BorderFactory.createTitledBorder("Thành Long"));
        frame.setContentPane(container);

        // Components
        // Buttons
        JButton logoutButton = new JButton("Đăng xuất");
        JButton changePassButton = new JButton("Đổi mật khẩu");

        // Navigate Bar
        JPanel navigatePanel = new JPanel();
        navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.X_AXIS));
        navigatePanel.add(logoutButton);
        navigatePanel.add(changePassButton);
        container.add(navigatePanel);

        JPanel baseClassPanel = createBaseClassPanel();
        JPanel subjectClassPanel = createSubjectClassPanel();
        container.add(baseClassPanel);
        container.add(subjectClassPanel);

        // Set window fits components
        frame.pack();
    }

    private static JPanel createBaseClassPanel() {

        // Set up the panel
        JPanel panel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        panel.setLayout(springLayout);
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Button
        JButton addClassButton = new JButton("+");
        JButton addStudentListButton = new JButton("Thêm sinh viên");
        JButton addScheduleButton = new JButton("Thêm thời khóa biểu");
        JButton showStudentListButton = new JButton("Xem danh sách lớp");
        JButton showScheduleButton = new JButton("Xem thời khóa biểu");

        // add buttons to panel
        panel.add(addClassButton);
        panel.add(addStudentListButton);
        panel.add(addScheduleButton);
        panel.add(showStudentListButton);
        panel.add(showScheduleButton);

        // Label
        JLabel baseClassLabel = new JLabel("Lớp sinh hoạt: ");
        panel.add(baseClassLabel);

        // ComboBox
        JComboBox<String> classList = new JComboBox<>(Objects.requireNonNull(getClassStringArray()));
        baseClass = classList;
        baseClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maLop = (String) classList.getSelectedItem();//get the selected item
                if (subjectClass != null)
                    updateSubjectClassComboBox(maLop, subjectClass);
            }
        });
        panel.add(classList);

        // Add Listener
        addClassButton.addActionListener(new AddBaseClassListener(getClassStringArray(), classList));
        addStudentListButton.addActionListener(new AddStudentListener(classList));
        showStudentListButton.addActionListener(new ShowStudentListListener(frame, container, classList, null));
        addScheduleButton.addActionListener(new AddScheduleListener(classList));
        showScheduleButton.addActionListener(new ShowScheduleListener(container, frame, classList));

        // label constraint
        springLayout.putConstraint(SpringLayout.WEST, baseClassLabel, SPACE_SIZE, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, baseClassLabel, SPACE_SIZE, SpringLayout.NORTH, panel);

        // addClassButton constraint
        springLayout.putConstraint(SpringLayout.WEST, addClassButton, 2 * SPACE_SIZE, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, addClassButton, SPACE_SIZE, SpringLayout.SOUTH, baseClassLabel);

        // comboBox constraint
        springLayout.putConstraint(SpringLayout.WEST, classList, SPACE_SIZE, SpringLayout.EAST, addClassButton);
        springLayout.putConstraint(SpringLayout.NORTH, classList, 0, SpringLayout.NORTH, addClassButton);

        // addStudentListButton constraint
        springLayout.putConstraint(SpringLayout.WEST, addStudentListButton, SPACE_SIZE, SpringLayout.EAST, classList);
        springLayout.putConstraint(SpringLayout.NORTH, addStudentListButton, 0, SpringLayout.NORTH, classList);

        // showStudentListButton constraint
        springLayout.putConstraint(SpringLayout.WEST, showStudentListButton, SPACE_SIZE, SpringLayout.EAST, addStudentListButton);
        springLayout.putConstraint(SpringLayout.NORTH, showStudentListButton, 0, SpringLayout.NORTH, classList);

        // addScheduleButton constraint
        springLayout.putConstraint(SpringLayout.WEST, addScheduleButton, SPACE_SIZE, SpringLayout.EAST, classList);
        springLayout.putConstraint(SpringLayout.NORTH, addScheduleButton, SPACE_SIZE, SpringLayout.SOUTH, addStudentListButton);

        // showScheduleButton constraint
        springLayout.putConstraint(SpringLayout.WEST, showScheduleButton, SPACE_SIZE, SpringLayout.EAST, addScheduleButton);
        springLayout.putConstraint(SpringLayout.NORTH, showScheduleButton, SPACE_SIZE, SpringLayout.SOUTH, addStudentListButton);

        return panel;
    }

    private static JPanel createSubjectClassPanel() {

        // Set up the panel
        JPanel panel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        panel.setLayout(springLayout);
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Button
        JButton addStudentButton = new JButton("Thêm sinh viên");
        JButton showStudentListButton = new JButton("Xem danh sách lớp");
        JButton deleteStudentButton = new JButton("Xóa sinh viên");
        JButton addTranscriptButton = new JButton("Thêm bảng điểm");

        // add buttons to panel
        panel.add(addStudentButton);
        panel.add(showStudentListButton);
        panel.add(deleteStudentButton);
        panel.add(addTranscriptButton);

        // Label
        JLabel baseClassLabel = new JLabel("Các môn học: ");
        panel.add(baseClassLabel);

        // ComboBox
        List<String> data = LopMonHocDao.layDanhSachMaMonHoc((String) baseClass.getSelectedItem());
        JComboBox<String> subClassList = new JComboBox<>(data.toArray(new String[0]));
        subjectClass = subClassList;
        panel.add(subClassList);

        // Add Listener
        showStudentListButton.addActionListener(new ShowStudentListListener(frame, container, baseClass, subjectClass));

        // label constraint
        springLayout.putConstraint(SpringLayout.WEST, baseClassLabel, SPACE_SIZE, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, baseClassLabel, SPACE_SIZE, SpringLayout.NORTH, panel);

        // comboBox constraint
        springLayout.putConstraint(SpringLayout.WEST, subClassList, 2 * SPACE_SIZE, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, subClassList, SPACE_SIZE, SpringLayout.SOUTH, baseClassLabel);

        // addStudentListButton constraint
        springLayout.putConstraint(SpringLayout.WEST, addStudentButton, SPACE_SIZE, SpringLayout.EAST, subClassList);
        springLayout.putConstraint(SpringLayout.NORTH, addStudentButton, 0, SpringLayout.NORTH, subClassList);

        // showStudentListButton constraint
        springLayout.putConstraint(SpringLayout.WEST, deleteStudentButton, SPACE_SIZE, SpringLayout.EAST, addStudentButton);
        springLayout.putConstraint(SpringLayout.NORTH, deleteStudentButton, 0, SpringLayout.NORTH, subClassList);

        // addScheduleButton constraint
        springLayout.putConstraint(SpringLayout.WEST, showStudentListButton, SPACE_SIZE, SpringLayout.EAST, subClassList);
        springLayout.putConstraint(SpringLayout.NORTH, showStudentListButton, SPACE_SIZE, SpringLayout.SOUTH, addStudentButton);

        // showScheduleButton constraint
        springLayout.putConstraint(SpringLayout.WEST, addTranscriptButton, SPACE_SIZE, SpringLayout.EAST, showStudentListButton);
        springLayout.putConstraint(SpringLayout.NORTH, addTranscriptButton, SPACE_SIZE, SpringLayout.SOUTH, addStudentButton);

        return panel;
    }

    public static String[] getClassStringArray() {

        List<LopSinhHoat> lopSinhHoats = LopSinhHoatDao.layDanhSachLop();
        List<String> arr = new ArrayList<>();

        if (lopSinhHoats != null) {

            lopSinhHoats.forEach(e -> {

                arr.add(e.getMaLop());
            });

            return arr.toArray(new String[0]);
        }

        return null;
    }

    public static void updateBaseClassComboBox(JComboBox<String> baseClassesComboBox) {

        baseClassesComboBox.removeAllItems();

        List<String> baseClassNames = Arrays.asList(Objects.requireNonNull(getClassStringArray()));

        baseClassNames.forEach(baseClassesComboBox::addItem);
    }

    public static void updateSubjectClassComboBox(String maLop, JComboBox<String> subjectClassesComboBox) {

        subjectClassesComboBox.removeAllItems();

        List<String> baseClassNames = LopMonHocDao.layDanhSachMaMonHoc(maLop);
        if (baseClassNames != null)
            baseClassNames.forEach(subjectClassesComboBox::addItem);
    }
}
