package edu.dao;

import edu.pojo.LopSinhHoat;
import edu.pojo.SinhVien;
import edu.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * edu.dao
 *
 * @Created by Long - StudentID : 18120455
 * @Date 14/06/2020 - 10:29 AM
 * @Description
 **/
public class LopSinhHoatDao {

    public static List layDanhSachLop() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from LopSinhHoat";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }

        return null;
    }

    public static void themLopSinhHoat(String maLop) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            LopSinhHoat lopSinhHoat = new LopSinhHoat(maLop);
            session.save(lopSinhHoat);

            session.getTransaction().commit();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }
    }
}
