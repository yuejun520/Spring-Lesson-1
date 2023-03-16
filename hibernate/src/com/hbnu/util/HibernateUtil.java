package com.hbnu.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author luanhao
 * @date 2023年02月22日 11:00
 */
public class HibernateUtil {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static  {
        configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session openSession() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
    public static void release(Session session) {
        session.close();
        sessionFactory.close();
    }
}
