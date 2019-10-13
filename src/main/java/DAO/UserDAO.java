package DAO;

import Main.*;
import Object.Users;
import Service.Service;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;



import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class UserDAO {
    private static Session session;
    private static Transaction tx;

    private boolean verification;

    public Users findById(int id) {
        return ConectDB.getSessionFactory().openSession().get(Users.class, id);
    }

    public Users operation(Users user, int i) {
        Users u = verification(user.getLogin(), user.getPassword());
        if (u == null) {
            try {
                session = ConectDB.getSessionFactory().openSession();
                tx = session.beginTransaction();
                switch (i){
                    case Service.NAME_PROCESS.create:
                        session.save(user);
                        break;
                    case Service.NAME_PROCESS.delete:
                        session.delete(user);
                        break;
                    case Service.NAME_PROCESS.update:
                        session.update(user);
                        break;
                }
                tx.commit();
            } catch (Exception e) {
                Main.LOGGER.error(e);
                if (tx != null) tx.rollback();
            } finally {
                session.close();
            }
            return u;
        }
        return u;
    }

    public Users verification(String login, String password) {
        session = ConectDB.getSessionFactory().openSession();
        tx = session.beginTransaction();
        SQLQuery query = session.createNativeQuery("SELECT * FROM public.\"Users\" as u WHERE u.login= :login  and password = :password")
                .setParameter("login", login)
                .setParameter("password", password);
        List<Object[]> l = query.list();
        if (l.size() > 0){
            Users u =  new Users(String.valueOf(l.get(0)[2]),String.valueOf(l.get(0)[5]),
                    String.valueOf(l.get(0)[3]),String.valueOf(l.get(0)[4]), (Date)l.get(0)[1]);
            u.setId(Integer.parseInt(String.valueOf(l.get(0)[0])));
            return u;
        }
        return null;
    }

    public List<Users> findAll() {
//        List<Users> users = (List<Users>) ConectDB.getSessionFactory()
//                .openSession().createQuery("from Users").list();
        return null;
    }

}
