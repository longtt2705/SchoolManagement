package edu.pojo;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:33 PM
 * @Description
 **/

class LopSinhHoatPK implements java.io.Serializable {

    private String maSinhVien;
    private String maLop;

    public LopSinhHoatPK() {}

    public LopSinhHoatPK(String maSinhVien, String maLop) {
        this.maSinhVien = maSinhVien;
        this.maLop = maLop;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Override
    public int hashCode() {
        return (getMaSinhVien().hashCode() + " " + getMaLop()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        try {
            LopSinhHoatPK other = (LopSinhHoatPK) obj;
            if (this.getMaSinhVien().equals(other.getMaSinhVien()) && this.getMaLop().equals(other.getMaLop())) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}

public class LopSinhHoat {

    private LopSinhHoatPK lopSinhHoatPK;
    private SinhVien sinhVien;
    private Lop lop;

    public LopSinhHoat() {}

    public LopSinhHoat(LopSinhHoatPK lopSinhHoatPK, SinhVien sinhVien, Lop lop) {
        this.lopSinhHoatPK = lopSinhHoatPK;
        this.sinhVien = sinhVien;
        this.lop = lop;
    }

    public LopSinhHoat(String maLop, String maMon) {
        lopSinhHoatPK = new LopSinhHoatPK(maLop, maMon);
    }

    public LopSinhHoatPK getLopSinhHoatPK() {
        return lopSinhHoatPK;
    }

    public void setLopSinhHoatPK(LopSinhHoatPK lopSinhHoatPK) {
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
