package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginDetailBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.LoginDetailDAO;
import lk.ijse.dto.LoginDetailDTO;
import lk.ijse.entity.LoginDetail;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginDetailBOimpl implements LoginDetailBO {

    private Session session;
    private Transaction transaction;
    private LoginDetailDAO loginDetailDAO;

    public LoginDetailBOimpl(){

       loginDetailDAO=DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGINDETAIL);
    }

    @Override
    public LoginDetailDTO findByPk(String pk) {
        LoginDetailDTO loginDetailDTO =null;
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

        try{
            LoginDetail loginDetail=loginDetailDAO.findByPk(session,pk);
             loginDetailDTO=new LoginDetailDTO(loginDetail.getUserID(),loginDetail.getUsername(),loginDetail.getPassword());
        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }

        }finally {

            session.close();
            return  loginDetailDTO;
        }
    }

    @Override
    public boolean update(LoginDetailDTO loginDetailDTO) {
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        try{
//           LoginDetail loginDetail=loginDetailDAO.update(session,loginDetailDTO);
//            LoginDetailDTO loginDetailDTO=new LoginDetailDTO(loginDetail.getUserID(),loginDetail.getUsername(),loginDetail.getPassword());

            transaction.commit();
            return true;
        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
            return false;
        }finally {
            session.close();
        }
    }
    }

