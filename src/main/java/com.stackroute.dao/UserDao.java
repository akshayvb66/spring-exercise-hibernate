package com.stackroute.dao;

import com.stackroute.user.User;

import java.util.List;

public interface UserDao {

    public boolean saveTrack(User user);

    public boolean deleteTrack(int id);

    public List<User> getAllTracks();

    public User getTrackById(int id);

    public boolean UpdateTrack(User user);




}
