package lk.ijse.dao.custom;

import lk.ijse.entity.LoginDetail;
import org.hibernate.Session;

public interface LoginDetailDAO {
    LoginDetail findByPk(Session session, String pk);

    boolean update(Session session, LoginDetail entity);
}
