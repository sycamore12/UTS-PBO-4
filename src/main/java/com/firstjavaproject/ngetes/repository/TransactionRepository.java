package com.firstjavaproject.ngetes.repository;

import Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<User, Long> {
}