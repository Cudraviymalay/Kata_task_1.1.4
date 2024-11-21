package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try (SessionFactory sessionFactory = Util.getSessionFactory()
             ; Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.createNativeQuery("create table if not exists Users (id int auto_increment primary key" +
                    ", name varchar(45) not null, lastName varchar(45) not null, age int)").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try (SessionFactory sessionFactory = Util.getSessionFactory()
             ; Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.createNativeQuery("drop table if exists Users").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (SessionFactory sessionFactory = Util.getSessionFactory()
             ; Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        try (SessionFactory sessionFactory = Util.getSessionFactory()
             ; Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        List<User> allUsers = new ArrayList<>();

        try (SessionFactory sessionFactory = Util.getSessionFactory()
             ; Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            allUsers = session.createNativeQuery("select * from Users").getResultList();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()
             ; Session session = sessionFactory.getCurrentSession()) {

            session.beginTransaction();
            session.createNativeQuery("truncate table users").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}