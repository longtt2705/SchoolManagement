package edu.main.GUI;

import edu.dao.SinhVienDao;
import edu.pojo.LopSinhHoat;
import edu.pojo.SinhVien;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 20/06/2020 - 8:20 AM
 * @Description
 **/
public class AddScheduleListener implements ActionListener {

    JComboBox<String> classList;

    public AddScheduleListener(JComboBox<String> classList) {
        this.classList = classList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // create an object of JFileChooser class
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // resctrict the user to select files of all types
        j.setAcceptAllFileFilterUsed(false);

        // set a title for the dialog
        j.setDialogTitle("Select a .csv file");

        // only allow files of .csv extension
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .csv files", "csv");
        j.addChoosableFileFilter(restrict);

        // invoke the showsOpenDialog function to show the save dialog
        int r = j.showOpenDialog(null);

        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {

            try {

                List<String> scheduleList = readCsvFile(j.getSelectedFile().getAbsolutePath());
                scheduleList.forEach(line -> {

                    SinhVien sinhVien = SinhVien.ParseStringToStudent(line);
                    sinhVien.setLopSinhHoat(new LopSinhHoat((String) classList.getSelectedItem())); // Add current base class

                    // Add student
                    SinhVienDao.themSinhVien(sinhVien);
                });

                JOptionPane.showMessageDialog(new JFrame(),"Thêm danh sách thành công");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(new JFrame(),"Có lỗi xảy ra", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ioException.printStackTrace();
            }
        }
    }

    private List<String> readCsvFile(String path) throws IOException {

        File file;
        List<String> list = new ArrayList<>();

        file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;

        // Skip header
        bufferedReader.readLine();

        while ((line = bufferedReader.readLine()) != null) {

            list.add(line);
        }

        bufferedReader.close();

        return list;
    }
}
