package ru.job4j.carsale.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.carsale.service.HibernateUtil;
import ru.job4j.models.Ad;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdDao implements IDao<Ad> {
    private HibernateUtil hibernateUtil = HibernateUtil.INSTANCE;

    @Override
    public Ad getById(int id) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            return session.get(Ad.class, id);
        }
    }

    @Override
    public List<Ad> getAll() {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Ad");
            return query.list();
        }
    }

    @Override
    public void create(Ad ad) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Ad ad) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(ad);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Ad ad) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(ad);
            session.getTransaction().commit();
        }
    }

    public List<Ad> getAllFiltered(String today, String carbrand) {
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            EntityManager em = session.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Ad> criteria = cb.createQuery(Ad.class);
            Root<Ad> root = criteria.from(Ad.class);
            criteria.select(root);
            List<Predicate> predicates = new ArrayList<>();
            if (today != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    long morning = sdf.parse(sdf.format(new Date())).getTime();
                    predicates.add(cb.greaterThanOrEqualTo(root.get("created"), new Timestamp(morning)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (carbrand != null) {
                try {
                    predicates.add(cb.equal(root.get("carbrand").get("id"), Integer.valueOf(carbrand)));
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }
            if (!predicates.isEmpty()) {
                criteria.where(predicates.toArray(new Predicate[0]));
            }
            em.getTransaction().commit();
            return em.createQuery(criteria).getResultList();
        }
    }

    @Override
    public void close() {
        this.hibernateUtil.closeConnection();
    }
}
