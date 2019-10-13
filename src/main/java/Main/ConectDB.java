package Main;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import Object.*;

public class ConectDB {
    private static SessionFactory sessionFactory;

    private ConectDB() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
                cfg.addAnnotatedClass(Users.class);
                cfg.addAnnotatedClass(Costs.class);
                cfg.configure();
//                sessionFactory = cfg.buildSessionFactory();
//                Configuration cfg = new Configuration();
//                cfg.addAnnotatedClass(com.tutorialspoint.hibernate.entity.Employee.class);
//                configuration.addAnnotatedClass(Costs.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
                sessionFactory = cfg.buildSessionFactory(builder.build());

            } catch (Exception e) {
                Main.LOGGER.error(e);
            }
        }
        Main.LOGGER.info("Database was connect.");
        return sessionFactory;
    }
}
