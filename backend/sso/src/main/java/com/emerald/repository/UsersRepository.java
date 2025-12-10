package com.emerald.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emerald.model.Users;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findById(int id);
    Optional<Users> findByUserName(String userName);
}