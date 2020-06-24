package edu.dao;

import edu.pojo.LopMonHoc;
import edu.pojo.MonHoc;
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
 * @Date 17/06/2020 - 6:59 PM
 * @Description
 **/
public class LopMonHocDao {

    public static List layDanhSachLopMonHoc() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from LopMonHoc";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy bảng Lớp Môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static LopMonHoc layLopMonHoc(LopMonHoc lopMonHoc) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from LopMonHoc lmh where lmh.monHoc.maMon = :monHoc and lmh.lopSinhHoat.maLop = :lopSinhHoat";

            Query query = session.createQuery(hql);
            query.setParameter("monHoc", lopMonHoc.getMonHoc().getMaMon());
            query.setParameter("lopSinhHoat", lopMonHoc.getLopSinhHoat().getMaLop());

            List<LopMonHoc> list = query.list();
            if (list.size() == 0)
                return null;

            return list.get(0);

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin lớp môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static List<LopMonHoc> layDanhSachLopMonHoc(String maLop) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from LopMonHoc lmh where lmh.lopSinhHoat.maLop = :maLop";

            Query query = session.createQuery(hql);
            query.setParameter("maLop", maLop);

            List<LopMonHoc> list = query.list();
            if (list.size() == 0)
                return null;

            return list;

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin lớp môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static void themLopMonHoc(LopMonHoc lopMonHoc) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            if (layLopMonHoc(lopMonHoc) != null) {
                JOptionPane.showMessageDialog(new JFrame(),"Lớp học này đã tồn tại",
                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            session.save(lopMonHoc);
            session.getTransaction().commit();


        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi thêm lóp học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }

    public static List<MonHoc> layDanhSachMonHoc(String maLop) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "select mh from LopMonHoc lmh inner join lmh.monHoc as mh where lmh.lopSinhHoat.maLop = :maLop";
            Query query = session.createQuery(hql);
            query.setParameter("maLop", maLop);

            return query.list();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin bảng Môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static List<String> layDanhSachMaMonHoc(String maLop) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "select lmh.monHoc.maMon from LopMonHoc lmh where lmh.lopSinhHoat.maLop = :maLop";
            Query query = session.createQuery(hql);
            query.setParameter("maLop", maLop);

            return query.list();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin bảng Môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }
}
