package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.LoginDetailDAO;
import lk.ijse.entity.LoginDetail;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class LoginDetailDAOimpl implements LoginDetailDAO {

    @Override
    public LoginDetail findByPk(Session session, String pk) {
        try {
            return (session.get(LoginDetail.class,pk));
        } catch (HibernateException e) {
            return  null;
        }
    }

    @Override
    public boolean update(Session session, LoginDetail entity) {
        try {
            session.update(entity);
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
}
