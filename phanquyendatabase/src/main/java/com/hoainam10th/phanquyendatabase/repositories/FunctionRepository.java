package com.hoainam10th.phanquyendatabase.repositories;

import com.hoainam10th.phanquyendatabase.entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {
}
