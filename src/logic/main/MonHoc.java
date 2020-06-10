package logic.main;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:45 PM
 * @Description
 **/

public class MonHoc {

    private String maMon;
    private String tenMon;
    private String phongHoc;

    public MonHoc(String maMon, String tenMon, String phongHoc) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.phongHoc = phongHoc;
    }

    public MonHoc(String maMon) {
        this.maMon = maMon;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }
}
