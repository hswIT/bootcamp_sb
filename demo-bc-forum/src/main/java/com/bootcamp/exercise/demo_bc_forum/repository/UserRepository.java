package com.bootcamp.exercise.demo_bc_forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.exercise.demo_bc_forum.entity.UserEntity;

@Repository // One of the component for Component Scan
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}