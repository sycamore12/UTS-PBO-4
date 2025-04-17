package com.firstjavaproject.ngetes.repository;

import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<User, Long> {
}
