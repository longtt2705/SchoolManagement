package edu.main.GUI;

import edu.dao.LopMonHocDao;
import edu.dao.LopSinhHoatDao;
import edu.main.MainProgram;
import edu.pojo.LopSinhHoat;

import javax.swing.*;
import java.awt.*;
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
    public static final int PANEL_HEIGHT = 125;
    public static final int PANEL_WIDTH = 450;

    public static final int TABLE_SCHEDULE = 0;
    public static final int TABLE_BASE_CLASS = 1;
    public static final int TABLE_SUBJECT_CLASS = 2;
    public static final int TABLE_TRANSCRIPT = 3;

    private static JPanel container;
    private static JFrame frame;
    private static JComboBox<String> baseClass;
    private static JComboBox<String> subjectClass;

    private static JScrollPane tableParent = null;
    private static JTable table = null;
    private static JPanel infoPanel = null;
    private static int typeTable;

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
        container.setBorder(BorderFactory.createTitledBorder("Giáo vụ"));
        frame.setContentPane(container);

        // Components
        // Buttons
        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.addActionListener(al -> {
            MainProgram.invokeGUI(MainProgram.ViewLevel.LOGIN);
            frame.dispose();
        });
        JButton changePassButton = new JButton("Đổi mật khẩu");
        changePassButton.addActionListener(new ChangePasswordListener());

        // Navigate Bar
        JPanel navigatePanel = new JPanel();
        navigatePanel.setLayout(new BoxLayout(navigatePanel, BoxLayout.X_AXIS));
        navigatePanel.add(changePassButton);
        navigatePanel.add(logoutButton);
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
        JPanel container = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        container.setLayout(springLayout);
        container.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        JPanel childPanel = new JPanel();
        childPanel.setLayout(new GridLayout(0, 2));
        container.add(childPanel);

        // Button
        JButton addClassButton = new JButton("+");
        JButton addStudentListButton = new JButton("Thêm sinh viên");
        JButton addScheduleButton = new JButton("Thêm thời khóa biểu");
        JButton showStudentListButton = new JButton("Xem danh sách lớp");
        JButton showScheduleButton = new JButton("Xem thời khóa biểu");
        JButton enrollCoursesButton = new JButton("Đăng ký môn học");

        // add buttons to panel
        container.add(addClassButton);
        childPanel.add(addStudentListButton);
        childPanel.add(addScheduleButton);
        childPanel.add(showStudentListButton);
        childPanel.add(showScheduleButton);
        childPanel.add(enrollCoursesButton);

        // Label
        JLabel baseClassLabel = new JLabel("Lớp sinh hoạt: ");
        container.add(baseClassLabel);

        // ComboBox
        JComboBox<String> classList = new JComboBox<>(Objects.requireNonNull(getClassStringArray()));
        baseClass = classList;
        baseClass.addActionListener(al -> {
            String maLop = (String) classList.getSelectedItem();//get the selected item
            if (subjectClass != null)
                updateSubjectClassComboBox(maLop, subjectClass);
        });
        container.add(classList);

        // Add Listener
        addClassButton.addActionListener(new AddBaseClassListener(getClassStringArray(), classList));
        addStudentListButton.addActionListener(new AddStudentListener(classList));
        showStudentListButton.addActionListener(new ShowStudentListListener(classList, null));
        addScheduleButton.addActionListener(new AddScheduleListener(classList));
        showScheduleButton.addActionListener(new ShowScheduleListener(classList));
        enrollCoursesButton.addActionListener(new EnrollStudentListener());

        // label constraint
        springLayout.putConstraint(SpringLayout.WEST, baseClassLabel, SPACE_SIZE, SpringLayout.WEST, container);
        springLayout.putConstraint(SpringLayout.NORTH, baseClassLabel, SPACE_SIZE, SpringLayout.NORTH, container);

        // addClassButton constraint
        springLayout.putConstraint(SpringLayout.WEST, addClassButton, 2 * SPACE_SIZE, SpringLayout.WEST, container);
        springLayout.putConstraint(SpringLayout.NORTH, addClassButton, SPACE_SIZE, SpringLayout.SOUTH, baseClassLabel);

        // comboBox constraint
        springLayout.putConstraint(SpringLayout.WEST, classList, SPACE_SIZE, SpringLayout.EAST, addClassButton);
        springLayout.putConstraint(SpringLayout.NORTH, classList, 0, SpringLayout.NORTH, addClassButton);

        // childPanel constraint
        springLayout.putConstraint(SpringLayout.WEST, childPanel, SPACE_SIZE, SpringLayout.EAST, classList);
        springLayout.putConstraint(SpringLayout.NORTH, childPanel, 0, SpringLayout.NORTH, classList);

        return container;
    }

    private static JPanel createSubjectClassPanel() {

        // Set up the panel
        JPanel container = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        container.setLayout(springLayout);
        container.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        JPanel childPanel = new JPanel();
        childPanel.setLayout(new GridLayout(0, 2));
        container.add(childPanel);

        // Button
        JButton showStudentListButton = new JButton("Xem danh sách lớp");
        JButton deleteStudentButton = new JButton("Xóa sinh viên");
        JButton addTranscriptButton = new JButton("Thêm bảng điểm");
        JButton showTranscriptButton = new JButton("Xem bảng điểm");
        JButton updateTranscriptButton = new JButton("Sửa điểm sinh viên");

        // add buttons to panel
        childPanel.add(showStudentListButton);
        childPanel.add(deleteStudentButton);
        childPanel.add(addTranscriptButton);
        childPanel.add(showTranscriptButton);
        childPanel.add(updateTranscriptButton);

        // Label
        JLabel baseClassLabel = new JLabel("Các môn học: ");
        container.add(baseClassLabel);

        // ComboBox
        List<String> data = LopMonHocDao.layDanhSachMaMonHoc((String) baseClass.getSelectedItem());
        JComboBox<String> subClassList = new JComboBox<>(data.toArray(new String[0]));
        subjectClass = subClassList;
        container.add(subClassList);

        // Add Listener
        showStudentListButton.addActionListener(new ShowStudentListListener(baseClass, subjectClass));
        deleteStudentButton.addActionListener(new DeleteStudentListener(baseClass, subjectClass));
        addTranscriptButton.addActionListener(new AddTranscriptListener(baseClass, subjectClass));
        showTranscriptButton.addActionListener(new ShowTranscriptListener(baseClass, subjectClass));
        updateTranscriptButton.addActionListener(new UpdateTranscriptListener(baseClass, subjectClass));

        // label constraint
        springLayout.putConstraint(SpringLayout.WEST, baseClassLabel, SPACE_SIZE, SpringLayout.WEST, container);
        springLayout.putConstraint(SpringLayout.NORTH, baseClassLabel, SPACE_SIZE, SpringLayout.NORTH, container);

        // comboBox constraint
        springLayout.putConstraint(SpringLayout.WEST, subClassList, 2 * SPACE_SIZE, SpringLayout.WEST, container);
        springLayout.putConstraint(SpringLayout.NORTH, subClassList, SPACE_SIZE, SpringLayout.SOUTH, baseClassLabel);

        // childPanel constraint
        springLayout.putConstraint(SpringLayout.WEST, childPanel, SPACE_SIZE, SpringLayout.EAST, subClassList);
        springLayout.putConstraint(SpringLayout.NORTH, childPanel, 0, SpringLayout.NORTH, subClassList);

        return container;
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

    public static void setTableView(JScrollPane scrollPane, JTable table, JPanel statusBar, int typeTable) {

        if (TeacherGUI.tableParent != null)
            container.remove(TeacherGUI.tableParent);

        if (TeacherGUI.infoPanel != null)
            container.remove(TeacherGUI.infoPanel);

        TeacherGUI.table = table;
        container.add(statusBar);
        container.add(scrollPane);
        TeacherGUI.tableParent = scrollPane;
        TeacherGUI.infoPanel = statusBar;

        container.repaint();
        container.revalidate();
        frame.pack();

        TeacherGUI.typeTable = typeTable;
    }

    public static JTable getTableView() {
        return table;
    }

    public static int getTypeTable() {
        return typeTable;
    }
}
