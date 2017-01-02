package com.ishan.cmpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ishan.cmpe.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
