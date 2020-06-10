package logic.main;

/**
 * project.edu.student.main
 *
 * @Created by Long - StudentID : 18120455
 * @Date 08/06/2020 - 8:33 PM
 * @Description
 **/

class LopId implements java.io.Serializable {
    private String maLop;
    private String maMon;

    public LopId(String maLop, String maMon) {
        this.maLop = maLop;
        this.maMon = maMon;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }
}

public class Lop {

    private LopId lopId;

    public Lop(String maLop, String maMon) {
        lopId = new LopId(maLop, maMon);
    }

    public LopId getLopId() {
        return lopId;
    }

    public void setLopId(LopId lopId) {
        this.lopId = lopId;
    }
}
