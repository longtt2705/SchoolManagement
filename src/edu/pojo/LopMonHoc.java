package edu.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:33 PM
 * @Description
 **/

public class LopMonHoc implements Serializable {

    private Integer lopMonHocPK;
    private LopSinhHoat lopSinhHoat;
    private MonHoc monHoc;
    private Set<DanhSachHoc> danhSachHocSet = new HashSet<>(0);


    public LopMonHoc() {}

    public LopMonHoc(Integer lopMonHocPK) {
        this.lopMonHocPK = lopMonHocPK;
    }

    public LopMonHoc(LopSinhHoat lopSinhHoat, MonHoc monHoc) {
        this.lopSinhHoat = lopSinhHoat;
        this.monHoc = monHoc;
    }

    public LopMonHoc(Integer lopMonHocPK, LopSinhHoat lopSinhHoat, MonHoc monHoc) {
        this.lopMonHocPK = lopMonHocPK;
        this.lopSinhHoat = lopSinhHoat;
        this.monHoc = monHoc;
    }

    public Integer getLopMonHocPK() {
        return lopMonHocPK;
    }

    public void setLopMonHocPK(Integer lopMonHocPK) {
        this.lopMonHocPK = lopMonHocPK;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public LopSinhHoat getLopSinhHoat() {
        return lopSinhHoat;
    }

    public void setLopSinhHoat(LopSinhHoat lopSinhHoat) {
        this.lopSinhHoat = lopSinhHoat;
    }

    public Set<DanhSachHoc> getDanhSachHocSet() {
        return danhSachHocSet;
    }

    public void setDanhSachHocSet(Set<DanhSachHoc> danhSachHocSet) {
        this.danhSachHocSet = danhSachHocSet;
    }
}
