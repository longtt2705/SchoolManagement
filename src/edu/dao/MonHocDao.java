package edu.dao;

import edu.pojo.LopMonHoc;
import edu.pojo.MonHoc;
import edu.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * edu.dao
 *
 * @Created by Long - StudentID : 18120455
 * @Date 17/06/2020 - 6:58 PM
 * @Description
 **/
public class MonHocDao {

    public static List layMonHoc() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from MonHoc";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng Môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static MonHoc layThongTinMonHocByMaMon(String maMon) {

        MonHoc monHoc = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            monHoc = session.get(MonHoc.class, maMon);

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin Môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return monHoc;
    }

    public static void themMonHoc(MonHoc monHoc) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            if (layThongTinMonHocByMaMon(monHoc.getMaMon()) != null) {
                JOptionPane.showMessageDialog(new JFrame(),"Môn học đã tồn tại",
                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            session.save(monHoc);
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi thêm Môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }

    public static void xoaToanBoMonHoc(String maLop) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            List<MonHoc> monHocList = LopMonHocDao.layDanhSachMonHoc(maLop);

            if (monHocList == null)
                return;

            StringBuilder sqlQuery = new StringBuilder("delete from monhoc mh where");

            for (MonHoc monHoc : monHocList) sqlQuery.append(" mh.maMon = ").append(monHoc.getMaMon());
            sqlQuery.append(";");

            Query query = session.createSQLQuery(sqlQuery.toString());
            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi xóa bảng môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }
}
