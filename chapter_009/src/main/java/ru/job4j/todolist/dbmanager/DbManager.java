package ru.job4j.todolist.dbmanager;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.models.Item;

import java.util.List;
import java.util.function.Function;

public enum  DbManager implements IDbManager {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(DbManager.class);
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    public void addOrUpdate(Item item) {
        try (Session session = this.factory.openSession()) {
            session.getTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        }
    }

    public void delete(Item item) {
        try (Session session = this.factory.openSession()) {
            session.getTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }
    }

    public List<Item> getAll() {
        return this.tx(
                session -> session.createQuery("from Item").list()
        );
    }

    public List<Item> getUndone() {
        return this.tx(
                session -> session.createQuery("from Item where done = false").list()
        );
    }

    public void toggleDone(int id, boolean done) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("update Item set done=:isDone where id=:itemId");
            query.setParameter("isDone", done);
            query.setParameter("itemId", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void close() {
        this.factory.close();
    }
}
