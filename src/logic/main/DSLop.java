package logic.main;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:50 PM
 * @Description
 **/

public class DSLop {

    private String maSinhVien;
    private LopId lopId;
    private Float diemGK;
    private Float diemCK;
    private Float diemKhac;
    private Float diemTong;

    public DSLop(String maSinhVien, LopId lopId, Float diemGK, Float diemCK, Float diemKhac, Float diemTong) {
        this.maSinhVien = maSinhVien;
        this.lopId = lopId;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public LopId getLopId() {
        return lopId;
    }

    public void setLopId(LopId lopId) {
        this.lopId = lopId;
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
}
