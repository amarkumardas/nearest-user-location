package com.example.user.location.problem.service;

import com.example.user.location.problem.entities.UserLocation;
import com.example.user.location.problem.exception.IdNotFoundException;

import java.util.List;

public interface IUserLocation {
    String createUserLocationTable();
    List<UserLocation> getAllUserLocation();

    UserLocation createUserLocation(UserLocation userLocation);

    UserLocation updateUserLocation(Long id, UserLocation userLocation) throws IdNotFoundException;

    List<UserLocation> getNearestUsers(int n);

    String deleteUserLocation(Long id) throws IdNotFoundException;
}
