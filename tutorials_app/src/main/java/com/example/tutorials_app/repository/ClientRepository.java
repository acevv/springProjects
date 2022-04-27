package com.example.tutorials_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tutorials_app.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
