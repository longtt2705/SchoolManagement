package edu.dao;

import edu.pojo.DanhSachHoc;
import edu.pojo.LopMonHoc;
import edu.pojo.SinhVien;
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
 * @Date 17/06/2020 - 6:55 PM
 * @Description
 **/
public class DanhSachHocDao {

    public static List layToanBoDanhSachHoc() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from DanhSachHoc";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }

        return null;
    }

    public static LopMonHoc layDanhSachHoc(DanhSachHoc danhSachHoc) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from DanhSachHoc dsh where dsh.sinhVien.maSinhVien = " +
                    ":maSinhVien and dsh.lopMonHoc.lopMonHocPK = :lopMonHocPK";

            Query query = session.createQuery(hql);
            query.setParameter("maSinhVien", danhSachHoc.getSinhVien().getMaSinhVien());
            query.setParameter("lopMonHocPK", danhSachHoc.getLopMonHoc().getLopMonHocPK());

            List<LopMonHoc> list = query.list();
            if (list.size() == 0)
                return null;

            return list.get(0);

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin Sinh Viên",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static void themDanhSachHoc(DanhSachHoc danhSachHoc) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            if (layDanhSachHoc(danhSachHoc) != null) {
                JOptionPane.showMessageDialog(new JFrame(),"Học sinh này đã học lớp này rồi",
                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            session.save(danhSachHoc);
            session.getTransaction().commit();


            JOptionPane.showMessageDialog(new JFrame(),"Đăng ký thành công");


        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi đăng ký môn học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }

    public static void xoaDanhSachHoc(DanhSachHoc danhSachHoc) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            Query query = session.createSQLQuery("delete from danhsachhoc dsh where dsh.mssv = :mssv and dsh.lmhid = :lmhid ;");
            query.setParameter("mssv", danhSachHoc.getSinhVien().getMaSinhVien());
            query.setParameter("lmhid", danhSachHoc.getLopMonHoc().getLopMonHocPK());

            query.executeUpdate();
            session.getTransaction().commit();

            JOptionPane.showMessageDialog(new JFrame(),"Xóa sinh viên thành công");

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng Danh sách học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }

    public static List<LopMonHoc> layDanhSachLopMonHocChuaThamGia(String mssv) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "select lmh from LopMonHoc lmh where lmh.lopMonHocPK not in " +
                    "(select dsh.lopMonHoc.lopMonHocPK from DanhSachHoc dsh where dsh.sinhVien.maSinhVien = :mssv)";

            Query query = session.createQuery(hql);
            query.setParameter("mssv", mssv);
            List<LopMonHoc> lopMonHocList = query.list();

            if (lopMonHocList.size() == 0)
                return null;
            return lopMonHocList;

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin danh sách học",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }
}
