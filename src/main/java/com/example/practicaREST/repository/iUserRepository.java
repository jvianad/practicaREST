package com.example.practicaREST.repository;

import com.example.practicaREST.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUserRepository extends JpaRepository<User,Integer> {
}
