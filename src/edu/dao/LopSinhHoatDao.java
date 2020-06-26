package edu.dao;

import edu.pojo.LopSinhHoat;
import edu.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

/**
 * edu.dao
 *
 * @Created by Long - StudentID : 18120455
 * @Date 14/06/2020 - 10:29 AM
 * @Description
 **/
public class LopSinhHoatDao {

    public static LopSinhHoat layLopSinhHoat(String maLop) {

        LopSinhHoat lopSinhHoat = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            lopSinhHoat = session.get(LopSinhHoat.class, maLop);

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin Lớp sinh hoạt",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return lopSinhHoat;
    }

    public static List<LopSinhHoat> layDanhSachLop() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from LopSinhHoat";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng lớp sinh hoạt",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng lớp sinh hoạt",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }
}
