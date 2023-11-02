package com.example.springmodels.repos;

import com.example.springmodels.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepo extends JpaRepository<Transport, Integer> {
    Transport findTransportByNumber(String number);
}
