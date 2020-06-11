package edu.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import edu.pojo.SinhVien;
import edu.util.HibernateUtil;

/**
 * logic.dao
 *
 * @Created by Long - StudentID : 18120455
 * @Date 11/06/2020 - 10:20 AM
 * @Description
 **/
public class SinhVienDao {

    public static SinhVien layThongTinhSinhVien(String maSinhVien) {

        SinhVien sinhVien = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            sinhVien = session.get(SinhVien.class, maSinhVien);
        } catch (HibernateException ex) {
            System.err.println(ex);
        }

        return sinhVien;
    }
}
