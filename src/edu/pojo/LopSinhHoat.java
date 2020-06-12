package edu.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * logic.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 10/06/2020 - 8:43 PM
 * @Description
 **/
public class LopSinhHoat implements Serializable {

    private String maLop;
    private Set<LopMonHoc> lopMonHocSet = new HashSet<>(0);

    public LopSinhHoat() {}

    public LopSinhHoat(String maLop) {
        this.maLop = maLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public Set<LopMonHoc> getLopMonHocSet() {
        return lopMonHocSet;
    }

    public void setLopMonHocSet(Set<LopMonHoc> lopMonHocSet) {
        this.lopMonHocSet = lopMonHocSet;
    }

}
