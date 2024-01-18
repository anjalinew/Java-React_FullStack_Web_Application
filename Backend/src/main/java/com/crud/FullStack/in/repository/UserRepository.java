package com.crud.FullStack.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.FullStack.in.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
