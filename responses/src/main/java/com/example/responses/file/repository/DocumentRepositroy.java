package com.example.responses.file.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.responses.file.entity.Document;

@Repository
public interface DocumentRepositroy extends JpaRepository<Document, Long>{

	@Transactional
	Document findByFileName(String fileName);
}
