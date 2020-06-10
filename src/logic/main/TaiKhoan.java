package logic.main;

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
    private Boolean loaiTaiKhoan;

    public TaiKhoan(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public TaiKhoan(String tenDangNhap, String matKhau, Boolean loaiTaiKhoan) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
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
