package com.techelevator.dao;

import com.techelevator.model.Availability;

import java.util.List;

public interface AvailabilityDao {
    List<Availability> getAvailableTimes();

    Availability createAvailability(Availability availability);
}
