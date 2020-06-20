package edu.dao;

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
public class MonHocDao {

    public static List layMonHoc() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "from MonHoc";
            Query query = session.createQuery(hql);

            return query.list();

        } catch (HibernateException ex) {
            System.err.println(ex);
        }

        return null;
    }
}
