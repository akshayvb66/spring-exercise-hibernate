package com.stackroute.dao;

import com.stackroute.user.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {

    }

    @Override
    public boolean saveTrack(User user) {
        try {

            sessionFactory.getCurrentSession().save(user);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTrack(int id) {

        try {

            User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
            sessionFactory.getCurrentSession().delete(user);
            sessionFactory.getCurrentSession().flush();
            return true;

        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAllTracks() {
        List<User> allTracks = sessionFactory.getCurrentSession().createQuery("from User order by id desc").list();
        return allTracks;

    }

    @Override
    public User getTrackById(int id) {
        User user = sessionFactory.getCurrentSession().load(User.class, id);
        Hibernate.initialize(user);
        return user;
    }

    @Override
    public boolean UpdateTrack(User user) {

        sessionFactory.getCurrentSession().update(user);
        return true;
    }



}
