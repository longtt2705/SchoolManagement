package edu.pojo;
import edu.dao.DanhSachHocDao;
import edu.dao.SinhVienDao;

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
    private Boolean ketQua;

    private SinhVien sinhVien;
    private LopMonHoc lopMonHoc;

    public DanhSachHoc() {}

    public DanhSachHoc(Integer danhSachHocPK) {
        this.danhSachHocPK = danhSachHocPK;
    }

    public DanhSachHoc(SinhVien sinhVien, LopMonHoc lopMonHoc) {
        this.sinhVien = sinhVien;
        this.lopMonHoc = lopMonHoc;
    }

    public DanhSachHoc(Integer danhSachHocPK, Float diemGK, Float diemCK, Float diemKhac, Float diemTong) {
        this.danhSachHocPK = danhSachHocPK;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
    }

    public DanhSachHoc(Integer danhSachHocPK, Float diemGK, Float diemCK, Float diemKhac, Float diemTong, Boolean ketQua) {
        this.danhSachHocPK = danhSachHocPK;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemKhac = diemKhac;
        this.diemTong = diemTong;
        this.ketQua = ketQua;
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

    public LopMonHoc getLopMonHoc() {
        return lopMonHoc;
    }

    public void setLopMonHoc(LopMonHoc lopMonHoc) {
        this.lopMonHoc = lopMonHoc;
    }

    public Boolean getKetQua() {
        return ketQua;
    }

    public void setKetQua(Boolean ketQua) {
        this.ketQua = ketQua;
    }

    public Object[] toArray(int index) {

        Object[] array = new Object[8];

        array[0] = String.valueOf(index);
        array[1] = sinhVien.getMaSinhVien();
        array[2] = sinhVien.getHoTen();
        array[3] = diemGK;
        array[4] = diemCK;
        array[5] = diemKhac;
        array[6] = diemTong;
        array[7] = ketQua ? "Đậu" : "Rớt";

        return array;
    }

    public static DanhSachHoc ParseStringToList(String line, LopMonHoc lopMonHoc) {

        String[] lineSplit = line.split(",");

        SinhVien sinhVien = SinhVienDao.layThongTinSinhVienByMSSV(lineSplit[1]);
        DanhSachHoc danhSachHoc = DanhSachHocDao.layDanhSachHoc(new DanhSachHoc(sinhVien, lopMonHoc));

        if (danhSachHoc == null)
            return null;

        danhSachHoc.setDiemGK(Float.parseFloat(lineSplit[3]));
        danhSachHoc.setDiemCK(Float.parseFloat(lineSplit[4]));
        danhSachHoc.setDiemKhac(Float.parseFloat(lineSplit[5]));
        danhSachHoc.setDiemTong(Float.parseFloat(lineSplit[6]));

        return danhSachHoc;
    }
}
