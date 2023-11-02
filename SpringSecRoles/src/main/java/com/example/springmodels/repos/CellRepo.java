package com.example.springmodels.repos;

import com.example.springmodels.models.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepo extends JpaRepository<Cell, Integer> {
    Cell findCellByProduct(String article);
}
