package lk.ijse.util;

import lk.ijse.entity.LoginDetail;
import lk.ijse.entity.Room;
import lk.ijse.entity.Rservations;
import lk.ijse.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    public  static  FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() throws HibernateException {
//        Configuration configuration= new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Room.class).addAnnotatedClass(Rservations.class).addAnnotatedClass(LoginDetail.class);
//        sessionFactory=configuration.  buildSessionFactory();

        //create configuration
        Configuration configuration = new Configuration();

        //create properties
        Properties properties = new Properties();

        //load property file using current thread
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //properties add to configure
        configuration.setProperties(properties);

        //configure annotated class
        configuration.addAnnotatedClass(LoginDetail.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(Rservations.class);
        configuration.addAnnotatedClass(Student.class);

        //build sessionfactory and assign it
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null ?
                factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;

    }
    public Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
