package com.manolo.sell.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manolo.sell.inventory.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
