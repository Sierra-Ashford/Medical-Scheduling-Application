package com.techelevator.dao;

import com.techelevator.model.Availability;

import java.util.List;

public interface AvailabilityDao {
    List<Availability> getAvailableTimes();

    Availability getAvailabilityById(int availabilityId);

    Availability createAvailability(Availability availability);

    void deleteAvailability(int availabilityId);

    Availability updateAvailability(Availability availability);
}
