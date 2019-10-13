package DAO;

import Main.ConectDB;
import Main.Main;
import Object.Costs;
import Service.Service;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CostsDAO {

    private static Session session;
    private static Transaction tx;

    public Costs findById(int id) {
        return ConectDB.getSessionFactory().openSession().get(Costs.class, id);
    }

    public void operation(Costs costs, int i) {
        try {
            session = ConectDB.getSessionFactory().openSession();
            tx = session.beginTransaction();
            switch (i) {
                case Service.NAME_PROCESS.create:
                    session.save(costs);
                    break;
                case Service.NAME_PROCESS.delete:
                    session.delete(costs);
                    break;
                case Service.NAME_PROCESS.update:
                    session.update(costs);
                    break;
            }
            tx.commit();
        } catch (Exception e) {
            Main.LOGGER.error(e);
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }


    public List<Object[]> findAll(int userID) {
        session = ConectDB.getSessionFactory().openSession();
        tx = session.beginTransaction();
        SQLQuery query = session.createNativeQuery("SELECT * FROM public.\"Costs\" as c WHERE c.user_id= :userID")
                .setParameter("userID", userID);
        List<Object[]> l = query.list();
        return (l.size() != 0)?l:null;
    }

}
