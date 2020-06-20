package edu.dao;

import edu.pojo.TaiKhoan;
import edu.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * edu.dao
 *
 * @Created by Long - StudentID : 18120455
 * @Date 17/06/2020 - 6:58 PM
 * @Description
 **/
public class TaiKhoanDao {

    public static List layTaiKhoan() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from TaiKhoan";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }

        return null;
    }

    public static void themTaiKhoan(TaiKhoan taiKhoan) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.save(taiKhoan);
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }
    }
}
