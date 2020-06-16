package edu.main.GUI;

import edu.dao.LopSinhHoatDao;
import edu.pojo.LopSinhHoat;

import javax.swing.*;
import java.awt.*;
import java.lang.ref.PhantomReference;
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

    private static final int SPACE_SIZE = 5;
    private static final int TAB_SIZE = 4 * SPACE_SIZE;
    private static final int PANEL_HEIGHT = 100;
    private static final int PANEL_WIDTH = 450;

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {

        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Quản Lý Lớp Học");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the window to be visible as the default to be false
        frame.setVisible(true);

        // Create Panel
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Set up the title for panels
        container.setBorder(BorderFactory.createTitledBorder("Thành Long"));
        frame.setContentPane(container);

        // Components
        // Buttons
        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        container.add(logoutButton);

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
        JButton addStudentListButton = new JButton("Thêm danh sách lớp");
        JButton addScheduleButton = new JButton("Thêm thời khóa biểu");
        JButton showStudentListButton = new JButton("Xem danh sách lớp");
        JButton showScheduleButton = new JButton("Xem thời khóa biểu");

        // add buttons to panel
        panel.add(addClassButton);
        panel.add(addStudentListButton);
        panel.add(addScheduleButton);
        panel.add(showStudentListButton);
        panel.add(showScheduleButton);
        System.out.println(addStudentListButton.getSize());
        // Label
        JLabel baseClassLabel = new JLabel("Lớp sinh hoạt: ");
        panel.add(baseClassLabel);

        // ComboBox
        JComboBox<String> classList = new JComboBox<>(Objects.requireNonNull(getClassStringArray()));
        panel.add(classList);

        // Add Listener
        addClassButton.addActionListener(new AddBaseClassListener(getClassStringArray(), classList));

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
        JButton addStudentListButton = new JButton("Thêm sinh viên");
        JButton addScheduleButton = new JButton("Xem danh sách lớp");
        JButton showStudentListButton = new JButton("Xóa sinh viên");
        JButton showScheduleButton = new JButton("Thêm bảng điểm");

        // add buttons to panel
        panel.add(addStudentListButton);
        panel.add(addScheduleButton);
        panel.add(showStudentListButton);
        panel.add(showScheduleButton);

        // Label
        JLabel baseClassLabel = new JLabel("Các môn học: ");
        panel.add(baseClassLabel);

        // ComboBox
        JComboBox<String> classList = new JComboBox<>(Objects.requireNonNull(getClassStringArray()));
        panel.add(classList);

        // label constraint
        springLayout.putConstraint(SpringLayout.WEST, baseClassLabel, SPACE_SIZE, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, baseClassLabel, SPACE_SIZE, SpringLayout.NORTH, panel);

        // comboBox constraint
        springLayout.putConstraint(SpringLayout.WEST, classList, 2 * SPACE_SIZE, SpringLayout.WEST, panel);
        springLayout.putConstraint(SpringLayout.NORTH, classList, SPACE_SIZE, SpringLayout.SOUTH, baseClassLabel);

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

    private static JTable createStudentTable() {


        return null;
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
}
