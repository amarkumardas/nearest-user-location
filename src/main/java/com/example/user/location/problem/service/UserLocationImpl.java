package com.example.user.location.problem.service;

import com.example.user.location.problem.entities.UserLocation;
import com.example.user.location.problem.exception.IdNotFoundException;
import com.example.user.location.problem.repository.IUserLocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLocationImpl implements IUserLocation{
    @Autowired
    IUserLocationRepo userLocationRepo;
    @Override
    public String createUserLocationTable() {
        userLocationRepo.deleteAll();
        return "User location table created successfully";
    }

    @Override
    public List<UserLocation> getAllUserLocation() {
        return userLocationRepo.findAll();
    }

    @Override
    public UserLocation createUserLocation(UserLocation userLocation) {
        return userLocationRepo.save(userLocation);
    }

    @Override
    public UserLocation updateUserLocation(Long id, UserLocation userLocation) throws IdNotFoundException {
        UserLocation existingLocation = userLocationRepo.findById(id).orElse(null);
        if (existingLocation != null) {
            existingLocation.setName(userLocation.getName());
            existingLocation.setLatitude(userLocation.getLatitude());
            existingLocation.setLongitude(userLocation.getLongitude());
            return userLocationRepo.save(existingLocation);
        }else {
            throw new IdNotFoundException("id not found");
        }
    }

    /**
     Returns a list of n the nearest users based on their location coordinates.
     @param n the number of the nearest users to retrieve
     @return a list of UserLocation objects representing the nearest users
     */
    @Override
    public List<UserLocation> getNearestUsers(int n) {
        List<UserLocation> userLocations = userLocationRepo.findAll();

        userLocations.sort((ul1, ul2) -> Double.compare(distance(ul1.getLatitude(), ul1.getLongitude(), 0, 0),
                                                        distance(ul2.getLatitude(), ul2.getLongitude(), 0, 0)));

        return userLocations.subList(0, Math.min(n, userLocations.size()));
    }

    /**
     * Calculates the great-circle distance between two points on a sphere using the Haversine formula.
     *
     * @param lat1 the latitude of the first point
     * @param lon1 the longitude of the first point
     * @param lat2 the latitude of the second point
     * @param lon2 the longitude of the second point
     * @return the distance between the two points in kilometers
     */
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double earthRadius = 6371; // kilometers
        return earthRadius * c;
    }
    @Override
    public String deleteUserLocation(Long id) throws IdNotFoundException {
        UserLocation existingLocation = userLocationRepo.findById(id).orElse(null);
        if(existingLocation != null){
            userLocationRepo.deleteById(id);
            return "Deleted successfully UserLocation id: "+id;
        }else {
            throw new IdNotFoundException("id not found");
        }
    }
}

