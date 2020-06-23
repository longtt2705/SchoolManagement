package edu.dao;

import edu.pojo.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import edu.util.HibernateUtil;
import org.hibernate.query.Query;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * logic.dao
 *
 * @Created by Long - StudentID : 18120455
 * @Date 11/06/2020 - 10:20 AM
 * @Description
 **/
public class SinhVienDao {

    public static SinhVien layThongTinSinhVienByMSSV(String maSinhVien) {

        SinhVien sinhVien = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            sinhVien = session.get(SinhVien.class, maSinhVien);

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi lấy thông tin Sinh Viên",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return sinhVien;
    }

    public static SinhVien laySinhVienByCMND(Integer CMND) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "from SinhVien where chungMinhThu = :CMND";

            Query query = session.createQuery(hql);
            query.setParameter("CMND", CMND);

            List<SinhVien> list = query.list();
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

    public static void themSinhVien(SinhVien sinhVien) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            if (laySinhVienByCMND(sinhVien.getChungMinhThu()) != null) {
                JOptionPane.showMessageDialog(new JFrame(),"Số chứng minh thư đã tồn tại",
                        "Unexpected error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (layThongTinSinhVienByMSSV(sinhVien.getMaSinhVien()) != null) {
                JOptionPane.showMessageDialog(new JFrame(),"Sinh viên đã tồn tại",
                        "Duplicate value error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add new account corresponding to new student
            TaiKhoan taiKhoan = new TaiKhoan(sinhVien.getMaSinhVien(), true);

            TaiKhoanDao.themTaiKhoan(taiKhoan);
            sinhVien.setTaiKhoan(taiKhoan);

            session.save(sinhVien);
            session.getTransaction().commit();

            List<LopMonHoc> danhSachLopMonHoc = LopMonHocDao.layDanhSachLopMonHoc(sinhVien.getLopSinhHoat().getMaLop());
            if (danhSachLopMonHoc != null) {
                danhSachLopMonHoc.forEach(lopMonHoc -> {
                    DanhSachHocDao.themDanhSachHoc(new DanhSachHoc(sinhVien, lopMonHoc));
                });
            }

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi thêm Sinh Viên",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }

    public static void xoaToanBoSinhVien(String maLop) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();

            Query query = session.createSQLQuery("delete from sinhvien sv where sv.maLop = :maLop ;");
            query.setParameter("maLop", maLop);

            query.executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng Sinh Viên",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }
    }

    public static List<SinhVien> layDanhSachSinhVien(String maLop, String maMon) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql;
            if (maLop == null)
                hql = "from SinhVien";
            else if (maMon != null) {
                hql = "select sv from DanhSachHoc dsh inner join dsh.lopMonHoc as lmh inner join dsh.sinhVien as sv " +
                        "where lmh.lopSinhHoat.maLop = :maLop and lmh.monHoc.maMon = :maMon";
            } else {
                hql = "from SinhVien where maLop = :maLop";
            }

            Query query = session.createQuery(hql);
            query.setParameter("maLop", maLop);
            if (maMon != null)
                query.setParameter("maMon", maMon);

            List<SinhVien> sinhVienList = query.list();

            return sinhVienList;

        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Có lỗi khi cập nhật bảng Sinh Viên",
                    "Unexpected error", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        }

        return null;
    }

    public static SinhVien inputStudentFillForm(String maLop) {

        JTextField mssv = new JTextField();
        JTextField hoTen = new JTextField();
        JComboBox<String> gioiTinh = new JComboBox<>(new String[] {"Nam", "Nữ"});
        JTextField cmnd = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("MSSV:"));
        panel.add(mssv);
        panel.add(new JLabel("Họ và tên:"));
        panel.add(hoTen);
        panel.add(new JLabel("Giới tính:"));
        panel.add(gioiTinh);
        panel.add(new JLabel("CMND:"));
        panel.add(cmnd);

        int result = JOptionPane.showConfirmDialog(new JFrame(), panel,
                "Thông tin sinh viên", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            return new SinhVien(mssv.getText(), hoTen.getText(),
                        (String)gioiTinh.getSelectedItem(), Integer.parseInt(cmnd.getText()), new LopSinhHoat(maLop));
        }
        return null;
    }
}
