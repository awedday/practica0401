package com.example.springmodels.repos;

import com.example.springmodels.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
    Location findLocationByAddress(String address);
}
