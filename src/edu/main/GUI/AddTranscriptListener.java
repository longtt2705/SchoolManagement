package edu.main.GUI;

import edu.dao.DanhSachHocDao;
import edu.dao.LopMonHocDao;
import edu.dao.MonHocDao;
import edu.dao.SinhVienDao;
import edu.pojo.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * edu.main.GUI
 *
 * @Created by Long - StudentID : 18120455
 * @Date 24/06/2020 - 8:55 PM
 * @Description
 **/
public class AddTranscriptListener implements ActionListener {
    JComboBox<String> baseClass;
    JComboBox<String> subClass;

    public AddTranscriptListener(JComboBox<String> baseClass, JComboBox<String> subClass) {
        this.baseClass = baseClass;
        this.subClass = subClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (baseClass.getSelectedItem() == null || subClass.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(new JFrame(),"Chưa tồn tại lớp học", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
                String maLop = (String) baseClass.getSelectedItem();
                String maMon = (String) subClass.getSelectedItem();
                LopMonHoc lopMonHoc = LopMonHocDao.layLopMonHoc(new LopMonHoc(new LopSinhHoat(maLop), new MonHoc(maMon)));

                List<String> transcriptList = readCsvFile(j.getSelectedFile().getAbsolutePath());

                transcriptList.forEach(line -> {

                    DanhSachHoc danhSachHoc = DanhSachHoc.ParseStringToList(line, lopMonHoc);

                    // update grade
                    DanhSachHocDao.capNhatDanhSachHoc(danhSachHoc);
                });

                JOptionPane.showMessageDialog(new JFrame(),"Thêm bảng điểm thành công");
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
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
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
