package edu.pojo;

import java.io.Serializable;
import java.util.HashSet;
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

    public SinhVien() {}

    public SinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public SinhVien(String maSinhVien, String hoTen, String gioiTinh, Integer chungMinhThu, TaiKhoan taiKhoan) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.chungMinhThu = chungMinhThu;
        this.taiKhoan = taiKhoan;
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
}
