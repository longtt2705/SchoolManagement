package logic.main;

/**
 * student.edu
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 7:32 PM
 * @Description
 **/

public class SinhVien {

    private String maSinhVien;
    private String hoTen;
    private String gioiTinh;
    private Integer chungMinhThu;
    private String tenDangNhap;

    public SinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public SinhVien(String maSinhVien, String hoTen, String gioiTinh, Integer chungMinhThu, String tenDangNhap) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.chungMinhThu = chungMinhThu;
        this.tenDangNhap = tenDangNhap;
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

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }
}
