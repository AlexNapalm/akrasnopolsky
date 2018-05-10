package ru.job4j.todolist.dbmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Item;

import java.util.List;
import java.util.function.Function;

/**
 * Decorator that implements getting data by using lambda-expressions.
 */
public class LambdaDecorator implements IDbManager {
    IDbManager dbManager;

    private SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public LambdaDecorator(IDbManager dbManager) {
        this.dbManager = dbManager;
    }

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

    @Override
    public void addOrUpdate(Item item) {
        dbManager.addOrUpdate(item);
    }

    @Override
    public void delete(Item item) {
        dbManager.delete(item);
    }

    @Override
    public void close() {
        dbManager.close();
    }

    @Override
    public List<Item> getAll() {
        return this.tx(
                session -> session.createQuery("from Item").list()
        );
    }

    @Override
    public List<Item> getUndone() {
        return this.tx(
                session -> session.createQuery("from Item where done = false").list()
        );
    }

    @Override
    public void toggleDone(int id, boolean done) {
        dbManager.toggleDone(id, done);
    }
}
