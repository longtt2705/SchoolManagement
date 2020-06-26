package edu.dao;

import edu.pojo.SinhVien;
import edu.pojo.TaiKhoan;
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
 * @Date 17/06/2020 - 6:58 PM
 * @Description
 **/
public class TaiKhoanDao {

    public static List<TaiKhoan> layTaiKhoan() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from TaiKhoan";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng Tài khoản",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static TaiKhoan layTaiKhoan(TaiKhoan taiKhoan) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "select tk from TaiKhoan tk where tk.tenDangNhap = :tendangnhap and tk.matKhau = :matkhau";
            Query query = session.createQuery(hql);
            query.setParameter("tendangnhap", taiKhoan.getTenDangNhap());
            query.setParameter("matkhau", taiKhoan.getTenDangNhap());

            List<TaiKhoan> list = query.list();
            if (list.size() == 0)
                return null;

            return list.get(0);

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin bảng Tài khoản",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static SinhVien laySinhVienTuongUng(TaiKhoan taiKhoan) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            if (!taiKhoan.getLoaiTaiKhoan()) {
                throw new HibernateException("Lỗi loại tài khoản");
            }

            String hql = "select sv from TaiKhoan tk inner join tk.sinhVien sv where tk.tenDangNhap = :tendangnhap";
            Query query = session.createQuery(hql);
            query.setParameter("tendangnhap", taiKhoan.getTenDangNhap());

            List<SinhVien> list = query.list();
            if (list.size() == 0)
                return null;

            return list.get(0);

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin bảng Tài khoản",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng Sinh Viên",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }
}
