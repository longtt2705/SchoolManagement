package edu.pojo;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:33 PM
 * @Description
 **/

public class LopSinhHoat {

    private Integer lopSinhHoatPK;
    private SinhVien sinhVien;
    private Lop lop;

    public LopSinhHoat() {}

    public LopSinhHoat(Integer lopSinhHoatPK, SinhVien sinhVien, Lop lop) {
        this.lopSinhHoatPK = lopSinhHoatPK;
        this.sinhVien = sinhVien;
        this.lop = lop;
    }

    public Integer getLopSinhHoatPK() {
        return lopSinhHoatPK;
    }

    public void setLopSinhHoatPK(Integer lopSinhHoatPK) {
        this.lopSinhHoatPK = lopSinhHoatPK;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }
}
