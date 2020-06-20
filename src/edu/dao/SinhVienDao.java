package edu.dao;

import edu.pojo.LopSinhHoat;
import edu.pojo.TaiKhoan;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import edu.pojo.SinhVien;
import edu.util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

/**
 * logic.dao
 *
 * @Created by Long - StudentID : 18120455
 * @Date 11/06/2020 - 10:20 AM
 * @Description
 **/
public class SinhVienDao {

    public static SinhVien layThongTinSinhVien(String maSinhVien) {

        SinhVien sinhVien = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            sinhVien = session.get(SinhVien.class, maSinhVien);

        } catch (HibernateException ex) {
            System.err.println(ex);
        }

        return sinhVien;
    }

    public static void themSinhVien(SinhVien sinhVien) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            // Add new account corresponding to new student
            TaiKhoan taiKhoan = new TaiKhoan(sinhVien.getMaSinhVien(), true);

            TaiKhoanDao.themTaiKhoan(taiKhoan);
            sinhVien.setTaiKhoan(taiKhoan);

            session.save(sinhVien);
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }
    }

    public static void xoaToanBoSinhVien() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            session.createSQLQuery("delete from sinhvien;").executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }
    }

    public static List<SinhVien> layDanhSachSinhVien(String maLop, String maMon) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql;
            if (maLop == null)
                hql = "from SinhVien";
            else if (maMon != null) {
                hql = "";
            } else {
                hql = "from SinhVien where maLop = :maLop";
            }

            Query query = session.createQuery(hql);
            query.setParameter("maLop", maLop);

            return query.list();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }

        return null;
    }
}
