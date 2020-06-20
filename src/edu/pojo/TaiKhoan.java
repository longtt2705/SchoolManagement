package edu.pojo;

import javax.swing.text.StyledEditorKit;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:54 PM
 * @Description
 **/

public class TaiKhoan {

    private String tenDangNhap;
    private String matKhau;
    private Boolean loaiTaiKhoan; // true = sinh vien, false = giao vu
    private SinhVien sinhVien;

    public TaiKhoan() {}

    public TaiKhoan(String tenDangNhap, Boolean loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = tenDangNhap;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public TaiKhoan(String tenDangNhap, String matKhau, Boolean loaiTaiKhoan, SinhVien sinhVien) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.sinhVien = sinhVien;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Boolean getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(Boolean loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
