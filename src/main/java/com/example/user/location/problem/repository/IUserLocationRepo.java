package com.example.user.location.problem.repository;

import com.example.user.location.problem.entities.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLocationRepo extends JpaRepository<UserLocation,Long> {
}
