package edu.pojo;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:50 PM
 * @Description
 **/

@Embeddable
class DanhSachHocPK implements Serializable {
    protected String maSinhVien;
    protected String maMon;

    DanhSachHocPK() {}

    public DanhSachHocPK(String maSinhVien, String maMon) {
        this.maSinhVien = maSinhVien;
        this.maMon = maMon;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    @Override
    public int hashCode() {
        return (getMaSinhVien().hashCode() + " " + getMaMon()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            LopSinhHoatPK other = (LopSinhHoatPK) obj;
            if (this.getMaSinhVien().equals(other.getMaSinhVien()) && this.getMaMon().equals(other.getMaLop())) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}

@Entity
public class DanhSachHoc implements Serializable {

    @EmbeddedId
    private DanhSachHocPK danhSachHocPK;

    private Float diemGK;
    private Float diemCK;
    private Float diemKhac;
    private Float diemTong;

    private SinhVien sinhVien;
    private MonHoc monHoc;

    public DanhSachHoc() {}

    public DanhSachHoc(DanhSachHocPK danhSachHocPK) {
        this.danhSachHocPK = danhSachHocPK;
    }

    public DanhSachHoc(DanhSachHocPK danhSachHocPK, Float diemGK, Float diemCK, Float diemKhac, Float diemTong, SinhVien sinhVien, MonHoc monHoc) {
        this.danhSachHocPK = danhSachHocPK;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
    }

    public DanhSachHocPK getDanhSachHocPK() {
        return danhSachHocPK;
    }

    public void setDanhSachHocPK(DanhSachHocPK danhSachHocPK) {
        this.danhSachHocPK = danhSachHocPK;
    }

    public Float getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(Float diemGK) {
        this.diemGK = diemGK;
    }

    public Float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(Float diemCK) {
        this.diemCK = diemCK;
    }

    public Float getDiemKhac() {
        return diemKhac;
    }

    public void setDiemKhac(Float diemKhac) {
        this.diemKhac = diemKhac;
    }

    public Float getDiemTong() {
        return diemTong;
    }

    public void setDiemTong(Float diemTong) {
        this.diemTong = diemTong;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }
}
