package com.example.user.location.problem.controller;

import com.example.user.location.problem.entities.UserLocation;
import com.example.user.location.problem.exception.IdNotFoundException;
import com.example.user.location.problem.service.IUserLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/userLocation/")
public class UserLocationController {
    @Autowired
    IUserLocation iUserLocation;

    /**
     * Endpoint for creating user location table.
     * Only users with ADMIN authority can access this endpoint.
     *
     * @return a ResponseEntity with the status code and response body
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create_data")
    public ResponseEntity<String> createTable(){
        return new ResponseEntity< >(iUserLocation.createUserLocationTable(),HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("update_data/{id}")
    public ResponseEntity<UserLocation> updateUserLocationById(@PathVariable("id") Long id,@RequestBody UserLocation userLocation) throws IdNotFoundException {
        return new ResponseEntity< >(iUserLocation.updateUserLocation(id,userLocation),HttpStatus.OK);
    }

    /**
     * Endpoint for getting the nearest user locations.
     * Only users with ADMIN or READER authority can access this endpoint.
     *
     * @param id the ID of the user
     * @return a ResponseEntity with the status code and response body
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','READER')")
    @GetMapping("get_users/{id}")
    public ResponseEntity<List<UserLocation>> getNearestUserLocation(@PathVariable("id") int id){
        return new ResponseEntity< >(iUserLocation.getNearestUsers(id),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<UserLocation> saveUserLocation(@RequestBody UserLocation userLocation){
        return new ResponseEntity< >(iUserLocation.createUserLocation(userLocation),HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserLocation>> getAllUserLocation(){
        return new ResponseEntity< >(iUserLocation.getAllUserLocation(),HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserLocationById(@PathVariable("id") Long id) throws IdNotFoundException {
        return new ResponseEntity< >(iUserLocation.deleteUserLocation(id),HttpStatus.OK);
    }
}