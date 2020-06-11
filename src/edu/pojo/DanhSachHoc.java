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

public class DanhSachHoc implements Serializable {

    private Integer danhSachHocPK;

    private Float diemGK;
    private Float diemCK;
    private Float diemKhac;
    private Float diemTong;

    private SinhVien sinhVien;
    private MonHoc monHoc;

    public DanhSachHoc() {}

    public DanhSachHoc(Integer danhSachHocPK) {
        this.danhSachHocPK = danhSachHocPK;
    }

    public DanhSachHoc(Integer danhSachHocPK, Float diemGK, Float diemCK, Float diemKhac, Float diemTong, SinhVien sinhVien, MonHoc monHoc) {
        this.danhSachHocPK = danhSachHocPK;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
    }

    public Integer getDanhSachHocPK() {
        return danhSachHocPK;
    }

    public void setDanhSachHocPK(Integer danhSachHocPK) {
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
