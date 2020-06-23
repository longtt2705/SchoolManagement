package edu.pojo;

import edu.main.GUI.TeacherGUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * student.edu
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 7:32 Piu
 * @Description
 **/

public class SinhVien implements Serializable {

    private String maSinhVien;
    private String hoTen;
    private String gioiTinh;
    private Integer chungMinhThu;
    private TaiKhoan taiKhoan;
    private Set<DanhSachHoc> danhSachHocSet = new HashSet<>(0);
    private LopSinhHoat lopSinhHoat;

    public SinhVien() {}

    public SinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public SinhVien(String maSinhVien, String hoTen, String gioiTinh, Integer chungMinhThu, LopSinhHoat lopSinhHoat) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.chungMinhThu = chungMinhThu;
        this.lopSinhHoat = lopSinhHoat;
    }

    public SinhVien(String maSinhVien, String hoTen, String gioiTinh, Integer chungMinhThu, TaiKhoan taiKhoan, Set<DanhSachHoc> danhSachHocSet) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.chungMinhThu = chungMinhThu;
        this.taiKhoan = taiKhoan;
        this.danhSachHocSet = danhSachHocSet;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getChungMinhThu() {
        return chungMinhThu;
    }

    public void setChungMinhThu(Integer chungMinhThu) {
        this.chungMinhThu = chungMinhThu;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Set<DanhSachHoc> getDanhSachHocSet() {
        return danhSachHocSet;
    }

    public void setDanhSachHocSet(Set<DanhSachHoc> danhSachHocSet) {
        this.danhSachHocSet = danhSachHocSet;
    }

    public LopSinhHoat getLopSinhHoat() {
        return lopSinhHoat;
    }

    public void setLopSinhHoat(LopSinhHoat lopSinhHoat) {
        this.lopSinhHoat = lopSinhHoat;
    }

    public Object[] toArray(int index) {

        Object[] array = new Object[5];

        array[0] = String.valueOf(index);
        array[1] = maSinhVien;
        array[2] = hoTen;
        array[3] = gioiTinh;
        array[4] = chungMinhThu;

        return array;
    }

    public static SinhVien ParseStringToStudent(String line) {

        SinhVien sinhVien = new SinhVien();
        String[] lineSplit = line.split(",");

        sinhVien.setMaSinhVien(lineSplit[1]);
        sinhVien.setHoTen(lineSplit[2]);
        sinhVien.setGioiTinh(lineSplit[3]);
        sinhVien.setChungMinhThu(Integer.parseInt(lineSplit[4]));

        return sinhVien;
    }
}
