package com.hoainam10th.phanquyendatabase.repositories;

import com.hoainam10th.phanquyendatabase.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
